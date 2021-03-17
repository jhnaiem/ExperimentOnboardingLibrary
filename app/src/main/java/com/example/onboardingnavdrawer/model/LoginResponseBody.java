package com.example.onboardingnavdrawer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponseBody {
    @SerializedName("auth_info")
    @Expose
    private AuthenticationInfoBody auth_info;
    @SerializedName("organization_info")
    @Expose
    private OrganizationResponseBody organization_info;
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("app_top_module_assignment")
    @Expose
    private List<AppModuleAssignment> app_top_module_assignment;
    @SerializedName("organization_logo")
    @Expose
    private String organization_logo;
    @SerializedName("organization_name")
    @Expose
    private String organization_name;


    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }


    public String getOrganization_logo() {
        return organization_logo;
    }
    public void setOrganization_logo(String organization_logo) {
        this.organization_logo = organization_logo;
    }
    /**
     * @return The auth_info
     */
    public AuthenticationInfoBody getAuth_info() {
        return auth_info;
    }
    /**
     * @param auth_info The auth_info
     */
    public void setAuth_info(AuthenticationInfoBody auth_info) {
        this.auth_info = auth_info;
    }
    /**
     * @return The success
     */
    public boolean isSuccess() {
        return success;
    }
    /**
     * @param success The success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
    /**
     * @return The token
     */
    public String getToken() {
        return token;
    }
    /**
     * @param token The token
     */
    public void setToken(String token) {
        this.token = token;
    }
    public List<AppModuleAssignment> getApp_top_module_assignment() {

        return app_top_module_assignment;
    }
    public void setApp_top_module_assignment(List<AppModuleAssignment> app_top_module_assignment) {
        this.app_top_module_assignment = app_top_module_assignment;
    }
    public OrganizationResponseBody getOrganization_info() {
        return organization_info;
    }
    public void setOrganization_info(OrganizationResponseBody organization_info) {
        this.organization_info = organization_info;
    }
}
