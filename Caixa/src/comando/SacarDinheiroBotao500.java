package comando;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.Conta;
import negocio.Dispenser;
import negocio.Saque;

public class SacarDinheiroBotao500 implements Comando {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String data;
		Calendar cldr = Calendar.getInstance();
		data = cldr.get(Calendar.DAY_OF_MONTH) + "/" + (cldr.get(Calendar.MONTH) + 1) + "/" + cldr.get(Calendar.YEAR);

		Conta conta = Conta.getInstance();
		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		Saque saque = new Saque(data, conta, new Dispenser(), 500.0);
		int code = saque.sacar();
		if (code == 0) {
			request.setAttribute("saque", saque.getTO());
			session.setAttribute("Saque", saque.getTO());
			view = request.getRequestDispatcher("Saque.jsp");
		} else if (code == 1) {
			view = request.getRequestDispatcher("Sacar.jsp");
		} else if (code == 2) {
			view = request.getRequestDispatcher("Sacar.jsp");
		} else if (code == 3) {
			view = request.getRequestDispatcher("Sacar.jsp");
		}

		view.forward(request, response);
	}
}
