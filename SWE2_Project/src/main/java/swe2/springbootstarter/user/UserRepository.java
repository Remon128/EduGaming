package swe2.springbootstarter.user;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends UserBaseRepository<Users> { /* ... */ }
