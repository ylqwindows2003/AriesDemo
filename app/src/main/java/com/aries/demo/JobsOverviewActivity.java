package com.aries.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.rxjava3.annotations.NonNull;

@SuppressWarnings("ConstantConditions")
public class JobsOverviewActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_overview);

        String title = getIntent().getExtras().getString(EXTRA_TITLE);

        RecyclerView recyclerView = findViewById(R.id.jobs_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Show loading
        ProgressBar progressBar = findViewById(R.id.overview_progressBar);
        progressBar.setVisibility(View.VISIBLE);

        Server.fetchJobs(title).subscribeWith(new DisposableObserver<Response<JobsOverviewQuery.Data>>() {
            @Override
            public void onNext(@NonNull Response<JobsOverviewQuery.Data> dataResponse) {
                if (dataResponse.getData() != null) {
                    JobsOverviewAdapter adapter = new JobsOverviewAdapter(dataResponse.getData().jobs(),JobsOverviewActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                // Query's done, so hide loading
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}