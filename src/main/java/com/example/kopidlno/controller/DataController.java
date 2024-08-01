package com.example.kopidlno.controller;

import com.example.kopidlno.dto.DistrictPartDto;
import com.example.kopidlno.dto.DistrictDto;
import com.example.kopidlno.service.DistrictPartService;
import com.example.kopidlno.service.DataService;
import com.example.kopidlno.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DataController {
    private final DataService dataService;
    private final DistrictService districtService;
    private final DistrictPartService districtPartService;

    @GetMapping("/process-data")
    public String processData() {
        try {
            dataService.processData();
            return "Data processed successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error processing data";
        }
    }

    @GetMapping("/districts")
    public List<DistrictDto> getAllDistricts() {
        return districtService.getAllDistricts();
    }

    @GetMapping("/district-parts")
    public List<DistrictPartDto> getAllDistrictParts() {
        return districtPartService.getAllDistrictParts();
    }
}
