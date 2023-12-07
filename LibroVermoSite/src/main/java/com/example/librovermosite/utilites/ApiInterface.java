package com.example.librovermosite.utilites;

import com.example.librovermosite.models.plugs.Language;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface ApiInterface {
    @POST("/getFromDB")
    Call<List<Object>> getAny(@Body SendObject object);
    @POST("/addToDB")
    Call<DAOResponse> addAny(@Body SendObject object);
    @GET("/logIn")
    Call<String> logIn(@Query("login") String login, @Query("password") String password);
}
