package test.data;

import java.util.ArrayList;
import java.util.Collection;

import data.contracts.IDataBaseService;
import data.contracts.repositories.IMarksRepository;
import data.contracts.repositories.RepositoryException;
import data.orm.Mark;
import data.orm.Student;

public class TestMarksRepository implements IMarksRepository {

	Collection<Mark> marks = new ArrayList<Mark>();
	IDataBaseService db;
	
	public TestMarksRepository(IDataBaseService db) {
		this.db = db;
	}

	private int MaxMarkID() {
		if (marks.size() == 0) {
			return 0;
		}
		int max = marks.iterator().next().getID();
		for (Mark m : marks) {

			if (m.getID() > max) {
				max = m.getID();
			}
		}
		return max;
	}
	@Override
	public Collection<Mark> getAll() throws RepositoryException {
		return marks;
	}

	@Override
	public Mark getByID(int ID) throws RepositoryException {
		for(Mark m : marks){
			if(m.getID() == ID)
				return m;
		}
		return null;
	}

	@Override
	public void insert(Mark obj) throws RepositoryException {
		obj.setID(MaxMarkID());
		marks.add(obj);
	}

	@Override
	public void remove(int ID) throws RepositoryException {
		marks.remove(getByID(ID));
	}

	@Override
	public void update(Mark obj) throws RepositoryException {
		marks.remove(getByID(obj.getID()));
		marks.add(obj);
	}

	@Override
	public boolean attach(Mark obj) throws RepositoryException {
		if(getByID(obj.getID()) == null)
			return false;
		
		return true;
	}

	

}
