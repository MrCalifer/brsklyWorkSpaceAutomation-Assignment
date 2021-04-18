package edu.califer.commonui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.califer.commonui.R;
import edu.califer.commonui.databinding.CommunityFeedRecyclerItemBinding;
import edu.califer.cvo.communityModels.Datum;
import edu.califer.cvo.communityModels.PostModel;

public class CommunityFeedAdapter extends RecyclerView.Adapter<CommunityFeedAdapter.FeedViewHolder> {

    private PostModel dataList;
    private Context context;

    private CommunityFeedRecyclerItemBinding binding;

    public CommunityFeedAdapter(Context context, PostModel dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = CommunityFeedRecyclerItemBinding
                .inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new FeedViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        holder.bind(dataList.getData().get(position));
        Log.d("AdapterModel", dataList.getData().get(position).getProfileImage().toString());
    }

    @Override
    public int getItemCount() {
        return dataList.getData().size();
    }

    class FeedViewHolder extends RecyclerView.ViewHolder {
        public FeedViewHolder(@NonNull CommunityFeedRecyclerItemBinding binding) {
            super(binding.getRoot());
        }

        public void bind(@NotNull Datum model) {

            /*Setting User Name.*/
            binding.setUserName(model.getName());

            binding.setUserCompanyName(model.getCompanyName());

            binding.setUserLocation(model.getLocationName());

            binding.setUserFeedPostDuration(getPeriodMonths(model.getPostDate()));

            if (model.getTotalLikes() != null) {
                binding.setFeedLikes(model.getTotalLikes().toString());
            }

            binding.setFeedComments(model.getTotalComments().toString());

            binding.setFeedText(model.getBody());

            Glide.with(binding.userProfilePicture.getContext()).load(model.getProfileImage()).placeholder(R.drawable.default_avtar).into(binding.userProfilePicture);

            Glide.with(binding.feedImage.getContext()).load(model.getImage()).placeholder(R.drawable.demo).into(binding.feedImage);

        }

        public String getPeriodMonths(String date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date d1 = sdf.parse(date);
                Date d2 = sdf.parse(getCurrentData(sdf));

                // Calucalte time difference
                // in milliseconds
                long difference_In_Time
                        = d2.getTime() - d1.getTime();

                // Calucalte time difference in
                // seconds, minutes, hours, years,
                // and days
                long difference_In_Seconds
                        = (difference_In_Time
                        / 1000)
                        % 60;

                long difference_In_Minutes
                        = (difference_In_Time
                        / (1000 * 60))
                        % 60;

                long difference_In_Hours
                        = (difference_In_Time
                        / (1000 * 60 * 60))
                        % 24;

                long difference_In_Years
                        = (difference_In_Time
                        / (1000l * 60 * 60 * 24 * 365));

                long difference_In_Days
                        = (difference_In_Time
                        / (1000 * 60 * 60 * 24))
                        % 365;

                if (!String.valueOf(difference_In_Years).equals("0")) {
                    return (difference_In_Years) + " years";
                } else if (!String.valueOf(difference_In_Days).equals("0")) {
                    return difference_In_Days + " days";
                } else {
                    return difference_In_Hours + " hours";
                }
            }

            // Catch the Exception
            catch (ParseException e) {
                e.printStackTrace();
                return "";
            }
        }

        public String getCurrentData(SimpleDateFormat sdf) {
            SimpleDateFormat formatter = sdf;
            Date date = new Date();
            return formatter.format(date);
        }
    }

}
