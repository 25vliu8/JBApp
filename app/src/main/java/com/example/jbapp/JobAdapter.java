package com.example.jbapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {
    private List<Job> jobs;
    Context context;


    public JobAdapter(List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_item, parent, false);
        context = parent.getContext();
        return new JobViewHolder(view);
    }


    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        Job job = jobs.get(position);

        holder.titleTextView.setText(job.getJobTitle());
        holder.salaryTextView.setText(job.getSalary());
        holder.locationTextView.setText(job.getLocation());
        holder.companyTextView.setText(String.valueOf(job.getCompany()));
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class JobViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView salaryTextView;
        public TextView locationTextView;
        public TextView companyTextView;


        public JobViewHolder(View itemView) {
            super(itemView);
            //set all card labels
            titleTextView = itemView.findViewById(R.id.job_title);
            salaryTextView = itemView.findViewById(R.id.salary_range);
            locationTextView = itemView.findViewById(R.id.location);
            companyTextView = itemView.findViewById(R.id.company);

        }
    }
}

