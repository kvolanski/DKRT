<!DOCTYPE html>
<html lang="">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>
    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>

    <title>D.K.R.T</title>
</head>

<body>

<%@include file="/WEB-INF/navbar/navbar.jsp" %>

<h1 class="geral">PRODUTOS</h1>
<span class="geral">
    <c:if test="${alteraForm == 'nao'}">
	    <form method="post" action="controller?acao=cadastro&tipo=produto">
    </c:if>
    <c:if test="${alteraForm == 'sim'}">
	    <form method="post" action="controller?acao=cadastro&tipo=alteraProduto">
    </c:if>
            <input type="hidden" name="idProduto" value="${produtoBusca.id}">
		   <label>Codigo: <input type="button" name="codigo" class="form-control" value="${produtoBusca.id}"
                                 disabled="disabled"/></label><br>
		   <label>Produto*: <input type="text" name="nomeProduto" class="form-control" value="${produtoBusca.nome}"
                                   required/></label>
			<label>Descrição: <input type="text" name="descricaoProduto" class="form-control"
                                      value="${produtoBusca.descricao}"/></label> <br>
			<label>Quantidade em Estoque*: <input type="text" name="quantidadeProduto" class="form-control"
                                                  value="${produtoBusca.qtdEstoque}" required/></label>
			<label>Preço de Venda(xxxx.xx)*: <input type="text" name="precoVendaProduto" class="form-control"
                                                    value="${produtoBusca.precoVenda}" required/></label> <br><br>
			<label>Preço de Custo(xxxx.xx)*: <input type="text" name="precoCustoProduto" class="form-control"
                                                    value="${produtoBusca.precoCusto}" required/></label> <br><br>
			<input value="Limpar Campos" type="reset" class="btn btn-danger"/>
			<input value="Salvar" type="submit" class="btn btn-success"/>
	</form>
    <input type="hidden" value="${condicao}" id="condicao">

    <c:if test="${condicao == 'produtoExistente'}">
        <center>
        <br><br><br>
        <c:set var="nome" value="${nomeProduto}"/>
        <label>Este produto já está cadastrado. Deseja editar?</label>
            <a><button>Não</button></a>
            <a href="controller?acao=cadastro&tipo=alteraProduto&nomeProduto=${nomeProduto}"><button>Sim</button></a>
        </center>
    </c:if>

    <script>
            var condicao = document.getElementById("condicao").value;

            if (condicao == "precoInvalido") {
                alert("Preço de venda/custo inválido(s). Por favor, digite no seguinte formato (xxxx.xx).")
            }

            if (condicao == "qtdInvalida") {
                alert("Quantidade inválida. Por favor, digite um número.")
            }

            if (condicao == "sucesso") {
                alert("Cadastro realizado com sucesso");
            }
    </script>
</span>
</body>
</html>
