package Biblioteca;

public class ExemplarFisico extends Titulo {

	private int numDisponiveis;
	/**
	  * Construtor para ExemplarFisico
	  * @param nome nome do titulo
	  * @param autor objeto da classe Autor
	  * @param editora objeto da classe Editora
	  * @param id id do titulo
	  * @param numdisp numero de exemplares disponiveis
	  */
	ExemplarFisico(String nome, Autor autor, Editora editora, int id, int numdisp){
		super(nome,autor,editora,id);
		this.numDisponiveis = numdisp;
	}
	/**
	  * Getter para numDisponiveis
	  * @return numDisponiveis numero de exemplares fisicos disponiveis
	  */
	public int getNumDisponiveis() {
		return numDisponiveis;
	}
	/**
	  * Setter para numDisponiveis
	  * @param numDisponiveis numero de exemplares fisicos disponiveis
	  */
	public void setNumDisponiveis(int numDisponiveis) {
		this.numDisponiveis = numDisponiveis;
	}

	

}
