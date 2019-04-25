 package com.blogbasbas.fortraining.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.blogbasbas.fortraining.R;
import com.blogbasbas.fortraining.adapter.AdapterBerita;
import com.blogbasbas.fortraining.helpers.MyFunction;
import com.blogbasbas.fortraining.helpers.SessionManager;
import com.blogbasbas.fortraining.model.BeritaItem;
import com.blogbasbas.fortraining.model.ResponseBerita;
import com.blogbasbas.fortraining.network.ApiServices;
import com.blogbasbas.fortraining.network.InitRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class MainActivity extends MyFunction {

     RecyclerView recyclerView;
     SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.rvListBerita);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swlayout);

        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        showData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showData();
            }
        });
    }

     private void showData() {
         ApiServices api = InitRetrofit.getInstance();
         Call<ResponseBerita> call = api.requestBerita();
         call.enqueue(new Callback<ResponseBerita>() {
             @Override
             public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                 if(response.body().getStatus()){
                     List<BeritaItem> berita = response.body().getBerita();
                     AdapterBerita adapterBerita = new AdapterBerita(MainActivity.this,berita);
                     swipeRefreshLayout.setRefreshing(false);
                     recyclerView.setAdapter(adapterBerita);

                 } else {
                 //    simpleToast("data tak ada");
                 }
             }

             @Override
             public void onFailure(Call<ResponseBerita> call, Throwable t) {
                 simpleToast("gagal req jaringan "+t.getMessage());
                 Log.e("Tag","error jaringan "+t.getLocalizedMessage());
             }
         });
     }

     //method oncreateOptionmenu

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater menuInflater = getMenuInflater();
         menuInflater.inflate(R.menu.menu,menu);
         return true;
     }

     @Override
     public void onBackPressed() {
         super.onBackPressed();
         System.exit(0);
         finish();
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                System.exit(0);
                finish();
            case R.id.menu_exit:

                logout();
              break;
            case R.id.menu_profil:
                getProfil();
                break;
                case R.id.menu_add:
               simpleIntent(InsertActivity.class);
                break;
                
                default:
        }
        
        return super.onOptionsItemSelected(item);
     }

     private void getProfil() {
                simpleIntent(MyProfilActivity.class);
     }

     private void logout() {
         AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
         alert.setTitle("Keluar . . . .");
         alert.setMessage("Apakah ingin keluar ?");
         alert.setPositiveButton("ya", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {

                 //session buat berakhir
                 new SessionManager(getApplicationContext()).logout();
                 finish();
             }
         });

         alert.setNegativeButton("tidak", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {

             }
         });
         alert.setCancelable(false);
         alert.create().show();

     }
 }
