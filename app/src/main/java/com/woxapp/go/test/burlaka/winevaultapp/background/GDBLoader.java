package com.woxapp.go.test.burlaka.winevaultapp.background;

import android.content.Context;
import android.database.Cursor;

import com.woxapp.go.test.burlaka.winevaultapp.data.ParseDashBoard;
import com.woxapp.go.test.burlaka.winevaultapp.data.singletone.InternetUser;
import com.woxapp.go.test.burlaka.winevaultapp.apicall.ApiFactory;
import com.woxapp.go.test.burlaka.winevaultapp.apicall.GDBService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Operator on 22.10.2016.
 */


public class GDBLoader /*get dash board loader*/ extends BaseLoader{


    private static final String TAG = "myTag";
    private InternetUser iu;
    private ParseDashBoard parsingDashBoard;


    public GDBLoader(Context context) {
        super(context);
    }

    @Override
    protected Cursor apiCall() throws IOException, JSONException {
         iu = InternetUser.getInstance();
        onParsing ();

       // Log.i(TAG, "Get Dash Board Body = " + onApiRequest().string());

        parsingDashBoard = new ParseDashBoard( new JSONObject(onApiRequest().string()));

        parsingDashBoard.onParsingJSON();

        //Log.i(TAG, "On Finish gdb loader.");

        return  null ;
    }


    private ResponseBody onApiRequest() throws IOException {
        //get dash board service
        GDBService gdbService = ApiFactory.getDBService();
        Call<ResponseBody> call = gdbService.getDAshBoard( iu.getImei(),iu.getAccess_token(),iu.getCellar_id());
        return call.execute().body();
    }


    private void onParsing() {


      // Log.i(TAG, "Get From Singleton  = " + iu.getJsonAccess());
        String json = iu.getJsonAccess();

        //freeing up resources from single tone
        //iu.setJsonAccess(null);

        try {

            JSONObject obj = new JSONObject(json);
            iu.setAccess_token(obj.getString("access_token"));
            iu.setCellar_id(obj.getString("cellar_id"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
