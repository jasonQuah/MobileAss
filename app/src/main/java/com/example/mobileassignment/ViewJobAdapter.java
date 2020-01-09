package com.example.mobileassignment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileassignment.models.Job;
import com.example.mobileassignment.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ViewJobAdapter extends RecyclerView.Adapter<ViewJobAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<Job> jobList;
    private String mAuth = FirebaseAuth.getInstance().getCurrentUser().getUid();

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference table_user = database.getReference("User");


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView position;
        public TextView name;
        public TextView Description;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            position = itemView.findViewById(R.id.jobProfile);
            name = itemView.findViewById(R.id.companyName);
            Description = itemView.findViewById(R.id.jobDescription);
        }
    }

    public ViewJobAdapter(ArrayList<Job> jobLists){
        jobList= jobLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(parent.getContext()).inflate(R.layout.custom_view_job_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Job currentItem = jobList.get(position);

            holder.position.setText(currentItem.getJob_Position());
            holder.name.setText(currentItem.getJob_requirement());
            holder.Description.setText(currentItem.getJob_description());

    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }
}
