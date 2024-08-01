package com.example.kopidlno.service.implementation;

import com.example.kopidlno.dto.DistrictDto;
import com.example.kopidlno.mapper.DistrictMapper;
import com.example.kopidlno.model.District;
import com.example.kopidlno.repository.DistrictRepository;
import com.example.kopidlno.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;

    @Override
    public List<DistrictDto> getAllDistricts() {
        List<District> districts = districtRepository.findAll();
        return districts.stream()
                .map(districtMapper::toDto)
                .toList();
    }

    @Override
    public void saveAll(List<DistrictDto> districtDtos) {
        List<District> districts = districtDtos.stream()
                .map(districtMapper::toEntity)
                .toList();
        districtRepository.saveAll(districts);
    }
}
