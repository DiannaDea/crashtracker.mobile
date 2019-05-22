package com.example.diana.crashtrackermobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DevicesListActivity extends AppCompatActivity {
    API api = new API();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices_list);
        api.getUserDevices("c2ccc569-caa2-45ac-8d7f-3c60dde45172", new VolleyCallback(){
            @Override
            public void onSuccess(Object devices){
                System.out.println("jjsjsjs");
            }
        });
    }
}
