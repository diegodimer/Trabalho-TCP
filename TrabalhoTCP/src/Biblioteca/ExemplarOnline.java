package Biblioteca;

public class ExemplarOnline extends Titulo {

	private String link;

	public ExemplarOnline(String nome, Autor autor, Editora editora, int id, String link) {
		super(nome, autor, editora, id);
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	
	

}
