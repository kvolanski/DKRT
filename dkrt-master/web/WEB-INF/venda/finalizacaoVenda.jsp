<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 19/11/2018
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Finalizar Venda</title>
    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/navbar/navbar.jsp" %>
<center>
<h1>Venda número ${idVenda}</h1>
<h2>Lista de produtos</h2><br>
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
<br><br><br>
<form method="post" action="controller">
    <input type="hidden" value="venda" name="acao">
    <input type="hidden" value="finalizarVenda" name="tipo">
    <select name="formaDePagamento" id="pagamento" onmouseup="selecionaForma()">
        <option>Selecione a forma de pagamento</option>
        <option value="debito">Débito</option>
        <option value="credito">Crédito</option>
    </select>
    <label id="numParcelas" hidden>Número de Parcelas:
    <select name="numeroParcelasVenda">
        <option value="1">1x</option>
        <option value="2">2x</option>
        <option value="3">3x</option>
        <option value="4">4x</option>
        <option value="5">5x</option>
        <option value="6">6x</option>
        <option value="7">7x</option>
        <option value="8">8x</option>
        <option value="9">9x</option>
        <option value="10">10x</option>
        <option value="11">11x</option>
        <option value="12">12x</option>
    </select>
    </label>
    <label>Desconto: <input type="text" name="descontoVenda"></label><br>
    <input type="submit" value="Fechar Venda">
</form>
</center>

<script>
    function selecionaForma(){
        var forma = document.getElementById("pagamento").value;

        if (forma == "credito"){
            document.getElementById("numParcelas").hidden = false;
        } else {
            document.getElementById("numParcelas").hidden = true;
        }
    }
</script>
</body>
</html>
