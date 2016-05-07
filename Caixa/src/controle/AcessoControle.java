package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.Conta;
import to.AcaoTO;
import util.Acesso;
import util.Dados;

/**
 * Servlet implementation class AcessoControle
 */
@WebServlet("/AcessoControle")
public class AcessoControle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pConta = request.getParameter("conta");
		String pAgencia = request.getParameter("agencia");
		String pSenha = request.getParameter("senha");
		String acao = request.getParameter("acao");
		
		RequestDispatcher view = null;

		if (acao.equals("logar")) {
			int conta;
			int agencia;
			int senha;
			int code = -1;
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
			
			ServletContext sc = getServletContext();
			String path = sc.getRealPath("WEB-INF\\classes");	
			new Dados(path);

			Acesso acesso = new Acesso(conta, agencia, senha);
			code = acesso.validar();
			

			if (code == 0) {
				view = request.getRequestDispatcher("GerarCodigo.jsp");
			} else if (code == 1) {
				view = request.getRequestDispatcher("AcessarCodigo.jsp");
			} else if (code == 2) {
				view = request.getRequestDispatcher("Login.jsp");
			} else if (code == 3) {
				view = request.getRequestDispatcher("Login.jsp");
			}
		}
		else if( acao.equals("sair")){
			HttpSession session = request.getSession();
			Enumeration<String> list = session.getAttributeNames();
			
			if( list.hasMoreElements() ){
				ArrayList<AcaoTO> lista = getArray(list);
				session.setAttribute("lista", lista);
				view = request.getRequestDispatcher("Acao.jsp");
			}else{
				Conta.destroy();
				session.invalidate();
				view = request.getRequestDispatcher("Login.jsp");
			}
		}

		view.forward(request, response);
	}
	
	private ArrayList<AcaoTO> getArray(Enumeration<String> list){
		ArrayList<AcaoTO> lista = new ArrayList<AcaoTO>();
		while(list.hasMoreElements()){
			AcaoTO to = new AcaoTO();
			to.setOperacao(list.nextElement());
			lista.add(to);
		}
		return lista;
	}

}
