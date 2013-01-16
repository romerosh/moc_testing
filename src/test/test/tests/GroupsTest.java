package test.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import log.ILogger;
import log.SimpleLoggerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.data.TestDataBaseService;
import test.log.TestLogProvider;

import data.contracts.IDataBaseService;
import data.contracts.repositories.IGroupsRepository;
import data.contracts.repositories.IStudentsRepository;
import data.contracts.repositories.RepositoryException;
import data.orm.Group;
import data.orm.ORMObjectException;
import data.orm.ORMObjectFactory;
import data.orm.Student;
public class GroupsTest {
	private static ILogger log = null;

	@Before
	public void init() {
		SimpleLoggerFactory.Initialize(TestLogProvider.prepareProvider());
		log = SimpleLoggerFactory.getLogger();
		
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
		log.info("addGroup2Test():");
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
	
	@Test
	public void updateGroupTest() throws ORMObjectException, RepositoryException {
		
		// Настроим моck объекты
		IGroupsRepository groupsRepository = mock(IGroupsRepository.class);
		IDataBaseService db = mock(IDataBaseService.class);
		
		doNothing().when(groupsRepository).update(any(Group.class));
		doReturn(true).when(groupsRepository).attach(any(Group.class));
		
		doReturn(groupsRepository).when(db).Groups();
		
		// Тестирование
		Group group = new Group("IF-57-A");
		group.setDb(db);
		group.setName("IF-57-B");
		
		// Проверим, была ли попытка синхронизировать группу с репозиторием
		// т.е. получить ее id по названию
		verify(groupsRepository,times(1)).attach(group);

		// Проверим, вызывалась ли функция Update для данной группы
		verify(groupsRepository).update(group);
		
	}

	@Test
	public void addStudentToGroupTest() throws ORMObjectException, RepositoryException {
		
		// Настроим моck объекты
		IGroupsRepository groupsRepository = mock(IGroupsRepository.class);
		IStudentsRepository studentsRepository = mock(IStudentsRepository.class);
		IDataBaseService db = mock(IDataBaseService.class);
		
		doReturn(true).when(groupsRepository).attach(any(Group.class));
		doNothing().when(groupsRepository).addStudent(any(Group.class),any( Student.class));
		
		doReturn(true).when(studentsRepository).attach(any(Student.class));
		
		doReturn(groupsRepository).when(db).Groups();
		doReturn(studentsRepository).when(db).Students();
		
		// Тестирование
		Group group = new Group("IF-57-A");
		group.setDb(db);
		Student student = new Student("Ivan","Ivanov");
		group.addStudent(student);
		
		// Проверим, была ли попытка синхронизировать группу с репозиторием
		// т.е. получить ее id по названию
		verify(groupsRepository).attach(group);

		// Проверим, была ли попытка синхронизировать студента с репозиторием
		// т.е. получить ее id по имени и фамилии
		verify(studentsRepository).attach(student);

		
		// Проверим, вызывалась ли функция добавления студента врепозиторий
		verify(groupsRepository).addStudent(group,student);
		
	}
}
