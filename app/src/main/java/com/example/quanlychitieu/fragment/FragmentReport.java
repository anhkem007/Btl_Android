package com.example.quanlychitieu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanlychitieu.MainActivity;
import com.example.quanlychitieu.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentReport extends Fragment {
    View lsthuchi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_report, container, false);
        anhXa(view);
        return view;
    }
    public void anhXa(View view) {
        lsthuchi = view.findViewById(R.id.bclstc);
        lsthuchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getContext();
                mainActivity.addFragmentLsthuchi();
            }
        });
    }
}
