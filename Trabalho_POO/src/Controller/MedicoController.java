package Controller;

import Entities.Medico;
import Repository.MedicoDAO;
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

public class MedicoController {
	
	private LongProperty id = new SimpleLongProperty(0);
	private StringProperty nome = new SimpleStringProperty("");
	private IntegerProperty crm = new SimpleIntegerProperty(0);
	private StringProperty areaAtuacao = new SimpleStringProperty("");
	private StringProperty telefone = new SimpleStringProperty("(11)");
	private StringProperty endereco = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> contratacao = new SimpleObjectProperty<>(LocalDate.now());
	
	private ObservableList<Medico> lista = FXCollections.observableArrayList();
	private MedicoDAO medicoDAO = new MedicoDAO();
	
	public LongProperty idProperty() {
		return this.id;
	}
	
	public StringProperty nomeProperty() {
		return this.nome;
	}
	
	public IntegerProperty crmProperty() {
		return this.crm;
	}
	
	public StringProperty areaAtuacaoProperty() {
		return this.areaAtuacao;
	}
	
	public StringProperty telefoneProperty() {
		return this.telefone;
	}
	
	public StringProperty enderecoProperty() {
		return this.endereco;
	}
	
	public ObjectProperty<LocalDate> contratacaoProperty() {
		return this.contratacao;
	}
	
	public ObservableList<Medico> getLista() {
		return this.lista;
	}
	
	public void salvar() {
		if(verificaCampos()) {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Medico m = new Medico();
		m.setId(id.get());
		m.setNome(nome.get());
		m.setCrm(crm.get());
		m.setAreaAtuacao(areaAtuacao.get());
		m.setTelefone(telefone.get());
		m.setEndereco(endereco.get());
		m.setContratacao(contratacao.get());
		medicoDAO.salvar(m);
		atualizar();
		JOptionPane.showMessageDialog(null, "Médico cadastrado com sucesso!");
	}
	
	public void atualizar() {
		List<Medico> medicos = medicoDAO.lerTodos();
		lista.clear();
		lista.addAll(medicos);
	}
	
	public void pesquisar() {
		List<Medico> medicos = medicoDAO.pesquisarNome(nome.get());
		lista.clear();
		lista.addAll(medicos);
	}
	
	private boolean verificaCampos() {
		return nome.get() == "" || crm.get() == 0 || areaAtuacao.get() == "" || endereco.get() == "" || telefone.get() == "";		
	}
}
