package com.example.abhi.newspeak;

import android.os.AsyncTask;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by abhil on 19-08-2016.
 */
public class Wikipedia {
    static List<String> contentList;

    public Wikipedia() {
        this.contentList = new ArrayList<>();
    }

    public void getInTheNewsSection() {
        try {
            new ParsePage().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    class ParsePage extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                // get the entire wikipedia page into a docoument
                Document doc = Jsoup.connect("http://en.wikipedia.org/").get();

                //select the div that corresponds to the column "In The News" and return the elements in it
                Elements newsHeadlines = doc.select("#mp-itn > ul");

                //loop through every child element of the parent and print its text
                for (int i = 0; i < newsHeadlines.first().children().size(); i++) {
                    Element tag = newsHeadlines.first().child(i);
                    System.out.println(tag.text());
                    contentList.add(tag.text());
                }
            } catch (IOException e) {
                return "failed";
            }
            return "Success";
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.equals("failed")){
                System.exit(1);
            }
        }
    }
}
