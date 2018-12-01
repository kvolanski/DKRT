<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 30/11/2018
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Listagem de Clientes - DKRT</title>
    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/navbar/navbar.jsp" %>

<center>
    <table style="text-align: center">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>RG</th>
            <th>CPF</th>
            <th>Data de Nascimento</th>
            <th>Email</th>
            <th>Telefone Principal</th>
            <th>Telefone Secundário</th>
            <th>Data de Cadastro</th>
            <th>Data de Alteração</th>
            <th>Observação</th>
            <th>Número de Compras</th>
            <th>Rua</th>
            <th>Cep</th>
            <th>Número</th>
            <th>Bairro</th>
            <th>Cidade</th>
            <th>Estado</th>
            <th>Ação</th>
        </tr>
        <c:forEach var="cliente" items="${listaClientes}">
            <c:if test="${cliente.ativo != 0}">
                <tr>
                    <td>${cliente.id}</td>
                    <td>${cliente.nome}</td>
                    <td>${cliente.rg}</td>
                    <td>${cliente.cpf}</td>
                    <td><fmt:formatDate value="${cliente.dtNascimento}" pattern="dd/MM/yyyy"/></td>
                    <td>${cliente.email}</td>
                    <td>${cliente.celular}</td>
                    <td>${cliente.telefone}</td>
                    <td><fmt:formatDate value="${cliente.dataCadastro}" pattern="dd/MM/yyyy"/></td>
                    <td><fmt:formatDate value="${cliente.dataAlteracao}" pattern="dd/MM/yyyy"/></td>
                    <td>${cliente.observacao}</td>
                    <td>${cliente.numeroCompras}</td>
                    <td>${cliente.enderecoDTO.rua}</td>
                    <td>${cliente.enderecoDTO.cep}</td>
                    <td>${cliente.enderecoDTO.numero}</td>
                    <td>${cliente.enderecoDTO.bairro}</td>
                    <td>${cliente.enderecoDTO.cidade}</td>
                    <td>${cliente.enderecoDTO.ufDTO.sigla}</td>
                    <td><a href="controller?acao=pesquisa&tipo=excluirCliente&id=${cliente.id}">Excluir</a></td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</center>

</body>
</html>
