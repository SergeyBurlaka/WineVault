package com.woxapp.go.test.burlaka.winevaultapp.data.singletone;

/**
 * Created by Operator on 22.10.2016.
 */
public class InternetUser {
   
    private static InternetUser ourInstance = new InternetUser();
    private String imei;
    private String access_token;
    private String cellar_id;
    private String jsonAccess;

    public static InternetUser getInstance() {
        return ourInstance;
    }

    private InternetUser() {
    }

    public String getCellar_id() {
        return cellar_id;
    }

    public void setCellar_id(String cellar_id) {
        this.cellar_id = cellar_id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getJsonAccess() {
        return jsonAccess;
    }

    public void setJsonAccess(String jsonAccess) {
        this.jsonAccess = jsonAccess;
    }
}
