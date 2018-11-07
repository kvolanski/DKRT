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
    <script src="../validaCep.js"></script>

    <title>D.K.R.T</title>
</head>

<body>

<%@include file="/WEB-INF/navbar/navbar.jsp" %>

<h1 class="geral">CLIENTES</h1>
<span class="geral">
	 <form method="post" action="controller?acao=cadastro&tipo=cliente">
				<label>Codigo: <input type="button" name="codigo" class="form-control" value="1"
                                      disabled="disabled"/></label><br>
				<label>Nome: <input type="text" name="nomeCliente" class="form-control" required/></label>
				<label>CPF: <input type="text" name="cpfCliente" class="form-control" required/></label>
				<label>Dt. Nascimento: <input type="text" name="dtNascCliente" class="form-control" required/></label>
				<label>Telefone: <input type="text" name="telefoneCliente" class="form-control" required/></label> <br>
				<label>CEP: <input type="text" name="cepEnderecoCliente"
                                   class="form-control" <%--onblur="pesquisacep(this.value);"--%> required/></label>
				<label>Rua: <input type="text" name="ruaEnderecoCliente" id="rua" class="form-control" required/></label>
				<label>Numero: <input type="text" name="numeroEnderecoCliente" class="form-control" required/></label><br>
				<label>Complemento: <input type="text" name="complementoEnderecoCliente"
                                           class="form-control"/></label><br>
				<label>Bairro: <input type="text" name="bairroEnderecoCliente" id="bairro"
                                      class="form-control" required/></label>
				<label>Cidade: <input type="text" name="cidadeEnderecoCliente" id="cidade"
                                      class="form-control" required/></label>
				<%--<label>Estado: <input type="text" name="estadoEnderecoCliente" id="uf" class="form-control"/></label>--%>
         <br><br>Uf:
         <select name="clienteUfId" class="form-control">
            <option value="0">Selecione uma opção</option>
            <c:forEach var="uf" items="${listaUfs}">
                <c:if test="${uf.sigla != 'N/A'}">
                    <option value="${uf.id}">${uf.sigla}</option>
                </c:if>
            </c:forEach>
         </select>
				<input name="ibge" type="hidden" id="ibge" size="8"/></label><br/>
			<br><br>
			<input value="Limpar Dados" type="reset" class="btn btn-danger"/>
			<input value="Cadastrar" type="submit" class="btn btn-success"/>
	</form>
<input type="hidden" id="condicao" value="${condicao}">

    <script>
        var condicao = document.getElementById("condicao").value;

        if (condicao == "sucesso"){
            alert("Cadastro realizado com sucesso");
        }
    </script>
	
	
</span>
</body>
</html>
