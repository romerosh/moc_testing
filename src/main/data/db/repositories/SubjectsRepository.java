package data.db.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import data.contracts.IDataBaseService;
import data.contracts.repositories.ISubjectsRepository;
import data.contracts.repositories.RepositoryException;
import data.db.IDBConnectionFactory;
import data.db.Repository;
import data.orm.Group;
import data.orm.Subject;

public class SubjectsRepository extends Repository implements ISubjectsRepository
{

	public SubjectsRepository(IDataBaseService dataBaseService,
			IDBConnectionFactory connectionFactory) {
		super(dataBaseService, connectionFactory);
	}

	@Override
	public Collection<Subject> getAll() throws RepositoryException {
		Connection c = super.getConnection();
		Collection<Subject> subjects = null;

		try {
			String query = "select * from subjects;";
			PreparedStatement ps = c.prepareStatement(query);
			ResultSet key = ps.executeQuery();
			subjects = new ArrayList<Subject>();
			while (key.next()) {

				Subject subject = new Subject();
				int id = key.getInt("id");
				String subjName = key.getString("name");
				subject.setID(id);
				subject.setSubjName(subjName);
				subject.setDb(dataBaseService);
				subjects.add(subject);
			}
		} catch (SQLException e) {
			super.throwable(e, RepositoryException.err_enum.c_sql_err);
		} finally {
			super.closeConnection(c);
		}
		return subjects;
	}

	@Override
	public Subject getByID(int ID) throws RepositoryException {
		Connection c = super.getConnection();
		Subject subject = null;

		try {
			String query = "select * from subjects where id = ? LIMIT 1;";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, ID);
			ResultSet key = ps.executeQuery();
			if (key.next()) {
				subject = new Subject();
				int id = key.getInt("id");
				String subjName = key.getString("name");
				subject.setID(id);
				subject.setSubjName(subjName);
				subject.setDb(dataBaseService);

			}
		} catch (SQLException e) {
			super.throwable(e, RepositoryException.err_enum.c_sql_err);
		} finally {
			super.closeConnection(c);
		}
		return subject;
	}

	@Override
	public void insert(Subject obj) throws RepositoryException {
		Connection c = super.getConnection();
		try {
			String query = "insert into subjects (name) values (?) returning id;";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, obj.getSubjName());
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
			String query = "delete from subjects where id = ?;";
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
	public void update(Subject obj) throws RepositoryException {
		Connection c = super.getConnection();
		try {
			String query = "update subjects set name = ? where id = ?;";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, obj.getSubjName());
			ps.setInt(2, obj.getID());
			ps.execute();
			super.commit(c);
		} catch (SQLException e) {
			super.throwable(e, RepositoryException.err_enum.c_sql_err);
		} finally {
			super.closeConnection(c);
		}
		
	}

	@Override
	public Subject getSubjectByName(String subject_name) throws RepositoryException {
		Connection c = super.getConnection();
		Subject subject = null;

		try {
			String query = "select * from subjects where name = ?;";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, subject_name);
			ResultSet key = ps.executeQuery();
			if (key.next()) {
				subject = new Subject();
				int id = key.getInt("id");
				subject.setID(id);
				subject.setSubjName(subject_name);
				subject.setDb(dataBaseService);
			}
		} catch (SQLException e) {
			super.throwable(e, RepositoryException.err_enum.c_sql_err);
		} finally {
			super.closeConnection(c);
		}
		return subject;

	}

	@Override
	public boolean attach(Subject obj) throws RepositoryException {
		if (obj.getDb() != null && obj.getID() > 0)
			return true;
		Subject g = this.getSubjectByName(obj.getSubjName());
		if (g == null)
			return false;
		obj.setDb(null);
		obj.setID(g.getID());
		obj.setDb(dataBaseService);
		return true;
	}

	@Override
	public void updateSubject(Subject subject, String new_name)
			throws RepositoryException {	
		Connection c = super.getConnection();
		try {
			String query = "update subjects set name = ? where id = ?;";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, new_name);
			ps.setInt(2, subject.getID());
			ps.execute();
			super.commit(c);
		} catch (SQLException e) {
			super.throwable(e, RepositoryException.err_enum.c_sql_err);
		} finally {
			super.closeConnection(c);
		}
	}

}
