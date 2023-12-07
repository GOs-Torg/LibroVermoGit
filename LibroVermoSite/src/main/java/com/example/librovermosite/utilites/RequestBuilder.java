package com.example.librovermosite.utilites;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RequestBuilder {

    private static String URL = "http://localhost:9009";
    private static Retrofit retrofit = null;

    public static Retrofit buildRequest(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
