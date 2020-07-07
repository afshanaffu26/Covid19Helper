package com.example.covid19helper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.NumberFormat;

public class CovidCasesActivity extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public static boolean isActiveCases = false;
    FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;
    RecyclerView recyclerView;
    TextView h_criticalordeath,h_mildorrecovered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_cases);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Covid-19 Cases");
        //display back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        h_criticalordeath = findViewById(R.id.h_criticalordeath);
        h_mildorrecovered = findViewById(R.id.h_mildorrecovered);

        if (isActiveCases)
        {
            h_mildorrecovered.setText("Mild");
            h_criticalordeath.setText("Serious/Critical");
        }
        else
        {
            h_mildorrecovered.setText("Recovered/Discharged");
            h_criticalordeath.setText("Deaths");
        }
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);

        //Query
        Query query = firebaseFirestore.collection("Countrywise");

        //RecyclerOptions
        FirestoreRecyclerOptions<CountrywiseModel> options = new FirestoreRecyclerOptions.Builder<CountrywiseModel>()
                .setQuery(query,CountrywiseModel.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<CountrywiseModel, CountrywiseViewHolder>(options) {
            @NonNull
            @Override
            public CountrywiseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_countrywise,parent,false);
                return new CountrywiseViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull CountrywiseViewHolder holder, int position, @NonNull CountrywiseModel model) {
                holder.list_country.setText(model.getCountry());
                if(isActiveCases) {
                    holder.list_criticalordeath.setText(formatNumbers(model.getCritical() + ""));
                    holder.list_mildorrecovered.setText(formatNumbers(model.getMild() + ""));
                }
                else
                {
                    holder.list_criticalordeath.setText(formatNumbers(model.getDeath() + ""));
                    holder.list_mildorrecovered.setText(formatNumbers(model.getRecovered() + ""));
                }

            }
        };

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    public String formatNumbers(String s){
        long num = Long.parseLong(s);
        return NumberFormat.getInstance().format(num);
    }

    private class CountrywiseViewHolder extends RecyclerView.ViewHolder {

        private TextView list_country,list_mildorrecovered,list_criticalordeath;

        public CountrywiseViewHolder(@NonNull View itemView) {
            super(itemView);
            list_country = itemView.findViewById(R.id.country);
            list_criticalordeath = itemView.findViewById(R.id.criticalordeath);
            list_mildorrecovered = itemView.findViewById(R.id.mildorrecovered);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
