package com.woxapp.go.test.burlaka.winevaultapp.auth;

import android.util.Log;

import com.google.gson.JsonObject;
import com.woxapp.go.test.burlaka.winevaultapp.R;
import com.woxapp.go.test.burlaka.winevaultapp.data.singletone.InternetUser;
import com.woxapp.go.test.burlaka.winevaultapp.apicall.ApiFactory;
import com.woxapp.go.test.burlaka.winevaultapp.apicall.SignInService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Operator on 28.10.2016.
 */
public class SignInReq implements Callback<ResponseBody>, SignInReqPresenter {

    private static final String TAG = "myTag";
    private String imei;
    private SignInView signInView;

    public SignInReq(SignInView signInView){
        this.signInView = signInView;
    }


    public void onStartRequest (String login, String pass, String imei){
       this.imei = imei;
       makeAuthReq (createBody ( login, pass, imei));
    }


    private void makeAuthReq(JsonObject jsonReqBody) {
        SignInService service = ApiFactory.getSignInService();

        Call<ResponseBody> call = service.signIn(jsonReqBody);
        call.enqueue(this);
    }


    private JsonObject createBody (String login, String pass, String imei) {

      //  String imei = signInView.get_imei();

        JsonObject bodyReg = new JsonObject();
        bodyReg.addProperty("login", login);
        bodyReg.addProperty("password", pass);
        bodyReg.addProperty("imei", imei);
        return bodyReg;

    /*json/*
        {"login":"admin","password":"123456","imei":"12345"
    /*json*/

    }


    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

        signInView.progressDialog (R.id.DIALOG_INVISIBLE);


        if (response.isSuccessful()) {
            try {

                Log.i(TAG, "Response is successful! "+response.body().toString());
                //cheers!
                //save utility cash in singleton
                InternetUser iu = InternetUser.getInstance();
                //iu.setImei("12345");
                iu.setImei(imei);
                iu.setJsonAccess(response.body().string());
                signInView.goNextView();

            } catch (IOException e) {
                e.printStackTrace();
            }

            call.toString();

        }else
        {
            signInView.showBadAnswer();

            Log.i(TAG, "NULL BODY -> " + response.errorBody());
        }
    }


    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        signInView.showBadAnswer();

        Log.e(TAG,"Failed");
        Log.e(TAG," "+t.getMessage());
    }


    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }


    @Override
    public void onDestroy() {
        signInView = null;
    }
}
