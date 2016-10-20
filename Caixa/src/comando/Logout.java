package comando;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.Conta;
import to.AcaoTO;

public class Logout implements Comando {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = null;
		HttpSession session = request.getSession();
		Enumeration<String> list = session.getAttributeNames();

		if (list.hasMoreElements()) {
			ArrayList<AcaoTO> lista = getArray(list);
			request.setAttribute("lista", lista);
			view = request.getRequestDispatcher("Acao.jsp");
		} else {
			Conta.destroy();
			session.invalidate();
			view = request.getRequestDispatcher("Login.jsp");
		}
		view.forward(request, response);
	}

	private ArrayList<AcaoTO> getArray(Enumeration<String> list) {
		ArrayList<AcaoTO> lista = new ArrayList<AcaoTO>();
		while (list.hasMoreElements()) {
			AcaoTO to = new AcaoTO();
			String aux = list.nextElement();
			if (!aux.equals("logado")) {
				to.setOperacao(aux);
			}
			lista.add(to);
		}
		return lista;
	}

}
