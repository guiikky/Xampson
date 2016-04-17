<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="negocio.Conta"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Menu</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
	<c:import url="Barra.jsp" />
    <!-- Container Principal -->
    <div id="main" class="container">
		<h3 class="page-header"><%=Conta.getInstance().getCliente().getNome() %></h3>
			<!-- area de campos do form -->
			<div class="col-md-6">
				<div class="page-header">
					<h3>Menu</h3>
				</div>
			</div>
	</div>
</body>

</html>