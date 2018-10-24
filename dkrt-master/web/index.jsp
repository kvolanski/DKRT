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


    <title>login</title>
</head>
<body>

<img src="logo.png" class="logo"/>
<div class="login">

    <label>USUÁRIO<input type="text" name="user" placeholder="USUÁRIO" required class="form-control"
                         value="admin"></label><br><br>
    <label>SENHA<input type="password" name="user" placeholder="SENHA" required CLASS="form-control"
                       value="admin"></label><br>
    <a href="home.html"><input value="ENTRAR" type="button" class="btn btn-success"/></a><br>
</div>


</body>
</html>