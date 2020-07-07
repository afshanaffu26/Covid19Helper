package com.example.covid19helper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.NumberFormat;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore firebaseFirestore;

    TextView txtActive,txtMild,txtCritical,txtDischarged,txtClosed,txtDeaths,txtMildPt,txtCriticalPt,txtDischargedPt,txtDeathsPt;
    CardView cardViewActive,cardViewClosed;
    //header back button
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseFirestore = FirebaseFirestore.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Covid-19 Cases");
        //display back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cardViewActive = findViewById(R.id.cardView1);
        cardViewClosed = findViewById(R.id.cardView2);
        txtActive = findViewById(R.id.txtActiveCases);
        txtClosed = findViewById(R.id.txtClosedCases);
        txtMild = findViewById(R.id.txtMild);
        txtMildPt = findViewById(R.id.txtMildPt);
        txtCritical = findViewById(R.id.txtCritical);
        txtCriticalPt = findViewById(R.id.txtCriticalPt);
        txtDischarged = findViewById(R.id.txtDischarged);
        txtDischargedPt = findViewById(R.id.txtDischargedPt);
        txtDeaths = findViewById(R.id.txtDeaths);
        txtDeathsPt = findViewById(R.id.txtDeathsPt);

        cardViewActive.setOnClickListener(this);
        cardViewClosed.setOnClickListener(this);
        firebaseFirestore.collection("ActiveAndClosedCases").document("qMw9NJQEvSl4QDPi2wId").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
            //Toast.makeText(getApplicationContext(), "suc", Toast.LENGTH_SHORT).show();
                txtActive.setText(formatNumbers((Long) documentSnapshot.get("active")));
                txtClosed.setText(formatNumbers((Long) documentSnapshot.get("closed")));
                txtMild.setText(formatNumbers((Long) documentSnapshot.get("mild")));
                txtCritical.setText(formatNumbers((Long) documentSnapshot.get("critical")));
                txtDischarged.setText(formatNumbers((Long) documentSnapshot.get("discharged")));
                txtDeaths.setText(formatNumbers((Long) documentSnapshot.get("deaths")));
                txtMildPt.setText("("+documentSnapshot.get("mildPt")+"%)");
                txtCriticalPt.setText("("+documentSnapshot.get("criticalPt")+"%)");
                txtDischargedPt.setText("("+documentSnapshot.get("dischargedPt")+"%)");
                txtDeathsPt.setText("("+documentSnapshot.get("deathsPt")+"%)");
            }
        });

    }
    public String formatNumbers(long num){
        return NumberFormat.getInstance().format(num);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cardView1:
                CovidCasesActivity.isActiveCases = true;
                Intent i = new Intent(getApplicationContext(), CovidCasesActivity.class);
                startActivity(i);
                //overridePendingTransition(0, 0);
                break;
            case R.id.cardView2:
                 CovidCasesActivity.isActiveCases = false;
                 //Intent i = new Intent(getActivity(), CovidCasesActivity.class);
                Intent intent = new Intent(getApplicationContext(), CovidCasesActivity.class);
                startActivity(intent);
//                ((Activity) getActivity()).overridePendingTransition(0, 0);
                break;
        }
    }
}
