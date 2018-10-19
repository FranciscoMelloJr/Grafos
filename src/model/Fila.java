package model;

public class Fila {

	private Elemento inicio, fim;
	int tamanho;

	public Fila() {
		inicio = fim = null;
	}

	public boolean vazia() {
		return inicio == null;
	}

	public void insere(Vertice v) {
		Elemento novo = new Elemento(v);
		if (vazia()) {
			inicio = novo;
			fim = novo;
			tamanho++;
		} else {
			fim.proximo = novo;
			fim = novo;
			tamanho++;
		}
	}

	public Vertice remove() {
		Vertice v = inicio.vertice;
		inicio = inicio.proximo;
		tamanho--;
		return v;
	}

	public Vertice frente() {
		return inicio.vertice;
	}

	public int tamanho() {
		return tamanho;
	}

}
