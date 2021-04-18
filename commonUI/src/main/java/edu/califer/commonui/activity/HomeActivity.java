package edu.califer.commonui.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;

import edu.califer.api.RetrofitClientInstance;
import edu.califer.commonui.R;
import edu.califer.commonui.databinding.ActivityHome2Binding;
import edu.califer.commonui.viewmodel.HomeViewModel;
import edu.califer.cvo.communityModels.PostModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private ActivityHome2Binding binding;

    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this , R.layout.activity_home2);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        getWindow().setStatusBarColor(Color.BLACK);

        binding.bottomNavigation.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) item -> {
            if (item.getItemId() == R.id.community_flow) {
                navController.navigate(R.id.action_community_flow);
            }else if (item.getItemId() == R.id.meeting_flow){
                navController.navigate(R.id.action_meeting_flow);
            }
            return false;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}