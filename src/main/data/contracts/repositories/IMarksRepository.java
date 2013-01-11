package data.contracts.repositories;

import data.orm.Mark;

public interface IMarksRepository extends IRepository<Mark>  {
	public Mark GetMark(int subject, int Student, int mark) throws RepositoryException;

}
