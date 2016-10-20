package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import to.ClienteTO;
import util.ConnectionFactory;

public class ClienteDAO {

	public void incluir(ClienteTO to) throws IOException {
		String sqlInsert = "INSERT INTO cliente(nome, tipo) VALUES (?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			// stm.setInt(1, to.getId());
			stm.setString(1, to.getNome());
			stm.setInt(2, to.getTipo());
			stm.execute();
			String sqlSelect = "select LAST_INSERT_ID()";
			try (PreparedStatement pst1 = conn.prepareStatement(sqlSelect); ResultSet rs = pst1.executeQuery();) {
				if (rs.next()) {
					to.setId(rs.getInt("last_insert_id()"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}

	public void atualizar(ClienteTO to) throws IOException {
		String sqlUpdate = "UPDATE cliente SET nome=?, tipo=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, to.getNome());
			stm.setInt(2, to.getTipo());
			stm.setInt(3, to.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}

	public void excluir(ClienteTO to) throws IOException {
		String sqlDelete = "DELETE FROM cliente WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, to.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}

	public ClienteTO carregar(int id) throws IOException {
		ClienteTO to = new ClienteTO();
		String sqlSelect = "SELECT nome, tipo FROM cliente WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					to.setNome(rs.getString("nome"));
					to.setTipo(rs.getInt("tipo"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e1) {
			e1.getStackTrace();
			throw new IOException(e1);
		}
		return to;
	}
}
