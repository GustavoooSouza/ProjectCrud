<%-- 
    Document   : gerenciarfornecedor
    Created on : 24/09/2021, 09:10:09
    Author     : Usuário
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerenciar Fornecedor</title>
    </head>
    <body>
        <h1>Cadastrar Fornecedor</h1>
        <form action="${pageContext.request.contextPath}/SalvarFornecedor" method="POST">
            <label for="idpessoa">ID Pessoa:</label>
            <input type="number" name="idpessoa" value="${fornecedor.idPessoa}">
            <br />
            <label for="idfornecedor">ID Fornecedor</label>
            <input type="number" name="idfornecedor" value="${fornecedor.idFornecedor}">
            <br />
            <label for="cpf">CPF:</label>
            <input type="text" name="cpf" value="${fornecedor.cpf}">
            <br />
            <label for="nome">Nome:</label>
            <input type="text" name="nome" value="${fornecedor.nome}">
            <br />
            <label for="datanascimento">Data Nascimento</label>
            <input type="date" name="datanascimento" value="${fornecedor.datanascimento}">
            <br />
            <label for="login">Login</label>
            <input type="text" name="login" value="${fornecedor.login}">
            <br />
            <label for="senha">Senha</label>
            <input type="text" name="senha" value="${fornecedor.senha}">
            <br />
            <label for="permiteLogin">Permite Login</label>
            <input type="radio" name="permiteLogin" value="S" ${fornecedor.permiteLogin == 'S'?'checked':''}> Sim
            <input type="radio" name="permiteLogin" value="N" ${fornecedor.permiteLogin == 'N'?'checked':''}> Não
            <br />
            <input type="submit" value="Salvar">
        </form>
        <h3>${mensagem}</h3>   
        
        <h1>Lista de Fornecedores</h1>
        <table>
            <thead>
                <tr>
                    <th>Id Pessoa</th>
                    <th>Id Fornecedor</th>
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
                
                <c:forEach items="${listafornecedor}" var="for">
                    <tr>
                        <td>${for.idPessoa}</td>
                        <td>${for.idFornecedor}</td>
                        <td>${for.nome}</td>
                        <td>${for.cpf}</td>
                        <td>${for.datanascimento}</td>
                        <td>${for.login}</td>
                        <td>${for.senha}</td>
                        <td>${for.permiteLogin}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/ExcluirFornecedor?idpessoa=${for.idPessoa}">
                                Excluir
                            </a>
                            <a href="${pageContext.request.contextPath}/CarregarFornecedor?idpessoa=${for.idPessoa}">
                                Alterar
                            </a>
                        </td>     
                    </tr>
                </c:forEach>
            </tbody>            
        </table>
    </body>
</html>
