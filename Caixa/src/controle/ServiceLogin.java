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

@WebServlet("/login1")
public class ServiceLogin extends HttpServlet {
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
		String[] contas = Dados.acesso();
		out.println(JSon.acessoListToJSon(contas));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		new Dados(getServletContext().getRealPath("WEB-INF\\classes"));
		StringBuilder sb = JSon.montaJSon(request);
		PrintWriter out = response.getWriter();

		try {
			Acesso acesso = JSon.jSonToAcesso(sb.toString());
			int code = acesso.validar();
			if (code == 0 || code == 1) {
				out.println(JSon.acessoToJSon(acesso));
			} else {
				out.println(JSon.acessoToJSon(new Acesso(-1, -1, -1)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSon.errorToJSon(e));
		}
	}
}
