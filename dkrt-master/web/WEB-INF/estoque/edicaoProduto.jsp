<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 25/11/2018
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Edição Produto nº ${produtoBusca.id}</title>
    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>
</head>
<body>
<center>
    <form method="post" action="controller">
        <input type="hidden" value="estoque" name="acao"><br><br>
        <input type="hidden" value="editarProduto" name="tipo"><br><br>
        <input type="hidden" value="edicao" name="tipoAlteracao"><br><br>
        <input type="hidden" value="${produtoBusca.id}" name="idProduto"><br><br>
        <input type="text" value="${produtoBusca.nome}" name="nomeProduto"><br><br>
        <input type="text" value="${produtoBusca.descricao}" name="descricaoProduto"><br><br>
        <input type="number" value="${produtoBusca.qtdEstoque}" name="quantidadeProduto"><br><br>
        <input type="number" value="${produtoBusca.precoVenda}" name="precoVendaProduto" step="any"><br><br>
        <input type="number" value="${produtoBusca.precoCusto}" name="precoCustoProduto" step="any"><br><br>
        <input type="submit" value="Enviar Alterações">
    </form>
</center>

</body>
</html>
