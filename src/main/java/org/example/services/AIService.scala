package org.example.services

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.example.repositories.LogRepository

import org.example.entities.Log
import java.time.LocalDate
import org.example.exceptions.ApiException
import java.util


@Service
class AIService @Autowired()(logRepo: LogRepository) {


}
