package com.example.kopidlno.repository;

import com.example.kopidlno.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    District findByCode(int code);
}
