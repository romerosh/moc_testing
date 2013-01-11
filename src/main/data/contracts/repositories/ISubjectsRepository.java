package data.contracts.repositories;

import data.orm.Subject;

public interface ISubjectsRepository extends IRepository<Subject>{
public Subject getSubjectByName (String subject_name) throws RepositoryException;
void updateSubject (Subject subject, String new_name) throws RepositoryException;
}
