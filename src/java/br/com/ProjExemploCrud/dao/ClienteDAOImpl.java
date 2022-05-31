/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ProjExemploCrud.dao;

import br.com.ProjExemploCrud.model.Cliente;
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
public class ClienteDAOImpl implements GenericDAO{
    private Connection conn;
    //construtor vazio
    public ClienteDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.conectar();
            System.out.println("Conectado com Sucesso - ClienteDAOImpl");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Cliente oCliente = (Cliente) object;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO cliente "
                + "(idpessoa, " //Preciso do atributo da classe pai
                + "observacao, "
                + "situacao, "
                + "permitelogin) "
                + "VALUES(?, ?, ?, ?);";

        try {
            stmt = conn.prepareStatement(sql);
            try {
                stmt.setInt(1, new PessoaDAOImpl().cadastrar(oCliente));
            } catch (Exception ex) {
                System.out.println("Erro ao cadastrar Pessoa \n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
            stmt.setString(2, oCliente.getObservacao());
            stmt.setString(3, oCliente.getSituacao());
            stmt.setString(4, oCliente.getPermiteLogin());

            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar Cliente \n Erro: " + ex.getMessage());
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

        String sql = "SELECT p.*, c.* "
                + "FROM pessoa p, cliente c "
                + "WHERE p.idpessoa = c.idpessoa "
                + "ORDER BY p.nome;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente oCliente = new Cliente();
                //Atributos Pessoa - Classe Pai
                oCliente.setIdPessoa(rs.getInt("idpessoa"));
                oCliente.setNome(rs.getString("nome"));
                oCliente.setCpf(rs.getString("cpf"));
                oCliente.setDatanascimento(rs.getDate("datanascimento"));
                oCliente.setLogin(rs.getString("login"));
                oCliente.setSenha(rs.getString("senha"));
                //Atributos ADM
                oCliente.setIdCliente(rs.getInt("idcliente"));
                oCliente.setObservacao(rs.getString("observacao"));
                oCliente.setSituacao(rs.getString("situacao"));
                oCliente.setPermiteLogin(rs.getString("permitelogin"));
                resultado.add(oCliente);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar Cliente \n Erro: " + ex.getMessage());
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
        
        String sql="DELETE FROM cliente WHERE idpessoa = ?;"
                + "COMMIT;"                
                + "DELETE FROM pessoa WHERE idpessoa = ?;";
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.setInt(2, idObject);
            stmt.executeUpdate();
        }catch (Exception ex) {
            System.out.println("Erro ao Excluir Cliente \n Erro: " + ex.getMessage());
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
        Cliente oCliente = null;

        String sql = "SELECT p.*, c.* "
                + "FROM pessoa p, cliente c "
                + "WHERE p.idpessoa = c.idpessoa "
                + "AND c.idpessoa = ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();

            if (rs.next()) {
                oCliente = new Cliente();
                //Atributos Pessoa - Classe Pai
                oCliente.setIdPessoa(rs.getInt("idpessoa"));
                oCliente.setNome(rs.getString("nome"));
                oCliente.setCpf(rs.getString("cpf"));
                oCliente.setDatanascimento(rs.getDate("datanascimento"));
                oCliente.setLogin(rs.getString("login"));
                oCliente.setSenha(rs.getString("senha"));
                //Atributos ADM
                oCliente.setIdCliente(rs.getInt("idcliente"));
                oCliente.setObservacao(rs.getString("observacao"));
                oCliente.setSituacao(rs.getString("situacao"));
                oCliente.setPermiteLogin(rs.getString("permitelogin"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar Cliente \n Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.fecharconexao(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao \n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oCliente;
    }

    @Override
    public Boolean alterar(Object object) {
        Cliente oCliente = (Cliente) object;
        PreparedStatement stmt = null;

        String sql = "UPDATE cliente SET "                
                + "observacao = ?, "
                + "situacao = ?, "
                + "permitelogin = ? "
                + "WHERE idpessoa = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, oCliente.getObservacao());
            stmt.setString(2, oCliente.getSituacao());
            stmt.setString(3, oCliente.getPermiteLogin());
            stmt.setInt(4, oCliente.getIdPessoa());
            
            try {
                if(new PessoaDAOImpl().alterar(oCliente)){
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
            System.out.println("Erro ao alterar Cliente \n Erro: " + ex.getMessage());
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

    public Cliente logarcliente(String login, String senha){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente oCliente = null;
        
        String sql = "Select * from pessoa, cliente "
                + "Where pessoa.idpessoa = cliente.idpessoa "
                + "AND pessoa.login = ? "
                + "AND pessoa.senha = ?;";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
                    
            if(rs.next()) {
                oCliente = new Cliente();
                //Atributos Pessoa - Classe Pai
                oCliente.setIdPessoa(rs.getInt("idpessoa"));
                oCliente.setNome(rs.getString("nome"));
                oCliente.setCpf(rs.getString("cpf"));
                oCliente.setDatanascimento(rs.getDate("datanascimento"));
                oCliente.setLogin(rs.getString("login"));
                //Atributos ADM
                oCliente.setIdCliente(rs.getInt("idcliente"));
                oCliente.setSituacao(rs.getString("situacao"));
                oCliente.setPermiteLogin(rs.getString("permitelogin"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao permitir login CLIENTE \n Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.fecharconexao(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao \n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oCliente;
    }
}
