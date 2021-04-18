package edu.califer.api;

import org.junit.Assert;
import org.junit.Test;

import retrofit2.http.GET;

public class APITesting {

    private RetrofitClient retrofitClient = new RetrofitClient();

    @Test
    public void getFeedPost() {
        Assert.assertNotNull(retrofitClient.authAPI.getFeedPost("1"));
    }
}
