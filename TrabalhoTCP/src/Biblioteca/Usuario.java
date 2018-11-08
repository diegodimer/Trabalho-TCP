package Biblioteca;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Usuario {

	private int userid;
	private String username;
	private String password;
	private int debito;
	private ArrayList<ExemplarAlugado> alugueis;
	private String email;
	private boolean isADM;

	Usuario(){
		
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;

	}

	public String getPassword() {
		return password;
	}

	public void setDebito(int debito) {
		this.debito = debito;
	}

	public int getDebito() {
		return this.debito;
	}


	public Usuario(String nome, String senha, String email) {
		this.username = nome;
		this.password = senha;
		this.email = email;
		this.debito = 0;
		this.setADM(false);
		this.alugueis=new ArrayList<>();
	}

	public void setEmail(String email) {
		this.email = email;

	}

	public String getEmail() {
		return email;
	}

	public boolean isADM() {
		return isADM;
	}

	public void setADM(boolean isADM) {
		this.isADM = isADM;
	}

	public ArrayList<ExemplarAlugado> getAlugueis() {
		return alugueis;
	}

	public void setAlugueis(ArrayList<ExemplarAlugado> alugueis) {
		this.alugueis = alugueis;
	}
	
	public void adicionaNovoAluguel(ExemplarAlugado aluguel) {
		this.alugueis.add(aluguel);
	}
	
	public String listaAlugueisAtivos() {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for(int i = 0 ; i < alugueis.size() ; i++ )
			stringBuilder.append( "Nome: " +  alugueis.get(i).getNome() + " Emprestado em: " + alugueis.get(i).getDataEmprestimo() + " Devolução: " + alugueis.get(i).getDataDevolucao() + "\n");
		
		return stringBuilder.toString();
		
	}
	public void resetaAlugueis() {
		this.alugueis.clear();
		
	}
}
