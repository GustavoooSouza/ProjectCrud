/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ProjExemploCrud.dao;

import br.com.ProjExemploCrud.model.Pessoa;
import br.com.ProjExemploCrud.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Usuário
 */
public class PessoaDAOImpl {

    //Conexão com o BD
    private Connection conn;

    public PessoaDAOImpl() throws Exception {
        //Construtor da Classe;
        try {
            this.conn = ConnectionFactory.conectar();
            System.out.println("Conectado com Sucesso");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public Integer cadastrar(Pessoa oPessoa) {
        //Manipular SQL - Statement
        PreparedStatement stmt = null;
        ResultSet rs = null; //Serve para armazenar o ID da pessoa que vai ser gerado
        Integer idPessoa = null;//será utilizado para enviar as classes filhas

        String sql = "insert into pessoa "
                + "(nome, cpf, datanascimento, login, senha) "
                + "values (?, ?, ?, ?, ?) RETURNING idpessoa;";

        try {
            stmt = conn.prepareStatement(sql);
            //Passagem dos parametros
            stmt.setString(1, oPessoa.getNome());
            stmt.setString(2, oPessoa.getCpf());
            stmt.setDate(3, new java.sql.Date(oPessoa.getDatanascimento().getTime()));
            stmt.setString(4, oPessoa.getLogin());
            stmt.setString(5, oPessoa.getSenha());
            //Executar o sql
            rs = stmt.executeQuery();

            if (rs.next()) {
                idPessoa = rs.getInt("idpessoa");
            }
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar pessoa \n Erro:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.fecharconexao(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão\n Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return idPessoa;
    }
    
    public Boolean alterar(Object object){
        //Existe ao alterar para não repetirmos códigos
        Pessoa oPessoa = (Pessoa) object;
        PreparedStatement stmt = null;
        
        String sql = "UPDATE pessoa SET "
                + "nome = ?,"
                + "cpf = ?,"
                + "datanascimento = ?,"
                + "login = ?,"
                + "senha = ? "
                + "WHERE idpessoa = ?";
        
        try{
             //Passagem dos parametros
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, oPessoa.getNome());
            stmt.setString(2, oPessoa.getCpf());
            stmt.setDate(3, new java.sql.Date(oPessoa.getDatanascimento().getTime()));
            stmt.setString(4, oPessoa.getLogin());
            stmt.setString(5, oPessoa.getSenha());
            stmt.setInt(6, oPessoa.getIdPessoa());
            stmt.executeUpdate();
            return true;
        }catch(Exception ex){
            System.out.println("Erro ao alterar Pessoa \n Erro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }finally{
            try{
                ConnectionFactory.fecharconexao(conn, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar conexão \n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }        
    }
    
    
}