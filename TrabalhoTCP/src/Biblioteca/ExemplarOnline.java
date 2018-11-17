package Biblioteca;

public class ExemplarOnline extends Titulo {

	private String link;
	/**
	  * Construtor para ExemplarOnline
	  * @param nome nome do titulo
	  * @param autor objeto da classe Autor
	  * @param editora objeto da classe Editora
	  * @param id id do titulo
	  * @param link link para o exemplar online
	  */
	public ExemplarOnline(String nome, Autor autor, Editora editora, int id, String link) {
		super(nome, autor, editora, id);
		this.link = link;
	}
	/**
	  * Getter para link
	  * @return link link para o exemplar online
	  */
	public String getLink() {
		return link;
	}
	/**
	  * Setter para link
	  * @param link link para o exemplar online
	  */
	public void setLink(String link) {
		this.link = link;
	}

	
	

}
