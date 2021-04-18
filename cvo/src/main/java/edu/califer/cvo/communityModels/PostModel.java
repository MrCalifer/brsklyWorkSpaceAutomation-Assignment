
package edu.califer.cvo.communityModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostModel {

    @SerializedName("data")
    private List<Datum> data;
    @SerializedName("success")
    private Boolean success;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> mData) {
        this.data = mData;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setmSuccess(Boolean mSuccess) {
        this.success = mSuccess;
    }

}
