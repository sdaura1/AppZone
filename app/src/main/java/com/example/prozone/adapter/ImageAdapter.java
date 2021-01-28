package com.example.prozone.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prozone.R;
import com.example.prozone.activity.ProviderDetails;
import com.example.prozone.model.Provider;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context context;
    private List<String> providers;

    public ImageAdapter(Context context, List<String> providers) {
        this.context = context;
        this.providers = providers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.provider_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(context)
                .load(providers.get(position))
                .into(holder.model_provider_image);
//        holder.model_provider_image.setText(providers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (providers.isEmpty()) {
            return 0;
        }else {
            return providers.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final View v;
        ImageView model_provider_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;

            model_provider_image = v.findViewById(R.id.model_provider_image);
        }
    }
}
