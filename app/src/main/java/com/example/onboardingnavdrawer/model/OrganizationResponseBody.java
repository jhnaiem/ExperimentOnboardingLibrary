package com.example.onboardingnavdrawer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrganizationResponseBody {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("vat_registration_number")
    @Expose
    private String vatRegistrationNumber;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("project_name")
    @Expose
    private String projectName;
    @SerializedName("translated_project_name")
    @Expose
    private String translatedProjectName;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getVatRegistrationNumber() {
        return vatRegistrationNumber;
    }
    public void setVatRegistrationNumber(String vatRegistrationNumber) {
        this.vatRegistrationNumber = vatRegistrationNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id == null ? 0 : id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getTranslatedProjectName() {
        return translatedProjectName;
    }
    public void setTranslatedProjectName(String translatedProjectName) {
        this.translatedProjectName = translatedProjectName;
    }

}
