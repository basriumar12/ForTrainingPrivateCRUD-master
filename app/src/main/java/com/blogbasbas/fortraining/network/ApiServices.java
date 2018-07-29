package com.blogbasbas.fortraining.network;


import com.blogbasbas.fortraining.model.ResponseBerita;
import com.blogbasbas.fortraining.model.ResponseLogin;
import com.blogbasbas.fortraining.model.ResponseUser;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Firas Luthfi on 2/12/2018.
 */

public interface ApiServices {

    //get berita
    @GET("get.php")
    Call<ResponseBerita>requestBerita();

    //login api
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> requestLogin(@Field("username") String username,
                                     @Field("password") String password);

    //register akun
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseLogin> requestRegister(@Field("fullname") String fullname,
                                        @Field("username") String username,
                                        @Field("password") String password);

    // insert berita dengan multipart

    @Multipart
    @POST("insert.php")
    Call<ResponseBerita> postBerita(@Part("title") String title,
                                    @Part("content") String content,
                                    @Part MultipartBody.Part file,
                                    @Part("file") RequestBody name,
                                    @Part("id_user") int iduser);

    //delete berita
    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseBerita> requestDelete(@Field("id_berita") int id_berita);


    //update berita dan gambar
    @Multipart
    @POST("update.php")
    Call<ResponseBerita> requestUpdateFoto(@Part("id_berita") int id_berita,
                                           @Part("title") String title,
                                           @Part("content") String content,
                                           @Part MultipartBody.Part file,
                                           @Part("file") RequestBody name);

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseBerita> requestUpdate(@Field("id_berita") String id_berita,
                                       @Field("title") String title,
                                       @Field("content") String content,
                                       @Field("flag") String flag);
    //update user
    @FormUrlEncoded
    @POST("updateUser.php")
    Call<ResponseLogin> requestChange(@Field("username") String username,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST("getProfil.php")
    Call<ResponseUser> requestProfil(@Field("username") String username);
}
