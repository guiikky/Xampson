<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://WebContent/WEB-INF/Conta.tld" prefix="conta"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>AcessarCodigo</title>

	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
</head>

<body>
	<!-- Barra superior com os menus de navegação -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">Caixa Eletronico</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse"></div>
		</div>
	</nav>
	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">${conta:getInstance().cliente.nome}</h3>
		<!-- Formulario para logar -->
		<form action="ServletControle" method="post">
			<!-- area de campos do form -->
			<div class="col-md-12">
				<div class="page-header">
					<h3>Codigo de Acesso</h3>
				</div>
				<div class="row">
					<div class="form-group col-md-12">	
						<input type="number" class="form-control" name="codigo" id="codigo" required maxlength="3" placeholder="123">
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[0]}">${vet[0]}</button>
						<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[1]}">${vet[1]}</button>
						<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[2]}">${vet[2]}</button>
						<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[3]}">${vet[3]}</button>
						<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[4]}">${vet[4]}</button>
						<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[5]}">${vet[5]}</button>
						<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[6]}">${vet[6]}</button>
						<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[7]}">${vet[7]}</button>
						<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[8]}">${vet[8]}</button>
						<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[9]}">${vet[9]}</button>
					</div>
				</div>
				<hr></hr>
				<div id="actions" class="row">
					<div class="col-md-12">
						<button type="submit" class="btn btn-primary" name="comando" value="AcessarCodigo">Continuar</button>
						<button type="reset" class="btn btn-default">Corrigir</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script>
	function myFunction(button) {
		var x = button.value;
	    document.getElementById("codigo").innerHTML += x;
	}
	</script>
</html>