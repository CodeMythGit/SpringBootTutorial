package com.codemyth.controller;

import com.codemyth.model.FileData;
import com.codemyth.model.FileDataViewModel;
import com.codemyth.repository.FileDataRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class FileUploadDownloadController {

    private FileDataRepository repository;

    private final String FOLDER_PATH = "D:/uploads/";

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("files") MultipartFile file) throws IOException {

        String path = FOLDER_PATH + file.getOriginalFilename();
        FileData fileData = FileData.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .fileLocation(path)
                .build();
        repository.save(fileData);
        file.transferTo(new File(path).toPath());

        return "redirect:/success";
    }

    @GetMapping("success")
    public String success(Model model) {
        List<FileData> fileDataList = repository.findAll();
        model.addAttribute("items", new FileDataViewModel(fileDataList));
        model.addAttribute("newitem", FileData.builder().build());
        return "index";
    }

    @GetMapping("/download")
    public ResponseEntity<Object> download(@RequestParam("id") Integer id) throws IOException {
        Optional<FileData> fileData = repository.findById(id);
        String path = fileData.get().getFileLocation();
        byte[] content = Files.readAllBytes(new File(path).toPath());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileData.get().getFileName() + "\"")
                .body(content);
    }

    @GetMapping("/delete")
    public String deleteFile(@RequestParam("id") Integer id){
        repository.deleteById(id);
        return "redirect:/success";
    }
}
