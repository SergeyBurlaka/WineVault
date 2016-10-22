package com.woxapp.go.test.burlaka.winevaultapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonObject;
import com.woxapp.go.test.burlaka.winevaultapp.retrofit.ApiFactory;
import com.woxapp.go.test.burlaka.winevaultapp.retrofit.SignInService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity implements Callback<ResponseBody> {

    private static final String TAG = "SignITest";
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        signIn = (Button)findViewById(R.id.button_enter);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createBody();

                SignInService service = ApiFactory.getSignInService();

                Call<ResponseBody> call = service.signIn(createBody());
                call.enqueue(SignInActivity.this);

                Intent goVault = new Intent(SignInActivity.this, WineVaultActivity.class);
               startActivity( goVault);
                finish();

            }
        });
    }

    public JsonObject createBody(){

        JsonObject bodyReg = new JsonObject();
        bodyReg.addProperty("login","admin");
        bodyReg.addProperty("password","123456");
        bodyReg.addProperty("imei","12345");
        return bodyReg;

    /*json/*
        {"login":"admin","password":"123456","imei":"12345"
    /*json*/

    }



    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


        if (response.isSuccessful()) {
                Log.i(TAG, "Response: "+response.body().toString());

        }else
        {

            Log.i(TAG, "NULL BODY -> " + response.errorBody());
        }

    }



    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

        Log.e(TAG,"Failed");
        Log.e(TAG," "+t.getMessage());

    }
}
