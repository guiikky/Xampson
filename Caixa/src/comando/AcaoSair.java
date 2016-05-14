package comando;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.Conta;

public class AcaoSair implements Comando {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Conta.destroy();
		HttpSession session = request.getSession();
		session.invalidate();
		RequestDispatcher view = request.getRequestDispatcher("Login.jsp");
		view.forward(request, response);
	}
}
