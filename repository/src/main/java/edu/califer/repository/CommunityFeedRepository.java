package edu.califer.repository;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import java.io.IOException;

import edu.califer.api.RetrofitClient;
import edu.califer.api.RetrofitClientInstance;
import edu.califer.cvo.communityModels.PostModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class CommunityFeedRepository {

    private final String TAG = "CommunityFeedRepository";

    private static CommunityFeedRepository instance;

    public static CommunityFeedRepository getInstance(){
        if (instance == null){
            instance = new CommunityFeedRepository();
        }
        return instance;
    }


    public MutableLiveData<PostModel> getPostFeedData(String spaceIds) throws InterruptedException {
        MutableLiveData<PostModel> data = new MutableLiveData<>();
        Runnable r = () -> {
            try{
                Log.d(TAG , "Initiating API call...");
                RetrofitClientInstance.authAPI.getFeedPost(spaceIds)
                        .enqueue(new Callback<PostModel>() {
                            @EverythingIsNonNull
                            @Override
                            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                                if (response.isSuccessful()){
                                    data.setValue(response.body());
                                }
                                Log.d(TAG , "Response Code : "+response.code());
                            }
                            @EverythingIsNonNull
                            @Override
                            public void onFailure(Call<PostModel> call, Throwable t) {
                                Log.d(TAG , "Initiating API call...");
                                t.printStackTrace();
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(r);
        thread.start();
        thread.join();
        return data;
    }
}
