package com.fstt.demo.projetweb;

import com.fstt.demo.ejb.IGestionScolariteRemote;
import com.fstt.demo.entities.Etudiant;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

// Servlet pour gérer les étudiants (Ajout et Affichage)
@WebServlet(name = "EtudiantServlet", urlPatterns = {"/etudiants"})
public class EtudiantServlet extends HttpServlet {

    // Injection de l'EJB distant
    @EJB
    private IGestionScolariteRemote ejb;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Récupérer la liste des étudiants via l'EJB
        List<Etudiant> etudiants = ejb.listEtudiants();

        // 2. Stocker la liste dans la requête
        request.setAttribute("listeEtudiants", etudiants);

        // 3. Transférer à la vue (JSP)
        RequestDispatcher dispatcher = request.getRequestDispatcher("etudiants.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Récupérer les données du formulaire
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String cne = request.getParameter("cne");
        String adresse = request.getParameter("adresse");
        String niveau = request.getParameter("niveau");

        // 2. Créer un nouvel objet Etudiant
        Etudiant etudiant = new Etudiant(null, nom, prenom, cne, adresse, niveau);

        // 3. Appeler l'EJB pour le sauvegarder
        ejb.addEtudiant(etudiant);

        // 4. Rediriger vers la liste (méthode GET) pour rafraîchir
        response.sendRedirect("etudiants");
    }
}