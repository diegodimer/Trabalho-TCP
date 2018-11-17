package Biblioteca;
public class Editora {

	private String nome;
	private int id;
	/**
	  * Construtor para Editora
	  */
	Editora(){
		
	}
	/**
	  * Construtor para Editora
	  * @param nome nome da editora
	  */
	Editora(String nome){
		this.nome = nome;
	}
	/**
	  * Construtor para Editora
	  * @param nome nome da editora
	  * @param id id da editora
	  */
	Editora(String nome, int id){
		this.nome = nome;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Id: "+ Integer.toString(id)+ "       Nome: "+nome; 
	}
	/**
	  * Getter para nome da editora
	  * @return nome nome da editora
	  */
	public String getNome() {
		return nome;
	}
	/**
	  * Setter para nome da Editora
	  * @param nome nome da editora
	  */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	  * Getter para id da editora
	  * @return id id da editora
	  */
	public int getId() {
		return id;
	}
	/**
	  * Setter para id da editora
	  * @param id id da editora
	  */
	public void setId(int id) {
		this.id = id;
	}

	

}
