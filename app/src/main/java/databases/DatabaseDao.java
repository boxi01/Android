package databases;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import quotation.Quotation;

@Dao
public interface DatabaseDao {

    @Query("SELECT * FROM quotation_table")
    List<Quotation> getQuotations();

    @Query("SELECT * FROM quotation_table WHERE quoteText = :name")
    Quotation getOneQuotation(String name);

    @Query("DELETE FROM quotation_table")
    void deleteAllQuotations();

    @Insert
    void addQuotation(Quotation... quotations);

    @Update
    void updateQuotation(Quotation quotations);

    @Delete
    void deleteQuotation(Quotation quotation);
}
