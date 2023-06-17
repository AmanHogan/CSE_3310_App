package com.example.cse3310defaultproject;
import com.example.cse3310defaultproject.data.DataRepository;
import com.example.cse3310defaultproject.data.model.ServiceRequest;
import com.example.cse3310defaultproject.data.model.Vendor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import java.util.Random;
public class OrderHistory extends AppCompatActivity
{
    ListView listView;
    ArrayList<String> progress = new ArrayList<>();
    ArrayList<Integer> icons = new ArrayList<>();
    public int selection = 0;
    DataRepository db = DataRepository.getInstance();
    ArrayList<Vendor> vendors = db.getVendors();
    ArrayList<ServiceRequest> service = db.getServiceRequests();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);




        listView=(ListView)findViewById(R.id.orders);

        progress.add("Complete");
        progress.add("In Progress");
        progress.add("Not Complete");

        icons.add(R.drawable.appliances);
        icons.add(R.drawable.electrical);
        icons.add(R.drawable.plumbing);
        icons.add(R.drawable.cleaning);
        icons.add(R.drawable.study);
        icons.add(R.drawable.moving);
        Random rand = new Random();
        if(service.size() > 0)
        {
            selection = rand.nextInt(service.size());

            CustomListView adapter = new CustomListView(this,service,icons);

            listView.setAdapter(adapter);
            listView.setOnItemClickListener((parent, view, i, l) -> {
                Intent intent = new Intent(getApplicationContext(), CancellationPage.class);
                intent.putStringArrayListExtra("Progress", progress);
                intent.putExtra("position", i);
                //intent.putExtra("Icon",icons.get(i));
                intent.putIntegerArrayListExtra("icon", icons);
                startActivityForResult(intent, 1);
            });
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK)
            {
                int pos = data.getIntExtra("delete", 0);
                service.remove(pos);
                listView = (ListView) findViewById(R.id.orders);
                CustomListView adapter = new CustomListView(this, service, icons);
                listView.setAdapter(adapter);
            }
    }
}