package swe2.springbootstarter.user;

import javax.transaction.Transactional;

import swe2.springbootstarter.entities.Users;

@Transactional
public interface UserRepository extends UserBaseRepository<Users> { 
	
	
}
