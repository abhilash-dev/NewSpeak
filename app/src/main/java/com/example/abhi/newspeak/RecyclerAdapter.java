package com.example.abhi.newspeak;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by abhil on 19-08-2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter {

    Context context;
    LayoutInflater inflater;

    public RecyclerAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
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
        viewHolder.tvListItemTitle.setText("Headline - " + position);
        //ToDO: get the desription from SCRAPING module and set it here
    }

    @Override
    public int getItemCount() {
        return 5; // no of rows to display in the recyclerView (Hardcoded for now)
    }
}
