import javafx.application.Application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;

public class ClientGUI extends Application {

	Scene introScene, loginScene, categoryScene, animalsScene, rulesScene, afterWinOne, winnerScene;
	TextField portInput;
	TextField input;
	boolean st = false;
	Button submit = new Button("Go"), c1 = new Button("Animals"), c2 = new Button("U.S States"),
			c3 = new Button("Food Cuisines"), go_back = new Button("Go Back");
	HBox forLog;
	Label chooseCat;
	Image rules = new Image("rules.png");
	ImageView r1 = new ImageView(rules);
	Image pic = new Image("ipho.jpg");
	ImageView iView = new ImageView(pic);
	Image pup = new Image("pup.jpg");
	ImageView p1 = new ImageView(pup);
	Image usa = new Image("usa.jpg");
	ImageView u1 = new ImageView(usa);
	Image food = new Image("food.jpg");
	ImageView f1 = new ImageView(food);
	String portNum = "127.0.0.1";
	Popup incorrect = new Popup();
	Button check = new Button("Check");
	Label category;
	int numGuesses = 6;
	ArrayList<String> listItems = new ArrayList<>();
	int numWins = 0;

	String firstOne = "Animals", secondOne = "U.S States", thirdOne = "Food Cuisines";
	String[] catArray = new String[3];

	ThreadedClient clientConnection;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		primaryStage.setTitle("Guessing Game");

		iView.setFitHeight(350);
		iView.setFitWidth(700);

		Label intro = new Label("Welcome to the word guessing game!");
		intro.setFont(Font.font("Arial", 35));
		intro.setStyle("-fx-background-color : white");

		Button b1 = new Button("Start");
		b1.setStyle("-fx-background-color : #5FFF3C");
		b1.setFont(Font.font("Arial", 30));
		Button b2 = new Button("How To Play");
		b2.setStyle("-fx-background-color : #F8F82F");
		b2.setFont(Font.font("Arial", 30));
		Button b3 = new Button("Exit");
		b3.setStyle("-fx-background-color : #FD2929");
		b3.setFont(Font.font("Arial", 30));

		HBox hb = new HBox(50, b1, b2, b3);
		hb.setAlignment(Pos.CENTER);

		VBox i = new VBox(70, iView, intro, hb);
		i.setAlignment(Pos.TOP_CENTER);

		BorderPane scene1 = new BorderPane(i);
		BorderPane.setAlignment(i, Pos.CENTER);
		scene1.setStyle("-fx-background-color : cyan");

		// set action for start button

		EventHandler<ActionEvent> forB1 = actionEvent -> {
			portInput = new TextField();
			Label request  = new Label("Input IP Address: ");
			request.setFont(Font.font("Arial", 35));
			request.setStyle("-fx-background-color : white");
			submit.setFont(Font.font("Arial", 20));
			portInput.setMinSize(40, 40);
			forLog = new HBox(50, request, portInput, submit);
			Image numbers = new Image("random.jpg");
			ImageView n1 = new ImageView(numbers);
			n1.setFitWidth(700);
			n1.setFitHeight(350);
			VBox fi = new VBox(160, forLog, n1);
			fi.setAlignment(Pos.CENTER);
			fi.setStyle("-fx-background-color : black");
			loginScene = new Scene(fi, 700, 700);
			primaryStage.setScene(loginScene);
		};

		b1.setOnAction(forB1);

		// set action for how to play button

		EventHandler<ActionEvent> how2play = actionEvent -> {
			r1.setFitHeight(500);
			r1.setFitWidth(700);
			go_back.setFont(Font.font("Arial", 20));
			VBox totally = new VBox(50, r1, go_back);
			totally.setAlignment(Pos.TOP_CENTER);
			rulesScene = new Scene(totally, 700, 700);
			primaryStage.setScene(rulesScene);
		};

		b2.setOnAction(how2play);

		// set action for go back button

		EventHandler<ActionEvent> goback = actionEvent -> primaryStage.setScene(introScene);

		go_back.setOnAction(goback);
		// set action for exit button

		b3.setOnAction(e->primaryStage.close());

		EventHandler<ActionEvent> aGame = actionEvent-> {
			clientConnection.send(c1.getText());
			category = new Label("You have chosen the " + firstOne + " category");
			catArray[0] = firstOne;
			System.out.println(catArray[0]);
			category.setFont(Font.font("Arial", 35));
			category.setStyle("-fx-background-color : white");
			Label tmp = new Label("Word from server has been received!");
			tmp.setFont(Font.font("Arial", 35));
			tmp.setStyle("-fx-background-color : white");
			Label ask = new Label("Please enter a character");
			ask.setFont(Font.font("Arial", 35));
			ask.setStyle("-fx-background-color : white");
			input = new TextField();
			input.setMinSize(40, 40);
			check.setFont(Font.font("Arial", 20));
			VBox hold1 = new VBox(50, category, tmp, ask);
			hold1.setAlignment(Pos.CENTER);
			HBox hold2 = new HBox(50, input, check);
			hold2.setAlignment(Pos.CENTER);
			VBox finalHold = new VBox(60, hold1, hold2);
			finalHold.setAlignment(Pos.CENTER);
			animalsScene = new Scene(finalHold, 700, 700);
			primaryStage.setScene(animalsScene);
		};

		EventHandler<ActionEvent> sGame = actionEvent-> {
			clientConnection.send(c2.getText());
			category = new Label("You have chosen the " + c2.getText() + " category");
			catArray[0] = c2.getText();
			category.setFont(Font.font("Arial", 35));
			category.setStyle("-fx-background-color : white");
			Label tmp = new Label("Word from server has been received!");
			tmp.setFont(Font.font("Arial", 35));
			tmp.setStyle("-fx-background-color : white");
			Label ask = new Label("Please enter a character");
			ask.setFont(Font.font("Arial", 35));
			ask.setStyle("-fx-background-color : white");
			input = new TextField();
			input.setMinSize(40, 40);
			check.setFont(Font.font("Arial", 20));
			VBox hold1 = new VBox(50, category, tmp, ask);
			hold1.setAlignment(Pos.CENTER);
			HBox hold2 = new HBox(50, input, check);
			hold2.setAlignment(Pos.CENTER);
			VBox finalHold = new VBox(60, hold1, hold2);
			finalHold.setAlignment(Pos.CENTER);
			animalsScene = new Scene(finalHold, 700, 700);
			primaryStage.setScene(animalsScene);
		};

		EventHandler<ActionEvent> cGame = actionEvent-> {
			clientConnection.send(c3.getText());
			category = new Label("You have chosen the " + c3.getText() + " category");
			catArray[0] = c3.getText();
			category.setFont(Font.font("Arial", 35));
			category.setStyle("-fx-background-color : white");
			Label tmp = new Label("Word from server has been received!");
			tmp.setFont(Font.font("Arial", 35));
			tmp.setStyle("-fx-background-color : white");
			Label ask = new Label("Please enter a character");
			ask.setFont(Font.font("Arial", 35));
			ask.setStyle("-fx-background-color : white");
			input = new TextField();
			input.setMinSize(40, 40);
			check.setFont(Font.font("Arial", 20));
			VBox hold1 = new VBox(50, category, tmp, ask);
			hold1.setAlignment(Pos.CENTER);
			HBox hold2 = new HBox(50, input, check);
			hold2.setAlignment(Pos.CENTER);
			VBox finalHold = new VBox(60, hold1, hold2);
			finalHold.setAlignment(Pos.CENTER);
			animalsScene = new Scene(finalHold, 700, 700);
			primaryStage.setScene(animalsScene);
		};

		// set action for submit button

		EventHandler<ActionEvent> forSubmit = actionEvent -> {
			chooseCat = new Label("Choose a category: ");
			chooseCat.setFont(Font.font("Arial", 35));
			chooseCat.setStyle("-fx-background-color : white");
			HBox layout = new HBox(140, c1, c2, c3);
			layout.setAlignment(Pos.CENTER);
			p1.setFitHeight(150);
			p1.setFitWidth(150);
			u1.setFitHeight(150);
			u1.setFitWidth(150);
			f1.setFitHeight(150);
			f1.setFitWidth(150);
			HBox images = new HBox(100, p1, u1, f1);
			images.setAlignment(Pos.CENTER);
			VBox tmp = new VBox(70, chooseCat, layout, images);
			c1.setFont(Font.font("Arial", 20));
			c2.setFont(Font.font("Arial", 20));
			c3.setFont(Font.font("Arial", 20));
			tmp.setAlignment(Pos.CENTER);
			categoryScene = new Scene(tmp, 700, 700);
			if (!portInput.getText().equals(portNum)) {
				Label forPop = new Label("Wrong IP Address, please try again.");
				forPop.setFont(Font.font("Arial", 30));
				forPop.setStyle("-fx-background-color : white");
				incorrect.getContent().add(forPop);
				incorrect.show(primaryStage);
			} else {
				incorrect.hide();
				primaryStage.setScene(categoryScene);

				clientConnection = new ThreadedClient(data -> {
					Platform.runLater(() -> {
						listItems.add(data.toString());
						if (listItems.get(listItems.size() - 1).equals("true")) {
							System.out.println(numWins);
							numWins++;
								// scene after win 1
								switch (catArray[0]) {
									case "Animals":
										c1.setDisable(true);
										break;
									case "U.S States":
										c2.setDisable(true);
										break;
									case "Food Cuisines":
										c3.setDisable(true);
										break;
								}
								HBox nbb = new HBox(20, c1, c2, c3);
								chooseCat = new Label("Choose a category: ");
								chooseCat.setFont(Font.font("Arial", 35));
								chooseCat.setStyle("-fx-background-color : white");
								HBox layout2 = new HBox(140, c1, c2, c3);
								layout2.setAlignment(Pos.CENTER);
								p1.setFitHeight(150);
								p1.setFitWidth(150);
								u1.setFitHeight(150);
								u1.setFitWidth(150);
								f1.setFitHeight(150);
								f1.setFitWidth(150);
								HBox images2 = new HBox(100, p1, u1, f1);
								images2.setAlignment(Pos.CENTER);
								VBox tmp2 = new VBox(70, chooseCat, layout2, images2);
								c1.setFont(Font.font("Arial", 20));
								c2.setFont(Font.font("Arial", 20));
								c3.setFont(Font.font("Arial", 20));
								tmp2.setAlignment(Pos.CENTER);
								afterWinOne = new Scene(tmp2, 700, 700);
								primaryStage.setScene(afterWinOne);
								if (numWins == 1) {
									Label winLabel = new Label("Congratulations! You Won!");
									winLabel.setFont(Font.font("Arial", 35));
									winLabel.setStyle("-fx-background-color : white");
									Button pa = new Button("Play again");
									pa.setStyle("-fx-background-color : #5FFF3C");
									pa.setFont(Font.font("Arial", 30));
									Button b3e = new Button("Exit");
									b3e.setStyle("-fx-background-color : #FD2929");
									b3e.setFont(Font.font("Arial", 30));
									HBox winL = new HBox(100, pa, b3e);
									winL.setAlignment(Pos.CENTER);
									VBox winLL = new VBox(70,winLabel, winL);
									winLL.setAlignment(Pos.CENTER);
									winnerScene = new Scene(winLL, 700, 700);
									primaryStage.setScene(winnerScene);
									EventHandler<ActionEvent> pAgain = actionEvent1 -> {
										numWins = 0;
										c1.setDisable(false);
										c2.setDisable(false);
										c3.setDisable(false);
										primaryStage.setScene(introScene);
									};
									pa.setOnAction(pAgain);
									EventHandler<ActionEvent> exit2 = actionEvent1 -> {
										primaryStage.close();
									};
									b3e.setOnAction(exit2);
								}
						}
					});
				}, portInput.getText()
				);

			}

			clientConnection.start();

		};

		submit.setOnAction(forSubmit);

		// set action for animals button
		c1.setOnAction(aGame);

		c2.setOnAction(sGame);

		c3.setOnAction(cGame);

		EventHandler<ActionEvent> toSend = actionEvent -> {
			clientConnection.send(input.getText());
			input.clear();
		};

		check.setOnAction(toSend);

		primaryStage.setOnCloseRequest(t -> {
			Platform.exit();
			System.exit(0);
		});


		introScene = new Scene(scene1, 700,700);
		primaryStage.setScene(introScene);
		primaryStage.show();
	}

}