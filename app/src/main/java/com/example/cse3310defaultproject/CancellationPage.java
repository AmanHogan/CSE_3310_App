package com.example.cse3310defaultproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import com.example.cse3310defaultproject.data.DataRepository;
import com.example.cse3310defaultproject.data.model.ServiceRequest;
import com.example.cse3310defaultproject.data.model.Vendor;


public class CancellationPage extends AppCompatActivity {
    public Random rand = new Random();

    public double taxes = 0;
    public double total = 0;
    public int day = 0;
    public int month = 0;
    public int year = 0;
    Calendar calendar = Calendar.getInstance();
    public int completed = 2;
    DataRepository db = DataRepository.getInstance();
    ArrayList<ServiceRequest> services = db.getServiceRequests();
    ArrayList<Vendor> vendors = db.getVendors();
    public int pog = 0;
    public String dates[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancellation_page);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        ArrayList<String> Progress;
        ArrayList<Integer> icons;
        icons = intent.getIntegerArrayListExtra("icon");
        Progress = intent.getStringArrayListExtra("Progress");

        System.out.println(position);
        for(int i = 0; i < vendors.size();i++)
        {
            if(services.get(position).getServiceCategory() == vendors.get(i).getServiceCategory())
                pog = i;
        }

        TextView VendorName = (TextView) findViewById(R.id.Name);
        ImageView imageView = (ImageView) findViewById(R.id.icon);
        TextView subtotal = (TextView) findViewById(R.id.subNum);
        TextView tax = (TextView) findViewById(R.id.taxNum);
        TextView totals = (TextView) findViewById(R.id.TotalNum);
        imageView.setImageResource(icons.get(services.get(position).getServiceCategory().ordinal()));
        Button cancel = (Button) findViewById(R.id.button);
        VendorName.setText(vendors.get(pog).getName());
        TextView date = (TextView) findViewById(R.id.Date);
        TextView pro = (TextView)findViewById(R.id.Progress);

        dates = services.get(position).getDate().split("/");


        month = calendar.get(Calendar.MONTH) + 1 - rand.nextInt(2) + rand.nextInt(4);

        year = calendar.get(Calendar.YEAR) + rand.nextInt(1);
        if (month == 2) {
            if (year % 4 == 0)
                day = calendar.get(Calendar.DAY_OF_MONTH) - rand.nextInt(5) + rand.nextInt(3);
            else
                day = calendar.get(Calendar.DAY_OF_MONTH) - rand.nextInt(5) + rand.nextInt(3);
        } else if (month % 2 == 0)
            day = calendar.get(Calendar.DAY_OF_MONTH) - rand.nextInt(5) + rand.nextInt(3);
        else
            day = calendar.get(Calendar.DAY_OF_MONTH) - rand.nextInt(5) + rand.nextInt(3);

        day = Integer.parseInt(dates[0]);
        month = Integer.parseInt(dates[1]);
        year = Integer.parseInt(dates[2]);

        date.setText(day + "/" + month + "/" + year);

        double price = services.get(position).getPrice();
        subtotal.setText("$ " + String.format("%.2f", price));
        taxes = (price * (0.0825));
        tax.setText("$ " + String.format("%.2f", taxes));
        total = taxes + price;
        totals.setText("$ " + String.format("%.2f", total));



        if ((year == calendar.get(Calendar.YEAR) && (day == calendar.get(Calendar.DAY_OF_MONTH)) && (month == calendar.get(Calendar.MONTH) + 1))) {
            cancel.setEnabled(false);
            //System.out.println("IT is today");
            completed = 1;
        }
        if ((month == calendar.get(Calendar.MONTH) + 1))
        {
             if (day < calendar.get(Calendar.DAY_OF_MONTH  ) || day == calendar.get(Calendar.DAY_OF_MONTH) + 1) {
                 //System.out.println("It has already been completed by day");
                cancel.setEnabled(false);
                completed = 0;
            }
        }
        if(month < calendar.get(Calendar.MONTH) +1)
        {
            //System.out.println("It has already been completed by month");
            cancel.setEnabled(false);
            completed = 0;
        }
        switch (completed)
        {
            case 0:
                pro.setText(Progress.get(completed));
                break;
            case 1:
                pro.setText((Progress.get(completed)));
                break;
            case 2:
                pro.setText(Progress.get(completed));
                break;
            default:
                pro.setText(Progress.get(completed));
                break;
        }

    }
    public void onClick(View view)
    {
        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);
        intent.putExtra("delete",position);
        setResult(RESULT_OK,intent);
        finish();
    }
}