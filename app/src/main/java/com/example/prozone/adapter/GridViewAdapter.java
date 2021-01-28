package com.example.prozone.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.prozone.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    Context mContext;
    List<String> image_urls;

    public GridViewAdapter(Context c, List<String> urls) {
        mContext = c;
        image_urls = urls;
    }

    @Override
    public int getCount() {
        return image_urls.size();
    }

    @Override
    public Object getItem(int position) {
        return image_urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.provider_image, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.model_provider_image);

        Picasso.with(mContext)
                .load(image_urls.get(position).substring(1, image_urls.get(position).length() - 1))
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_baseline_person_24)
                .into(imageView);

        return convertView;
    }
}
