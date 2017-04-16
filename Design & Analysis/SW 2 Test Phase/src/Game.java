import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {
	public  String GName;
	public int NumOfQuestions;
	public String Category,Field;
	Game_DB gdb =new  Game_DB();
	public  void  CreateGame(String password) throws IOException
	{
		String fGname,F,C;
		Scanner input =new Scanner(System.in);
		System.out.println("Enter Field name : ");
		F=input.nextLine();
		System.out.println("Enter Category name : ");
		C=input.nextLine();

		System.out.println("Enter File Name : ");
		fGname=input.nextLine();
		
		FileWriter f =new FileWriter( password+".txt",true);
		BufferedWriter out=new BufferedWriter(f);
		
		out.write(F);
		out.newLine();
		
		out.write(C);
		out.newLine();
		
		out.write(fGname);
		out.newLine();
		
		out.close();
		f.close();
		
		BufferedReader br =new BufferedReader(new FileReader("Teachers_GCreated.txt"));
		String p;
		boolean result = false;
        p=br.readLine();
		while( (p!=null)&&(result==false) )
		{
			if (p.equals(password) ) 
			{
				result=true;
			}
			p=br.readLine();
		}
		
		br.close();
		
		if(result==false)
		{
		    f =new FileWriter( "Teachers_GCreated.txt",true);
		   out=new BufferedWriter(f);
		   out.write(password+".txt");
		   out.newLine();
		   out.close();
		   f.close();
		}
	}
	
	public  void  PlayGame() throws IOException
	{
		int score =0;
		Scanner input =new Scanner(System.in);	
		ArrayList<String>Fields=new ArrayList<String>();   ////////// math science
		ArrayList<String>Categorys=new ArrayList<String>();  ////////// mcq tand f
		ArrayList<String>Games=new ArrayList<String>();  
		ArrayList<String>Ques=new ArrayList<String>();  
		ArrayList<String>options=new ArrayList<String>();  
		ArrayList<String>answers=new ArrayList<String>();  

		gdb.LoadFields(Fields) ;
		 System.out.println("Choose Fielde : ");
		 System.out.println(Fields);
		 Field=input.nextLine();
		 
		 gdb.LoadCategorys(Categorys) ;
		 System.out.println("Choose Categorys : ");
		 System.out.println(Categorys);
		 Category=input.nextLine();
		 
		 gdb.LoadGnames(Games,Field , Category);
		 System.out.println("Choose Game : ");
		 System.out.println(Games);
		 GName=input.nextLine();
		 
		 if(Category.equals("TandF"))
		 {
			 int result;
			 gdb.LoadGameT(GName, Ques, answers);
			
			 for(int i=0 ;i<Ques.size();i++)
			 {
				 System.out.println(Ques.get(i));
				 System.out.println(" 1.True . ");
				 System.out.println(" 2.False. ");
				 result=input.nextInt();
				 if(result==1)
				 {
					 if(answers.get(i).equals("True") )
					 {
						 score++;
						 System.out.println("Your Score is : "+score);
					 }
					 else
					 {
						 System.out.println("Wrong Answer and The right answer is : " + answers.get(i));
						 System.out.println("Your Score is : "+score);
					 }
				 }
				 else if(result==2)
				 {
					 if(answers.get(i).equals("False") )
					 {
						 score++;
						 System.out.println("Your Score is : "+score);
					 }
					 else
					 {
					   System.out.println("Wrong Answer and The right answer is : " + answers.get(i));
					   System.out.println("Your Score is : "+score);
					 }
				 }
			 }
			 
		 }
		 
        
		 if(Category.equals("MCQ"))
		 {
			 gdb.LoadGameMCQ(GName, Ques, options, answers);
			 int j=0;
			 String result;
			 for(int i=0 ;i<Ques.size();i++)
			 {
				 System.out.println(Ques.get(i));
				 System.out.println("1."+options.get(j));
				 j++;
				 System.out.println("2."+options.get(j));
				 j++;
				 System.out.println("3."+options.get(j));
				 j++;
				 System.out.println("4."+options.get(j));
				 j++;

				 
				 result=input.nextLine();
				 if(result.equals(answers.get(i)))
				 {
						 score++;
						 System.out.println("Your Score is : "+score);
				 }
				 else 
				 {
					 System.out.println("Wrong Answer and The right answer is : " + answers.get(i));
					 System.out.println("Your Score is : "+score);
				 }
			 }
			 
		 }
		 
		 /////////////////////////////////////////////////////////////////////////////////
		
		
		
	}
	
	

}
