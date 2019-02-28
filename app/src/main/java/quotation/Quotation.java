package quotation;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "quotation_table")


public class Quotation {

    @PrimaryKey(autoGenerate = true)
    private int quotation_id;

    @ColumnInfo (name = "quoteText")
    private String quoteText;

    @ColumnInfo (name = "quoteAuthor")
    private String quoteAuthor;


    public Quotation(String quoteText, String quoteAuthor) {
        this.quoteText = quoteText;
        this.quoteAuthor = quoteAuthor;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    public void setQuoteAuthor(String quoteAuthor) {
        this.quoteAuthor = quoteAuthor;
    }

    public int getQuotation_id() {
        return quotation_id;
    }

    public void setQuotation_id(int quotation_id) {
        this.quotation_id = quotation_id;
    }
}
