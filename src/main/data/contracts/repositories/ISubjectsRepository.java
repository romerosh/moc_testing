package data.contracts.repositories;

import data.orm.Student;
import data.orm.Subject;

public interface ISubjectsRepository extends IRepository<Subject>{
public Subject getSubjectByName (String subject_name) throws RepositoryException;
}
