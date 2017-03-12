import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AccountTest {
  @DataProvider(name="Accounts")
  public static Object[][] accounts() {
	  Account a=new Account();
	  Account b=new Account();
	  b.Account_name="swe"; b.Account_passsword=null;
	  Account c=new Account();
	  c.Account_name=null; c.Account_passsword="sd";
	  Account d=new Account();
	  d.Account_name="ahmed"; d.Account_passsword="12345678";
	  Account e=new Account();
	  e.Account_name="ahmed"; e.Account_passsword="123a";
	  Account f=new Account();
	  f.Account_name="ali"; f.Account_passsword="12345678";
	  
	return new Object[][] {{false,a},{false,b},{false,c},{true,d},{false,e},{true,f}}; 
  
  }
  @Test (dataProvider="Accounts")
  public void Add_Account(boolean result,Account x) {
	  Account n=new Account();
	 try {
		Assert.assertEquals(result, n.Add_Account(x));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    //throw new RuntimeException("Test not implemented");
  }
}
