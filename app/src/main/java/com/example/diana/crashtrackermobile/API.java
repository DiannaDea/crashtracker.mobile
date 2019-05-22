package com.example.diana.crashtrackermobile;
import android.app.Application;
import android.support.constraint.Group;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;


public class API extends Application {
    String baseUrl;
    Gson gson;
    RequestQueue requestQueue;

    public API() {
        this.baseUrl = State.getInstance().getBaseUrl();
        this.gson = new Gson();
        this.requestQueue = Volley.newRequestQueue(LoginActivity.getContext());
    }

    public void getUserDevices(final VolleyCallback callback) {
        String url = "https://crash-tracker-server.herokuapp.com/api/users/c2ccc569-caa2-45ac-8d7f-3c60dde45172/devices";

        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    List<Device> devices = new ArrayList<Device>();
                    if (response.length() > 0) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObj = response.getJSONObject(i);
                                Device device = gson.fromJson(jsonObj.toString(), Device.class);
                                devices.add(device);
                            } catch (JSONException e) {
                                Log.e("Volley", "Invalid JSON Object.");
                            }
                        }
                        callback.onSuccess(devices);
                    } else {
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // If there a HTTP error then add a note to our repo list.
                    //setRepoListText("Error while calling REST API");
                       Log.e("==========Volley", error.toString());
                }
            }
        );
        requestQueue.add(arrReq);
    }
}
