package test.data;

import java.util.ArrayList;
import java.util.Collection;

import data.contracts.IDataBaseService;
import data.contracts.repositories.IStudentsRepository;
import data.contracts.repositories.RepositoryException;
import data.orm.Group;
import data.orm.Mark;
import data.orm.ORMObjectException;
import data.orm.Student;

public class TestStudentsRepository implements IStudentsRepository {

	Collection<Student> students = new ArrayList<Student>();
	IDataBaseService db;

	public TestStudentsRepository(IDataBaseService db) {
		this.db = db;
	}

	@Override
	public Collection<Student> getAll() {
			return students;
	}

	@Override
	public Student getByID(int ID) {
		for (Student st : students) {
			if (st.getID() == ID)
				return st;
		}
		return null;
	}

	private int MaxStudentID() {
		if (students.size() == 0) {
			return 0;
		}
		int max = students.iterator().next().getID();
		for (Student st : students) {

			if (st.getID() > max) {
				max = st.getID();
			}

		}
		return max;
	}

	@Override
	public void insert(Student obj) {
		obj.setID(MaxStudentID() + 1);
		obj.setDb(db);
		students.add(obj);
	}

	@Override
	public void remove(int ID) {
		students.remove(getByID(ID));

	}

	@Override
	public void update(Student obj) {
		Student st = this.getByID(obj.getID());
		st.setDb(null);
		try {
			st.setName(obj.getName());
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		st.setDb(this.db);
	}


	@Override
	public Collection<Mark> GetMarks(Student student) throws RepositoryException {
		Collection<Mark> allmarks = new ArrayList<Mark>();
		
		try {
			for (Mark m : db.Marks().getAll()) {
				if (student.getID() == m.getStudent_id()) {
					allmarks.add(m);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allmarks;
	}




	@Override
	public double getAverageMark(Student student) throws ORMObjectException, RepositoryException {
		Collection<Mark> marks = new ArrayList<>();
		marks = this.getByID(student.getID()).GetMarks();
		double avg = 0;
		for (Mark m : marks) {
			avg += m.getMark();
		}
		return avg/marks.size();
	}

	@Override
	public Student getByName(String name, String surname)
			throws RepositoryException {
		for (Student st : students) {
			if (st.getName().equals(name) && st.getName().equals(surname))
				return st;
		}
		return null;
	}

	@Override
	public boolean attach(Student obj) throws RepositoryException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateStudent(Student student, String new_name,
			String new_surname) throws RepositoryException {
		Student st = this.getByID(student.getID());
		st.setDb(null);
		try {
			st.setName(new_name);
			st.setSurname(new_surname);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		st.setDb(this.db);
		
	}

}
