package Biblioteca;
public class ExemplarAlugado extends Titulo {

	private String dataDevolucao;
	private String dataEmprestimo;
	
	ExemplarAlugado(Titulo e){
		super(e.getNome(), e.getIdTitulo());
	}
	
	ExemplarAlugado(String nome, int id){
		super();
		this.setNome(nome);
		this.setIdTitulo(id);
	}
	
	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public String getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(String dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	
	

}
