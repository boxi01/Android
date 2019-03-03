package tasks;

import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.example.myapplication.DashboardActivity;
import com.example.myapplication.QuotationActivity;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import quotation.Quotation;

public class MyHTTPAsyncTask extends AsyncTask<Void,Void, Quotation> {

    WeakReference<QuotationActivity> weakReference;

    public MyHTTPAsyncTask(QuotationActivity weakReference) {
        this.weakReference = new WeakReference<>(weakReference);
    }

    @Override
    protected Quotation doInBackground(Void... voids) {

        Quotation quotation = null;

        try {
            // Creates a new URL from the URI

            // Build the URI to the RESTful web services
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("https");
            builder.authority("api.forismatic.com");

            builder.appendPath("api")      ;
            builder.appendPath("1.0");
            builder.appendPath("");
            // As being a GET request, include the parameters on the URI
            builder.appendQueryParameter("method", "getQuote");
            builder.appendQueryParameter("format", "json");
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(weakReference.get());
            String qLang = settings.getString("language", "English");

            if(qLang.equals("Russian"))
                builder.appendQueryParameter("lang", "ru");
            else
                builder.appendQueryParameter("lang", "en");

            URL url = new URL(builder.toString());
            Log.d("debug", "Hello TEST "+url.toString())   ;
    /*      Log.d(   
            if (useVolley) {
                // Custom Volley request, including URl,
                // listener for successful requests, and
                // listener for any errors
                WeatherRequest request = new WeatherRequest(builder.build().toString(),
                        // Display the retrieved data
                        new Response.Listener<WeatherPOJO>() {
                            @Override
                            public void onResponse(WeatherPOJO response) {
                                displayWeather(response);
                            }
                        },
                        // Notify the user that the request could not be completed,
                        // probably the name of the city was wrong
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(RestfulWeatherActivity.this,
                                        R.string.not_found, Toast.LENGTH_SHORT).show();
                            }
                        });
                // Add the request to the queue for processing
                queue.add(request);

            } else {
                // Launch the AsyncTask in charge of accessing the web service

            } */

            // Get a connection to the web service
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            // Process the response if it was successful (HTTP_OK = 200)
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Open an input channel to receive the response from the web service
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                // Create a Gson object through a GsonBuilder to process the response
                Gson gson = new Gson();
                // Deserializes the JSON response into a Quotation object
                quotation = gson.fromJson(reader, Quotation.class);
                // Close the input channel
                reader.close();
            }

            // Close the connection
            connection.disconnect();


        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (
                ProtocolException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        // Return the received WeatherPOJO object
        return quotation;
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
