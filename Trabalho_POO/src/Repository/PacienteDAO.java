package Repository;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mariadb.jdbc.Connection;
import Entities.Paciente;

public class PacienteDAO implements IObjectDAO<Paciente>{
	
	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/clinica?characterEncoding=latin1";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "123456";
	private Connection conexao;
	
	public PacienteDAO() {
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
	public void salvar(Paciente p) {
		String sql = "INSERT INTO pacientes (id, nome, cpf, telefone, endereco, nascimento, numero_convenio)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, p.getId());
			stmt.setString(2, p.getNome());
			stmt.setString(3, p.getCpf());
			stmt.setString(4, p.getTelefone());
			stmt.setString(5, p.getEndereco());
			stmt.setDate(6, Date.valueOf(p.getNascimento()));
			stmt.setInt(7, p.getNumeroConvenio());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Paciente> lerTodos() {
		return pesquisarNome("");
	}

	@Override
	public List<Paciente> pesquisarNome(String nome) {
		List<Paciente> pacientes = new ArrayList<>();
		String sql = "SELECT * FROM pacientes WHERE nome LIKE ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Paciente p = new Paciente();
				p.setId(rs.getLong("id"));
				p.setNome(rs.getString("nome"));
				p.setCpf(rs.getString("cpf"));
				p.setTelefone(rs.getString("telefone"));
				p.setEndereco(rs.getString("endereco"));
				p.setNascimento(rs.getDate("nascimento").toLocalDate());
				p.setNumeroConvenio(rs.getInt("numero_convenio"));
				pacientes.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pacientes;
	}
}
