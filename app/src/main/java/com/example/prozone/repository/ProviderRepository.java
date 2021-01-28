package com.example.prozone.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.prozone.model.Provider;
import com.example.prozone.model.State;
import com.example.prozone.network.APIClient;
import com.example.prozone.network.ApiService;
import com.example.prozone.network.Is_Network;
import com.example.prozone.network.SharedPreferenceManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProviderRepository {

    Application application;
    SharedPreferenceManager sharedPreferenceManager;
    private ApiService apiService;
    String token;

    public ProviderRepository(Application application){
        this.application = application;
        sharedPreferenceManager = SharedPreferenceManager.getInstance(application);
        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjExNTYzNjEzLCJleHAiOjE2MTQxNTU2MTN9.e5Df8V75KU44Hz4IG1ilKby0ptkJzX7hFcbX5XZ-EEI";
    }

    public LiveData<List<Provider>> get_providers() {
        MutableLiveData<List<Provider>> mutableLiveData = new MutableLiveData<>();
        List<Provider> providerList = new ArrayList<>();
        List<String> providerImageList = new ArrayList<>();
        if (new Is_Network().isOnline(application)) {
            apiService = APIClient.getCacheEnabledRetrofit(application).create(ApiService.class);
            final Call<JsonArray> health_tips_call = apiService.get_providers("Bearer " + token);
            health_tips_call.enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    if (response.isSuccessful()) {
                        providerList.clear();
                        for (int i = 0; i < response.body().size(); i++) {
                            Provider provider = new Provider();
                            providerImageList.clear();
                            provider.setId(Integer.parseInt(response.body().get(i).getAsJsonObject()
                                    .get("id").toString()));
                            provider.setName(response.body().get(i).getAsJsonObject()
                                    .get("name").toString());
                            provider.setDescription(response.body().get(i).getAsJsonObject()
                                    .get("description").toString());
//                            provider.setProvider_type(response.body().get(i).getAsJsonObject()
//                                    .get("provider_type").getAsJsonObject().get("name").toString());
//                                provider.setRating(Integer.parseInt(response.body().get(i).getAsJsonObject()
//                                        .get("rating").toString()));
                            provider.setAddress(response.body().get(i).getAsJsonObject()
                                    .get("address").toString());
                            provider.setActive_status(response.body().get(i).getAsJsonObject()
                                    .get("active_status").toString());
//                            State state = new State(response.body().get(i).getAsJsonObject().get("state").getAsJsonObject().get("name").toString(),
//                                    Integer.parseInt(response.body().get(i).getAsJsonObject().get("state").getAsJsonObject().get("id").toString()));
//                            provider.setState(state);
                            JsonArray jsonArray = response.body().get(i).getAsJsonObject()
                                    .get("images").getAsJsonArray();
                            if (jsonArray.size() != 0){
                                for (int j = 0; j < jsonArray.size(); j++) {
                                    providerImageList.add(jsonArray.get(j).getAsJsonObject().get("url").toString());
                                }
                                provider.setProvider_image_url(providerImageList);
                            }
                            providerList.add(provider);
                        }
                        mutableLiveData.setValue(providerList);
                    }
                }

                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) {
                }
            });
        }
        return mutableLiveData;
    }

    public LiveData<List<State>> get_states() {
        MutableLiveData<List<State>> mutableLiveData = new MutableLiveData<>();
        List<State> providerList = new ArrayList<>();
        if (new Is_Network().isOnline(application)) {
            apiService = APIClient.getCacheEnabledRetrofit(application).create(ApiService.class);
            final Call<JsonArray> health_tips_call = apiService.get_states("Bearer " + token);
            health_tips_call.enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    if (response.isSuccessful()) {
                        providerList.clear();
                        for (int i = 0; i < response.body().size(); i++) {
                            State state = new State();
                            state.setId(Integer.parseInt(response.body().get(i).getAsJsonObject().get("id").toString()));
                            state.setName(response.body().get(i).getAsJsonObject().get("name").toString());

                            providerList.add(state);
                        }
                        mutableLiveData.setValue(providerList);
                    }
                }

                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) {
                }
            });
        }
        return mutableLiveData;
    }

    public LiveData<Provider> get_provider_by_id(int id) {
        MutableLiveData<Provider> mutableLiveData = new MutableLiveData<>();
        Provider provider = new Provider();
        List<String> providerImageList = new ArrayList<>();
        if (new Is_Network().isOnline(application)) {
            apiService = APIClient.getCacheEnabledRetrofit(application).create(ApiService.class);
            final Call<JsonObject> health_tips_call = apiService.get_provider_by_id("Bearer " + token, id);
            health_tips_call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        provider.setId(Integer.parseInt(response.body().get("id").toString()));
                        provider.setName(response.body().get("name").toString());
                        provider.setDescription(response.body().get("description").toString());
                        provider.setProvider_type(response.body().get("provider_type").getAsJsonObject().get("name").toString());
                        provider.setRating(Integer.parseInt(response.body().get("rating").toString()));
                        provider.setAddress(response.body().get("address").toString());
                        provider.setActive_status(response.body().get("active_status").toString());
                        State state = new State(response.body().get("state").getAsJsonObject().get("name").toString(),
                                Integer.parseInt(response.body().get("state").getAsJsonObject().get("id").toString()));
                        provider.setState(state);
                        JsonArray jsonArray = response.body().get("images").getAsJsonArray();
                        if (jsonArray.size() != 0){
                            for (int j = 0; j < jsonArray.size(); j++) {
                                providerImageList.add(jsonArray.get(j).getAsJsonObject().get("url").toString());
                            }
                            provider.setProvider_image_url(providerImageList);
                        }

                        mutableLiveData.setValue(provider);
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                }
            });
        }
        return mutableLiveData;
    }

    public LiveData<Provider> add_provider(JsonObject jsonObject) {
        Provider provider = new Provider();
        MutableLiveData<Provider> mutableLiveData = new MutableLiveData<>();
        if (new Is_Network().isOnline(application)) {
            apiService = APIClient.getCacheEnabledRetrofit(application).create(ApiService.class);
            final Call<JsonObject> health_tips_call = apiService.add_provider("Bearer " + token, jsonObject);
            health_tips_call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        provider.setId(Integer.parseInt(response.body().get("id").toString()));
                        provider.setName(response.body().get("name").toString());
                        provider.setDescription(response.body().get("description").toString());
                        provider.setProvider_type(response.body().get("provider_type").getAsJsonObject().get("name").toString());
                        provider.setRating(Integer.parseInt(response.body().get("rating").toString()));
                        provider.setAddress(response.body().get("address").toString());
                        provider.setActive_status(response.body().get("active_status").toString());
                        State state = new State(response.body().get("state").getAsJsonObject().get("name").toString(),
                                Integer.parseInt(response.body().get("state").getAsJsonObject().get("id").toString()));
                        provider.setState(state);

                        mutableLiveData.setValue(provider);
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                }
            });
        }
        return mutableLiveData;
    }

    public LiveData<Provider> update_provider(int id, JsonObject jsonObject) {
        Provider provider = new Provider();
        MutableLiveData<Provider> mutableLiveData = new MutableLiveData<>();
        List<String> providerImageList = new ArrayList<>();
        if (new Is_Network().isOnline(application)) {
            apiService = APIClient.getCacheEnabledRetrofit(application).create(ApiService.class);
            final Call<JsonObject> health_tips_call = apiService.update_provider("Bearer " + token, id, jsonObject);
            health_tips_call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        provider.setId(Integer.parseInt(response.body().get("id").toString()));
                        provider.setName(response.body().get("name").toString());
                        provider.setDescription(response.body().get("description").toString());
                        provider.setProvider_type(response.body().get("provider_type").getAsJsonObject().get("name").toString());
                        provider.setRating(Integer.parseInt(response.body().get("rating").toString()));
                        provider.setAddress(response.body().get("address").toString());
                        provider.setActive_status(response.body().get("active_status").toString());
                        State state = new State(response.body().get("state").getAsJsonObject().get("name").toString(),
                                Integer.parseInt(response.body().get("state").getAsJsonObject().get("id").toString()));
                        provider.setState(state);

                        JsonArray jsonArray = response.body().get("images").getAsJsonArray();
                        if (jsonArray.size() != 0){
                            for (int j = 0; j < jsonArray.size(); j++) {
                                providerImageList.add(jsonArray.get(j).getAsJsonObject().get("url").toString());
                            }
                            provider.setProvider_image_url(providerImageList);
                        }
                        mutableLiveData.setValue(provider);
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                }
            });
        }
        return mutableLiveData;
    }
}
