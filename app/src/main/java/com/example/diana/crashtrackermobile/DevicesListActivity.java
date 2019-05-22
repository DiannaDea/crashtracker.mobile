package com.example.diana.crashtrackermobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DevicesListActivity extends AppCompatActivity {
    API api = new API();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices_list);
        api.getUserDevices(new VolleyCallback(){
            @Override
            public void onSuccess(Object devices){
                System.out.println("jjsjsjs");
            }
        });
    }
}
