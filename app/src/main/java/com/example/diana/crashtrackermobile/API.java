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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class API extends Application {
    String baseUrl;
    Gson gson;
    RequestQueue requestQueue;

    public API() {
        this.baseUrl = State.getInstance().getBaseUrl();
        this.gson = new Gson();
        this.requestQueue = Volley.newRequestQueue(LoginActivity.getContext());
    }

    public void login(String email, String password, final VolleyCallback callback) {
        Map<String, String> params = new HashMap();
        params.put("email", email);
        params.put("password", password);

        JSONObject bodyParams = new JSONObject(params);

        String url = String.format("%s/auth/signin", State.getInstance().getBaseUrl());
        JsonObjectRequest arrReq = new JsonObjectRequest(Request.Method.POST, url, bodyParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Token token = gson.fromJson(response.toString(), Token.class);
                        callback.onSuccess(token);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String message = new String(error.networkResponse.data,"UTF-8");
                    callback.onError(message);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        );
        requestQueue.add(arrReq);
    }

    public void getUserInfo(String email, final VolleyCallback callback) {
        String url = String.format("%s/users/%s", State.getInstance().getBaseUrl(), email);
        JsonObjectRequest arrReq = new JsonObjectRequest(Request.Method.GET, url, (String)null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        User user = gson.fromJson(response.toString(), User.class);
                        callback.onSuccess(user);
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

    public void startService(final String deviceId, final VolleyCallback callback) {
        String url = String.format("%s/service/%s/start", State.getInstance().getBaseUrl(), deviceId);
        JsonObjectRequest arrReq = new JsonObjectRequest(Request.Method.POST, url, (String)null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("========== Start", response.toString());
                        callback.onSuccess(response);
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

    public void stopService(final String deviceId, final VolleyCallback callback) {
        String url = String.format("%s/service/%s/stop", State.getInstance().getBaseUrl(), deviceId);
        JsonObjectRequest arrReq = new JsonObjectRequest(Request.Method.POST, url, (String)null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("========== Stop", response.toString());
                        callback.onSuccess(response);
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

    public void deleteDevice(final String deviceId, final VolleyCallback callback) {
        String url = String.format("%s/devices/%s", State.getInstance().getBaseUrl(), deviceId);
        JsonObjectRequest arrReq = new JsonObjectRequest(Request.Method.DELETE, url, (String)null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("========== Stop", response.toString());
                        callback.onSuccess(response);
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

    public void deleteSector(final String sectorId, final VolleyCallback callback) {
        String url = String.format("%s/sectors/%s", State.getInstance().getBaseUrl(), sectorId);
        JsonObjectRequest arrReq = new JsonObjectRequest(Request.Method.DELETE, url, (String)null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("========== Stop", response.toString());
                        callback.onSuccess(response);
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
