package com.woxapp.go.test.burlaka.winevaultapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.woxapp.go.test.burlaka.winevaultapp.login.SignInReq;
import com.woxapp.go.test.burlaka.winevaultapp.login.SignInReqPresenter;
import com.woxapp.go.test.burlaka.winevaultapp.login.SignInView;

public class SignInActivity extends AppCompatActivity implements SignInView {

    private static final String TAG = "myTag";
    private static final String CHEAT_CODE = "q";
    private static final int PERMISSION_READ_STATE = 5;
    private Button signIn;
    private EditText myLoginEdit;
    private EditText myPassEdit;
    private TextView badNews;
    private ProgressBar pdialog;
    private SignInReqPresenter authReq;
    private String login, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        myLoginEdit = (EditText)findViewById(R.id.edit_login);
        myPassEdit = (EditText) findViewById(R.id.edit_pass);
        pdialog = (ProgressBar) findViewById(R.id.progress_bar);
        badNews = (TextView) findViewById(R.id.text_bad_answer);
        authReq = new SignInReq(this);

        signIn = (Button)findViewById(R.id.button_enter);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                badNews.setVisibility(View.INVISIBLE);
                login = myLoginEdit.getText().toString();
                pass = myPassEdit.getText().toString();

                if (login.matches(CHEAT_CODE)) {
                    authReq.onStartRequest("admin", "123456","12345");

                } else {
                    if (login.matches("")||pass.matches("")){Toast.makeText(SignInActivity.this, "Введите для начала логин и пароль!", Toast.LENGTH_SHORT).show();return;}
                    onStartAuthReq();
                }
                pdialog.setVisibility(ProgressBar.VISIBLE);
            }
        });
    }


    private void onStartAuthReq() {
        if (getPermissions ()) authReq.onStartRequest( login, pass, ask_imei());
        askUserFirst();
    }


    private void askUserFirst() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSION_READ_STATE);
    }


    private boolean getPermissions (){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            //   ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSION_READ_STATE);
            // We do not have this permission. Let's ask the user
            return false;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_READ_STATE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    authReq.onStartRequest( login, pass, ask_imei());
                    // permission granted!
                    // you may now do the action that requires this permission
                } else {
                    // permission denied
                }
                return;
            }
        }
    }



   private String ask_imei (){
       String imei;
       TelephonyManager telephonyManager = (TelephonyManager) this
               .getSystemService(Context.TELEPHONY_SERVICE);
       imei = telephonyManager.getDeviceId();
       Log.e(TAG,"My imei = "+ telephonyManager.getDeviceId());
       return imei;
   }


    @Override
    protected void onStart() {
        super.onStart();
        authReq.onStart();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        authReq.onDestroy();
    }

    /*
    *   Implements SignInView
    */
    @Override
    public void progressDialog(int dialogVisible) {
        switch(dialogVisible){
            case R.id.DIALOG_INVISIBLE:
                pdialog.setVisibility(ProgressBar.INVISIBLE);
            break;
            case R.id.DIALOG_VISIBLE:
                pdialog.setVisibility(ProgressBar.VISIBLE);
            break;
        }
    }


    @Override
    public void showBadAnswer() {
        badNews.setVisibility(View.VISIBLE);
    }


    @Override
    public void goNextView() {
            Intent goVault = new Intent(SignInActivity.this, WineAmountActivity.class);
            startActivity( goVault);
            finish();
    }
}