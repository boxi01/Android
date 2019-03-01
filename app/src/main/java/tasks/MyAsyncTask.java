package tasks;

import android.os.AsyncTask;

import com.example.myapplication.FavouriteActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import quotation.Quotation;

public class MyAsyncTask extends AsyncTask<Boolean,Void, List<Quotation>> {

    private WeakReference<FavouriteActivity> weakReference;

    @Override
    protected List<Quotation> doInBackground(Boolean... booleans) {
        return null;
    }

    @Override
    protected void onPostExecute(List<Quotation> quotations) {
        super.onPostExecute(quotations);
    }

    MyAsyncTask (FavouriteActivity favouriteActivity){
        weakReference=new WeakReference<>(favouriteActivity);
    }
}
