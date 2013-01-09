package data.db;

import data.contracts.IDataBaseService;
import data.contracts.repositories.IGroupsRepository;
import data.contracts.repositories.IMarksRepository;
import data.contracts.repositories.IStudentsRepository;
import data.contracts.repositories.ISubjectsRepository;
import data.db.repositories.GroupsRepository;
import data.db.repositories.MarksRepository;
import data.db.repositories.StudentsRepository;
import data.db.repositories.SubjectsRepository;

public class DataBaseService implements IDataBaseService {

	IGroupsRepository groupsRepository;
	IStudentsRepository studentsRepository;
	ISubjectsRepository subjectsRepository;
	IMarksRepository marksRepository;

	public DataBaseService(IDBConnectionFactory connectionFactory) {
		groupsRepository = new GroupsRepository(this, connectionFactory);
		studentsRepository = new StudentsRepository(this, connectionFactory);
		subjectsRepository = new SubjectsRepository(this, connectionFactory);
		marksRepository = new MarksRepository(this, connectionFactory);
	}

	@Override
	public IGroupsRepository Groups() {
		return groupsRepository;
	}

	@Override
	public IStudentsRepository Students() {
		return studentsRepository;
	}

	@Override
	public ISubjectsRepository Subjects() {

		return subjectsRepository;
	}

	@Override
	public IMarksRepository Marks() {
		return marksRepository;
	}

}
