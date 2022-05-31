/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ProjExemploCrud.controller;

import br.com.ProjExemploCrud.dao.AdministradorDAOImpl;
import br.com.ProjExemploCrud.model.Administrador;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuário
 */
@WebServlet(name = "LogarAdministrador", urlPatterns = {"/LogarAdministrador"})
public class LogarAdministrador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menssagem = "";
        if (request.getParameter("acao").equals("logar")) {
            String login = request.getParameter("loginpessoa");
            String senha = request.getParameter("senhapessoa");

            if (!login.equals("") && !senha.equals("")) {
                try {
                    AdministradorDAOImpl dao = new AdministradorDAOImpl();
                    Administrador oAdministrador = dao.logaradm(login, senha);
                    if (oAdministrador != null) {
                        if(oAdministrador.getPermiteLogin().equals("S")){
                        HttpSession session = request.getSession(true);
                        session.setAttribute("administradorlogado", dao);
                        menssagem = "Seja bem vindo(a)" + oAdministrador.getNome();
                        session.setAttribute("saudacao", menssagem);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                        } else {
                            menssagem = "Voce nao tem permissao para logar no sitema, fale com o administrador";
                            request.setAttribute("menssagem", menssagem);
                            request.getRequestDispatcher("logaradministrador.jsp").forward(request, response);
                        }
                    } else {
                        menssagem = "Login ou Senha invalidos!!!";
                        request.setAttribute("menssagem", menssagem);
                        request.getRequestDispatcher("logaradministrador.jsp").forward(request, response);
                    }
                } catch (Exception ex) {
                    System.out.println("Erro ao logar adm!!! " + ex.getMessage());
                    request.getRequestDispatcher("logaradministrador.jsp").forward(request, response);
                }
            } else {
                menssagem = "Login ou senha invalidos (CAMPOS VAZIOS)";
                request.setAttribute("menssagem", menssagem);
                request.getRequestDispatcher("logaradministrador.jsp").forward(request, response);
            }
        } else if (request.getParameter("acao").equals("logout")) {
            HttpSession session = request.getSession(true);
            session.invalidate();
            response.sendRedirect("logaradministrador.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
