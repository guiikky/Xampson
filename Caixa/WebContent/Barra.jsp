<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="Menu.jsp">Caixa Eletronico</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="Sacar.jsp">Saque</a></li>
				<li><a href="Extrato.jsp">Extrato</a></li>
				<li><a href="Transferencia.jsp">Transferencia</a></li>
				<li><a href="Saldo.jsp">Saldo</a></li>
				<li><a href="DebitoAutomatico.jsp">Debito Automatico</a></li>
				<li><a href="AcessoControle?acao=sair">Sair</a></li>
			</ul>
		</div>
	</div>
</nav>