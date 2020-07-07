package com.example.covid19helper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_settings, container, false);
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        ImageView imgProfileBtn = v.findViewById(R.id.imgProfileBtn);
        imgProfileBtn.setOnClickListener(this);
        ImageView imgHelplineBtn = v.findViewById(R.id.imgHelplineBtn);
        imgHelplineBtn.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgProfileBtn:
                Intent i = new Intent(getActivity(), ProfileActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
                break;
            case R.id.imgHelplineBtn:
                Intent intent = new Intent(getActivity(), HelplineActivity.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
                break;
        }
    }

}
