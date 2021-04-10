
package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import login.Login;
import root.RootView;
import root.traverse.branch.Branch;

/**
 * 
 * @author carlhidestal
 *
 */
public class MainApplication extends Application {

	private static final String TITLE = "CRM TREE";
	private static final String EXEC_MESSAGE = "1. Launching application.Main \t - \t start()";
	protected double yOffset;
	protected double xOffset;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/*//------ Teststage ------//
		
		Stage testStage = new Stage();
		AnchorPane testRoot = new AnchorPane();
		Scene testScene = new Scene(testRoot, 450,350);
//		Scene testScene = new Scene(testRoot);

		testStage.setScene(testScene);
		testStage.setTitle("Test Stage");
		testStage.show();
		Login login = new Login();
		login.loadGUI(testRoot);
		
		//------ End Teststage ------//*/

		System.out.println(EXEC_MESSAGE);

		AnchorPane root = new AnchorPane();
		
		makeWindowDraggable(root, primaryStage);
		buildTestTreeStructure();
		Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(getClass().getResource("css/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle(TITLE);
		primaryStage.show();
		RootView rv = new RootView();

		rv.loadGUI(root);

	}
	
	public void buildTestTreeStructure() {

		Branch root = new Branch("Root");
		TraverseTree.setRoot(root);
		Branch node1 = new Branch("node1");
		Branch node2 = new Branch("node2");
		Branch node3 = new Branch("node3");
		Branch node4 = new Branch("node4");
		Branch node5 = new Branch("node5");
		
		root.addChild(node1);
		node1.addChild(node2);
		node2.addChild(node3);
		node2.addChild(node4);
		node3.addChild(node5);

		root.setDescription("Testing description");

		
		
	}

	private void makeWindowDraggable(Pane root, Stage primaryStage) {
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = primaryStage.getX() - event.getScreenX();
				yOffset = primaryStage.getY() - event.getScreenY();
			}
		});

		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				primaryStage.setX(event.getScreenX() + xOffset);
				primaryStage.setY(event.getScreenY() + yOffset);
			}
		});

		root.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				root.setFocusTraversable(true);
			}

		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}