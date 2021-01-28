package com.example.prozone.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.prozone.model.Provider;
import com.example.prozone.model.State;
import com.example.prozone.repository.ProviderRepository;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.util.List;

public class ProviderViewModel extends AndroidViewModel {

    ProviderRepository providerRepository;

    public ProviderViewModel(@NonNull Application application) {
        super(application);
        providerRepository = new ProviderRepository(application);
    }

    public LiveData<Provider> add_provider(String name, String description, int rating, String address, String active_status, String provider_type, State state){
        Provider p = new Provider();
        p.setProvider_type(provider_type);
        p.setName(name);
        p.setDescription(description);
        p.setRating(rating);
        p.setAddress(address);
        p.setState(state);
        p.setActive_status(active_status);

        return providerRepository.add_provider(new JsonParser().parse(new Gson().toJson(p)).getAsJsonObject());
    }

    public LiveData<Provider> update_provider(int id, String name, String description, int rating, String address, String active_status, String provider_type, State state){
        Provider p = new Provider();
        p.setProvider_type(provider_type);
        p.setName(name);
        p.setDescription(description);
        p.setRating(rating);
        p.setAddress(address);
        p.setState(state);
        p.setActive_status(active_status);

        return providerRepository.update_provider(id, new JsonParser().parse(new Gson().toJson(p)).getAsJsonObject());
    }

    public LiveData<Provider> get_provider_by_id(int id){
        return providerRepository.get_provider_by_id(id);
    }

    public LiveData<List<Provider>> get_providers(){
        return providerRepository.get_providers();
    }

    public LiveData<List<State>> get_states(){
        return providerRepository.get_states();
    }

}
