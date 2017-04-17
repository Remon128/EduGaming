package user_stud_teach.user;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class user_service
{
	//private int type;
	
	user_service(){
		
	}
	protected abstract void addUser(user user_obj);
	protected abstract user getUser(String mail);

}
