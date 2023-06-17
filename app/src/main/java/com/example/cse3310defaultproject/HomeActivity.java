package com.example.cse3310defaultproject;

import android.content.Intent;
import android.os.Bundle;

import com.example.cse3310defaultproject.databinding.ActivityHomeBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.cse3310defaultproject.data.DataRepository;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;



public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    public Button button;
    public ArrayList<String> userInfo;
    public String nameOfUser;

    public String userServiceInformation[];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getCurrentUser();



    }

    public void openActivityOrder()
    {
        Intent intent = new Intent(this, OrderHistory.class);
        startActivity(intent);
    }

    public void openActivityOrder(View view) {
        Intent intent = new Intent(this, OrderHistory.class);
        startActivity(intent);
    }

    public void openFindService(View view) {
        Intent intent = new Intent(this, FindService.class);
        startActivity(intent);
    }

    public void openPlaceService(View view){
        Intent intent = new Intent(this, PlaceService.class);
        startActivity(intent);
    }

    public void getCurrentUser() {
        DataRepository db = DataRepository.getInstance();
        nameOfUser = db.getCurrentUser().getName();
        TextView nameDisplay = findViewById(R.id.welcome_message);
        nameDisplay.setText("Welcome, " + nameOfUser);
    }

    public void openManageService(View view){
        Intent intent = new Intent(this, ManageService.class);
        startActivity(intent);
    }

    public void openSettings(View view){
        Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openAcceptService(View view){
        Intent intent = new Intent(HomeActivity.this, AcceptService.class);
        startActivity(intent);
    }


}


