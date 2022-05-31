/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ProjExemploCrud.model;

import java.util.Date;

/**
 *
 * @author Usuário
 */
public class Administrador extends Pessoa{
    private Integer idAdministrador;
    private String situacao;
    private String permiteLogin;
    private String imagemAdministrador;

    public Administrador(Integer idPessoa, String cpf, String nome, Date datanascimento, String login, String senha) {
        super(idPessoa, cpf, nome, datanascimento, login, senha);
    }

    public Administrador(Integer idAdministrador, String situacao, String permiteLogin, Integer idPessoa, String cpf, String nome, Date datanascimento, String login, String senha) {
        super(idPessoa, cpf, nome, datanascimento, login, senha);
        this.idAdministrador = idAdministrador;
        this.situacao = situacao;
        this.permiteLogin = permiteLogin;
        this.imagemAdministrador = imagemAdministrador;
    }

    public Administrador(){
        
    }
    
    public Integer getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getPermiteLogin() {
        return permiteLogin;
    }

    public void setPermiteLogin(String permiteLogin) {
        this.permiteLogin = permiteLogin;
    }

    public String getImagemAdministrador() {
        return imagemAdministrador;
    }

    public void setImagemAdministrador(String imagemAdministrador) {
        this.imagemAdministrador = imagemAdministrador;
    }
    
}

