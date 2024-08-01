package com.example.kopidlno.service.implementation;

import com.example.kopidlno.dto.DistrictPartDto;
import com.example.kopidlno.mapper.DistrictPartMapper;
import com.example.kopidlno.model.DistrictPart;
import com.example.kopidlno.repository.DistrictPartRepository;
import com.example.kopidlno.service.DistrictPartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictPartServiceImpl implements DistrictPartService {

    private final DistrictPartRepository districtPartRepository;
    private final DistrictPartMapper districtPartMapper;

    @Override
    public List<DistrictPartDto> getAllDistrictParts() {
        List<DistrictPart> districtParts = districtPartRepository.findAll();
        return districtParts.stream()
                .map(districtPartMapper::toDto)
                .toList();
    }

    @Override
    public void saveAll(List<DistrictPartDto> districtPartDtos) {
        List<DistrictPart> districtParts = districtPartDtos.stream()
                .map(districtPartMapper::toEntity)
                .toList();
        districtPartRepository.saveAll(districtParts);
    }
}
