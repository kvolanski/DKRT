<!DOCTYPE html>
<html lang="">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>

    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>
    <title>D.K.R.T</title>
</head>

<body>

<%@include file="/WEB-INF/navbar/navbar.jsp" %>

<h1 class="geral">VENDAS</h1><br>
<c:if test="${mostraCliente == 'sim'}">
    <form method="get" action="controller">
        <input type="hidden" value="venda" name="acao">
        <input type="hidden" value="abrirVenda" name="tipo">
        Escolha um cliente para realizar a venda:
        <select name="idCliente">
            <c:forEach var="cliente" items="${listaClientes}">
                <option value="${cliente.id}">${cliente.nome}</option>
            </c:forEach>
        </select><br><br>
        <input type="submit" value="Abrir Venda">
    </form>
</c:if>

<br><br>
<center>
    <c:if test="${clienteBusca.id != 0}">
        <label>Número da Venda:<input type="text" value="" style="text-align: center" name="idVenda"
                                      class="form-control" disabled="disabled"/></label><br>
        <label>Nome:<input type="text" name="nomeCliente" class="form-control" value="${clienteBusca.nome}"
                           disabled="disabled"/></label>
        <label>Telefone Principal:<input type="text" name="celularCliente" class="form-control"
                                         value="${clienteBusca.celular}" disabled="disabled"/></label><br><br>

        <form method="get" action="controller">
            <input type="hidden" value="venda" name="acao">
            <input type="hidden" value="buscaProduto" name="tipo">
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

        <form method="post" action="controller">
            <input type="hidden" value="venda" name="acao">
            <input type="hidden" value="adicionarPedido" name="tipo">
            <input type="hidden" value="${produtoBusca.id}" name="idProduto">
            <label>Descricao:<input value="${produtoBusca.descricao}" type="text" name="descricaoProduto"
                                    class="form-control" disabled="disabled"></label>
            <label>Quantidade:<input type="number" name="quantidadeProduto" class="form-control"></label>
            <label>Valor Un.:<input value="${produtoBusca.precoVenda}" type="number" name="valorUnitProduto"
                                    class="form-control" step="any"></label><br>
            <input value="Limpar Campos" type="reset" class="btn btn-danger"/>
            <input value="Adicionar" type="submit" class="btn btn-success"/>
        </form>

        <table>
            <tr>
                <th>Produto</th>
                <th>Descrição</th>
                <th>Quantidade</th>
                <th>Valor Unit.</th>
                <th>Valor Total</th>
            </tr>
            <c:forEach var="pedido" items="${listaPedido}">
                <tr>
                    <td>${pedido.produtoDTO.nome}</td>
                    <td>${pedido.produtoDTO.descricao}</td>
                    <td>${pedido.quantidade}</td>
                    <td>${pedido.valorUnitario}</td>
                    <td>${pedido.valorTotal}</td>
                </tr>
            </c:forEach>
        </table>
        <br><br>
        <form method="get" action="controller">
            <input type="hidden" value="venda" name="acao">
            <input type="hidden" value="finalizarVenda" name="tipo">
            <input type="submit" value="Finalizar Venda">
        </form>

    </c:if>
</center>
</body>
</html>
