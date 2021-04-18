package edu.califer.api;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private final String baseURL = "https://devapis.brskly.co/api/v1/app/";

    private final Interceptor authenticationInterceptor = chain -> {
        Request request = chain.request();
        String authenticationToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtZW1iZXJfaWQiOjEsInNwYWNlSWQiOjEsImlhdCI6MTYxNjQwOTA5MX0.c3u-NySd6ZpUWw1hEuewpZaqGRoJ_HifQL9fxuiASQE";
        request = request.newBuilder().header("Authorization", "Bearer" + authenticationToken).build();
        return chain.proceed(request);
    };

    final Retrofit.Builder retrofit = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create());

    final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(2, TimeUnit.SECONDS);

    public final AuthAPIService authAPI = retrofit
            .client(okHttpBuilder.addInterceptor(authenticationInterceptor).build())
            .build()
            .create(AuthAPIService.class);
}
