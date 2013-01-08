package data.orm;

import data.contracts.IDataBaseService;
import data.contracts.repositories.RepositoryException;

public class ORMObjectFactory {

	public static Mark createMarkObj(IDataBaseService db, Subject s,Student st, int mark) throws RepositoryException {
		Mark m = new Mark(s,st,mark); 
		db.getStudents().AddMark(m);
		return m;
	}
	public static Group createGroupObj(IDataBaseService db, String groupName) throws RepositoryException {
		Group g = new Group(groupName);
		db.getGroups().insert(g);
		return g;
	}
	
	public static Student createStudentObj(IDataBaseService db,String name, String sName) throws RepositoryException{
		Student st = new Student(name,sName);
		db.getStudents().insert(st);
		return st;
	}
	
	public static Subject createSubjectObj(IDataBaseService db, String name) throws RepositoryException{
		Subject s = new Subject(name);
		db.getSubjects().insert(s);
		return s;
	}
	
}
