<!DOCTYPE html>
<html lang="">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <title>Pesquisa de Cliente - D.K.R.T</title>
    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/navbar/navbar.jsp" %>

<h1 class="geral">PESQUISAR CLIENTE</h1><br>

<span class="geral">
    <form method="get" action="controller">
        <input type="radio" value="checkNome" id="checkNome" name="checkTudo" onclick="validaTipoPesquisa()"> Pesquisar por nome
        <input type="radio" value="checkId" id="checkId" name="checkTudo" onclick="validaTipoPesquisa()"> Pesquisar por id
        <input type="radio" value="checkNumCompras" id="checkNumCompras" name="checkTudo"
               onclick="validaTipoPesquisa()"> Listar por número de compras
        <input type="hidden" value="pesquisa" name="acao">
        <input type="hidden" value="clientePesquisa" name="tipo">
        <br><br><label id="nomePesquisa" hidden>Nome: <input type="text" id="inputNomePesquisa" name="nomePesquisa"
                                                             class="form-control"></label>
        <label id="idPesquisa" hidden>Id: <input type="number" id="inputIdPesquisa" name="nomePesquisa"
                                                 class="form-control"></label><br>
        <br><input value="Pesquisar" type="submit" id="botaoPesquisa" class="btn btn-success" disabled/>
    </form>
</span>

<c:if test="${mostraTable == 'sim'}">
    <br>
    <center>
    <table style="text-align: center">
        <tr>
            <th>Id</th>
            <th>Nome</th>
            <th>Nome Social</th>
            <th>RG</th>
            <th>CPF</th>
            <th>Dt. Nasc.</th>
            <th>Email</th>
        </tr>
        <c:forEach var="cliente" items="${listaClientesLike}">
            <tr>
                <td>${cliente.id}</td>
                <td>${cliente.nome}</td>
                <td>${cliente.nomeSocial}</td>
                <td>${cliente.rg}</td>
                <td>${cliente.cpf}</td>
                <td><fmt:formatDate value="${cliente.dtNascimento}" pattern="dd/MM/yyyy"/></td>
                <td>${cliente.email}</td>
            </tr>
        </c:forEach>
    </table>
        <form method="post" action="controller">

        </form>
    </center>
</c:if>

<script>
    function validaTipoPesquisa() {
        var check1 = document.getElementsByName("checkTudo")[0].checked;
        var check2 = document.getElementsByName("checkTudo")[1].checked;
        var check3 = document.getElementsByName("checkTudo")[2].checked;


        if (check1 == true) {
            document.getElementById("nomePesquisa").hidden = false;
            document.getElementById("idPesquisa").hidden = true;
            document.getElementById("botaoPesquisa").disabled = false;
            document.getElementById("inputNomePesquisa").required = true;
            document.getElementById("inputIdPesquisa").value = "";
        } else {
            document.getElementById("nomePesquisa").hidden = true;
            document.getElementById("inputNomePesquisa").required = false;
        }

        if (check2 == true) {
            document.getElementById("nomePesquisa").hidden = true;
            document.getElementById("idPesquisa").hidden = false;
            document.getElementById("botaoPesquisa").disabled = false;
            document.getElementById("inputIdPesquisa").required = true;
            document.getElementById("inputNomePesquisa").value = "";
        } else {
            document.getElementById("idPesquisa").hidden = true;
            document.getElementById("inputIdPesquisa").required = false;
        }

        if (check3 == true) {
            document.getElementById("nomePesquisa").hidden = true;
            document.getElementById("idPesquisa").hidden = true;
            document.getElementById("botaoPesquisa").disabled = false;
            document.getElementById("inputNomePesquisa").required = true;
            document.getElementById("inputIdPesquisa").required = true;
            document.getElementById("inputNomePesquisa").value = "";
            document.getElementById("inputIdPesquisa").value = "";
        }
    }

</script>

</body>
</html>
