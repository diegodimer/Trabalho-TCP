package Biblioteca;
public class ExemplarAlugado extends Titulo {

	private String dataDevolucao;
	private String dataEmprestimo;
	/**
	  * Construtor para ExemplarAlugado
	  * @param e objeto da classe Titulo
	  */
	ExemplarAlugado(Titulo e){
		super(e.getNome(), e.getIdTitulo());
	}
	/**
	  * Construtor para ExemplarAlugado
	  * @param nome nome do titutlo
	  * @param id id do titulo
	  */
	ExemplarAlugado(String nome, int id){
		super();
		this.setNome(nome);
		this.setIdTitulo(id);
	}
	/**
	  * Construtor para ExemplarAlugado
	  * @param nome nome do titutlo
	  * @param autor objeto da classe Autor
	  * @param editora objeto da classe Editora
	  */
	public ExemplarAlugado(String nome, Autor autor, Editora editora) {
		super(nome,autor,editora);
	}
	/**
	  * Construtor para ExemplarAlugado
	  * @param nome nome do titutlo
	  * @param autor objeto da classe Autor
	  * @param editora objeto da classe Editora
	  * @param idTitulo id do titulo
	  */
	public ExemplarAlugado(String nome, Autor autor, Editora editora, int idTitulo) {
		super(nome,autor,editora,idTitulo);
	}
	/**
	  * Construtor para ExemplarAlugado
	  * @param nome nome do titutlo
	  * @param autor nome do autor
	  * @param dataEmprestimo data da ocorrencia do emprestimo
	  * @param dataDevolucao data da devolucao do exemplar
	  */
	public ExemplarAlugado(String nome, String autor, String dataEmprestimo, String dataDevolucao) {
		super(nome, autor);
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
	}
	/**
	  * Setter para dataDevolucao
	  * @param dataDevolucao data de devolucao
	  */
	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	/**
	  * Getter para dataDevolucao
	  * @return dataDevolucao data de devolucao
	  */
	public String getDataDevolucao() {
		return dataDevolucao;
	}
	/**
	  * Getter para dataEmprestimo
	  * @return dataEmprestimo data de emprestimo
	  */
	public String getDataEmprestimo() {
		return dataEmprestimo;
	}
	/**
	  * Setter para dataEmprestimo
	  * @param dataEmprestimo data de emprestimo
	  */
	public void setDataEmprestimo(String dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	
	

}
