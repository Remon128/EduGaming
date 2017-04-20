package user_stud_teach.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Teacher_Service extends user_service
{
	
	@Autowired
    private user_Repositry teacher_R;

	@Override
	protected void addUser(user user_obj) {
		teacher_R.save(user_obj);
		
	}

	 protected user getUser(String mail) {
		teacher_R.findOne(mail);
		return user;
	}

    
	

}
