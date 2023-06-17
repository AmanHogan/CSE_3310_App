package com.example.cse3310defaultproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.cse3310defaultproject.data.DataRepository;
import com.example.cse3310defaultproject.data.model.ServiceRequest;
import com.example.cse3310defaultproject.data.model.User;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ManageService extends AppCompatActivity {

    DataRepository db;
    ListView listView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_find_service_list_view);
        setContentView(R.layout.activity_service_manager);
        this.db = DataRepository.getInstance();
        User currentUser = db.getCurrentUser();

        ArrayList<ServiceRequest> services = db
                .getServiceRequests()
                .stream()
                .filter(service -> service.getCustomerID().equals(currentUser.getUserId()))
                .collect(
                        Collectors.toCollection(ArrayList<ServiceRequest>::new)
                );

        Log.d("ManageServices", "Services: " + services.toString());

        ListView listView = (ListView) findViewById(R.id.manageServiceListView);
        if (listView == null)
            Log.d("ManageService", "it's null");
        Log.d("ManageService", "list view = " + listView.toString());
        ManageServiceAdapter adapter = new ManageServiceAdapter(getApplicationContext(), services);
        listView.setAdapter(adapter);
    }
}