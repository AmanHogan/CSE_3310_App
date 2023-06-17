package com.example.cse3310defaultproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cse3310defaultproject.data.DataRepository;
import com.example.cse3310defaultproject.data.model.User;

public class AcceptService extends AppCompatActivity {

    DataRepository db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_service);

        // Calling database and getting information about the user
        this.db = DataRepository.getInstance();
        User currentUser = db.getCurrentUser();

        // If the user is a Vendor... Else?
        if(currentUser.isVendor() == true){

        }
        else{
            Toast.makeText(AcceptService.this, "Logout and Register as a Vendor First", Toast.LENGTH_SHORT).show();
            finish();
        }



    }

    public void openVendorAcceptService(View view){
        Intent intent = new Intent(AcceptService.this, VendorAcceptService.class);
        startActivity(intent);
    }
}