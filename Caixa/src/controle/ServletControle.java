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
//		new Dados(getServletContext().getRealPath("WEB-INF\\classes"));	
//		ServletContext sc = getServletContext();
//		String path = sc.getRealPath("WEB-INF\\classes");	
//		new Dados(path);
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





//<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[0]}">${vet[0]}</button>
//<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[1]}">${vet[1]}</button>
//<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[2]}">${vet[2]}</button>
//<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[3]}">${vet[3]}</button>
//<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[4]}">${vet[4]}</button>
//<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[5]}">${vet[5]}</button>
//<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[6]}">${vet[6]}</button>
//<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[7]}">${vet[7]}</button>
//<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[8]}">${vet[8]}</button>
//<button type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[9]}">${vet[9]}</button>

//<input type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[0]}">
//<input type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[1]}">
//<input type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[2]}">
//<input type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[3]}">
//<input type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[4]}">
//<input type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[5]}">
//<input type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[6]}">
//<input type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[7]}">
//<input type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[8]}">
//<input type="button" id="myBtn" class="btn btn btn-default" onclick="myFunction(this)" value="${vet[9]}">




