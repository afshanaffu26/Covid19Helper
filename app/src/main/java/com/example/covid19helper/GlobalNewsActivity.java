package com.example.covid19helper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class GlobalNewsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseRecyclerOptions options;
    FirebaseRecyclerAdapter<GlobalNewsModel,MyViewHolder> adapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_news);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("testing");

        loadData();
        
    }

    private void loadData() {
        options = new FirebaseRecyclerOptions.Builder<GlobalNewsModel>()
        .setQuery(databaseReference,GlobalNewsModel.class)
        .build();
        adapter = new FirebaseRecyclerAdapter<GlobalNewsModel, MyViewHolder>(options) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i, @NonNull GlobalNewsModel globalNewsModel) {
                viewHolder.txtView.setText(globalNewsModel.getTitle()+"");
                viewHolder.txtViewDesc.setText(globalNewsModel.getDesc()+"");

                // Glide.with(getApplicationContext()).load(globalNewsModel.getImageUrl().toString()).into(viewHolder.imgView);
                Picasso.get().load(globalNewsModel.getImageUrl()).into(viewHolder.imgView);

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.global_news_single,parent,false);
                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView txtView,txtViewDesc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imageView);
            txtView = itemView.findViewById(R.id.txtTitle);
            txtViewDesc = itemView.findViewById(R.id.txtDesc);

        }
    }
}
