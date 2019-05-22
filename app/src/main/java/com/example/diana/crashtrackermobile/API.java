package com.example.diana.crashtrackermobile;
import android.app.Application;
import android.support.constraint.Group;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
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

    public void getUserDevices(String userId, final VolleyCallback callback) {
        String url = String.format("%s/users/%s/devices", State.getInstance().getBaseUrl(), userId);

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

    public void getDeviceInfo(final String deviceId, final VolleyCallback callback) {
        String url = String.format("%s/devices/%s", State.getInstance().getBaseUrl(), deviceId);
        JsonObjectRequest arrReq = new JsonObjectRequest(Request.Method.GET, url, (String)null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        DeviceInfo device = gson.fromJson(response.toString(), DeviceInfo.class);
                        callback.onSuccess(device);
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

    public void getDeviceSectors(final String deviceId, final VolleyCallback callback) {
        String url = String.format("%s/devices/%s/sectors", State.getInstance().getBaseUrl(), deviceId);
        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Sector> sectors = new ArrayList<Sector>();
                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObj = response.getJSONObject(i);
                                    Sector sector = gson.fromJson(jsonObj.toString(), Sector.class);
                                    sectors.add(sector);
                                } catch (JSONException e) {
                                    Log.e("Volley", "Invalid JSON Object.");
                                }
                            }
                            callback.onSuccess(sectors);
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