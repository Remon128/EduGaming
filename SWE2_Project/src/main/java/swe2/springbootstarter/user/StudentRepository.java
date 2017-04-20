package swe2.springbootstarter.user;

import javax.transaction.Transactional;

@Transactional
public interface StudentRepository extends UserBaseRepository<Student> { /* ... */ }
