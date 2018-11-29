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

    <table width="90%" align="center" cellpadding="10">

        <tr align="center" bgcolor="#EDEDED">
            <td><strong>ID do produto</strong></td>
            <td><strong>Produto</strong></td>
            <td><strong>Descrição</strong></td>
            <td><strong>Preço de Venda</strong></td>
            <td><strong>Preço de Custo</strong></td>
            <td><strong>Quantidade em Estoque</strong></td>
            <td><strong>Ativo</strong></td>
            <td><strong>Data de Cadastro</strong></td>
            <td><strong>Última modificação</strong></td>
            <td colspan="2"><strong>Ação</strong></td>
        </tr>
         <c:forEach var="produto" items="${listaProdutos}">
            <tr style="text-align: center; background-color: gainsboro;" class="border_bottom">
                <td>${produto.id}</td>
                <td>${produto.nome}</td>
                <td>${produto.descricao}</td>
                <td>${produto.precoVenda}</td>
                <td>${produto.precoCusto}</td>
                <td>${produto.qtdEstoque}</td>
                <td>${produto.ativo}</td>
                <td><fmt:formatDate value="${produto.dataCadastro}" pattern="dd/MM/yyyy"/></td>
                <td><fmt:formatDate value="${produto.dataAlteracao}" pattern="dd/MM/yyyy"/></td>
                <td class="editar"><a href="controller?acao=estoque&tipo=alteraProduto&idProduto=${produto.id}">EDITAR</a></td>
                <td class="editar"><a href="controller?acao=estoque&tipo=excluiProduto&idProduto=${produto.id}">EXCLUIR</a></td>
            </tr>
        </c:forEach>
    </table>
</center>

</body>
</html>
