package app;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import log.ILogger;
import log.ILoggerProvider;
import log.MessageType;
import log.SimpleLoggerFactory;
import log.appenders.FileAppender;
import log.appenders.file.FileManager;
import log.impl.SimpleLoggerProvider;

import data.contracts.IDataBaseService;
import data.contracts.repositories.RepositoryException;
import data.db.DataBaseService;
import data.db.IDBConnectionFactory;
import data.db.impl.DBConnectionFactory;
import data.db.impl.PGDataSource;
import data.orm.Group;
import data.orm.ORMObjectException;
import data.orm.Student;
import data.orm.Subject;

public class Main {

	public static IDBConnectionFactory getDBConnectionFactory() {
		PGDataSource source = new PGDataSource("localhost", "StudDB",
				"postgres", "postgres");
		IDBConnectionFactory factory = new DBConnectionFactory(
				source.getDataSource());

		return factory;
	}

	public static ILoggerProvider prepareLogProvider() {
		List<MessageType> msgTypes = Arrays.asList(MessageType.INFO,
				MessageType.ERROR, MessageType.WARN);
		FileAppender fileAppender = new FileAppender();
		fileAppender.setFile(new FileManager("logs/lab5.log"));

		SimpleLoggerProvider logProvider = new SimpleLoggerProvider();

		logProvider.addAppenderForMsgTypes(fileAppender, msgTypes);
		return logProvider;
	}

	public static void main(String[] args) throws ORMObjectException {
		IDataBaseService db = null;
		Scanner inp = new Scanner(System.in);
		try {
			SimpleLoggerFactory.Initialize(prepareLogProvider());
			IDBConnectionFactory connectionFactory = getDBConnectionFactory();
			db = new DataBaseService(connectionFactory);
			while (true) {

				System.out.println("Enter cmd: ");
				String cmd = inp.nextLine();
				if (cmd.equals("add_group") == true) {
					try {
						System.out.print("Group Name:   ");
						String gr = inp.nextLine();
						Group group = new Group(gr);
						db.getGroups().insert(group);
						System.out.print("ok\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
				}
				if (cmd.equals("get_groups") == true) {
					try {
						System.out.print("----" + "  " + "Groups" + "  "
								+ "----" + "\n");
						System.out.print("id" + "  " + " |" + "  " + "Name "
								+ "\n");
						Collection<Group> groups = db.getGroups().getAll();
						for (Group g : groups) {
							System.out.print(g.getID() + "  " + " |" + "  "
									+ g.getName() + "\n");
						}
						System.out.print("ok\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
				}
				if (cmd.equals("add_studs") == true) {
					try {
						System.out.print("Student name:   ");
						String stud_name = inp.nextLine();
						System.out.print("Student surname:   ");
						String stud_surname = inp.nextLine();
						Student student = new Student(stud_name, stud_surname);
						db.getStudents().insert(student);
						System.out.print("ok" + "\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
				}
				if (cmd.equals("get_studs") == true) {
					try {
						System.out.print("--------" + "  " + "Students" + "  "
								+ "--------" + "\n");
						System.out.print("id" + "  " + " |" + "  " + "Name "
								+ "  " + " |" + " Surname" + "\n");
						Collection<Student> students = db.getStudents()
								.getAll();
						for (Student st : students) {
							System.out.print(st.getID() + "  " + " |" + "  "
									+ st.getName() + " |" + st.getSurname()
									+ "\n");
						}
						System.out.print("ok" + "\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
				}
				if (cmd.equals("add_subj") == true) {
					try {
						System.out.print("Subject name:   ");
						String subj = inp.nextLine();
						Subject subject = new Subject(subj);
						db.getSubjects().insert(subject);
						System.out.print("ok" + "\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
				}
				if (cmd.equals("get_subj") == true) {
					try {
						System.out.print("----" + "  " + "Subjects" + "  "
								+ "----" + "\n");
						System.out.print("id" + "  " + " |" + "  " + "Name "
								+ "\n");
						Collection<Subject> subjects = db.getSubjects()
								.getAll();
						for (Subject subj : subjects) {
							System.out.print(subj.getID() + "  " + " |" + "  "
									+ subj.getSubjName() + "\n");
						}
						System.out.print("ok" + "\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
				}

				if (cmd.equals("add_mark") == true) {
					try {
						System.out.print("Enter mark:   ");
						int m = inp.nextInt();
						// Mark mark = new Mark (m,)
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			SimpleLoggerFactory.Dispose();
		}

	}

}
