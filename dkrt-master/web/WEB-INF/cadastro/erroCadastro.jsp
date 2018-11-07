<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 06/11/2018
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>D.K.R.T - ERRO</title>
    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>
</head>

<body>

<%@include file="/WEB-INF/navbar/navbar.jsp" %>
<c:set var="condicao" value="${condicao}"/>
<center>
    <c:if test="${fn:containsIgnoreCase(condicao, 'nomeCliente')}">
        <div>O nome não pode conter números.</div>
        <br><br>
    </c:if>

    <c:if test="${fn:containsIgnoreCase(condicao, 'cpfCliente')}">
        <div>O numero de CPF não pode conter letras.</div>
        <br><br>
    </c:if>

    <c:if test="${fn:containsIgnoreCase(condicao, 'telefoneCliente')}">
        <div>O numero de telefone não pode conter letras.</div>
        <br><br>
    </c:if>

    <c:if test="${fn:containsIgnoreCase(condicao, 'cepEndereco')}">
        <div>O CEP do endereço não pode conter letras.</div>
        <br><br>
    </c:if>

    <c:if test="${fn:containsIgnoreCase(condicao, 'numEndereco')}">
        <div>O número do endereço não pode conter letras.</div>
        <br><br>
    </c:if>

    <c:if test="${fn:containsIgnoreCase(condicao, 'ufIncorreto')}">
        <div>UF inválido. Por favor, escolha uma opção válida.</div>
        <br><br>
    </c:if>

    <c:if test="${fn:containsIgnoreCase(condicao, 'numNegativo')}">
        <div>Quantidade em estoque e/ou preço do produto não pode(m) ser negativo(s).</div>
        <br><br>
    </c:if>

    <c:if test="${tipoCadastro == 'cliente'}">
        <a href="controller?acao=cadastro&tipo=cliente">
            <button>Voltar</button>
        </a>
    </c:if>

    <c:if test="${tipoCadastro == 'produto'}">
        <a href="controller?acao=cadastro&tipo=produto">
            <button>Voltar</button>
        </a>
    </c:if>
</center>


</body>
</html>
