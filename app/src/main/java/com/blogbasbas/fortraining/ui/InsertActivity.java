package com.blogbasbas.fortraining.ui;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blogbasbas.fortraining.R;
import com.blogbasbas.fortraining.helpers.MyFunction;
import com.blogbasbas.fortraining.helpers.MyValidation;
import com.blogbasbas.fortraining.helpers.SessionManager;
import com.blogbasbas.fortraining.model.ResponseBerita;
import com.blogbasbas.fortraining.model.ResponseUser;
import com.blogbasbas.fortraining.network.ApiServices;
import com.blogbasbas.fortraining.network.InitRetrofit;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 29/07/2018.
 */

public class InsertActivity extends MyFunction {

    /**
     * Context Variables
     */
    Context mContext;

    /**
     * Views
     */
    View parentView;

    @BindView(R.id.edt_title)
    EditText edtTitle;
    @BindView(R.id.edt_content)
    EditText edtContent;
    @BindView(R.id.btnFile)
    Button btnFile;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.btn_insertdata)
    Button btnInsertdata;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    @BindView(R.id.btnhapus)
    Button btnhapus;
    @BindView(R.id.ln1)
    LinearLayout ln1;

    ProgressDialog progressDialog;

    String mediaPath;
    String[] mediaColumns = {MediaStore.Video.Media._ID};
    @BindView(R.id.fileName)
    TextView fileName;
    @BindView(R.id.lnVisible)
    LinearLayout lnVisible;

    File file;

    String id_berita, title, content, foto, msg;
    int id_user;
    SessionManager sessionManager;


    MyValidation myValidation;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyStoragePermissions(InsertActivity.this);
        setContentView(R.layout.activity_crud);
        ButterKnife.bind(this);
        myValidation = new MyValidation(this);
        parentView = findViewById(R.id.ln1);
        progressDialog = new ProgressDialog(this);
        mContext = this;
        getProfile();


    }

    private void getProfile() {
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        String userProfile = user.get(SessionManager.kunci_username);
        Log.e("TAG","Hasil dari session "+userProfile);
        ApiServices api = InitRetrofit.getInstance();
        Call<ResponseUser> call = api.requestProfil(userProfile);
        call.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                if (response.isSuccessful()){
                        id_user = response.body().getUsername().get(0).getIdUser();
                        Log.e("TAG","Hasil dari session "+id_user);
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

    @OnClick({R.id.btnFile, R.id.btn_insertdata, R.id.btnUpdate, R.id.btnhapus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnFile:

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
                
                break;
            case R.id.btn_insertdata:

                String title = edtTitle.getText().toString();
                String content = edtContent.getText().toString();
                String  fn = fileName.getText().toString();

                if (!myValidation.isEmptyField(title)) {
                    edtTitle.setError("Fill news title in here.");
                }else if(!myValidation.isEmptyField(content)){
                    edtContent.setError("Fill news content in here.");
                }else if(!myValidation.isEmptyField(fn)){
                    Snackbar.make(view, "Choose news image first.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{

                    //method insert data
                    insertBerita();
                }
                
                break;
            case R.id.btnUpdate:
                break;
            case R.id.btnhapus:
                break;
        }
    }

    private void insertBerita() {


                progressDialog = ProgressDialog.show(mContext, null, "Uploading...", true, false);
        // Map is used to multipart the file using okhttp3.RequestBody
        file = new File(mediaPath);

        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        String title = edtTitle.getText().toString();
        String content = edtContent.getText().toString();
        getProfile();
        Log.e("TAG","dapatkan id user "+id_user);


        ApiServices getResponse = InitRetrofit.getInstance();
        Call<ResponseBerita> call = getResponse.postBerita(title, content, fileToUpload, filename, id_user);
        call.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {

                    if (response.body().getStatus()) {
                        Snackbar.make(parentView, "Berhasil Insert", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        simpleIntent(MainActivity.class);
                        finish();
                        progressDialog.dismiss();
                    }
                        else {
                        Snackbar.make(parentView, "Gagal Insert ", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

            }


            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                Snackbar.make(parentView, "Cek Jaringan", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                progressDialog.dismiss();
                Log.e("TAG","Error jaringan "+t.getMessage());

            }
        });
    }


    //vervikasi permission storage

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                String[] nameFl = mediaPath.toString().split("/");
                int jum = nameFl.length;
                // Set the Image in ImageView for Previewing the Media
                imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();
                lnVisible.setVisibility(View.VISIBLE);
//                fileName.setText(mediaPath);
                fileName.setText(nameFl[jum - 1]);
                fileName.setVisibility(View.VISIBLE);

            } else {
                Snackbar.make(parentView, "You haven't picked Image.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        } catch (Exception e) {
            Snackbar.make(parentView, "Something went wrong.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

    }
}
