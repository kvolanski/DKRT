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
				<label>Nome *: <input type="text" name="nomeCliente" class="form-control" required/></label>
				<label>RG *: <input type="text" name="rgCliente" class="form-control" required/></label>
				<label>CPF *: <input type="text" onclick="limpaCampo()" onblur="mostraErro(this.value)" id="cpfCliente" maxlength="11" name="cpfCliente" onkeyup="validarCpf(this.value)" class="form-control" required/></label>
				<label>Dt. Nascimento *: <input type="text" name="dtNascCliente" class="form-control" required/></label>
				<label>E-mail *: <input type="text" onclick="limpaCampo()" onblur="mostraErro(this.value)" id="emailCliente" onkeyup="validarEmail(this.value)" name="emailCliente" class="form-control" required/></label>
				<label>Celular *: <input type="text" name="celularCliente" class="form-control" required/></label> <br>
				<label>Telefone: <input type="text" name="telefoneCliente" class="form-control"/></label> <br>
				<label>CEP *: <input type="text" name="cepEnderecoCliente"
                                     class="form-control" <%--onblur="pesquisacep(this.value);"--%> required/></label>
				<label>Rua *: <input type="text" name="ruaEnderecoCliente" id="rua" class="form-control"
                                     required/></label>
				<label>Numero *: <input type="text" name="numeroEnderecoCliente" class="form-control"
                                        required/></label><br>
				<label>Complemento: <input type="text" name="complementoEnderecoCliente"
                                           class="form-control"/></label><br>
				<label>Bairro *: <input type="text" name="bairroEnderecoCliente" id="bairro"
                                        class="form-control" required/></label>
				<label>Cidade*: <input type="text" name="cidadeEnderecoCliente" id="cidade"
                                       class="form-control" required/></label>
                <label>Observação: <input type="text" name="observacaoCliente" class="form-control"/></label>
				<%--<label>Estado: <input type="text" name="estadoEnderecoCliente" id="uf" class="form-control"/></label>--%>
         <br><br><label>Uf *:
         <select name="clienteUfId" class="form-control" style="width: 300px;">
            <option value="0">Selecione uma opção</option>
            <c:forEach var="uf" items="${listaUfs}">
                <c:if test="${uf.sigla != 'N/A'}">
                    <option value="${uf.id}">${uf.sigla}</option>
                </c:if>
            </c:forEach>
         </select>
             </label>

				<input name="ibge" type="hidden" id="ibge" size="8"/></label><br/>
			<br><br>
			<input value="Limpar Dados" type="reset" class="btn btn-danger"/>
			<input value="Cadastrar" type="submit" class="btn btn-success"/>
	</form>
<input type="hidden" id="condicao" value="${condicao}">

    <script>
        var condicao = document.getElementById("condicao").value;

        if (condicao == "sucesso") {
            alert("Cadastro realizado com sucesso");
        }

        function validarEmail(email) {

            var emailCliente = document.getElementById("emailCliente");
            document.getElementById('emailCliente').style.color="#000000";

            if(email != null && email.length > 0){
                var usuario = email.substring(0, email.indexOf("@"));
                var dominio = email.substring(email.indexOf("@")+ 1, email.length);
                if ((usuario.length >=1) &&
                    (dominio.length >=3) &&
                    (usuario.search("@")==-1) &&
                    (dominio.search("@")==-1) &&
                    (usuario.search(" ")==-1) &&
                    (dominio.search(" ")==-1) &&
                    (dominio.search(".")!=-1) &&
                    (dominio.indexOf(".") >=1)&&
                    (dominio.lastIndexOf(".") < dominio.length - 1)) {
                    emailCliente.style.borderColor = "green";
                }
                else{
                    emailCliente.style.borderColor = "red";
                }
            }

        }

        function validarCpf(cpf) {


            var cpfCliente = document.getElementById("cpfCliente");

            if(cpf != null && cpf.length > 0){
                var Soma;
                var Resto;
                Soma = 0;
                if (cpf == "00000000000"){
                    cpfCliente.style.borderColor = "red";
                    return false;
                }


                for (i=1; i<=9; i++) Soma = Soma + parseInt(cpf.substring(i-1, i)) * (11 - i);
                Resto = (Soma * 10) % 11;

                if ((Resto == 10) || (Resto == 11))  Resto = 0;
                if (Resto != parseInt(cpf.substring(9, 10)) ) {
                    cpfCliente.style.borderColor = "red";
                    return false;
                }

                Soma = 0;
                for (i = 1; i <= 10; i++) Soma = Soma + parseInt(cpf.substring(i-1, i)) * (12 - i);
                Resto = (Soma * 10) % 11;

                if ((Resto == 10) || (Resto == 11))  Resto = 0;
                if (Resto != parseInt(cpf.substring(10, 11) ) ) {
                    cpfCliente.style.borderColor = "red";
                    return false;
                }
                cpfCliente.style.borderColor = "green";
                return true;
            }

        }

        function mostraErro(valor){

            var emailCliente = document.getElementById("emailCliente");
            var cpfCliente = document.getElementById("cpfCliente");

            if (valor != null && valor.length >0) {


                if (emailCliente.style.borderColor == "red") {
                    document.getElementById('emailCliente').value = "E-mail inválido!";
                    document.getElementById('emailCliente').style.color="#ff0000";
                }


                if (cpfCliente.style.borderColor == "red") {
                    document.getElementById('cpfCliente').value = "CPF inválido!";
                    document.getElementById('cpfCliente').style.color="#ff0000";
                }


            }


        }

        function limpaCampo() {
            var emailCliente = document.getElementById("emailCliente");
            var cpfCliente = document.getElementById("cpfCliente");

            if (emailCliente.style.borderColor == "red") {
                document.getElementById('emailCliente').value = "";
                document.getElementById('emailCliente').style.color="#000000";
            }


            if (cpfCliente.style.borderColor == "red") {
                document.getElementById('cpfCliente').value = "";
                document.getElementById('cpfCliente').style.color="#000000";
            }



        }

    </script>
	
	
</span>
</body>
</html>

