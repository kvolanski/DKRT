<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 27/11/2018
  Time: 09:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Orçamento nº ${orcamentoBusca.id}</title>

    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/navbar/navbar.jsp" %>
<center>
    <h1>Orçamento número ${orcamentoBusca.id}</h1>
    <h2>Lista de produtos</h2><br>
    <table width="90%" align="center" cellpadding="10">
        <tr align="center" bgcolor="#EDEDED">
            <td><strong>Produto</strong></td>
            <td><strong>Descrição</strong></td>
            <td><strong>Quantidade</strong></td>
            <td><strong>Valor Unit.</strong></td>
            <td><strong>Valor Total</strong></td>
        </tr>
        <c:forEach var="pedido" items="${listaPedido}">
            <tr align="center" bgcolor="#EDEDED">
                <td>${pedido.produtoDTO.nome}</td>
                <td>${pedido.produtoDTO.descricao}</td>
                <td>${pedido.quantidade}</td>
                <td>${pedido.valorUnitario}</td>
                <td>${pedido.valorTotal}</td>
            </tr>
        </c:forEach>
    </table>
    <br><br><br>
    <hr>
    <br><br>
    <form method="post" action="controller">
        <input type="hidden" value="orcamento" name="acao">
        <input type="hidden" value="finalizarOrcamento" name="tipo">
        <label class="txt2">Escolher data de expiração do Orçamento <input type="checkbox" id="expiracao"></label><br><br><br>
        <label id="displayData" hidden><input type="date" onchange="validaData()" id="dataExpiracao" name="dataExpiracao"></label>
        <label>Desconto(%): <input type="number" name="descontoOrcamento" value="0" onkeyup="updateValorTotal()" onclick="updateValorTotal()" id="desconto" required></label><br>
        <br>
        <input type="hidden" value="${valorTotal}" id="valorTotal">
        <label class="txt2">Valor Total: </label><label class="txt" id="displayValorTotal"></label>
        <br><br><br>
        <input type="submit" value="Fechar Orçamento" id="enviarInf" class="btnInterno">
    </form>
</center>

<script>
    var valorTotal = document.getElementById("valorTotal").value;
    document.getElementById("displayValorTotal").innerHTML = valorTotal;

    function pegaDataExpiracao() {
        var dataExpiracao = document.getElementById("dataExpiracao").value;

        return dataExpiracao;
    }

    function validaData() {

        var dataExpiracao = pegaDataExpiracao();

        dataExpiracao = new Date(dataExpiracao);

        var dataAtual = new Date();

        dataAtual.setHours(22);
        dataAtual.setMinutes(0);
        dataAtual.setSeconds(0);


        if(dataExpiracao  < dataAtual){
            alert("Data da Expiração não pode ser menor que a data atual!");
            document.getElementById('dataExpiracao').value = "";
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

    document.getElementById("expiracao").addEventListener('click', function () {
        var check = document.getElementById("expiracao").checked;

        if (check == true){
            document.getElementById("displayData").hidden = false;
        } else {
            document.getElementById("displayData").hidden = true;
        }
    })
</script>
</body>
</html>
