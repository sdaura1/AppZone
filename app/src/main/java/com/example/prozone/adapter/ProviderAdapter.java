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

import com.bumptech.glide.Glide;
import com.example.prozone.R;
import com.example.prozone.activity.ProviderDetails;
import com.example.prozone.model.Provider;

import java.util.List;

public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.ViewHolder> {

    private Context context;
    private List<Provider> providers;

    public ProviderAdapter(Context context, List<Provider> providers) {
        this.context = context;
        this.providers = providers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.provider_item, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.model_provider_name.setText(providers.get(position).getName());
        holder.model_provider_rating.setText(String.valueOf(providers.get(position).getRating()));
        holder.model_doctor_description.setText(providers.get(position).getDescription());
        holder.model_provider_status.setText(providers.get(position).getActive_status());
        holder.model_provider_address.setText(providers.get(position).getAddress());
//        holder.model_provider_state.setText(providers.get(position).getState().getName());
        holder.v.setOnClickListener(view -> context.startActivity(new Intent(context, ProviderDetails.class)
                .putExtra("id", providers.get(position).getId())));
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
        TextView model_provider_name, model_provider_state, model_provider_address, model_doctor_description, model_provider_rating, model_provider_status;
        ImageView model_provider_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;

            model_provider_name = v.findViewById(R.id.model_provider_name);
            model_provider_state = v.findViewById(R.id.model_provider_state);
            model_doctor_description = v.findViewById(R.id.model_provider_description);
            model_provider_rating = v.findViewById(R.id.model_provider_rating);
            model_provider_address = v.findViewById(R.id.model_provider_address);
            model_provider_status = v.findViewById(R.id.model_provider_status);
            model_provider_image = v.findViewById(R.id.model_provider_image);
        }
    }
}
