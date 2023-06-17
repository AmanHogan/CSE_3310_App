package com.example.cse3310defaultproject;

import android.content.Intent;
import android.os.Bundle;

import com.example.cse3310defaultproject.data.DataRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.Random;

import com.example.cse3310defaultproject.ui.main.SectionsPagerAdapter;
import com.example.cse3310defaultproject.databinding.ActivityFindServiceBinding;

public class FindService extends AppCompatActivity {



    String serviceCategory[] = {"Plumbing Inc.", "Electrical Inc.", "Tutoring Inc.", "Appliances Inc.", "Cleaning Inc.", "Moving"};
    ListView listView;
    SearchView searchView;
    ArrayAdapter<String> arrayAdapter;
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_service);
        DataRepository db = DataRepository.getInstance();

        // Creating an array adapter that will filter the names listed in the search view
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, serviceCategory);
        searchView = findViewById(R.id.searching);
        listView = (ListView) findViewById(R.id.service_list_view);
        listView.setAdapter(arrayAdapter);

        //ServiceBaseAdapter serviceBaseAdapter = new ServiceBaseAdapter(getApplicationContext(), db.getVendors());
        //listView.setAdapter(serviceBaseAdapter);



        // Upon Clicking an item in the list view, open the Rating section//
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent = new Intent(FindService.this, LeaveRating.class);

                // Send the selected item (Company Name) to the LeaveRating Class//
                intent.putExtra("company", serviceCategory[i]);
                startActivity(intent);
            }
        });

        // Filters searchbar based on entered characters
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                FindService.this.arrayAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                FindService.this.arrayAdapter.getFilter().filter(s);
                return false;
            }
        });
    }
}