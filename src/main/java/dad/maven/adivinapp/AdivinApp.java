package dad.maven.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application{
	
	private Button comprobarButton;
	private TextField numeroText;
	private Label instruccionesLabel;
	private Alert acierto;
	private Alert fallo;
	private Alert numeroNoValido;
	int numAdivinar = (int) ((Math.random() * 100) + 1); // Generador del número a Adivinar
	int numIntentos = 0;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		//Texto de la App
		
		numeroText = new TextField();
		numeroText.setPrefColumnCount(5);
		numeroText.setPromptText("Introduzca un número del 1 al 100");
		numeroText.setMaxWidth(150);
		
		//Boton de la App
		
		comprobarButton = new Button();
		comprobarButton.setText("Comprobar");
		comprobarButton.setOnAction(e -> onComprobarButtonAction(e));
		comprobarButton.setDefaultButton(true);
		
		//Label
		
		instruccionesLabel = new Label();
		instruccionesLabel.setTranslateY(-80);
		instruccionesLabel.setText("Introduce un número del 1 al 100");
		
		//Caja que contiene los elementos de la App
		
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(numeroText, comprobarButton, instruccionesLabel);

		Scene interfaz = new Scene(root, 420, 200);

		primaryStage.setScene(interfaz);
		primaryStage.setTitle("AdivinApp");
		primaryStage.show();

	}
	private void onComprobarButtonAction(ActionEvent e) {

		String textDado = numeroText.getText();
		
		if (validate(textDado)) {
			int numDado = Integer.parseInt(numeroText.getText());
			if (numDado > 0 && numDado <= 100) {
				if (numAdivinar == numDado) {
					numIntentos++;
					correcto(numIntentos);
				} else {
					numIntentos++;
					if (numDado < numAdivinar)
						fallo("mayor", numDado);
					else
						fallo("menor", numDado);
				}
			} else {
				numeroNoValido();
			}
		} else {
			numeroNoValido();
		}
	}

	private boolean validate(String text) {
		return text.matches("[0-9]*");
	}
	
	public void numeroNoValido() {
		numeroNoValido = new Alert(AlertType.ERROR);
		numeroNoValido.setTitle("AdivinApp");
		numeroNoValido.setHeaderText("Error");
		numeroNoValido.setContentText("El número introducido no es válido");

		numeroNoValido.showAndWait();
	}
	
	
	public void correcto(int numInt) {
		acierto = new Alert(AlertType.INFORMATION);
		acierto.setTitle("AdivinApp");
		acierto.setHeaderText("¡Has ganado!");
		acierto.setContentText("Sólo has necesitado " + numInt + " intentos" + "\n\nVuelve a jugar y hazlo mejor");

		acierto.showAndWait();
	}
	
	
	public void fallo(String valor, int numIntroducido) {
		fallo = new Alert(AlertType.WARNING);
		fallo.setTitle("AdivinApp");
		fallo.setHeaderText("¡Has fallado!");
		fallo.setContentText("El número a adivinar es " + valor + " que " + numIntroducido);

		fallo.showAndWait();

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
