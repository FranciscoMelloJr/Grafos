package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Aresta;
import model.Fila;
import model.Vertice;

public class SecundarioController {

	@FXML
	RadioButton ckOrientado;

	@FXML
	TextField txtVertice;
	@FXML
	TextField txtOrigem;
	@FXML
	TextField txtDestino;
	@FXML
	TextField txtValor;
	@FXML
	TextField txtSource;

	@FXML
	TableView<Vertice> tbl;
	@FXML
	TableColumn<Vertice, String> colNome;
	@FXML
	TableColumn<Vertice, Number> colDistancia;
	@FXML
	TableColumn<Vertice, String> colPath;

	ArrayList<Vertice> verticeLista = new ArrayList<Vertice>();
	ArrayList<Aresta> arestaLista = new ArrayList<Aresta>();
	Vertice source = null;
	Fila fila = new Fila();

	@FXML
	public void initialize() {
		inicializaTbl();
	}

	@FXML
	public void finalizar() {

		Vertice atual = null;
		System.out.println(fila.tamanho());
		while (fila.tamanho() != 0) {
			atual = fila.remove();
			System.out.println(fila.tamanho());
			System.out.println(atual.getNome());
			insereADJ(atual);
			for (int i = 0; i < atual.getAdj().size(); i++) {
				System.out.println("PRIMEIRO FOR");
				for (Aresta aresta : arestaLista) {
					System.out.println("nome do atal" +atual.getNome() + " aresta oriem:" +aresta.getOrigem());
					System.out.println("nome adj do atual" +atual.getAdj().get(i).getNome() + " aresta destino:" +aresta.getDestino());
					if ((atual.getNome().equals(aresta.getOrigem()))
							&& (atual.getAdj().get(i).getNome().equals(aresta.getDestino()))) {
						System.out.println("VERTICE E ARESTAS COMPARA");
						for (Vertice vertice : verticeLista) {
							System.out.println("nome do vertice" + vertice.getNome()+ "atual adj nome" + atual.getAdj().get(i).getNome());
							if (vertice.getNome().equals(atual.getAdj().get(i).getNome())) {
								System.out.println("distancia atual :" + atual.getDistancia() +"+" +
							"valor da aresta " + aresta.getValor() + " <  distancia do vertice" + vertice.getDistancia() );
								if (atual.getDistancia() + aresta.getValor() < vertice.getDistancia()) {
									System.out.println("ENTRA PRA ALTERAR");
									vertice.setDistancia((atual.getDistancia() + aresta.getValor()));
									vertice.setPath(atual.getNome());
								}
							}
						}
					}
				}
			}

			atual.setPerm(true);
			System.out.println("Loop aqui");
		}
		tbl.setItems(FXCollections.observableArrayList(verticeLista));
	}

	public void insereADJ(Vertice vertice) {

		for (int i = 0; i < vertice.getAdj().size(); i++) {
			if (!vertice.getAdj().get(i).isPerm()) {
				System.out.print("Adicionou adjacente");
				fila.insere(vertice.getAdj().get(i));
			}
		}
	}

	@FXML
	public void adicionaSource() {

		for (Vertice vertice : verticeLista) {
			if (vertice.getNome().equals(txtSource.getText())) {
				source = vertice;
			}
		}
		source.setDistancia(0);
		txtSource.setText("");
		fila.insere(source);

	}

	@FXML
	public void adicionaAresta() {

		Aresta aresta = new Aresta();
		aresta.setOrigem(txtOrigem.getText());
		aresta.setDestino(txtDestino.getText());
		aresta.setValor(Integer.parseInt(txtValor.getText()));
		arestaLista.add(aresta);

		for (Vertice vertice : verticeLista) {
			if (vertice.getNome().equals(aresta.getOrigem()))
				for (Vertice adjacente : verticeLista) {
					if (aresta.getDestino().equals(adjacente.getNome())) {
						vertice.getAdj().add(adjacente);
						adjacente.getAdj().add(vertice);
					}
				}
		}
		limpaTelaE();

	}

	@FXML
	public void adicionaVertice() {

		Vertice vertice = new Vertice();
		vertice.setNome(txtVertice.getText());
		verticeLista.add(vertice);
		txtVertice.setText("");

	}

	private void inicializaTbl() {
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colDistancia.setCellValueFactory(cellData -> cellData.getValue().distanciaProperty());
		colPath.setCellValueFactory(cellData -> cellData.getValue().pathProperty());

	}

	@FXML
	public void limpaTelaE() {

		txtOrigem.setText("");
		txtValor.setText("");
		txtDestino.setText("");

	}
}
