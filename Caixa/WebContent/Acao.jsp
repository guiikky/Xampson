<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://WebContent/WEB-INF/Conta.tld" prefix="conta"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
			<!-- area de campos do form -->
			<div class="col-md-6">
				<div class="page-header">
					<h3>Acoes Efetuadas</h3>
				</div>
			</div>
	</div>
	
	<div id="main" class="container-fluid">
    	<form action="ServletControle" method="post">
        <hr />
        <div id="list" class="row">
       		<div class="table-responsive col-md-12">
            	<table class="table table-striped">
                	<thead>
                    	<tr>
                        	<th>Operacao</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="acao" items="${lista}">
                    	<tr>
                       		<td>
                        		${acao.operacao}
                        	</td>
                        </tr>
                    </c:forEach>
					</tbody>
                </table>
                <hr></hr>
				<div id="actions" class="row">
					<div class="form-group col-md-12">
						<button type="submit" class="btn btn-primary" name="comando" value="AcaoSair">OK</button>
					</div>
				</div>
            </div>
        </div>
        </form>
                <!-- /#bottom -->
    </div>
</body>
</html>