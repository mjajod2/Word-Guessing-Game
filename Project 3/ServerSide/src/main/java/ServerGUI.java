import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.graalvm.compiler.nodes.virtual.VirtualBoxingNode;

public class ServerGUI extends Application {


	Button ConnectServer,b1;
	Scene connectScene;
	Label enter_Port,intro;
	TextField  Port;
	ThreadedServer serverConnection;

	Popup incorrect = new Popup();
	ListView<String> listItems;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	public void start(Stage primaryStage) throws Exception {

		listItems = new ListView<String>();
		enter_Port = new Label("Enter Port Number ");
		enter_Port.setFont(new Font("Arial", 30));
		enter_Port.setStyle("-fx-background-color : white");

		Port = new TextField();
		Port.setPrefSize(100, 40);

		intro = new Label("Guessing Game Sever");
		intro.setStyle("fx-background-color : red");
		intro.setFont(new Font("Arial", 25));

		ConnectServer = new Button("Connect");
		ConnectServer.setFont(new Font("Arial", 15));
		double r = 40;
		ConnectServer.setShape(new Circle(r));
		ConnectServer.setMinSize(2*r, 2*r);
		ConnectServer.setMaxSize(2*r, 2*r);


		Button toff = new Button("Quit");
		toff.setShape(new Circle(r));
		toff.setMinSize(2*r, 2*r);
		toff.setMaxSize(2*r, 2*r);
		toff.setOnAction(e->{
			listItems.getItems().clear();
			Platform.exit();
			System.exit(0);
		});

		ConnectServer.setOnAction(e-> {
			if (!Port.getText().equals("5555")) {
				Label forPop = new Label("Wrong IP Address, please try again.");
				forPop.setFont(Font.font("Arial", 30));
				forPop.setStyle("-fx-background-color : white");
				incorrect.getContent().add(forPop);
				incorrect.show(primaryStage);

			} else {
				incorrect.hide();
				VBox paneLay = new VBox(30, intro, listItems, toff);
				paneLay.setPadding(new Insets(70));
				paneLay.setStyle("-fx-background-color: coral");
				paneLay.setAlignment(Pos.CENTER);
				Scene new1 = new Scene(paneLay, 500, 500);
				primaryStage.setScene(new1);
			}
			serverConnection = new ThreadedServer(data -> {
				Platform.runLater(() -> {
					listItems.getItems().add(data.toString());
				});
			},Port.getText());
		});

		primaryStage.setOnCloseRequest(t -> {
			Platform.exit();
			System.exit(0);
		});

		HBox hbox1 = new HBox(50, enter_Port, Port);
		hbox1.setAlignment(Pos.CENTER);

		Label Welcome = new Label("Welcome to the server for Guessing Game");
		Welcome.setFont(new Font("Arial", 30));
		Welcome.setStyle("-fx-background-color : white");
		VBox lay1 = new VBox(60, Welcome, hbox1, ConnectServer);
		lay1.setAlignment(Pos.CENTER);
		lay1.setStyle("-fx-background-color : black");


		connectScene = new Scene(lay1,700,700);
		primaryStage.setScene(connectScene);
		primaryStage.setTitle("Guessing Game Server");
		primaryStage.show();
	}
}