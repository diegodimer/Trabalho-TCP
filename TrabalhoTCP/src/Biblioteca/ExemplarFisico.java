package Biblioteca;

public class ExemplarFisico extends Titulo {

	private int numDisponiveis;
	
	ExemplarFisico(String nome, Autor autor, Editora editora, int id, int numdisp){
		super(nome,autor,editora,id);
		this.numDisponiveis = numdisp;
	}

	public int getNumDisponiveis() {
		return numDisponiveis;
	}

	public void setNumDisponiveis(int numDisponiveis) {
		this.numDisponiveis = numDisponiveis;
	}

	

}
