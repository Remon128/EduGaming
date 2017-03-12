import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Game_DBTest {
  

  ArrayList<String> Fields = new ArrayList<String>();
  
  @Test
  public void LoadFields() {
	  
	  Game_DB gD = new Game_DB();
	  try {
		gD.LoadFields(Fields);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  String Result;
	  Scanner Read = null;
	try {
		Read = new Scanner(new File("SYSFields.txt"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  for(int i=0; i<Fields.size(); i++){
		  
		  Result = (String) Fields.get(i);
		  Assert.assertEquals(Result, Read.nextLine());
		  
	  }
   
  }
  
}
