package edu.califer.commonui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.jetbrains.annotations.NotNull;

import edu.califer.api.RetrofitClientInstance;
import edu.califer.commonui.R;
import edu.califer.commonui.adapter.CommunityFeedAdapter;
import edu.califer.commonui.databinding.FragmentCommunityBinding;
import edu.califer.commonui.viewmodel.HomeViewModel;
import edu.califer.cvo.communityModels.PostModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class CommunityFragment extends Fragment {

    public CommunityFragment() {
        // Required empty public constructor
    }

    private final String TAG = "CommunityFragment";

    private PostModel model;

    private FragmentCommunityBinding binding;
    private HomeViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
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
            viewModel.getFeedPost("1").observe(getViewLifecycleOwner(), postModel -> {
                Log.d(TAG , "Model : "+postModel.getData());
                binding.progressBar.setVisibility(View.GONE);
                binding.communityFeed.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.communityFeed.setAdapter(new CommunityFeedAdapter(getContext(), postModel));
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}