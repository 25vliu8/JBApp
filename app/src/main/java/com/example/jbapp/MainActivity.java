package com.example.jbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView mRecyclerView;
    public JobAdapter mJobAdapter;
    List<Job> orders = new ArrayList<>();
    Button job_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        job_btn = findViewById(R.id.job_btn);
        job_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewJobActivity.class);
            startActivity(intent);
        });

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mJobAdapter = new JobAdapter(orders);
        mRecyclerView.setAdapter(mJobAdapter);

        updateOrders();

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateOrders();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateOrders() {
        List<Job> all_jobs = new ArrayList<>();
        //get all the orders that are not in the delivered state

        CollectionReference collectionRef = db.collection("jobs");

        collectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null) {
                    for (QueryDocumentSnapshot documentSnapshot : querySnapshot) {
                        // Access the document data using documentSnapshot.getData()
                        // For example, if your documents have a "title" field:
                        String title = documentSnapshot.getString("title");
                        String company = documentSnapshot.getString("company");
                        String min = documentSnapshot.getString("min");
                        String max = documentSnapshot.getString("max");
                        String salary = "$" + min + "-$" + max;
                        String location = documentSnapshot.getString("location");

                        Job curr_job = new Job(title, company, salary, location);

                        all_jobs.add(curr_job);
                    }
                }
            } else {
                Log.d("Firestore", "Error getting documents: ", task.getException());
            }

            mJobAdapter = new JobAdapter(all_jobs);
            mRecyclerView.setAdapter(mJobAdapter);
            mJobAdapter.notifyDataSetChanged();
        });
    }
}