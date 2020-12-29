package com.aashita.random.connectionpool;

import java.sql.Connection;

import com.aashita.random.connectionpool.exceptions.ConnectionPoolException;


public interface IConnectionPoolManager {
	public Connection getConnection(boolean autoCommit) throws ConnectionPoolException;
	public void releaseConnection (Connection conn);
	public void closeAllConnections ();
}
