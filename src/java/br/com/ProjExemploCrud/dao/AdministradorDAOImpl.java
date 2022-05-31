/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ProjExemploCrud.dao;

import br.com.ProjExemploCrud.model.Administrador;
import br.com.ProjExemploCrud.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuário
 */
public class AdministradorDAOImpl implements GenericDAO {

    private Connection conn;

    //construtor vazio
    public AdministradorDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.conectar();
            System.out.println("Conectado com Sucesso - AdminsitradorDAOImpl");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Administrador oAdministrador = (Administrador) object;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO administrador "
                + "(idpessoa, " //Preciso do atributo da classe pai
                + "situacao, "
                + "permitelogin, "
                + "imagemAdministrador)"
                + "VALUES(?, ?, ?, ?);";

        try {
            stmt = conn.prepareStatement(sql);
            try {
                stmt.setInt(1, new PessoaDAOImpl().cadastrar(oAdministrador));
            } catch (Exception ex) {
                System.out.println("Erro ao cadastrar Pessoa \n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
            stmt.setString(2, oAdministrador.getSituacao());
            stmt.setString(3, oAdministrador.getPermiteLogin());
            stmt.setString(4, oAdministrador.getImagemAdministrador());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar ADM \n Erro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.fecharconexao(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao \n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT p.*, a.* "
                + "FROM pessoa p, administrador a "
                + "WHERE p.idpessoa = a.idpessoa "
                + "ORDER BY p.nome;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Administrador oAdministrador = new Administrador();
                //Atributos Pessoa - Classe Pai
                oAdministrador.setIdPessoa(rs.getInt("idpessoa"));
                oAdministrador.setNome(rs.getString("nome"));
                oAdministrador.setCpf(rs.getString("cpf"));
                oAdministrador.setDatanascimento(rs.getDate("datanascimento"));
                oAdministrador.setLogin(rs.getString("login"));
                oAdministrador.setSenha(rs.getString("senha"));
                //Atributos ADM
                oAdministrador.setIdAdministrador(rs.getInt("idadministrador"));
                oAdministrador.setSituacao(rs.getString("situacao"));
                oAdministrador.setPermiteLogin(rs.getString("permitelogin"));
                resultado.add(oAdministrador);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar ADM \n Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.fecharconexao(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao \n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    @Override
    public void excluir(int idObject) {
        PreparedStatement stmt = null;
        
        String sql="DELETE FROM administrador WHERE idpessoa = ?;"
                + "COMMIT;"                
                + "DELETE FROM pessoa WHERE idpessoa = ?;";
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.setInt(2, idObject);
            stmt.executeUpdate();
        }catch (Exception ex) {
            System.out.println("Erro ao Excluir ADM \n Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.fecharconexao(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao \n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Object carregar(int idObject) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Administrador oAdministrador = null;

        String sql = "SELECT p.*, a.* "
                + "FROM pessoa p, administrador a "
                + "WHERE p.idpessoa = a.idpessoa "
                + "AND a.idpessoa = ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();

            if (rs.next()) {
                oAdministrador = new Administrador();
                //Atributos Pessoa - Classe Pai
                oAdministrador.setIdPessoa(rs.getInt("idpessoa"));
                oAdministrador.setNome(rs.getString("nome"));
                oAdministrador.setCpf(rs.getString("cpf"));
                oAdministrador.setDatanascimento(rs.getDate("datanascimento"));
                oAdministrador.setLogin(rs.getString("login"));
                oAdministrador.setSenha(rs.getString("senha"));
                //Atributos ADM
                oAdministrador.setImagemAdministrador(rs.getString("imagemAdministrador"));
                oAdministrador.setIdAdministrador(rs.getInt("idadministrador"));
                oAdministrador.setSituacao(rs.getString("situacao"));
                oAdministrador.setPermiteLogin(rs.getString("permitelogin"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar ADM \n Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.fecharconexao(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao \n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oAdministrador;
    }

    @Override
    public Boolean alterar(Object object) {
        Administrador oAdministrador = (Administrador) object;
        PreparedStatement stmt = null;

        String sql = "UPDATE administrador SET "                
                + "situacao = ?, "
                + "permitelogin = ?, "
                + "imagemAdministrador = ? "
                + "WHERE idpessoa = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, oAdministrador.getSituacao());
            stmt.setString(2, oAdministrador.getPermiteLogin());
            stmt.setInt(3, oAdministrador.getIdPessoa());
            stmt.setString(4, oAdministrador.getImagemAdministrador());
            
            try {
                if(new PessoaDAOImpl().alterar(oAdministrador)){
                    stmt.executeUpdate();
                    return true;
                }else{
                    return false;
                }                
            } catch (Exception ex) {
                System.out.println("Erro ao alterar Pessoa \n Erro: " + ex.getMessage());
                ex.printStackTrace();
                return false;
            }            
        } catch (Exception ex) {
            System.out.println("Erro ao alterar ADM \n Erro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.fecharconexao(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao \n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
    
    public Administrador logaradm(String login, String senha){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Administrador oAdministrador = null;
        
        String sql = "Select * from pessoa, administrador "
                + "Where pessoa.idpessoa = administrador.idpessoa "
                + "AND pessoa.login = ? "
                + "AND pessoa.senha = ?;";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
                    
            if(rs.next()) {
                oAdministrador = new Administrador();
                //Atributos Pessoa - Classe Pai
                oAdministrador.setIdPessoa(rs.getInt("idpessoa"));
                oAdministrador.setNome(rs.getString("nome"));
                oAdministrador.setCpf(rs.getString("cpf"));
                oAdministrador.setDatanascimento(rs.getDate("datanascimento"));
                oAdministrador.setLogin(rs.getString("login"));
                //Atributos ADM
                oAdministrador.setIdAdministrador(rs.getInt("idadministrador"));
                oAdministrador.setSituacao(rs.getString("situacao"));
                oAdministrador.setPermiteLogin(rs.getString("permitelogin"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao permitir login ADM \n Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.fecharconexao(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao \n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oAdministrador;
    }
}
