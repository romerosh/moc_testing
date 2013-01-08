package data.contracts;

import data.contracts.repositories.IGroupsRepository;
import data.contracts.repositories.IStudentsRepository;
import data.contracts.repositories.ISubjectsRepository;

public interface IDataBaseService {

	IGroupsRepository getGroups();
	IStudentsRepository getStudents();
	ISubjectsRepository getSubjects();
	
} 
