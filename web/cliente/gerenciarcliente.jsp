<%-- 
    Document   : gerenciarcliente
    Created on : 24/09/2021, 09:10:09
    Author     : Usuário
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerenciar Cliente</title>
    </head>
    <body>
        <h1>Cadastrar Cliente</h1>
        <form action="${pageContext.request.contextPath}/SalvarCliente" method="POST">
            <label for="idpessoa">ID Pessoa:</label>
            <input type="number" name="idpessoa" value="${cliente.idPessoa}">
            <br />
            <label for="idcliente">ID Fornecedor</label>
            <input type="number" name="idcliente" value="${cliente.idCliente}">
            <br />
            <label for="cpf">CPF:</label>
            <input type="text" name="cpf" value="${cliente.cpf}">
            <br />
            <label for="nome">Nome:</label>
            <input type="text" name="nome" value="${cliente.nome}">
            <br />
            <label for="datanascimento">Data Nascimento</label>
            <input type="date" name="datanascimento" value="${cliente.datanascimento}">
            <br />
            <label for="login">Login</label>
            <input type="text" name="login" value="${cliente.login}">
            <br />
            <label for="senha">Senha</label>
            <input type="text" name="senha" value="${cliente.senha}">
            <br />
            <label for="permiteLogin">Permite Login</label>
            <input type="radio" name="permiteLogin" value="S" ${cliente.permiteLogin == 'S'?'checked':''}> Sim
            <input type="radio" name="permiteLogin" value="N" ${cliente.permiteLogin == 'N'?'checked':''}> Não
            <br />
            <input type="submit" value="Salvar">
        </form>
        <h3>${mensagem}</h3>   
        
        <h1>Lista de Clientes</h1>
        <table>
            <thead>
                <tr>
                    <th>Id Pessoa</th>
                    <th>Id Cliente</th>
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
                
                <c:forEach items="${listacliente}" var="cli">
                    <tr>
                        <td>${cli.idPessoa}</td>
                        <td>${cli.idCliente}</td>
                        <td>${cli.nome}</td>
                        <td>${cli.cpf}</td>
                        <td>${cli.datanascimento}</td>
                        <td>${cli.login}</td>
                        <td>${cli.senha}</td>
                        <td>${cli.permiteLogin}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/ExcluirCliente?idpessoa=${cli.idPessoa}">
                                Excluir
                            </a>
                            <a href="${pageContext.request.contextPath}/CarregarCliente?idpessoa=${cli.idPessoa}">
                                Alterar
                            </a>
                        </td>     
                    </tr>
                </c:forEach>
            </tbody>            
        </table>
    </body>
</html>
