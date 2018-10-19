package model;

public class Fila {

	private Elemento inicio, atual, temp;
	int tamanho;

	public Fila() {
		inicio = atual = temp = null;
	}

	public boolean vazia() {
		return inicio == null;
	}

	public void prioridade(Elemento novo) {

		int i;
		atual = inicio;
		for (i = 0; i < this.tamanho; i++) {
			if (novo.vertice.getDistancia() > atual.vertice.getDistancia()) {
				if (atual.proximo != null) {
					atual = atual.proximo;
				} else {
					atual.proximo = novo;
					novo.anterior = atual;
				}
			}
		}

		if (i < this.tamanho - 1) {
			if (atual.vertice.getDistancia() > novo.vertice.getDistancia()) {
				inicio = novo;
			}
			temp = atual.anterior;
			atual.anterior = novo;
			novo.anterior = temp;
			novo.anterior.proximo = novo;
			novo.proximo = atual;
			tamanho++;
		}
	}

	public void insere(Vertice v) {

		Elemento novo = new Elemento(v);

		if (vazia()) {
			inicio = novo;
			temp = novo;
			atual = novo;
			tamanho++;
		} else {
			prioridade(novo);
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
