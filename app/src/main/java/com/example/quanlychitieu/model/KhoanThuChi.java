package com.example.quanlychitieu.model;

public class KhoanThuChi {
    private Integer id;
    private Integer idVi;       // sử dụng ví nào để thanh toán id ViTien
    private Integer idDanhMuc;  // id DanhMucThuChi
    private String ten;
    private Float tien;
    private Boolean loai;       // 0 = thu, 1 = chi;
    private String ghiChu;

    public KhoanThuChi(Integer id, Integer idVi, Integer idDanhMuc, String ten, Float tien, Boolean loai, String ghiChu) {
        this.id = id;
        this.idVi = idVi;
        this.idDanhMuc = idDanhMuc;
        this.ten = ten;
        this.tien = tien;
        this.loai = loai;
        this.ghiChu = ghiChu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdVi() {
        return idVi;
    }

    public void setIdVi(Integer idVi) {
        this.idVi = idVi;
    }

    public Integer getIdDanhMuc() {
        return idDanhMuc;
    }

    public void setIdDanhMuc(Integer idDanhMuc) {
        this.idDanhMuc = idDanhMuc;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Float getTien() {
        return tien;
    }

    public void setTien(Float tien) {
        this.tien = tien;
    }

    public Boolean getLoai() {
        return loai;
    }

    public void setLoai(Boolean loai) {
        this.loai = loai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
