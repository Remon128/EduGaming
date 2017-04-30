package example.com.teachme.Connection;

import android.content.Context;

import example.com.teachme.DBHandler;
import example.com.teachme.UserDBTable;
import example.com.teachme.model.User;
import retrofit2.http.Path;

/**
 * Created by MrHacker on 4/25/2017.
 */

public class DbUtils {
    static private DBHandler dbHandler = null;
    static public String mail = null;
    static public String gameId ;
    static public int courseId ;
    static public int questionId ;
    static public boolean isTeacher = false ;
    Context context = null;

    public DbUtils() {

    }

    public static void createDBUtils(Context context)
    {
        setDbHandler(context);
    }

    public static void addUser(String email, String password, String uname) {
        dbHandler.deleteAll();
        if (!dbHandler.isChecked(email)) {
            UserDBTable userDBTable = new UserDBTable();
            userDBTable.setId(email);
            userDBTable.setUsername(uname);
            userDBTable.setPassword(password);
            mail = email ;
            courseId = 0 ;
            gameId = null ;
            questionId = 0 ;
            dbHandler.addUser(userDBTable);
        }//    dbHandler.close();
    }

    public static User getUser()
    {
        User user = new User();
        UserDBTable u = dbHandler.selectUser(mail);
        user.setMail(u.getId());
        user.setPassword(u.getPassword());
        user.setName(u.getUsername());

        return user ;
    }

    public static void delete() {
        if (dbHandler.isChecked(mail))
            dbHandler.deleteAll();
    }

    public static void setDbHandler(Context context) {
        if (dbHandler == null) {
            DbUtils.dbHandler = new DBHandler(context);
        }
    }
}
