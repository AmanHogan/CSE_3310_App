package com.example.cse3310defaultproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Random;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class Plumbing_Fragment extends Fragment {


    Random ran = new Random();
    int upp = 20;
    int low = 0;
    int ranint = (int)Math.floor(Math.random()*(upp-low+1)+low);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.plumb_layout, container, false);
    }

    public int getList()
    {
        return ranint;
    }
}
