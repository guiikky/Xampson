package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import to.DebitoAutomaticoTO;
import util.ConnectionFactory;

public class DebitoAutomaticoDAO {

	public void incluir(DebitoAutomaticoTO to) {
		String sqlInsert = "INSERT INTO debitoAutomatico VALUES(operadora, consumidor, data, conta) VALUES (?, ?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, to.getOperadora());
			stm.setInt(2, to.getConsumidor());
			stm.setString(3, to.getData());
			stm.setInt(4, to.getConta().getConta());
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}