package dad.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {
	
	private Label instruccionLabel;
	private Button comprobarButton;
	private TextField numeroText;
	private VBox root;
	private Alert correcto;
	private Alert fallo;
	private Alert numInvalido;
	int numAdivinar= (int) ((Math.random()*100)+1);
	int numIntentos=0;

	
	public void start(Stage primaryStage) throws Exception {
		
		//Creamos cuadro de texto
		 numeroText = new TextField();
		 numeroText.setText("0");
		 numeroText.setAlignment(Pos.CENTER);
		 numeroText.setTranslateY(20);
		 numeroText.setMaxWidth(150);
		 
		//Creamos el label 
		 instruccionLabel = new Label();
		 instruccionLabel.setTranslateY(-40);
		 instruccionLabel.setText("Introduce un numero del 1 al 100");
		 
		 //Boton para comprobar
		 comprobarButton = new Button();
		 comprobarButton.setText("Comprobar");
		 comprobarButton.setTooltip(new Tooltip("Cuando me pulses compruebo el numero"));
		 comprobarButton.setTranslateY(-20);
		 comprobarButton.setOnAction(e -> onComprobarButtonAction(e));
		 comprobarButton.setDefaultButton(true);
		 
		 //Panel
		 root = new VBox();
		 root.setSpacing(15);
		 root.setAlignment(Pos.CENTER);
		 root.getChildren().addAll(numeroText, instruccionLabel, comprobarButton);
		 
		 //Escena
		 Scene scene = new Scene(root, 320, 200);
		 primaryStage.setTitle("AdivinApp");
		 primaryStage.setScene(scene);
		 primaryStage.setResizable(false);
		 primaryStage.show();
		 
		
	}

	private void onComprobarButtonAction(ActionEvent e) {

		String numText = numeroText.getText();
		
		if(validate(numText)) {
			int num = Integer.parseInt(numText);
			if(num > 0 && num <= 100 ) {
				if (numAdivinar == num) {
					numIntentos++;
					correcto(numIntentos);
				}
				else {
					numIntentos++;
					if(num < numAdivinar) {
						fallo("mayor", num);
					}
					else
						fallo("menor", num);
					
				}
			}
		}
		else
			numInvalido();
	}
	
	//Metodo para validar el numero que nos dan en string
	private boolean validate(String num) {
		
		return num.matches("[0-9]*");
	}

	//Alerta si el caracter introducido no es valido 
	private void numInvalido() {

		numInvalido = new Alert(AlertType.ERROR);
		numInvalido.setTitle("AdivinApp");
		numInvalido.setHeaderText("Error");
		numInvalido.setContentText("El número introducido no es válido.");

		numInvalido.showAndWait();
	}

	//Alerta si fallamos la comprobacion
	private void fallo(String rango , int num) {
		
		fallo = new Alert(AlertType.WARNING);
		fallo.setTitle("AdivinApp");
		fallo.setHeaderText("¡Has fallado!");
		fallo.setContentText("El número a adivinar es " + rango + " que " + num + ".");

		fallo.showAndWait();

		
	}

	//Alerta si acertamos la comprobacion
	private void correcto(int numIntento) {
		
		correcto = new Alert(AlertType.INFORMATION);
		correcto.setTitle("AdivinApp");
		correcto.setHeaderText("¡Has ganado!");
		correcto.setContentText("Sólo has necesitado " + numIntento + " intentos." + "\n\nVuelve a jugar y hazlo mejor.");

		correcto.showAndWait();
	}



	public static void main(String[] args) {
		
		launch(args);
	}

}
