package model;

public class Fila {

	private Elemento inicio, atual, temp;
	int tamanho;

	public Fila() {
		inicio = atual = temp = null;
	}

	public boolean vazia() {
		return atual == null;
	}

	public void prioridade(Elemento novo) {

		int i;
		atual = inicio;
		for (i = 1; i <= this.tamanho; i++) {
			System.out.println("Valor de i" + i);
			System.out.println("Distancia do novo vertice: " + novo.vertice.getDistancia());
			System.out.println("Distancia do vertice atual: " + atual.vertice.getDistancia());
			if (novo.vertice.getDistancia() > atual.vertice.getDistancia()) {
				if (atual.proximo != null) {
					System.out.println("navegando pelo atual: " + i);
					atual = atual.proximo;
				} else {
					System.out.println("--Entrou no final--");
					atual.proximo = novo;
					novo.anterior = atual;
					break;
				}
			} else {
				if (inicio.vertice.getNome().equals(atual.vertice.getNome())) {
					inicio.anterior = novo;
					novo.proximo = inicio;
					inicio = novo;
					System.out.println("--Inicio se tornou o novo--");
				} else {
					System.out.println("-- i menor que o tamanho, (não é o ultimo)-- ");
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
			System.out.println("--Insere no vazio--");

		} else {
			System.out.println("--Entrou para prioridade--");
			prioridade(novo);
			tamanho++;
		}
	}

	public Vertice remove() {

		System.out.println("Tamanho no remove(): " + tamanho);
		System.out.println("Nome do incio: " + inicio.vertice.getNome());
		Vertice v = inicio.vertice;
		inicio = inicio.proximo;
		tamanho--;

		if (tamanho == 0) {
			inicio = null;
			atual = null;
			temp = null;
		}
		return v;
	}

	public int tamanho() {
		return tamanho;
	}

}
