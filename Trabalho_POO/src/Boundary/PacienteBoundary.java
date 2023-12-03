package Boundary;

import java.time.format.DateTimeFormatter;
import Controller.PacienteController;
import Entities.Paciente;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;

public class PacienteBoundary implements BoundaryRender{
	
	private TableView<Paciente> table = new TableView<>();
	private TextField txtId = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtCpf = new TextField();
	private TextField txtTelefone = new TextField();
	private TextField txtEndereco = new TextField();
	private TextField txtNascimento = new TextField();
	private TextField txtNumConvenio = new TextField();
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private PacienteController controller = new PacienteController();
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Override
	public Pane render() {
		BorderPane panePrincipal = new BorderPane();
		
		txtId.setEditable(false);
		
		GridPane paneForm = new GridPane();
		paneForm.add(new Label("Id: "), 0, 0);
		paneForm.add(txtId, 1, 0);
		paneForm.add(new Label("Nome: "), 0, 1);
		paneForm.add(txtNome, 1, 1);
		paneForm.add(new Label("CPF: "), 0, 2);
		paneForm.add(txtCpf, 1, 2);
		paneForm.add(new Label("Telefone: "), 0, 3);
		paneForm.add(txtTelefone, 1, 3);
		paneForm.add(new Label("Endereço: "), 0, 4);
		paneForm.add(txtEndereco, 1, 4);
		paneForm.add(new Label("Nascimento: "), 0, 5);
		paneForm.add(txtNascimento, 1, 5);
		paneForm.add(new Label("N° Convênio: "), 0, 6);
		paneForm.add(txtNumConvenio, 1, 6);
		
		paneForm.add(btnAdicionar, 0, 7);
		paneForm.add(btnPesquisar, 1, 7);
		
		btnAdicionar.setOnAction(e-> controller.salvar());
		btnPesquisar.setOnAction(e-> controller.pesquisar());
		
		panePrincipal.setCenter(table);
		panePrincipal.setTop(paneForm);

		generateBindings();
		generateTable();
		return panePrincipal;
	}
	
	@SuppressWarnings("unchecked")
	private void generateBindings() {
		Bindings.bindBidirectional(txtId.textProperty(), controller.idProperty(), 
				new NumberStringConverter());
		Bindings.bindBidirectional(txtNome.textProperty(), controller.nomeProperty());
		Bindings.bindBidirectional(txtCpf.textProperty(), controller.cpfProperty());
		Bindings.bindBidirectional(txtTelefone.textProperty(), controller.telefoneProperty());
		Bindings.bindBidirectional(txtEndereco.textProperty(), controller.enderecoProperty());
		@SuppressWarnings("rawtypes")
		StringConverter localDtf = new LocalDateStringConverter(dtf, dtf);
		Bindings.bindBidirectional(txtNascimento.textProperty(), controller.nascimentoProperty(), 
				localDtf);
		Bindings.bindBidirectional(txtNumConvenio.textProperty(), controller.numeroConvenioProperty(), 
				new NumberStringConverter());
	}
	
	@SuppressWarnings("unchecked")
	private void generateTable() { 
		table.getColumns().clear();

		table.setItems(controller.getLista());
		
		TableColumn<Paciente, Long> colId = new TableColumn<>("Id");
		colId.setCellValueFactory(new PropertyValueFactory<Paciente, Long>("id"));
		
		TableColumn<Paciente, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(itemData ->	new ReadOnlyStringWrapper(itemData.getValue().getNome()));
		
		TableColumn<Paciente, String> colCpf = new TableColumn<>("CPF");
		colNome.setCellValueFactory(itemData ->	new ReadOnlyStringWrapper(itemData.getValue().getCpf()));
		
		TableColumn<Paciente, String> colTelefone = new TableColumn<>("Telefone");
		colTelefone.setCellValueFactory(itemData ->	new ReadOnlyStringWrapper(itemData.getValue().getTelefone()));
		
		TableColumn<Paciente, String> colEndereco = new TableColumn<>("Endereço");
		colTelefone.setCellValueFactory(itemData ->	new ReadOnlyStringWrapper(itemData.getValue().getEndereco()));
		
		TableColumn<Paciente, String> colNascimento = new TableColumn<>("Nascimento");
		colNascimento.setCellValueFactory(itemData ->	new ReadOnlyStringWrapper(dtf.format(itemData.getValue().getNascimento())));
	
		TableColumn<Paciente, Integer> colConvenio = new TableColumn<>("N° Convênio");
		colConvenio.setCellValueFactory(new PropertyValueFactory<Paciente, Integer>("numeroConvenio"));
		
		table.getColumns().addAll(colId, colNome, colCpf, colTelefone, colEndereco, colNascimento, colConvenio);
	}
}
