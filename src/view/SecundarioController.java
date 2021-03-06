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
	RadioButton ckDestiny;

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
	TextField txtDestiny;

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
	Fila fila = new Fila();
	Vertice destiny;

	@FXML
	public void initialize() {
		inicializaTbl();
	}

	@FXML
	public void finalizar() {

		while (fila.tamanho() != 0) {
			Vertice atual = fila.remove();
			for (int i = 0; i < atual.getAdj().size(); i++) {
				if (!atual.getAdj().get(i).isPerm()) {
					for (Aresta aresta : arestaLista) {
						if (ckOrientado.isSelected()) {
							if ((atual.getNome().equals(aresta.getOrigem()))
									&& (atual.getAdj().get(i).getNome().equals(aresta.getDestino()))) {
								alteraDist(atual, aresta, i);
							}
						} else {
							if ((atual.getNome().equals(aresta.getOrigem())
									|| atual.getNome().equals(aresta.getDestino()))
									&& (atual.getAdj().get(i).getNome().equals(aresta.getDestino())
											|| atual.getAdj().get(i).getNome().equals(aresta.getOrigem()))) {
								alteraDist(atual, aresta, i);
							}
						}
					}
				}
			}
			insereADJ(atual);
			atual.setPerm(true);
			if ((ckDestiny.isSelected()) && (destiny.isPerm())) {
				break;
			}
		}
		tbl.setItems(FXCollections.observableArrayList(verticeLista));
	}

	public void alteraDist(Vertice atual, Aresta aresta, int i) {

		for (Vertice vertice : verticeLista) {
			if (vertice.getNome().equals(atual.getAdj().get(i).getNome())) {
				if (atual.getDistancia() + aresta.getValor() < vertice.getDistancia()) {
					vertice.setDistancia((atual.getDistancia() + aresta.getValor()));
					vertice.setPath(atual.getNome());
				}
			}
		}
	}

	public void insereADJ(Vertice vertice) {

		for (int i = 0; i < vertice.getAdj().size(); i++) {
			if (!vertice.getAdj().get(i).isPerm()) {
				if (!(fila.verificaIgual((vertice.getAdj().get(i).getNome())))) {
					fila.insere(vertice.getAdj().get(i));
				}
			}
		}
	}

	@FXML
	public void adicionaDestiny() {

		for (Vertice vertice : verticeLista) {
			if (vertice.getNome().equals(txtDestiny.getText())) {
				destiny = vertice;
			}
		}
		txtDestiny.setText("");

	}

	@FXML
	public void destinySN() {
		if (ckDestiny.isSelected()) {
			txtDestiny.setDisable(false);
		} else {
			txtDestiny.setDisable(true);
		}
	}

	@FXML
	public void adicionaSource() {

		Vertice source = null;
		for (Vertice vertice : verticeLista) {
			if (vertice.getNome().equals(txtSource.getText())) {
				source = vertice;
			}
		}
		source.setDistancia(0);
		source.setPerm(true);
		fila.insere(source);
		txtSource.setText("");

	}

	@FXML
	public void adicionaAresta() {

		Aresta aresta = new Aresta();
		aresta.setOrigem(txtOrigem.getText());
		aresta.setDestino(txtDestino.getText());
		aresta.setValor(Integer.parseInt(txtValor.getText()));
		arestaLista.add(aresta);

		for (Vertice vertice : verticeLista) {
			if (vertice.getNome().equals(aresta.getOrigem())) {
				for (Vertice adjacente : verticeLista) {
					if (adjacente.getNome().equals(aresta.getDestino())) {
						vertice.getAdj().add(adjacente);
						adjacente.getAdj().add(vertice);
					}
				}
			}
		}
		limpaTelaE();
		txtOrigem.requestFocus();
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

	@FXML
	public void focusValor() {
		txtValor.requestFocus();
	}

	@FXML
	public void focusDestino() {
		txtDestino.requestFocus();

	}

}
