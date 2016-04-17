<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="negocio.Conta"%>
<%@ page import="to.ExtratoTO"%>
<%@ page import="java.util.ArrayList"%>
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
		<h3 class="page-header"><%=Conta.getInstance().getCliente().getNome() %></h3>
			<!-- area de campos do form -->
			<div class="col-md-6">
				<div class="page-header">
					<h3>Extrato</h3>
				</div>
			</div>
	</div>
	
	<div id="main" class="container-fluid">
		<h3 class="page-header"><%=Conta.getInstance().getCliente().getNome() %></h3>
                <form action="ExtratoControle" method="post">
                    <div id="top" class="row">
                        <div class="col-md-3">
                            <h2>Extrato</h2>
                        </div>

                        <div class="col-md-6">
                            <div class="input-group h2">
                                <input name="data[search]" class="form-control" id="search" type="text" placeholder="Pesquisar Clientes (deixe vazio para trazer todos)">
                                <span class="input-group-btn">
                <button class="btn btn-primary" type="submit">
                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <!-- /#top -->
                </form>
                <hr />
                <div id="list" class="row">

                    <div class="table-responsive col-md-12">
                        <table class="table table-striped" cellpadding="0" cellpadding="0">
                            <thead>
                                <tr>
                                    <th>Data</th>
                                    <th>Operacao</th>
                                    <th>vVlor</th>
                                    <th class="actions">Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% ArrayList<ExtratoTO>lista = (ArrayList
                                    <ExtratoTO>)request.getAttribute("lista"); for(ExtratoTO to:lista){ %>
                                        <tr>
                                            <td>
                                                <%=to.getData()%>
                                            </td>
                                            <td>
                                                <%=to.getOperacao() %>
                                            </td>
                                            <td>
                                                <%=to.getValor() %>
                                            </td>
                                            <td class="actions">
                                                <a class="btn btn-success btn-xs" href="ManterCliente.do?acao=Visualizar&id=<%=to.getId()%>">Visualizar</a>
                                                <a class="btn btn-warning btn-xs" href="ManterCliente.do?acao=Editar&id=<%=to.getId()%>">Editar</a>
                                                <button id="btn<%=to.getId()%>" type="button" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#delete-modal" data-cliente="<%=to.getId()%>">Excluir</button>
                                            </td>
                                        </tr>
                                        <% } %>

                            </tbody>
                        </table>

                    </div>
                </div>
                <!-- /#list -->

                <div id="bottom" class="row">
                    <div class="col-md-12">
                        <!-- paginação ainda não foi implementada -->
                        <ul class="pagination">
                            <li class="disabled"><a>&lt; Anterior</a>
                            </li>
                            <li class="disabled"><a>1</a>
                            </li>
                            <li><a href="#">2</a>
                            </li>
                            <li><a href="#">3</a>
                            </li>
                            <li class="next"><a href="#" rel="next">Próximo &gt;</a>
                            </li>
                        </ul>
                        <!-- /.pagination -->

                    </div>
                </div>
                <!-- /#bottom -->
            </div>
	
	
</body>

</html>