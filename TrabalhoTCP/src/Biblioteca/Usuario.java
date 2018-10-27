package Biblioteca;
import java.util.ArrayList;

public class Usuario {

	private int userid;
	private String username;
	private String password;
	private int debito;
	private ArrayList<Aluguel> alugueis;
	private String email;
	private ExemplarAlugado exemplarAlugado;
	private ExemplarAlugavel exemplarAlugavel;
	private boolean isADM;

	public Aluguel Aluga(ExemplarAlugavel livro) {
		return null;
	}

	Usuario(){
		
	}
	public void log_in() {

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

	public void setAlugueis(ArrayList<Aluguel> alugueis) {

	}

	public ArrayList<Aluguel> getAlugueis() {
		return null;
	}

	public Usuario(String nome, String senha, String email) {
		this.username = nome;
		this.password = senha;
		this.email = email;
		this.debito = 0;
		this.setADM(false);
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

}
