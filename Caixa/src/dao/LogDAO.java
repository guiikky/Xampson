package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import to.LogTO;
import util.ConnectionFactory;

public class LogDAO {
	public ArrayList<LogTO> carregar() {
		ArrayList<LogTO> lista = new ArrayList<LogTO>();
		String sqlSelect = "SELECT * FROM log";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();) {
			while (rs.next()) {
				LogTO to = new LogTO();
				to.setConta(rs.getInt("conta"));
				to.setAgencia(rs.getInt("agencia"));
				to.setData(rs.getString("data"));
				to.setValor(rs.getDouble("valor"));
				to.setOperacao(rs.getString("operacao"));
				lista.add(to);
			}
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return lista;
	}
}
