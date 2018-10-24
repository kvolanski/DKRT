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
                <li><a href='venda.html'>Venda</a></li>
                <li><a href='estoque.html'>Estoque</a></li>
            </ul>
        </li>
        <li><a href='#'>PESQUISAR</a>
            <ul>
                <li><a href='../pesquisa/cliente.html'>Cliente</a></li>
                <li><a href='../pesquisa/produto.html'>Produto</a></li>
            </ul>
        </li>
        <li><a href='../index.html' onclick="alert('VOCÊ ESTA SAINDO DO SISTEMA, ATE MAIS')">SAIR</a></li>
    </ul>
</div>

<br><br><br>
<h1 class="geral">RELATÓRIO VENDAS</h1><br>

<span class="geral">


			<form method="post" action="#">


				<select id="tipo" onchange="optionCheck()">
				  <option value="cliente">SELECIONE O MODELO PARA GERAR O RELATÓRIO</option>
				  <option value="diario">DIÁRIO</option>
				  <option value="periodo">PERÍODO</option>
				  <option value="mensal">MENSAL</option>
				  <option value="anual">ANUAL</option>


				</select><br>




				<div id="diario" style="visibility:hidden;">
                    <br>
                          <span class="font">  Empresa testa </span>
                          <span class="tipo">  Relatório Diário </span>
                    <br>

                    <center>

                        <table class="tabela">

                            <tr>
                            <td>ID</td>
                            <td>PRODUTO</td>
                            <td>NÚMERO DO PEDIDO</td>
                            <td>CLIENTE</td>
                            <td>QUANTIDADE</td>
                            <td>TOTAL</td>

                            </tr>
                            <tr>
                            <td>1</td>
                            <td>avell fox 7</td>
                            <td>PD0001</td>
                            <td>Teste Projeto</td>
                            <td>1</td>
                            <td>6.700</td>
                            </tr>

                         </table>
                    </center>
                    <br><br>
                        <input value="Imprimir" type="button" class="btn btn-success"
                               onclick="alert('IMPRESSÃO REALZIADA COM SUCESSO   ')"/>


                </div>

                <div id="periodo" style="visibility:hidden; margin-top: -237px">


                     <br><br><label>Data Inicio</label>
                        <input type="date" value="2018-09-15">
                     <label>Data Final</label>
                        <input type="date" value="2018-09-15">

                    <br>

                          <span class="font">  Empresa testa </span>
                          <span class="tipo">  Relatório Período </span>

                    <br>

                    <center>

                        <table class="tabela">

                            <tr>
                            <td>ID</td>
                            <td>PRODUTO</td>
                            <td>NÚMERO DO PEDIDO</td>
                            <td>CLIENTE</td>
                            <td>QUANTIDADE</td>
                            <td>TOTAL</td>

                            </tr>
                            <tr>
                            <td>1</td>
                            <td>avell fox 7</td>
                            <td>PD0001</td>
                            <td>Teste Projeto</td>
                            <td>1</td>
                            <td>6.700</td>
                            </tr>

                         </table>
                    </center>
                    <br><br>
                       <input value="Imprimir" type="button" class="btn btn-success"
                              onclick="alert('IMPRESSÃO REALZIADA COM SUCESSO   ')"/>

                </div>


                <div id="mensal" style="visibility:hidden; margin-top: -237px">
                    <br>
                        <span class="font">  Empresa testa </span>
                        <span class="tipo" style="left: 990px!important ">  Relatório Mensal: </span>
                        <span class="tipo">  Setembro/09</span>
                    <br>

                    <center>

                        <table class="tabela">

                            <tr>
                            <td>ID</td>
                            <td>PRODUTO</td>
                            <td>NÚMERO DO PEDIDO</td>
                            <td>CLIENTE</td>
                            <td>QUANTIDADE</td>
                            <td>TOTAL</td>

                            </tr>
                            <tr>
                            <td>1</td>
                            <td>avell fox 7</td>
                            <td>PD0001</td>
                            <td>Teste Projeto</td>
                            <td>1</td>
                            <td>6.700</td>
                            </tr>

                         </table>
                    </center>
                    <br><br>
                          <input value="Imprimir" type="button" class="btn btn-success"
                                 onclick="alert('IMPRESSÃO REALZIADA COM SUCESSO   ')"/>
                </div>

                <div id="anual" style="visibility:hidden; margin-top: -237px">
                    <br>
                          <span class="font">  Empresa testa </span>
                          <span class="tipo">  Relatório Anual <Anual></Anual> </span>
                    <br>

                    <center>

                        <table class="tabela">

                            <tr>
                            <td>ID</td>
                            <td>PRODUTO</td>
                            <td>NÚMERO DO PEDIDO</td>
                            <td>CLIENTE</td>
                            <td>QUANTIDADE</td>
                            <td>TOTAL</td>

                            </tr>
                            <tr>
                            <td>1</td>
                            <td>avell fox 7</td>
                            <td>PD0001</td>
                            <td>Teste Projeto</td>
                            <td>1</td>
                            <td>6.700</td>
                            </tr>

                         </table>
                    </center>
                    <br><br>

					    <input value="Imprimir" type="button" class="btn btn-success"
                               onclick="alert('IMPRESSÃO REALZIADA COM SUCESSO   ')"/>


                </div>



<script type="text/javascript">
    function optionCheck() {
        var option = document.getElementById("tipo").value;

        if (option == "diario") {
            document.getElementById("diario").style.visibility = "visible";
            document.getElementById("periodo").style.visibility = "hidden";
            document.getElementById("mensal").style.visibility = "hidden";
            document.getElementById("anual").style.visibility = "hidden";


        }
        if (option == "periodo") {

            document.getElementById("periodo").style.visibility = "visible";
            document.getElementById("diario").style.visibility = "hidden";
            document.getElementById("mensal").style.visibility = "hidden";
            document.getElementById("anual").style.visibility = "hidden";
        }
        if (option == "mensal") {

            document.getElementById("mensal").style.visibility = "visible";
            document.getElementById("diario").style.visibility = "hidden";
            document.getElementById("periodo").style.visibility = "hidden";
            document.getElementById("anual").style.visibility = "hidden";
        }
        if (option == "anual") {

            document.getElementById("anual").style.visibility = "visible";
            document.getElementById("diario").style.visibility = "hidden";
            document.getElementById("mensal").style.visibility = "hidden";
            document.getElementById("periodo").style.visibility = "hidden";
        }
    }
</script>


</body>
</html>
