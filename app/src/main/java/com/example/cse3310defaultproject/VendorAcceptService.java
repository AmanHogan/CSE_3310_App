package com.example.cse3310defaultproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class VendorAcceptService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_accept_service);
    }
    public void onClickBackToHomeMenu(View view){
        Toast.makeText(VendorAcceptService.this, "Customer was notified of your bid", Toast.LENGTH_SHORT).show();
        finish();
    }
}
