<!DOCTYPE html>
<html lang="">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	<form method="post" action="controller?acao=cadastro&tipo=produto">
		   <label>Codigo: <input type="button" name="codigo" class="form-control" value="1"
                                 disabled="disabled"/></label><br>
		   <label>Produto*: <input type="text" name="nomeProduto" class="form-control" required/></label>
			<label>Descrição*: <input type="text" name="descricaoProduto" class="form-control" required/></label> <br>
			<label>Quantidade em Estoque*: <input type="text" name="quantidadeProduto" class="form-control" required/></label>
			<label>Preço de Venda(xxxx.xx)*: <input type="text" name="precoProduto" class="form-control" required/></label> <br><br>
			<input value="Limpar Campos" type="reset" class="btn btn-danger"/>
			<input value="Salvar" type="submit" class="btn btn-success"/>
	</form>
    <input type="hidden" value="${condicao}" id="condicao">

    <script>
        var condicao = document.getElementById("condicao").value;

        if (condicao == "precoInvalido"){
            alert("Preço inválido. Por favor, digite no seguinte formato (xxxx.xx).")
        }

        if (condicao == "qtdInvalida"){
            alert("Quantidade inválida. Por favor, digite um número.")
        }
    </script>
</span>
</body>
</html>
