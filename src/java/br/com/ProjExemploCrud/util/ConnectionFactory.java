/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ProjExemploCrud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Usuário
 */
public class ConnectionFactory {
    //A primeira ação a ser feita é adicionar 
    //a biblioteca JDBC para utilizar o Driver
    //>> Botão Direito em cima de Bibliotecas 
    //>> Adicionar Bibliotecas
    //>> Driver JDBC do PostgreSQL
    
    //Método de conexão com BD
    public static Connection conectar() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/bdheranca";
        String user = "postgres";
        String password = "gustavo";
        
        try{
            /*Chamar o Driver que vai fazer a conexão*/
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url,user,password);            
        }catch(Exception ex){
            throw new Exception("Erro ao conectar" + ex.getMessage());
        }        
    }

    //Fechar a Conexão
    private static void fechar(Connection conn, Statement stmt, ResultSet rs) throws Exception{
        try{
            if(conn!=null) conn.close();
            if(stmt!=null) stmt.close();
            if(rs!=null) rs.close();
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    
    public static void fecharconexao(Connection conn) throws Exception{
        fechar(conn, null, null);
    }
    
    public static void fecharconexao(Connection conn, Statement stmt) throws Exception{
        fechar(conn, stmt, null);
    }
    
    public static void fecharconexao(Connection conn, Statement stmt, ResultSet rs) throws Exception{
        fechar(conn, stmt, rs);
    }
    
}
