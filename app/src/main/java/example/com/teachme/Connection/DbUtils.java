package example.com.teachme.Connection;

import android.content.Context;

import example.com.teachme.DBHandler;
import example.com.teachme.UserDBTable;
import retrofit2.http.Path;

/**
 * Created by MrHacker on 4/25/2017.
 */

public class DbUtils {
    static private DBHandler dbHandler = null;
    static public String email = null;
    Context context = null;

    public DbUtils() {

    }

    public static void createDBUtils(Context context)
    {
        setDbHandler(context);
    }

    public static void addUser(String email, String password, String uname) {
        if (!dbHandler.isChecked(email)) {
            UserDBTable userDBTable = new UserDBTable();
            userDBTable.setId(email);
            userDBTable.setUsername(uname);
            userDBTable.setPassword(password);
            dbHandler.addUser(userDBTable);
        }//    dbHandler.close();
    }

    public static void delete() {
        if (dbHandler.isChecked(email))
            dbHandler.deleteUser(email);
    }

    public static void setDbHandler(Context context) {
        if (dbHandler == null) {
            DbUtils.dbHandler = new DBHandler(context);
            email = dbHandler.selectAll().get(0).getId();
        }
    }
}
