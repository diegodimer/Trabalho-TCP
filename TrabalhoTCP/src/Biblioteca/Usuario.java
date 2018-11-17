package Biblioteca;
import java.util.ArrayList;

public class Usuario {

	private int userid;
	private String username;
	private String password;
	private int debito;
	private ArrayList<ExemplarAlugado> alugueis;
	private String email;
	private boolean isADM;
	/**
	  * Construtor para Usuario
	  */
	Usuario(){
		
	}
	/**
	  * Setter para id do usuario
	  * @param userid id do usuario
	  */
	public void setUserid(int userid) {
		this.userid = userid;
	}
	/**
	  * Getter para id do usuario
	  * @return userid id do usuario
	  */
	public int getUserid() {
		return userid;
	}
	/**
	  * Setter para nome do usuario
	  * @param username nome do usuario
	  */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	  * Getter para o nome do usuario
	  * @return username nome do usuario
	  */
	public String getUsername() {
		return username;
	}
	/**
	  * Setter para senha do usuario
	  * @param password senha do usuario
	  */
	public void setPassword(String password) {
		this.password = password;

	}
	/**
	  * Getter para senha do usuario
	  * @return password senha do usuario
	  */
	public String getPassword() {
		return password;
	}
	/**
	  * Setter para debito do usuario
	  * @param debito debito do usuario
	  */
	public void setDebito(int debito) {
		this.debito = debito;
	}
	/**
	  * Getter para debito do usuario
	  * @return debito debito do usuario
	  */
	public int getDebito() {
		return this.debito;
	}

	/**
	  * Construtor para usuario
	  * @param nome nome do usuario
	  * @param senha senha do usuario
	  * @param email email do usuario
	  */
	public Usuario(String nome, String senha, String email) {
		this.username = nome;
		this.password = senha;
		this.email = email;
		this.debito = 0;
		this.setADM(false);
		this.alugueis=new ArrayList<>();
	}
	/**
	  * Construtor para usuario
	  * @param nome nome do usuario
	  * @param senha senha do usuario
	  * @param email email do usuario
	  * @param userid id do usuario
	  */
	public Usuario(String nome, String senha, String email, int userid) {
		this.username = nome;
		this.password = senha;
		this.email = email;
		this.debito = 0;
		this.setADM(false);
		this.alugueis=new ArrayList<>();
		this.userid = userid;
	}
	public String toString() {
		return "Id: "+ Integer.toString(userid)+ "       Nome: "+username; 
	}
	/**
	  * Setter para o email do usuario
	  * @param email email do usuario
	  */
	public void setEmail(String email) {
		this.email = email;

	}
	/**
	  * Getter para o email do usuario
	  * @return email email do usuario
	  */
	public String getEmail() {
		return email;
	}
	/**
	  * Getter para status de administrador de usuario
	  * @return isADM status de admin do usuario
	  */
	public boolean isADM() {
		return isADM;
	}
	/**
	  * Setter para status de administrador de usuario
	  * @param isADM status de admin do usuario
	  */
	public void setADM(boolean isADM) {
		this.isADM = isADM;
	}
	/**
	  * Getter para alugueis de usuario
	  * @return alugueis lista de objetos da classe ExemplarAlugado
	  */
	public ArrayList<ExemplarAlugado> getAlugueis() {
		return alugueis;
	}
	/**
	  * Setter para alugueis de usuario
	  * @param alugueis lista de objetos da classe ExemplarAlugado
	  */
	public void setAlugueis(ArrayList<ExemplarAlugado> alugueis) {
		this.alugueis = alugueis;
	}
	/**
	  * Metodo para adição de novos alugueis a lista de alugueis ativos do usuario
	  * @param aluguel objeto da classe ExemplarAlugado
	  */
	public void adicionaNovoAluguel(ExemplarAlugado aluguel) {
		this.alugueis.add(aluguel);
	}
	/**
	  * Metodo para listar todos alugueis ativos do usuario
	  * @return string string contendo nome data de emprestimo e de devolucao de todos alugueis do usuario
	  */
	public String listaAlugueisAtivos() {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for(int i = 0 ; i < alugueis.size() ; i++ )
			stringBuilder.append( "Nome: " +  alugueis.get(i).getNome() + " Emprestado em: " + alugueis.get(i).getDataEmprestimo() + " Devolução: " + alugueis.get(i).getDataDevolucao() + "\n");
		
		return stringBuilder.toString();
		
	}
	/**
	  * Metodo para limpar todos alugueis de usuario, esvazia o valor alugueis da classe Usuario
	  */
	public void resetaAlugueis() {
		this.alugueis.clear();
		
	}
}
