package com.example.phonelocator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonelocator.model.DeviceModel;
import com.example.phonelocator.model.LastTimeGpsModel;
import com.example.phonelocator.model.ResponseModel;
import com.example.phonelocator.network.ApiLastTimeGps;
import com.example.phonelocator.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceList extends AppCompatActivity {
    // Inisialasi variable
    RecyclerView recyclerView;
    ItemClickListener itemClickListener;
    MainAdapter adapter;
    Button buttonInquiry;

    RadioButton radioButton30 , radioButton2 , radioButton6, radioButtonMaps, radioButtonSatellite ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);
        ArrayList<String> arrayList = new ArrayList<>();

        System.out.println(ResponseModel.devices);
        ResponseModel.deviceId = ResponseModel.devices[0].deviceId;
        for (DeviceModel device : ResponseModel.devices) {
            System.out.println(device.name);
            arrayList.add(device.name);

        }

        // assign variable
        recyclerView = findViewById(R.id.recycle_view);

        //Inisial ArrayList


        //Inisialisasi listener
        itemClickListener = new ItemClickListener() {
            @Override
            public void onClick(String s) {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
                System.out.println("terpilif "+ s);
            }
        };
        // set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(DeviceList.this));
        // inisialisasi adaptor
        adapter = new MainAdapter(arrayList,itemClickListener);
        // set adapter
        System.out.println("lewat 1");
        recyclerView.setAdapter(adapter);
        System.out.println("lewat 2");

        buttonInquiry = findViewById(R.id.button_inquiry);
        buttonInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            senddata();
            }
        });

        radioButton30 =findViewById(R.id.minutes);
        radioButton30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResponseModel.duration = 30;
            }
        });

        radioButton2 =findViewById(R.id.two_hours);
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResponseModel.duration = 120;
            }
        });

        radioButton6 =findViewById(R.id.six_hours);
        radioButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResponseModel.duration = 360;;
            }
        });
        radioButtonMaps =findViewById(R.id.radio_button_maps);
        radioButtonMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResponseModel.hybrid = false;
            }
        });

        radioButtonSatellite =findViewById(R.id.radio_button_satellite);
        radioButtonSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResponseModel.hybrid = true;
            }
        });
        if (ResponseModel.hybrid)
            radioButtonSatellite.setChecked(true);
        else
            radioButtonMaps.setChecked(true);
    }

    private void senddata() {
        ApiLastTimeGps apiLastTimeGps = RetrofitClient.getRetrofit().create(ApiLastTimeGps.class);
        final LastTimeGpsModel lastTimeGpsModel = new LastTimeGpsModel(ResponseModel.deviceId,ResponseModel.token,ResponseModel.duration);
        Call<LastTimeGpsModel> call = apiLastTimeGps.postData(lastTimeGpsModel, "32833ss@dsdefjedfedfpf");
        call.enqueue(new Callback<LastTimeGpsModel>() {
            @Override
            public void onResponse(Call<LastTimeGpsModel> call, Response<LastTimeGpsModel> response) {
                     ResponseModel.code = response.body().getCode();
                    ResponseModel.locations = response.body().locations;
                    startActivity(new Intent( getApplicationContext(),MapsActivity.class));
                    overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            }

            @Override
            public void onFailure(Call<LastTimeGpsModel> call, Throwable t) {
                System.out.println("sampai 7");
                Toast.makeText(DeviceList.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });

    }
}