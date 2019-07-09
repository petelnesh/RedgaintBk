package com.redgiantbkke.techsavanna.redgiantbk;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.redgiantbkke.techsavanna.redgiantbk.app.AppConfig;
import com.redgiantbkke.techsavanna.redgiantbk.fragment.ExtraShopFragment;
import com.redgiantbkke.techsavanna.redgiantbk.helper.ConfirmLocationInterface;
import com.redgiantbkke.techsavanna.redgiantbk.helper.LocationHelper;
import com.redgiantbkke.techsavanna.redgiantbk.methods.SendStructure;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingleStructureActivity extends AppCompatActivity implements ConnectionCallbacks,
        OnConnectionFailedListener,ActivityCompat.OnRequestPermissionsResultCallback {
    TextView structureid, structurename, registrationdate, registrationtype, region, county, telephone;
    public static final String STRUCTURE_ID = "structure_id";
    public static final String STRUCTURE_NAME = "structure_name";
    public static final String STRUCTURE_REGISTRATIONDATE = "structure_registrationdate";
    public static final String STRUCTURE_REGISTATIONTYPE = "structure_registrationtype";
    public static final String STRUCTURE_TELEPHONE = "structure_telephone";
    public static final String STRUCTURE_REGION = "structure_region";
    public static final String STRUCTURE_COUNTRY = "structure_county";

    String structurenamestn, registrationdatestn, registrationtypestn, regionstn, countystn, telephonestn;
    int structureidstn;
    Button btnconfirm;

    private Location mLastLocation;

    double latitude;
    double longitude;

    LocationHelper locationHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_structure);

        structureidstn = getIntent().getExtras().getInt(STRUCTURE_ID);
        structurenamestn = getIntent().getExtras().getString(STRUCTURE_NAME);

        // System.out.print("First:name: "+firstname);
        registrationdatestn = getIntent().getExtras().getString(STRUCTURE_REGISTRATIONDATE);
        registrationtypestn = getIntent().getExtras().getString(STRUCTURE_REGISTATIONTYPE);
        telephonestn = getIntent().getExtras().getString(STRUCTURE_TELEPHONE);
        regionstn = getIntent().getExtras().getString(STRUCTURE_REGION);
        countystn = getIntent().getExtras().getString(STRUCTURE_COUNTRY);

        structurename = findViewById(R.id.name);
        telephone = findViewById(R.id.phones);
        registrationdate = findViewById(R.id.regdate);
        county = findViewById(R.id.county);

        btnconfirm = findViewById(R.id.confirmbtn);

        structurename.setText(structurenamestn);
        telephone.setText(telephonestn);
        registrationdate.setText(registrationdatestn);
        county.setText(countystn);

        locationHelper=new LocationHelper(this);
        locationHelper.checkpermission();


        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SingleStructureActivity.this, R.style.MyDialogTheme);

                builder.setTitle("Confirmation");
                builder.setIcon(R.drawable.resized);
                builder.setMessage("Confirm location by dropping a pin");
                builder.setPositiveButton(Html.fromHtml("<font color='orange'>GPS</font>"),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mLastLocation=locationHelper.getLocation();
//                                LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                                if (ActivityCompat.checkSelfPermission(SingleStructureActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SingleStructureActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                                    // TODO: Consider calling
//                                    //    ActivityCompat#requestPermissions
//                                    // here to request the missing permissions, and then overriding
//                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                    //                                          int[] grantResults)
//                                    // to handle the case where the user grants the permission. See the documentation
//                                    // for ActivityCompat#requestPermissions for more details.
//                                    return;
//                                }
//                                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                if (mLastLocation != null) {

                                    longitude = mLastLocation.getLongitude();
                                    latitude = mLastLocation.getLatitude();

                                    System.out.println();
                                    final String name = structurenamestn;
                                    final String tele = telephonestn;
                                    String lon = String.valueOf(longitude);
                                    String lat = String.valueOf(latitude);

                                    System.out.println("data out" + lat + lon + name + tele);

                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(AppConfig.SERVER_URL)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();
                                    ConfirmLocationInterface confirmLocationInterface = retrofit.create(ConfirmLocationInterface.class);

                                    Call<SendStructure> fileUpload = confirmLocationInterface.locationPost(name, tele, lat, lon);
                                    System.out.println("data out" + fileUpload.toString());
                                    fileUpload.enqueue(new Callback<SendStructure>() {
                                        @Override
                                        public void onResponse(Call<SendStructure> call, Response<SendStructure> response) {
                                            //  System.out.println("Response app"+response.body().getSuccess());
//                                      Toast.makeText(SingleStructureActivity.this, "Response " + response.raw().message(), Toast.LENGTH_LONG).show();
                                            String ans = response.body().getSuccess();
                                            switch (ans) {
                                                case "1":
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(SingleStructureActivity.this, R.style.MyDialogTheme);

                                                    builder.setTitle("Confirmation");
                                                    builder.setIcon(R.drawable.resized);
                                                    builder.setMessage("Pin dropped successfully");
                                                    builder.setPositiveButton(Html.fromHtml("<font color='orange'>Ok</font>"),
                                                            new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int id) {
                                                                    showExtraShopDetailsDialog(name,tele);
                                                                }
                                                            });
                                                    builder.create().show();


                                                    break;
                                            }

//                                        Toast.makeText(SingleStructureActivity.this, "Pin dropped success " , Toast.LENGTH_LONG).show();


                                        }

                                        @Override
                                        public void onFailure(Call<SendStructure> call, Throwable t) {
                                            System.out.println("Error " + t.getMessage());


                                        }
                                    });

                                }else {

//                                    if(btnProceed.isEnabled())
//                                        btnProceed.setEnabled(false);
//
//                                    showToast("Couldn't get the location. Make sure location is enabled on the device");
                                }
                            }});

//                builder.setNeutralButton(Html.fromHtml("<font color='orange'>Continue</font>"),
//                        new DialogInterface.OnClickListener()
//                        {
//                            public void onClick(DialogInterface dialog, int id)
//                            {
//                                Intent intent=new Intent(SingleStructureActivity.this,GetPicActivity.class);
//                                startActivity(intent);
//                            }
//                        });

                builder.setNegativeButton(Html.fromHtml("<font color='orange'>Exit</font>"),
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                dialog.cancel();
                            }
                        });
                builder.create().show();
    }
});
        if (locationHelper.checkPlayServices()) {

            // Building the GoogleApi client
            locationHelper.buildGoogleApiClient();
        }
    }

    private void showExtraShopDetailsDialog( String name,String tele) {
        FragmentManager fm = getSupportFragmentManager();
        ExtraShopFragment siteFragment = ExtraShopFragment.newInstance(name,tele);
        siteFragment.show(fm, "fragment_edit_name");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        locationHelper.onActivityResult(requestCode,resultCode,data);
    }


    @Override
    protected void onResume() {
        super.onResume();
        locationHelper.checkPlayServices();
    }

    /**
     * Google api callback methods
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i("Connection failed:", " ConnectionResult.getErrorCode() = "
                + result.getErrorCode());
    }

    @Override
    public void onConnected(Bundle arg0) {

        // Once connected with google api, get the location
        mLastLocation=locationHelper.getLocation();
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        locationHelper.connectApiClient();
    }


    // Permission check functions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // redirects to utils
        locationHelper.onRequestPermissionsResult(requestCode,permissions,grantResults);

    }

    public void showToast(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


}