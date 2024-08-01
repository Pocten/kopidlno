package com.example.kopidlno.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "district")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class District {
    @Id
    @Column(name = "code", unique = true, nullable = false)
    private int code;

    @Column(name = "name")
    private String name;
}
