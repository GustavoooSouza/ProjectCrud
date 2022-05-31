/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ProjExemploCrud.dao;


import br.com.ProjExemploCrud.model.Fornecedor;
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
public class FornecedorDAOImpl implements GenericDAO{
    private Connection conn;

    //construtor vazio
    public FornecedorDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.conectar();
            System.out.println("Conectado com Sucesso - FornecedorDAOImpl");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Fornecedor oFornecedor = (Fornecedor) object;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO fornecedor "
                + "(idpessoa, " //Preciso do atributo da classe pai
                + "observacao, "
                + "url, "
                + "situacao, "
                + "permitelogin) "
                + "VALUES(?, ?, ?, ?, ?);";

        try {
            stmt = conn.prepareStatement(sql);
            try {
                stmt.setInt(1, new PessoaDAOImpl().cadastrar(oFornecedor));
            } catch (Exception ex) {
                System.out.println("Erro ao cadastrar Pessoa \n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
            stmt.setString(2, oFornecedor.getObservacao());
            stmt.setString(3, oFornecedor.getUrl());
            stmt.setString(4, oFornecedor.getSituacao());
            stmt.setString(5, oFornecedor.getPermiteLogin());

            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar Fornecedor \n Erro: " + ex.getMessage());
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

        String sql = "SELECT p.*, f.* "
                + "FROM pessoa p, fornecedor f "
                + "WHERE p.idpessoa = f.idpessoa "
                + "ORDER BY p.nome;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor oFornecedor = new Fornecedor();
                //Atributos Pessoa - Classe Pai
                oFornecedor.setIdPessoa(rs.getInt("idpessoa"));
                oFornecedor.setNome(rs.getString("nome"));
                oFornecedor.setCpf(rs.getString("cpf"));
                oFornecedor.setDatanascimento(rs.getDate("datanascimento"));
                oFornecedor.setLogin(rs.getString("login"));
                oFornecedor.setSenha(rs.getString("senha"));
                //Atributos ADM
                oFornecedor.setIdFornecedor(rs.getInt("idfornecedor"));
                oFornecedor.setObservacao(rs.getString("observacao"));
                oFornecedor.setUrl(rs.getString("url"));
                oFornecedor.setSituacao(rs.getString("situacao"));
                oFornecedor.setPermiteLogin(rs.getString("permitelogin"));
                resultado.add(oFornecedor);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar Fornecedor \n Erro: " + ex.getMessage());
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
        
        String sql="DELETE FROM fornecedor WHERE idpessoa = ?;"
                + "COMMIT;"                
                + "DELETE FROM pessoa WHERE idpessoa = ?;";
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            stmt.setInt(2, idObject);
            stmt.executeUpdate();
        }catch (Exception ex) {
            System.out.println("Erro ao Excluir Fornecedor \n Erro: " + ex.getMessage());
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
        Fornecedor oFornecedor = null;

        String sql = "SELECT p.*, f.* "
                + "FROM pessoa p, fornecedor f "
                + "WHERE p.idpessoa = f.idpessoa "
                + "AND f.idpessoa = ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();

            if (rs.next()) {
                oFornecedor = new Fornecedor();
                //Atributos Pessoa - Classe Pai
                oFornecedor.setIdPessoa(rs.getInt("idpessoa"));
                oFornecedor.setNome(rs.getString("nome"));
                oFornecedor.setCpf(rs.getString("cpf"));
                oFornecedor.setDatanascimento(rs.getDate("datanascimento"));
                oFornecedor.setLogin(rs.getString("login"));
                oFornecedor.setSenha(rs.getString("senha"));
                //Atributos ADM
                oFornecedor.setIdFornecedor(rs.getInt("idfornecedor"));
                oFornecedor.setObservacao(rs.getString("observacao"));
                oFornecedor.setUrl(rs.getString("url"));
                oFornecedor.setSituacao(rs.getString("situacao"));
                oFornecedor.setPermiteLogin(rs.getString("permitelogin"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar Fornecedor \n Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.fecharconexao(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao \n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return oFornecedor;
    }

    @Override
    public Boolean alterar(Object object) {
        Fornecedor oFornecedor = (Fornecedor) object;
        PreparedStatement stmt = null;

        String sql = "UPDATE fornecedor SET "                
                + "observacao = ?, "
                + "url = ?, "
                + "situacao = ?, "
                + "permitelogin = ? "
                + "WHERE idpessoa = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, oFornecedor.getObservacao());
            stmt.setString(2, oFornecedor.getUrl());
            stmt.setString(3, oFornecedor.getSituacao());
            stmt.setString(4, oFornecedor.getPermiteLogin());
            stmt.setInt(5, oFornecedor.getIdPessoa());
            
            try {
                if(new PessoaDAOImpl().alterar(oFornecedor)){
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
            System.out.println("Erro ao alterar Fornecedor \n Erro: " + ex.getMessage());
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
}
