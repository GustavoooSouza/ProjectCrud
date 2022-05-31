/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ProjExemploCrud.controller;

import br.com.ProjExemploCrud.dao.ClienteDAOImpl;
import br.com.ProjExemploCrud.dao.GenericDAO;
import br.com.ProjExemploCrud.model.Cliente;
import br.com.ProjExemploCrud.util.Conversao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuário
 */
@WebServlet(name = "SalvarCliente", urlPatterns = {"/SalvarCliente"})
public class SalvarCliente extends HttpServlet {

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
        String mensagem = "";
        
        Cliente oCliente = new Cliente();
        //Recupera os dados do front
        oCliente.setNome(request.getParameter("nome"));
        oCliente.setCpf(request.getParameter("cpf"));
        oCliente.setDatanascimento(Conversao.converterData(request.getParameter("datanascimento")));
        oCliente.setLogin(request.getParameter("login"));
        oCliente.setSenha(request.getParameter("senha"));
        oCliente.setObservacao(request.getParameter("observacao"));
        oCliente.setSituacao(request.getParameter("situacao"));
        oCliente.setPermiteLogin(request.getParameter("permiteLogin"));
        //Armazenamento
        try{
            GenericDAO dao = new ClienteDAOImpl();
            if(request.getParameter("idpessoa").equals("")){
                if(dao.cadastrar(oCliente)){
                    mensagem = "Cliente cadastrado com Sucesso!";
                }else{
                    mensagem = "Erro ao cadastrar Cliente";
                }
            }else{
                oCliente.setIdPessoa(Integer.parseInt(request.getParameter("idpessoa")));
                if(dao.alterar(oCliente)){
                    mensagem = "Cliente alterado com Sucesso!";
                }else{
                    mensagem = "Erro ao alterar Cliente";
                }
            }            
            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("ListarCliente").forward(request, response);
            
        } catch (Exception ex) {
            System.out.println("Problemas no Servlet SalvarCliente \n Erro: " + ex.getMessage());
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
