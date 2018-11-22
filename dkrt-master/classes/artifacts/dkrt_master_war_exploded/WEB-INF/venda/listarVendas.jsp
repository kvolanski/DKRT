<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 22/11/2018
  Time: 02:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Listagem de Vendas</title>
    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/navbar/navbar.jsp" %>

<center>
    <table>
        <tr>
            <th>Num. Venda</th>
            <th>Nome Cliente</th>
            <th>CPF Cliente</th>
            <th>Valor da Venda</th>
            <th>Forma de Pagamento</th>
            <th>Num. Parcelas</th>
            <th>Status</th>
            <th>Desconto</th>
            <th>Data da Venda</th>
            <th colspan="2">Ação</th>
        </tr>
        <c:forEach var="venda" items="${listaVendas}">
            <tr>
                <td>${venda.id}</td>
                <td>${venda.clienteDTO.nome}</td>
                <td>${venda.clienteDTO.cpf}</td>
                <td>${venda.valorTotal}</td>
                <td>${venda.formaDePagamento}</td>
                <td>${venda.parcelas}</td>
                <td>${venda.status}</td>
                <td>${venda.desconto}</td>
                <td><fmt:formatDate value="${venda.dataDeVenda}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                <td>Abrir</td>
            </tr>
        </c:forEach>
    </table>
</center>

</body>
</html>
