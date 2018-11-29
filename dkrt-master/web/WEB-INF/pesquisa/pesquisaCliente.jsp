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
        <label id="idPesquisa" hidden>Id: <!--<input type="number" id="inputIdPesquisa" name="idPesquisa"
                                                 class="form-control">-->
            <select name="idPesquisa" id="selectIdPesquisa">
                <c:forEach var="cliente" items="${listaClientes}">
                    <option value="${cliente.id}">${cliente.id} - ${cliente.nome}</option>
                </c:forEach>
            </select>
        </label><br>
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
                <th>Celular</th>
                <th>Telefone</th>
                <th>Ativo</th>
                <th>Observação do Cliente</th>
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
                    <td>${cliente.celular}</td>
                    <td>${cliente.telefone}</td>
                    <td>${cliente.ativo}</td>
                    <td>${cliente.observacao}</td>
                </tr>
            </c:forEach>
        </table>
        <form method="post" action="controller">

        </form>
    </center>
</c:if>
<br>
<c:if test="${mostrarDetalhe == 'sim'}">
    <center>
        <fieldset>
            <legend>Informações Pessoais</legend>
            <label>Nome: ${clienteBusca.nome}</label><br>
            <label>Nome Social: ${clienteBusca.nomeSocial}</label><br>
            <label>RG: ${clienteBusca.rg}</label><br>
            <label>CPF: ${clienteBusca.cpf}</label><br>
            <label>Dt. Nasc.: <fmt:formatDate value="${clienteBusca.dtNascimento}" pattern="dd/MM/yyyy"/></label><br>
            <label>E-mail: ${clienteBusca.email}</label><br>
            <label>Observação: ${clienteBusca.observacao}</label><br>
        </fieldset>
        <fieldset>
            <legend>Endereço</legend>
            <label>Nome da rua: ${clienteBusca.enderecoDTO.rua}</label><br>
            <label>CEP: ${clienteBusca.enderecoDTO.cep}</label><br>
            <label>Número da residência: ${clienteBusca.enderecoDTO.numero}</label><br>
            <label>Complemento: ${clienteBusca.enderecoDTO.complemento}</label><br>
            <label>Bairro: ${clienteBusca.enderecoDTO.bairro}</label><br>
            <label>Cidade: ${clienteBusca.enderecoDTO.cidade}</label><br>
            <label>Estado: ${clienteBusca.enderecoDTO.ufDTO.sigla}</label><br>
        </fieldset>
        <fieldset>
            <legend>Informações Adicionais</legend>
            <label>Número de compras realizadas: ${clienteBusca.numeroCompras}</label><br>
        </fieldset>
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
        } else {
            document.getElementById("nomePesquisa").hidden = true;
            document.getElementById("inputNomePesquisa").required = false;
        }

        if (check2 == true) {
            document.getElementById("nomePesquisa").hidden = true;
            document.getElementById("idPesquisa").hidden = false;
            document.getElementById("botaoPesquisa").disabled = false;
            document.getElementById("inputNomePesquisa").value = "";
        } else {
            document.getElementById("idPesquisa").hidden = true;
        }

        if (check3 == true) {
            document.getElementById("nomePesquisa").hidden = true;
            document.getElementById("idPesquisa").hidden = true;
            document.getElementById("botaoPesquisa").disabled = false;
            document.getElementById("inputNomePesquisa").required = false;
            document.getElementById("inputNomePesquisa").value = "";
            document.getElementById("selectIdPesquisa").value = "";
        }
    }

</script>

</body>
</html>
