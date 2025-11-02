package com.fstt.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "etudiant")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Etudiant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_etudiant;

    private String nom;
    private String prenom;
    @Column(unique = true)
    private String cne;
    private String adresse;
    private String niveau;

}
