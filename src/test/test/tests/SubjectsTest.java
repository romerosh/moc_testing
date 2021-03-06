package test.tests;

import static org.junit.Assert.*;
import log.SimpleLoggerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.data.TestDataBaseService;
import test.log.TestLogProvider;

import data.contracts.IDataBaseService;
import data.contracts.repositories.RepositoryException;
import data.orm.Group;
import data.orm.Mark;
import data.orm.ORMObjectException;
import data.orm.ORMObjectFactory;
import data.orm.Student;
import data.orm.Subject;

public class SubjectsTest {
	
	@Before
	public void init() {
		SimpleLoggerFactory.Initialize(TestLogProvider.prepareProvider());
	}

	@After
	public void destroy() {
		SimpleLoggerFactory.Dispose();
	}
	@Test
	public void addSubjectTest() throws ORMObjectException, RepositoryException {
		IDataBaseService db = new TestDataBaseService();
		
		//Create group
		Group group = ORMObjectFactory.createGroupObj(db,"IF-58A");
		int groupsCount = db.Groups().getAll().size();
		db.Groups().insert(group);
		assertTrue(group.getID()>0);
		assertEquals(db.Groups().getAll().size(), groupsCount + 1);
		
		//Create subjects
		Subject subject1 = ORMObjectFactory.createSubjectObj(db,"Matan");		
		int subjCount = db.Subjects().getAll().size();
		db.Subjects().insert(subject1);
		assertTrue (subject1.getID()>0);
		assertEquals(db.Subjects().getAll().size(), subjCount + 1);
		Subject subject2 = ORMObjectFactory.createSubjectObj(db,"Funkan");
	    subjCount = db.Subjects().getAll().size();
		db.Subjects().insert(subject2);
		assertTrue (subject2.getID()>0);
		assertEquals(db.Subjects().getAll().size(), subjCount + 1);
		
		//Create students
		Student stud1 = ORMObjectFactory.createStudentObj(db,"Marianna","Roshchenko");
		assertTrue (stud1.getID()>0);
		Student stud2 = ORMObjectFactory.createStudentObj(db,"Vasia","Pupkin");	
		assertTrue (stud2.getID()>0);
		
		// Add students to group
		int studCount = group.getStudents().size();
		group.addStudent(stud1);	
		group.addStudent(stud2);
		assertEquals(group.getStudents().size(), studCount + 2);
		
					
		// Insert marks
		stud1.AddMark(subject1, 5);
		stud1.AddMark(subject2, 4);
		assertEquals(db.Subjects().getAll().size(), subjCount + 1);
		stud2.AddMark(subject1, 5);
		stud2.AddMark(subject2, 5);
		assertEquals(db.Subjects().getAll().size(), subjCount + 1);
		
		//Average mark
		stud1.getAverage_Mark();
		assertEquals(stud1.getAverage_Mark(), 4.5, 0.0001);
		stud2.getAverage_Mark();
		assertEquals(stud2.getAverage_Mark(), 5,0.0001);
		
		// Average mark by group
		group.getAverageMark();
		assertEquals(group.getAverageMark(), 4.75,0.0001);
	}
	
	

}
