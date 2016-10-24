package com.woxapp.go.test.burlaka.winevaultapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.woxapp.go.test.burlaka.winevaultapp.data.singletone.InternetUser;
import com.woxapp.go.test.burlaka.winevaultapp.retrofit.ApiFactory;
import com.woxapp.go.test.burlaka.winevaultapp.retrofit.SignInService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity implements Callback<ResponseBody> {

    private static final String TAG = "myTag";
    private static final String CHEAT_CODE = "q";
    
    private Button signIn;
    private String imei;

    private EditText myLoginEdit;
    private EditText myPassEdit;
    private TextView badNews;
    private ProgressBar pdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myLoginEdit = (EditText)findViewById(R.id.edit_login);
        myPassEdit = (EditText) findViewById(R.id.edit_pass);
        pdialog = (ProgressBar) findViewById(R.id.progress_bar);
        badNews = (TextView) findViewById(R.id.text_bad_answer);
        /*
        * SignInService service = ApiFactory.getSignInService();

                Call<ResponseBody> call = service.signIn(createBody());
                call.enqueue(SignInActivity.this);
        * */
        signIn = (Button)findViewById(R.id.button_enter);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JsonObject jsonReqBody;
                String login, pass;

                badNews.setVisibility(View.INVISIBLE);
                login = myLoginEdit.getText().toString();
                pass = myPassEdit.getText().toString();

                if (login.matches(CHEAT_CODE)) {
                    jsonReqBody = createBody();
                } else {
                    if (login.matches("")||pass.matches(""))
                    {
                     Toast.makeText(SignInActivity.this, "Введите для начала логин и пароль!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                     jsonReqBody = createBody(login, pass);
                }
                
                pdialog.setVisibility(ProgressBar.VISIBLE);

                SignInService service = ApiFactory.getSignInService();

                Call<ResponseBody> call = service.signIn(jsonReqBody);
                call.enqueue(SignInActivity.this);
            }
        });
    }


    public JsonObject createBody(){
        imei = "12345";
        JsonObject bodyReg = new JsonObject();
        bodyReg.addProperty("login","admin");
        bodyReg.addProperty("password","123456");
        bodyReg.addProperty("imei","12345");
        return bodyReg;

    /*json/*
        {"login":"admin","password":"123456","imei":"12345"
    /*json*/
    }


    public JsonObject createBody(String login, String pass) {

        JsonObject bodyReg = new JsonObject();
        bodyReg.addProperty("login", login);
        bodyReg.addProperty("password", pass);
        bodyReg.addProperty("imei", getImei());
        return bodyReg;

    /*json/*
        {"login":"admin","password":"123456","imei":"12345"
    /*json*/
    }


    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        pdialog.setVisibility(ProgressBar.INVISIBLE);

        if (response.isSuccessful()) {
            try {

                Log.i(TAG, "Response is successful! "+response.body().toString());
               
                //save utility cash in singleton
                InternetUser iu = InternetUser.getInstance();
                iu.setImei(imei);
                iu.setJsonAccess(response.body().string());
                goNext();

            } catch (IOException e) {
                e.printStackTrace();
            }

            call.toString();

        }else
        {
            badNews.setVisibility(View.VISIBLE);
            Log.i(TAG, "NULL BODY -> " + response.errorBody());
        }
    }


    private void goNext() {
        Intent goVault = new Intent(SignInActivity.this, WineAmountActivity.class);
        startActivity( goVault);
        finish();
    }


    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        badNews.setVisibility(View.VISIBLE);
        Log.e(TAG,"Failed");
        Log.e(TAG," "+t.getMessage());
    }

    
    public  String getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) this
                .getSystemService(Context.TELEPHONY_SERVICE);
        imei = telephonyManager.getDeviceId();
        Log.e(TAG,"My imei = "+ telephonyManager.getDeviceId());
        return imei;
    }
}
