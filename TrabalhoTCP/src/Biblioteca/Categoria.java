package Biblioteca;
public class Categoria {

	private int id;
	private String nome;
	
	Categoria() {
		
	}
	Categoria(String cat){
		this.nome=cat;
	}
	Categoria(String cat, int id) {
		this.nome=cat;
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}


	
}
