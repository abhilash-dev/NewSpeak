package com.example.abhi.newspeak.NyTimes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhi.newspeak.NyTimes.TopStories.Technology.News;
import com.example.abhi.newspeak.NyTimes.TopStories.Technology.WebViewActivity;
import com.example.abhi.newspeak.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.list_item_big, parent, false);

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
        viewHolder.tvListItemReadMore.setOnClickListener(new ReadMoreClickListener(newsObject.getFullNewsUrl()));
    }

    private class ReadMoreClickListener extends AppCompatActivity implements View.OnClickListener {

        private String fullNewsURL;

        public ReadMoreClickListener(String fullNewsURL) {
            this.fullNewsURL = fullNewsURL;
        }

        @Override
        public void onClick(View view) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fullNewsURL),context,WebViewActivity.class);
            browserIntent.putExtra("fullNewsUrl",fullNewsURL);
            context.startActivity(browserIntent);
        }
    }

    @Override
    public int getItemCount() {
        return num_results;
    }
}
