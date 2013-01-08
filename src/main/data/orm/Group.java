package data.orm;

import java.util.Collection;

import data.contracts.IDataBaseService;
import data.contracts.repositories.RepositoryException;

public class Group extends ORMObject {
	private int ID;
	private String Name;

	public Group() {

	}

	public Group(String group_name) {
		this.Name = group_name;
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
		if(this.db != null){
			
			this.db.getGroups().update(this);
		}
			
		
		
	}

	public void addStudent(Student student) throws ORMObjectException {
		if (this.db != null) {
			try {
				this.db.getGroups().addStudent(this, student);
			} catch (RepositoryException e) {
				throw new ORMObjectException("The student can not add to group");
			}

		} else
			throw new ORMObjectException(
					"This student was not linked with repository");
	}

	public void removeStudent(Student student) throws ORMObjectException, RepositoryException {
		if (this.db != null) {
			this.db.getGroups().removeStudent(this, student);
		} else
			throw new ORMObjectException(
					"This student was not linked with repository");
	}

	public Student getStudent(Student student) throws ORMObjectException, RepositoryException {
		if (this.db != null) {
			return this.db.getStudents().getByID(this.ID);
		} else
			throw new ORMObjectException(
					"This student was not linked with repository");
	}

	public Collection<Student> getStudents() throws ORMObjectException, RepositoryException {
		if (this.db != null) {
			return this.db.getGroups().getAllByGroup(this);
		} else
			throw new ORMObjectException(
					"This student was not linked with repository");
	}

	public double getAverageMark() throws ORMObjectException, RepositoryException {
		if (this.db != null) {
			return this.db.getGroups().getAverageMark(this.ID);
		} else
			throw new ORMObjectException(
					"This student was not linked with repository");
	}

	@Override
	public String toString() {
		return "Group [ID=" + ID + ", Name=" + Name + "]";
	}

}
