package example.com.teachme.Comment;

import example.com.teachme.model.Game;

import example.com.teachme.model.Student;
import example.com.teachme.model.Teacher;
import example.com.teachme.model.User;

public class Comment {

    Integer id ;

    private String comment;
    private String mail;

    private Game game;



    public Comment() {

    }



    public void setGame(Game game) {
        this.game = game;
    }



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Game getGame() {
        return game;
    }

    public Comment(String comment, Game game, String mail) {
        super();
        this.comment = comment;
        this.game = game;
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", mail='" + mail + '\'' +
                ", game=" + game +
                '}';
    }

    public String getMail() {
        return mail;
    }


    public void setMail(String mail) {
        this.mail = mail;
    }


    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }

}