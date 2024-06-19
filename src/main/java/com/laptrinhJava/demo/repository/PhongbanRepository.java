package com.laptrinhJava.demo.repository;

import com.laptrinhJava.demo.Model.Phongban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhongbanRepository extends JpaRepository<Phongban, Long> {
}
