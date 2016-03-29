package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import to.ExtratoTO;
import util.ConnectionFactory;

public class ExtratoDAO {

	public ExtratoTO incluir(ExtratoTO to) {
		String sqlInsert = "INSERT INTO log(conta, agencia, operacao, valor, data) VALUES (?, ?, ?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, to.getConta().getConta());
			stm.setInt(2, to.getConta().getAgencia());
			stm.setString(3, to.getOperacao());
			stm.setDouble(4, to.getValor());
			stm.setString(5, to.getData());
			stm.execute();
			String sqlSelect = "select LAST_INSERT_ID()";
			try (PreparedStatement pst1 = conn.prepareStatement(sqlSelect); ResultSet rs = pst1.executeQuery();) {
				if (rs.next()) {
					to.setId(rs.getInt("last_insert_id()"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return to;
	}

	public void atualizar(ExtratoTO to) {
		String sqlUpdate = "UPDATE log SET conta = ?, agencia = ?, operacao = ?, valor = ?, data = ? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, to.getConta().getConta());
			stm.setInt(2, to.getConta().getAgencia());
			stm.setString(3, to.getOperacao());
			stm.setDouble(4, to.getValor());
			stm.setString(5, to.getData());
			stm.setInt(6, to.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(ExtratoTO to) {
		String sqlDelete = "DELETE FROM log WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, to.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ExtratoTO> carregar(int id) {
		ArrayList<ExtratoTO> array = new ArrayList<ExtratoTO>();
		String sqlSelect = "SELECT valor, data, operacao FROM log WHERE conta = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					ExtratoTO to = new ExtratoTO();
					to.setData(rs.getString("data"));
					to.setOperacao(rs.getString("operacao"));
					to.setValor(rs.getDouble("valor"));
					array.add(to);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return array;
	}
}
