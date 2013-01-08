package data.db.impl;

import javax.sql.DataSource;
import org.postgresql.ds.PGPoolingDataSource;

public class PGDataSource {
	private PGPoolingDataSource source = null;
	
	private PGPoolingDataSource createSource(String dataSourceName,String serverName,String dbName,String user,String password)
	{
		if(PGPoolingDataSource.getDataSource(dataSourceName)!=null)
			return PGPoolingDataSource.getDataSource(dataSourceName);
		PGPoolingDataSource source  = new PGPoolingDataSource();
		source.setDataSourceName(dataSourceName);
		source.setDatabaseName(dbName);
		source.setUser(user);
		source.setPassword(password);
		source.setMaxConnections(100);
		source.setServerName(serverName);
		
		return source;
	}

	public PGDataSource(String host, String db, String user, String password)
	{
		source = createSource("jdbc:postgresql",host,db,user,password);
	}
	
	public DataSource getDataSource()
	{
		return (DataSource)source;
	}
	
	public void close()
	{
		if(source!=null)
			source.close();
	}
}
