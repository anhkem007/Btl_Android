package com.example.quanlychitieu.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlychitieu.Data.DanhMucThuChiDAO;
import com.example.quanlychitieu.Data.KhoanThuChiDao;
import com.example.quanlychitieu.Data.ViDao;
import com.example.quanlychitieu.MainActivity;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapter.DanhMucAdapter;
import com.example.quanlychitieu.adapter.ViAdapter;
import com.example.quanlychitieu.model.DanhMucThuChi;
import com.example.quanlychitieu.model.KhoanThuChi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentAdd extends Fragment {
    KhoanThuChi khoanThuChi;
    EditText edtDate, edtTen, edtSoTien, edtGhiChu;
    Spinner spnthuchi;
    Button btndate, btnluu;
    View chondanhmuc, chonVi;
    ImageView imgdanhmuc, imgVi;
    TextView txttendanhmuc, txttenvi;
    View view;
    Calendar calendar;
    List<DanhMucThuChi> danhMucThuChiList; // danh sach hang muc hien thi
    DanhMucThuChiDAO danhMucThuChiDAO; // truy cap toi co so du lieu
    KhoanThuChiDao khoanThuChiDao;
    ViDao viDao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view != null) return view;
        view = inflater.inflate(R.layout.fragment_add, container, false);
        danhMucThuChiDAO = new DanhMucThuChiDAO(getContext());
        danhMucThuChiList = danhMucThuChiDAO.loadAllChi();
        khoanThuChiDao = new KhoanThuChiDao(getContext());
        viDao = new ViDao(getContext());
        anhXa(view);
        return view;
    }

    public void anhXa(View view) {
        khoanThuChi = new KhoanThuChi();
        edtTen = view.findViewById(R.id.tenkhoanthuchi);
        edtSoTien = view.findViewById(R.id.sotien);
        edtGhiChu = view.findViewById(R.id.edtghichu);
        calendar = Calendar.getInstance();
        khoanThuChi.setThoigian(calendar);
        edtDate = view.findViewById(R.id.date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        edtDate.setText(simpleDateFormat.format(calendar.getTime()));
        btndate = view.findViewById(R.id.btndate);
        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        spnthuchi = view.findViewById(R.id.spinner_thu_chi);
        imgdanhmuc = view.findViewById(R.id.imgdanhmucadd);
        imgVi = view.findViewById(R.id.imgloaivi);
        txttenvi = view.findViewById(R.id.txttenvi);
        txttendanhmuc = view.findViewById(R.id.txttendanhmuc);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.drop_simple, new String[]{getString(R.string.thu), getString(R.string.chi)});
        spnthuchi.setAdapter(arrayAdapter);
        chondanhmuc = view.findViewById(R.id.chonhangmuc);
        chonVi = view.findViewById(R.id.chonvi);
        chondanhmuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupChonDanhMuc();
            }
        });
        chonVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupChonVi();
            }
        });
        btnluu = view.findViewById(R.id.btnluukhoanthuchi);
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khoanThuChi.setTen(edtTen.getText().toString());
                khoanThuChi.setGhiChu(edtGhiChu.getText().toString());
                khoanThuChi.setTien(Float.parseFloat(edtSoTien.getText().toString()));
                khoanThuChi.setLoai(true);
                khoanThuChiDao.themKhoanThuChi(khoanThuChi);
                MainActivity mainActivity = (MainActivity) getContext();
                Toast.makeText(mainActivity,mainActivity.getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();
                mainActivity.addFragmentAdd();
            }
        });
    }
    public void showPopupChonDanhMuc(){
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.popup_list_danh_muc);
        ListView listView = dialog.findViewById(R.id.listdm);
        DanhMucAdapter danhMucAdapter = new DanhMucAdapter(getContext(), R.layout.item_list_danh_muc, danhMucThuChiList);
        listView.setAdapter(danhMucAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imgdanhmuc.setImageBitmap(danhMucThuChiList.get(position).getHinhanh());
                txttendanhmuc.setText(danhMucThuChiList.get(position).getTen());
                khoanThuChi.setDanhMucThuChi(danhMucThuChiList.get(position));
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                khoanThuChi.setThoigian(calendar);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    public void showPopupChonVi() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.popup_list_vi);
        ListView listView = dialog.findViewById(R.id.listvi);
        if(FragmentWallet.viTienList == null) {
            FragmentWallet.viTienList = viDao.getAllVi();
        }
        ViAdapter viAdapter = new ViAdapter(getContext(), R.layout.item_list_vi, FragmentWallet.viTienList);
        listView.setAdapter(viAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               khoanThuChi.setViTien(FragmentWallet.viTienList.get(position));
               txttenvi.setText(FragmentWallet.viTienList.get(position).getTen());
               if(FragmentWallet.viTienList.get(position).getLoai() == 2) {
                   imgVi.setImageResource(R.drawable.credit_card);
               } else imgVi.setImageResource(R.drawable.tien_mat);
               dialog.dismiss();

            }
        });
        dialog.show();
    }
}
