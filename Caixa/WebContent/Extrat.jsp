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
	<c:import url="Barra.jsp" />
    <!-- Container Principal -->
    <div id="main" class="container">
		<h3 class="page-header">${conta:getInstance().cliente.nome}</h3>
			<!-- area de campos do form -->
			<div class="col-md-6">
				<div class="page-header">
					<h3>Extrato</h3>
				</div>
			</div>
	</div>
	
	<div id="main" class="container-fluid">
        <hr />
        <div id="list" class="row">
       		<div class="table-responsive col-md-12">
            	<table class="table table-striped">
                	<thead>
                    	<tr>
                        	<th>Data</th>
                            <th>Operacao</th>
                            <th>Valor</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="extrato" items="${lista}">
                    	<tr>
                       		<td>
                        		${extrato.data}
                        	</td>
                        	<td>
                        		${extrato.operacao}
                        	</td>
                        	<td>
                        		${extrato.valor}
                        	</td>
                        </tr>
                    </c:forEach>
					</tbody>
                </table>
            </div>
        </div>
                <!-- /#list -->
		<div id="bottom" class="row">
        	<div class="col-md-12">
            	<!-- paginação ainda não foi implementada -->
             	<ul class="pagination">
                	<li class="disabled"><a>&lt; Anterior</a> </li>
                    <li class="disabled"><a>1</a> </li>
                    <li><a href="#">2</a> </li>
                    <li><a href="#">3</a> </li>
                    <li class="next"><a href="#" rel="next">Próximo &gt;</a> </li>
                </ul>
                        <!-- /.pagination -->
            </div>
        </div>
                <!-- /#bottom -->
    </div>
</body>
</html>