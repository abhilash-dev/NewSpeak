package com.example.abhi.newspeak.NyTimes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.abhi.newspeak.NyTimes.TopStories.Technology.News;
import com.example.abhi.newspeak.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by abhil on 19-08-2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {

    Context context;
    LayoutInflater inflater;
    private List<News> NYTnews;
    JSONObject jsonObject;
    int num_results;

    public RecyclerAdapter(Context context, String json) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        try {
            jsonObject = new JSONObject(json);
            num_results = jsonObject.getInt("num_results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.NYTnews = new ArrayList<>();
        parseJSON(jsonObject);
    }

    public void parseJSON(JSONObject jsonObject) {
        try {
            JSONObject resultObject;
            for (int i = 0; i < num_results; i++) {
                resultObject = jsonObject.getJSONArray("results").getJSONObject(i);
                News news = new News();

                news.setTitle(resultObject.getString("title"));
                news.setDescription(resultObject.getString("abstract"));
                news.setFullNewsUrl(resultObject.getString("url"));

                JSONArray multimedia = resultObject.getJSONArray("multimedia");
                if (multimedia.length() == 0) {
                    news.setImgUrl("https://placehold.it/350x150");
                    news.setImgCaption("Sample Image Caption");
                } else {
                    JSONObject multimediaObject = multimedia.getJSONObject(multimedia.length() - 1);

                    news.setImgUrl(multimediaObject.getString("url"));
                    news.setImgCaption(multimediaObject.getString("caption"));
                }

                NYTnews.add(news);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // dummy data to check for recyclerView's 5 size error

        /*for (int i = 0; i < num_results; i++) {
            News news = new News();
            news.setTitle("Title " + i);
            news.setDescription("Sample Description " + i);
            news.setImgUrl("https://placehold.it/350x150");
            NYTnews.add(news);
        }*/
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.list_item, parent, false);

        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerViewHolder viewHolder = (RecyclerViewHolder) holder;

        News newsObject = new News();
        newsObject = NYTnews.get(position);

        viewHolder.tvListItemTitle.setText(newsObject.getTitle());
        viewHolder.tvListItemDescription.setText(newsObject.getDescription());
        ImageLoader.getInstance().displayImage(newsObject.getImgUrl(), viewHolder.ivListItemAvatar); // Default options will be used
    }

    @Override
    public int getItemCount() {
        return num_results;
    }
}