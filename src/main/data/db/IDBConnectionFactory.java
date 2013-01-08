package data.db;

import java.sql.Connection;

public interface IDBConnectionFactory {
	Connection getConnection();

	void closeConnection(Connection connection);

	void dispose();
}
