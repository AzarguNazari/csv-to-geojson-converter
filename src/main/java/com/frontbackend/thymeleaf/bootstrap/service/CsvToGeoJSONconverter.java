package com.frontbackend.thymeleaf.bootstrap.service;

import com.fasterxml.jackson.core.util.Separators;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class CsvToGeoJSONconverter {

    public void convert(MultipartFile file) throws IOException {

        File targetFile = getUploadedFile(file);

        try (BufferedReader br = new BufferedReader(new FileReader(targetFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                System.out.printf(Arrays.toString(values));
            }
        }
    }

    private File getUploadedFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        File targetFile = new File("src/main/resources/temp.csv");
        java.nio.file.Files.copy(inputStream, targetFile.toPath(), REPLACE_EXISTING);
        IOUtils.closeQuietly(inputStream);
        return targetFile;
    }

}
