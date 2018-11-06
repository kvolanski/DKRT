<!DOCTYPE html>
<html lang="">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>

    <style>
        <%@include file="../estilo/estilo.css" %>
    </style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.5.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {

            var input = '<label style="display: block">' +
                '<label>Buscar Produto: <input type="text" name="buscarproduto" class="form-control" placeholder="ID ou NOME"></label>' +
                '<label>Qunatidade atual:<input value="5" type="number" name="quantidadeatual" class="form-control" placeholder="ID ou NOME" disabled="disabled"></label>' +
                '<label>Quantidade de venda: <input type="number" name="quantidadeproduto" class="form-control"></label>' +
                '<label>Valor: <input type="number" name="total" class="form-control"></label>' +
                '<label>Desconto: <input type="number" name="desconto" class="form-control"></label>' +
                '<label>Total: <input type="number" name="total" class="form-control" disabled="disabled"></label>     ' +
                '<a href="#" class="remove"><i class="far fa-times-circle icons"></i></a></label>';

            $("input[name='add']").click(function (e) {
                $('#inputs_adicionais').append(input);
            });

            $('#inputs_adicionais').delegate('a', 'click', function (e) {
                e.preventDefault();
                $(this).parent('label').remove();
            });

        });
    </script>


    <title>D.K.R.T</title>
</head>

<body>

<%@include file="/WEB-INF/navbar/navbar.jsp"%>

<h1 class="geral">GERAR ORÇAMENTO</h1><br>

<span class="geral">


			<form method="post" action="#">


				<select id="cliente" onchange="optionCheck()">
				  <option value="cliente">SELECIONE O CLIENTE</option>
				  <option value="name">Teste</option>


				</select><br>
				<div id="codigo" style="visibility:hidden;">
						<label>Número do orçamento:<input type="text" value="OC0001" style="text-align: center"
                                                          name="codigo" class="form-control"
                                                          disabled="disabled"/></label><br>
						<label>Codigo:<input type="button" name="codigo" class="form-control" value="1"
                                             disabled="disabled"/></label>
						<label>Nome:<input type="text" name="nome" class="form-control" value="Teste Projeto"
                                           disabled="disabled"/></label>
						<label>Telefone:<input type="text" name="numero" class="form-control" value="(041) 9 9999-9999"
                                               disabled="disabled"/></label><br><br>
						<label>Validade orçamento:<input type="datetime-local" value="2018-06-15T22:30" name="numero"
                                                         class="form-control"/></label><br><br>
						<label>Buscar Produto:<input value="Avell FOX 7" type="text" name="buscarproduto"
                                                     class="form-control" placeholder="ID ou NOME"></label>
						<label>Qunatidade atual:<input value="5" type="number" name="quantidadeatual"
                                                       class="form-control" placeholder="ID ou NOME"
                                                       disabled="disabled"></label>
						<label>Quantidade de venda:<input value="1" type="number" name="quantidadeproduto"
                                                          class="form-control"></label>
						<label>Valor:<input value="6.700" type="number" name="total" class="form-control"
                                            disabled="disabled"></label>
						<label>Desconto:<input value="0" type="number" name="desconto" class="form-control"></label>
						<label>Total:<input value="6.700" type="number" name="total" class="form-control"
                                            disabled="disabled"></label>
						<label><i class="fas fa-cart-plus icons" style="text-align: left"></i><input
                                class="transparente" type="button" name="add"/></label>
  						<fieldset id="inputs_adicionais" style="border: none"></fieldset><br>
						<label>Total do pedido:<input value="6.700" type="number" name="total" class="form-control"
                                                      disabled="disabled"></label><br><br>

					<input value="Cancelar" type="button" class="btn btn-danger"/>
					<input value="Salvar" type="button" class="btn btn-success"
                           onclick="alert('ORÇAMENTO SALVO COM SUCESSO')"/>
					<input value="Imprimir" type="button" class="btn btn-primary"
                           onclick="alert('IMPRESSÃO REALIZADA COM SUCESSO')"/>

				</div>


<script type="text/javascript">
    function optionCheck() {
        var option = document.getElementById("cliente").value;
        if (option == "name") {
            document.getElementById("codigo").style.visibility = "visible";
        }
        if (option == "goto") {
            window.location = "http://google.com";
        }
    }
</script>
</body>
</html>
