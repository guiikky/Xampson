package comando;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Conta;
import negocio.Extrato;
import to.ExtratoTO;

public class ConsultarExtrato7 implements Comando {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Conta conta = Conta.getInstance();
		ArrayList<ExtratoTO> lista = Extrato.carregar(conta.getConta());

		request.setAttribute("lista", lista);
		RequestDispatcher view = request.getRequestDispatcher("Extrat.jsp");
		view.forward(request, response);

	}

}
