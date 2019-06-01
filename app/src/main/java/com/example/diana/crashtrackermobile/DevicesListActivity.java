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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_devices_list);
        this.devicesListLayout = (LinearLayout) findViewById(R.id.devicesList);

        api.getUserDevices(State.getInstance().getUserId(), new VolleyCallback(){
            @Override
            public void onSuccess(Object devices){
                for(Device device : (ArrayList<Device>)devices) {
                    addVotingToList(device);
                }
            }

            @Override
            public void onError(Object error) {

            }
        });
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

        Button moreBtn = Utils.createDefaultBtn(this, getResources().getString(R.string.btnMore));
        Button serviceBtn;

        if (device.status.code != 5) {
            serviceBtn = Utils.createPrimaryBtn(this, getResources().getString(R.string.btnStartService));
        } else {
            serviceBtn = Utils.createDangerBtn(this, getResources().getString(R.string.btnStopService));
        }

        serviceBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (device.status.code != 5) {
                    api.startService(device.id, new VolleyCallback(){
                        @Override
                        public void onSuccess(Object response){
                            DevicesListActivity.this.clearDevicesListContent();
                            api.getUserDevices(State.getInstance().getUserId(), new VolleyCallback(){
                                @Override
                                public void onSuccess(Object devices){
                                    for(Device device : (ArrayList<Device>)devices) {
                                        addVotingToList(device);
                                    }
                                }

                                @Override
                                public void onError(Object error) {

                                }
                            });
                        }

                        @Override
                        public void onError(Object error) {

                        }
                    });
                } else {
                    api.stopService(device.id, new VolleyCallback(){
                        @Override
                        public void onSuccess(Object response){
                            DevicesListActivity.this.clearDevicesListContent();
                            api.getUserDevices(State.getInstance().getUserId(), new VolleyCallback(){
                                @Override
                                public void onSuccess(Object devices){
                                    for(Device device : (ArrayList<Device>)devices) {
                                        addVotingToList(device);
                                    }
                                }

                                @Override
                                public void onError(Object error) {

                                }
                            });
                        }

                        @Override
                        public void onError(Object error) {

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

        TextView name = Utils.getTextView(this, device.name, false);
        deviceTextContainer.addView(name);

        TextView status = Utils.getTextView(this, device.status.name, true);
        deviceTextContainer.addView(status);

        String nextServiceDate = String.format("%s: %s", getResources().getString(R.string.labelNextService), new SimpleDateFormat("dd.MM.yyyy").format(device.dateLastService));
        TextView nextService = Utils.getTextView(this, nextServiceDate, false);
        deviceTextContainer.addView(nextService);

        deviceContainer.addView(deviceTextContainer);
        deviceContainer.addView(buttonsContainer);

        this.devicesListLayout.addView(deviceContainer);
    }
}
