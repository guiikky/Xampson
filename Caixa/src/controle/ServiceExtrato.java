package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Extrato;
import to.ExtratoTO;
import util.JSon;

@WebServlet("/extrato1")
public class ServiceExtrato extends HttpServlet {
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
		ArrayList<ExtratoTO> lista = null;
		PrintWriter out = response.getWriter();

		try {
			int idConta = Integer.parseInt(conta);
			lista = Extrato.carregar(idConta);
			out.println(JSon.extratoListToJSon(lista));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSon.errorToJSon(e));
		}

	}
}