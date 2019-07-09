package com.redgiantbkke.techsavanna.redgiantbk.fragment;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.redgiantbkke.techsavanna.redgiantbk.DashboardActivity;
import com.redgiantbkke.techsavanna.redgiantbk.R;
import com.redgiantbkke.techsavanna.redgiantbk.helper.ApiExtraDetailsClient;
import com.redgiantbkke.techsavanna.redgiantbk.helper.ApiExtraDetailsService;
import com.redgiantbkke.techsavanna.redgiantbk.methods.ShopExtra;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExtraShopFragment extends DialogFragment {
    private ProgressDialog pDialog;

    String[] typeSpinner = new String[] {
            "Kiosk", "Supermarket", "Minimart", "Hotel", "Hawkers"};
    Spinner shopsizespinner;
    Button btnnext;
    EditText stockext;

    static String nameshop,teleshop;


    public ExtraShopFragment() {
        // Required empty public constructor
    }
public static ExtraShopFragment newInstance(String name,String tele){
        System.out.println("title "+name);

    nameshop=name;
    teleshop=tele;
    ExtraShopFragment frag = new ExtraShopFragment();
    Bundle args = new Bundle();
    args.putString("title", name);
    frag.setArguments(args);
    return frag;

}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_blank, container, false);
        shopsizespinner=v.findViewById(R.id.shopsizetype);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, typeSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shopsizespinner.setAdapter(adapter);
        btnnext=v.findViewById(R.id.btnnext);
        stockext=v.findViewById(R.id.editqty);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToServerDB(nameshop,teleshop);
            }
        });
        return v;
    }

    public void saveToServerDB(String nameshop,String teleshop){
        pDialog = new ProgressDialog(getActivity(),
                R.style.Theme_AppCompat_DayNight_DarkActionBar);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Sending site expense...");
        pDialog.setCancelable(false);

        showpDialog();

        String shopsize =  shopsizespinner.getSelectedItem().toString();
        String stocktake = stockext.getText().toString();


        ApiExtraDetailsService service = ApiExtraDetailsClient.getClient().create(ApiExtraDetailsService.class);
        //User user = new User(name, email, password);


        Call<ShopExtra> userCall = service.siteExpense(shopsize, stocktake,nameshop,teleshop);
        System.out.println("data shop"+ shopsize+" "+stocktake+" "+nameshop+" "+teleshop);

        userCall.enqueue(new Callback<ShopExtra>() {
            @Override
            public void onResponse(Call<ShopExtra> call, Response<ShopExtra> response) {
                hidepDialog();
                //onSignupSuccess();
                System.out.println("data out"+ call);
                Log.d("onResponse", "" + response.body().getMessage());

String res=response.body().getMessage();



                if(response.body().getSuccess() == 1) {
                    Toast.makeText(getActivity(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);

                    builder.setTitle("Confirmation");
                    builder.setIcon(R.drawable.resized);
                    builder.setMessage("Saved successfully");
                    builder.setPositiveButton(Html.fromHtml("<font color='orange'>Ok</font>"),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(getActivity(), DashboardActivity.class);
                                    startActivity(intent);

                                }
                            });
                    builder.create().show();

                }else {
                    Toast.makeText(getActivity(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ShopExtra> call, Throwable t) {
                hidepDialog();
                Log.d("onFailure", t.toString());
            }
        });
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
