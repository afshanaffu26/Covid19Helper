package com.example.covid19helper;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ru.embersoft.expandabletextview.ExpandableTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class GlobalNewsFragment extends Fragment {
    RecyclerView recyclerView;
    FirebaseRecyclerOptions options;
    FirebaseRecyclerAdapter<GlobalNewsModel, GlobalNewsFragment.MyViewHolder> adapter;
    DatabaseReference databaseReference;
    ArrayList<GlobalNewsModel> items = new ArrayList<GlobalNewsModel>();
    int position;
    public GlobalNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_global_news, container, false);

        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setHasFixedSize(true);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("testing");

        loadData();
        return v;
    }
    private void loadData() {
        options = new FirebaseRecyclerOptions.Builder<GlobalNewsModel>()
                .setQuery(databaseReference,GlobalNewsModel.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<GlobalNewsModel, GlobalNewsFragment.MyViewHolder>(options) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void onBindViewHolder(@NonNull GlobalNewsFragment.MyViewHolder viewHolder, int i, @NonNull final GlobalNewsModel globalNewsModel) {
                items.add(new GlobalNewsModel(globalNewsModel.getImageUrl(),globalNewsModel.getTitle(),globalNewsModel.getDesc()));
                position = i;
                viewHolder.txtView.setText(globalNewsModel.getTitle()+"");
                viewHolder.txtViewDesc.setText(globalNewsModel.getDesc()+"");

                // Glide.with(getApplicationContext()).load(globalNewsModel.getImageUrl().toString()).into(viewHolder.imgView);
                Picasso.get().load(globalNewsModel.getImageUrl()).into(viewHolder.imgView);
                viewHolder.txtViewDesc.setOnStateChangeListener(new ExpandableTextView.OnStateChangeListener() {
                    @Override
                    public void onStateChange(boolean isShrink) {
                        GlobalNewsModel contentItem = items.get(position);
                        contentItem.setShrink(isShrink);
                        items.set(position, contentItem);
                    }
                });
                viewHolder.txtViewDesc.setText(globalNewsModel.getDesc()+"");
                viewHolder.txtViewDesc.resetState(globalNewsModel.isShrink());
            }

            @NonNull
            @Override
            public GlobalNewsFragment.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expandable_text_view,parent,false);
                return new GlobalNewsFragment.MyViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
    private class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView txtView;
        ExpandableTextView txtViewDesc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imageView);
            txtView = itemView.findViewById(R.id.txtTitle);
            txtViewDesc = itemView.findViewById(R.id.txtDesc);

        }
    }
}
