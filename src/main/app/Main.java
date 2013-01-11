package app;

import java.io.Console;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import log.ILoggerProvider;
import log.MessageType;
import log.SimpleLoggerFactory;
import log.appenders.ConsoleAppender;
import log.appenders.FileAppender;
import log.appenders.file.FileManager;
import log.impl.SimpleLoggerProvider;

import data.contracts.IDataBaseService;
import data.db.DataBaseService;
import data.db.IDBConnectionFactory;
import data.db.impl.DBConnectionFactory;
import data.db.impl.PGDataSource;
import data.orm.Group;
import data.orm.Mark;
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
		fileAppender.setFile(new FileManager("lab5.log"));

		SimpleLoggerProvider logProvider = new SimpleLoggerProvider();

		 logProvider.addAppenderForMsgTypes(new ConsoleAppender(), msgTypes);
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

				System.out.print("Command> ");
				String cmd = inp.nextLine();
				if (cmd.equals("add_group") == true) {
					try {
						System.out.print("Group Name:   ");
						String gr = inp.nextLine();
						Group group = new Group(gr);
						db.Groups().insert(group);
						System.out.print("ok\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
					continue;
				}

				if (cmd.equals("get_groups") == true) {
					try {
						System.out.print("----" + "  " + "Groups" + "  "
								+ "----" + "\n");
						System.out.print("id" + "  " + " |" + "  " + "Name "
								+ "\n");
						Collection<Group> groups = db.Groups().getAll();
						for (Group g : groups) {
							System.out.print(g.getID() + "  " + " |" + "  "
									+ g.getName() + "\n");
						}
						System.out.print("ok\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
					continue;
				}
				if (cmd.equals("add_stud") == true) {
					try {
						System.out.print("Student name:   ");
						String stud_name = inp.nextLine();
						System.out.print("Student surname:   ");
						String stud_surname = inp.nextLine();
						Student student = new Student(stud_name, stud_surname);
						db.Students().insert(student);
						System.out.print("ok" + "\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
					continue;
				}
				if (cmd.equals("get_studs") == true) {
					try {
						System.out.print("--------" + "  " + "Students" + "  "
								+ "--------" + "\n");
						System.out.print("id" + "  " + " |" + "  " + "Name "
								+ "  " + " |" + " Surname" + "\n");
						Collection<Student> students = db.Students().getAll();
						for (Student st : students) {
							System.out.print(st.getID() + "  " + " |" + "  "
									+ st.getName() + " |" + st.getSurname()
									+ "\n");
						}
						System.out.print("ok" + "\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
					continue;
				}
				if (cmd.equals("add_subj") == true) {
					try {
						System.out.print("Subject name:   ");
						String subj = inp.nextLine();
						Subject subject = new Subject(subj);
						db.Subjects().insert(subject);
						System.out.print("ok" + "\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
					continue;
				}
				if (cmd.equals("get_subj") == true) {
					try {
						System.out.print("----" + "  " + "Subjects" + "  "
								+ "----" + "\n");
						System.out.print("id" + "  " + " |" + "  " + "Name "
								+ "\n");
						Collection<Subject> subjects = db.Subjects().getAll();
						for (Subject subj : subjects) {
							System.out.print(subj.getID() + "  " + " |" + "  "
									+ subj.getSubjName() + "\n");
						}
						System.out.print("ok" + "\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
					continue;
				}

				if (cmd.equals("remove_group") == true) {
					try {
						System.out.print("Enter group:   ");
						String gr = inp.nextLine();
						// Group en_group = new Group (gr);
						Group group = db.Groups().getByName(gr);
						db.Groups().remove(group.getID());
						System.out.print("ok\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
					continue;
				}

				if (cmd.equals("update_group") == true) {
					try {
						System.out.print("Enter old group:   ");
						String old_gr = inp.nextLine();
						// Group en_group = new Group (gr);
						Group old_group = db.Groups().getByName(old_gr);
						System.out.print("Enter new group:   ");
						String new_gr = inp.nextLine();
						Group new_group = db.Groups().getByName(new_gr);
						old_group = new_group;
						db.Groups().update(old_group);
						System.out.print("ok\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
					continue;
				}

				if (cmd.equals("get_group_info") == true) {
					try {
						System.out.print("Enter group:   ");
						String gr = inp.nextLine();
						Group group = db.Groups().getByName(gr);
						System.out.print("id" + "  " + " |" + "  " + "Name "
								+ "\n");
						System.out.print(group.getID() + "  " + " |" + "  "
								+ group.getName() + "\n");
						System.out.print("ok\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
					continue;
				}

				if (cmd.equals("add_stud_to_group") == true) {
					try {
						Student student = new Student();
						System.out.print("Enter group:   ");
						String gr = inp.nextLine();
						Group group = db.Groups().getByName(gr);
						if (group == null) {
							System.out.print("Faied! The group was not found");
							continue;
						}
						System.out.print("Student name:   ");
						String stud_name = inp.nextLine();
						System.out.print("Student surname:   ");
						String stud_surname = inp.nextLine();
						student.setName(stud_name);
						student.setSurname(stud_surname);
						group.addStudent(student);
						/*
						 * System.out.print(db.getGroups().getByID(group.getID())
						 * + "    " +
						 * db.getStudents().getByID(student.getID()));
						 */
						System.out.print(student.getName());
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
					continue;
				}

				if (cmd.equals("remove_stud") == true) {
					try {
						System.out.print("Student name:   ");
						String stud_name = inp.nextLine();
						System.out.print("Student surname:   ");
						String stud_surname = inp.nextLine();
						Student student = db.Students().getByName(stud_name,
								stud_surname);
						if (student == null) {
							System.out
									.print("Failed. Student wat not found. \n");
							continue;
						}
						db.Students().remove(student.getID());
						System.out.print("ok\n");

					} catch (Exception e) {

						System.out.print("failed.\n");

					}
					continue;
				}
				if (cmd.equals("remove_subj") == true) {
					try {
						System.out.print("Enter subject:   ");
						String sub = inp.nextLine();
						Subject subject = db.Subjects().getSubjectByName(sub);
						db.Subjects().remove(subject.getID());
						System.out.print("ok\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
					continue;
				}

				if (cmd.equals("add_mark") == true) {
					try {
						System.out.print("Student name:   ");
						String stud_name = inp.nextLine();
						System.out.print("Student surname:   ");
						String stud_surname = inp.nextLine();
						Student student = db.Students().getByName(stud_name,
								stud_surname);
						System.out.print("Subject name:   ");
						String subj = inp.nextLine();
						System.out.print("Mark:   ");
						String mark = inp.nextLine();

						student.AddMark(new Subject(subj),
								Integer.parseInt(mark));
						System.out.print("ok\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
					continue;
				}

				if (cmd.equals("remove_mark") == true) {
					try {
						System.out.print("Student name:   ");
						String stud_name = inp.nextLine();
						System.out.print("Student surname:   ");
						String stud_surname = inp.nextLine();
						Student student = db.Students().getByName(stud_name,
								stud_surname);
						System.out.print("Subject name:   ");
						String subj = inp.nextLine();
						Subject subject = db.Subjects().getSubjectByName(subj);
						// System.out.print(mark.getID());
						// db.getStudents().RemoveMark(mark.getID());
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
					continue;
				}

				if (cmd.equals("get_marks") == true) {
					try {
						System.out.print("----" + "  " + "Marks" + "  "
								+ "----" + "\n");
						System.out.print("id" + "  " + " |" + "  " + "Name "
								+ "\n");
						Collection<Mark> marks = db.Marks().getAll();
						for (Mark m : marks) {
							System.out.print(m.getStudent_id());
						}
						System.out.print("ok" + "\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
					continue;
				}

				if (cmd.equals("exit") == true) {
					System.out.print("Bye bye :)\n");
					return;
				}
				System.out
						.print("Failed! Command was not found. Please, try again! \n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			SimpleLoggerFactory.Dispose();
		}
	}

}
