package Repository;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mariadb.jdbc.Connection;
import Entities.Medico;

public class MedicoDAO implements IObjectDAO<Medico>{

	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/clinica?characterEncoding=latin1";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "123456";
	private Connection conexao;
	
	public MedicoDAO() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conexao = (Connection) DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void salvar(Medico m) {
		String sql = "INSERT INTO medicos (id, nome, crm, area_atuacao, telefone, endereco, data_contratacao)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, m.getId());
			stmt.setString(2, m.getNome());
			stmt.setInt(3, m.getCrm());
			stmt.setString(4, m.getAreaAtuacao());
			stmt.setString(5, m.getTelefone());
			stmt.setString(6, m.getEndereco());
			stmt.setDate(7, Date.valueOf(m.getContratacao()));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Medico> lerTodos() {
		return pesquisarNome("");
	}

	@Override
	public List<Medico> pesquisarNome(String nome) {
		List<Medico> medicos = new ArrayList<>();
		String sql = "SELECT * FROM medicos WHERE nome LIKE ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Medico m = new Medico();
				m.setId(rs.getLong("id"));
				m.setNome(rs.getString("nome"));
				m.setCrm(rs.getInt("crm"));
				m.setAreaAtuacao(rs.getString("area_atuacao"));
				m.setTelefone(rs.getString("telefone"));
				m.setEndereco(rs.getString("endereco"));
				m.setContratacao(rs.getDate("data_contratacao").toLocalDate());
				medicos.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medicos;
	}
}
