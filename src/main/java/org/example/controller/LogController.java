package org.example.controller;

import org.example.entities.Log;
import org.example.services.FileService;
import org.example.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/ai")
public class LogController {

    @Autowired
    private LogService service;
    @Autowired
    private FileService fileService;

    @GetMapping
    public List<Log> getAllLogs () {
        return service.getAll();

    }

    @GetMapping("/{id}")
    public Log getLog (@PathVariable Long id){
        return service.getById(id);

    }


    @PostMapping("/upload-file")
    public ResponseEntity<String> createLog (@RequestParam("file")MultipartFile file) {
        try {
            String savedPath = fileService.saveFile(file);
            service.create(savedPath);
            return ResponseEntity.ok("File upload: SUCCESS!");
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("File upload failed due to: " + ex.getMessage());
        }
    }


    public Log updateLog (@PathVariable Long id, @RequestBody Log log){
        return service.update(id, log);
    }

    @DeleteMapping("/{id}")
    public String deleteLog (@PathVariable Long id){
        service.deleteById(id);
        return "Log deleted";
    }

    @DeleteMapping
    public String deleteAll () {
        service.deleteAll();
        return "All logs deleted";
    }
}

