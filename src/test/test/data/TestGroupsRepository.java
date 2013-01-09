package test.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

import data.contracts.IDataBaseService;
import data.contracts.repositories.IGroupsRepository;
import data.contracts.repositories.RepositoryException;
import data.orm.Group;
import data.orm.ORMObjectException;
import data.orm.Student;

public class TestGroupsRepository implements IGroupsRepository {

	Collection<Group> groups = new ArrayList<Group>();
	HashMap<Student, Group> stBygroup = new HashMap<Student, Group>();
	IDataBaseService db;

	public TestGroupsRepository(IDataBaseService db) {
		this.db = db;
	}

	@Override
	public Collection<Group> getAll() {
		// TODO Auto-generated method stub
		return groups;
	}

	@Override
	public Group getByID(int ID) {
		for (Group g : groups) {
			if (g.getID() == ID)
				return g;
		}
		return null;
	}

	@Override
	public void insert(Group obj) {
		Random generator = new Random();
		obj.setID(generator.nextInt(100) + 10);
		obj.setDb(db);
		groups.add(obj);
	}

	@Override
	public void remove(int ID) {
		groups.remove(getByID(ID));

	}

	@Override
	public void update(Group obj) throws RepositoryException {
		Group g = this.getByID(obj.getID());
		g.setName(obj.getName());
	}

	@Override
	public Group getByName(String name) {
		for (Group g : groups) {
			if (g.getName().equals(name))
				return g;
		}
		return null;
	}

	@Override
	public double getAverageMark(int groupID) {

		try {
			Group g = getByID(groupID);
			double avg = 0.0;
			for (Student st : g.getStudents()) {
				avg += st.getAverage_Mark();
			}
			return avg / (g.getStudents().size() * 1.0);
		} catch (ORMObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void removeStudent(Group group, Student student) {
		this.stBygroup.remove(student);

	}

	@Override
	public void addStudent(Group group, Student student)
			throws RepositoryException {
		if (this.stBygroup.get(student) == null)
			this.stBygroup.put(student, group);
		else
			throw new RepositoryException("Student has already had in group");

	}

	@Override
	public Collection<Student> getAllByGroup(Group group) {
		Iterator<Entry<Student, Group>> i = this.stBygroup.entrySet()
				.iterator();
		Collection<Student> students = new ArrayList<Student>();
		while (i.hasNext()) {
			Entry<Student, Group> sg = i.next();
			if (sg.getValue() == group) {
				students.add(sg.getKey());
			}
		}
		return students;
	}

	@Override
	public boolean attach(Group obj) throws RepositoryException {
		
		return false;
	}
}
