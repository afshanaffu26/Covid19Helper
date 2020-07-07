package com.example.covid19helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.NumberFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorldFragment extends Fragment implements View.OnClickListener {
    TextView txtTotal;
    private FirebaseFirestore firebaseFirestore;

    public WorldFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_world, container, false);
        Button btnViewStats = v.findViewById(R.id.btnViewStats);
        btnViewStats.setOnClickListener(this);
        txtTotal = v. findViewById(R.id.txtTotal);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("ActiveAndClosedCases").document("qMw9NJQEvSl4QDPi2wId").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                txtTotal.setText(formatNumbers((Long) documentSnapshot.get("total")));
            }
        });

        return v;
    }
    public String formatNumbers(long num){
        return NumberFormat.getInstance().format(num);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnViewStats:
                startActivity(new Intent(getActivity(),HomeActivity.class));
                break;
        }
    }
}
