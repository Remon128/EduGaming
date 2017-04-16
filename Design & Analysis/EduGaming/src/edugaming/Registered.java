/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edugaming;

/**
 *
 * @author Rami
 */
public class Registered{
    private String email;
    private String password;
    private String country;
    private String school;
    private boolean isTeacher;
     private String userName;
    private long userID;

    public Registered(){
        userName="";
        userID=0;
        email="";
        password="";
        country="";
        school="";
        
    }
        public void setUName(String n){
        userName=n;
    }
    public void setUID(long i){
        userID=i;
    }
    public String getUName(){
        return userName;
    }
    public long getUID(){
        return userID;
    }

    public void setEmail(String e){
        email=e;
    }
    public void setPassword(String p){
        password=p;
    }
    public void setCountry(String c){
        country=c;
    }
    public void setSchool(String s){
        school=s;
    }
    public void setBoolTrue(){
       isTeacher=true;
    }
    public boolean getBool(){
        return isTeacher;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getCountry(){
        return country;
    }
    public String getSchool(){
        return school;
    }
}
