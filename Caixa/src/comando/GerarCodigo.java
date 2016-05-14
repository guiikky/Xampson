package comando;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Dados;

public class GerarCodigo implements Comando {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("codigo");

		int vet[] = Dados.random();
		request.setAttribute("vet", vet);

		Dados.gerarCodigo(codigo);
		RequestDispatcher view = request.getRequestDispatcher("AcessarCodigo.jsp");
		view.forward(request, response);
	}
}
