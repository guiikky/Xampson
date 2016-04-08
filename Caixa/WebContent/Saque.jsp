<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="to.SaqueTO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Saque</title>
</head>
<body>
	<h3>Saque</h3>
		<%SaqueTO to = (SaqueTO)request.getAttribute("saque"); %>
		Data: <%=to.getData() %><br>
		Valor: <%=to.getValor() %><br>
		Saldo: <%=to.getConta().getSaldo() %>
</body>
</html>