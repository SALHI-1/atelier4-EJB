package com.fstt.demo.ejb;

import com.fstt.demo.entities.Etudiant;
import com.fstt.demo.entities.Suivie;
import com.fstt.demo.entities.Module;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless // Annotation clé pour un EJB Session sans état
public class GestionScolariteBean implements IGestionScolariteRemote {

    @PersistenceContext(unitName = "cnx")
    private EntityManager em;

    // --- Implémentation CRUD Etudiant ---
    @Override
    public Etudiant addEtudiant(Etudiant etudiant) {
        em.persist(etudiant);
        return etudiant;
    }

    @Override
    public Etudiant getEtudiant(int id) {
        return em.find(Etudiant.class, id);
    }

    @Override
    public List<Etudiant> listEtudiants() {
        TypedQuery<Etudiant> query = em.createQuery("SELECT e FROM Etudiant e", Etudiant.class);
        return query.getResultList();
    }

    @Override
    public Etudiant updateEtudiant(Etudiant etudiant) {
        return em.merge(etudiant);
    }

    @Override
    public void deleteEtudiant(int id) {
        Etudiant etudiant = getEtudiant(id);
        if (etudiant != null) {
            em.remove(etudiant);
        }
    }

    // --- Implémentation CRUD Module ---
    @Override
    public Module addModule(Module module) {
        em.persist(module);
        return module;
    }

    @Override
    public Module getModule(int id) {
        return em.find(Module.class, id);
    }

    @Override
    public List<Module> listModules() {
        TypedQuery<Module> query = em.createQuery("SELECT m FROM Module m", Module.class);
        return query.getResultList();
    }

    @Override
    public void deleteModule(int id) {
        Module module = getModule(id);
        if (module != null) {
            em.remove(module);
        }
    }

    // --- Implémentation CRUD Suivie (Note) ---
    @Override
    public Suivie addSuivie(Suivie suivie) {
        em.persist(suivie);
        return suivie;
    }

    @Override
    public List<Suivie> listSuivies() {
        // Eager fetch pour éviter les problèmes de LazyInitialization
        TypedQuery<Suivie> query = em.createQuery(
                "SELECT s FROM Suivie s JOIN FETCH s.etudiant JOIN FETCH s.module",
                Suivie.class
        );
        return query.getResultList();
    }
}