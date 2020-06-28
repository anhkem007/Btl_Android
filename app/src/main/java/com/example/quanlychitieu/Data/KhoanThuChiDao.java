package com.example.quanlychitieu.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quanlychitieu.model.DanhMucThuChi;
import com.example.quanlychitieu.model.KhoanThuChi;
import com.example.quanlychitieu.model.ViTien;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class KhoanThuChiDao extends SQLiteOpenHelper {
    public static String DB_NAME = "qlthuchi";
    public static String TB_NAME = "khoan_thu_chi";
    public static String ID = "id";
    public static String IDVI = "id_vi";
    public static String IDDM = "id_dm";
    public static String TEN = "ten";
    public static String TIEN = "tien";
    public static String LOAI = "loai";
    public static String THOIGIAN = "thoigian";
    public static String GHICHU = "ghichu";

    public static String CREATE_TB = "CREATE TABLE " + TB_NAME + " ( " +
            ID + " INTEGER PRIMARY KEY, " +
            IDVI +  " INTEGER, " +
            IDDM + " INTEGER, " +
            TEN + " TEXT, " +
            TIEN + " REAL, " +
            LOAI + " BIT, " +
            THOIGIAN + " INTEGER, " +
            GHICHU + " TEXT )";




    public static int VERSION = 1;

    DanhMucThuChiDAO danhMucThuChiDAO;
    ViDao viDao;


    public KhoanThuChiDao(Context context) {
        super(context, DB_NAME, null, VERSION );
        viDao = new ViDao(context);
        danhMucThuChiDAO = new DanhMucThuChiDAO(context);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB);

    }

    public void themKhoanThuChi(KhoanThuChi khoanThuChi) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IDVI, khoanThuChi.getViTien().getId());
        contentValues.put(IDDM, khoanThuChi.getDanhMucThuChi().getId());
        contentValues.put(TEN, khoanThuChi.getTen());
        contentValues.put(LOAI, khoanThuChi.getLoai());
        contentValues.put(THOIGIAN, khoanThuChi.getThoigian().getTimeInMillis());
        contentValues.put(GHICHU, khoanThuChi.getGhiChu());
        contentValues.put(TIEN, khoanThuChi.getTien());
        database.insert(TB_NAME, null, contentValues);
    }

    public List<KhoanThuChi> getAll(){
        List<KhoanThuChi> khoanThuChis = new ArrayList<>();
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.query(TB_NAME, null, null, null,null,null, THOIGIAN);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            int idVi = cursor.getInt(1);
            int idDm = cursor.getInt(2);
            String ten = cursor.getString(3);
            boolean loai = false;
            if(cursor.getInt(5)== 1) loai = true;
            long date = cursor.getLong(6);
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(new Date(date));
            } catch (Exception e) {
            }
            Float tien = cursor.getFloat(4);
            String ghichu = cursor.getString(7);
            ViTien viTien = viDao.getViById(idVi);
            DanhMucThuChi danhMucThuChi = danhMucThuChiDAO.getDmById(idDm);
            KhoanThuChi khoanThuChi = new KhoanThuChi(id, viTien, danhMucThuChi, ten, tien, calendar, loai, ghichu);
            khoanThuChis.add(khoanThuChi);
        }
        return khoanThuChis;
    }

    public List<KhoanThuChi> getbyDate(Long startDate, Long endDate){
        List<KhoanThuChi> khoanThuChis = new ArrayList<>();
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.query(TB_NAME, null, THOIGIAN + " >= ? AND " + THOIGIAN + " <= ?" , new String[]{startDate.toString(), endDate.toString()},null,null, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            int idVi = cursor.getInt(1);
            int idDm = cursor.getInt(2);
            String ten = cursor.getString(3);
            boolean loai = false;
            if(cursor.getInt(5)== 1) loai = true;
            Long date = cursor.getLong(6);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(date));
            Float tien = cursor.getFloat(4);
            String ghichu = cursor.getString(7);
            ViTien viTien = viDao.getViById(idVi);
            DanhMucThuChi danhMucThuChi = danhMucThuChiDAO.getDmById(idDm);
            KhoanThuChi khoanThuChi = new KhoanThuChi(id, viTien, danhMucThuChi, ten, tien, calendar, loai, ghichu);
            khoanThuChis.add(khoanThuChi);
        }
        return khoanThuChis;
    }

    public List<KhoanThuChi> getbyDate(Long startDate, Long endDate, Integer loaitc){
        List<KhoanThuChi> khoanThuChis = new ArrayList<>();
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor;
        if(loaitc == 2){
            cursor = database.query(TB_NAME, null, THOIGIAN + " >= ? AND " + THOIGIAN + " <= ?" , new String[]{startDate.toString(), endDate.toString()},null,null, null);
        } else {
            cursor = database.query(TB_NAME, null, THOIGIAN + " >= ? AND " + THOIGIAN + " <= ? AND " +LOAI + " =?" , new String[]{startDate.toString(), endDate.toString(), loaitc.toString()},null,null, null);
        }
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            int idVi = cursor.getInt(1);
            int idDm = cursor.getInt(2);
            String ten = cursor.getString(3);
            boolean loai = false;
            if(cursor.getInt(5)== 1) loai = true;
            Long date = cursor.getLong(6);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(date));
            Float tien = cursor.getFloat(4);
            String ghichu = cursor.getString(7);
            ViTien viTien = viDao.getViById(idVi);
            DanhMucThuChi danhMucThuChi = danhMucThuChiDAO.getDmById(idDm);
            KhoanThuChi khoanThuChi = new KhoanThuChi(id, viTien, danhMucThuChi, ten, tien, calendar, loai, ghichu);
            khoanThuChis.add(khoanThuChi);
        }
        return khoanThuChis;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
