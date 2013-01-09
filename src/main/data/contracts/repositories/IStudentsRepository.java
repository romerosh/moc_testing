package data.contracts.repositories;

import java.util.Collection;

import data.orm.Group;
import data.orm.Mark;
import data.orm.ORMObjectException;
import data.orm.Student;
import data.orm.Subject;

public interface IStudentsRepository extends IRepository<Student> {

	public Collection <Mark> GetMarks (Student student) throws RepositoryException;
	public Collection <Mark> GetAllMarks () throws RepositoryException;
	public void AddMark(Mark mark) throws RepositoryException;
	public void RemoveMark(int ID) throws RepositoryException;
	double getAverageMark(Student student) throws ORMObjectException, RepositoryException;
Student getByName(String name, String surname) throws RepositoryException;
}
