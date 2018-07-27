package com.blogbasbas.fortraining.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blogbasbas.fortraining.R;
import com.blogbasbas.fortraining.helpers.MyFunction;
import com.blogbasbas.fortraining.helpers.MyValidation;
import com.blogbasbas.fortraining.helpers.SessionManager;
import com.blogbasbas.fortraining.model.ResponseLogin;
import com.blogbasbas.fortraining.network.ApiServices;
import com.blogbasbas.fortraining.network.InitRetrofit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 25/07/2018.
 */

public class LoginActivity extends MyFunction {


    //inisialisasi id di layout activity login
    //TAG
    private final static String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.etPassword)
    EditText etPassword;
    ProgressDialog loading;
    @BindView(R.id.etUsername)
    EditText etUsername;
    Context mContext;
    String username, password;
    MyValidation myValidation;
    @BindView(R.id.tvPass)
    TextView tvPass;
    @BindView(R.id.tvVersion)
    TextView tvVersion;
    View parentView;
    @BindView(R.id.rlLayout)
    RelativeLayout rlLayout;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;



       @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        myValidation = new MyValidation(this);
    }



        // genearate id button utk klik pakai butter knife
    @OnClick({R.id.tvPass, R.id.btnLogin, R.id.btnRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //lupas password
            case R.id.tvPass:
                username =etUsername.getText().toString();

                if (!myValidation.isEmptyField(username)){
                    etUsername.setError("Username Harus Di isi");
                } else {

                    Intent changeUsername = new Intent(LoginActivity.this,RegisterActivity.class);
                    changeUsername.putExtra("US",username);
                    startActivity(changeUsername);
                    finish();
                }

                break;
                //untuk login
            case R.id.btnLogin:
                requestLogin();
                break;

                //untuk register
            case R.id.btnRegister:
                Intent changeUsername = new Intent(LoginActivity.this,RegisterActivity.class);
                changeUsername.putExtra("US","");
                startActivity(changeUsername);
                finish();
                break;
        }
    }

    private void requestLogin() {

           username = etUsername.getText().toString();
           password = etPassword.getText().toString();
        if (!myValidation.isEmptyField(username)){
            etUsername.setError("Username Harus Di isi");
        } else if (!myValidation.isEmptyField(password)){
            etPassword.setError("Data Harus di isi");
        } else {
            ApiServices api1 = InitRetrofit.getInstance();
            retrofit2.Call<ResponseLogin> call = api1.requestChange(username, password);
            call.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    boolean status = response.body().isStatus();
                    if (status==true) {
                        simpleToast("Berhasil Login" + response.body().getMsg());
                        //nyimpan session
                        new SessionManager(getApplicationContext()).createSession(username);
                        simpleIntent(MainActivity.class);
                        finish();
                    } else {
                        simpleToast("gagal Login");
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    simpleToast("gagal jaringan");
                }
            });



        }

    }
}
