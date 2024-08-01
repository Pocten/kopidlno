package com.example.kopidlno.mapper;

import com.example.kopidlno.dto.DistrictDto;
import com.example.kopidlno.model.District;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DistrictMapper {

    DistrictDto toDto(District district);

    District toEntity(DistrictDto districtDto);
}
