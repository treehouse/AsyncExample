package com.teamtreehouse.asyncexample;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Bitmap[] bitmaps = {};

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.update(bitmaps[position]);
    }

    @Override
    public int getItemCount() {
        return bitmaps.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

        void update(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }
}
