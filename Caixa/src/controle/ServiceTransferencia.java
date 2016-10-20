package controle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Transferencia;
import util.JSon;

@WebServlet("/transferencia1")
public class ServiceTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		super.service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StringBuilder sb = JSon.montaJSon(request);
		PrintWriter out = response.getWriter();

		try {
			Transferencia transferencia = JSon.jSonToTransferencia(sb.toString());
			transferencia.transferir();
			out.println(JSon.transferenciaToJSon(transferencia));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(JSon.errorToJSon(e));
		}
	}	
}
