package com.laptrinhJava.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhanvien")
public class Nhanvien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gt;

    public String getNoisinh() {
        return noisinh;
    }

    public void setNoisinh(String noisinh) {
        this.noisinh = noisinh;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaphong() {
        return maphong;
    }

    public void setMaphong(String maphong) {
        this.maphong = maphong;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public Phongban getPhongban() {
        return phongban;
    }

    public void setPhongban(Phongban phongban) {
        this.phongban = phongban;
    }

    private String noisinh;
    private String maphong;
    private int luong;
    @ManyToOne
    @JoinColumn(name = "maphong_id")
    private Phongban phongban;

}