package login;

import java.io.IOException;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import root.traverse.branch.Branch;

public class LoginController extends Controller<Login>{

	private Login view;

	@FXML
	private JFXTextField username_txtField;
	@FXML
	private JFXPasswordField password_pwField;
	

	public LoginController() throws IOException {
		
	}
	
	public void setView(Login view) {
		this.view = view;
	

	}

	public Login getView() {
		return view;
	}
	

	@FXML
	public void initialize() {
		System.out.println("Initializing LoginController");
	}

	@FXML
	public void exit(ActionEvent e) {
		System.exit(0);
	}

}
