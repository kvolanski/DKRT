<!DOCTYPE html>
<html lang="">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <title>Pesquisa de Produto - D.K.R.T</title>
    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/navbar/navbar.jsp" %>

<h1 class="geral">PESQUISAR PRODUTO</h1><br>

<span class="geral">
    <form method="get" action="controller">
        <input type="hidden" value="pesquisa" name="acao">
        <input type="hidden" value="produtoPesquisa" name="tipo">
        <input value="Pesquisar" type="button" class="btn btn-success"/>
    </form>
</span>

</body>
</html>
