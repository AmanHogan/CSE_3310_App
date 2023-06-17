package com.example.cse3310defaultproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cse3310defaultproject.data.DataRepository;
import com.example.cse3310defaultproject.data.model.ServiceRequest;
import com.example.cse3310defaultproject.data.model.Vendor;

import java.util.ArrayList;

public class ManageServiceAdapter extends BaseAdapter {


    Context context;
    private ArrayList<ServiceRequest> srs;
    LayoutInflater inflater;
    private DataRepository db;

    public ManageServiceAdapter(Context ctx, ArrayList<ServiceRequest> srs) {
        this.context = ctx;
        this.srs = srs;
        this.inflater = LayoutInflater.from(ctx);
        this.db = DataRepository.getInstance();
    }

    @Override
    public int getCount() {
        return this.srs.size();
    }

    @Override
    public Object getItem(int i) {
        return srs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        convertView = inflater.inflate(R.layout.manage_service_row, null);
        TextView text1 = (TextView) convertView.findViewById(R.id.serviceText1);
        TextView text2 = (TextView) convertView.findViewById(R.id.serviceText2);
        TextView text3 = (TextView) convertView.findViewById(R.id.serviceText3);
        ImageView catImage = (ImageView) convertView.findViewById(R.id.serviceCatIcon);

        ServiceRequest sr = srs.get(i);
        Vendor vendor = this.db.getVendorByID(sr.getVendorID());
        if (vendor == null)
            text1.setText("No vendor assigned");
        else
            text1.setText(vendor.getName());
        text2.setText(sr.getDate());
        text3.setText(sr.getDesc());
        catImage.setImageResource(R.drawable.appliances);
        return convertView;
    }
}
