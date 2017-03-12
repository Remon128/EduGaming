import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Teacher_DB
{

	
	public  boolean AddT(Teacher x) throws IOException
	{
		BufferedReader br =new BufferedReader(new FileReader("TPasswords.txt"));
		String p;
		boolean result = true;
        p=br.readLine();
		while( (p!=null)&&(result==true) )
		{
			if (p.equals(x.password) ) 
			{
				result=false;
			}
			p=br.readLine();
		}
		
		br.close();
		if(result==true)
		{
			
			FileWriter f =new FileWriter( "Teachers.txt",true);
			BufferedWriter out=new BufferedWriter(f);
			
			out.write(x.password);
			out.newLine();
			out.write(x.mail);
			out.newLine();
			out.close();
			f.close();
			
			f=new FileWriter("TPasswords.txt",true);
			out =new BufferedWriter(f);
			out.write(x.password);
			out.newLine();
			out.close();
			f.close();
			
			
			Account y=new Account();
			y.Account_name=x.account_name;
			y.Account_passsword=x.password;
			result=y.Add_Account(y);
			return result;
			
		}
		else
		{
			return result;
		}
		
		
		
	}
	public  boolean VerifyT(Teacher x ) throws IOException
	{
		boolean result = false;
		BufferedReader br =new BufferedReader(new FileReader("Teachers.txt"));
		String p,m;
		p=br.readLine();
		while( (p!=null)&&(result==false) )
		{
			m=br.readLine();
			if( (p.equals(x.password)) && (m.equals(x.mail)) )
			{
				result=true;
			}
			p=br.readLine();
		}
		
		br.close();
		return result;
	}
	
	
 	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
