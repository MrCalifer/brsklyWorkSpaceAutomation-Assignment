package edu.califer.commonui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.io.IOException;

import edu.califer.api.RetrofitClientInstance;
import edu.califer.commonui.R;
import edu.califer.commonui.adapter.CommunityFeedAdapter;
import edu.califer.commonui.databinding.FragmentCommunityBinding;
import edu.califer.commonui.viewmodel.HomeViewModel;
import edu.califer.cvo.communityModels.PostModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityFragment extends Fragment {

    public CommunityFragment() {
        // Required empty public constructor
    }

    private PostModel model;

    private FragmentCommunityBinding binding;
    private HomeViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community, container, false);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            RetrofitClientInstance.authAPI.getFeedPost("1")
                    .enqueue(new Callback<PostModel>() {
                        @Override
                        public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                            if (response.isSuccessful()) {
                                model = response.body();
                                Log.d("Model" , model.toString());
                                binding.progressBar.setVisibility(View.GONE);
                                binding.communityFeed.setLayoutManager(new LinearLayoutManager(getContext()));
                                binding.communityFeed.setAdapter(new CommunityFeedAdapter(getContext(), model));
                            }
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