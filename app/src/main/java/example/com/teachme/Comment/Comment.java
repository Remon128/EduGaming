package example.com.teachme.Comment;

import example.com.teachme.model.Game;

import example.com.teachme.model.Student;
import example.com.teachme.model.Teacher;
import example.com.teachme.model.User;




public class Comment {

    private Integer id;

    private String comment;

    private Game game;

    private Teacher teacher;

    private Student student;


    public Game getGame() {
        return game;
    }


    public void setGame(Game game) {
        this.game = game;
    }


    public Comment() {

    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comment(String comment, Game game) {
        super();
        this.comment = comment;
        this.game = game;
    }


    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


    public Student getStudent() {
        return student;
    }


    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
