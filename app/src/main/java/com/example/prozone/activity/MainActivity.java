package com.example.prozone.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.prozone.R;
import com.example.prozone.adapter.ProviderAdapter;
import com.example.prozone.model.Provider;
import com.example.prozone.viewmodel.ProviderViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Multipart;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ProviderAdapter providerAdapter;
    FloatingActionButton fab;
    ProviderViewModel providerViewModel;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        providerViewModel = ViewModelProviders.of(this).get(ProviderViewModel.class);
        recyclerView = findViewById(R.id.providers_recycler);
        fab = findViewById(R.id.add_provider_fab);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait");
        progressDialog.show();

        providerViewModel.get_providers().observe(this, providers -> {
            if (providers != null){
                progressDialog.dismiss();
                providerAdapter = new ProviderAdapter(this, providers);
                layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(providerAdapter);
            }else {
                progressDialog.dismiss();
            }
        });

        fab.setOnClickListener(v -> startActivity(new Intent(this, ProviderEdit.class)));

    }
}