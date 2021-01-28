package com.example.prozone.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.prozone.R;
import com.example.prozone.adapter.GridViewAdapter;
import com.example.prozone.model.State;
import com.example.prozone.viewmodel.ProviderViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProviderEdit extends AppCompatActivity {

    private static final String TAG = "ProviderEdit";
    EditText rating, name, description, address, provider_type;
    Spinner statesSpinner, statusSpinner;
    String state, status;
    Button add;
    List<String> states;
    List<Integer> statesId;
    int state_id;
    String state_name;
    ArrayAdapter<CharSequence> statusAdapter;
    ArrayAdapter<String> statesAdapter;
    ProviderViewModel providerViewModel;
    State state_model;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_edit);

        providerViewModel = ViewModelProviders.of(this).get(ProviderViewModel.class);

        states = new ArrayList<>();
        statesId = new ArrayList<>();
        rating = findViewById(R.id.add_rating);
        name = findViewById(R.id.add_name);
        description = findViewById(R.id.add_description);
        address = findViewById(R.id.add_address);
        provider_type = findViewById(R.id.add_type);
        rating = findViewById(R.id.add_rating);
        statesSpinner = findViewById(R.id.add_state);
        statusSpinner = findViewById(R.id.add_status);
        add = findViewById(R.id.add_button);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait");
        progressDialog.show();

        providerViewModel.get_states().observe(this, states1 -> {
            if (states1 != null){
                for (State state: states1){
                    states.add(state.getName().substring(1, state.getName().length() - 1));
                    statesId.add(state.getId());
                }
            }
        });

        statesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, states);
        statesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statesSpinner.setAdapter(statesAdapter);

        statusAdapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);

        statesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state_name = states.get(position);
                state_id = statesId.get(position);

                state_model = new State(state_name, state_id);

                Log.d(TAG, "onItemSelected: " + state_model.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (getIntent().hasExtra("id")){
            providerViewModel.get_provider_by_id(getIntent().getIntExtra("id", 0)).observe(this, provider -> {
                if (provider != null){
                    progressDialog.dismiss();
                    name.setText(provider.getName());
                    address.setText(provider.getAddress());
                    description.setText(provider.getDescription());
                    rating.setText(String.valueOf(provider.getRating()));
                }else {
                    progressDialog.dismiss();
                    finish();
                }
            });

            add.setOnClickListener(v -> {
                if (!name.getText().toString().isEmpty() || !status.isEmpty() || !address.getText().toString().isEmpty()){
                    progressDialog.show();
                    providerViewModel.update_provider(getIntent().getIntExtra("id", 0), name.getText().toString(), description.getText().toString(),
                            Integer.parseInt(rating.getText().toString()), address.getText().toString(), status, provider_type.getText().toString(), state_model).observe(this, provider -> {
                        if (provider != null){
                            Toast.makeText(this, "Success Update " + provider.getId(), Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    });
                }
            });
        } else {
            progressDialog.dismiss();
            add.setOnClickListener(v -> {
                if (!name.getText().toString().isEmpty() || !status.isEmpty() || !address.getText().toString().isEmpty()){
                    progressDialog.show();
                    providerViewModel.add_provider(name.getText().toString(), description.getText().toString(), Integer.parseInt(rating.getText().toString()),
                            address.getText().toString(), status, provider_type.getText().toString(), state_model).observe(this, provider -> {
                        if (provider != null){
                            Toast.makeText(this, "Success" + provider.getId(), Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    });
                }
            });
        }
    }
}