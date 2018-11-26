<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 25/11/2018
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Estoque</title>
    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/navbar/navbar.jsp" %>

<center>
    <table style="text-align: center">
        <tr>
            <th>ID do produto</th>
            <th>Produto</th>
            <th>Descrição</th>
            <th>Preço de Venda</th>
            <th>Preço de Custo</th>
            <th>Quantidade em Estoque</th>
            <th>Ativo</th>
            <th>Data de Cadastro</th>
            <th>Última modificação</th>
            <th>Ação</th>
        </tr>
        <c:forEach var="produto" items="${listaProdutos}">
            <tr>
                <td>${produto.id}</td>
                <td>${produto.nome}</td>
                <td>${produto.descricao}</td>
                <td>${produto.precoVenda}</td>
                <td>${produto.precoCusto}</td>
                <td>${produto.qtdEstoque}</td>
                <td>${produto.ativo}</td>
                <td><fmt:formatDate value="${produto.dataCadastro}" pattern="dd/MM/yyyy"/></td>
                <td><fmt:formatDate value="${produto.dataAlteracao}" pattern="dd/MM/yyyy"/></td>
                <td><a href="controller?acao=estoque&tipo=alteraProduto&idProduto=${produto.id}">Editar</a></td>
            </tr>
        </c:forEach>
    </table>
</center>

</body>
</html>
