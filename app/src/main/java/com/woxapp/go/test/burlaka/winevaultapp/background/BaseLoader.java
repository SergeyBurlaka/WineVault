package com.woxapp.go.test.burlaka.winevaultapp.background;

import android.content.AsyncTaskLoader;
import android.content.Context;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Operator on 22.10.2016.
 */
public abstract class BaseLoader extends AsyncTaskLoader<String> {

    private static final String TAG = "BaseLoaderTAG";

    public BaseLoader(Context context) {
        super(context);
    }


    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }


    @Override
    public String loadInBackground() {
        try {
            return apiCall();
        } catch (IOException e) {
            e.printStackTrace();
            //   Log.e(TAG, "Error in loader!"+e.toString());
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            //  Log.e(TAG, "Error in loader!"+e.toString());
            return null;
        }
    }


    protected abstract String apiCall() throws IOException, JSONException;
}

