package com.example.kopidlno.service;

import com.example.kopidlno.dto.DistrictDto;

import java.util.List;

public interface DistrictService {
    List<DistrictDto> getAllDistricts();
    void saveAll(List<DistrictDto> districtDtos);
}
