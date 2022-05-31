<%-- 
    Document   : gerenciaradm
    Created on : 24/09/2021, 09:10:09
    Author     : Usuário
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerenciar Administrador</title>
        
    </head>
    <body class="container" >
        <h1 >Cadastrar Administrador</h1><br/>
        <form action="${pageContext.request.contextPath}/SalvarAdministrador" method="POST" enctype="multipart/form-data">
            <label for="idpessoa">ID Pessoa:</label>
            <input type="number" name="idpessoa" value="${administrador.idPessoa}">
            <br />
            <label for="idadministrador">ID Adm:</label>
            <input type="number" name="idadministrador" value="${administrador.idAdministrador}">
            <br />
            <label for="cpf">CPF:</label>
            <input type="text" name="cpf" value="${administrador.cpf}">
            <br />
            <label for="nome">Nome:</label>
            <input type="text" name="nome" value="${administrador.nome}">
            <br />
            <label for="datanascimento">Data Nascimento</label>
            <input type="date" name="datanascimento" value="${administrador.datanascimento}">
            <br />
            <label for="login">Login</label>
            <input type="text" name="login" value="${administrador.login}">
            <br />
            <label for="senha">Senha</label>
            <input type="text" name="senha" value="${administrador.senha}">
            <br />
            <label for="permiteLogin">Permite Login</label>
            <input type="radio" name="permiteLogin" value="S" ${administrador.permiteLogin == 'S'?'checked':''}> Sim
            <input type="radio" name="permiteLogin" value="N" ${administrador.permiteLogin == 'N'?'checked':''}> Não
            <br />
            <p>Foto: <input class="form-control" type="file" required value="${administrador.caminhoImagemAdministrador}" name="foto"></p>
            <input type="submit" value="Salvar">
        </form>
        <h3>${mensagem}</h3>   
        
        <h1>Lista de Administradores</h1>
        <table>
            <thead>
                <tr aria-current="true">
                    <th>Id Pessoa</th>
                    <th>Id Adm</th>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>Data Nascimento</th>
                    <th>Login</th>
                    <th>Senha</th>
                    <th>Permite Login</th>
                    <th colspan="2">Editar</th>
                </tr>
            </thead>
            <tbody>
                <!--Adicionar tag lib-->
                
                <c:forEach items="${listaadm}" var="adm">
                    <tr>
                        <td>${adm.idPessoa}</td>
                        <td>${adm.idAdministrador}</td>
                        <td>${adm.nome}</td>
                        <td>${adm.cpf}</td>
                        <td>${adm.datanascimento}</td>
                        <td>${adm.login}</td>
                        <td>${adm.senha}</td>
                        <td>${adm.permiteLogin}</td>
                        <td id="editar">
                            <a href="${pageContext.request.contextPath}/ExcluirAdministrador?idpessoa=${adm.idPessoa}">
                                Excluir
                            </a>
                            <a href="${pageContext.request.contextPath}/CarregarAdministrador?idpessoa=${adm.idPessoa}">
                                Alterar
                            </a>
                        </td>     
                    </tr>
                </c:forEach>
            </tbody>            
        </table>
    </body>
</html>
