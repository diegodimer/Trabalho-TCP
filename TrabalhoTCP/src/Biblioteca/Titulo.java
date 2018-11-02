package Biblioteca;

public class Titulo {

	private String nome;
	private Autor autor;
	private Editora editora;
	private int idTitulo;
	
	Titulo(){
		
	}
	Titulo(String nome, int idTitulo){
		this.nome = nome;
		this.idTitulo = idTitulo;
	}
	Titulo(String nome, Autor autor, Editora editora){
		this.nome = nome;
		this.autor = autor;
		this.editora = editora;
	}
	Titulo(String nome, Autor autor, Editora editora, int id){
		this.nome = nome;
		this.autor = autor;
		this.editora = editora;
		this.idTitulo = id;
	}
	
	public int getIdTitulo() {
		return idTitulo;
	}
	public void setIdTitulo(int idTitulo) {
		this.idTitulo = idTitulo;
	}
	
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public Editora getEditora() {
		return editora;
	}
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
