package controle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Acesso;
import util.Dados;
import util.JSon;

@WebServlet("/validar1")
public class ServiceValidar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		super.service(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		new Dados(getServletContext().getRealPath("WEB-INF\\classes"));
		PrintWriter out = response.getWriter();
		String conta = request.getParameter("conta");
		String agencia = request.getParameter("agencia");
		String normal[] = Dados.acesso();
		Dados.setDados(normal);

		if (Dados.verificar(conta, agencia)) {
			out.println(JSon.acessoToJSon(new Acesso(Integer.parseInt(conta), Integer.parseInt(agencia), 0)));
		} else {
			out.println(JSon.acessoToJSon(new Acesso(-1, -1, -1)));
		}

	}
}
