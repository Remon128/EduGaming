package swe2.springbootstarter.user;

import javax.transaction.Transactional;

import swe2.springbootstarter.entities.Student;

@Transactional
public interface StudentRepository extends UserBaseRepository<Student> { /* ... */ }
