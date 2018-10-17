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

	ArrayList<String> verticeLista = new ArrayList<String>();
	ArrayList<Aresta> arestaLista = new ArrayList<Aresta>();

	String vertice[] = new String[20];
	String listaAdjacencia[][] = new String[10][10];
	String matrizIncidencia[][] = new String[10][20];
	String auxValor[] = new String[20];

	public void calculaListaAdjacencia() {

		ArrayList<Aresta> adjacenciaLista = new ArrayList<Aresta>();

		for (int i = 0; i < verticeLista.size(); i++) {
			Aresta a = new Aresta();
			a.setOrigem(verticeLista.get(i) + "->");
			adjacenciaLista.add(a);
			for (Aresta aresta : arestaLista) {
				if (verticeLista.get(i).equals(aresta.getOrigem())) {
					a.setDestino(a.getDestino() + " " + aresta.getDestino());
				}
			}
		}

		if (!ckOrientado.isSelected()) {
			for (int j = 0; j < verticeLista.size(); j++) {
				for (Aresta aresta : arestaLista) {
					if (verticeLista.get(j).equals(aresta.getDestino())) {
						adjacenciaLista.get(j).setOrigem(adjacenciaLista.get(j).getOrigem() + " " + aresta.getOrigem());
					}
				}
			}
		}

		System.out.println("-----Lista de adjacência----");
		for (int k = 0; k < verticeLista.size(); k++) {
			System.out.println(adjacenciaLista.get(k).nValorado());
		}
	}

	public void calculaListaAresta() {
		System.out.println("-----Lista de Arestas-----");
		ArrayList<Aresta> lista = arestaLista;

		if (!ckValorado.isSelected()) {
			for (int i = 0; i < arestaLista.size(); i++) {
				for (Aresta aresta : lista) {
					if (arestaLista.get(i).nValorado().equals(aresta.nValorado())) {
						aresta.setValor(aresta.getValor() + 1);
					}
				}
			}
			for (int j = 0; j < lista.size(); j++) {
				for (int l = 0; l < lista.size(); l++) {
					if (lista.get(j).nValorado().equals(lista.get(l).nValorado())) {
						if (lista.get(l).getValor() > 1) {
							lista.remove(l);
						}
					}
				}
			}
		}
		for (int k = 0; k < lista.size(); k++) {
			System.out.println(lista.get(k).toString());
		}
	}

	public void calculaMatrizAdjacencia() {

		String matrizAdjacencia[][] = new String[verticeLista.size() + 1][verticeLista.size() + 1];
		int indiceX = 1;
		int indiceY = 1;
		for (Aresta a : arestaLista) {
			for (int x = 0; x < verticeLista.size(); x++) {
				if (a.getOrigem().equals(verticeLista.get(x))) {
					indiceX = x + 1;
				}
			}
			for (int y = 0; y < verticeLista.size(); y++) {
				if (a.getDestino().equals(verticeLista.get(y))) {
					indiceY = y + 1;
				}
			}
			if ((matrizAdjacencia[indiceX][indiceY] != null)) {
				int arestas = Integer.parseInt(matrizAdjacencia[indiceX][indiceY]);
				arestas++;
				matrizAdjacencia[indiceX][indiceY] = String.valueOf(arestas);
			} else {
				matrizAdjacencia[indiceX][indiceY] = "1";
			}
			if (ckValorado.isSelected()) {
				matrizAdjacencia[indiceX][indiceY] = String.valueOf(a.getValor());
			}
			if (!ckOrientado.isSelected()) {
				if (matrizAdjacencia[indiceY][indiceX] != null) {
					int aux = Integer.parseInt(matrizAdjacencia[indiceY][indiceX]);
					if (indiceX != indiceY) {
						aux++;
					}
					matrizAdjacencia[indiceY][indiceX] = String.valueOf(aux);
				} else {
					matrizAdjacencia[indiceY][indiceX] = "1";
				}
				if (ckValorado.isSelected()) {
					matrizAdjacencia[indiceY][indiceX] = String.valueOf(a.getValor());
				}
			}
		}
		System.out.println("-----Matriz Adjacência-----");
		for (int k = 0; k < verticeLista.size(); k++) {
			matrizAdjacencia[0][k + 1] = verticeLista.get(k);
			matrizAdjacencia[k + 1][0] = verticeLista.get(k);
		}
		matrizAdjacencia[0][0] = " ";
		for (int i = 0; i < matrizAdjacencia.length; i++) {
			for (int j = 0; j < matrizAdjacencia.length; j++) {
				if (matrizAdjacencia[i][j] == null) {
					if (ckValorado.isSelected()) {
						matrizAdjacencia[i][j] = "X";
					} else {
						matrizAdjacencia[i][j] = "0";
					}
				}
				System.out.print(matrizAdjacencia[i][j] + " ");
			}
			System.out.println("");
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
	public void finalizar() {

		calculaListaAresta();
		calculaMatrizAdjacencia();
		calculaListaAdjacencia();
		calculaMatrizIncidencia();

	}

	@FXML
	public void adicionaAresta() {

		Aresta aresta = new Aresta();
		aresta.setOrigem(txtOrigem.getText());
		aresta.setDestino(txtDestino.getText());
		if (ckValorado.isSelected()) {
			aresta.setValor(Integer.parseInt(txtValor.getText()));
		} else {
			aresta.setValor(0);
		}
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
