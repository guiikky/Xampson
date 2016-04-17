<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Caixa Eletronico</title>

	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
</head>

<body>
	<!-- Barra superior com os menus de navegação -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="Login.html">Caixa Eletronico</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse"></div>
		</div>
	</nav>
	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">Login</h3>
		<!-- Formulario para logar -->
		<form action="AcessoControle" method="post">
			<!-- area de campos do form -->
			<div class="row">
				<div class="form-group col-md-12">
					<label for="conta">Conta</label>
					<input type="number" class="form-control" name="conta" id="conta" required maxlength="8" placeholder="00000000">
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label for="agencia">Agencia</label>
					<input type="number" class="form-control" name="agencia" id="agencia" required maxlength="4" placeholder="0000">
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label for="senha">Senha</label>
					<input type="password" class="form-control" name="senha" id="senha" required maxlength="4" placeholder="xxxx">
				</div>
			</div>
			<hr></hr>
			<div id="actions" class="row">
				<div class="form-group col-md-12">
					<button type="submit" class="btn btn-primary" name="acao" value="Logar">Logar</button>
				</div>
			</div>
		</form>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>