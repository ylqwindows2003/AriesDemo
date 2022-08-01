package com.aries.demo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class JobsOverviewAdapter extends RecyclerView.Adapter<JobsOverviewAdapter.JobHolder> {

    private Context context;
    private List<JobsOverviewQuery.Jobs> jobs;

    JobsOverviewAdapter(List<JobsOverviewQuery.Jobs> jobs, Context context) {
        this.jobs = jobs;
        this.context = context;
    }

    @NonNull
    @Override
    public JobHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_company, parent, false);

        return new JobHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobHolder holder, int position) {
        JobsOverviewQuery.Jobs job = jobs.get(position);

        holder.company.setText(job.company().name());
        holder.company.setOnClickListener(view -> {
            Intent intent = new Intent(context, JobsDetailsActivity.class);
            intent.putExtra(JobsDetailsActivity.EXTRA_ID, job.id());
            intent.putExtra(JobsDetailsActivity.EXTRA_TITLE, job.title());
            intent.putExtra(JobsDetailsActivity.EXTRA_NAME, job.company().name());
            intent.putExtra(JobsDetailsActivity.EXTRA_LOGO, job.logoUrl());
            intent.putExtra(JobsDetailsActivity.EXTRA_DESC, job.description());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    class JobHolder extends RecyclerView.ViewHolder {
        TextView company;

        public JobHolder(@NonNull View itemView) {
            super(itemView);

            company = itemView.findViewById(R.id.company_title);
        }
    }
}
