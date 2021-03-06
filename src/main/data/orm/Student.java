package data.orm;

import java.util.ArrayList;
import java.util.Collection;

import data.contracts.repositories.RepositoryException;

public class Student extends ORMObject {
	private int ID;
	private String Name;
	private String Surname;

	public Student() {

	}

	public Student(String name, String surname) {
		this.Name = name;
		this.Surname = surname;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) throws RepositoryException {
		Name = name;
		if (this.db != null) {
			this.db.Students().update(this);
		}
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) throws RepositoryException {
		Surname = surname;
		if (this.db != null) {
			this.db.Students().update(this);
		}
	}

	public double getAverage_Mark() throws ORMObjectException,
			RepositoryException {
		return this.db.Students().getAverageMark(this);
	}

	public Collection<Mark> GetMarks() throws ORMObjectException,
			RepositoryException {
		if (this.db != null) {
			return this.db.Students().GetMarks(this);
		} else
			throw new ORMObjectException(
					"This student was not linked with repository");

	}

	public Mark AddMark(Subject s, int mark) throws ORMObjectException,
			RepositoryException {

		if (this.db != null) {
			return ORMObjectFactory.createMarkObj(db, s, this, mark);
		}

		else
			throw new ORMObjectException(
					"This student was not linked with repository");
	}

	public void RemoveMark(Mark mark) throws ORMObjectException,
			RepositoryException {
		if (this.db != null) {
			this.db.Students().remove(mark.getID());
		} else
			throw new ORMObjectException(
					"This student was not linked with repository");
	}

}
