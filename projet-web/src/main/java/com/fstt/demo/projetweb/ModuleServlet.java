package com.fstt.demo.projetweb;

import com.fstt.demo.ejb.IGestionScolariteRemote;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fstt.demo.entities.Module;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ModuleServlet", urlPatterns = {"/modules"})
public class ModuleServlet extends HttpServlet {

    @EJB
    private IGestionScolariteRemote ejb;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Module> modules = ejb.listModules();
        request.setAttribute("listeModules", modules);
        RequestDispatcher dispatcher = request.getRequestDispatcher("modules.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nomModule = request.getParameter("nom_module");
        String semestre = request.getParameter("semestre");

        Module module = new Module();
        module.setNom_module(nomModule);
        module.setSemestre(semestre);

        ejb.addModule(module);

        response.sendRedirect("modules");
    }
}