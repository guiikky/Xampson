package negocio;

import java.io.IOException;
import java.util.ArrayList;

import dao.LogDAO;
import to.LogTO;

public class Log {

	public static ArrayList<LogTO> carregar() throws IOException {
		LogDAO dao = new LogDAO();
		ArrayList<LogTO> lista = dao.carregar();
		return lista;
	}

}
