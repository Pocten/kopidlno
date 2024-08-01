package com.example.kopidlno.service;

import com.example.kopidlno.dto.DistrictPartDto;

import java.util.List;

public interface DistrictPartService {
    List<DistrictPartDto> getAllDistrictParts();
    void saveAll(List<DistrictPartDto> districtPartDtos);
}
