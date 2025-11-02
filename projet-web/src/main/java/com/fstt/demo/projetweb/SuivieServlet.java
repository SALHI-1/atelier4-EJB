package com.fstt.demo.projetweb;

import com.fstt.demo.ejb.IGestionScolariteRemote;
import com.fstt.demo.entities.Etudiant;
import com.fstt.demo.entities.Module;
import com.fstt.demo.entities.Suivie;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "SuivieServlet", urlPatterns = {"/suivies"})
public class SuivieServlet extends HttpServlet {

    @EJB
    private IGestionScolariteRemote ejb;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Récupérer la liste des notes (Suivies) pour affichage
        List<Suivie> suivies = ejb.listSuivies();

        // 2. Récupérer les listes pour les menus déroulants du formulaire
        List<Etudiant> etudiants = ejb.listEtudiants();
        List<Module> modules = ejb.listModules();

        // 3. Stocker toutes les listes dans la requête
        request.setAttribute("listeSuivies", suivies);
        request.setAttribute("listeEtudiants", etudiants);
        request.setAttribute("listeModules", modules);

        // 4. Transférer à la vue (JSP)
        RequestDispatcher dispatcher = request.getRequestDispatcher("suivies.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 1. Récupérer les données du formulaire
            String noteStr = request.getParameter("note");
            String dateStr = request.getParameter("date_notation");
            String etudiantIdStr = request.getParameter("id_etudiant");
            String moduleIdStr = request.getParameter("id_module");

            // 2. Conversion et validation simple
            double note = Double.parseDouble(noteStr);
            Date dateNotation = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            int etudiantId = Integer.parseInt(etudiantIdStr);
            int moduleId = Integer.parseInt(moduleIdStr);

            // 3. Récupérer les objets Etudiant et Module via l'EJB
            Etudiant etudiant = ejb.getEtudiant(etudiantId);
            Module module = ejb.getModule(moduleId);

            // 4. Créer et peupler le nouvel objet Suivie
            Suivie suivie = new Suivie();
            suivie.setNote(note);
            suivie.setDate_notation(dateNotation);
            suivie.setEtudiant(etudiant);
            suivie.setModule(module);

            // 5. Appeler l'EJB pour sauvegarder
            ejb.addSuivie(suivie);

        } catch (ParseException e) {
            // Gérer l'erreur de parsing de date
            throw new ServletException("Erreur de format de date", e);
        } catch (NumberFormatException e) {
            // Gérer l'erreur de parsing de nombre (note ou IDs)
            throw new ServletException("Erreur de format de nombre", e);
        }

        // 6. Rediriger vers la liste (méthode GET) pour rafraîchir
        response.sendRedirect("suivies");
    }
}