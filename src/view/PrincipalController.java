package view;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Aresta;

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

	ArrayList<String> verticeLista = new ArrayList<String>();
	ArrayList<Aresta> arestaLista = new ArrayList<Aresta>();

	String vertice[] = new String[20];
	String listaAdjacencia[][] = new String[10][10];
	String matrizIncidencia[][] = new String[10][20];
	String auxValor[] = new String[20];

	public void calculaMatrizAdjacencia() {

		String matrizAdjacencia[][] = new String[verticeLista.size() + 1][verticeLista.size() + 1];
		int indiceX = 1;
		int indiceY = 1;
		for (Aresta a : arestaLista) {
			for (int x = 0; x < verticeLista.size(); x++)
				if (a.getOrigem().equals(verticeLista.get(x)))
					indiceX = x+1;
			for (int y = 0; y < verticeLista.size(); y++)
				if (a.getDestino().equals(verticeLista.get(y)))
					indiceY = y+1;
			if (!ckOrientado.isSelected()) {
				matrizAdjacencia[indiceX][indiceY] = String.valueOf(matrizAdjacencia[indiceX][indiceY]+1);
				matrizAdjacencia[indiceY][indiceX] = String.valueOf(matrizAdjacencia[indiceY][indiceX]+1);
				if (ckValorado.isSelected()) {
					matrizAdjacencia[indiceX][indiceY] = String.valueOf(a.getValor());
					matrizAdjacencia[indiceY][indiceX] = String.valueOf(a.getValor());
				}
			} else {
				matrizAdjacencia[indiceX][indiceY] = String.valueOf(matrizAdjacencia[indiceX][indiceY]+1);
				if (ckValorado.isSelected()) {
					matrizAdjacencia[indiceX][indiceY] = String.valueOf(a.getValor());
				}
			}
		}

		System.out.println("-----Matriz Adjacência-----");
		for (int k = 0; k < verticeLista.size() ; k++) {
			matrizAdjacencia[0][k+1] = verticeLista.get(k);
			matrizAdjacencia[k+1][0] = verticeLista.get(k);
		}
		matrizAdjacencia[0][0] = " ";
		for (int i = 0; i <= matrizAdjacencia.length; i++) {
			for (int j = 0; j < matrizAdjacencia.length; j++) {
				if (matrizAdjacencia[i][j] == null)
					matrizAdjacencia[i][j] = "0";
				System.out.print(matrizAdjacencia[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public void calculaListaAdjacencia() {

		k = 1;
		for (int x = 1; x < verticeLista.size(); x++) {
			if (txtOrigem.getText().equals(vertice[x])) {
				while (!(listaAdjacencia[x - 1][k] == null))
					k++;
				listaAdjacencia[x - 1][k] = txtDestino.getText();
			}
		}
		if (!ckOrientado.isSelected()) {
			k = 1;
			for (int y = 1; y < verticeLista.size(); y++) {
				if (txtDestino.getText().equals(vertice[y])) {
					while (listaAdjacencia[y - 1][k] != null)
						k++;
					listaAdjacencia[y - 1][k] = txtOrigem.getText();
				}
			}
		}

	}

	public void calculaMatrizIncidencia() {

		auxValor[w + 1] = txtValor.getText();
		matrizIncidencia[0][w + 1] = txtValor.getText();
		int indiceV = 0;

		for (int z = 1; z < auxValor.length; z++) {
			if (txtValor.getText().equals(auxValor[z])) {
				indiceV = z;
			}

		}
		for (int x = 1; x < verticeLista.size(); x++) {
			if (txtOrigem.getText().equals(vertice[x])) {
				matrizIncidencia[x][indiceV] = "1";

			}
		}
		for (int y = 1; y < verticeLista.size(); y++) {
			if (txtDestino.getText().equals(vertice[y])) {
				if (ckOrientado.isSelected()) {
					matrizIncidencia[y][indiceV] = "-1";
				} else {
					matrizIncidencia[y][indiceV] = "1";
				}
			}
		}

		w++;

	}

	@FXML
	public void finalizar() {

		calculaMatrizAdjacencia();
		calculaListaAdjacencia();
		calculaMatrizIncidencia();

		System.out.println("-----Lista de Arestas-----");
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < 3; y++) {
				if ((y == 2) & (listaAresta[x][y] == null))
					listaAresta[x][y] = "";
				System.out.print(listaAresta[x][y] + " ");
			}
			System.out.println("");
		}

		System.out.println("-----Lista de adjacência----");
		for (int x = 0; x < i - 1; x++) {
			listaAdjacencia[x][0] = vertice[x + 1] + "->";
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
			matrizIncidencia[x][0] = vertice[x];
			for (int y = 0; y <= w; y++) {
				if (matrizIncidencia[x][y] == null)
					matrizIncidencia[x][y] = "0";
				System.out.print(matrizIncidencia[x][y] + " ");
			}
			System.out.println("");
		}
	}

	@FXML
	public void adicionaAresta() {

		Aresta aresta = new Aresta();
		aresta.setOrigem(txtOrigem.getText());
		aresta.setDestino(txtDestino.getText());
		if (ckValorado.isSelected()) {
			aresta.setValor(Integer.parseInt(txtValor.getText()));
		} else
			aresta.setValor(0);
		arestaLista.add(aresta);
		limpaTelaE();

	}

	@FXML //
	public void adicionaVertice() {

		verticeLista.add(txtVetor.getText());
		i++;
		j++;
		txtVetor.setText("");

	}

	@FXML
	public void limpaTelaE() {
		txtOrigem.setText("");
		txtValor.setText("");
		txtDestino.setText("");

	}
}
