package com.woxapp.go.test.burlaka.winevaultapp.login;

/**
 * Created by Operator on 28.10.2016.
 */
public interface SignInReqPresenter {
    void onStart();
    void onStop ();
    void onDestroy();
    void onStartRequest(String login, String pass, String imei);

    //JsonObject createBody();
    //JsonObject createBody (String login, String pass,);
    //void makeAuthReq (JsonObject jsonReqBody);
}
