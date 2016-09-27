package com.georg.qulextestapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.georg.qulextestapp.R;
import com.georg.qulextestapp.view.holder.GifViewHolder;

import java.util.ArrayList;

/**
 * Created by georg on 23.09.2016.
 */
public class GifAdapter extends RecyclerView.Adapter<GifViewHolder> {

    private ArrayList<String> gifItems = new ArrayList<>();
    private final LayoutInflater mInflater;
    private Context context;

    public GifAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setGifItems(ArrayList<String> gifItems) {
        if (gifItems != null) {
            this.gifItems = gifItems;
            notifyDataSetChanged();
        }
    }

    @Override
    public GifViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = mInflater.inflate(R.layout.gif_list_item, parent, false);
        return new GifViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GifViewHolder holder, int position) {
        String itemUrl = gifItems.get(position);
        holder.bind(itemUrl,context);
    }

    @Override
    public int getItemCount() {
        return gifItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
