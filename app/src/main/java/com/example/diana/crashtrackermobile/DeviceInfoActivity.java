package com.example.diana.crashtrackermobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class DeviceInfoActivity extends AppCompatActivity {
    API api = new API();
    DeviceInfo deviceInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);

        String deviceId = State.getInstance().getCurDeviceId();

        api.getDeviceInfo(deviceId, new VolleyCallback(){
            @Override
            public void onSuccess(Object deviceInfo){
                DeviceInfoActivity.this.deviceInfo = (DeviceInfo) deviceInfo;
                DeviceInfoActivity.this.setDeviceInfo();
            }

            @Override
            public void onError(Object error) {

            }
        });
    }

    public void setDeviceInfo() {
        TextView deviceNameField = findViewById(R.id.deviceNameValue);
        deviceNameField.setText(this.deviceInfo.name);

        TextView deviceModelField = findViewById(R.id.deviceModelValue);
        deviceModelField.setText(this.deviceInfo.model);

        TextView deviceTypeField = findViewById(R.id.deviceTypeValue);
        deviceTypeField.setText(this.deviceInfo.type);

        TextView deviceIntervalField = findViewById(R.id.deviceServiceIntervalValue);
        deviceIntervalField.setText(String.format("%d hours", this.deviceInfo.serviceInterval));

        TextView deviceNotifyField = findViewById(R.id.deviceNotifyValue);
        deviceNotifyField.setText(String.format("%.0f %%", this.deviceInfo.notifyBeforeService));

        TextView deviceDescriptionField = findViewById(R.id.deviceDescriptionValue);
        deviceDescriptionField.setText(this.deviceInfo.description);
    }

    public void goToSectorsListPage(View view) {
        Intent intent = new Intent(this, SectorsListActivity.class);
        startActivity(intent);
    }

    public void deleteDevice(View view) {
        String deviceId = State.getInstance().getCurDeviceId();

        api.deleteDevice(deviceId, new VolleyCallback(){
            @Override
            public void onSuccess(Object deviceInfo){
                Intent intent = new Intent(DeviceInfoActivity.this, DevicesListActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(Object error) {

            }
        });
    }
}
