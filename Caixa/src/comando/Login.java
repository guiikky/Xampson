package comando;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.Conta;
import util.Acesso;
import util.Dados;

public class Login implements Comando {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pConta = request.getParameter("conta");
		String pAgencia = request.getParameter("agencia");
		String pSenha = request.getParameter("senha");

		int conta;
		int agencia;
		int senha;
		try {
			conta = Integer.parseInt(pConta);
			agencia = Integer.parseInt(pAgencia);
			senha = Integer.parseInt(pSenha);
		} catch (NumberFormatException e) {
			conta = agencia = senha = -1;
			e.printStackTrace();
		}

		int vet[] = Dados.random();
		request.setAttribute("vet", vet);

		Conta logado = new Conta(conta, agencia, 0, null);
		Acesso acesso = new Acesso(conta, agencia, senha);
		int code = acesso.validar();
		
		HttpSession session = request.getSession();
		RequestDispatcher view = null;

		if (code == 0) {
			logado.carregar();
			session.setAttribute("logado", logado.getTO());
			view = request.getRequestDispatcher("GerarCodigo.jsp");
		} else if (code == 1) {
			logado.carregar();
			session.setAttribute("logado", logado.getTO());
			view = request.getRequestDispatcher("AcessarCodigo.jsp");
		} else if (code == 2) {
			view = request.getRequestDispatcher("Login.jsp");
		} else if (code == 3) {
			view = request.getRequestDispatcher("Login.jsp");
		}

		view.forward(request, response);
	}
}
