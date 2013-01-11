package data.contracts.repositories;

import java.util.Collection;

import data.orm.Group;
import data.orm.Mark;
import data.orm.ORMObjectException;
import data.orm.Student;
import data.orm.Subject;

public interface IStudentsRepository extends IRepository<Student> {

	public Collection<Mark> GetMarks(Student student)
			throws RepositoryException;

	public double getAverageMark(Student student) throws ORMObjectException,
			RepositoryException;

	public Student getByName(String name, String surname) throws RepositoryException;

	void updateStudent (Student student, String new_name, String new_surname) throws RepositoryException;

	
}
