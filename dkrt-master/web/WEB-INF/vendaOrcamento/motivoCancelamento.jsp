<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 23/11/2018
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Motivo do Cancelamento</title>
    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/navbar/navbar.jsp" %>
<center>
    <h1>Cancelamento da venda ${idVenda}</h1>
    <form method="post" action="controller">
        <input type="hidden" value="venda" name="acao">
        <input type="hidden" value="cancelamentoMotivo" name="tipo">
        <label class="txt2">
            Digite o motivo do cancelamento da venda de número: ${idVenda}
            <br><br>
            <input class="formInterno" style="width: 300px" type="text" name="motivoCancelamento">
        </label><br>
        <input type="submit" value="Enviar" class="btnInterno">
    </form>
</center>

</body>
</html>
