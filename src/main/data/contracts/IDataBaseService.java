package data.contracts;

import data.contracts.repositories.IGroupsRepository;
import data.contracts.repositories.IMarksRepository;
import data.contracts.repositories.IStudentsRepository;
import data.contracts.repositories.ISubjectsRepository;

public interface IDataBaseService {

	IGroupsRepository Groups();
	IStudentsRepository Students();
	ISubjectsRepository Subjects();
	IMarksRepository Marks();
} 
