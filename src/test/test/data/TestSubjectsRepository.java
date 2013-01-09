package test.data;

import java.util.ArrayList;
import java.util.Collection;

import data.contracts.IDataBaseService;
import data.contracts.repositories.ISubjectsRepository;
import data.contracts.repositories.RepositoryException;
import data.orm.Student;
import data.orm.Subject;


public class TestSubjectsRepository implements ISubjectsRepository {
	Collection<Subject> subjects = new ArrayList<Subject>();
	IDataBaseService db;
	
	public TestSubjectsRepository (IDataBaseService db) {
		this.db = db;
	}

	@Override
	public Collection<Subject> getAll() {
		// TODO Auto-generated method stub
		return subjects;
	}

	@Override
	public Subject getByID(int ID) {
		for (Subject sub : subjects) {
			if (sub.getID() == ID)
				return sub;
		}
		return null;
	}

	private int MaxSubjectID() {
		if (subjects.size() == 0) {
			return 0;
		}
		int max = subjects.iterator().next().getID();
		for (Subject sub : subjects) {

			if (sub.getID() > max) {
				max = sub.getID();
			}

		}
		return max;
	}

	
	@Override
	public void insert(Subject obj) {
		obj.setID(MaxSubjectID() + 1);
		obj.setDb(db);
		subjects.add(obj);

	}

	@Override
	public void remove(int ID) {
		subjects.remove(getByID(ID));
		
	}

	@Override
	public void update(Subject obj) {
		Subject sub = this.getByID(obj.getID());
		sub.setDb(null);
		try {
			sub.setSubjName(obj.getSubjName());
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sub.setDb(db);
	}
	
	@Override
	public Subject getSubjectByName (String subject_name)
	{
		for (Subject sub : subjects) {
			if (sub.getSubjName().equals(subject_name))
				return sub;
		}
		return null;
	}
	

}
