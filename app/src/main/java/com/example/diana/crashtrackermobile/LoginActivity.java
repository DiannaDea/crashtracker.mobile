package com.example.diana.crashtrackermobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {
    private static LoginActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
    }

    public static LoginActivity getContext() {
        return mContext;
    }

    public void goToDevicesListPage(View view) {
        Intent intent = new Intent(this, DevicesListActivity.class);
        startActivity(intent);
    }
}
