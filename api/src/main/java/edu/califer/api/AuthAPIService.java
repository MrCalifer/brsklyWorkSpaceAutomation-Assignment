package edu.califer.api;

import edu.califer.cvo.communityModels.PostModel;
import edu.califer.cvo.meetingModels.MeetingDataModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface AuthAPIService {

    @GET("/api/v1/app/news_feed/post/all")
    Call<PostModel> getFeedPost(@Query("spaceIds") String spaceIds);

    @GET("/api/v1/app/meetingRoom/bookings")
    Call<MeetingDataModel> getMeetingDetails(@Query("date") String data,
                                             @Query("resource_id") String resource_id,
                                             @Query("open_time") String open_time,
                                             @Query("close_time") String close_time);
}
