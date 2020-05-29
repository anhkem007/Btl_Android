package com.example.quanlychitieu;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class NavAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Integer> list;
    ArrayList<View> viewList;

    public NavAdapter(Context context, int layout, ArrayList<Integer> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        viewList = new ArrayList<>();
        viewList.add(null);
        viewList.add(null);
        viewList.add(null);
        viewList.add(null);
        viewList.add(null);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public void reset(){
        for(View view : viewList){
            view.setBackgroundColor(Color.TRANSPARENT);

        }

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(viewList.get(position) !=null) {
            return viewList.get(position);
        }
        LayoutInflater inflater =(LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, parent , false);
        ImageView imageView = convertView.findViewById(R.id.imageView);
        imageView.setImageResource(list.get(position));
        viewList.set(position,convertView);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                v.setBackgroundColor(Color.RED);
            }
        });
        return convertView;
    }
}
