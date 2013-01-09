package data.orm;

import data.contracts.repositories.RepositoryException;

public class Subject extends ORMObject {
	private String SubjName;
	private int ID;

	public Subject() {

	}

	public Subject(String subj_name) {
		this.SubjName = subj_name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getSubjName() {
		return SubjName;
	}

	public void setSubjName(String subjName) throws RepositoryException {
		SubjName = subjName;
		if (this.db != null) {
			this.db.getSubjects().update(this);
		}
	}

}
