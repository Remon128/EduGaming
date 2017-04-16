import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Account 
{
	String Account_name;
	String Account_passsword;
	
public 	boolean Add_Account(Account y) throws IOException

	{
		boolean result;
		
		FileWriter f =new FileWriter( "Accounts.txt",true);
		BufferedWriter out=new BufferedWriter(f);
		out.write(y.Account_passsword );
		out.newLine();
		out.write(y.Account_name);
		out.newLine();
		result=true;
		out.close();
		f.close();
		
		//f.close();
//		PrintWriter out=new PrintWriter("Accounts.txt");
//		out.println(y.Account_passsword);
//		out.println(y.Account_name);
		return result;
	}
	
	
	

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
