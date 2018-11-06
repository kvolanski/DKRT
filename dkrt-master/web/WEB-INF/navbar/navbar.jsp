<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 06/11/2018
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="drop">
    <ul class="drop_menu">
        <li><a>CADASTRO</a>
            <ul>
                <li><a href="controller?acao=cadastro&tipo=produto">Pruduto</a></li>
                <li><a href="controller?acao=cadastro&tipo=cliente">Cliente</a></li>
            </ul>
        </li>
        <li><a>VENDAS</a>
            <ul>
                <li><a href="controller?acao=venda&tipo=efetuar">Efetuar Venda</a></li>
                <li><a href="controller?acao=venda&tipo=cancelar">Cancelar Venda</a></li>
            </ul>
        </li>
        <li><a>ORÇAMENTOS</a>
            <ul>
                <li><a href="controller?acao=orcamento&tipo=gerar">Gerar orçamento</a></li>
                <li><a href="controller?acao=orcamento&tipo=salvos">Orçamentos Salvos</a></li>
            </ul>
        </li>
        <li><a>RELATÓRIOS</a>
            <ul>
                <li><a href="controller?acao=relatorio&tipo=venda">Venda</a></li>
                <li><a href="controller?acao=relatorio&tipo=estoque">Estoque</a></li>
            </ul>
        </li>
        <li><a>PESQUISAR</a>
            <ul>
                <li><a href="controller?acao=pesquisa&tipo=cliente">Cliente</a></li>
                <li><a href="controller?acao=pesquisa&tipo=produto">Produto</a></li>
            </ul>
        </li>
        <%--<li><a href='index.html' onclick="alert('VOCÊ ESTA SAINDO DO SISTEMA, ATE MAIS')">SAIR</a></li>--%>
    </ul>
</div>
<br><br><br>
</body>
</html>
