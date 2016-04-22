package negocio;

import java.util.ArrayList;

import dao.LogDAO;
import to.LogTO;

public class Log {

	public static String[][] carregar() {
		LogDAO dao = new LogDAO();
		ArrayList<LogTO> array = dao.carregar();
		String mat[][] = new String[array.size()][5];
		for (int i = 0; i < mat.length; i++) {
			mat[i][0] = array.get(i).getConta() + "";
			mat[i][1] = array.get(i).getAgencia() + "";
			mat[i][2] = array.get(i).getData();
			mat[i][3] = array.get(i).getOperacao();
			mat[i][4] = array.get(i).getValor() + "";
		}
		return mat;
	}

}
