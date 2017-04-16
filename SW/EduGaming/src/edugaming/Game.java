/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edugaming;

import java.util.ArrayList;

/**
 *
 * @author Rami
 */
class Game
{
    private long gameID,creatorID;
    private String gameName,gameCategory,type;
   private ArrayList<String> questions,answers;
   public Game(){
       questions= new ArrayList<String>();
       answers= new ArrayList<String>();
   } 
   public void addQuestion(String s){
       questions.add(s);
   }
   public void addAnswer(String s){
       answers.add(s);
   }
   public String getQuestion(int i){
       return questions.get(i);
   }
   public int getQuestionsSize(){
       return questions.size();
   }
   public String getAnswer(int i){
       return answers.get(i);
   }
   public void setGameID(long id)
    {
       gameID=id;
    }
    public void setGameName(String gn)
    {
        gameName=gn;
    }
    public void setGameCat(String gc)
    {
       gameCategory=gc;
    }
    public void setCID(long cID)
    {
       creatorID=cID;
    }
    public void setType(String t){
        type=t;
    }
    public String getType(){
        return type;
    }
    public long getGameID()
    {
        return gameID;
    }
    public String getGameName()
    {
        return gameName;
    }
    public String getGameCat()
    {
        return gameCategory;
    }
    public long getCID()
    {
        return creatorID;
    }
    
}