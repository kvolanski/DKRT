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

<%@include file="/WEB-INF/navbar/navbar.jsp"%>

<h1 class="geral">PRODUTOS</h1>
<span class="geral">
	<form method="post" action="#">   		
		   <label>Codigo: <input type="button" name="codigo" class="form-control" value="1"
                                 disabled="disabled"/></label><br>
		   <label>Produto: <input name="produto" class="form-control"/></label>
			<label>Descrição: <input name="descricao" class="form-control"/></label> <br>
			<label>Quantidade: <input type="number" name="quantidade" class="form-control"/></label>
			<label>Preço de Venda: <input type="number" name="venda" class="form-control"/></label> <br><br>
			<input value="Cancelar" type="button" class="btn btn-danger"/>
			<input value="Salvar" type="button" class="btn btn-success"
                   onclick="alert('PRODUTO CADASTRADO COM SUCESSO')"/>
					
	</form>
</span>
</body>
</html>
