package Biblioteca;

public class Titulo {

	private String nome;
	private Autor autor;
	private Editora editora;
	private int idTitulo;
	/**
	  * Construtor para Titulo
	  */
	Titulo(){
		
	}
	/**
	  * Construtor para Titulo
	  * @param nome nome do titulo
	  * @param idTitulo id do titulo
	  */
	Titulo(String nome, int idTitulo){
		this.nome = nome;
		this.idTitulo = idTitulo;
	}
	/**
	  * Construtor para Titulo
	  * @param nome nome do titulo
	  * @param autor objeto da classe Autor
	  * @param editora objeto da classe Editora
	  */
	Titulo(String nome, Autor autor, Editora editora){
		this.nome = nome;
		this.autor = autor;
		this.editora = editora;
	}
	/**
	  * Construtor para Titulo
	  * @param nome nome do titulo
	  * @param autor objeto da classe Autor
	  * @param editora objeto da classe Editora
	  * @param id id do titulo
	  */
	Titulo(String nome, Autor autor, Editora editora, int id){
		this.nome = nome;
		this.autor = autor;
		this.editora = editora;
		this.idTitulo = id;
	}
	
	@Override
	public String toString() {
		return "Id: "+ Integer.toString(idTitulo)+ "       Nome: "+nome+"        Editora: "+this.getEditora().getNome()+"        Autor: "+this.getAutor().getNome(); 
	}
	/**
	  * Construtor para Titulo
	  * @param nome nome do titulo
	  * @param autor objeto da classe Autor
	  */
	public Titulo(String nome, String autor) {
		this.nome = nome;
		this.autor = new Autor(autor);
	}
	/**
	  * Getter para idTitulo
	  * @return idTitulo id do titulo
	  */
	public int getIdTitulo() {
		return idTitulo;
	}
	/**
	  * Setter para idTitulo
	  * @param idTitulo id do titulo
	  */
	public void setIdTitulo(int idTitulo) {
		this.idTitulo = idTitulo;
	}
	/**
	  * Getter para Autor
	  * @return autor objeto da classe Autor
	  */
	public Autor getAutor() {
		return autor;
	}
	/**
	  * Setter para Autor
	  * @param autor objeto da classe Autor
	  */
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	/**
	  * Getter para Editora
	  * @return editora objeto da classe Editora
	  */
	public Editora getEditora() {
		return editora;
	}
	/**
	  * Setter para Editora
	  * @param editora objeto da classe Editora
	  */
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	/**
	  * Getter para nome do titulo
	  * @return nome nome do titulo
	  */
	public String getNome() {
		return nome;
	}
	/**
	  * Setter para nome do titulo
	  * @param nome nome do titulo
	  */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
