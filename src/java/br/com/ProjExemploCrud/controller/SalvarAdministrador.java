/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ProjExemploCrud.controller;

import br.com.ProjExemploCrud.dao.AdministradorDAOImpl;
import br.com.ProjExemploCrud.dao.GenericDAO;
import br.com.ProjExemploCrud.model.Administrador;
import br.com.ProjExemploCrud.util.Conversao;
import br.com.ProjExemploCrud.util.Upload;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuário
 */
@WebServlet(name = "SalvarAdministrador", urlPatterns = {"/SalvarAdministrador"})
public class SalvarAdministrador extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String mensagem = "";

        System.out.println("ContentType"+request.getContentType());
        Upload up = new Upload();
        up.setFolderUpload("fotos");

        try {
            if (up.formProcess(getServletContext(), request)) {

                Administrador oAdministrador = new Administrador();
                oAdministrador.setImagemAdministrador(up.getFiles().get(0));
                //Recupera os dados do front
                oAdministrador.setNome(up.getForm().get("nome").toString());
                oAdministrador.setCpf(up.getForm().get("cpf").toString());
                //oAdministrador.setDatanascimento(Conversao.converterData(request.getParameter("datanascimento"))); //duvidaaaaaa aquiiiiii
                Date dataNascimanto = Conversao.converterData(up.getForm().get("datanascimento").toString());
                oAdministrador.setDatanascimento(dataNascimanto);
                oAdministrador.setLogin(up.getForm().get("login").toString());
                oAdministrador.setSenha(up.getForm().get("senha").toString());
                //oAdministrador.setSituacao(up.getForm().get("situacao").toString());
                oAdministrador.setPermiteLogin(up.getForm().get("permiteLogin").toString());
                
                //Armazenamento
                try {
                    GenericDAO dao = new AdministradorDAOImpl();
                    if (up.getForm().get("idpessoa").toString().equals("")) {
                        if (dao.cadastrar(oAdministrador)) {
                            mensagem = "Adm cadastrado com Sucesso!";
                        } else {
                            mensagem = "Erro ao cadastrar Adm";
                        }
                    } else {
                        oAdministrador.setIdPessoa(Integer.parseInt(up.getForm().get("idpessoa").toString()));
                        if (dao.alterar(oAdministrador)) {
                            mensagem = "Adm alterado com Sucesso!";
                        } else {
                            mensagem = "Erro ao alterar Adm";
                        }
                    }
                    request.setAttribute("mensagem", mensagem);
                    request.getRequestDispatcher("ListarAdministrador").forward(request, response);

                } catch (Exception ex) {
                    System.out.println("Problemas no Servlet SalvarAdministrador \n Erro: " + ex.getMessage());
                    ex.printStackTrace();
                }
            } else {
                System.err.println("Errooooooooooooooooooooooooo!");
            }
        } catch (Exception ex) {
            System.out.println("Erro ao processar dados. ERRO: " + ex.getMessage());
            ex.printStackTrace();
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
