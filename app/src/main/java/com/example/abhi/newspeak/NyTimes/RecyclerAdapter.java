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

import com.example.abhi.newspeak.R;

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
import java.util.concurrent.ExecutionException;

/**
 * Created by abhil on 19-08-2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {

    Context context;
    LayoutInflater inflater;
    JSONObject resultObject;
    Bitmap bitmap;

    public RecyclerAdapter(Context context, JSONObject resultObject,Bitmap bitmap) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.resultObject = resultObject;
        this.bitmap = bitmap;
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
        try {
            if (resultObject != null) {
                viewHolder.tvListItemTitle.setText(resultObject.getString("title"));
                viewHolder.tvListItemDescription.setText(resultObject.getString("abstract"));
                viewHolder.ivListItemAvatar.setImageBitmap(bitmap);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
