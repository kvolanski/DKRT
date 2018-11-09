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

<%@include file="/WEB-INF/navbar/navbar.jsp" %>

<h1 class="geral">PESQUISAR CLIENTE</h1><br>

    <span class="geral">
			<form method="post" action="#">
              <label><input type="text" name="buscarproduto" class="form-control"  placeholder="ID ou NOME"></label>
               <input value="Pesquisar" type="button" class="btn btn-success"/>
            </form>
    </span>
</body>
</html>
