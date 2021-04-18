
package edu.califer.cvo.meetingModels;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import edu.califer.cvo.communityModels.Datum;

public class MeetingDataModel {

    @SerializedName("data")
    private List<MeetingDatum> mData;
    @SerializedName("success")
    private Boolean mSuccess;

    public List<MeetingDatum> getData() {
        return mData;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

}
