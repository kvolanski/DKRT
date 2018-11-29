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

    <br><br>
<form method="post" action="controller">
    <input type="hidden" value="venda" name="acao">
    <input type="hidden" value="finalizarVenda" name="tipo">
    <select class="formInterno" style="width: 300px;" name="formaDePagamento" id="pagamento" onmouseup="selecionaForma(), habilitaBotao()">
        <option value="semForma">Selecione a forma de pagamento</option>
        <option value="Debito">Débito</option>
        <option value="Credito">Crédito</option>
    </select>
    <br>
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
    <label>Desconto(%): <input type="number" name="descontoVenda" value="${vendaBusca.desconto}" onkeyup="updateValorTotal()" onclick="updateValorTotal()" id="desconto" required></label><br>
    <br><br>
    <input type="hidden" value="${valorTotal}" id="valorTotal">
    <label class="txt2">Valor Total: </label><label class="txt" id="displayValorTotal"></label>

    <br><br><br>
    <input class="btnInterno" type="submit" value="Fechar Venda" id="enviarInf" disabled class="btn btn-success">
</form>
</center>

<script>
    var valorTotal = document.getElementById("valorTotal").value;
    document.getElementById("displayValorTotal").innerHTML = valorTotal;

    function habilitaBotao() {
        var forma = document.getElementById("pagamento").value;

        if (forma == "semForma"){
            document.getElementById("enviarInf").disabled = true;
        } else {
            document.getElementById("enviarInf").disabled = false;
        }
    }

    function selecionaForma(){
        var forma = document.getElementById("pagamento").value;

        if (forma == "Credito"){
            document.getElementById("numParcelas").hidden = false;
        } else {
            document.getElementById("numParcelas").hidden = true;
        }
    }

    function updateValorTotal() {
        var valorTotal = document.getElementById("valorTotal").value;
        var desconto = document.getElementById("desconto").value;

        valorTotal = valorTotal - ((valorTotal*desconto)/100);

        if (desconto < 0 || desconto > 100){
            alert("Não é possível dar descontos menores que 0 ou maiores que 100");
            document.getElementById("desconto").value = 0;
        } else {
            document.getElementById("displayValorTotal").innerHTML = valorTotal;
        }

    }
</script>
</body>
</html>
