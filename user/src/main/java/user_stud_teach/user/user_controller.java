package user_stud_teach.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@RestController
public class user_controller {
	
	@Autowired
	private user_service user_s;
	private user_Repositry u_R;
	private int retType;
	
		@RequestMapping (method=RequestMethod.POST,value="/{name}/{mail}/{password}/{flag}")
		public void addUser(@RequestBody user usr ,int flag ){
		
			if(flag==0){
				user_s = new Student_Service();
				user_s.addUser(usr);
			}
			else{
				user_s = new Teacher_Service();
				user_s.addUser(usr);
			}
		}
	
	
	@RequestMapping ("/{mail}/{password}/{flag}")
	public void getUser(@PathVariable String mail , int flag){
                
		if(flag==0){
			user_s = new Student_Service();
			user_s.getUser(mail);
		}
		else{
			user_s = new Teacher_Service();
			user_s.getUser(mail);
		}
			

	}
	
	
	
	

}
