<!DOCTYPE html>
<html lang="">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="../estilo/estilo.css" type="text/css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.5.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){

            var input = '<label style="display: block">'+
                '<label>Buscar Produto: <input type="text" name="buscarproduto" class="form-control" placeholder="ID ou NOME"></label>'+
                '<label>Qunatidade atual:<input value="5" type="number" name="quantidadeatual" class="form-control" placeholder="ID ou NOME" disabled="disabled"></label>' +
                '<label>Quantidade de venda: <input type="number" name="quantidadeproduto" class="form-control"></label>' +
                '<label>Valor: <input type="number" name="total" class="form-control"></label>' +
                '<label>Desconto: <input type="number" name="desconto" class="form-control"></label>' +
                '<label>Total: <input type="number" name="total" class="form-control" disabled="disabled"></label>     ' +
                '<a href="#" class="remove"><i class="far fa-times-circle icons"></i></a></label>';

            $("input[name='add']").click(function( e ){
                $('#inputs_adicionais').append( input );
            });

            $('#inputs_adicionais').delegate('a','click',function( e ){
                e.preventDefault();
                $( this ).parent('label').remove();
            });

        });
    </script>



    <title>D.K.R.T</title>
</head>

<body>
<div class="drop">
    <ul class="drop_menu">
        <li><a href='#'>CADASTRO</a>
            <ul>
                <li><a href='../cadastro/produto.html'>Pruduto</a></li>
                <li><a href='../cadastro/cliente.html'>Cliente</a></li>
            </ul>
        </li>
        <li><a href='#'>VENDAS</a>
            <ul>
                <li><a href='../venda/vender.html'>Efetuar Venda</a></li>
                <li><a href='../venda/cancelar.html'>Cancelar Venda</a></li>
            </ul>
        </li>
        <li><a href='#'>ORÇAMENTOS</a>
            <ul>
                <li><a href='../orcamento/orcamento.html'>Gerar orçamento</a></li>
                <li><a href='../orcamento/orcsalvo.html'>Orçamentos Salvos</a></li>
            </ul>
        </li>
        <li><a href='#'>RELATÓRIOS</a>
            <ul>
                <li><a href='../relatorio/venda.html'>Venda</a></li>
                <li><a href='../relatorio/estoque.html'>Estoque</a></li>
            </ul>
        </li>
        <li><a href='#'>PESQUISAR</a>
            <ul>
                <li><a href='cliente.html'>Cliente</a></li>
                <li><a href='produto.html'>Produto</a></li>
            </ul>
        </li>

        <li><a href='../index.html' onclick="alert('VOCÊ ESTA SAINDO DO SISTEMA, ATE MAIS')">SAIR</a></li>
    </ul>
</div>

<br><br><br><h1 class="geral">PESQUISAR CLIENTE</h1><br>

<span class="geral">


			<form method="post" action="#">

              <label><input type="text" name="buscarproduto" class="form-control" placeholder="ID ou NOME"></label>
               <input value="Pesquisar" type="button" class="btn btn-success"/>



            </form>




</body>
</html>
