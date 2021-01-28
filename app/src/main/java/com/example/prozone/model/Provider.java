package com.example.prozone.model;

import java.util.List;

public class Provider {
    String name, description, address, active_status, provider_type;
    State state;
    List<String> provider_image_url;
    int rating, id;

    public Provider() {
    }

    public Provider(String name, String description, String address, int id, String active_status, String provider_type, State state, List<String> provider_image_url, int rating) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.id = id;
        this.active_status = active_status;
        this.provider_type = provider_type;
        this.state = state;
        this.provider_image_url = provider_image_url;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActive_status() {
        return active_status;
    }

    public void setActive_status(String active_status) {
        this.active_status = active_status;
    }

    public String getProvider_type() {
        return provider_type;
    }

    public void setProvider_type(String provider_type) {
        this.provider_type = provider_type;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<String> getProvider_image_url() {
        return provider_image_url;
    }

    public void setProvider_image_url(List<String> provider_image_url) {
        this.provider_image_url = provider_image_url;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
