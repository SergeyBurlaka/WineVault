package com.woxapp.go.test.burlaka.winevaultapp.apicall;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Operator on 21.10.2016.
 */
public interface SignInService {

    @POST("api/v1/auth")
    Call<ResponseBody> signIn( @Body JsonObject bean );
}
