package com.example.kopidlno.mapper;

import com.example.kopidlno.dto.DistrictPartDto;
import com.example.kopidlno.model.DistrictPart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DistrictMapper.class})
public interface DistrictPartMapper {

    @Mapping(source = "district", target = "district")
    DistrictPartDto toDto(DistrictPart districtPart);

    @Mapping(target = "district", ignore = true)
    DistrictPart toEntity(DistrictPartDto districtPartDto);
}
