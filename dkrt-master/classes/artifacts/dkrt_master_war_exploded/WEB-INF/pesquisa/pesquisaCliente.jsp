<!DOCTYPE html>
<html lang="">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">


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

        <label><input type="radio" value="checkNome" id="checkNome" name="checkTudo" onclick="validaTipoPesquisa()"> Pesquisar por nome</label>
        <label><input type="radio" value="checkId" id="checkId" name="checkTudo" onclick="validaTipoPesquisa()"> Pesquisar por id</label>
        <label><input type="radio" value="checkNumCompras" id="checkNumCompras" name="checkTudo"
               onclick="validaTipoPesquisa()"> Listar por número de compras</label>

        <input type="hidden" value="pesquisa" name="acao">
        <input type="hidden" value="clientePesquisa" name="tipo">
        <br><br>
        <label id="nomePesquisa" hidden>Nome: <input type="text" id="inputNomePesquisa" name="nomePesquisa" class="form-control"></label>
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
        <table width="90%" align="center" cellpadding="10">
            <tr align="center" bgcolor="#EDEDED">
                <td><strong>Id</strong></td>
                <td><strong>Nome</strong></td>
                <td><strong>Nome Social</strong></td>
                <td><strong>RG</strong></td>
                <td><strong>CPF</strong></td>
                <td><strong>Dt. Nasc.</strong></td>
                <td><strong>Email</strong></td>
                <td><strong>Celular</strong></td>
                <td><strong>Telefone</strong></td>
                <td><strong>Ativo</strong></td>
                <td><strong>Observação do Cliente</strong></td>
                <td><strong>Numero de Compras</strong></td>
            </tr>
            <c:forEach var="cliente" items="${listaClientesLike}">
                <tr style="text-align: center; background-color: gainsboro;" class="border_bottom">
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
                    <td>${cliente.numeroCompras}</td>
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
            <label class="formInterno" style="width: 300px;">Nome: ${clienteBusca.nome}</label>
            <label class="formInterno" style="width: 300px;">Nome Social: ${clienteBusca.nomeSocial}</label>
            <label class="formInterno" style="width: 300px;">RG: ${clienteBusca.rg}</label>
            <label class="formInterno" style="width: 300px;">CPF: ${clienteBusca.cpf}</label>
            <label class="formInterno" style="width: 300px;">Dt. Nasc.: <fmt:formatDate value="${clienteBusca.dtNascimento}" pattern="dd/MM/yyyy"/></label>
            <label class="formInterno" style="width: 300px;">E-mail: ${clienteBusca.email}</label>
            <label class="formInterno" style="width: 300px;">Observação: ${clienteBusca.observacao}</label><br>

        </fieldset>
        <fieldset>
            <legend>Endereço</legend>
            <label class="formInterno" style="width: 300px;">Nome da rua: ${clienteBusca.enderecoDTO.rua}</label>
            <label class="formInterno" style="width: 300px;">CEP: ${clienteBusca.enderecoDTO.cep}</label>
            <label class="formInterno" style="width: 300px;">Número da residência: ${clienteBusca.enderecoDTO.numero}</label>
            <label class="formInterno" style="width: 300px;">Complemento: ${clienteBusca.enderecoDTO.complemento}</label>
            <label class="formInterno" style="width: 300px;">Bairro: ${clienteBusca.enderecoDTO.bairro}</label>
            <label class="formInterno" style="width: 300px;">Cidade: ${clienteBusca.enderecoDTO.cidade}</label>
            <label class="formInterno" style="width: 300px;">Estado: ${clienteBusca.enderecoDTO.ufDTO.sigla}</label>
        </fieldset>
        <fieldset>
            <legend>Informações Adicionais</legend>
            <label class="formInterno" style="width: 300px;">Número de compras realizadas: ${clienteBusca.numeroCompras}</label>
        </fieldset>
    <br>
       <a class="btn btn-success" href="controller?acao=impressao&tipo=fichaCliente&id=${clienteBusca.id}">GERAR PDF</a>
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
