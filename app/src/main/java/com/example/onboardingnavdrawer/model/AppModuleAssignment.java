package com.example.onboardingnavdrawer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class AppModuleAssignment extends RealmObject {


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_LABEL = "label";
    public static final String COLUMN_TRANSLATED_LABEL = "label_translated";
    public static final String COLUMN_MODULE = "module";
    public static final String COLUMN_ACCESS = "access";
    public static final String COLUMN_ACCESS_KEY = "access_key";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_PARENT = "parent";
    public static final String COLUMN_MODULE_CATEGORY = "module_category";
    public static final String COLUMN_MODULE_ID = "module_id";
    public static final String COLUMN_LANDING_MODEL = "landing_model";
    public static final String COLUMN_LANDING_MODEL_LEVEL = "landing_model_level";
    public static final String COLUMN_SAAS_MODULE_ACCESS_KEY = "saas_module_key";
    public static final String COLUMN_LANDING_MODEL_FINAL_LEVEL = "landing_model_final_level";
    public static final int APP_MODULE_ASSIGNMENT_ACCESS_PRESENT = 1;
    @SerializedName(COLUMN_ID)
    @Expose
    private Long id;
    @SerializedName(COLUMN_MODULE_ID)
    @Expose
    private Long module_id;
    @SerializedName(COLUMN_LABEL)
    @Expose
    private String label;
    @SerializedName(COLUMN_TRANSLATED_LABEL)
    @Expose
    private String label_translated;
    @SerializedName(COLUMN_MODULE)
    @Expose
    private String module;
    @SerializedName(COLUMN_ACCESS)
    @Expose
    private Long access;
    @SerializedName(COLUMN_WEIGHT)
    @Expose
    private Long weight;
    @SerializedName(COLUMN_PARENT)
    @Expose
    private Long parent;
    @SerializedName(COLUMN_ACCESS_KEY)
    @Expose
    private String access_key;
    @SerializedName(COLUMN_MODULE_CATEGORY)
    @Expose
    private String module_category;
    @SerializedName(COLUMN_LANDING_MODEL)
    @Expose
    private String landing_model;
    @SerializedName(COLUMN_LANDING_MODEL_LEVEL)
    @Expose
    private String landing_model_level;
    @SerializedName(COLUMN_LANDING_MODEL_FINAL_LEVEL)
    @Expose
    private String landing_model_final_level;
    @SerializedName(COLUMN_SAAS_MODULE_ACCESS_KEY)
    @Expose
    private String saas_module_key;

    private Long user_id;

    public Long getId() {
        if (id == null) return 0L;
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModule_id() {
        return module_id == null ? 0 : module_id;
    }

    public void setModule_id(Long module_id) {
        this.module_id = module_id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel_translated() {
        return label_translated;
    }

    public void setLabel_translated(String label_translated) {
        this.label_translated = label_translated;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Long getAccess() {
        return access;
    }

    public void setAccess(Long access) {
        this.access = access;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getAccess_key() {
        return access_key;
    }

    public void setAccess_key(String access_key) {
        this.access_key = access_key;
    }

    public String getModule_category() {
        return module_category;
    }

    public void setModule_category(String module_category) {
        this.module_category = module_category;
    }

    public Long getUser_id() {
        return user_id == null ? 0 : user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getLanding_model() {
        return landing_model;
    }

    public void setLanding_model(String landing_model) {
        this.landing_model = landing_model;
    }

    public String getLanding_model_level() {
        return landing_model_level;
    }

    public void setLanding_model_level(String landing_model_level) {
        this.landing_model_level = landing_model_level;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

//    public String getFinalAccessKey() {
//        return Validator.isValid(this.landing_model_level) ?
//                this.landing_model_level : this.access_key;
//    }

    public String getSaas_module_key() {
        return saas_module_key;
    }

    public void setSaas_module_key(String saas_module_key) {
        this.saas_module_key = saas_module_key;
    }

    public String getLanding_model_final_level() {
        return landing_model_final_level;
    }

    public void setLanding_model_final_level(String landing_model_final_level) {
        this.landing_model_final_level = landing_model_final_level;
    }
}
