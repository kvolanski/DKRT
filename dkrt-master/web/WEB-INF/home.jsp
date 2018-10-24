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
        <%@include file="estilo/estilo.css" %>
    </style>

    <title>D.K.R.T</title>
</head>

<body>
<div class="drop">
    <ul class="drop_menu">
        <li><a href='#'>CADASTRO</a>
            <ul>
                <li><a href='cadastro/produto.html'>Pruduto</a></li>
                <li><a href='cadastro/cliente.html'>Cliente</a></li>
            </ul>
        </li>
        <li><a href='#'>VENDAS</a>
            <ul>
                <li><a href='venda/vender.html'>Efetuar Venda</a></li>
                <li><a href='venda/cancelar.html'>Cancelar Venda</a></li>
            </ul>
        </li>
        <li><a href='#'>ORÇAMENTOS</a>
            <ul>
                <li><a href='orcamento/orcamento.html'>Gerar orçamento</a></li>
                <li><a href='orcamento/orcsalvo.html'>Orçamentos Salvos</a></li>
            </ul>
        </li>
        <li><a href='#'>RELATÓRIOS</a>
            <ul>
                <li><a href='relatorio/venda.html'>Venda</a></li>
                <li><a href='relatorio/estoque.html'>Estoque</a></li>
            </ul>
        </li>
        <li><a href='#'>PESQUISAR</a>
            <ul>
                <li><a href='pesquisa/cliente.html'>Cliente</a></li>
                <li><a href='pesquisa/produto.html'>Produto</a></li>
            </ul>
        </li>
        <li><a href='index.html' onclick="alert('VOCÊ ESTA SAINDO DO SISTEMA, ATE MAIS')">SAIR</a></li>
    </ul>
</div>

<br><br><br>

<img src="logo.png" class="logohome"/>

</body>
</html>
