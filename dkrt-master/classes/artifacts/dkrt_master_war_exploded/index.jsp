<!DOCTYPE html>
<html lang="pt-br">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <style>
        <%@include file="WEB-INF/estilo/estilo.css" %>
    </style>

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>


    <title>DKRT - Login</title>
</head>
<body>

<img src="..\logo.png" class="logo"/>
<div class="login">
    <form method="post" action="controller?acao=login">
        <input type="hidden" value="${erro}" id="msgErro">
        <label>USUÁRIO<input type="text" name="loginUsuario" placeholder="Usuário" required class="form-control"></label><br><br>
        <label>SENHA<input type="password" name="senhaUsuario" placeholder="Senha" required CLASS="form-control"></label><br><br>
        <input value="Logar" type="submit" class="btn btn-success"/><br>
    </form>
</div>

<script>
    var erro = document.getElementById("msgErro").value;
    if (erro == "sim"){
        alert("Usuário e/ou senha inválido(s)");
    }
</script>
</body>
</html>