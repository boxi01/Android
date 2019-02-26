package databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import quotation.Quotation;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "database_file", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db){

        //  String not null: qoute
        //  String not null: name


        db.execSQL("CREATE TABLE contacts_table (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "quoteText TEXT NOT NULL, quoteAuthor TEXT NOT NULL);");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    /*
    public List<Quotation> getContacts() {
        List<Quotation> result = new ArrayList<>();
        Quotation quotation;

        // Get access to the database in read mode
        SQLiteDatabase database = getReadableDatabase();
        // Query the table to get the text of quotation and name of author
        Cursor cursor = database.query("quotation_table", new String[]{"quoteText", "quoteAuthor"},
                null, null, null, null, "name", null);
        // Go through the resulting cursor
        while (cursor.moveToNext()) {
            // Create a HashMap<String,String> object for the given entry in the database
            quotation = new Quotation(
                    cursor.getString(0),
                    cursor.getString(1));
            // Add the object to the result list
            result.add(quotation);
        }
        // Close the cursor and database
        cursor.close();
        database.close();
        return result;
    } */
}
