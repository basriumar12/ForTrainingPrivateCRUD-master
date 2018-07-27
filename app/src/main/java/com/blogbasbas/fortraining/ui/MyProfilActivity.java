package com.blogbasbas.fortraining.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.blogbasbas.fortraining.R;
import com.blogbasbas.fortraining.helpers.MyFunction;
import com.blogbasbas.fortraining.helpers.SessionManager;
import com.blogbasbas.fortraining.model.ResponseUser;
import com.blogbasbas.fortraining.network.ApiServices;
import com.blogbasbas.fortraining.network.InitRetrofit;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfilActivity extends MyFunction {

    @BindView(R.id.tv_fullname)
    TextView fullname;
    @BindView(R.id.tv_username) TextView username;
    @BindView(R.id.tv_access) TextView akses;
    String userProfile;
    SessionManager sessionManager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profil);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(getApplicationContext());

        HashMap<String, String> user = sessionManager.getUserDetails();
        userProfile = user.get(SessionManager.kunci_username);
        Log.e("TAG","Hasil dari session "+userProfile);


        profil();
    }

    private void profil() {
        ApiServices api = InitRetrofit.getInstance();
        Call<ResponseUser> call = api.requestProfil(userProfile);
        call.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                if (response.isSuccessful()==true){
                    username.setText(response.body().getUsername().get(0).getUsername());
                    fullname.setText(response.body().getUsername().get(0).getFullname());
                    akses.setText(response.body().getUsername().get(0).getAccess());

                }
                else {
                    simpleToast("Gagal request data");
                }


            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                simpleToast("gagal jaringan");
            }
        });
    }

}
