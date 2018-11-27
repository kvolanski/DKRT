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
    <h1><label>Número da venda: ${vendaBusca.id}</label></h1>
    <h2>Detalhes da venda</h2>
    <fieldset>
        <legend>Cliente</legend>
        <label>Nome do cliente: ${vendaBusca.clienteDTO.nome}</label><br>
        <label>Data de Nascimento: <fmt:formatDate value="${vendaBusca.clienteDTO.dtNascimento}"
                                                   pattern="dd/MM/yyyy"/></label><br>
        <label>RG: ${vendaBusca.clienteDTO.rg}</label><br>
        <label>CPF: ${vendaBusca.clienteDTO.cpf}</label><br>
        <label>Email: ${vendaBusca.clienteDTO.email}</label><br>
        <label>Telefone Principal: ${vendaBusca.clienteDTO.celular}</label><br>
        <label>Telefone: ${vendaBusca.clienteDTO.telefone}</label><br>
    </fieldset>
    <br>
    <fieldset>
        <legend>Venda</legend>
        <label>Valor total: ${vendaBusca.valorTotal}</label><br>
        <label>Forma de pagamento: ${vendaBusca.formaDePagamento}</label><br>
        <label>Número de parcelas(Caso Crédito): ${vendaBusca.parcelas}</label><br>
        <label>Valor da parcela(Caso Crédito): ${vendaBusca.valorParcelas}</label><br>
        <label>Desconto(%): ${vendaBusca.desconto}</label><br>
    </fieldset>
    <br>
    <fieldset>
        <legend>Lista de Produtos</legend>
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
    </fieldset>
</center>

</body>
</html>
