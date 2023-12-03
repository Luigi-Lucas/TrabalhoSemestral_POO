package Controller;

import Entities.Paciente;
import Repository.PacienteDAO;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javafx.beans.property.LongProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PacienteController {
	
	private LongProperty id = new SimpleLongProperty(0);
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty cpf = new SimpleStringProperty("");
	private StringProperty telefone = new SimpleStringProperty("");
	private StringProperty endereco = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> nascimento = new SimpleObjectProperty<>(LocalDate.now());
	private IntegerProperty numeroConvenio = new SimpleIntegerProperty();
	
	private ObservableList<Paciente> lista = FXCollections.observableArrayList();
	private PacienteDAO pacienteDao = new PacienteDAO();

	public LongProperty idProperty() {
		return this.id;
	}
	
	public StringProperty nomeProperty() {
		return this.nome;
	}
	
	public StringProperty cpfProperty() {
		return this.cpf;
	}
	
	public StringProperty telefoneProperty() {
		return this.telefone;
	}
	
	public StringProperty enderecoProperty() {
		return this.endereco;
	}
	
	public ObjectProperty<LocalDate> nascimentoProperty() {
		return this.nascimento;
	}
	
	public IntegerProperty numeroConvenioProperty() {
		return this.numeroConvenio;
	}
	
	public ObservableList<Paciente> getLista() {
		return this.lista;
	}
	
	public void salvar() {
		if(verificaCampos()) {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(!verificaCpf()) {
			JOptionPane.showMessageDialog(null, "CPF deve ter 11 números!", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Paciente p = new Paciente();
		p.setId(id.get());
		p.setNome(nome.get());
		p.setCpf(cpf.get());
		p.setTelefone(telefone.get());
		p.setEndereco(endereco.get());
		p.setNascimento(nascimento.get());
		p.setNumeroConvenio(numeroConvenio.get());
		pacienteDao.salvar(p);
		atualizar();
		JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");
	}

	public void atualizar() {
		List<Paciente> pacientes = pacienteDao.lerTodos();
		lista.clear();
		lista.addAll(pacientes);
	}
	
	public void pesquisar() {
		List<Paciente> pacientes = pacienteDao.pesquisarNome(nome.get());
		lista.clear();
		lista.addAll(pacientes);
	}
	
	private boolean verificaCampos() {
		return nome.get() == "" || cpf.get() == "" || telefone.get() == "" || endereco.get() == "" || numeroConvenio.get() == 0;		
	}
	
	private boolean verificaCpf() {
		return cpf.get().length() == 11;
	}
}
