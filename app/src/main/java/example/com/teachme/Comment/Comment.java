package example.com.teachme.Comment;

import example.com.teachme.model.Game;

import example.com.teachme.model.User;


/**
 * Created by MrHacker on 5/13/2017.
 */

public class Comment {

    Integer id ;

    private String comment;

    private Game game;

    public Game getGame() {
        return game;
    }



    public void setGame(Game game) {
        this.game = game;
    }

    private User user;

    public Comment() {

    }



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comment(String comment, User user, Game game) {
        super();
        this.comment = comment;
        this.user = user;
        this.game = game;
    }



    public User getUser() {
        return user;
    }



    public void setUser(User user) {
        this.user = user;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
