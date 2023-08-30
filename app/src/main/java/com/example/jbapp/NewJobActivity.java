package com.example.jbapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NewJobActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job);

        submit_btn = findViewById(R.id.submit);

        submit_btn.setOnClickListener(v -> {
            // put them into a new object then put that object into firestore
            // update "from" so that it is a dropdown
            TextInputLayout titleInput = findViewById(R.id.title_input);
            TextInputLayout companyInput = findViewById(R.id.company_input);
            EditText minInput = findViewById(R.id.minNumber);
            EditText maxInput = findViewById(R.id.maxNumber);
            EditText locationInput = findViewById(R.id.location_input);
            String title_string = titleInput.getEditText().getText().toString();
            String company_string = companyInput.getEditText().getText().toString();
            String min_string = minInput.getText().toString();
            String max_string = maxInput.getText().toString();
            int min_int = Integer.parseInt(min_string);
            int max_int = Integer.parseInt(max_string);
            String location_string = locationInput.getText().toString();

            if (TextUtils.isEmpty(title_string) || TextUtils.isEmpty(company_string) || TextUtils.isEmpty(min_string)
            || TextUtils.isEmpty(max_string) || TextUtils.isEmpty(location_string)) {
                Toast.makeText(getApplicationContext(), "Please fill out the required fields", Toast.LENGTH_SHORT).show();
            }  else if (min_int >= max_int){
                Toast.makeText(getApplicationContext(), "Please make sure your maximum salary is greater than the minimum salary", Toast.LENGTH_SHORT).show();
            }
            else {
                Map<String, String> new_job = new HashMap<>();
                new_job.put("title", title_string);
                new_job.put("company", company_string);
                new_job.put("min", min_string);
                new_job.put("max", max_string);
                new_job.put("location", location_string);
                db.collection("jobs").add(new_job);

                finish();
            }
        });
    }
}