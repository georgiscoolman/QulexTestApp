package com.georg.qulextestapp.view.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.georg.qulextestapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by georg on 23.09.2016.
 */
public class GifViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_view)
    ImageView mImageViewGif;

    public void bind(String gifUrl, Context context) {
        if (gifUrl != null) {
            Glide
                    .with(context)
                    .load(gifUrl)
                    .asGif()
                    .into(mImageViewGif);
        }
    }


    public GifViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
