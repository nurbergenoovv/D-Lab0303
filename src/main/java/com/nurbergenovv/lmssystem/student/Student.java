package com.nurbergenovv.lmssystem.student;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private int exam;
    private String mark;
}