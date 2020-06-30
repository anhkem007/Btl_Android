package com.example.quanlychitieu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.quanlychitieu.Data.DanhMucThuChiDAO;
import com.example.quanlychitieu.Data.KhoanThuChiDao;
import com.example.quanlychitieu.Data.ViDao;
import com.example.quanlychitieu.fragment.FragmentAdd;
import com.example.quanlychitieu.fragment.FragmentHome;
import com.example.quanlychitieu.fragment.FragmentReport;
import com.example.quanlychitieu.fragment.FragmentReportBdtc;
import com.example.quanlychitieu.fragment.FragmentReportHm;
import com.example.quanlychitieu.fragment.FragmentReportLstc;
import com.example.quanlychitieu.fragment.FragmentUser;
import com.example.quanlychitieu.fragment.FragmentWallet;
import com.example.quanlychitieu.model.DanhMucThuChi;
import com.example.quanlychitieu.model.KhoanThuChi;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment home, wallet, user, add, report, fragmentlstc, fragmenthm, fragmentBdtc;
    public static DanhMucThuChiDAO danhMucThuChiDAO;
    public static ViDao viDao;
    public static KhoanThuChiDao khoanThuChiDao;
    public String tagback;
    public String tagPresent;
    Fragment fragment;
    public static List<DanhMucThuChi> danhMucThuChis, danhMucChi, danhMucThu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        danhMucThuChiDAO = new DanhMucThuChiDAO(this);
        danhMucThuChiDAO.getWritableDatabase();
        danhMucThuChis = danhMucThuChiDAO.loadAll();
        danhMucThu = danhMucThuChiDAO.loadAllthu();
        danhMucChi = danhMucThuChiDAO.loadAllChi();
        viDao = new ViDao(this);
        khoanThuChiDao = new KhoanThuChiDao(this);
        try {
            long b = new SimpleDateFormat("dd/MM/yyyy").parse("1/6/2020").getTime();
            long a = new SimpleDateFormat("dd/MM/yyyy").parse("1/7/2020").getTime();
            Date d = new SimpleDateFormat("dd/MM/yyyy").parse("1/7/2020");
            khoanThuChiDao.getDataset(b,a,1);
        } catch (Exception ex) {

        }

        fragmentlstc = new FragmentReportLstc();
        fragmentBdtc = new FragmentReportBdtc();
        fragmenthm = new FragmentReportHm();
        home = new FragmentHome();
        add = new FragmentAdd();
        user = new FragmentUser();
        wallet = new FragmentWallet();
        report = new FragmentReport();
        fragment = home;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home :
                        fragment = home;
                        tagPresent = FragmentHome.class.getName();
                        break;
                    case R.id.wallet :
                        fragment = wallet;
                        tagPresent = FragmentWallet.class.getName();
                        break;
                    case R.id.user :
                        fragment = user;
                        break;
                    case R.id.add :
                        fragment = add;
                        break;
                    case R.id.report :
                        fragment = report;
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName()).show(fragment).commit();
                return true;
            }
        });
    }
    public void addFragmentAdd(){
        add = new FragmentAdd();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, add).commit();
    }
    public void addFragmentLsthuchi(){
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new FragmentReportLstc(), fragmentlstc.getClass().getSimpleName()).hide(fragment).show(fragmentlstc).addToBackStack(fragment.getClass().getSimpleName()).commit();
    }
    public void addFragmentbcHangmuc(){
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragmenthm, fragmenthm.getClass().getSimpleName()).hide(fragment).show(fragmenthm).addToBackStack(fragment.getClass().getSimpleName()).commit();
    }
    public void addFragmentbdtc(){
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragmentBdtc, fragmentBdtc.getClass().getSimpleName()).hide(fragment).show(fragmentBdtc).addToBackStack(fragment.getClass().getSimpleName()).commit();
    }
}
