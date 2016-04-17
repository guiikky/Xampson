<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="negocio.Conta"%>
<%@ page import="to.TransferenciaTO"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Transferencia</title>

	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
</head>

<body>
	<c:import url="Barra.jsp" />
	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header"><%=Conta.getInstance().getCliente().getNome() %></h3>
		<!-- Formulario para logar -->
		<form action="TransfControle" method="post">
			<div class="col-md-12">
				<div class="page-header">
					<h3>Transferencia</h3>
				</div>
				<% TransferenciaTO to = (TransferenciaTO)request.getAttribute("transf"); %>
				<!-- area de campos do form -->
				<div class="row">
					<div class="form-group col-md-12">
						<label for="nome">Nome: <%=to.getCos().getCliente().getNome()%></label>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label for="conta">Conta: <%=to.getCos().getConta()%></label>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label for="agencia">Agencia: <%=to.getCos().getAgencia()%></label>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label for="valor">Valor: <%=to.getValor() %></label>
					</div>
				</div>
				<hr></hr>
				<div id="actions" class="row">
					<div class="col-md-12">
						<button type="submit" class="btn btn-primary" name="acao" value="confirmar">Confirmar</button>
						<button type="submit" class="btn btn-default" name="acao" value="cancelar">Cancelar</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>