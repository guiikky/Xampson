package controle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Conta;
import util.JSon;

@WebServlet("/saldo1")
public class ServiceSaldo extends HttpServlet {
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

		String conta = request.getParameter("conta");
		PrintWriter out = response.getWriter();

		try {
			int idConta = Integer.parseInt(conta);
			Conta cont = new Conta(idConta, 0, 0, null);
			cont.carregar();
			out.println(JSon.contaToJSon(cont));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSon.errorToJSon(e));
		}

	}
}
