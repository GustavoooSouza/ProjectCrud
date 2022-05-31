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
public class Cliente extends Pessoa{
    private Integer idCliente;
    private String observacao;
    private String situacao;
    private String permiteLogin;

    public Cliente(Integer idCliente, String observacao, String situacao, String permiteLogin, Integer idPessoa, String cpf, String nome, Date datanascimento, String login, String senha) {
        super(idPessoa, cpf, nome, datanascimento, login, senha);
        this.idCliente = idCliente;
        this.observacao = observacao;
        this.situacao = situacao;
        this.permiteLogin = permiteLogin;
    }
    
    public Cliente(){
    }

    public Cliente(Integer idPessoa, String cpf, String nome, Date datanascimento, String login, String senha) {
        super(idPessoa, cpf, nome, datanascimento, login, senha);
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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
      
    
}
