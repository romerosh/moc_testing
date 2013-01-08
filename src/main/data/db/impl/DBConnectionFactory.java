package data.db.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import data.db.IDBConnectionFactory;

public class DBConnectionFactory implements IDBConnectionFactory {

	DataSource source;

	public DBConnectionFactory(DataSource source) {
		this.source = source;
	}

	@Override
	public Connection getConnection() {
		try {
			return this.source.getConnection();
		} catch (SQLException e) {
		}
		return null;
	}

	@Override
	public void closeConnection(Connection connection) {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {

		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
