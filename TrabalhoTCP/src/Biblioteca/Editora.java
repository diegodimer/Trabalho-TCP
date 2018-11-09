package Biblioteca;
public class Editora {

	private String nome;
	private int id;
	Editora(){
		
	}
	
	Editora(String nome){
		this.nome = nome;
	}
	
	Editora(String nome, int id){
		this.nome = nome;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Id: "+ Integer.toString(id)+ "       Nome: "+nome; 
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
