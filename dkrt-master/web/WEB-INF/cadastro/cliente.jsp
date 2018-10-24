<!DOCTYPE html>
<html lang="">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous"> 
<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	<style><%@include file="../estilo/estilo.css"%></style>
	<script src="../validaCep.js"></script>
		
	<title>D.K.R.T</title>
</head>

<body>
<div class="drop">
	<ul class="drop_menu">
		<li><a href='#'>CADASTRO</a>
			<ul>
				<li><a href='produto.html'>Pruduto</a></li>
				<li><a href='cliente.html'>Cliente</a></li>
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
				<li><a href='../pesquisa/cliente.html'>Cliente</a></li>
				<li><a href='../pesquisa/produto.html'>Produto</a></li>
			</ul>
		</li>

		<li><a href='../index.html' onclick="alert('VOCÊ ESTA SAINDO DO SISTEMA, ATE MAIS')">SAIR</a></li>

	</ul>
</div>

<br><br><br><h1 class="geral">CLIENTES</h1>
<span class="geral">
	 <form method="get" action=".">   		
				<label>Codigo: <input type="button" name="codigo" class="form-control" value="1" disabled="disabled"/></label><br>
				<label>Nome: <input type="text" name="nome" class="form-control"/></label>
				<label>CPF: <input type="text" name="nome" class="form-control" /></label>				
				<label>Telefone: <input type="number" name="numero" class="form-control"/></label> <br>
				<label>CEP: <input type="text" name="cep" class="form-control" onblur="pesquisacep(this.value);" /></label> 
				<label>Numero: <input type="number" name="numero" class="form-control"/></label><br>
				<label>Rua: <input type="text" name="rua"  id="rua" class="form-control"/></label>
				<label>Bairro: <input type="text" name="bairro" id="bairro" class="form-control"/></label>
				<label>Cidade: <input type="text" name="cidade" id="cidade" class="form-control"/></label>
				<label>Estado: <input type="text" name="estado" id="uf" class="form-control"/></label>
				
				
				<input name="ibge" type="hidden" id="ibge" size="8" /></label><br />
				
			<br><br>
			<input value="Cancelar" type="button" class="btn btn-danger"/>
			<input value="Salvar" type="button" class="btn btn-success" onclick="alert('CLIENTE CADASTRO COM SUCESSO')"/>
				
				
				

					
	</form>
	

	
	
</span>	
</body>
</html>
