package edu.califer.commonui.viewmodel;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;

import java.io.IOException;

import edu.califer.cvo.communityModels.PostModel;
import edu.califer.repository.CommunityFeedRepository;

public class HomeViewModel extends ViewModel {

    public MutableLiveData<PostModel> postData = null;

    public void getFeedPost(String spaceIds) throws IOException {
        CommunityFeedRepository repository = new CommunityFeedRepository();
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    repository.getFeedPost(spaceIds, postData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }


    @BindingAdapter("SetImage")
    public void setImage(ImageView view, String url) {
        Glide.with(view).load(url).into(view);
    }

}
