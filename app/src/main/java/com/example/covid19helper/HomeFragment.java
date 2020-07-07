package com.example.covid19helper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
TextView txtAbout;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        String about = "COVID-19 has been declared a global pandemic.\n\n" +
                "Coronaviruses are a large family of viruses. Some cause illness in people and others cause illness in animals. Human coronaviruses are common and are typically associated with mild illnesses, similar to the common cold.\n\n" +
                "There have been 2 other specific coronaviruses that have spread from animals to humans and which have caused severe illness in humans. These are the:\n\n" +
                "1.\tSevere acute respiratory syndrome coronavirus (SARS CoV)\n" +
                "2.\tMiddle East respiratory syndrome coronavirus (MERS CoV)\n\n" +
                "The disease caused by the new coronavirus has been named COVID-19.\n";
            txtAbout = v.findViewById(R.id.txtAbout);
            txtAbout.setText(about);
        return v;
    }
}
