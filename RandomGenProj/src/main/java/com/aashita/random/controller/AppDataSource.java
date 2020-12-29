package com.aashita.random.controller;

/**
 * @author Sharan Patil
 *
 */

import java.sql.Connection;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AppDataSource {
	private static InitialContext context;
	private static String dbName;
	private static AppDataSource self = null;
	private static DataSource aDataSource;

	private void initialize() throws NamingException {
		context = new InitialContext();
		ResourceBundle bundle = ResourceBundle.getBundle("prugiapp");
		aDataSource =
			(DataSource) context.lookup(bundle.getString("JDBC_NAME"));
	}

	public Connection getConnection() throws Exception {
		return aDataSource.getConnection();
	}

	public void releaseConnection(Connection con) {
		try {
			if (con != null && !con.isClosed())
				con.close();
		} catch (Exception e) {
			System.err.println(
				"Error in DBHelper.releaseConnection." + e.getMessage());
		}
	}

	/**
	 * Gets a Singleton
	 * 
	 * @return ParisDataSource
	 */
	public synchronized static AppDataSource singleton() {
		try {
			if (self == null) {
				self = new AppDataSource();
				self.initialize();
			}
		} catch (Exception e) {
			throw new Error(
				"Loading Failure: SparcDataSource " + e.getMessage());
		}
		return self;
	}
}
