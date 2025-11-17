package org.example.controller;

import org.example.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class AIController {

    @Autowired
    private LogService service;

    //analyse/summarise a log

    //Generate incident summary

    //categorise a log by log type

    //Detect Anomalies

    //Recommend fix

    //Start chat

}
