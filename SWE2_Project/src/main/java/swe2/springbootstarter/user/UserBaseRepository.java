package swe2.springbootstarter.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import swe2.springbootstarter.entities.Users;

@NoRepositoryBean
public interface UserBaseRepository<T extends Users> 
extends CrudRepository<T, String> {

  
 
}
