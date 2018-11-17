package Biblioteca;
public class Aluguel {

	private ExemplarAlugado titulo;
	private int tempoRestante;
	/**
	  * Construtor para devolucao
	  */
	public void devolucao() {

	}
	/**
	  * Getter para o tempo restante de aluguel
	  * @return int valor de tempo restante
	  */
	public int getTempoRestante() {
		return tempoRestante;
	}
	/**
	  * Setter para o tempo restante de aluguel
	  * @param int valor de tempo restante
	  */
	public void setTempoRestante(int tempoRestante) {
		this.tempoRestante = tempoRestante;
	}
	/**
	  * Getter para o exemplar alugado
	  * @return titulo objeto da classe ExemplarAlugado
	  */
	public ExemplarAlugado getTitulo() {
		return titulo;
	}
	/**
	  * Setter para o exemplar alugado
	  * @param titulo objeto da classe ExemplarAlugado
	  */
	public void setTitulo(ExemplarAlugado titulo) {
		this.titulo = titulo;
	}
	
	

}
