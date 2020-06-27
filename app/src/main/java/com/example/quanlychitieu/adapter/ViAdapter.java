package com.example.quanlychitieu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.model.ViTien;

import java.util.List;

public class ViAdapter extends BaseAdapter {
    Context context;
    Integer layout;
    List<ViTien> viList;

    public ViAdapter(Context context, Integer layout, List<ViTien> viList) {
        this.context = context;
        this.layout = layout;
        this.viList = viList;
    }

    public void setViList(List<ViTien> viList) {
        this.viList = viList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return viList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null, false);
        ImageView imageView = convertView.findViewById(R.id.wallet_img);
        TextView textView = convertView.findViewById(R.id.tenthe);
        textView.setText(viList.get(position).getTen());
        TextView tongtien = convertView.findViewById(R.id.tongtien);
        tongtien.setText(viList.get(position).getSodu().toString() + "đ");
        if(viList.get(position).getLoai() == 1) {
            imageView.setImageResource(R.drawable.tien_mat);
        }
        if(viList.get(position).getLoai() == 2) {
            imageView.setImageResource(R.drawable.credit_card);
        }
        return convertView;
    }
}
