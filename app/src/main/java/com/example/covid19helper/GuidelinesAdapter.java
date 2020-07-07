package com.example.covid19helper;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class GuidelinesAdapter extends RecyclerView.Adapter<GuidelinesAdapter.GuidelinesViewHolder> {

    List<Guidelines> guidelinesList;

    public GuidelinesAdapter(List<Guidelines> guidelinesList) {
        this.guidelinesList = guidelinesList;
    }

    @NonNull
    @Override
    public GuidelinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guidelines,parent,false);
        return new GuidelinesViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return guidelinesList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull GuidelinesViewHolder holder, int position) {
        Guidelines guidelines = guidelinesList.get(position);
        holder.txtTitle.setText(guidelines.getTitle());
        holder.txtExpDesc.setText(guidelines.getDescription());
        boolean isExpandable = guidelinesList.get(position).isExpandable();
        holder.txtExpDesc.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
    }

    public class GuidelinesViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle,txtExpDesc;
        LinearLayout linearLayout;
        RelativeLayout relativeLayout;
        public GuidelinesViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtExpDesc = itemView.findViewById(R.id.txtDesc);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            relativeLayout = itemView.findViewById(R.id.relative_layout);

            linearLayout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Guidelines guidelines = guidelinesList.get(getAdapterPosition());
                    guidelines.setExpandable(!guidelines.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
