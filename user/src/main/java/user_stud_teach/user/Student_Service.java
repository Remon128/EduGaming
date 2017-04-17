package user_stud_teach.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Student_Service extends user_service
{
    @Autowired
    private user_Repositry user_R;


	protected void addUser(user user_obj) {
		user_R.save(user_obj);
	}
	
	protected user getUser(String mail) {
		user_R.findOne(mail);
		return user;
	}
	
   
	

}
