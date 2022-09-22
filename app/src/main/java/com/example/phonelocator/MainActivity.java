package com.example.phonelocator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonelocator.model.DeviceModel;
import com.example.phonelocator.model.PostModel;
import com.example.phonelocator.model.ResponseModel;
import com.example.phonelocator.network.ApiInterface;
import com.example.phonelocator.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText editTextLogin, editTextSandi;
    private Button buttonLogin;
    private TextView textViewLogin;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLogin = findViewById(R.id.editTextPersonName);
        editTextSandi = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.button);
        textViewLogin = findViewById(R.id.textview_Login_Status);
        progressDialog = new ProgressDialog(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendData();

            }

            private void sendData() {
                progressDialog.setMessage("Post Data in progress....");
                progressDialog.setCancelable(false);
                progressDialog.show();
                ApiInterface apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
                final PostModel postModel = new PostModel(editTextLogin.getText().toString(), editTextSandi.getText().toString());
                Call<PostModel> call = apiInterface.postData(postModel, "32833ss@dsdefjedfedfpf");
                System.out.println("sampai 2");
                call.enqueue(new Callback<PostModel>() {
                    @Override
                    public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                        String token;
                        System.out.println("sampai 5");
                        progressDialog.dismiss();
                        if ((response.code() == 200) || (response.code() == 201)) {
                            ResponseModel.code = response.body().getCode();
                            System.out.println(ResponseModel.code);
                            if (ResponseModel.code.equals("00")) {
                                token = response.body().getToken();
                                if (token != null) {

                                    ResponseModel.token = token;
                                    ResponseModel.devices = response.body().getDevice();
                                    startActivity(new Intent(getApplicationContext(), DeviceList.class));
                                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                                    System.out.println("LOGINTrue<<<<<<<<<1");
                                    textViewLogin.setText("");
                                } else {
                                    textViewLogin.setText("User / Password Salah");

                                }
                            }
                        } else {
                            textViewLogin.setText("User / Password Salah");

                        }


                    }

                    @Override
                    public void onFailure(Call<PostModel> call, Throwable t) {
                        System.out.println("sampai 7");
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(t.getMessage());
                    }
                });

            }
        });

    }
}