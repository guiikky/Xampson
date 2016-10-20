package controle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Saque;
import util.JSon;

@WebServlet("/saque1")
public class ServiceSaque extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		super.service(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuilder sb = JSon.montaJSon(request);
		PrintWriter out = response.getWriter();

		try {
			Saque saque = JSon.jSonToSaque(sb.toString());
			saque.sacar();
			out.println(JSon.saqueToJSon(saque));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSon.errorToJSon(e));
		}
	}
}
