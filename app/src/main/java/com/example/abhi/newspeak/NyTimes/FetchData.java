package com.example.abhi.newspeak.NyTimes;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.abhi.newspeak.*;
import com.example.abhi.newspeak.NyTimes.TopStories.Technology.News;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchData extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    List<News> NYTnews;
    private String jsonURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fetch_data);
        NYTnews = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        Bundle bundle = getIntent().getExtras();
        jsonURL = bundle.getString("jsonURL");
        new GetJSON().execute(jsonURL);

    }

    private class GetJSON extends AsyncTask<String, String, String> {


        HttpURLConnection connection = null;
        BufferedReader reader = null;
        private ProgressDialog progressDialog = new ProgressDialog(FetchData.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Grab a cup of coffee, we are fetching latest news!");
            progressDialog.show();
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    GetJSON.this.cancel(true);
                }
            });
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuffer buffer = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));

                buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            return buffer.toString(); // JSON data from the link

        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            // end process dialog
            this.progressDialog.dismiss();

            // calling adapter after json is fetched
            adapter = new RecyclerAdapter(FetchData.this, json);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(FetchData.this));
        }
    }
}
