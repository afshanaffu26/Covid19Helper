package com.example.covid19helper;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableTextViewAdapter extends BaseExpandableListAdapter {
    Context context;
    private boolean isGovtGuidelines = false;
    String[] arrTitle;
    String[][] arrDescription;
    public ExpandableTextViewAdapter(Context context) {
        this.context = context;
    }
    public ExpandableTextViewAdapter(String[] arrTitle,String[][] arrDescription,Context context) {
        this.context = context;
        this.arrTitle = arrTitle;
        this.arrDescription = arrDescription;
    }

    public boolean isGovtGuidelines() {
        return isGovtGuidelines;
    }

    public void setGovtGuidelines(boolean govtGuidelines) {
        isGovtGuidelines = govtGuidelines;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return arrTitle.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return arrDescription[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return arrTitle[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arrDescription[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String titleText = (String)getGroup(groupPosition);
        if (convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.title,null); //title.xml
        }
        TextView txtTitle =  convertView.findViewById(R.id.txtTitle);
        txtTitle.setTypeface(null, Typeface.BOLD);
        txtTitle.setText(titleText);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
      final String descText = (String)getChild(groupPosition,childPosition);
      if (convertView==null)
      {
          LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
          convertView = inflater.inflate(R.layout.description,null); //description.xml

      }
        TextView txtDesc =  convertView.findViewById(R.id.txtDesc);
        txtDesc.setText(descText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
