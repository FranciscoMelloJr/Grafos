package model;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vertice {

	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty path = new SimpleStringProperty("");
	private IntegerProperty distancia = new SimpleIntegerProperty(999);
	private boolean perm = false;
	private ArrayList<Vertice> adj = new ArrayList<Vertice>();

	@Override
	public String toString() {
		return "\nNome: " + nome + "\nPath: " + path + "\nDistancia: " + distancia + "\nPerm:" + perm;
	}

	public ArrayList<Vertice> getAdj() {
		return adj;
	}

	public void setAdj(ArrayList<Vertice> adj) {
		this.adj = adj;
	}

	public boolean isPerm() {
		return perm;
	}

	public void setPerm(boolean perm) {
		this.perm = perm;
	}

	public final StringProperty nomeProperty() {
		return this.nome;
	}

	public final String getNome() {
		return this.nomeProperty().get();
	}

	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}

	public final IntegerProperty distanciaProperty() {
		return this.distancia;
	}

	public final int getDistancia() {
		return this.distanciaProperty().get();
	}

	public final void setDistancia(final int distancia) {
		this.distanciaProperty().set(distancia);
	}

	public final StringProperty pathProperty() {
		return this.path;
	}

	public final String getPath() {
		return this.pathProperty().get();
	}

	public final void setPath(final String path) {
		this.pathProperty().set(path);
	}

}
