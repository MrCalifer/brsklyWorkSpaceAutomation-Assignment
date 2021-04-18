package edu.califer.repository;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import java.io.IOException;
import edu.califer.api.RetrofitClientInstance;
import edu.califer.cvo.communityModels.PostModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityFeedRepository {

    private final String TAG = "CommunityFeedRepository";

    public void getFeedPost(String spaceIds, MutableLiveData<PostModel> postData) throws IOException {
            try {
                RetrofitClientInstance.authAPI.getFeedPost(spaceIds)
                        .enqueue(new Callback<PostModel>() {
                    @Override
                    public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                        if (response.isSuccessful()) {
                            postData.setValue(response.body());
                        }
                        Log.d("Response" , "Code :"+response.code());
                    }
                    @Override
                    public void onFailure(Call<PostModel> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
