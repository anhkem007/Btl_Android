package com.example.quanlychitieu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.quanlychitieu.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentAdd extends Fragment {
    Spinner spnthuchi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_add, container, false);
        anhXa(view);
        return view;
    }
    public void anhXa(View view){
        spnthuchi = view.findViewById(R.id.spinner_thu_chi);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.drop_simple, new String[]{getString(R.string.thu), getString(R.string.chi)});
        spnthuchi.setAdapter(arrayAdapter);
    }
}
