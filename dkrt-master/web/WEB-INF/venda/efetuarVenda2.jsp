<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 16/11/2018
  Time: 00:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>
    <title>D.K.R.T</title>
</head>
<body>
<%@include file="/WEB-INF/navbar/navbar.jsp" %>

<%--<label>Número da Venda:<input type="text" value="" style="text-align: center" name="idVenda"--%>
                              <%--class="form-control" disabled="disabled"/></label><br>--%>
<%--<label>Nome:<input type="text" name="nomeCliente" class="form-control" value="${clienteBusca.nome}"--%>
                   <%--disabled="disabled"/></label>--%>
<%--<label>Telefone Principal:<input type="text" name="celularCliente" class="form-control"--%>
                                 <%--value="${clienteBusca.celular}" disabled="disabled"/></label><br><br>--%>

    <form method="post" action="controller?acao=venda&tipo=buscaProduto">
        <input type="hidden" value="${clienteBusca.id}" id="idCliente">
        <label>Produto:
            <select id="" name="idProduto">
                <option>Selecione um produto.</option>
                <c:forEach var="produto" items="${listaProdutos}">
                    <option value="${produto.id}">${produto.nome}</option>
                </c:forEach>
            </select>
        </label>
        <input type="submit" value="Carregar Produto"><br><br>
    </form>

</body>
</html>
