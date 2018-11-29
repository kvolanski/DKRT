<!DOCTYPE html>
<html lang="">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>D.K.R.T</title>
</head>

<body>

<%@include file="/WEB-INF/navbar/navbar.jsp" %>

<c:choose>
    <c:when test="${tipoDeAcao == 'orcamento'}">
        <h1 class="geral">ORÇAMENTO</h1><br>
    </c:when>
    <c:otherwise>
        <h1 class="geral">VENDAS</h1><br>
    </c:otherwise>
</c:choose>
<center>
    <c:if test="${mostraCliente == 'sim'}">
        <form method="get" action="controller">
            <c:choose>
                <c:when test="${tipoDeAcao == 'orcamento'}">
                    <input type="hidden" value="orcamento" name="acao">
                    <input type="hidden" value="comecarOrcamento" name="tipo">
                </c:when>
                <c:otherwise>
                    <input type="hidden" value="venda" name="acao">
                    <input type="hidden" value="abrirVenda" name="tipo">
                </c:otherwise>
            </c:choose>
            <label>Escolha um cliente para realizar a venda:</label>
            <select style="width: 300px;" class="form-control" name="idCliente" id="selecionaCliente" onmouseup="validaSelecaoCliente()"
                    onkeyup="validaSelecaoCliente()">
                <option value="0">Selecione um cliente</option>
                <c:forEach var="cliente" items="${listaClientes}">
                    <option value="${cliente.id}">${cliente.nome}</option>
                </c:forEach>
            </select><br><br>
            <input type="submit" value="Abrir Venda" id="inputAbrirVenda" disabled class="btn btn-success">
        </form>
    </c:if>
</center>

<br><br>
<center>
    <c:if test="${clienteBusca.id != 0}">
        <label>Número da Venda:<input type="text" value="" style="text-align: center" name="idVenda"
                                      class="form-control" disabled="disabled"/></label><br>
        <label>Nome:<input type="text" name="nomeCliente" class="form-control" value="${clienteBusca.nome}"
                           disabled="disabled"/></label>
        <label>Telefone Principal:<input type="text" name="celularCliente" class="form-control"
                                         value="${clienteBusca.celular}" disabled="disabled"/></label><br><br>

        <form method="get" action="controller">
            <c:choose>
                <c:when test="${tipoDeAcao == 'orcamento'}">
                    <input type="hidden" value="orcamento" name="acao">
                    <input type="hidden" value="buscaProduto" name="tipo">
                </c:when>
                <c:otherwise>
                    <input type="hidden" value="venda" name="acao">
                    <input type="hidden" value="buscaProduto" name="tipo">
                </c:otherwise>
            </c:choose>
            <input type="hidden" value="${clienteBusca.id}" id="idCliente">
            <label>Produto:
                <select id="selecionaProduto" name="idProduto" onmouseup="validaSelecaoProduto()"
                        onkeyup="validaSelecaoProduto()" class="form-control">
                    <option value="0">Selecione um produto.</option>
                    <c:forEach var="produto" items="${listaProdutos}">
                        <option value="${produto.id}">${produto.nome}</option>
                    </c:forEach>
                </select>
            </label>
            <br><br>
            <input type="submit" value="Carregar Produto" id="inputCarregarProduto" disabled class="btn btn-success"><br><br>
        </form>

        <form method="post" action="controller">
            <c:choose>
                <c:when test="${tipoDeAcao == 'orcamento'}">
                    <input type="hidden" value="orcamento" name="acao">
                    <input type="hidden" value="adicionarPedidoOrcamento" name="tipo">
                    <input type="hidden" value="orcamento" name="tipoAlteracao">
                </c:when>
                <c:otherwise>
                    <input type="hidden" value="venda" name="acao">
                    <input type="hidden" value="adicionarPedido" name="tipo">
                    <input type="hidden" value="venda" name="tipoAlteracao">
                </c:otherwise>
            </c:choose>
            <input type="hidden" value="${produtoBusca.id}" name="idProduto">
            <label>Descricao:<input value="${produtoBusca.descricao}" type="text" name="descricaoProduto"
                                    class="form-control" disabled="disabled" id="descricaoAdiciona"></label>
            <label>Quantidade:<input type="number" name="quantidadeProduto" id="campoQuantidade" value="0"
                                     class="form-control"></label>
            <label>Valor Un.:<input value="${produtoBusca.precoVenda}" type="number" name="valorUnitProduto"
                                    class="form-control" step="any"></label>
            <label>Qtd. em Estoque:<input value="${produtoBusca.qtdEstoque}" type="number" disabled="disabled" class="form-control"></label><br><br>
            <input value="Limpar Campos" type="reset" class="btn btn-danger"/>
            <input value="Adicionar" type="submit" class="btn btn-success" id="botaoAdiciona"/><br><br>
        </form>

        <table width="40%" align="center" cellpadding="10">
            <tr align="center" bgcolor="#CCC">
                <td><strong>Produto</strong></td>
                <td><strong>Descrição</strong></td>
                <td><strong>Quantidade</strong></td>
                <td><strong>Valor Unit.</strong></td>
                <td><strong>Valor Total</strong></td>
                <td><strong>Ação</strong></td>

            </tr>

            <c:forEach var="pedido" items="${listaPedido}">
                <tr style="text-align: center; background-color: gainsboro;" class="border_bottom">
                    <td>${pedido.produtoDTO.nome}</td>
                    <td>${pedido.produtoDTO.descricao}</td>
                    <td>${pedido.quantidade}</td>
                    <td>${pedido.valorUnitario}</td>
                    <td>${pedido.valorTotal}</td>
                    <c:choose>
                        <c:when test="${tipoDeAcao == 'orcamento'}">
                            <td><a href="controller?acao=orcamento&tipo=tirarProdutoLista&id=${pedido.id}">Excluir</a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="controller?acao=venda&tipo=tirarProdutoLista&id=${pedido.id}">Excluir</a></td>
                        </c:otherwise>
                    </c:choose>

                </tr>
            </c:forEach>

        </table>
        <br><br>
        <form method="get" action="controller">
            <c:choose>
                <c:when test="${tipoDeAcao == 'orcamento'}">
                    <input type="hidden" value="orcamento" name="acao">
                    <input type="hidden" value="finalizarOrcamento" name="tipo">
                    <input type="submit" value="Finalizar Orcamento" id="botaoFinalizar">
                </c:when>
                <c:otherwise>
                    <input type="hidden" value="venda" name="acao">
                    <input type="hidden" value="finalizarVenda" name="tipo">
                    <label>Deixar venda em aberto</label>
                    <input type="checkbox" id="checkAberto" value="emAberto" name="statusAberto" class="form-control" style="width: 20px; height: 20px"><br>

                    <input type="submit" value="Finalizar Venda" id="botaoFinalizar" disabled class="btn btn-success">
                </c:otherwise>
            </c:choose>
        </form>


    </c:if>
</center>

<script>
    var descricao = document.getElementById("descricaoAdiciona").value;

    if (descricao != "") {
        alert(botaoQuantidade);
        document.getElementById("botaoAdiciona").disabled = false;
    } else {
        document.getElementById("botaoAdiciona").disabled = true;
    }

    function validaSelecaoCliente() {
        var selecionaCliente = document.getElementById("selecionaCliente").value;
        if (selecionaCliente == 0) {
            document.getElementById("inputAbrirVenda").disabled = true;
        } else {
            document.getElementById("inputAbrirVenda").disabled = false;
        }
    }

    function validaSelecaoProduto() {
        var selecionaCliente = document.getElementById("selecionaProduto").value;
        if (selecionaCliente == 0) {
            document.getElementById("inputCarregarProduto").disabled = true;
        } else {
            document.getElementById("inputCarregarProduto").disabled = false;
        }
    }

    document.getElementById("checkAberto").addEventListener('click', function () {
        var check = document.getElementById("checkAberto").checked;
        if (check == true) {
            document.getElementById("botaoFinalizar").disabled = false;
        } else {
            document.getElementById("botaoFinalizar").disabled = true;
        }
    })
</script>
</body>
</html>
