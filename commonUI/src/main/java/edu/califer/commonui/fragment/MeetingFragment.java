package edu.califer.commonui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import edu.califer.api.RetrofitClientInstance;
import edu.califer.commonui.R;
import edu.califer.commonui.adapter.MeetingSlotAdapter;
import edu.califer.commonui.databinding.FragmentMeetingBinding;
import edu.califer.cvo.meetingModels.MeetingDataModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeetingFragment extends Fragment {

    private FragmentMeetingBinding binding;

    private MeetingDataModel model;

    public MeetingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_meeting, container, false);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.setIntervalDuration("0.5");

        try {
            RetrofitClientInstance.authAPI.getMeetingDetails("2019-05-24", "1", "09:00", "23:00")
                    .enqueue(new Callback<MeetingDataModel>() {
                        @Override
                        public void onResponse(Call<MeetingDataModel> call, Response<MeetingDataModel> response) {
                            if (response.isSuccessful()) {
                                model = response.body();
                                binding.meetingProgressBar.setVisibility(View.GONE);
                                binding.meetingSlotTime.setLayoutManager(new GridLayoutManager(getContext() , 3));
                                binding.meetingSlotTime.setAdapter(new MeetingSlotAdapter(model));
                            }
                        }
                        @Override
                        public void onFailure(Call<MeetingDataModel> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}