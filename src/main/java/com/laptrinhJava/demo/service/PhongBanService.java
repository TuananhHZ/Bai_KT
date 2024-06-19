package com.laptrinhJava.demo.service;

import com.laptrinhJava.demo.Model.Phongban;
import com.laptrinhJava.demo.repository.PhongbanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhongBanService {

    @Autowired
    private PhongbanRepository phongbanRepository;


    public List<Phongban> getAllPhongban() {
        return phongbanRepository.findAll();
    }

    public Optional<Phongban> getPhongbanById(Long id) {
        return phongbanRepository.findById(id);
    }

    public Phongban addPhongban(Phongban phongban) {
        return phongbanRepository.save(phongban);
    }

    public Phongban updatePhongban(Long id, Phongban updatedPhongban) {
        return phongbanRepository.findById(id)
                .map(phongban -> {
                    phongban.setName(updatedPhongban.getName());
                    return phongbanRepository.save(phongban);
                })
                .orElseThrow(() -> new IllegalArgumentException("Invalid Phongban Id: " + id));
    }

    public void deletePhongbanById(Long id) {
        phongbanRepository.deleteById(id);
    }
}