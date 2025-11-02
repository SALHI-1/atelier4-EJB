package com.fstt.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "suivie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Suivie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_suivie;

    private double note;

    @Temporal(TemporalType.DATE)
    private Date date_notation;

    // Relation N-1 vers Etudiant
    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    private Etudiant etudiant;

    // Relation N-1 vers Module
    @ManyToOne
    @JoinColumn(name = "id_module")
    private Module module;


}
