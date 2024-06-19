package com.laptrinhJava.demo.service;

import com.laptrinhJava.demo.Model.Nhanvien;
import com.laptrinhJava.demo.repository.NhanvienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhanvienService {

    private final NhanvienRepository nhanvienRepository;

    @Autowired
    public NhanvienService(NhanvienRepository nhanvienRepository) {
        this.nhanvienRepository = nhanvienRepository;
    }

    public List<Nhanvien> getAllNhanvien() {
        return nhanvienRepository.findAll();
    }

    public Optional<Nhanvien> getNhanvienById(Long id) {
        return nhanvienRepository.findById(id);
    }

    public Nhanvien addNhanvien(Nhanvien nhanvien) {
        return nhanvienRepository.save(nhanvien);
    }

    public Nhanvien updateNhanvien(Nhanvien updatedNhanvien) {
        return nhanvienRepository.findById(updatedNhanvien.getId())
                .map(nhanvien -> {
                    nhanvien.setName(updatedNhanvien.getName());
                    nhanvien.setGt(updatedNhanvien.getGt());
                    nhanvien.setNoisinh(updatedNhanvien.getNoisinh());
                    nhanvien.setMaphong(updatedNhanvien.getMaphong());
                    nhanvien.setLuong(updatedNhanvien.getLuong());
                    nhanvien.setPhongban(updatedNhanvien.getPhongban());
                    return nhanvienRepository.save(nhanvien);
                })
                .orElseThrow(() -> new IllegalArgumentException("Invalid Nhanvien Id: " + updatedNhanvien.getId()));
    }

    public void deleteNhanvienById(Long id) {
        nhanvienRepository.deleteById(id);
    }
}