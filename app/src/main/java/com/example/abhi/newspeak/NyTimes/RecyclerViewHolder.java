package com.example.abhi.newspeak.NyTimes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhi.newspeak.R;

/**
 * Created by abhil on 19-08-2016.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView tvListItemTitle, tvListItemDescription,tvListItemReadMore;
    ImageView ivListItemAvatar;
    Button btnPlay;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        tvListItemTitle = (TextView) itemView.findViewById(R.id.list_item_title);
        tvListItemDescription = (TextView) itemView.findViewById(R.id.list_item_description);
        ivListItemAvatar = (ImageView) itemView.findViewById(R.id.list_item_avatar);
        tvListItemReadMore = (TextView)itemView.findViewById(R.id.list_item_read_more);
        btnPlay = (Button)itemView.findViewById(R.id.play);
    }
}
