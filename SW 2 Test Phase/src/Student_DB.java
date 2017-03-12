import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Student_DB
{
	
	
public  boolean AddS(Student x) throws IOException
{
		BufferedReader br =new BufferedReader(new FileReader("SPasswords.txt"));
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
		FileWriter f =new FileWriter( "Students.txt",true);
		BufferedWriter out=new BufferedWriter(f);
		out.write(x.password);
		out.newLine();
		
      	out.write(x.mail);
		out.newLine();
		out.close();
		f.close();
		////////////////////////////////////////////////// not append 
//		PrintWriter out =new PrintWriter( "Students.txt");
//        out.println(x.password);
//        out.println(x.mail);
//        out.close();
        /////////////////////////////////////////////////////
		
		f=new FileWriter( "SPasswords.txt",true);
		out=new BufferedWriter(f);
		out.write(x.password);
        out.newLine();
        out.close();
	    f.close();
		///////////////////////////////////////////////// not append 
//        out =new PrintWriter("SPasswords.txt" );
//		out.println(x.password);
//		out.close();
		//////////////////////////////////////////////////////////
		
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
	
public  boolean VerifyS(Student x ) throws IOException
 {
		boolean result = false;
		BufferedReader br =new BufferedReader(new FileReader("Students.txt"));
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

}
