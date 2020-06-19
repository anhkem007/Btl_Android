package com.example.quanlychitieu.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quanlychitieu.model.DanhMucThuChi;

public class DanhMucThuChiDAO extends SQLiteOpenHelper {


    private Context context;
    public DanhMucThuChiDAO(Context context) {
        super(context, "qlthuchi", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
