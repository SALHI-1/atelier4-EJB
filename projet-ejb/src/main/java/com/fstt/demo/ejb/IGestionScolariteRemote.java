package com.fstt.demo.ejb;

import com.fstt.demo.entities.Etudiant;
import com.fstt.demo.entities.Suivie;
import com.fstt.demo.entities.Module;
import jakarta.ejb.Remote;
import java.util.List;

@Remote // Annotation clé pour l'accès distant
public interface IGestionScolariteRemote {

    // --- CRUD Etudiant ---
    Etudiant addEtudiant(Etudiant etudiant);
    Etudiant getEtudiant(int id);
    List<Etudiant> listEtudiants();
    Etudiant updateEtudiant(Etudiant etudiant);
    void deleteEtudiant(int id);

    // --- CRUD Module ---
    Module addModule(Module module);
    Module getModule(int id);
    List<Module> listModules();
    void deleteModule(int id);

    // --- CRUD Suivie (Note) ---
    Suivie addSuivie(Suivie suivie);
    List<Suivie> listSuivies();
}