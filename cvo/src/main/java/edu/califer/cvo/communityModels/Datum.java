package edu.califer.cvo.communityModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum {

    @SerializedName("alternate_id")
    private String mAlternateId;
    @SerializedName("body")
    private String mBody;
    @SerializedName("company_name")
    private String mCompanyName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("images")
    private List<String> mImages;
    @SerializedName("is_approved")
    private Long mIsApproved;
    @SerializedName("is_authorized")
    private Long mIsAuthorized;
    @SerializedName("is_liked")
    private Long mIsLiked;
    @SerializedName("is_suspended")
    private Long mIsSuspended;
    @SerializedName("location_name")
    private String mLocationName;
    @SerializedName("name")
    private String mName;
    @SerializedName("post_date")
    private String mPostDate;
    @SerializedName("posted_at")
    private String mPostedAt;
    @SerializedName("profile_image")
    private String mProfileImage;
    @SerializedName("total_comments")
    private Long mTotalComments;
    @SerializedName("total_likes")
    private Long mTotalLikes;

    public String getAlternateId() {
        return mAlternateId;
    }

    public void setAlternateId(String alternateId) {
        mAlternateId = alternateId;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public List<String> getImages() {
        return mImages;
    }

    public void setImages(List<String> images) {
        mImages = images;
    }

    public Long getIsApproved() {
        return mIsApproved;
    }

    public void setIsApproved(Long isApproved) {
        mIsApproved = isApproved;
    }

    public Long getIsAuthorized() {
        return mIsAuthorized;
    }

    public void setIsAuthorized(Long isAuthorized) {
        mIsAuthorized = isAuthorized;
    }

    public Long getIsLiked() {
        return mIsLiked;
    }

    public void setIsLiked(Long isLiked) {
        mIsLiked = isLiked;
    }

    public Long getIsSuspended() {
        return mIsSuspended;
    }

    public void setIsSuspended(Long isSuspended) {
        mIsSuspended = isSuspended;
    }

    public String getLocationName() {
        return mLocationName;
    }

    public void setLocationName(String locationName) {
        mLocationName = locationName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPostDate() {
        return mPostDate;
    }

    public void setPostDate(String postDate) {
        mPostDate = postDate;
    }

    public String getPostedAt() {
        return mPostedAt;
    }

    public void setPostedAt(String postedAt) {
        mPostedAt = postedAt;
    }

    public String getProfileImage() {
        return mProfileImage;
    }

    public void setProfileImage(String profileImage) {
        mProfileImage = profileImage;
    }

    public Long getTotalComments() {
        return mTotalComments;
    }

    public void setTotalComments(Long totalComments) {
        mTotalComments = totalComments;
    }

    public Long getTotalLikes() {
        return mTotalLikes;
    }

    public void setTotalLikes(Long totalLikes) {
        mTotalLikes = totalLikes;
    }
}
