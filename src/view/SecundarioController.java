package view;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Aresta;

public class SecundarioController {

	@FXML
	RadioButton ckOrientado;
	
	@FXML
	TextField txtVetor;
	@FXML
	TextField txtOrigem;
	@FXML
	TextField txtDestino;
	@FXML
	TextField txtValor;

	ArrayList<String> verticeLista = new ArrayList<String>();
	ArrayList<Aresta> arestaLista = new ArrayList<Aresta>();
	
	@FXML
	public void adicionaAresta() {

		Aresta aresta = new Aresta();
		aresta.setOrigem(txtOrigem.getText());
		aresta.setDestino(txtDestino.getText());
		aresta.setValor(0);
		arestaLista.add(aresta);
		limpaTelaE();

	}

	@FXML
	public void adicionaVertice() {

		verticeLista.add(txtVetor.getText());
		txtVetor.setText("");

	}

	@FXML
	public void limpaTelaE() {

		txtOrigem.setText("");
		txtValor.setText("");
		txtDestino.setText("");

	}

}
