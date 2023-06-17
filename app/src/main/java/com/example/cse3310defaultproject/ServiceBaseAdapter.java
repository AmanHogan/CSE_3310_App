package com.example.cse3310defaultproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cse3310defaultproject.data.model.ServiceCategory;
import com.example.cse3310defaultproject.data.model.Vendor;

import java.util.ArrayList;

public class ServiceBaseAdapter extends BaseAdapter {

    Context context;

    ArrayList<Vendor> vendors;

//    String [] listService;
//    int [] listIcons = {R.drawable.plumbing, R.drawable.electrical, R.drawable.study, R.drawable.appliances, R.drawable.cleaning};
//    String [] distantAway;
//    String [] rating;
    LayoutInflater inflater;


    public ServiceBaseAdapter(Context ctx, ArrayList<Vendor> vendors)
    {
        this.context = ctx;
//        this.listService = ServiceList;
//        this.distantAway = distaway;
//        this.rating = starRating;
        this.vendors = vendors;
        inflater = LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {
        return this.vendors.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private void setCatImage(ImageView serviceImg, int position) {

        ServiceCategory cat = this.vendors.get(position).getServiceCategory();

        if (cat.equals(ServiceCategory.Appliances)) {
            serviceImg.setImageResource(R.drawable.appliances);
        }
        if (cat.equals(ServiceCategory.Tutoring)) {
            serviceImg.setImageResource(R.drawable.study);
        }
        if (cat.equals(ServiceCategory.HomeCleaning)) {
            serviceImg.setImageResource(R.drawable.cleaning);
        }
        if (cat.equals(ServiceCategory.Plumbing)) {
            serviceImg.setImageResource(R.drawable.plumbing);
        }
        if (cat.equals(ServiceCategory.Electrical)) {
            serviceImg.setImageResource(R.drawable.electrical);
        }
        if (cat.equals(ServiceCategory.Moving)) {
            serviceImg.setImageResource(R.drawable.moving);
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_find_service_list_view, null);
        TextView vendorName = (TextView) convertView.findViewById(R.id.vendorName);
        TextView serviceCategory = (TextView) convertView.findViewById(R.id.serviceCategory);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);

        ImageView serviceImg = (ImageView) convertView.findViewById(R.id.imageIcon);
        setCatImage(serviceImg, position);

        vendorName.setText(vendors.get(position).getName());
        serviceCategory.setText(vendors.get(position).getServiceCategory().toString());

        rating.setText(String.valueOf(vendors.get(position).getRating()) + " stars");
        return convertView;
    }
}
