package edu.califer.repository;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import edu.califer.api.RetrofitClientInstance;
import edu.califer.cvo.meetingModels.MeetingDataModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MeetingRepository {

    private final String TAG = "MeetingRepository";

    private static MeetingRepository instance;

    public static MeetingRepository getInstance() {
        if (instance == null) {
            instance = new MeetingRepository();
        }
        return instance;
    }

    public MutableLiveData<MeetingDataModel> getMeetingSlotBookingAvailability(String date,
                                                                               String id,
                                                                               String open_time,
                                                                               String close_time)
            throws InterruptedException {
        MutableLiveData<MeetingDataModel> data = new MutableLiveData<>();
        Runnable task = () -> {
            Log.d(TAG, "Initiating API call...");
            RetrofitClientInstance.authAPI.getMeetingDetails(date, id, open_time, close_time)
                    .enqueue(new Callback<MeetingDataModel>() {
                        @EverythingIsNonNull
                        @Override
                        public void onResponse(Call<MeetingDataModel> call, Response<MeetingDataModel> response) {
                            if (response.isSuccessful()) {
                                data.setValue(response.body());
                            }
                            Log.d(TAG, "Response Code : " + response.code());
                        }

                        @Override
                        public void onFailure(Call<MeetingDataModel> call, Throwable t) {
                            Log.d(TAG, "Initiating API call failed due to " + t.getMessage());
                            t.printStackTrace();
                        }
                    });
        };
        Thread thread = new Thread(task);
        thread.start();
        thread.join();
        return data;
    }
}
