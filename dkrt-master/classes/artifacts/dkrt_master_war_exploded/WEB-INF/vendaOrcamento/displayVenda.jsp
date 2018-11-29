<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 22/11/2018
  Time: 03:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Venda ${vendaBusca.id}</title>
    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/navbar/navbar.jsp" %>
<center>
    <h1><label class="title">Número da venda: ${vendaBusca.id}</label></h1>
    <h2>Detalhes da venda</h2>
    <table width="90%" align="center" cellpadding="10">
        <td><h3>Dados do Cliente</h3></td>
        <tr align="center" bgcolor="#EDEDED">
            <td><strong>Nome do cliente</strong></td>
            <td><strong>Data de Nascimento</strong></td>
            <td><strong>RG</strong></td>
            <td><strong>CPF</strong></td>
            <td><strong>Email</strong></td>
            <td><strong>Telefone Principal</strong></td>
            <td><strong>Telefone</strong></td>

        </tr>

        <tr style="text-align: center; background-color: gainsboro;" class="border_bottom">
            <td>${vendaBusca.clienteDTO.nome}</td>
            <td><fmt:formatDate value="${vendaBusca.clienteDTO.dtNascimento}"
                                pattern="dd/MM/yyyy"/></td>
            <td>${vendaBusca.clienteDTO.rg}</td>
            <td>${vendaBusca.clienteDTO.cpf}</td>
            <td>${vendaBusca.clienteDTO.email}</td>
            <td>${vendaBusca.clienteDTO.celular}</td>
            <td>${vendaBusca.clienteDTO.telefone}</td>
        </tr>
    </table>
<br><br>
    <table width="90%" align="center" cellpadding="10">
        <td><h3>Dados da Venda</h3></td>
            <tr align="center" bgcolor="#EDEDED">
                <td><strong>Valor total</strong></td>
                <td><strong>Forma de pagamento</strong></td>
                <td><strong>Número de parcelas(Caso Crédito)</strong></td>
                <td><strong>Valor da parcela(Caso Crédito)</strong></td>
                <td><strong>Desconto(%)</strong></td>
            </tr>

            <tr style="text-align: center; background-color: gainsboro;" class="border_bottom">
                <td>${vendaBusca.valorTotal}</td>
                <td>${vendaBusca.formaDePagamento}</td>
                <td>${vendaBusca.parcelas}</td>
                <td>${vendaBusca.valorParcelas}</td>
                <td>${vendaBusca.desconto}</td>
            </tr>
    </table>
    <br><br>

    <table width="90%" align="center" cellpadding="10">
        <td><h3>Lista de Produtos</h3></td>
        <tr align="center" bgcolor="#EDEDED">
            <td><strong>Produto</strong></td>
            <td><strong>Descrição</strong></td>
            <td><strong>Quantidade</strong></td>
            <td><strong>Valor Unit.</strong></td>
            <td><strong>Valor Total</strong></td>
        </tr>

        <c:forEach var="pedido" items="${listaPedido}">

            <tr style="text-align: center; background-color: gainsboro;" class="border_bottom">
                <td>${pedido.produtoDTO.nome}</td>
                <td>${pedido.produtoDTO.descricao}</td>
                <td>${pedido.quantidade}</td>
                <td>${pedido.valorUnitario}</td>
                <td>${pedido.valorTotal}</td>
            </tr>
        </c:forEach>
    </table>

</center>

</body>
</html>
