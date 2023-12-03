package Boundary;

import java.time.format.DateTimeFormatter;
import Controller.MedicoController;
import Entities.Medico;
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

public class MedicoBoundary implements BoundaryRender{

	private TableView<Medico> table = new TableView<>();
	private TextField txtId = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtCrm = new TextField();
	private TextField txtAreaAtuacao = new TextField();
	private TextField txtTelefone = new TextField();
	private TextField txtEndereco = new TextField();
	private TextField txtContratacao = new TextField();
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private MedicoController controller = new MedicoController();
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
		paneForm.add(new Label("CRM: "), 0, 2);
		paneForm.add(txtCrm, 1, 2);
		paneForm.add(new Label("Área de atuação: "), 0, 3);
		paneForm.add(txtAreaAtuacao, 1, 3);
		paneForm.add(new Label("Telefone: "), 0, 4);
		paneForm.add(txtTelefone, 1, 4);
		paneForm.add(new Label("Endereço: "), 0, 5);
		paneForm.add(txtEndereco, 1, 5);
		paneForm.add(new Label("Data contratação: "), 0, 6);
		paneForm.add(txtContratacao, 1, 6);
		
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
		Bindings.bindBidirectional(txtCrm.textProperty(), controller.crmProperty(), 
				new NumberStringConverter());
		Bindings.bindBidirectional(txtAreaAtuacao.textProperty(), controller.areaAtuacaoProperty());
		Bindings.bindBidirectional(txtTelefone.textProperty(), controller.telefoneProperty());
		Bindings.bindBidirectional(txtEndereco.textProperty(), controller.enderecoProperty());
		@SuppressWarnings("rawtypes")
		StringConverter localDtf = new LocalDateStringConverter(dtf, dtf);
		Bindings.bindBidirectional(txtContratacao.textProperty(), controller.contratacaoProperty(), 
				localDtf);
	}
	
	@SuppressWarnings("unchecked")
	private void generateTable() { 
		table.getColumns().clear();

		table.setItems(controller.getLista());
		
		TableColumn<Medico, Long> colId = new TableColumn<>("Id");
		colId.setCellValueFactory(new PropertyValueFactory<Medico, Long>("id"));
		
		TableColumn<Medico, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(itemData ->	new ReadOnlyStringWrapper(itemData.getValue().getNome()));
		
		TableColumn<Medico, Integer> colCrm = new TableColumn<>("CRM");
		colCrm.setCellValueFactory(new PropertyValueFactory<Medico, Integer>("crm"));
		
		TableColumn<Medico, String> colAreaAtuacao = new TableColumn<>("Área de atuação");
		colAreaAtuacao.setCellValueFactory(itemData ->	new ReadOnlyStringWrapper(itemData.getValue().getAreaAtuacao()));
		
		TableColumn<Medico, String> colTelefone = new TableColumn<>("Telefone");
		colTelefone.setCellValueFactory(itemData ->	new ReadOnlyStringWrapper(itemData.getValue().getTelefone()));
		
		TableColumn<Medico, String> colEndereco = new TableColumn<>("Endereço");
		colEndereco.setCellValueFactory(itemData ->	new ReadOnlyStringWrapper(itemData.getValue().getEndereco()));
		
		TableColumn<Medico, String> colContratacao = new TableColumn<>("Contratação");
		colContratacao.setCellValueFactory(itemData ->	new ReadOnlyStringWrapper(dtf.format(itemData.getValue().getContratacao())));
		
		table.getColumns().addAll(colId, colNome, colCrm, colAreaAtuacao, colTelefone, colEndereco, colContratacao);
	}
}
