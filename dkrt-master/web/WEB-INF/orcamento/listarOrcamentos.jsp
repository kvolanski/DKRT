<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 27/11/2018
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Listagem de Orçamentos</title>
    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/navbar/navbar.jsp" %>

<center>
    <table>
        <tr>
            <th>Nº Orçamento</th>
            <th>Nome do Cliente</th>
            <th>Email</th>
            <th>Telefone</th>
            <th>Valor do Orçamento</th>
            <th>Desconto(%)</th>
            <th>Expiração</th>
            <th>Status</th>
            <th colspan="3">Ação</th>
        </tr>
        <c:forEach var="orcamento" items="${listaOrcamentos}">
            <tr>
                <c:if test="${orcamento.status != 'Incompleto'}">
                    <td>${orcamento.id}</td>
                    <td>${orcamento.clienteDTO.nome}</td>
                    <td>${orcamento.clienteDTO.email}</td>
                    <td>${orcamento.clienteDTO.celular}</td>
                    <td>${orcamento.valorTotal}</td>
                    <td>${orcamento.desconto}</td>
                    <c:if test="${orcamento.dataExpiracao == null}">
                        <td>N/A</td>
                    </c:if>
                    <c:if test="${orcamento.dataExpiracao != null}">
                        <td><fmt:formatDate value="${orcamento.dataExpiracao}" pattern="dd/MM/yyyy"/></td>
                    </c:if>
                    <td>${orcamento.status}</td>
                    <c:if test="${orcamento.status != 'Expirado' && orcamento.status != 'Vendido'}">
                        <td><a href="controller?acao=orcamento&tipo=comecarVendaOrcamento&id=${orcamento.id}">Vender</a>
                        </td>
                        <td><a href="controller?acao=orcamento&tipo=excluirOrcamento&id=${orcamento.id}">Excluir</a>
                        </td>
                    </c:if>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</center>
</body>
</html>
