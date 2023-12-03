package Boundary;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BoundaryPrincipal extends Application{
	
	private PacienteBoundary pacienteBoundary = new PacienteBoundary();
	private MedicoBoundary medicoBoundary = new MedicoBoundary();
	private BorderPane panePrincipal = new BorderPane();

	@Override
	public void start(Stage stage) throws Exception {
		
		Scene scn = new Scene(panePrincipal, 800, 600);
		
		MenuBar mnuPrincipal = new MenuBar();
		Menu mnuFile = new Menu("Arquivo");
		Menu mnuCadastro = new Menu("Cadastro");
		MenuItem mnuItemSair = new MenuItem("Sair");
		MenuItem mnuItemPaciente = new MenuItem("Pacientes");
		MenuItem mnuItemMedico = new MenuItem("Médicos");
		mnuPrincipal.getMenus().addAll(mnuFile, mnuCadastro);
		mnuFile.getItems().addAll(mnuItemSair);
		mnuCadastro.getItems().addAll(mnuItemPaciente, mnuItemMedico);
		
		mnuItemSair.setOnAction(
				e-> {
					int opc = JOptionPane.showConfirmDialog(null, "Finalizar o programa?", "Finalizar", JOptionPane.YES_NO_OPTION);
					if(opc == JOptionPane.YES_OPTION) {
					Platform.exit();
					System.exit(0);
					}
				});
		
		mnuItemPaciente.setOnAction(
				e-> {
					stage.setTitle("Cadastro de Pacientes");
					panePrincipal.setCenter(pacienteBoundary.render());
				});
		
		mnuItemMedico.setOnAction(
				e-> {
					stage.setTitle("Cadastro de Médicos");
					panePrincipal.setCenter(medicoBoundary.render());
				});
		
		panePrincipal.setTop(mnuPrincipal);
		panePrincipal.setCenter(null);
		
		stage.setScene(scn);
		stage.setTitle("Gestão Clínica Médica");
		stage.show();
	}
	
	public static void main(String[] args) { 
		Application.launch(BoundaryPrincipal.class, args);
	}
}
