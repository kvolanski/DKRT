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
    <table width="90%" align="center" cellpadding="10">
        <tr align="center" bgcolor="#EDEDED">
            <td><strong>ID</strong></td>
            <td><strong>Nome</strong></td>
            <td><strong>RG</strong></td>
            <td><strong>CPF</strong></td>
            <td><strong>Data de Nascimento</strong></td>
            <td><strong>Email</strong></td>
            <td><strong>Telefone Principal</strong></td>
            <td><strong>Telefone Secundário</strong></td>
            <td><strong>Data de Cadastro</strong></td>
            <td><strong>Data de Alteração</strong></td>
            <td><strong>Observação</strong></td>
            <td><strong>Número de Compras</strong></td>
            <td><strong>Rua</strong></td>
            <td><strong>Cep</strong></td>
            <td><strong>Número</strong></td>
            <td><strong>Bairro</strong></td>
            <td><strong>Cidade</strong></td>
            <td><strong>Estado</strong></td>
            <td colspan="2"><strong>Ação</strong></td>
        </tr>
        <c:forEach var="cliente" items="${listaClientes}">
            <c:if test="${cliente.ativo != 0}">
                <tr align="center" bgcolor="#EDEDED">
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
                    <td class="editar"><a href="controller?acao=pesquisa&tipo=editarCliente&id=${cliente.id}">Editar</a></td>
                    <td class="cancelar"><a href="controller?acao=pesquisa&tipo=excluirCliente&id=${cliente.id}">Excluir</a></td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</center>

</body>
</html>
