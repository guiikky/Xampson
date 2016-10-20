package controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comando.Comando;
import util.Dados;


@WebServlet("/ServletControle")
public class ServletControle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doExecute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			new Dados(getServletContext().getRealPath("WEB-INF\\classes"));	
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			Comando comando = (Comando) Class.forName("comando." + request.getParameter("comando")).newInstance();
			comando.execute(request, response);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doExecute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doExecute(request, response);
	}

}