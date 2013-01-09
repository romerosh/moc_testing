package test.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import log.ILoggerProvider;
import log.MessageType;
import log.SimpleLoggerFactory;
import log.appenders.ConsoleAppender;
import log.appenders.FileAppender;
import log.impl.SimpleLoggerProvider;

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

public class GroupsTest {

	@Before
	public void init() {
		SimpleLoggerFactory.Initialize(TestLogProvider.prepareProvider());
	}

	@After
	public void destroy() {
		SimpleLoggerFactory.Dispose();
	}

	@Test
	public void addGroupTest() throws RepositoryException {
		IDataBaseService db = new TestDataBaseService();
		Group group = ORMObjectFactory.createGroupObj(db, "IF-58A");
		int groupsCount = db.Groups().getAll().size();
		db.Groups().insert(group);
		assertTrue(group.getID() > 0);
		assertEquals(db.Groups().getAll().size(), groupsCount + 1);

	}

	@Test
	public void addGroup2Test() throws ORMObjectException, RepositoryException {
		IDataBaseService db = new TestDataBaseService();

		Group group = ORMObjectFactory.createGroupObj(db, "IF-59-A");
		Group newGroup = ORMObjectFactory.createGroupObj(db, "IF-58-A");

		assertNotNull(newGroup);
		Student newStudent = ORMObjectFactory.createStudentObj(db, "Marianna",
				"Roshchenko");

		int OldStudentsCount = db.Groups().getAllByGroup(newGroup).size();
		newGroup.addStudent(newStudent);
		int NewStudentsCount = db.Groups().getAllByGroup(newGroup).size();
		assertEquals(OldStudentsCount, NewStudentsCount - 1);

		newGroup.getAverageMark();
	}

	@Test(expected = ORMObjectException.class)
	public void addStudsTest() throws ORMObjectException, RepositoryException {
		IDataBaseService db = new TestDataBaseService();

		Student s = ORMObjectFactory.createStudentObj(db, "Ivan", "Ivanov");
		Group g1 = ORMObjectFactory.createGroupObj(db, "IF-58-A");
		Group g2 = ORMObjectFactory.createGroupObj(db, "IF-59-B");

		g1.addStudent(s);
		g1.addStudent(s);
		g2.addStudent(s);
	}

}
