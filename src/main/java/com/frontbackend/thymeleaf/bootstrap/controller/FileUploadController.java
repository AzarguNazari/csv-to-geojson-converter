package com.frontbackend.thymeleaf.bootstrap.controller;

import com.frontbackend.thymeleaf.bootstrap.service.CsvToGeoJSONconverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class FileUploadController {

    @Autowired
    private CsvToGeoJSONconverter csvToGeoJSONconverter;

    @GetMapping
    public String main() {
        return "index";
    }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {

        if(!file.getOriginalFilename().endsWith(".csv")){
            redirectAttributes.addFlashAttribute("message", "The file should be CSV format");
        }
        else{
            csvToGeoJSONconverter.convert(file);
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
        }

        return "redirect:/";
    }
}