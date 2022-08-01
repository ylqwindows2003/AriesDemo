package com.aries.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import io.reactivex.rxjava3.annotations.NonNull;

@SuppressWarnings("ConstantConditions")
public class JobsDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_TITLE = "TITLE";
    public static final String EXTRA_NAME = "NAME";
    public static final String EXTRA_LOGO = "LOGO";
    public static final String EXTRA_DESC = "DESC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_detail);

//        String id = getIntent().getExtras().getString(EXTRA_ID);

        String title = getIntent().getExtras().getString(EXTRA_TITLE);
        String name = getIntent().getExtras().getString(EXTRA_NAME);
        String logo = getIntent().getExtras().getString(EXTRA_LOGO);
        String description = getIntent().getExtras().getString(EXTRA_DESC);

        TextView comTitle = findViewById(R.id.com_title);
        ImageView comLogo = findViewById(R.id.com_logo);
        TextView comName = findViewById(R.id.com_name);
        TextView comDesc = findViewById(R.id.com_desc);

        comTitle.setText(title);
        comName.setText(name);
        Picasso.get().load(logo).into(comLogo);
        comDesc.setText(description);
    }
}