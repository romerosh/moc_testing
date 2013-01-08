package data.orm;

import data.contracts.IDataBaseService;

public class ORMObject {

	protected IDataBaseService db;

	public IDataBaseService getDb() {
		return db;
	}

	public void setDb(IDataBaseService db) {
		this.db = db;
	}
	
	
	
	
}
