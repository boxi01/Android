package myadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

import quotation.Quotation;

public class MyAdapter extends ArrayAdapter {

    private int layout;

    private class ViewHolder {
        TextView tvQ;
        TextView tvA;
    }


    public MyAdapter( @NonNull Context context, int resource, @NonNull List<Quotation> qList){
        super(context, resource, qList);
        this.layout = resource;
    }

    @NonNull
    @Override
    //TODO dokonczyc implmentację getView()
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View result = convertView;
        ViewHolder viewHolder;

        if (result == null) {
            result = LayoutInflater.from(getContext()).inflate(layout, null);
           // View newView = layoutInflater.inflate(position, null);
            viewHolder = new ViewHolder();
            viewHolder.tvQ = result.findViewById(R.id.quoteFav);
            Log.d("DEBUG", "Trudne 4");

            viewHolder.tvA = result.findViewById(R.id.authotFav);
            result.setTag(viewHolder);
        }

            Quotation quotation = (Quotation) getItem(position);
            // Retrieve the ViewHolder from the View
            viewHolder = (ViewHolder) result.getTag();
            // Populate the View with information from the required position of the data source
            Log.d("DEBUG", "Trudne 5");

            viewHolder.tvQ.setText(quotation.getQuoteText());
            viewHolder.tvA.setText(quotation.getQuoteAuthor());

            // Return the View
            return result;

    }

    public void update(Quotation quotation, int position) {
        // Get the quotation located a the given position
        Quotation replaceQuotation = (Quotation) getItem(position);
        // Remove that Quotation
        remove(replaceQuotation);
        // Insert the new Quotation (the one with updated information) at that same position
        insert(quotation, position);
    }

}
