<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://WebContent/WEB-INF/Conta.tld" prefix="conta"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Extrato</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <c:import url="Barra.jsp" />
    <!-- Container Principal -->
    <div id="main" class="container">
		<h3 class="page-header">${conta:getInstance().cliente.nome}</h3>
		<!-- Formulario para logar -->
		<form action="ServletControle" method="post">
			<div class="col-md-12">
				<div class="page-header">
					<h3>Extrato</h3>
				</div>
				<!-- area de campos do form -->
				<div class="row">
					<div class="form-group col-md-12">
						<label for="dias">Dias</label>
						<input type="number" class="form-control" name="dias" id="dias" maxlength="8" placeholder="5">
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<button type="submit" class="btn btn btn-default" name="comando" value="ConsultarExtrato7">7 Dias</button>
						<button type="submit" class="btn btn btn-default" name="comando" value="ConsultarExtrato">Tudo</button>
						<button type="submit" class="btn btn btn-default" name="comando" value="ConsultarExtrato15">15 Dias</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>