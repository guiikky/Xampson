package comando;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Dados;

public class AcessarCodigo implements Comando {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("codigo");

		int vet[] = Dados.random();
		request.setAttribute("vet", vet);

		RequestDispatcher view = null;

		int code = Dados.acessarCodigo(codigo);
		if (code == 0) {
			view = request.getRequestDispatcher("Menu.jsp");
		} else if (code == 1) {
			view = request.getRequestDispatcher("AcessarCodigo.jsp");
		} else if (code == 2) {
			view = request.getRequestDispatcher("Login.jsp");
		}

		view.forward(request, response);
	}
}
