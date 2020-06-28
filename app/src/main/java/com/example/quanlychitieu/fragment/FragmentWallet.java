package com.example.quanlychitieu.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.quanlychitieu.Data.ViDao;
import com.example.quanlychitieu.MainActivity;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapter.ViAdapter;
import com.example.quanlychitieu.model.ViTien;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentWallet extends Fragment {
    public static List<ViTien> viTienList;
    ListView listvi;
    ViAdapter viAdapter;
    ImageView themvi;
    View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view != null) return view;
        view = inflater.inflate(R.layout.fragment_wallet, container, false);
        anhxa(view);
        viTienList = MainActivity.viDao.getAllVi();
        viAdapter = new ViAdapter(getContext(), R.layout.item_list_vi, viTienList);
        listvi.setAdapter(viAdapter);
        return view;
    }

    public void anhxa(View view) {
        themvi = view.findViewById(R.id.themvi);
        themvi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupThemVi();
            }
        });
        listvi = (ListView) view.findViewById(R.id.list_vi);
    }

    public void showPopupThemVi() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.popup_them_vi);
        final EditText edttenvi = dialog.findViewById(R.id.edttenvi);
        final EditText sodu = dialog.findViewById(R.id.edtsodu);
        final Spinner spinner = dialog.findViewById(R.id.spnloaivi);
        spinner.setAdapter(new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, new String[]{"vi","the ngan hang"}));
        Button btnLuu = (Button) dialog.findViewById(R.id.btnluu);
        Button btnhuy = (Button) dialog.findViewById(R.id.btnthoat);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edttenvi.getText().toString();
                Float sd = Float.parseFloat(sodu.getText().toString());
                int loai = spinner.getSelectedItemPosition() + 1;
                ViTien viTien = new ViTien(1, ten, sd, loai);
                MainActivity.viDao.themVi(viTien);
                viTienList = MainActivity.viDao.getAllVi();
                viAdapter.setViList(viTienList);
                dialog.dismiss();
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
