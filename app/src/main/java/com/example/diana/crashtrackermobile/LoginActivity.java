package com.example.diana.crashtrackermobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
        final API api = new API();

        final String email = ((TextView)findViewById(R.id.emailInput)).getText().toString();
        String password = ((TextView)findViewById(R.id.passwordInput)).getText().toString();

        api.login(email, password, new VolleyCallback(){
            @Override
            public void onSuccess(Object token){
                api.getUserInfo(email, new VolleyCallback(){
                    @Override
                    public void onSuccess(Object userObj){
                        User user = (User) userObj;
                        State.getInstance().setUserId(user.id);
                        Intent intent = new Intent(LoginActivity.this, DevicesListActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(Object error) {

                    }
                });
            }

            @Override
            public void onError(Object error) {
                TextView loginError = findViewById(R.id.loginError);
                loginError.setText((String)error);
            }
        });
    }
}
