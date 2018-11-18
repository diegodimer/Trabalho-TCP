package Biblioteca;

public class Autor {

	private String nome;
	private int id;
	/**
	  * Construtor para autor
	  */
	Autor(){
		
	}
	/**
	  * Construtor para autor
	  * @param nome nome do autor
	  */
	Autor(String nome){
		this.nome = nome;
	}
	/**
	  * Construtor para autor
	  * @param nome nome do autor
	  * @param id id do autor
	  */
	Autor(String nome, int id){
		this.nome = nome;
		this.id = id;
	}
	@Override
	public String toString() {
		return "Id: "+ Integer.toString(id)+ "       Nome: "+nome; 
	}
	/**
	  * Getter para o nome do autor
	  * @return nome nome do autor
	  */
	public String getNome() {
		return nome;
	}
	/**
	  * Setter para o nome do  autor
	  * @param nome nome do autor
	  */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	  * Getter para o id do autor
	  * @return id id do autor
	  */
	public int getId() {
		return id;
	}
	/**
	  * Setter para o id do autor
	  * @param id id do autor
	  */
	public void setId(int id) {
		this.id = id;
	}

	
}
