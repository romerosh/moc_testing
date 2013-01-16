package data.db.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import data.contracts.IDataBaseService;
import data.contracts.repositories.IMarksRepository;
import data.contracts.repositories.RepositoryException;
import data.db.IDBConnectionFactory;
import data.db.Repository;
import data.orm.Mark;
import data.orm.Student;

public class MarksRepository extends Repository implements IMarksRepository {

	public MarksRepository(IDataBaseService dataBaseService,
			IDBConnectionFactory connectionFactory) {
		super(dataBaseService, connectionFactory);
	}

	@Override
	public Collection<Mark> getAll() throws RepositoryException {
		Connection c = super.getConnection();
		Collection<Mark> marks = null;

		try {
			String query = "select * from marks;";
			PreparedStatement ps = c.prepareStatement(query);
			ResultSet key = ps.executeQuery();
			marks = new ArrayList<Mark>();
			while (key.next()) {
				Mark mark = new Mark();
				int id = key.getInt("id");
				int m = key.getInt("mark");
				int stud_id = key.getInt("student_id");
				int subj_id = key.getInt("subject_id");
				mark.setID(id);
				mark.setMark(m);
				mark.setStudent_id(stud_id);
				mark.setSubject_id(subj_id);
				mark.setDb(dataBaseService);
				marks.add(mark);
			}
		} catch (SQLException e) {
			super.throwable(e, RepositoryException.err_enum.c_sql_err);
		} finally {
			super.closeConnection(c);
		}
		return marks;
	}

	@Override
	public Mark getByID(int ID) throws RepositoryException {
		Connection c = super.getConnection();
		Mark mark = null;

		try {
			String query = "select * from marks where id = ? LIMIT 1;";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, ID);
			ResultSet key = ps.executeQuery();
			if (key.next()) {
				mark = new Mark();
				int id = key.getInt("id");
				int markGrade = key.getInt("mark");
				int student_id = key.getInt("student_id");
				int subject_id = key.getInt("subject_id");

				mark.setID(id);
				mark.setMark(markGrade);
				mark.setStudent_id(student_id);
				mark.setSubject_id(subject_id);
				mark.setDb(dataBaseService);
			}
		} catch (SQLException e) {
			super.throwable(e, RepositoryException.err_enum.c_sql_err);
		} finally {
			super.closeConnection(c);
		}
		return mark;
	}

	@Override
	public void insert(Mark obj) throws RepositoryException {
		Connection c = super.getConnection();
		try {
			String query = "insert into marks (mark, student_id, subject_id) values (?,?,?) returning id;";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, obj.getMark());
			ps.setInt(2, obj.getStudent_id());
			ps.setInt(3, obj.getSubject_id());
			ResultSet key = ps.executeQuery();
			if (key.next()) {
				int id = key.getInt("id");
				obj.setID(id);
				obj.setDb(dataBaseService);
			}
			super.commit(c);
		} catch (SQLException e) {
			super.throwable(e, RepositoryException.err_enum.c_sql_err);
		} finally {
			super.closeConnection(c);
		}
	}

	@Override
	public void remove(int ID) throws RepositoryException {
		Connection c = super.getConnection();
		try {
			String query = "delete from marks where id = ? ;";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, ID);
			ps.execute();
			super.commit(c);
		} catch (SQLException e) {
			super.throwable(e, RepositoryException.err_enum.c_sql_err);
		} finally {
			super.closeConnection(c);
		}

	}

	@Override
	public void update(Mark obj) throws RepositoryException {
		Connection c = super.getConnection();
		try {
			String query = "update mars set mark = ?, student_id = ?, subject_id = ? where id = ?;";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, obj.getMark());
			ps.setInt(2, obj.getStudent_id());
			ps.setInt(3, obj.getSubject_id());
			ps.setInt(4, obj.getID());
			ps.execute();
			super.commit(c);
		} catch (SQLException e) {
			super.throwable(e, RepositoryException.err_enum.c_sql_err);
		} finally {
			super.closeConnection(c);
		}
	}

	@Override
	public boolean attach(Mark obj) throws RepositoryException {
		if (obj.getDb() != null && obj.getID() > 0)
			return true;
		Mark mark = this.getByID(obj.getID());
		if (mark == null)
			return false;
		obj.setDb(dataBaseService);
		return true;
	}



}
