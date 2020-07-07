package com.example.covid19helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.embersoft.expandabletextview.ExpandableTextView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<ItemExpandableTextView> items;
    private Context context;
    public Adapter(ArrayList<ItemExpandableTextView> items, Context context){
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expandable_text_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, final int position) {
        final ItemExpandableTextView item = items.get(position);
        holder.imageView.setImageResource(item.getImgResource());
        holder.txtTitle.setText(item.getTitle());
        holder.expTextDesc.setText(item.getDesc());
        holder.expTextDesc.setOnStateChangeListener(new ExpandableTextView.OnStateChangeListener() {
            @Override
            public void onStateChange(boolean isShrink) {
                ItemExpandableTextView contentItem = items.get(position);
                contentItem.setShrink(isShrink);
                items.set(position, contentItem);
            }
        });
        holder.expTextDesc.setText(item.getDesc());
        holder.expTextDesc.resetState(item.isShrink());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        ExpandableTextView expTextDesc;
        TextView txtTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            expTextDesc = itemView.findViewById(R.id.txtDesc);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}
