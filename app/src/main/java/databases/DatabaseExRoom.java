package databases;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import quotation.Quotation;

public abstract class DatabaseExRoom extends RoomDatabase {

    private static DatabaseExRoom quotationsDatabase;

    public synchronized static DatabaseExRoom getInstance(Context quotation) {
        if (quotationsDatabase == null) {
            quotationsDatabase = Room
                    .databaseBuilder(quotation, DatabaseExRoom.class, "quotation_table")
                    .allowMainThreadQueries()
                    .build();
        }
        return quotationsDatabase;
    }

    public abstract DatabaseDao databaseDao();

}
