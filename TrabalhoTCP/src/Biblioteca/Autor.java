package Biblioteca;

public class Autor {

	private String nome;
	private int id;
	
	Autor(){
		
	}
	Autor(String nome){
		this.nome = nome;
	}
	
	Autor(String nome, int id){
		this.nome = nome;
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
}
