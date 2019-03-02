package tasks;

import android.os.AsyncTask;

import com.example.myapplication.DashboardActivity;
import com.example.myapplication.FavouriteActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import databases.Database;
import databases.DatabaseExRoom;
import quotation.Quotation;

public class MyAsyncTask extends AsyncTask<Boolean,Void, List<Quotation>> {

    private WeakReference<FavouriteActivity> weakReference;
    List<Quotation> quotationList;

    @Override
    protected List<Quotation> doInBackground(Boolean... booleans) {
        boolean isRoom=booleans[0];
        if (isRoom==Boolean.TRUE) {
            quotationList = DatabaseExRoom.getInstance(weakReference.get()).databaseDao().getQuotations();
        }
        else {
            quotationList = Database.getInstance(weakReference.get()).getQuotations();
        }
        return quotationList;
    }

    @Override
    protected void onPostExecute(List<Quotation> quotations) {
        weakReference.get().addToAdapter(quotations);
        super.onPostExecute(quotations);
    }

    MyAsyncTask (FavouriteActivity favouriteActivity){
        weakReference=new WeakReference<>(favouriteActivity);
    }
}
