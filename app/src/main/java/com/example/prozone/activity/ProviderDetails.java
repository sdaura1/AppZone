package com.example.prozone.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prozone.R;
import com.example.prozone.adapter.GridViewAdapter;
import com.example.prozone.adapter.ImageAdapter;
import com.example.prozone.viewmodel.ProviderViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProviderDetails extends AppCompatActivity {

    private static final String TAG = "ProviderDetails";
    TextView name, address, description, rating, state, status, type;
    ProviderViewModel providerViewModel;
    GridView gridView;
    FloatingActionButton fab;
    int id;
    ProgressDialog progressDialog;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_details);

        providerViewModel = ViewModelProviders.of(this).get(ProviderViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait");
        progressDialog.show();

        name = findViewById(R.id.detail_name);
        address = findViewById(R.id.detail_address);
        description = findViewById(R.id.detail_description);
        rating = findViewById(R.id.detail_rating);
        state = findViewById(R.id.detail_state);
        status = findViewById(R.id.detail_status);
        type = findViewById(R.id.detail_type);

        fab = findViewById(R.id.add_provider_fab);
        gridView = findViewById(R.id.provider_images);

        providerViewModel.get_provider_by_id(getIntent().getIntExtra("id", 0)).observe(this, provider -> {
            if (provider != null){
                progressDialog.dismiss();
                name.setText("Name: " + provider.getName());
                address.setText("Address: " + provider.getAddress());
                description.setText("Description: " + provider.getDescription());
                rating.setText("Rating: " + provider.getRating());
                state.setText("State: " + provider.getState());
                status.setText("Status: " + provider.getActive_status());
                type.setText("Type: " + provider.getProvider_type());

                gridView.setAdapter(new GridViewAdapter(this, provider.getProvider_image_url()));
            }else {
                progressDialog.dismiss();
                finish();
            }
        });

        fab.setOnClickListener(v -> startActivity(new Intent(this, ProviderEdit.class)
                .putExtra("id", getIntent().getIntExtra("id", 0))));
    }
}