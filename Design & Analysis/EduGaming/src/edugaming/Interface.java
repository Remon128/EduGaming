/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edugaming;

import java.util.Scanner;

/**
 *
 * @author khale
 */
public class Interface {
    private static Database db;
    
    public Interface(){
        db=new Database();
    }    
    public int getUsersSize(){
        return db.getUser().size();
    }
    public Registered reqLogin(String email,String password){
        //System.out.println(email);
        //Scanner in=new Scanner(System.in);
   /*     System.out.println("Enter User Email: ");
        String email=in.nextLine();
        in=new Scanner(System.in);
        System.out.println("Enter User Password: ");
        String password=in.nextLine();
*/        Registered r=db.login(email,password);
  /*      if(r.getEmail().length()==0){
            System.out.println("Wrong Email or Password");
            reqLogin();
        }
  */      
        return r;
    }
    public void reqRegister(String name,String acc,String country,String email,String school,String password){
        db.register(name,acc,country,email,school,password);
    }
    public void reqCreate(Registered t){
        db.CreateGame(t);
    }
        
    public void reqCategory(){
     Scanner in=new Scanner(System.in);
     String x=in.nextLine();
     db.SelectCalegory(x);
    }
    
    public void reqPlay(long id){
        db.playGame(id);
    }
    public void logOut(){
        
    }
    
}
