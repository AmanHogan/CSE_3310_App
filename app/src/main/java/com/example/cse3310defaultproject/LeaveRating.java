package com.example.cse3310defaultproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cse3310defaultproject.data.DataRepository;
import com.example.cse3310defaultproject.data.model.User;

public class LeaveRating extends AppCompatActivity {

    DataRepository db;
    RatingBar ratingBar;
    String companyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.db = DataRepository.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_rating);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        companyName = bundle.getString("Company");

        Button getRating = findViewById(R.id.submit_rating);
        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        getRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = "Rating is :" + ratingBar.getRating();
                Toast.makeText(LeaveRating.this, "Rating Submitted", Toast.LENGTH_LONG).show();

                finish();
            }
        });

        populateAutoComplete();
    }

    private void populateAutoComplete() {

        // Automatically populates the user information into the form
        User currentUser = db.getCurrentUser();

        TextView ViewCompanyName = (TextView)findViewById(R.id.company_name_review);
        ViewCompanyName.setText("Did you like this company?");

        EditText nameOfUser = (EditText) findViewById(R.id.user_name_rating);
        nameOfUser.setText(currentUser.getName());

        EditText emailOfUser = findViewById(R.id.user_email_rating);
        emailOfUser.setText(currentUser.getAddress());
    }
}