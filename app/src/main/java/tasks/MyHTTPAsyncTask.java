package tasks;

import android.os.AsyncTask;

import com.example.myapplication.QuotationActivity;

import java.lang.ref.WeakReference;

import quotation.Quotation;

public class MyHTTPAsyncTask extends AsyncTask<Void,Void, Quotation> {

    private WeakReference<QuotationActivity> weakReference;

    @Override
    protected Quotation doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        weakReference.get().showProgressBar();
    }

    @Override
    protected void onPostExecute(Quotation quotation) {
        super.onPostExecute(quotation);
        weakReference.get().hideProgressBar(quotation);
    }
}
