package application;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class Main extends Application {
	int[] array;
	int[] myIndexArray;
	int[] hisIndexArray;
	int[] himIndexCoin;

	@Override
	public void start(Stage primaryStage) {

		GridPane root = new GridPane();
		root.setPadding(new Insets(20));
		root.setAlignment(Pos.CENTER_LEFT);

		Label NumCoins = new Label("Enter the number of Coins (Even Only): ");
		NumCoins.setFont(new Font("Cambria", 20));
		TextField txtNumCoins = new TextField("");
		txtNumCoins.setPromptText("even number.");
		txtNumCoins.setTranslateX(-250);
		txtNumCoins.setMaxWidth(150);

		Label labelCoins = new Label("Enter the Coins:");
		labelCoins.setFont(new Font("Cambria", 20));
		TextField txtCoins = new TextField("");
		txtCoins.setPromptText("1 5 2 7");
		txtCoins.setTranslateX(-360);
		txtCoins.setPrefWidth(300);

		Label labelMaxResult = new Label("Maximum Result:");
		labelMaxResult.setFont(new Font("Cambria", 20));
		TextField txtMaxResult = new TextField("");
		txtMaxResult.setTranslateX(-360);
		txtMaxResult.setMaxWidth(150);

		Label labelCoinsResultP1 = new Label("Coins of Player 1:");
		labelCoinsResultP1.setFont(new Font("Cambria", 20));
		TextField txtCoinsResultP1 = new TextField("");
		txtCoinsResultP1.setTranslateX(-430);
		txtCoinsResultP1.setMaxWidth(150);

		Label labelCoinsResultP2 = new Label("Coins of Player 2:");
		labelCoinsResultP2.setFont(new Font("Cambria", 20));
		TextField txtCoinsResultP2 = new TextField("");
		txtCoinsResultP2.setTranslateX(-430);
		txtCoinsResultP2.setMaxWidth(150);

		Label DPlabel = new Label("Dynamic Programming Table:");
		DPlabel.setFont(new Font("Cambria", 20));
		DPlabel.setTranslateX(400);

		Label lablenote = new Label("Process:");
		lablenote.setFont(new Font("Cambria", 20));
		TextField txtnote = new TextField("");
		txtnote.setPromptText("Process:");
		txtnote.setTranslateX(-360);
		txtnote.setMaxWidth(150);

		TextArea txtArea = new TextArea();
		txtArea.setPrefSize(600, 400);
		txtArea.setTranslateX(170);
		txtArea.setTranslateY(10);

		Image image101 = new Image("https://img.icons8.com/cute-clipart/512/test-passed.png");
		ImageView imageView101 = new ImageView();
		imageView101.setImage(image101);
		Button Result = new Button("Result", imageView101);
		Result.setStyle("-fx-background-color:transparent;");
		imageView101.setFitHeight(40);
		imageView101.setFitWidth(40);
		Label lablenote2 = new Label("Relationship : F(i,j)=max(Vi+min(max(i+2,j),max(i+1,j-1)),(Vj+min(max(i+1,j-1),max(i,j-2))))");
		lablenote2.setFont(new Font("Cambria", 20));

		Image image10 = new Image("https://img.icons8.com/ultraviolet/512/animation-rig.png");
		ImageView imageView10 = new ImageView();
		imageView10.setImage(image10);
		Button Animation = new Button("Animation", imageView10);
		Animation.setStyle("-fx-background-color:transparent;");
		imageView10.setFitHeight(40);
		imageView10.setFitWidth(40);

		Image image102 = new Image("https://img.icons8.com/clouds/512/broom.png");
		ImageView imageView102 = new ImageView();
		imageView102.setImage(image102);
		Button Clear = new Button("Clear", imageView102);
		Clear.setStyle("-fx-background-color:transparent;");
		imageView102.setFitHeight(40);
		imageView102.setFitWidth(40);

		DropShadow shadow5 = new DropShadow();

		Animation.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eh) -> {
			Animation.setEffect(shadow5);
		});

		Animation.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ej) -> {
			Animation.setEffect(null);
		});
		
		Clear.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eh) -> {
			Clear.setEffect(shadow5);
		});

		Clear.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ej) -> {
			Clear.setEffect(null);
		});
		
		Result.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eh) -> {
			Result.setEffect(shadow5);
		});

		Result.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ej) -> {
			Result.setEffect(null);
		});

		Result.setOnAction(e -> {
			try {
				// Get the number of coins
				int n = Integer.parseInt(txtNumCoins.getText());
				// Check if the number of coins is odd, execute the condition sentence, and if
				// it is even, execute the else
				if (n % 2 != 0) {
					txtnote.setText("pleas enter even number");
				} else {

					array = new int[n];

					// get coins and store them in String
					String h = txtCoins.getText();

					// Separate each coin and convert it to integer and store it in array
					String[] st = h.split(" ");
					for (int i = 0; i < array.length; i++) {
						array[i] = Integer.parseInt(st[i] + "");
					}
					txtnote.setText("That is true");

					int[][] array2 = new int[n][n];
					// Here he creates DP table and store it in array2[][]
					for (int i = n - 1; i >= 0; i--) { // row
						for (int j = i; j < n; j++) { // colume
							if (i == j) {
								array2[i][j] = array[i];

							} else {

								int op1;
								int op2;

								// If i or j are outside the bounds of the Index, then run the following
								// condition
								if ((i + 1 | i + 2) > n || (j - 1) < 0) {
									op1 = array[i];
								} else {
									op1 = array[i] + Math.min(array2[i + 2][j], array2[i + 1][j - 1]);
								}
								if ((i + 1) > n || (j - 1 | j - 2) < 0) {
									op2 = array[j];
								} else {
									op2 = array[j] + Math.min(array2[i + 1][j - 1], array2[i][j - 2]);
								}
								array2[i][j] = Math.max(op1, op2);
							}
						}
					}

					// put max value
					txtMaxResult.setText(array2[0][n - 1] + "");

					myIndexArray = new int[n / 2];
					hisIndexArray = new int[n / 2];

					int i = 0, myIndex = 0, hisIndex = 0, j = array.length - 1;

					while (i < j) {
						boolean ans = mySeqCoin(myIndexArray, array, array2, myIndex, i, j);
						// index of j > index of i
						if (ans == true) {
							j--;
							myIndex++;

						} else {
							i++;
							myIndex++;
						}

						int letter = hisSeqCoin(hisIndexArray, array, array2, hisIndex, i, j);

						// index of j < index of i
						if (letter == 1) {
							i++;
							hisIndex++;
						} else if (letter == 2) {
							j--;
							hisIndex++;
						} else {
							break;

						}
					}
					// print coins P1
					txtCoinsResultP1.setText(printCons(myIndexArray, array));
					// print coins P2
					txtCoinsResultP2.setText(printCons(hisIndexArray, array));

					// print table in TextArea
					String stt = printTable(array2);
					txtArea.setText(stt);

				}
			} catch (Exception x) {
				txtnote.setText("enter all items correct");

			}
		});

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER_RIGHT);
		Animation.setOnAction(e2 -> {

			TextField[] ani = new TextField[array.length];

			for (int f = 0; f < array.length; f++) {
				ani[f] = new TextField();
				ani[f].setPrefWidth(40);
				ani[f].setPrefHeight(40);
				hbox.getChildren().add(ani[f]);
				ani[f].setText(Integer.toString(array[f]));
			}

			// to play a group of transitions one after another.
			SequentialTransition sT = new SequentialTransition();
			for (int i = 0; i < myIndexArray.length; i++) {
				// Used to run a group of moves in the order that is specified.
				TranslateTransition tt = new TranslateTransition(Duration.seconds(1.5));
				tt.setNode(ani[myIndexArray[i]]);
				tt.setToY(-50);
				// sets the number of times that the transition should play.
				tt.setCycleCount(1);
				sT.getChildren().add(tt);

				TranslateTransition translateHim = new TranslateTransition(Duration.seconds(1.5));
				translateHim.setNode(ani[hisIndexArray[i]]);
				translateHim.setToY(50);
				translateHim.setCycleCount(1);
				sT.getChildren().add(translateHim);
			}
			sT.play();
		});

		root.addRow(0, NumCoins, txtNumCoins);
		root.addRow(2, labelCoins, txtCoins);
		root.addRow(3, lablenote2);
		root.addRow(17, lablenote, txtnote);
		root.addRow(6, labelMaxResult, txtMaxResult);
		root.addRow(9, txtArea);
		root.addRow(14, labelCoinsResultP1, txtCoinsResultP1);
		root.addRow(15, labelCoinsResultP2, txtCoinsResultP2);
		root.addRow(8, DPlabel);
		root.add(Animation, 1, 15);
		root.addRow(15, Clear);
		root.add(Result, 1, 4);
		root.setVgap(10);
		root.setHgap(10);
		root.add(hbox, 4, 8);

		Clear.setOnAction(e -> {
			txtNumCoins.clear();
			txtCoins.clear();
			txtnote.clear();
			txtMaxResult.clear();
			txtCoinsResultP1.clear();
			txtCoinsResultP2.clear();
			txtArea.clear();
			hbox.getChildren().clear();
		});

		Scene s = new Scene(root, Color.RED);

		primaryStage.setMaximized(true);
		primaryStage.setScene(s);
		primaryStage.show();
		primaryStage.setTitle("Optimal Strategy for a Game");

	}

	public static String printCons(int[] index, int[] array1) {
		String st = "";
		for (int i = 0; i < index.length; i++) {
			st = st + "[ " + array1[index[i]] + " ],";
		}
		return st;
	}

	public static String printTable(int[][] game) {
		String st = "";
		for (int i = 0; i < game.length; i++) {
			for (int j = 0; j < game.length; j++) {
				st = st + " [" + game[i][j] + "] ";
			}
			st = st + "\n";
		}

		return st;
	}

	private static boolean mySeqCoin(int[] myIndexArray, int[] Array, int[][] Array2, int index, int i, int j) {

		int[] ans = new int[2];

		// if there is two coin
		if (i + 1 == j) {

			int x = Array[i];
			int y = Array[j];
			if (x > y) {
				myIndexArray[index] = i;
				return false;
			} else if (x < y) {
				myIndexArray[index] = j;
				return true;
			} else {

				myIndexArray[index] = i;
				ans[0] = x;
				ans[1] = y;
				return false;

			}

		}

		int x = Array[i] + Math.min(Array2[i + 2][j], Array2[i + 1][j - 1]);
		int y = Array[j] + Math.min(Array2[i + 1][j - 1], Array2[i][j - 2]);

		if (x > y) {
			// to store my index of coins
			myIndexArray[index] = i;
			return false;
		} else if (x < y) {
			myIndexArray[index] = j;
			return true;
		} else {
			if (Array[i] == Array[j]) {
				myIndexArray[index] = i;
				return false;
			} else if (Array[i] > Array[j]) {
				myIndexArray[index] = i;
				return false;
			} else {
				myIndexArray[index] = j;
				return true;
			}
		}
	}

	private static int hisSeqCoin(int[] hisIndexCoin, int[] originalArray, int[][] matrix, int index, int i, int j) {
		if (i == j) {
			hisIndexCoin[index] = i;
			return 0;
		}
		// if there 3 coins
		if (i + 2 == j || j - 2 == i) {
			if (originalArray[i] > originalArray[j]) {

				hisIndexCoin[index] = i;
				return 1;

			} else if (originalArray[i] < originalArray[j]) {

				hisIndexCoin[index] = j;
				return 2;

			} else {

				hisIndexCoin[index] = i;
				return 1;

			}
		}

		int x = originalArray[i] + Math.min(matrix[i + 2][j], matrix[i + 1][j - 1]);
		int y = originalArray[j] + Math.min(matrix[i + 1][j - 1], matrix[i][j - 2]);

		if (x > y) {

			hisIndexCoin[index] = i;
			return 1;

		} else if (x < y) {

			hisIndexCoin[index] = j;
			return 2;

		} else {
			if (originalArray[i] == originalArray[j]) {
				if (originalArray[i + 1] > originalArray[j - 1]) {

					hisIndexCoin[index] = j;
					return 2;

				} else if (originalArray[i + 1] < originalArray[j - 1]) {

					hisIndexCoin[index] = i;
					return 1;

				} else if (originalArray[i + 1] == originalArray[j - 1]) {
					hisIndexCoin[index] = i;
					return 1;

				}
			}

			if (originalArray[i] > originalArray[j]) {

				hisIndexCoin[index] = i;
				return 1;

			} else {

				hisIndexCoin[index] = j;
				return 2;

			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}