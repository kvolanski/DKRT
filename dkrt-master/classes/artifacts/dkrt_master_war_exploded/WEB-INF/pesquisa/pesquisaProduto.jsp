<!DOCTYPE html>
<html lang="">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <title>Pesquisa de Produto - D.K.R.T</title>
    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/navbar/navbar.jsp" %>

<h1 class="geral">PESQUISAR PRODUTO</h1><br>

<center>
    <form method="get" action="controller">
        <input type="radio" value="checkNome" id="checkNome" name="checkTudo" onclick="validaTipoPesquisa()"> Pesquisar
        por
        nome
        <input type="radio" value="checkId" id="checkId" name="checkTudo" onclick="validaTipoPesquisa()"> Pesquisar por
        id
        <input type="hidden" value="pesquisa" name="acao">
        <input type="hidden" value="produtoPesquisa" name="tipo">
        <br><br><label id="nomePesquisa" hidden>Nome: <input type="text" id="inputNomePesquisa" name="nomePesquisa"
                                                             class="form-control"></label>
        <label id="idPesquisa" hidden>Id: <!--<input type="number" id="inputIdPesquisa" name="idPesquisa"
                                                 class="form-control">-->
            <select name="idPesquisa" id="selectIdPesquisa">
                <c:forEach var="produto" items="${listaProdutos}">
                    <option value="${produto.id}">${produto.id} - ${produto.nome}</option>
                </c:forEach>
            </select>
        </label><br>
        <br><input value="Pesquisar" type="submit" id="botaoPesquisa" class="btn btn-success" disabled/>
    </form>
</center>

<c:if test="${mostraTable == 'sim'}">
    <br>
    <center>
        <table style="text-align: center">
            <tr>
                <th>Id</th>
                <th>Nome do Produto</th>
                <th>Descrição</th>
                <th>Preço de Venda</th>
                <th>Preço de Custo</th>
                <th>Qtd. em Estoque</th>
            </tr>
            <c:forEach var="produto" items="${listaProdutosLike}">
                <tr>
                    <td>${produto.id}</td>
                    <td>${produto.nome}</td>
                    <td>${produto.descricao}</td>
                    <td>${produto.precoVenda}</td>
                    <td>${produto.precoCusto}</td>
                    <td>${produto.qtdEstoque}</td>
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
            <legend>Informações do Produto</legend>
            <label>Nome do Produto: ${produtoBusca.nome}</label><br>
            <label>Descrição: ${produtoBusca.descricao}</label><br>
            <label>Preço de Venda: ${produtoBusca.precoVenda}</label><br>
            <label>Preço de Custo: ${produtoBusca.precoCusto}</label><br>
            <label>Quantidade em Estoque: ${produtoBusca.qtdEstoque}</label><br>
            <label>Data de Cadastro: <fmt:formatDate value="${produtoBusca.dataCadastro}" pattern="dd/MM/yyyy"/></label><br>
            <label>Data da Última Alteração: <fmt:formatDate value="${produtoBusca.dataAlteracao}"
                                                             pattern="dd/MM/yyyy"/></label><br>
        </fieldset>
    </center>
</c:if>

<script>
    function validaTipoPesquisa() {
        var check1 = document.getElementsByName("checkTudo")[0].checked;
        var check2 = document.getElementsByName("checkTudo")[1].checked;


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
    }

</script>

</body>
</html>
