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

		source.setDistancia(0);
		insereADJ(source);

		while (!fila.vazia()) {
			Vertice atual = fila.remove();
			for (int i = 0; i < atual.getAdj().size(); i++) {
				for (Aresta aresta : arestaLista) {
					if ((atual.getNome().equals(aresta.getOrigem()))
							&& (atual.getAdj().get(i).getNome().equals(aresta.getDestino()))) {

						for (Vertice vertice : verticeLista) {
							if (vertice.getNome().equals(atual.getAdj().get(i).getNome())) {

								if (atual.getDistancia() + aresta.getValor() < vertice.getDistancia()) {
									vertice.setDistancia((atual.getDistancia() + aresta.getValor()));
									vertice.setPath(atual.getNome());
								}
							}
						}
					}

				}
			}
			insereADJ(atual);
		}
		tbl.setItems(FXCollections.observableArrayList(verticeLista));
	}

	private void inicializaTbl() {
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colDistancia.setCellValueFactory(cellData -> cellData.getValue().distanciaProperty());
		colPath.setCellValueFactory(cellData -> cellData.getValue().pathProperty());

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

	@FXML
	public void adicionaSource() {

		for (Vertice vertice : verticeLista) {
			if (vertice.getNome().equals(txtSource.getText())) {
				source = vertice;
			}
		}
		txtSource.setText("");
	}

	@FXML
	public void limpaTelaE() {

		txtOrigem.setText("");
		txtValor.setText("");
		txtDestino.setText("");

	}

	public void insereADJ(Vertice vertice) {

		for (int i = 0; i < vertice.getAdj().size(); i++) {
			if (!vertice.getAdj().get(i).isPerm()) {
				fila.insere(vertice);
			}
		}
	}

}
