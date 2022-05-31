<%-- 
    Document   : logarcliente
    Created on : 25/11/2021, 14:26:30
    Author     : Usuário
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Site do Gustavo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <script src="jv/js.js" type="text/javascript"></script>
        <style>
            form{
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
            }
        </style>    

    </head>
    <body>
        <h2 align="center">LOGIN</h2>
        <br />
        <form action="LogarCliente" method="POST">
            <input type="text" placeholder="Login" name="loginpessoa">
            <br /><br />
            <input type="password" placeholder="Senha" name="senhapessoa">
            <br /><br />
            <input type="hidden" name="acao" value="logar"/>
            <input type="submit" value="logar">
            
            <h3>${menssagem}</h3>
        </form>
    </body>
</html>