import java.io.IOException;
import java.util.Scanner;


public class Registration
{
	static Student_DB sdb=new Student_DB() ;
	static Teacher_DB tdb=new Teacher_DB() ;
	static Game x;
	public static  void  Login(String type ) throws IOException
	{
		Scanner input =new Scanner(System.in);	
		String t=type;
		boolean result=false;
	    if(type.equals("Student"))
	    {
	    	Student x = new Student();
	    	
			System.out.println("Enter Your mail :   ");
			x.mail=input.nextLine();
			System.out.println("Enter Your Password :   ");
			x.password=input.nextLine();
	    	result=sdb.VerifyS(x);
	    	//System.out.println(result);
	    	if(result==false)
	    	{
	    		System.out.println("Your data not correct try again . ");
	    		Login(t);
	    	}
	    	else
	    	{
	    		String Account_name = null;
	    		Display(t,Account_name,x.password);
	    	}
	    }
	    else if(type.equals("Teacher"))
	    {
	    	Teacher x =new Teacher();
	    	System.out.println("Enter Your mail :   ");
			x.mail=input.nextLine();
			System.out.println("Enter Your Password :   ");
			x.password=input.nextLine();
	    	result=tdb.VerifyT(x);
	    	if(result==false)
	    	{
	    		System.out.println("Your data not correct try again . ");
	    		Login(t);
	    	}
	    	else
	    	{
	    		String Account_name = null;
	    		Display(t,Account_name,x.password);
	    	}
	    	
	    }
	    
		
		//input.close();
	}
	
	
	public static void Signup(String type) throws IOException
	{
		Scanner input =new Scanner(System.in);	
		String t=type;
		boolean result=false;
		if(type.equals("Student"))
		{
			Student x=new Student();
			System.out.println("Enter Your mail :   ");
			x.mail=input.nextLine();
			System.out.println("Enter Your Password :   ");
			x.password=input.nextLine();
			System.out.println("Enter Your Account Name :  ");
			x.account_name=input.nextLine();
			result =sdb.AddS(x);
			if(result ==true)
			{
				System.out.println(" Your New Account Created Successfully .");
				String n =x.password;
				System.out.println(n);
				Display(type,x.account_name,n);
			}
			else if(result ==false)
			{
				System.out.println(" Can't Create Your Account Enter Your Data Again .");
				Signup(t);
			}
			
		}
		else if(type.equals("Teacher")) 
		{
			Teacher x=new Teacher();
			System.out.println("Enter Your mail :   ");
			x.mail=input.nextLine();
			System.out.println("Enter Your Password :   ");
			x.password=input.nextLine();
			System.out.println("Enter Your Account Name :  ");
			x.account_name=input.nextLine();
			result =tdb.AddT(x);
			if(result ==true)
			{
				System.out.println(" Your New Account Created Successfully .");
				String n=x.password;
				Display(type,x.account_name,n);
			}
			else if(result ==false)
			{
				System.out.println(" Can't Create Your Account Enter Your Data Again  .");
				Signup(t);
			}
			
		}
		
		//input.close();
	}
	
	public static void Display(String type, String Account_name,String password) throws IOException
	{
		//System.out.println("I'm In Display . ");
		String n =password;
		if(type.equals("Student"))
		{
			x=new Game();
			x.PlayGame();
		}
		else if(type.equals("Teacher"))
		{
			x=new Game();
			x.CreateGame(n);
		}
	}
	public static void main(String[] args) throws IOException
	
	{
		Scanner input =new Scanner(System.in);
		int result ;
		int type;
		System.out.println(" 1 For Login And 2 For Sign Up :  ");
		result=input.nextInt();
		if(result==1)
		{
			System.out.println(" 1 For Login As a Teacher and 2 For Login As a Student . ");
			type=input.nextInt();
			if(type==1)
			{
				Login("Teacher");
			}
			else if(type==2)
			{
				Login("Student");
			}
		}
		else if(result==2)
		{
			System.out.println(" 1 For Signup As a Teacher and 2 For Signup As a Student . ");
			type=input.nextInt();
			if(type==1)
			{
				Signup("Teacher");
			}
			else if(type==2)
			{
				Signup("Student");
			}
		}
		
	//Signup("Teacher");
	//Signup("Teacher");
	//Login("Teacher");
	//Login("Teacher");
		
	//Signup("Student");
	//Signup("Student");
	//Login("Student");
	//Login("Student");

		
		// TODO Auto-generated method stub

	}

}
