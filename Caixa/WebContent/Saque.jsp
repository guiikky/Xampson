<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="negocio.Conta"%>
<%@ page import="to.SaqueTO"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Saque</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
	<c:import url="Barra.jsp" />
    <!-- Container Principal -->
    <div id="main" class="container">
		<h3 class="page-header"><%=Conta.getInstance().getCliente().getNome() %></h3>
		<div class="col-md-12">
			<div class="page-header">
				<h3>Nao emitimos comprovante</h3>
			</div>
			<%SaqueTO to = (SaqueTO)request.getAttribute("saque"); %>
				<!-- area de campos do form -->
			<div class="row">
				<div class="form-group col-md-12">
					<label for="conta">Data: <%=to.getData() %></label>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label for="agencia">Valor: <%=to.getValor() %></label>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label for="senha">Saldo: <%=to.getConta().getSaldo() %></label>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>