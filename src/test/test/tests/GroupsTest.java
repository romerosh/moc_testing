package test.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import test.data.TestDataBaseService;

import data.contracts.IDataBaseService;
import data.contracts.repositories.RepositoryException;
import data.orm.Group;
import data.orm.Mark;
import data.orm.ORMObjectException;
import data.orm.ORMObjectFactory;
import data.orm.Student;

public class GroupsTest {


	@Before
	public void init(){
	
	}
	
	@After
	public void destroy(){
		
	}
	
	@Test
	public void addGroupTest() throws RepositoryException {
		IDataBaseService db = new TestDataBaseService();
		Group group = ORMObjectFactory.createGroupObj(db,"IF-58A");
		int groupsCount = db.getGroups().getAll().size();
		db.getGroups().insert(group);
		assertTrue(group.getID()>0);
		assertEquals(db.getGroups().getAll().size(), groupsCount + 1);
		
	}
	
	@Test
	public void addGroup2Test() throws ORMObjectException, RepositoryException {
		IDataBaseService db = new TestDataBaseService();
		
		Group group = ORMObjectFactory.createGroupObj(db, "IF-59-A");
		Group newGroup = ORMObjectFactory.createGroupObj(db, "IF-58-A");
		
		assertNotNull(newGroup);		
		Student newStudent = ORMObjectFactory.createStudentObj(db,"Marianna","Roshchenko");
		
		int OldStudentsCount = db.getGroups().getAllByGroup(newGroup).size();
		newGroup.addStudent(newStudent);
		int NewStudentsCount = db.getGroups().getAllByGroup(newGroup).size();
		assertEquals(OldStudentsCount, NewStudentsCount-1);
		
		newGroup.getAverageMark();
	}
	
	@Test (expected = ORMObjectException.class)
	public void addStudsTest() throws ORMObjectException, RepositoryException{
		IDataBaseService db = new TestDataBaseService();
		
		Student s = ORMObjectFactory.createStudentObj(db,"Ivan","Ivanov");
		Group g1 = ORMObjectFactory.createGroupObj(db, "IF-58-A");
		Group g2 = ORMObjectFactory.createGroupObj(db, "IF-59-B");
		
		g1.addStudent(s);
		g1.addStudent(s);
		g2.addStudent(s);	
	}

}
