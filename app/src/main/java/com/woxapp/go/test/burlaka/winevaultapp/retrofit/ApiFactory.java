package com.woxapp.go.test.burlaka.winevaultapp.retrofit;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;
        import okhttp3.OkHttpClient;
        import okhttp3.logging.HttpLoggingInterceptor;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 60;
    private static final int TIMEOUT = 60;
    private static OkHttpClient sClient;
    // private static final String TAG = "GetChannel";

    @NonNull
    public static SignInService getSignInService() {
        return buildRetrofit().create(SignInService.class);
    }





    @NonNull
    private static Retrofit buildRetrofit() {
        // Log.i(TAG, "onBuildRetrofitApiFactory");
        return new Retrofit.Builder()
                .baseUrl("http://wine-cellar.biznestext.com/")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @NonNull
    private static OkHttpClient getClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (ApiFactory.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }


    @NonNull
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor())
                .build();
    }
}