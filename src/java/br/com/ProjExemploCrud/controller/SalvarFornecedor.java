/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ProjExemploCrud.controller;


import br.com.ProjExemploCrud.dao.FornecedorDAOImpl;
import br.com.ProjExemploCrud.dao.GenericDAO;
import br.com.ProjExemploCrud.model.Fornecedor;
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
@WebServlet(name = "SalvarFornecedor", urlPatterns = {"/SalvarFornecedor"})
public class SalvarFornecedor extends HttpServlet {

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
        
        Fornecedor oFornecedor = new Fornecedor();
        //Recupera os dados do front
        oFornecedor.setNome(request.getParameter("nome"));
        oFornecedor.setCpf(request.getParameter("cpf"));
        oFornecedor.setDatanascimento(Conversao.converterData(request.getParameter("datanascimento")));
        oFornecedor.setLogin(request.getParameter("login"));
        oFornecedor.setSenha(request.getParameter("senha"));
        oFornecedor.setObservacao(request.getParameter("observacao"));
        oFornecedor.setUrl(request.getParameter("url"));
        oFornecedor.setSituacao(request.getParameter("situacao"));
        oFornecedor.setPermiteLogin(request.getParameter("permiteLogin"));
        //Armazenamento
        try{
            GenericDAO dao = new FornecedorDAOImpl();
            if(request.getParameter("idpessoa").equals("")){
                if(dao.cadastrar(oFornecedor)){
                    mensagem = "Fornecedor cadastrado com Sucesso!";
                }else{
                    mensagem = "Erro ao cadastrar Fornecedor";
                }
            }else{
                oFornecedor.setIdPessoa(Integer.parseInt(request.getParameter("idpessoa")));
                if(dao.alterar(oFornecedor)){
                    mensagem = "Fornecedor alterado com Sucesso!";
                }else{
                    mensagem = "Erro ao alterar Fornecedor";
                }
            }            
            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("ListarFornecedor").forward(request, response);
            
        } catch (Exception ex) {
            System.out.println("Problemas no Servlet SalvarFornecedor \n Erro: " + ex.getMessage());
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
