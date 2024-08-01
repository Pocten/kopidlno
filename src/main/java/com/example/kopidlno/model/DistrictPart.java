package com.example.kopidlno.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "district_part")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistrictPart {
    @Id
    @Column(name = "code", unique = true, nullable = false)
    private int code;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_code", nullable = false)
    private District district;
}
