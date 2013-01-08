package test.data;

import data.contracts.IDataBaseService;
import data.contracts.repositories.IGroupsRepository;
import data.contracts.repositories.IStudentsRepository;
import data.contracts.repositories.ISubjectsRepository;

public class TestDataBaseService implements IDataBaseService {

	private IGroupsRepository groups = new TestGroupsRepository(this);
    private IStudentsRepository students = new TestStudentsRepository(this);
    private ISubjectsRepository subjects = new TestSubjectsRepository(this);
	
	@Override
	public IGroupsRepository getGroups() {
		return groups;
	}

	@Override
	public IStudentsRepository getStudents() {
		// TODO Auto-generated method stub
		return students;
	}

	@Override
	public ISubjectsRepository getSubjects() {
		// TODO Auto-generated method stub
		return subjects;
	}
	

}
