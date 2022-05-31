<%-- 
    Document   : index.jsp
    Created on : 05/11/2021, 11:08:13
    Author     : Usuário
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Site do Gustavo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h3>${saudacao}</h3>
        <a href="ListarAdministrador">Gerenciar Administradores</a><br/>
        <a href="ListarFornecedor">Gerenciar Fornecedor</a><br/>
        <a href="ListarCliente">Gerenciar Cliente</a>
        <a href="LogarAdministrador">Login Administrador</a>
        <h3><a href="LogarAdministrador?acao=logout">Sair</a></h3>
    </body>
</html>
