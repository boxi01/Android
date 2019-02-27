package databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.QuotationActivity;

import java.util.ArrayList;
import java.util.List;

import quotation.Quotation;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "quotation_database", null, 1);
    }

    // Singleton pattern to centralize access to the
    private static Database ourInstance;

    public synchronized static Database getInstance(Context context) {

        if (ourInstance == null) {
            ourInstance = new Database(context, "quotation_database", null, 1);
        }
        return ourInstance;

    }

    private Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db){

        //  String not null: qoute
        //  String not null: name


        db.execSQL("CREATE TABLE quotation_database (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "quoteText TEXT NOT NULL, quoteAuthor TEXT NOT NULL);");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }


    public List<Quotation> getQuotations() {
        List<Quotation> result = new ArrayList<>();
        Quotation quotation;

        // Get access to the database in read mode
        SQLiteDatabase database = getReadableDatabase();
        // Query the table to get the text of quotation and name of author
        Cursor cursor = database.query("quotation_database", new String[]{"quoteText", "quoteAuthor"},
                null, null, null, null, null);
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
    }


        public void addQuotation(Quotation quotation) {
        // Get access to the database in write mode
        SQLiteDatabase database = getWritableDatabase();
        // Insert the new quotation into the table (autoincremental id)
        ContentValues values = new ContentValues();
        values.put("quoteText", quotation.getQuoteText());
        values.put("quoteAuthor", quotation.getQuoteAuthor());
        database.insert("quotation_database", null, values);
        // Close the database
        database.close();
    }

    /*
        Update the data of a given contact from the database
    */
    public void updateQuotation(String name, Quotation quotation) {
        // Get access to the database in write mode
        SQLiteDatabase database = getWritableDatabase();
        // Update the data from the contact identified by the given name
        ContentValues values = new ContentValues();
        values.put("quoteText", quotation.getQuoteText());
        values.put("quoteAuthor", quotation.getQuoteAuthor());
        database.update("quotation_database", values, "name=?", new String[]{name});
        // Close the database
        database.close();
    }
}
