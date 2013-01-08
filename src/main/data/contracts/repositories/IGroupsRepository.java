package data.contracts.repositories;

import java.util.Collection;

import data.orm.Group;
import data.orm.ORMObjectException;
import data.orm.Student;

public interface IGroupsRepository extends IRepository<Group> {

	Group getByName(String name) throws RepositoryException;

	double getAverageMark(int groupID) throws RepositoryException;

	Collection<Student> getAllByGroup(Group newGroup)
			throws RepositoryException;

	void removeStudent(Group group, Student student) throws RepositoryException;

	void addStudent(Group group, Student student) throws RepositoryException;
}
