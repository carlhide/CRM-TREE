package root;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class RootController {

	@FXML
	private AnchorPane canvas;
	@FXML
	private Button sett_btn, trav_btn, dash_btn, chat_btn;

	// ----------------FXML----------------//

	private ArrayList<Button> menuButtons;

	private RootView rv;

	public RootController() throws IOException {
		System.out.println("3. Launching RootController 	 - 	 RootController()");
		menuButtons = new ArrayList<Button>();
	}

	@FXML
	public void initialize() throws IOException {
		System.out.println("4. Launching RootController 	 - 	 initialize()");

		addMenuButtonsToArrayAndSetOnAction();

	}

	public Button getSett_btn() {
		return sett_btn;
	}

	public void setSett_btn(Button sett_btn) {
		this.sett_btn = sett_btn;
	}

	public Button getTrav_btn() {
		return trav_btn;
	}

	public void setTrav_btn(Button trav_btn) {
		this.trav_btn = trav_btn;
	}

	public Button getDash_btn() {
		return dash_btn;
	}

	public void setDash_btn(Button dash_btn) {
		this.dash_btn = dash_btn;
	}

	public void setCanvas(AnchorPane canvas) {
		this.canvas = canvas;
	}

	public AnchorPane getCanvas() {
		System.out.println("5. Launching RootController 	 - 	 initialize()");

		return canvas;
	}

	public void setRootView(RootView rv) {
		this.rv = rv;
	}

	private void addMenuButtonsToArrayAndSetOnAction() {
		menuButtons.add(sett_btn);
		menuButtons.add(dash_btn);
		menuButtons.add(trav_btn);
		menuButtons.add(chat_btn);

		for (Button b : menuButtons) {

			b.setOnAction(e -> {
				try {
					rv.menuHandler(e.getSource().toString());
					b.getStyleClass().remove(b.getStyleClass().size() - 1);
					b.getStyleClass().add("crm-menubutton-selected");
					for (Button b2 : menuButtons) {
						if (!b2.equals(b)) {
							b2.getStyleClass().remove(b2.getStyleClass().size() - 1);
							b2.getStyleClass().add("crm-menubutton");
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});

		}
	}

	private void draggable() {

	}
}
