package com.societe.anonyme.epandable_listview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    HashMap<String, List<String>> myHeader;
    List<String> myChild;
    ExpandableListView expList;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expList = findViewById(R.id.idListView);
        myHeader = DataProvider.getInfo();
        myChild = new ArrayList<String>(myHeader.keySet());
        adapter = new MyAdapter(this, myHeader, myChild);
        expList.setAdapter(adapter);

    }
}
 class MyAdapter extends BaseExpandableListAdapter {

     private Context ctx;
     private HashMap<String, List<String>> ChildTitles;
     private List<String> HeaderTitles;

     public MyAdapter(Context ctx, HashMap<String, List<String>> ChildTitles, List<String> HeaderTitles) {
         this.ctx = ctx;
         this.ChildTitles = ChildTitles;
         this.HeaderTitles = HeaderTitles;
     }

     @Override
     public int getGroupCount() {
         return HeaderTitles.size();
     }

     @Override
     public int getChildrenCount(int groupPosition) {
         return ChildTitles.get(HeaderTitles.get(groupPosition)).size();
     }

     @Override
     public Object getGroup(int groupPosition) {
         return HeaderTitles.get(groupPosition);
     }

     @Override
     public Object getChild(int groupPosition, int childPosition) {
         return ChildTitles.get(HeaderTitles.get(groupPosition)).get(childPosition);
     }

     @Override
     public long getGroupId(int groupPosition) {
         return groupPosition;
     }

     @Override
     public long getChildId(int groupPosition, int childPosition) {
         return groupPosition;
     }

     @Override
     public boolean hasStableIds() {
         return false;
     }

     @Override
     public View getGroupView(int groupPosition, boolean isExpand, View view, ViewGroup viewGroup) {
         String title = (String) this.getGroup(groupPosition);
         if (view == null) {
             LayoutInflater inflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             view = inflater.inflate(R.layout.custom_header, null);
         }
         TextView txt = (TextView) view.findViewById(R.id.idTitle);
         txt.setTypeface(null, Typeface.BOLD);
         txt.setText(title);
         return view;
     }

     @Override
     public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
         String title = (String) this.getChild(groupPosition, childPosition);
         if (convertView == null) {
             LayoutInflater inflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             convertView = inflater.inflate(R.layout.custom_childitems, null);
         }
         TextView txt = (TextView) convertView.findViewById(R.id.idChild);
         txt.setText(title);
         return convertView;
     }

     @Override
     public boolean isChildSelectable(int groupPosition, int childPosition) {
         return true;
     }
 }
     class DataProvider {
         public static HashMap<String, List<String>> getInfo() {
             HashMap<String, List<String>> HeaderDetails = new HashMap<String, List<String>>();
             List<String> ChildDetails1 = new ArrayList<String>();
             ChildDetails1.add("This is Children 11");
             ChildDetails1.add("This is Children 12");
             ChildDetails1.add("This is Children 13");
             ChildDetails1.add("This is Children 14");
             List<String> ChildDetails2 = new ArrayList<String>();
             ChildDetails2.add("This is Children 21");
             ChildDetails2.add("This is Children 22");
             ChildDetails2.add("This is Children 23");
             ChildDetails2.add("This is Children 24");
             List<String> ChildDetails3 = new ArrayList<String>();
             ChildDetails3.add("This is Children 31");
             ChildDetails3.add("This is Children 32");
             ChildDetails3.add("This is Children 33");
             ChildDetails3.add("This is Children 34");

             HeaderDetails.put("header1", ChildDetails1);
             HeaderDetails.put("header2", ChildDetails2);
             HeaderDetails.put("header3", ChildDetails3);
             return HeaderDetails;
         }
}













