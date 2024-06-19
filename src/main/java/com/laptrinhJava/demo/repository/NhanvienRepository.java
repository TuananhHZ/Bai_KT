package com.laptrinhJava.demo.repository;

import com.laptrinhJava.demo.Model.Nhanvien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanvienRepository extends JpaRepository<Nhanvien, Long> {
}