package data.contracts.repositories;

import java.util.Collection;

import data.orm.Group;
import data.orm.Mark;
import data.orm.ORMObjectException;
import data.orm.Student;
import data.orm.Subject;

public interface IStudentsRepository extends IRepository<Student> {

	public Collection <Mark> GetMarks (Student student) throws RepositoryException;
	public void AddMark(Mark mark) throws RepositoryException;
	public void RemoveMark(Mark mark) throws RepositoryException;
	double getAverageMark(Student student) throws ORMObjectException, RepositoryException;

}
