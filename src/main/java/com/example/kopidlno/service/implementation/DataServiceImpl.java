package com.example.kopidlno.service.implementation;

import com.example.kopidlno.model.DistrictPart;
import com.example.kopidlno.model.District;
import com.example.kopidlno.repository.DistrictPartRepository;
import com.example.kopidlno.repository.DistrictRepository;
import com.example.kopidlno.service.DataService;
import com.example.kopidlno.util.XMLParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {

    private final DistrictRepository districtRepository;
    private final DistrictPartRepository districtPartRepository;

    @Override
    public void processData() throws IOException {
        String fileUrl = "https://www.smartform.cz/download/kopidlno.xml.zip";
        String destDir = "xml";

        XMLParser.downloadZipFile(fileUrl, "kopidlno.zip");
        XMLParser.unzip("kopidlno.zip", destDir);
        List<District> districts = XMLParser.parseDistricts("xml/20210331_OB_573060_UZSZ.xml");
        List<DistrictPart> districtParts = XMLParser.parseDistrictParts("xml/20210331_OB_573060_UZSZ.xml");

        // Сначала сохраняем все сущности District
        districtRepository.saveAll(districts);

        // Теперь можно назначить правильные ссылки на сохраненные сущности District и сохранить DistrictPart
        for (DistrictPart districtPart : districtParts) {
            District district = districtRepository.findByCode(districtPart.getDistrict().getCode());
            districtPart.setDistrict(district);
        }

        districtPartRepository.saveAll(districtParts);
    }
}
