<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 22/11/2018
  Time: 02:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Listagem de Vendas</title>
    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/navbar/navbar.jsp" %>

<center>
    <table width="90%" align="center" cellpadding="10">
        <c:if test="${tipoStatus == 'normal'}">
            <tr align="center" bgcolor="#EDEDED">
                <td><strong>Num. Venda</strong></td>
                <td><strong>Nome Cliente</strong></td>
                <td><strong>CPF</strong></td>
                <td><strong>Valor da Venda</strong></td>
                <td><strong>Forma de Pagamento</strong></td>
                <td><strong>Num. Parcelas</strong></td>
                <td><strong>Status</strong></td>
                <td><strong>Desconto(%)</strong></td>
                <td><strong>Data da Venda</strong></td>
                <th colspan="3">Ação</th>
            </tr>
        </c:if>
        <c:if test="${tipoStatus == 'cancelada'}">
            <tr >
                <th>Num. Venda</th>
                <th>Nome Cliente</th>
                <th>CPF</th>
                <th>Status</th>
                <th colspan="3">Motivo</th>
            </tr>
        </c:if>
        <c:forEach var="venda" items="${listaVendas}">
            <c:if test="${tipoStatus == 'normal'}">
                <c:if test="${venda.status != 'Cancelada' && venda.status != 'Incompleta'}">
                    <tr style="text-align: center; background-color: gainsboro;" class="border_bottom">
                        <td>${venda.id}</td>
                        <td>${venda.clienteDTO.nome}</td>
                        <td>${venda.clienteDTO.cpf}</td>
                        <td>${venda.valorTotal}</td>
                        <td>${venda.formaDePagamento}</td>
                        <td>${venda.parcelas}</td>
                        <td>${venda.status}</td>
                        <td>${venda.desconto}</td>
                        <td><fmt:formatDate value="${venda.dataDeVenda}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                        <c:choose>
                            <c:when test="${venda.status == 'Completa'}">
                              <td class="abrir" colspan="3"><a href="controller?acao=venda&tipo=buscaVenda&id=${venda.id}">Abrir</a></td>

                            </c:when>
                            <c:when test="${venda.status == 'Em aberto'}">
                                <td class="finalziar">
                                    <a href="controller?acao=venda&tipo=finalizarVendaEmAberto&id=${venda.id}">Finalizar</a>
                                </td>
                                <td class="editar">
                                    <a href="controller?acao=venda&tipo=adicionarProdutosEmAberto&id=${venda.id}&idCliente=${venda.clienteDTO.id}">Adicionar
                                        Produtos</a></td>
                                <td class="cancelar"><a href="controller?acao=venda&tipo=cancelarVenda&id=${venda.id}">Cancelar</a></td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:if>
            </c:if>
            <c:if test="${tipoStatus == 'cancelada'}">
                <c:if test="${venda.status == 'Cancelada' || venda.status == 'Incompleta'}">
                    <tr>
                        <td>${venda.id}</td>
                        <td>${venda.clienteDTO.nome}</td>
                        <td>${venda.clienteDTO.cpf}</td>
                        <td>${venda.status}</td>
                        <td colspan="3">${venda.motivoCancelamento}</td>
                        <td><fmt:formatDate value="${venda.dataDeVenda}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                        <c:choose>
                            <c:when test="${venda.status == 'Completa'}">
                                <td colspan="3"><a href="controller?acao=venda&tipo=buscaVenda&id=${venda.id}">Abrir</a>
                                </td>
                            </c:when>
                            <c:when test="${venda.status == 'Em aberto'}">
                                <td>
                                    <a href="controller?acao=venda&tipo=finalizarVendaEmAberto&id=${venda.id}">Finalizar</a>
                                </td>
                                <td>
                                    <a href="controller?acao=venda&tipo=adicionarProdutosEmAberto&id=${venda.id}&idCliente=${venda.clienteDTO.id}">Adicionar
                                        Produtos</a></td>
                                <td><a href="controller?acao=venda&tipo=cancelarVenda&id=${venda.id}">Cancelar</a></td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:if>
            </c:if>
        </c:forEach>
    </table>
</center>

</body>
</html>
