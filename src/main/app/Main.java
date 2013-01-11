package app;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.html.HTML;

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
import helpers.*;

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
				MessageType.DEBUG, MessageType.WARN, MessageType.ERROR);
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
				// *****Groups commands*****//
				System.out.print("Command> ");
				String cmd = inp.nextLine();
				if (cmd.equals("add_group") == true) {
					try {
						System.out.print("Group Name:   ");
						String gr = inp.nextLine();
						Group group = new Group(gr);
						db.Groups().insert(group);
						System.out.print("INFO: The group was added!\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
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
						System.out.print("INFO: ok\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
					}
					continue;
				}

				if (cmd.equals("remove_group") == true) {
					try {
						System.out.print("Enter group:  ");
						String gr = inp.nextLine();
						Group group = db.Groups().getByName(gr);
						if (group == null) {
							System.out.print("INFO: Faied! The group was not found!\n");
							continue;
						}
						db.Groups().remove(group.getID());					
						System.out.print("INFO: The group was removed!\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
					}
					continue;
				}

				if (cmd.equals("update_group") == true) {
					try {
						System.out.print("Enter changed group: ");
						String old_gr = inp.nextLine();
						Group old_group = db.Groups().getByName(old_gr);
						if (old_group == null) {
							System.out.print("INFO: Faied! The group was not found!\n");
							continue;
						}
						System.out.print("Enter new group: ");
						String new_gr = inp.nextLine();
						db.Groups().updateGroup(old_group, new_gr);
						System.out.print("INFO: The group was update!\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
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

				if (cmd.equals("get_all_in_group") == true) {
					try {
						int i = 1;
						System.out.print("Enter group: ");
						String gr = inp.nextLine();
						Group group = db.Groups().getByName(gr);
						if (group == null) {
							System.out.print("INFO: Faied! The group was not found!\n");
							continue;
						}
						Collection<Student> students = db.Groups().getAllByGroup(group);
						for (Student st : students) {
							System.out.print(i + "  " + st.getName() + "  "
									+ st.getSurname() + "\n");
							i++;
						}
						System.out.println("INFO: Command was done!\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed! \n");
					}
					continue;
				}

				// ***** Students commands *****//
				if (cmd.equals("add_stud") == true) {
					try {
						System.out.print("Student name: ");
						String stud_name = inp.nextLine();
						System.out.print("Student surname: ");
						String stud_surname = inp.nextLine();
						Student student = new Student(stud_name, stud_surname);
						db.Students().insert(student);
						System.out.print("INFO: The student was added!" + "\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed.\n");
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
						System.out.print("INFO: The command was done!" + "\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
					}
					continue;
				}

				if (cmd.equals("remove_stud") == true) {
					try {
						System.out.print("Student name: ");
						String stud_name = inp.nextLine();
						System.out.print("Student surname: ");
						String stud_surname = inp.nextLine();
						Student student = db.Students().getByName(stud_name,
								stud_surname);
						if (student == null) {
							System.out.print("INFO: Failed! Student was not found. \n");
							continue;
						}
						db.Students().remove(student.getID());
						System.out.print("INFO: The student was removed!\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
					}
					continue;
				}

				if (cmd.equals("update_stud") == true) {
					try {
						System.out.print("Enter name: ");
						String old_name = inp.nextLine();
						System.out.print("Enter surname: ");
						String old_surname = inp.nextLine();
						Student old_stud = db.Students().getByName(old_name,
								old_surname);
						if (old_stud == null) {
							System.out.print("INFO: Failed! Student was not found. \n");
							continue;
						}
						System.out.print("Enter new name: ");
						String new_name = inp.nextLine();
						System.out.print("Enter new surname: ");
						String new_surname = inp.nextLine();
						db.Students().updateStudent(old_stud, new_name,
								new_surname);
						System.out.print("INFO: The student was update!\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
					}
					continue;
				}
				// ***** Subjects commands *****//
				if (cmd.equals("add_subj") == true) {
					try {
						System.out.print("Subject name: ");
						String subj = inp.nextLine();
						Subject subject = new Subject(subj);
						db.Subjects().insert(subject);
						System.out.print("INFO: The subject was added!" + "\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
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
						System.out.print("INFO: The command was done!" + "\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
					}
					continue;
				}

				if (cmd.equals("remove_subj") == true) {
					try {
						System.out.print("Enter subject: ");
						String sub = inp.nextLine();
						Subject subject = db.Subjects().getSubjectByName(sub);
						if (subject == null) {
							System.out.print("INFO: Failed! Subject was not found. \n");
							continue;
						}
						db.Subjects().remove(subject.getID());
						System.out.print("INFO: The subject was removed!\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
					}
					continue;
				}

				if (cmd.equals("update_subj") == true) {
					try {
						System.out.print("Enter old subject: ");
						String old_sub = inp.nextLine();
						Subject old_subs = db.Subjects().getSubjectByName(
								old_sub);
						if (old_subs == null) {
							System.out.print("INFO: Failed! Subject was not found. \n");
							continue;
						}
						System.out.print("Enter new subject: ");
						String new_sub = inp.nextLine();
						db.Subjects().updateSubject(old_subs, new_sub);
						System.out.print("INFO: The subject was update!\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
					}
					continue;
				}
				// ***** Mark commands *****//
				if (cmd.equals("add_mark") == true) {
					try {
						System.out.print("Student name: ");
						String stud_name = inp.nextLine();
						System.out.print("Student surname: ");
						String stud_surname = inp.nextLine();
						Student student = db.Students().getByName(stud_name,
								stud_surname);
						System.out.print("Subject name: ");
						String subj = inp.nextLine();
						Subject subject = db.Subjects().getSubjectByName(subj);
						System.out.print("Mark: ");
						int marks = inp.nextInt();
						Mark mark = new Mark(subject, student, marks);
						db.Marks().insert(mark);
						System.out.print("INFO: The mark was added!\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
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
							System.out.println(m.getID() + "   " + m.getMark()
									+ "  " + m.getStudent_id() + " ");
						}
						System.out.print("INFO: The command was done!" + "\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
					}
					continue;
				}

				if (cmd.equals("remove_mark") == true) {
					try {
						System.out.print("Student name: ");
						String stud_name = inp.nextLine();
						System.out.print("Student surname: ");
						String stud_surname = inp.nextLine();
						Student student = db.Students().getByName(stud_name,
								stud_surname);
						System.out.print("Subject name: ");
						String subj = inp.nextLine();
						System.out.print("Mark: ");
						int m = inp.nextInt();
						Subject subject = db.Subjects().getSubjectByName(subj);
						if (student == null && subject==null) {
							System.out.print("INFO: Failed! Student or subject was not found! \n");
							continue;
						}
						Mark mark = db.Marks().GetMark(subject.getID(),student.getID(), m);
						db.Marks().remove(mark.getID());
						System.out.print("INFO: The mark was removed! \n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
					}
					continue;
				}

				// ***** Mix commands *****//
				if (cmd.equals("add_stud_to_group") == true) {
					try {
						Student student = new Student();
						System.out.print("Enter group: ");
						String gr = inp.nextLine();
						Group group = db.Groups().getByName(gr);
						if (group == null) {
							System.out.print("Faied! The group was not found");
							continue;
						}
						System.out.print("Student name: ");
						String stud_name = inp.nextLine();
						System.out.print("Student surname: ");
						String stud_surname = inp.nextLine();
						student.setName(stud_name);
						student.setSurname(stud_surname);
						group.addStudent(student);
						System.out.print("INFO: The student "
								+ student.getName()+ " "+ student.getSurname() + " was added to group: "
								+ group.getName() + "\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
					}
					continue;
				}

				if (cmd.equals("remove_stud_from_group") == true) {
					try {
						// Student student = new Student();
						System.out.print("Enter group:   ");
						String gr = inp.nextLine();
						Group group = db.Groups().getByName(gr);
						if (group == null) {
							System.out.print("Faied! The group was not found");
							continue;
						}
						System.out.print("Student name: ");
						String stud_name = inp.nextLine();
						System.out.print("Student surname: ");
						String stud_surname = inp.nextLine();
						Student student = db.Students().getByName(stud_name,
								stud_surname);
						group.removeStudent(student);
						//db.Groups().removeStudent(group, student);
						System.out
								.print("INFO: Student removed from the group!\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
					}
					continue;
				}

				if (cmd.equals("avg_mark_group") == true) {
					try {
						System.out.print("Enter group:   ");
						String gr = inp.nextLine();
						Group group = db.Groups().getByName(gr);
						if (group == null) {
							System.out.print("Faied! The group was not found");
							continue;
						}
						double avg = group.getAverageMark();
					//	double avg = db.Groups().getAverageMark(group.getID());
						// System.out.println(NumberHelper.round(avg, 2));
						System.out.print("Average mark by group = "
								+ NumberHelper.round(avg, 2) + "\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
					}
					continue;
				}

				if (cmd.equals("avg_mark_stud") == true) {
					try {
						// Student student = new Student();
						System.out.print("Enter name:   ");
						String name = inp.nextLine();
						System.out.print("Enter surname:   ");
						String surname = inp.nextLine();
						Student student = db.Students()
								.getByName(name, surname);
						if (student == null) {
							System.out.print("Faied! The group was not found");
							continue;
						}
						double avg = db.Students().getAverageMark(student);
						// System.out.println(NumberHelper.round(avg, 2));
						System.out.print("Average mark by group = "
								+ NumberHelper.round(avg, 2) + "\n");
					} catch (Exception e) {
						System.out.print("failed.\n");
					}
					continue;
				}

				if (cmd.equals("get_stud_marks") == true) {
					try {
						System.out.print("Student name: ");
						String name = inp.nextLine();
						System.out.print("Student surname: ");
						String surname = inp.nextLine();
						Student student = db.Students()
								.getByName(name, surname);
						if (student == null) {
							System.out.print("Faied! The group was not found");
							continue;
						}
						Collection<Mark> marks = db.Students()
								.GetMarks(student);
						for (Mark m : marks) {
							System.out.print(m.getMark() + " "
									+ db.Subjects().getByID(m.getSubject_id()));
						}
						System.out.print("INFO: The command was done!\n");
					} catch (Exception e) {
						System.out.print("INFO: Failed!\n");
					}
					continue;
				}

				if (cmd.equals("exit") == true) {
					System.out.print("Bye bye :)\n");
					return;
				}
				
				if (cmd.equals("help") == true) {
					System.out.print("add_group - Добавляет группу в список.\n");
					System.out.print("get_groups - Выводит список всех созданных групп.\n");
					System.out.print("remove_group - Удаляет группу.\n");
					System.out.print("update_group - Меняет название группы.\n");
					System.out.print("get_all_in_group - Выводит список всех студентов в данной группе.\n");
					System.out.print("add_stud - Добавляет студента в список.\n");
					System.out.print("get_studs - Выводит список всех студентов.\n");
					System.out.print("remove_stud - Удаляет студента из списка.\n");
					System.out.print("update_stud - Меняет имя студента.\n");
					System.out.print("add_subj - Добавляет новый предмет.\n");
					System.out.print("get_subj - Выводит список всех созданных предметов.\n");
					System.out.print("remove_subj - Удаляет предмет из спика.\n");
					System.out.print("update_subj - Меняет название предмета.\n");
					System.out.print("add_mark - Добавляет оценку выбранному студенту по конкретному предмету.\n");
					System.out.print("get_marks - Выводит список всех оценок.\n");
					System.out.print("remove_mark - Удаляет оценку из списка.\n");
					System.out.print("add_stud_to_group - Добавляет студента в группу.\n");
					System.out.print("remove_stud_from_group - Удаляет студента из группы.\n");
					System.out.print("avg_mark_group - Средняя оценка по группе.\n");
					System.out.print("avg_mark_stud - Средняя оценка по студенту.\n");
					System.out.print("get_stud_marks - Выводит все оценки студента.\n");
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
