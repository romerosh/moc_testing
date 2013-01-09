package data.contracts.repositories;

import java.util.Collection;

public interface IRepository<T> {

	
	Collection<T> getAll() throws RepositoryException;
	T getByID(int ID) throws RepositoryException;
	void insert(T obj) throws RepositoryException;
	void remove(int ID) throws RepositoryException;
	void update(T obj) throws RepositoryException;
	boolean attach(T obj) throws RepositoryException;
}
