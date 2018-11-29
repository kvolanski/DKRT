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
    <table width="90%" align="center" cellpadding="10">
        <tr align="center" bgcolor="#EDEDED">
            <td><strong>Nº Orçamento</strong></td>
            <td><strong>Nome do Cliente</strong></td>
            <td><strong>Email</strong></td>
            <td><strong>Telefone</strong></td>
            <td><strong>Valor do Orçamento</strong></td>
            <td><strong>Desconto(%)</strong></td>
            <td><strong>Expiração</strong></td>
            <td><strong>Status</strong></td>
            <td colspan="3"><strong>Ação</strong></td>
        </tr>
        <c:forEach var="orcamento" items="${listaOrcamentos}">
            <tr align="center" bgcolor="#EDEDED">
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
                        <td class="finalziar"><a href="controller?acao=orcamento&tipo=comecarVendaOrcamento&id=${orcamento.id}">Vender</a>
                        </td>
                        <td class="cancelar"><a href="controller?acao=orcamento&tipo=excluirOrcamento&id=${orcamento.id}">Excluir</a>
                        </td>
                    </c:if>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</center>
</body>
</html>
