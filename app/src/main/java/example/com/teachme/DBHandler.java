package example.com.teachme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by MrHacker on 4/25/2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PASS = "password";
    private static final String COLUMN_UNAME = "username";

    private Context context;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            //Toast.makeText(context, "onCreate method", Toast.LENGTH_LONG).show();
            String query = "CREATE TABLE " + TABLE_NAME +
                    "( " +
                    COLUMN_ID + " TEXT PRIMARY KEY ," +
                    COLUMN_PASS + " TEXT , " +
                    COLUMN_UNAME + " TEXT " +
                    ");";
            db.execSQL(query);
        } catch (SQLException e) {
            //Toast.makeText(context,"due to: "+e,Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            //Toast.makeText(context,"onUpgrade method",Toast.LENGTH_LONG).show();
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        } catch (SQLException e) {
            // Toast.makeText(context,"due to: "+e,Toast.LENGTH_LONG).show();
        }
    }

    public void addUser(UserDBTable table) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, table.getId());
        values.put(COLUMN_PASS, table.getPassword());
        values.put(COLUMN_UNAME, table.getUsername());

        SQLiteDatabase db = getWritableDatabase();

        try {
            //Toast.makeText(context,"addMovie method",Toast.LENGTH_LONG).show();

            long rowId = db.insert(TABLE_NAME, null, values);

        } catch (SQLException e) {
            //Toast.makeText(context,"due to add : "+e,Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }


    public void deleteUser(String emailId) {
        SQLiteDatabase db = getWritableDatabase();
        try {
//            Toast.makeText(context,"deleteMovie method",Toast.LENGTH_LONG).show();

            db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=\"" + emailId + "\" ;");

        } catch (SQLException e) {
            //Toast.makeText(context,"due to delete: "+e,Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        try {

            db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE 1 ; ");

        } catch (SQLException e) {
            //Toast.makeText(context,"due to delete: "+e,Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }

    public boolean isChecked(String id) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " =\"" + id + "\"";
        Cursor c = db.rawQuery(query, null);
        int count = c.getCount();
        db.close();
        c.close();

        return count > 0;
    }

    public UserDBTable selectUser(String mail) {
        UserDBTable tables = new UserDBTable();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + mail + " ;";
        Cursor c = db.rawQuery(query, null);
        UserDBTable t = null;

        try {
            //      Toast.makeText(context,"databaseToString method",Toast.LENGTH_SHORT).show();

            c.moveToFirst();

            String row = "";
            while (!c.isAfterLast()) {

                t = new UserDBTable();
                t.setId(c.getString(c.getColumnIndex(COLUMN_ID)));
                t.setPassword(c.getString(c.getColumnIndex(COLUMN_PASS)));
                t.setUsername(c.getString(c.getColumnIndex(COLUMN_UNAME)));
                c.moveToNext();
            }
        } catch (SQLException e) {
            // Toast.makeText(context,"due to: "+e,Toast.LENGTH_LONG).show();
        } finally {
            c.close();
            db.close();
        }

        return t;
    }


    public ArrayList<UserDBTable> selectAll() {
        ArrayList<UserDBTable> tables = new ArrayList<UserDBTable>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1 ;";

        Cursor c = db.rawQuery(query, null);

        try {
            //      Toast.makeText(context,"databaseToString method",Toast.LENGTH_SHORT).show();

            c.moveToFirst();

            String row = "";
            UserDBTable t;
            while (!c.isAfterLast()) {

                t = new UserDBTable();
                t.setId(c.getString(c.getColumnIndex(COLUMN_ID)));
                t.setPassword(c.getString(c.getColumnIndex(COLUMN_PASS)));
                t.setUsername(c.getString(c.getColumnIndex(COLUMN_UNAME)));

                tables.add(t);
                c.moveToNext();
            }
        } catch (SQLException e) {
            // Toast.makeText(context,"due to: "+e,Toast.LENGTH_LONG).show();
        } finally {
            c.close();
            db.close();
        }

        return tables;
    }
}