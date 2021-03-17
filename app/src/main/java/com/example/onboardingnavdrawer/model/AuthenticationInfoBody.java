package com.example.onboardingnavdrawer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthenticationInfoBody {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("user_id")
    @Expose
    private long userId;
    @SerializedName("user_photo")
    @Expose
    private String userPhoto;
    @SerializedName("username")
    @Expose
    private String userName;
    @SerializedName("role_id")
    @Expose
    private long roleId;
    @SerializedName("role_name")
    @Expose
    private String roleName;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("assigned_to")
    @Expose
    private long assignedTo;
    /**
     * @return The userType
     */
    public String getUserType() {
        return userType;
    }
    /**
     * @param userType The user_type
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
    /**
     * @return The roleId
     */
    public long getRoleId() {
        return roleId;
    }
    /**
     * @param roleId The role_id
     */
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserPhoto() {
        return userPhoto;
    }
    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * @return The roleName
     */
    public String getRoleName() {
        return roleName;
    }
    /**
     * @param roleName The role_name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    /**
     * @return The userId
     */
    public long getUserId() {
        return userId;
    }
    /**
     * @param userId The user_id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public long getAssignedTo() {
        return assignedTo;
    }
    public void setAssignedTo(long assignedTo) {
        this.assignedTo = assignedTo;
    }

}
