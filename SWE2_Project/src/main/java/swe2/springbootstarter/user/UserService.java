package swe2.springbootstarter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swe2.springbootstarter.entities.Users;

@Service
public class UserService {

	
	@Autowired
	UserRepository userRepository;
	
	
	public void addUser(Users user){
		userRepository.save(user);
	}
	public Users getUser(String mail){
		
		return userRepository.findOne(mail);
	}
	
	public void deleteUser(String mail){
		userRepository.delete(mail);
	}
	public boolean isUserExist(String mail) {
		// TODO Auto-generated method stub
		return userRepository.exists(mail);
	}
	
}
