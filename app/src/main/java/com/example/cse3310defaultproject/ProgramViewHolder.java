package com.example.cse3310defaultproject;

import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import com.example.cse3310defaultproject.R;

public class ProgramViewHolder
{
    ImageView iView;
    TextView name;
    ProgramViewHolder(View v)
    {
        iView = v.findViewById(R.id.imageView);
        name = v.findViewById(R.id.Cname);
    }
}
