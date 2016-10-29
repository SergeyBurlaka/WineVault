package com.woxapp.go.test.burlaka.winevaultapp.background;

import android.content.Context;
import android.content.SharedPreferences;

import com.woxapp.go.test.burlaka.winevaultapp.App;
import com.woxapp.go.test.burlaka.winevaultapp.apicall.ApiFactory;
import com.woxapp.go.test.burlaka.winevaultapp.apicall.GDBService;
import com.woxapp.go.test.burlaka.winevaultapp.data.ParseDashBoard;
import com.woxapp.go.test.burlaka.winevaultapp.data.singletone.InternetUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Operator on 22.10.2016.
 */


public class GDBLoader /*get dash board loader*/ extends BaseLoader{

    public static final String ACCESS_PREFERENCES = "AccessPrefs";
    public static final String ACCESS_TOKEN = "AccessToken";
    public static final String CELLAR_ID = "Cellar_id";
    public static final String IMEI = "imei";
    private static final String FAIL = "fail";
    private static final String SUCCESS = "success";
    private InternetUser iu;
    private ParseDashBoard parsingDashBoard;
    private SharedPreferences.Editor accessSave;


    public GDBLoader(Context context) {
        super(context);
    }


    @Override
    protected String apiCall() throws IOException, JSONException {
         iu = InternetUser.getInstance();
        if(iu.getJsonAccess() == null) return FAIL;
        onParsing ();
       // Log.i(TAG, "Get Dash Board Body = " + onApiRequest().string());
        parsingDashBoard = new ParseDashBoard( new JSONObject(onApiRequest().string()));
        parsingDashBoard.onParsingJSON();
        //Log.i(TAG, "On Finish gdb loader.");
        return  SUCCESS;
    }


    private ResponseBody onApiRequest() throws IOException {
        //get dash board service
        GDBService gdbService = ApiFactory.getDBService();
        Call<ResponseBody> call = gdbService.getDAshBoard( iu.getImei(),iu.getAccess_token(),iu.getCellar_id());

        //Saved access in sh. preference, so it is no reason to keep access in singleton
        //Free up resources from single tone.
        iu.setJsonAccess(null);
        iu.setAccess_token(null);
        iu.setImei(null);
        return call.execute().body();
    }


    private void onParsing() {
        String json = iu.getJsonAccess();

        try {
            JSONObject obj = new JSONObject(json);
            iu.setAccess_token(obj.getString("access_token"));
            iu.setCellar_id(obj.getString("cellar_id"));
            saveAccess ();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void saveAccess() {
        accessSave = App.getContext().getSharedPreferences(ACCESS_PREFERENCES, App.getContext().MODE_APPEND).edit();
        accessSave.putString(ACCESS_TOKEN, iu.getAccess_token());
        accessSave.putString(CELLAR_ID, iu.getCellar_id());
        accessSave.putString(IMEI, iu.getImei());
        accessSave.commit();
    }
}
