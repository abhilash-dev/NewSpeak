package com.example.abhi.newspeak.NyTimes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhi.newspeak.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class Main extends AppCompatActivity {

    private RecyclerView topicsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        topicsRecyclerView = (RecyclerView) findViewById(R.id.topics_recycler_view);

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this);
        topicsRecyclerView.setAdapter(adapter);
        topicsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initImageLoader();
    }

    void initImageLoader() {
        // Create default options which will be used for every
        //  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()

                .cacheInMemory(true)
                .cacheOnDisk(true)

                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())

                .defaultDisplayImageOptions(defaultOptions)

                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start
    }


    private class MyRecyclerViewAdapter extends RecyclerView.Adapter {

        private Context context;
        private LayoutInflater inflater;

        public MyRecyclerViewAdapter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            Toast.makeText(Main.this, "Tap on a topic to read more!", Toast.LENGTH_LONG).show();
        }

        String[] topics = {"Business", "Technology", "Sports", "Science", "Health", "Movies", "Fashion",
                "Arts", "Books", "Opinion", "World", "National", "Politics", "Up Shot", "NY Region",
                "Theatre", "Sunday Review", "T Magazine", "Real Estate", "Automobiles", "Obituaries", "Insider"};

        String[] jsonList = {"https://api.nytimes.com/svc/topstories/v2/business.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/technology.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/sports.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/science.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/health.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/movies.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/fashion.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/arts.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/books.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/opinion.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/world.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/national.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/politics.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/upshot.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/nyregion.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/theatre.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/sundayreview.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/tmagazine.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/realestate.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/automobiles.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/obituaries.json?api-key=ccde25f273d54d74be7269f71376890c",
                "https://api.nytimes.com/svc/topstories/v2/insider.json?api-key=ccde25f273d54d74be7269f71376890c"};


        @Override
        public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.inflate(R.layout.topics_list_item, parent, false);

            MyRecyclerViewHolder viewHolder = new MyRecyclerViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyRecyclerViewHolder viewHolder = (MyRecyclerViewHolder) holder;
            OnTopicClickListener onTopicClickListener = new OnTopicClickListener(jsonList[position]);

            ImageLoader.getInstance().displayImage("https://placehold.it/450x200", viewHolder.ivTopicAvatar); // Default options will be used

            /*SpannableStringBuilder builder = new SpannableStringBuilder();
            builder.append(topics[position]).append(" ");
            builder.setSpan(new ImageSpan(context, R.drawable.next), builder.length() - 1, builder.length(), 0);
            viewHolder.tvTopicTitle.setText(builder);*/

            viewHolder.tvTopicTitle.setText(topics[position]);

            viewHolder.tvTopicTitle.setOnClickListener(onTopicClickListener);
        }

        class OnTopicClickListener implements View.OnClickListener {
            private String json;

            public OnTopicClickListener(String json) {
                this.json = json;
            }

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, FetchData.class);
                intent.putExtra("jsonURL", json);
                startActivity(intent);
            }
        }

        @Override
        public int getItemCount() {
            return topics.length;
        }
    }

    private class MyRecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView ivTopicAvatar;
        TextView tvTopicTitle;

        public MyRecyclerViewHolder(View itemView) {
            super(itemView);

            ivTopicAvatar = (ImageView) itemView.findViewById(R.id.topic_list_item_avatar);
            tvTopicTitle = (TextView) itemView.findViewById(R.id.topic_list_item_title);

            Typeface face = Typeface.createFromAsset(getAssets(), "fonts/FHACondFrenchNC.ttf");
            tvTopicTitle.setTypeface(face);
        }
    }
}
