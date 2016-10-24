package com.woxapp.go.test.burlaka.winevaultapp.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Operator on 22.10.2016.
 */
public interface GDBService /*get dash board loader*/  {

    @GET("/api/v1/dashboard")
        Call<ResponseBody> getDAshBoard(@Query("imei") String imei,
                                        @Query("access_token") String access_token,
                                        @Query("cellar_id") String cellar_id);
    /*http/*
    http://wine-cellar.biznestext.com/api/v1/dashboard ?
        imei=54321&
            access_token=e827ccb0eea8a706c4c34a16891f84e7b&
                cellar_id=1
    /*http*/
}
