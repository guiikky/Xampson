<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="to.ExtratoTO" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Extrato</title>
</head>
<body>
	<h3>Extrato</h3>
		<%ArrayList<ExtratoTO> to = (ArrayList<ExtratoTO>)request.getAttribute("extrato"); %>
		<%for(ExtratoTO i: to ) { %>
		Data: <%=i.getData() %><br>
		Operacao: <%=i.getOperacao()%><br>
		Valor: <%=i.getValor() %><br><br>
		<%} %>
</body>
</html>