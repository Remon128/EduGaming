package example.com.teachme.User;


public abstract class User {
	
	protected String mail;
	protected String name;
	protected String password;
	
	protected User(){
		
	}
	
	protected User(String name , String mail , String password ){
		
		this.name = name;
		this.mail = mail;
		this.password = password;
	}
	
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected String getMail() {
		return mail;
	}
	protected void setMail(String mail) {
		this.mail = mail;
	}
	protected String getPassword() {
		return password;
	}
	protected void setPassword(String password) {
		this.password = password;
	}

}
