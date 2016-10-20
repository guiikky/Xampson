package comando;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.Conta;
import negocio.DebitoAutomatico;
import util.Dados;

public class CriarDebito implements Comando {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pOperadoara = request.getParameter("operadora");
		String pConsumidor = request.getParameter("consumidor");
		String pValor = request.getParameter("valor");
		String data;

		Calendar cldr = Calendar.getInstance();
		data = cldr.get(Calendar.DAY_OF_MONTH) + "/" + (cldr.get(Calendar.MONTH) + 1) + "/" + cldr.get(Calendar.YEAR);

		int operadora;
		int consumidor;
		double valor;
		try {
			operadora = Integer.parseInt(pOperadoara);
			consumidor = Integer.parseInt(pConsumidor);
			valor = Double.parseDouble(pValor);
		} catch (NumberFormatException e) {
			operadora = consumidor = -1;
			valor = -1;
			e.printStackTrace();
		}

		Conta conta = Conta.getInstance();
		DebitoAutomatico debito = new DebitoAutomatico(operadora, consumidor, data, valor, conta);
		debito.criar();

		HttpSession session = request.getSession();
		request.setAttribute("debito", debito.getTO());
		session.setAttribute(Dados.getAcao() + "-DebitoAutomatico", debito.getTO());
		RequestDispatcher view = request.getRequestDispatcher("Menu.jsp");
		view.forward(request, response);
	}

}
