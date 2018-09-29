package view;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class PrincipalController {

	@FXML
	RadioButton ckOrientado;
	@FXML
	RadioButton ckValorado;

	@FXML
	TextField txtVetor;
	@FXML
	TextField txtOrigem;
	@FXML
	TextField txtDestino;
	@FXML
	TextField txtValor;

	int w = 0, k = 1;
	int i = 1, j = 1;
	String listaAresta[][] = new String[20][3];
	String matrizAdjacencia[][] = new String[20][20];
	String aux[] = new String[20];
	String listaAdjacencia[][] = new String[10][10];
	String matrizIncidencia[][] = new String[10][20];
	String auxValor[] = new String[20];

	@FXML
	public void adicionaListaAresta() {

		listaAresta[w][0] = txtOrigem.getText();
		listaAresta[w][1] = txtDestino.getText();
		if (ckValorado.isSelected())
			listaAresta[w][2] = txtValor.getText();

		int indiceX = 0;
		int indiceY = 0;
		for (int x = 0; x < aux.length; x++)
			if (txtOrigem.getText().equals(aux[x]))
				indiceX = x;
		for (int y = 0; y < aux.length; y++)
			if (txtDestino.getText().equals(aux[y]))
				indiceY = y;
		if (ckOrientado.isSelected()) {
			matrizAdjacencia[indiceX][indiceY] = "1";
			if (ckValorado.isSelected()) {
				matrizAdjacencia[indiceX][indiceY] = txtValor.getText();
			}

		} else {
			matrizAdjacencia[indiceX][indiceY] = "1";
			matrizAdjacencia[indiceY][indiceX] = "1";
		}
		if (ckValorado.isSelected()) {
			matrizAdjacencia[indiceX][indiceY] = txtValor.getText();
			matrizAdjacencia[indiceY][indiceX] = txtValor.getText();
		}

		k = 1;
		for (int x = 1; x < aux.length; x++) {
			if (txtOrigem.getText().equals(aux[x])) {
				while (!(listaAdjacencia[x - 1][k] == null))
					k++;
				listaAdjacencia[x - 1][k] = txtDestino.getText();
			}
		}
		if (!ckOrientado.isSelected()) {
			k = 1;
			for (int y = 1; y < aux.length; y++) {
				if (txtDestino.getText().equals(aux[y])) {
					while (listaAdjacencia[y - 1][k] != null)
						k++;
					listaAdjacencia[y - 1][k] = txtOrigem.getText();
				}
			}
		}

		auxValor[w + 1] = txtValor.getText();
		matrizIncidencia[0][w + 1] = txtValor.getText();
		int indiceV = 0;

		for (int z = 1; z < auxValor.length; z++) {
			if (txtValor.getText().equals(auxValor[z])) {
				indiceV = z;
			}

		}
		for (int x = 1; x < aux.length; x++) {
			if (txtOrigem.getText().equals(aux[x])) {
				matrizIncidencia[x][indiceV] = "1";

			}
		}
		for (int y = 1; y < aux.length; y++) {
			if (txtDestino.getText().equals(aux[y])) {
				if (ckOrientado.isSelected()) {
					matrizIncidencia[y][indiceV] = "-1";
				} else {
					matrizIncidencia[y][indiceV] = "1";
				}
			}
		}

		w++;
		limpaTelaE();
	}

	@FXML //
	public void conjuntoV() {
		aux[i] = txtVetor.getText();
		i++;
		j++;
		txtVetor.setText("");
	}

	@FXML
	public void finalizar() {
		System.out.println("-----Lista de Arestas-----");
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < 3; y++) {
				if ((y == 2) & (listaAresta[x][y] == null))
					listaAresta[x][y] = "";
				System.out.print(listaAresta[x][y] + " ");
			}
			System.out.println("");
		}

		System.out.println("-----Matriz Adjacência-----");
		for (int o = 0; o < i; o++) {
			matrizIncidencia[o][0] = aux[o];
			listaAdjacencia[o][0] = aux[o + 1] + "->";
			matrizAdjacencia[0][o] = aux[o];
			matrizAdjacencia[o][0] = aux[o];
		}
		matrizAdjacencia[0][0] = " ";
		for (int x = 0; x < i; x++) {
			for (int y = 0; y < j; y++) {
				if (matrizAdjacencia[x][y] == null)
					matrizAdjacencia[x][y] = "0";
				System.out.print(matrizAdjacencia[x][y] + " ");
			}
			System.out.println("");
		}

		System.out.println("-----Lista de adjacência----");
		for (int x = 0; x < i - 1; x++) {
			for (int y = 0; y < listaAdjacencia.length; y++) {
				if (listaAdjacencia[x][y] == null)
					listaAdjacencia[x][y] = "";
				System.out.print(listaAdjacencia[x][y] + " ");
			}
			System.out.println("");
		}

		System.out.println("-----Matriz Incidência----");
		matrizIncidencia[0][0] = " ";
		for (int x = 0; x < i; x++) {
			for (int y = 0; y <= w; y++) {
				if (matrizIncidencia[x][y] == null)
					matrizIncidencia[x][y] = "0";
				System.out.print(matrizIncidencia[x][y] + " ");
			}
			System.out.println("");
		}
	}

	@FXML
	public void limpaTelaE() {
		txtOrigem.setText("");
		txtValor.setText("");
		txtDestino.setText("");

	}

	public void finalera() {
		finalizar();
	}

}
