package com.example.cse3310defaultproject;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Random;

import com.example.cse3310defaultproject.data.DataRepository;
import com.example.cse3310defaultproject.data.model.ServiceRequest;
import com.example.cse3310defaultproject.data.model.Vendor;

public class CustomListView extends ArrayAdapter
{
    //private ArrayList<String> Services;
    private ArrayList<Integer> imgID;
    Random rand = new Random();
    String name;
    public ArrayList<ServiceRequest> history;
    public ArrayList<Vendor> vendors;
    final private Activity context;
    DataRepository db = DataRepository.getInstance();

    int i = 0;
    public CustomListView(Activity context, ArrayList<ServiceRequest> services, ArrayList<Integer> imgID) {
        super(context, R.layout.order_history_entry,R.id.Cname,services);
        this.context = context;
        this.imgID = imgID;
        this.history = services;
    }
    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent)
    {
        vendors = db.getVendors();
        for(int j = 0; j < vendors.size(); j++)
        {
            if(history.get(position).getServiceCategory() == vendors.get(j).getServiceCategory())
            {
                name = vendors.get(j).getName();
            }
        }

        View singleItem = convertView;
        ProgramViewHolder holder = null;
        if(singleItem == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.inflate(R.layout.order_history_entry,parent,false);
            holder = new ProgramViewHolder(singleItem);
            singleItem.setTag(holder);
        }
        else
        {
            holder = (ProgramViewHolder) singleItem.getTag();
        }
        holder.iView.setImageResource(imgID.get(history.get(position).getServiceCategory().ordinal()));
        holder.name.setText(name);

        return singleItem;
    }
}
