package edu.califer.commonui.viewmodel;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;

import java.io.IOException;

import edu.califer.cvo.communityModels.PostModel;
import edu.califer.cvo.meetingModels.MeetingDataModel;
import edu.califer.repository.CommunityFeedRepository;
import edu.califer.repository.MeetingRepository;

public class HomeViewModel extends ViewModel{

    private final String TAG = "HomeViewModel";

    public MutableLiveData<PostModel> postData;
    private CommunityFeedRepository communityFeedRepository;

    public MutableLiveData<MeetingDataModel> meetingData;
    private MeetingRepository meetingRepository;

    public LiveData<PostModel> getFeedPost(String spaceIds) throws InterruptedException {
        if (postData != null){
            return postData;
        }
        communityFeedRepository = CommunityFeedRepository.getInstance();
        Runnable run = () -> {
            try {
                Log.d(TAG , "Sending Request to CommunityFeed Repository");
                postData = communityFeedRepository.getPostFeedData(spaceIds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(run);
        thread.start();
        thread.join();
        return postData;
    }

    public LiveData<MeetingDataModel> getMeetingSlotDetails(String date,
                                                            String id,
                                                            String open_time,
                                                            String close_time)
            throws InterruptedException {
        if (meetingData != null){
            return meetingData;
        }
        meetingRepository = MeetingRepository.getInstance();
        Runnable task = () ->{
            try {
                Log.d(TAG , "Sending Request to Meeting Repository");
                meetingData = meetingRepository.getMeetingSlotBookingAvailability(
                        date,
                        id,
                        open_time,
                        close_time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        thread.join();
        return meetingData;
    }


    @BindingAdapter("SetImage")
    public void setImage(ImageView view, String url) {
        Glide.with(view).load(url).into(view);
    }
}
