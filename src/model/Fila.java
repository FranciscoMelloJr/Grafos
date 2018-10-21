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

		atual = inicio;
		for (int i = 1; i <= this.tamanho; i++) {
			if (novo.vertice.getDistancia() > atual.vertice.getDistancia()) {
				if (atual.proximo != null) {
					atual = atual.proximo;
				} else {
					atual.proximo = novo;
					novo.anterior = atual;
					break;
				}
			} else {
				if (inicio.vertice.getNome().equals(atual.vertice.getNome())) {
					inicio.anterior = novo;
					novo.proximo = inicio;
					inicio = novo;
				} else {
					temp = atual.anterior;
					atual.anterior = novo;
					novo.anterior = temp;
					novo.anterior.proximo = novo;
					novo.proximo = atual;
				}
				break;
			}
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
			tamanho++;
		}
	}

	public Vertice remove() {

		Vertice v = inicio.vertice;
		inicio = inicio.proximo;
		tamanho--;

		return v;
	}

	public boolean verificaIgual(String vertice) {

		boolean adiciona = false;

		if (this.vazia()) {
			return adiciona;
		} else {
			temp = inicio;
			for (int i = 0; i < this.tamanho; i++) {
				if (vertice.equals(temp.vertice.getNome())) {
					adiciona = true;
				}
				temp = temp.proximo;
			}
			return adiciona;
		}
	}

	public int tamanho() {
		return tamanho;
	}

}
