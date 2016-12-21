/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edugaming;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author khale
 */
public class Database {
 private ArrayList<Registered> users;
 private ArrayList<Game> games;
 
 public Database(){
     users=new ArrayList<Registered>();
     games=new ArrayList<Game>();
 }
 public Registered login(String email,String p){
     Registered r = null;
     for (int i = 0; i < users.size(); i++) {
         if (email.equals(users.get(i).getEmail())&&p.equals(users.get(i).getPassword())) {
             r=users.get(i);
             break;
         }
         else{
             r = new Registered();
         }
     }
     
     return r;
 }
 public void register(String name,String acc,String country,String email,String school,String password){
        Registered r=new Registered();
        r.setCountry(country);
        r.setUName(name);
        r.setEmail(email);
        r.setUID(users.size()+1);
        r.setSchool(school);
        r.setPassword(password); 
        if(acc.equalsIgnoreCase("Teacher")){
        
            r.setBoolTrue();
        }
        users.add(r);
 }
 public void CreateGame(Registered t){
  addGame(t);   
 }
public ArrayList<Game> SelectCalegory(String x){
     ArrayList<Game> list=new ArrayList<Game>();
     for (int i = 0; i < games.size(); i++) {
         if (games.get(i).getGameCat().equalsIgnoreCase(x)) {
             list.add(games.get(i));
         }     
    }
     return list;
 }
  public void playGame(long id){
      int choice=0;
      for (int i = 0; i < games.size(); i++) {
          if(id==games.get(i).getGameID()){
              choice=i;
              break;
          }
      }
      for (int i = 0; i < games.get(choice).getQuestionsSize(); i++) {

        JFrame frame = new JFrame("Play Game");
          String x=JOptionPane.showInputDialog(frame,games.get(choice).getQuestion(i));
          if(!x.equals(games.get(choice).getAnswer(i)))
          {
              JFrame f= new JFrame("Wrong");
              JOptionPane.showMessageDialog(f, "Wrong Answer");
              i--;
          }
      }
      
 }

  public ArrayList<Registered> getUser(){
      return users;
  }
   public ArrayList<Game> getGames(){
      return games;
  }
 public void addGame(Registered t){
       Game g=new Game();
        JFrame frame = new JFrame("Add Game");
         String name = JOptionPane.showInputDialog(frame, "Enter Game Name: ");
         frame = new JFrame("Add Game");
         String cat = JOptionPane.showInputDialog(frame, "Enter Game Category: ");
         frame = new JFrame("Add Game");
         String ty = JOptionPane.showInputDialog(frame, "Enter Game Type: ");
         g.setType(ty);
         frame = new JFrame("Add Game");
         int num= Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Questions Number: "));
        for (int i = 0; i < num; i++) {
             frame = new JFrame("Add Game");
             String q=JOptionPane.showInputDialog(frame, "Enter Game Question: ");
             if (g.getType().equalsIgnoreCase("MCQ")) {
                for (int j = 1; j < 5; j++) {
                    q+="\n";
                    q+=j;
                    q+=") ";
               frame = new JFrame("Add Game");
               q+=JOptionPane.showInputDialog(frame, "Enter Choice "+ j);
                }
                frame = new JFrame("Add Game");
                String a=JOptionPane.showInputDialog(frame, "Enter Question Answer:");
                g.addQuestion(q);
                g.addAnswer(a);            
}
}
        g.setGameCat(cat);
        g.setCID(t.getUID());
        g.setGameID(games.size()+1);
        g.setGameName(name);
        games.add(g);
 }

 
}
