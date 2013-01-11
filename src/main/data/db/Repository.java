package data.db;

import java.sql.Connection;
import java.sql.SQLException;

import log.ILogger;
import log.SimpleLoggerFactory;

import data.contracts.IDataBaseService;
import data.contracts.repositories.RepositoryException;
import data.contracts.repositories.RepositoryException.err_enum;

public class Repository {
	private final static ILogger log = SimpleLoggerFactory.getLogger();

	protected Connection connection;
	protected boolean isClosed;
	protected boolean isCommited;
	protected IDBConnectionFactory connectionFactory;
	protected IDataBaseService dataBaseService;
	public Repository(IDataBaseService dataBaseService,IDBConnectionFactory connectionFactory) {
		this.dataBaseService = dataBaseService;
		this.connectionFactory = connectionFactory;
		connection = null;
		isClosed = true;
		isCommited = true;
		
	}

	protected void throwable(Exception e, err_enum err)
			throws RepositoryException {
		
		if (e instanceof RepositoryException)
			throw (RepositoryException) e;
		else
			throw new RepositoryException(err);
	}

	protected void throwable(Exception e, String text)
			throws RepositoryException {
		log.error(e.getMessage());
		
		if (e instanceof RepositoryException)
			throw (RepositoryException) e;
		else
			throw new RepositoryException(text);
	}

	protected void rollback(Connection c) throws RepositoryException {
		try {
			if (c != null && !c.isClosed())
				c.rollback();
			throw new RepositoryException(
					RepositoryException.err_enum.c_transaction_err);
		} catch (SQLException sqx) {
		}
	}

	protected void commit(Connection c) throws SQLException {
		if (c != null && this.isCommited)
			c.commit();
	}

	protected Connection getConnection() throws RepositoryException {
		if (connection != null)
			return connection;
		Connection conn = connectionFactory.getConnection();
		try {
			if (conn == null) {
				throw new RepositoryException(
						RepositoryException.err_enum.c_connection_invalid);
			}
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RepositoryException(
					RepositoryException.err_enum.c_connection_invalid);
		}

		return conn;
	}

	protected void closeConnection(Connection c) {
		if (isClosed && c != null)
			connectionFactory.closeConnection(c);
	}
}
