package com.example.quanlychitieu.model;

public class DanhMucThuChi {
    private Integer id;
    private String ten;
    private Integer hinhanh;
    private Boolean loai;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(Integer hinhanh) {
        this.hinhanh = hinhanh;
    }

    public Boolean getLoai() {
        return loai;
    }

    public void setLoai(Boolean loai) {
        this.loai = loai;
    }

    public DanhMucThuChi(Integer id, String ten, Integer hinhanh, Boolean loai) {
        this.id = id;
        this.ten = ten;
        this.hinhanh = hinhanh;
        this.loai = loai;
    }
}
