package com.aashita.random.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.aashita.random.connectionpool.exceptions.ConnectionLimitReachedException;
import com.aashita.random.connectionpool.exceptions.ConnectionPoolException;
import com.aashita.random.connectionpool.exceptions.ConnectionPoolInitException;



/**
 *
 * Singleton class responsible for initialization and maintaining the integrity of the ConnectionPool object
 * Each database should be getting its own Manager / Connection pool setup. 
 * 
 * TBD: whether Manager should wrap ConnectionPool methods. 
 * The purpose of the manager is to serve as a gatekeeper in initialization and access the ConnectionPool;
 * it does not seem like there is a need to obtain / maintain connections from Manager rather than from 
 * ConnectionPool itself. If it becomes apparent in the future that access to the ConnectionPool should be
 * limited even further, appropriate methods should be created. 
 * 
 * NOTE: It is NOT an intention of this class to contain any kind of business logic methods, any 
 * query to the DB should be done from an appropriate code unit. 
 */
public class ConnectionPoolManager implements IConnectionPoolManager {
	
	public static final ConnectionPoolManager INSTANCE = new ConnectionPoolManager();
	private static ConnectionPool connectionPool = null;
	private static boolean isConnPoolInitialized = false;
	private static boolean doLogConnectionPool = true;
	private Thread loggingThread;
	
	private ConnectionPoolManager () {
	
	}

	public Connection getConnection(boolean autoCommit) throws ConnectionPoolException {
		
		if (!isConnPoolInitialized()) {
			try {
				initializeConnectionPool();
			}
			catch (ConnectionPoolInitException e){
				ConnectionHelper.errorLog(this.getClass().getName() + ".getConnection(): "+e.getMessage());
				throw new ConnectionPoolException(this.getClass().getName() + ": " +  e.getMessage());
			}
		}
		
		//allow several attempts to get connection before reporting an error
		int retryAttempts = 1;
		Connection conn = null;
		//TODO Maybe put number of attempts to the build.properties
		while (retryAttempts <= 5) {
			try {
				conn = connectionPool.getConnection();
				conn.setAutoCommit(autoCommit);
				ConnectionHelper.traceLog(this.getClass().getName()+ ": connection obtained");
				break;
			}
			
			catch (ConnectionLimitReachedException le){
				ConnectionHelper.errorLog(this.getClass().getName() + ".getConnection(): No connections available, attempt "+retryAttempts);
				if (retryAttempts == 5) {
					ConnectionHelper.errorLog(this.getClass().getName() + ".getConnection(): Unable to obtain connection after 5 consequtive attempts");
					//if Connection Pool is busy - throw exception and let user try again later;
					//in a more drastic scenarion, an attempt can be made here to delete all connections by calling
					//closeAllConnections() method
					throw new ConnectionPoolException(this.getClass().getName() + ": " +  le.getMessage());
				}
				retryAttempts++;
			}
			catch (SQLException se) {
				ConnectionHelper.errorLog(this.getClass().getName() + ".getConnection(): "+se.getMessage());
				throw new ConnectionPoolException(this.getClass().getName() + ": " +  se.getMessage());
			}
		}
		return conn;
	}


	public void releaseConnection(Connection conn) { 
		ConnectionHelper.traceLog(this.getClass().getName()+ ": connection released; => "+ connectionPool);
		connectionPool.free(conn);
	}
	
	public void closeAllConnections() {
		ConnectionHelper.traceLog(this.getClass().getName()+ ": all connections closed");
		connectionPool.closeAllConnections();
	}
	
	private void initializeConnectionPool() throws ConnectionPoolInitException {
		try {
			ResourceBundle rb = ResourceBundle.getBundle("build");
			String jdbcDriver = rb.getString("JDBC_DRIVER");
			ConnectionHelper.traceLog("JDBC_DRIVER: "  + jdbcDriver);
			String jdbcUrl = rb.getString("JDBC_URL");
			ConnectionHelper.traceLog("JDBC_URL: "  + jdbcUrl);
			String jdbcUserName = rb.getString("DB_USERNAME");
			ConnectionHelper.traceLog("DB_USERNAME: "  + jdbcUserName);
			String jdbcPassword = rb.getString("DB_PASSWORD");
			ConnectionHelper.traceLog("DB_PASSWORD: "  + " Yes, you'd wish :)");
			String initConnections = rb.getString("CONNECTION_POOL_INIT_CONNECTIONS");
			ConnectionHelper.traceLog("CONNECTION_POOL_INIT_CONNECTIONS: "  + initConnections);
			String maxConnections = rb.getString("CONNECTION_POOL_MAX_CONNECTIONS");
			ConnectionHelper.traceLog("CONNECTION_POOL_MAX_CONNECTIONS: "  + maxConnections);
			String waitIfBusy = rb.getString("CONNECTION_POOL_WAIT_IF_BUSY");
			ConnectionHelper.traceLog("CONNECTION_POOL_WAIT_IF_BUSY: "  + waitIfBusy);
			
			connectionPool = new ConnectionPool(
					jdbcDriver, 
					jdbcUrl, 
					jdbcUserName, 
					jdbcPassword, 
					Integer.parseInt(initConnections), 
					Integer.parseInt(maxConnections), 
					Boolean.getBoolean(waitIfBusy));
			
			setConnPoolInitialized(true);
			ConnectionHelper.traceLog("Sparc Connection Pool initialized "  + connectionPool.toString());
			
			Runnable r = new Runnable() {
				public void run() {
					try {
						logConnectionPool();
					} catch (Exception e) {
						ConnectionHelper.errorLog(this.getClass().getName()  + " error running monitoring thread ", e);
					}
				}
			};
			ConnectionHelper.infoLog("Starting Connection Pool logging thread");
			
			
			loggingThread = new Thread(r);
			loggingThread.start();
		}
		catch (MissingResourceException e) {
			ConnectionHelper.errorLog(this.getClass().getName()+ ".getConnection(): "+e.getMessage());
			throw new ConnectionPoolInitException (e.getMessage());
		}
		catch (SQLException se) {
			ConnectionHelper.errorLog(this.getClass().getName()+ ".getConnection(): "+se.getMessage());
			throw new ConnectionPoolInitException (se.getMessage());
		}
	}
	
	
	private void logConnectionPool() {
		while (doLogConnectionPool) {
			ConnectionHelper.infoLog("Sparc Connection Pool status "  + connectionPool.toString());
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); 
			}
		}
	}
	
	public void stopConnectionPoolLogging() {
		doLogConnectionPool = false;
		ConnectionHelper.infoLog("Connection Pool logging stopped");
		loggingThread.interrupt();
	}

	private static boolean isConnPoolInitialized() {
		return isConnPoolInitialized;
	}
	
	private static void setConnPoolInitialized(boolean cpIsInitialized) {
		isConnPoolInitialized = cpIsInitialized;
	}
}
