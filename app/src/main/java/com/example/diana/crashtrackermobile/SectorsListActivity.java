package com.example.diana.crashtrackermobile;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SectorsListActivity extends AppCompatActivity {
    API api = new API();
    LinearLayout sectorsListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sectors_list);

        this.sectorsListLayout = (LinearLayout) findViewById(R.id.sectorsList);

        String deviceId = State.getInstance().getCurDeviceId();

        api.getDeviceSectors(deviceId, new VolleyCallback(){
            @Override
            public void onSuccess(Object sectors){
                for(Sector sector : (ArrayList<Sector>)sectors) {
                    addSectorToList(sector);
                }
            }

            @Override
            public void onError(Object error) {

            }
        });
    }

    private void addSectorToList(final Sector sector){
        LinearLayout sectorContainer = new LinearLayout(this);
        LinearLayout.LayoutParams sectorItemParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        sectorItemParams.setMargins(0,20,0,0);
        sectorContainer.setOrientation(LinearLayout.HORIZONTAL);
        sectorContainer.setBackgroundColor(Color.parseColor("#eaeaea"));
        sectorContainer.setLayoutParams(sectorItemParams);

        LinearLayout buttonsContainer = new LinearLayout(this);
        LinearLayout.LayoutParams buttonsContainerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonsContainerParams.setMargins(20,90,20,20);
        buttonsContainer.setOrientation(LinearLayout.VERTICAL);
        buttonsContainer.setLayoutParams(buttonsContainerParams);

        Button deleteBtn = Utils.createDangerBtn(this, getResources().getString(R.string.btnDeleteDevice));
        Button moreBtn = Utils.createDefaultBtn(this, getResources().getString(R.string.btnMore));

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                api.deleteSector(sector.id, new VolleyCallback(){
                    @Override
                    public void onSuccess(Object response){
                        Intent intent = new Intent(SectorsListActivity.this, DeviceInfoActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(Object error) {

                    }
                });
            }
        });

        buttonsContainer.addView(moreBtn);
        buttonsContainer.addView(deleteBtn);

        LinearLayout sectorTextContainer = new LinearLayout(this);
        LinearLayout.LayoutParams sectorTextParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        sectorTextParams.setMargins(0,20,0,0);
        sectorTextContainer.setPadding(50, 50, 50, 50);
        sectorTextContainer.setOrientation(LinearLayout.VERTICAL);
        sectorTextContainer.setBackgroundColor(Color.parseColor("#eaeaea"));
        sectorTextContainer.setLayoutParams(sectorTextParams);


        TextView name = Utils.getTextView(this, sector.name, false);
        sectorTextContainer.addView(name);

        TextView status = Utils.getTextView(this, String.format("%s %s", getResources().getString(R.string.labelSectorStatus), sector.status.name), true);
        sectorTextContainer.addView(status);

        TextView maxT = Utils.getTextView(this, String.format("%s %.0f °C",getResources().getString(R.string.labelMaxT), sector.maxTemperature), false);
        sectorTextContainer.addView(maxT);

        TextView minT = Utils.getTextView(this, String.format("%s %.0f °C", getResources().getString(R.string.labelMinT), sector.minTemperature), false);
        sectorTextContainer.addView(minT);

        TextView curT = Utils.getTextView(this, String.format("%s %.0f °C", getResources().getString(R.string.labelCurT), sector.status.currentTemp), true);
        sectorTextContainer.addView(curT);

        sectorContainer.addView(sectorTextContainer);
        sectorContainer.addView(buttonsContainer);

        this.sectorsListLayout.addView(sectorContainer);
    }
}
