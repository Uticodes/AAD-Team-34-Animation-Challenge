package com.example.tekquotez.services;

import com.example.tekquotez.Quote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("quotes.json")
    Call<List<Quote>> getQuotes();
}
