package edu.califer.api;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofitClient;

    private static final String baseUrl = "https://devapis.brskly.co/";

    public static Retrofit getRetrofitAuthInstance() {
        if (retrofitClient == null) {
            retrofitClient = new Retrofit.Builder()
                    .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpBuilder.addInterceptor(authenticationInterceptor).build())
                    .build();
        }
        return retrofitClient;
    }

    public static Retrofit getRetrofitInstance() {
        if (retrofitClient == null) {
            retrofitClient = new Retrofit.Builder()
                    .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitClient;
    }


    private static final Interceptor authenticationInterceptor = chain -> {
        Request request = chain.request();
        String authenticationToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtZW1iZXJfaWQiOjEsInNwYWNlSWQiOjEsImlhdCI6MTYxNjQwOTA5MX0.c3u-NySd6ZpUWw1hEuewpZaqGRoJ_HifQL9fxuiASQE";
        request = request.newBuilder().header("Authorization", "Bearer " +authenticationToken).build();
        return chain.proceed(request);
    };

    private static final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS);

    public static final AuthAPIService authAPI = RetrofitClientInstance.getRetrofitAuthInstance().create(AuthAPIService.class);

    public static final AuthAPIService api = RetrofitClientInstance.getRetrofitInstance().create(AuthAPIService.class);
}
