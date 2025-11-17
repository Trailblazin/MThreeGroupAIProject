package org.example.controller;

import org.example.entities.Log;
import org.example.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ai")
public class LogController {

    @Autowired
    private LogService service;

    @GetMapping
    public List<Log> getAllLogs () {
        return service.getAll();

    }

    @GetMapping("/{id}")
    public Log getLog (@PathVariable Long id){
        return service.getById(id);

    }


    @PostMapping
    public Log createLog (@RequestBody Log log){
        return service.create(log);
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

