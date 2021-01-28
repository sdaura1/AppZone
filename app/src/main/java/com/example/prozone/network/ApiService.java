package com.example.prozone.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("providers")
    Call<JsonArray> get_providers(@Header("Authorization") String token);

    @POST("providers")
    Call<JsonObject> add_provider(@Header("Authorization") String token,
                                  @Body JsonObject jsonObject);

    @PUT("providers/{id}")
    Call<JsonObject> update_provider(@Header("Authorization") String token,
                                     @Path("id") int id,
                                     @Body JsonObject jsonObject);

    @GET("providers/{id}")
    Call<JsonObject> get_provider_by_id(@Header("Authorization") String token,
                                        @Path("id") int id);

    @GET("provider-types")
    Call<JsonObject> get_provider_type(@Header("Authorization") String token);

    @GET("states")
    Call<JsonArray> get_states(@Header("Authorization") String token);

}
