package com.example.abhi.newspeak;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by abhil on 19-08-2016.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView tvListItemTitle, tvListItemDescription;
    ImageView ivListItemAvatar;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        tvListItemTitle = (TextView) itemView.findViewById(R.id.list_item_title);
        tvListItemDescription = (TextView) itemView.findViewById(R.id.list_item_description);
        ivListItemAvatar = (ImageView) itemView.findViewById(R.id.list_item_avatar);
    }
}
