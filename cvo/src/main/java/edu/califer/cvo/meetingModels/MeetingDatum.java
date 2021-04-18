package edu.califer.cvo.meetingModels;

import com.google.gson.annotations.SerializedName;

public class MeetingDatum {

    @SerializedName("start_time")
    private Float mStartTime;
    @SerializedName("display_name")
    private String mDisplayTimeSlot;
    @SerializedName("is_booked")
    private Boolean mIsBooked;

    public Float getStartTime() {
        return mStartTime;
    }

    public String getTimeSlot() {
        return mDisplayTimeSlot;
    }

    public Boolean getIsBooked() {
        return mIsBooked;
    }
}
