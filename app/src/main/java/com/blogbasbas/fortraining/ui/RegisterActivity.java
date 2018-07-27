package com.blogbasbas.fortraining.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blogbasbas.fortraining.R;
import com.blogbasbas.fortraining.helpers.MyFunction;
import com.blogbasbas.fortraining.helpers.MyValidation;
import com.blogbasbas.fortraining.model.ResponseLogin;
import com.blogbasbas.fortraining.model.ResponseLogin;
import com.blogbasbas.fortraining.network.ApiServices;
import com.blogbasbas.fortraining.network.InitRetrofit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 25/07/2018.
 */

public class RegisterActivity extends MyFunction {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etNama)
    EditText etNama;
    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etRePassword)
    EditText etRePassword;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.btnChange)
    Button btnChange;
    @BindView(R.id.rlLayout)
    RelativeLayout rlLayout;
    MyValidation myValidation;

    String getUsername, username,password, fullname, rePassword;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        //inisialisasi kelasnya
        myValidation = new MyValidation(this);

        getUsername = getIntent().getStringExtra("US");
        if (getUsername.equals("")){
            etUsername.setVisibility(View.VISIBLE);
            etNama.setVisibility(View.VISIBLE);


        }else {
            etUsername.setVisibility(View.GONE);
            etNama.setVisibility(View.GONE);
            btnRegister.setVisibility(View.GONE);
            btnChange.setVisibility(View.VISIBLE);
        }
    }



    @OnClick({R.id.btnRegister, R.id.btnChange})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                //method register
                registerUser();

                break;
            case R.id.btnChange:
                updatePassword();
                break;
        }
    }

    private void updatePassword() {

        password = etPassword.getText().toString();
        rePassword = etRePassword.getText().toString();


        if (!myValidation.isEmptyField(password)) {
            etPassword.setError("data harus di isi");
        } else if (!myValidation.isEmptyField(rePassword)) {
            etRePassword.setError("data harus di isi");
        } else if (!myValidation.isMatch(password, rePassword)) {
            etRePassword.setError("passwordnya tak sama");
        } else {
            ApiServices api1 = InitRetrofit.getInstance();
            retrofit2.Call<ResponseLogin> call = api1.requestChange(getUsername, password);
            call.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(retrofit2.Call<ResponseLogin> call, Response<ResponseLogin> response) {

                    boolean status = response.body().isStatus();
                    if (status == true) {
                        simpleToast("Berhasil update password " + response.body().getMsg());
                        simpleIntent(LoginActivity.class);
                    } else {
                        simpleToast("gagal update");
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<ResponseLogin> call, Throwable t) {
                    simpleToast("gagal jaringan");
                }
            });

        }
    }

    private void registerUser() {


        fullname = etNama.getText().toString();
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        rePassword = etRePassword.getText().toString();


        if (!myValidation.isEmptyField(fullname)){
            etNama.setError("data harus di isi");
        }
        else if (!myValidation.isEmptyField(username)){
            etUsername.setError("data harus di isi");
        }
        else if (!myValidation.isEmptyField(password)){
            etPassword.setError("data harus di isi");
        }else if (!myValidation.isEmptyField(rePassword)){
            etRePassword.setError("data harus di isi");
        }
        else if (!myValidation.isMatch(password,rePassword)){
            etRePassword.setError("passwordnya tak sama");
        } else {
            ApiServices api = InitRetrofit.getInstance();
            retrofit2.Call<ResponseLogin> call = api.requestRegister(fullname,username,password);
            call.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(retrofit2.Call<ResponseLogin> call, Response<ResponseLogin> response) {

                    boolean status = response.body().isStatus();
                    if (status==true){
                        simpleToast("Berhasil Register "+response.body().getMsg());
                        simpleIntent(LoginActivity.class);
                    } else {
                        simpleToast("Username Sudah ada" + response.body().getMsg());
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<ResponseLogin> call, Throwable t) {
                    simpleToast("gagal jaringan");
                }
            });


        }


        }}