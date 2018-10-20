<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 01/10/2018
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="functions.js"></script>

<head>
    <title>DKRT</title>
</head>
<body>

<center>
<form method="post" action="controller?acao=login">
    Login
    <input type="text" name="usuario" style="width: 300px;" required>
    <br><br>
    Senha
    <input type="password" name="senha" style="width: 300px;" required>
    <br><br>
    <input type="submit" value="Logar">

</form>
</center>

</body>
</html>
