package com.blogbasbas.fortraining.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogbasbas.fortraining.R;
import com.blogbasbas.fortraining.helpers.MyFunction;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.ivGambarBerita)
    ImageView ivGambarBerita;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tvTglTerbit)
    TextView tvTglTerbit;
    @BindView(R.id.tvPenulis)
    TextView tvPenulis;
    @BindView(R.id.wvKontenBerita)
    WebView wvKontenBerita;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    Context mContext;

    String idBerita,title, content, publisher, publishdate, foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //enable button up
        ActionBar ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        mContext = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent share = new Intent();
                share.setAction(Intent.ACTION_VIEW);
                share.addCategory(Intent.CATEGORY_BROWSABLE);
                share.setData(Uri.parse("https://www.instagram.com"));
                startActivity(share);
            }
        });

        addFormData();
    }

    private void addFormData() {

        Intent terima = getIntent();
        idBerita = terima.getStringExtra("ID");
        title = terima.getStringExtra("TITLE");
        content = terima.getStringExtra("CONTENT");
        publisher = terima.getStringExtra("PUBLISHER");
        publishdate = terima.getStringExtra("TGL");
        foto = terima.getStringExtra("FOTO");

        // Set judul actionbar / toolbar
        getSupportActionBar().setTitle(title);

        tvPenulis.setText("Oleh : " + publisher);
        tvTglTerbit.setText(publishdate);
        Picasso.get().load(foto).into(ivGambarBerita);
        // Set isi berita sebagai html ke WebView
        wvKontenBerita.getSettings().setJavaScriptEnabled(true);
        wvKontenBerita.loadData(content, "text/html; charset=utf-8", "UTF-8");
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        Intent kirim = new Intent(DetailActivity.this, InsertActivity.class);

        kirim.putExtra("ID", idBerita.toString());
        kirim.putExtra("TITLE", title.toString());
        kirim.putExtra("CONTENT", content.toString());
        kirim.putExtra("FOTO", foto.toString());

        startActivity(kirim);
        finish();
    }


}
