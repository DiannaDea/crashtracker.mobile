package com.example.diana.crashtrackermobile;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DevicesListActivity extends AppCompatActivity {
    API api = new API();
    LinearLayout devicesListLayout;
    private static DevicesListActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_devices_list);
        this.devicesListLayout = (LinearLayout) findViewById(R.id.devicesList);

        api.getUserDevices("2b14b4a5-bf74-43d5-be95-792cd9738ee2", new VolleyCallback(){
            @Override
            public void onSuccess(Object devices){
                for(Device device : (ArrayList<Device>)devices) {
                    addVotingToList(device);
                }
            }
        });
        mContext = this;
    }

    public static DevicesListActivity getContext() {
        return mContext;
    }

    public void clearDevicesListContent() {
        this.devicesListLayout.removeAllViews();
    }

    private void addVotingToList(final Device device){
        LinearLayout deviceContainer = new LinearLayout(this);
        LinearLayout.LayoutParams deviceItemParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        deviceItemParams.setMargins(0,20,0,0);
        deviceContainer.setOrientation(LinearLayout.HORIZONTAL);
        deviceContainer.setBackgroundColor(Color.parseColor("#eaeaea"));
        deviceContainer.setLayoutParams(deviceItemParams);

        LinearLayout buttonsContainer = new LinearLayout(this);
        LinearLayout.LayoutParams buttonsContainerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonsContainerParams.setMargins(0,20,20,20);
        buttonsContainer.setOrientation(LinearLayout.VERTICAL);
        buttonsContainer.setLayoutParams(buttonsContainerParams);

        Button moreBtn = Utils.createDefaultBtn(this, "More");
        Button serviceBtn;

        if (device.status.code != 5) {
            serviceBtn = Utils.createPrimaryBtn(this, "Start service");
        } else {
            serviceBtn = Utils.createDangerBtn(this, "Stop service");
        }

        serviceBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (device.status.code != 5) {
                    api.startService(device.id, new VolleyCallback(){
                        @Override
                        public void onSuccess(Object response){
                            DevicesListActivity.this.clearDevicesListContent();
                            api.getUserDevices("2b14b4a5-bf74-43d5-be95-792cd9738ee2", new VolleyCallback(){
                                @Override
                                public void onSuccess(Object devices){
                                    for(Device device : (ArrayList<Device>)devices) {
                                        addVotingToList(device);
                                    }
                                }
                            });
                        }
                    });
                } else {
                    api.stopService(device.id, new VolleyCallback(){
                        @Override
                        public void onSuccess(Object response){
                            DevicesListActivity.this.clearDevicesListContent();
                            api.getUserDevices("2b14b4a5-bf74-43d5-be95-792cd9738ee2", new VolleyCallback(){
                                @Override
                                public void onSuccess(Object devices){
                                    for(Device device : (ArrayList<Device>)devices) {
                                        addVotingToList(device);
                                    }
                                }
                            });
                        }
                    });
                }
            }
        });

        moreBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                State.getInstance().setCurDeviceId(device.id);

                Intent intent = new Intent(DevicesListActivity.this, DeviceInfoActivity.class);
                startActivity(intent);
            }
        });


        buttonsContainer.addView(serviceBtn);
        buttonsContainer.addView(moreBtn);

        LinearLayout deviceTextContainer = new LinearLayout(this);
        LinearLayout.LayoutParams deviceTextParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        deviceItemParams.setMargins(0,20,0,0);
        deviceTextContainer.setPadding(50, 50, 50, 50);
        deviceTextContainer.setOrientation(LinearLayout.VERTICAL);
        deviceTextContainer.setBackgroundColor(Color.parseColor("#eaeaea"));
        deviceTextContainer.setLayoutParams(deviceTextParams);

        TextView name = new TextView(this);
        name.setText(device.name);
        deviceTextContainer.addView(name);

        TextView status = new TextView(this);
        status.setText(device.status.name);
        deviceTextContainer.addView(status);

        TextView nextService = new TextView(this);
        nextService.setText("Next service: ".concat(new SimpleDateFormat("dd.MM.yyyy").format(device.dateLastService)));
        deviceTextContainer.addView(nextService);

        deviceContainer.addView(deviceTextContainer);
        deviceContainer.addView(buttonsContainer);

        this.devicesListLayout.addView(deviceContainer);


    }

}
