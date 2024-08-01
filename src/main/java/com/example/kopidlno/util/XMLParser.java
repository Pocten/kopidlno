package com.example.kopidlno.util;

import com.example.kopidlno.model.DistrictPart;
import com.example.kopidlno.model.District;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class XMLParser {
    public static void downloadZipFile(String fileUrl, String destinationFile) throws IOException {
        try (InputStream in = new URL(fileUrl).openStream()) {
            Files.copy(in, Paths.get(destinationFile), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static void unzip(String zipFilePath, String destDir) throws IOException {
        File dir = new File(destDir);
        if (!dir.exists()) dir.mkdirs();
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                String filePath = destDir + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    extractFile(zipIn, filePath);
                } else {
                    File dirEntry = new File(filePath);
                    dirEntry.mkdirs();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
    }

    public static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[4096];
            int read;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }
    }

    public static List<District> parseDistricts(String filePath) {
        List<District> districtList = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(filePath));
            doc.getDocumentElement().normalize();

            NodeList districtsListXML = doc.getElementsByTagName("vf:Obec");
            for (int i = 0; i < districtsListXML.getLength(); i++) {
                Node districtNode = districtsListXML.item(i);
                if (districtNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element districtElement = (Element) districtNode;
                    int code = Integer.parseInt(districtElement.getElementsByTagName("obi:Kod").item(0).getTextContent());
                    String name = districtElement.getElementsByTagName("obi:Nazev").item(0).getTextContent();

                    District district = new District();
                    district.setCode(code);
                    district.setName(name);

                    districtList.add(district);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return districtList;
    }

    public static List<DistrictPart> parseDistrictParts(String filePath) {
        List<DistrictPart> districtPartList = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(filePath));
            doc.getDocumentElement().normalize();

            NodeList districtPartsListXML = doc.getElementsByTagName("vf:CastObce");
            for (int i = 0; i < districtPartsListXML.getLength(); i++) {
                Node districtPartNode = districtPartsListXML.item(i);
                if (districtPartNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element districtPartElement = (Element) districtPartNode;
                    int code = Integer.parseInt(districtPartElement.getElementsByTagName("coi:Kod").item(0).getTextContent());
                    String name = districtPartElement.getElementsByTagName("coi:Nazev").item(0).getTextContent();
                    int districtCode = Integer.parseInt(districtPartElement.getElementsByTagName("obi:Kod").item(0).getTextContent());

                    DistrictPart districtPart = new DistrictPart();
                    districtPart.setCode(code);
                    districtPart.setName(name);

                    District district = new District();
                    district.setCode(districtCode);
                    districtPart.setDistrict(district);

                    districtPartList.add(districtPart);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return districtPartList;
    }
}
