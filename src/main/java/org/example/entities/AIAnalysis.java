package org.example.entities;

import jakarta.persistence.Entity;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
public class AIAnalysis {
    BigInteger id;
    String analysis_type;
    String result;
    LocalDateTime created_at;
}
