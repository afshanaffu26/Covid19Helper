package com.example.covid19helper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HelplineActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imgCall1,imgCall2,imgCall3,imgCall4,imgCall5;
    TextView txtCall1,txtCall2,txtCall3,txtCall4,txtCall5;
    String number;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Helpline Numbers");
        //display back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgCall1 = findViewById(R.id.imgCall1);
        imgCall2 = findViewById(R.id.imgCall2);
        imgCall3 = findViewById(R.id.imgCall3);
        imgCall4 = findViewById(R.id.imgCall4);
        imgCall5 = findViewById(R.id.imgCall5);

        txtCall1 = findViewById((R.id.txtPhone1));
        txtCall2 = findViewById((R.id.txtPhone2));
        txtCall3 = findViewById((R.id.txtPhone3));
        txtCall4 = findViewById((R.id.txtPhone4));
        txtCall5 = findViewById((R.id.txtPhone5));

        imgCall1.setOnClickListener(this);
        imgCall2.setOnClickListener(this);
        imgCall3.setOnClickListener(this);
        imgCall4.setOnClickListener(this);
        imgCall5.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imgCall1:
                number = txtCall1.getText().toString();
                callToHelplineNumber(number);
                break;
            case R.id.imgCall2:
                number = txtCall2.getText().toString();
                callToHelplineNumber(number);
                break;
            case R.id.imgCall3:
                number = txtCall3.getText().toString();
                callToHelplineNumber(number);
                break;
            case R.id.imgCall4:
                number = txtCall4.getText().toString();
                callToHelplineNumber(number);
                break;
            case R.id.imgCall5:
                number = txtCall5.getText().toString();
                callToHelplineNumber(number);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void callToHelplineNumber(String number) {
        Intent intentCall = new Intent(Intent.ACTION_CALL);
        intentCall.setData(Uri.parse("tel:"+number));
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(getApplicationContext(),"Please grant permission",Toast.LENGTH_SHORT).show();
            requestPermissions();
        }
        else
        {
            startActivity(intentCall);
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(HelplineActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }
}
