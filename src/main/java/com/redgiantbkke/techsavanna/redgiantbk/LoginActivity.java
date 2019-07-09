package com.redgiantbkke.techsavanna.redgiantbk;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.redgiantbkke.techsavanna.redgiantbk.helper.ApiInterfaceStart;
import com.redgiantbkke.techsavanna.redgiantbk.helper.ApiStart;
import com.redgiantbkke.techsavanna.redgiantbk.methods.Start;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ApiInterfaceStart apiInterface;
    private int SPLASH_TIME = 1000;
    ProgressBar progressBar;

    private static final String TAG = "LoginActivity";
    private static final int PERMISSION_CALLBACK_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    String[] permissionsRequired = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.READ_PHONE_STATE

    };
    private SharedPreferences permissionStatus;
    private boolean sentToSettings = false;

    TelephonyManager mngr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.prograss);

                    String deviceId = Settings.Secure.getString(LoginActivity.this.getContentResolver(),
                            Settings.Secure.ANDROID_ID);

                   // System.out.println("Print"+deviceId);
                    fetchDeviceid(deviceId);


    }

    private void fetchDeviceid(String deviceid) {

        apiInterface = ApiStart.getApiClient().create(ApiInterfaceStart.class);
        Call<List<Start>> call = apiInterface.getDeviceid(deviceid);
      // System.out.println("Print"+deviceid);
        call.enqueue(new Callback<List<Start>>() {
            @Override
            public void onResponse(Call<List<Start>> call, Response<List<Start>> response) {


                System.out.println("Print"+response);
                progressBar.setVisibility(View.GONE);
                progressBar.setBackgroundColor(Color.BLUE);
if (response.toString()=="1"){
    Intent intent =new Intent(LoginActivity.this,LoginOneActivity.class);
    startActivity(intent);
}else{
    Intent intent =new Intent(LoginActivity.this,RegisterOneActivity.class);
    startActivity(intent);
}

            }

            @Override
            public void onFailure(Call<List<Start>> call, Throwable t) {

                progressBar.setVisibility(View.GONE);

                Toast.makeText(LoginActivity.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();

                System.out.println("Print"+t.toString());
                Intent intent =new Intent(LoginActivity.this,LoginOneActivity.class);
                startActivity(intent);
            }
        });
    }
}