package Biblioteca;
public class Categoria {

	private int id;
	private String nome;
	/**
	  * Construtor para Categoria
	  */
	Categoria() {
		
	}
	/**
	  * Construtor para Categoria
	  * @param cat nome da categoria
	  */
	Categoria(String cat){
		this.nome=cat;
	}
	/**
	  * Construtor para Categoria
	  * @param nome nome do autor
	  * @param id id da categoria
	  */
	Categoria(String cat, int id) {
		this.nome=cat;
		this.id=id;
	}
	/**
	  * Getter para o id da categoria
	  * @return id id da categoria
	  */
	public int getId() {
		return id;
	}
	/**
	  * Setter para o id da categoria
	  * @param id id da categoria
	  */
	public void setId(int id) {
		this.id = id;
	}
	/**
	  * Getter para o nome da categoria
	  * @return nome nome da categoria
	  */
	public String getNome() {
		return nome;
	}
	/**
	  * Setter para o nome da categoria
	  * @param nome nome da categoria
	  */
	public void setNome(String nome) {
		this.nome = nome;
	}


	
}
