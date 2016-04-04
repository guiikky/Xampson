package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import negocio.Cliente;
import to.ClienteTO;
import to.ContaTO;
import util.ConnectionFactory;

public class ContaDAO {

	public void incluir(ContaTO to) {
		ClienteTO to2 = new ClienteTO();
		ClienteDAO dao = new ClienteDAO();
		to2.setNome(to.getCliente().getNome());
		to2.setTipo(to.getCliente().getTipo());
		dao.incluir(to2);
		to.getCliente().setId(to2.getId());
		String sqlInsert = "INSERT INTO conta(conta, agencia, saldo, idCliente) VALUES (?, ?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, to.getConta());
			stm.setInt(2, to.getAgencia());
			stm.setDouble(3, to.getSaldo());
			stm.setInt(4, to2.getId());
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizar(ContaTO to) {
		String sqlUpdate = "UPDATE conta SET agencia=?, saldo=? WHERE conta=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, to.getAgencia());
			stm.setDouble(2, to.getSaldo());
			stm.setInt(3, to.getConta());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(ContaTO to) {
		ClienteTO to2 = new ClienteTO();
		ClienteDAO dao = new ClienteDAO();
		String sqlDelete = "DELETE FROM conta WHERE conta = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, to.getConta());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		to2.setId(to.getCliente().getId());
		dao.excluir(to2);
	}

	public ContaTO carregar(int id) {
		ContaTO to = new ContaTO();
		String sqlSelect = "SELECT agencia, saldo, idCliente FROM conta WHERE conta = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					to.setAgencia(rs.getInt("agencia"));
					to.setSaldo(rs.getDouble("saldo"));
					int idCliente = rs.getInt("idCliente");
					Cliente cl = new Cliente(idCliente, null, 0);
					cl.carregar();
					to.setCliente(cl);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return to;
	}
}