import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Game_DB {
	
	public void LoadFields(ArrayList<String>Fields) throws IOException
	{
		
		BufferedReader br =new BufferedReader(new FileReader("SYSFields.txt"));
		String f;
        f=br.readLine();
		while(f!=null )
		{
			Fields.add(f);
			f=br.readLine();
		}
		
		br.close();
	}
	public void LoadCategorys(ArrayList<String>Categorys) throws IOException
	{
		BufferedReader br =new BufferedReader(new FileReader("SYSCategories.txt"));
		String c;
        c=br.readLine();
		while(c!=null )
		{
			Categorys.add(c);
			c=br.readLine();
		}
		
		br.close();
		
	}
	public void LoadGnames(ArrayList<String>Games,String F,String C) throws IOException
	{
		
		if(F.equals("Math")&& C.equals("TandF")  )
		{
			BufferedReader br =new BufferedReader(new FileReader("SYSMathTandF.txt"));
			String g;
	        g=br.readLine();
			while(g!=null )
			{
				Games.add(g);
				g=br.readLine();
			}
			
			br.close();
		}
		else if(F.equals("Math")&& C.equals("MCQ")  )
		{
			BufferedReader br =new BufferedReader(new FileReader("SYSMathMCQ.txt"));
			String g;
	        g=br.readLine();
			while(g!=null )
			{
				Games.add(g);
				g=br.readLine();
			}
			
			br.close();
		}
		else if(F.equals("Science")&& C.equals("MCQ")  )
		{
			BufferedReader br =new BufferedReader(new FileReader("SYSScienceMCQ.txt"));
			String g;
	        g=br.readLine();
			while(g!=null )
			{
				Games.add(g);
				g=br.readLine();
			}
			
			br.close();
		}
		else if(F.equals("Science")&& C.equals("TandF")  )
		{
			BufferedReader br =new BufferedReader(new FileReader("SYSScienceTandF.txt"));
			String g;
	        g=br.readLine();
			while(g!=null )
			{
				Games.add(g);
				g=br.readLine();
			}
			
			br.close();
		}
		///////////////////////////////////////////////// teacher games 
		  BufferedReader brr ;// teacher games 
	    BufferedReader br ;  /// teacher passwrds
	    
		 br =new BufferedReader(new FileReader("Teachers_GCreated.txt"));
		 String p,tf,tc,tg;
		 p=br.readLine();
		 int r =0;
		  while(p!=null)
		  {
			  brr=new BufferedReader(new FileReader(p));
			  tf=brr.readLine();
			  
			  while(tf!=null)
			  { r=1;
				  if(F.equals(tf))
				  {
					  tc=brr.readLine();
					  r=2;
					  if(C.equals(tc))
					  {
						  tg=brr.readLine();
						  r=3;
						  Games.add(tg);
	
					  }
				  }
				 if(r==3)
				  tf=brr.readLine();//// don't scape 
				 else if(r==2)
				 {
					 tf=brr.readLine();/// scape game name 
					 tf=brr.readLine(); /// read next field 
				 }
				 else if(r==1)
				 {
					 tf=brr.readLine();
					 tf=brr.readLine();
					 tf=brr.readLine();
				 }
					 
			  }
			  
			 
			  p=br.readLine();
		  }
		
		
		
	}
	public void LoadGameT(String gn,ArrayList<String>Q,ArrayList<String>ans) throws IOException
	{
		BufferedReader br =new BufferedReader(new FileReader(gn));
		String q,a;
		
			q=br.readLine();
			while(q!=null )
			{
				Q.add(q);
				a=br.readLine();
				ans.add(a);
				q=br.readLine();
		}
			br.close();
		
//////////////////////////////////////////////////////////////////////////////// teacher games 

			
	}
	public void LoadGameMCQ(String gn,ArrayList<String>Q,ArrayList<String>option,ArrayList<String>ans) throws IOException
	{
		BufferedReader br =new BufferedReader(new FileReader(gn));
		String q,a,o;
		
		q=br.readLine();
		while(q!=null )
		{
			Q.add(q);
			
			o=br.readLine();
			option.add(o);
			o=br.readLine();
			option.add(o);
			o=br.readLine();
			option.add(o);
			o=br.readLine();
			option.add(o);
			
			a=br.readLine();
			ans.add(a);
			q=br.readLine();
		}
		
		br.close();
	}

}
