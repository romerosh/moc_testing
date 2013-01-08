package test.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import test.data.TestDataBaseService;

import data.contracts.IDataBaseService;
import data.contracts.repositories.RepositoryException;
import data.orm.Group;
import data.orm.Mark;
import data.orm.ORMObjectException;
import data.orm.ORMObjectFactory;
import data.orm.Student;
import data.orm.Subject;

public class SubjectsTest {
	
	
	@Test
	public void addGroupTest() throws ORMObjectException, RepositoryException {
		IDataBaseService db = new TestDataBaseService();
		
		//Create group
		Group group = ORMObjectFactory.createGroupObj(db,"IF-58A");
		int groupsCount = db.getGroups().getAll().size();
		db.getGroups().insert(group);
		assertTrue(group.getID()>0);
		assertEquals(db.getGroups().getAll().size(), groupsCount + 1);
		
		//Create subjects
		Subject subject1 = ORMObjectFactory.createSubjectObj(db,"Matan");		
		int subjCount = db.getSubjects().getAll().size();
		db.getSubjects().insert(subject1);
		assertTrue (subject1.getID()>0);
		assertEquals(db.getSubjects().getAll().size(), subjCount + 1);
		Subject subject2 = ORMObjectFactory.createSubjectObj(db,"Funkan");
	    subjCount = db.getSubjects().getAll().size();
		db.getSubjects().insert(subject2);
		assertTrue (subject2.getID()>0);
		assertEquals(db.getSubjects().getAll().size(), subjCount + 1);
		
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
		assertEquals(db.getSubjects().getAll().size(), subjCount + 1);
		stud2.AddMark(subject1, 5);
		stud2.AddMark(subject2, 5);
		assertEquals(db.getSubjects().getAll().size(), subjCount + 1);
		
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
