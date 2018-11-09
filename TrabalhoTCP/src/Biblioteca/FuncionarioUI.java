package Biblioteca;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class FuncionarioUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Database dataBase;
	private Usuario user;
	
	private JButton btnMakeAdm;
	private JButton btnAdicionaEditora;
	private JButton btnAdicionaCategoria;
	private JButton btnAdicionaTitulo;
	private JButton btnAdicionaExemplarFisico;
	private JButton btnAdicionaExemplarOnline;
	private JButton btnDeletarAutor;
	private JButton btnDeletarEditora; 
	private JButton btnDeletarTitulo;
	private JButton btnDeletarUsuario;
	private JButton btnAdicionarAutor;
	
	private JButton btnDeletarTitulo2;
	private JTextField CampoId;
	
	public FuncionarioUI(Usuario user, Database dataBase) {
		setResizable(false);
		this.dataBase = dataBase;
		this.user = user;
		setTitle("Biblioteca manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 213, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFuncionario = new JLabel("Você está autenticado como " + user.getUsername());
		lblFuncionario.setBounds(5, 5, 197, 11);
		lblFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblFuncionario);
		
		btnMakeAdm = new JButton("Torna usu\u00E1rio ADM");
		btnMakeAdm.addActionListener(this);
		btnMakeAdm.setBounds(15, 27, 174, 23);
		contentPane.add(btnMakeAdm);
		
		btnAdicionaEditora = new JButton("Adiciona Editora");
		btnAdicionaEditora.addActionListener(this);
		btnAdicionaEditora.setBounds(15, 105, 174, 23);
		contentPane.add(btnAdicionaEditora);
		
		btnAdicionaCategoria = new JButton("Adiciona Categoria");
		btnAdicionaCategoria.addActionListener(this);
		btnAdicionaCategoria.setBounds(15, 79, 174, 23);
		contentPane.add(btnAdicionaCategoria);
		
		btnAdicionaTitulo = new JButton("Adiciona Titulo");
		btnAdicionaTitulo.addActionListener(this);
		btnAdicionaTitulo.setBounds(15, 131, 174, 23);
		contentPane.add(btnAdicionaTitulo);
		
		btnAdicionaExemplarFisico = new JButton("Adiciona Exemplar Fisico");
		btnAdicionaExemplarFisico.addActionListener(this);
		btnAdicionaExemplarFisico.setBounds(15, 157, 174, 23);
		contentPane.add(btnAdicionaExemplarFisico);
		
		btnAdicionaExemplarOnline = new JButton("Adiciona Exemplar Online");
		btnAdicionaExemplarOnline.addActionListener(this);
		btnAdicionaExemplarOnline.setBounds(15, 183, 174, 23);
		contentPane.add(btnAdicionaExemplarOnline);
		
		btnDeletarAutor = new JButton("Deletar Autor");
		btnDeletarAutor.addActionListener(this);
		btnDeletarAutor.setBounds(15, 209, 174, 23);
		contentPane.add(btnDeletarAutor);
		
		btnDeletarEditora = new JButton("Deletar Editora");
		btnDeletarEditora.addActionListener(this);
		btnDeletarEditora.setBounds(15, 235, 174, 23);
		contentPane.add(btnDeletarEditora);
		
		btnDeletarTitulo = new JButton("Deletar Titulo");
		btnDeletarTitulo.addActionListener(this);
		btnDeletarTitulo.setBounds(15, 287, 174, 23);
		contentPane.add(btnDeletarTitulo);
		
		btnDeletarUsuario = new JButton("Deletar Usuario");
		btnDeletarUsuario.addActionListener(this);
		btnDeletarUsuario.setBounds(15, 261, 174, 23);
		contentPane.add(btnDeletarUsuario);
		
		btnAdicionarAutor = new JButton("Adicionar Autor");
		btnAdicionarAutor.addActionListener(this);
		btnAdicionarAutor.setBounds(15, 53, 174, 23);
		contentPane.add(btnAdicionarAutor);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnMakeAdm) 
			tornaUserADM();
		else if(e.getSource() == btnAdicionaEditora)
			adicionaEditora();
			else if(e.getSource() == btnAdicionaCategoria)
				adicionaCategoria();
				else if(e.getSource() == btnDeletarEditora)
					deletarEditora();
					else if(e.getSource() == btnAdicionaTitulo)
						adicionaTitulo();
						else if(e.getSource() == btnAdicionaExemplarFisico)
							adicionaExemplarFisico();
							else if(e.getSource() == btnAdicionaExemplarOnline)
								adicionaExemplarOnline();
								else if(e.getSource() == btnDeletarAutor)
									deletarAutor();
									else if(e.getSource() == btnDeletarTitulo)
										deletarTitulo();
										else if(e.getSource() == btnDeletarUsuario)
											deletarUsuario();
											else if(e.getSource() == btnAdicionarAutor)
												adicionarAutor();
												else if(e.getSource() == btnDeletarTitulo2)
													deletarTitulo2();
		


		
	}
	
	private void deletarTitulo() {
		JFrame janela = new JFrame("Deletar Titulo");
		janela.setSize(300,400);
		
		JPanel Painel = new JPanel();
		janela.add(Painel);
		
		Painel.setLayout(null);
		JLabel Labeltitulo = new JLabel("Id:");
		Labeltitulo.setBounds(20,20,150,23);
		Painel.add(Labeltitulo);
		
		CampoId = new JTextField(12);
		CampoId.setBounds(40,20,45,23);
		Painel.add(CampoId);
		
		btnDeletarTitulo2 = new JButton("Deletar");
		btnDeletarTitulo2.setBounds(95,20,80,23);
		btnDeletarTitulo2.addActionListener(this);
		Painel.add(btnDeletarTitulo2);
		/*
		setBounds(100, 100, 489, 300);
		Painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JTextField campoID = new JTextField(5);
		Painel.add(new JLabel("Id:"));
		Painel.add(campoID);
		*/
		janela.setVisible(true);
		
	}
	
	private void deletarTitulo2(){
		String titulo = CampoId.getText();
		if(dataBase.removeTitulo(Integer.parseInt(titulo))) {
			JOptionPane.showMessageDialog(null, "titulo removido!");
		}
			
			
	}
	
	private void deletarEditora() {
		JOptionPane.showMessageDialog(null, "deletar Editora");
		
	}
	private void deletarAutor() {
		JOptionPane.showMessageDialog(null, "deletarAutor");
		
	}
	private void adicionaExemplarFisico() {
		JTextField nomeField = new JTextField(5);
	    JTextField autorField = new JTextField(5);
	    JTextField editoraField = new JTextField(5);
	    JTextField numDisponiveisField = new JTextField(5);
	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Nome:"));
	    myPanel.add(nomeField);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Autor:"));
	    myPanel.add(autorField);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Editora:"));
	    myPanel.add(editoraField);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Num. Disponiveis:"));
	    myPanel.add(numDisponiveisField);
	    

	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Digite os dados: ", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	    	  try {
	    		  Autor autorLivroAdicionado = dataBase.findAutor(autorField.getText());
	    		  Editora editoraLivroAdicionado = dataBase.findEditora(editoraField.getText());
	    		  
	    		  Titulo tituloAdicionado = new Titulo(nomeField.getText(), autorLivroAdicionado, editoraLivroAdicionado);
	    		  Titulo tituloEncontrado = dataBase.findTitulo(tituloAdicionado);
	    		  
	    		  dataBase.addExemplarFisico(tituloEncontrado.getIdTitulo(), Integer.parseInt(numDisponiveisField.getText()));
	    		  JOptionPane.showMessageDialog(null, "Livro adicionado com sucesso!");
	    	  }
	    	  catch(AutorNaoEncontradoException | TituloNaoEncontradoException | EditoraNaoEncontradaException | DatabaseInoperanteException e1)
	    	  {
	    		  JOptionPane.showMessageDialog(null, e1.getMessage() + " Lembre que eu sou case sensitive!!!!");
	    	  }
	      }
		
	}
	private void adicionaTitulo() {
		JTextField nomeField = new JTextField(5);
	    JTextField autorField = new JTextField(5);
	    JTextField editoraField = new JTextField(5);
	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Nome:"));
	    myPanel.add(nomeField);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Autor:"));
	    myPanel.add(autorField);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Editora:"));
	    myPanel.add(editoraField);
	    

	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Digite os dados: ", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	    	  try {
	    		  Autor autorLivroAdicionado = dataBase.findAutor(autorField.getText());
	    		  Editora editoraLivroAdicionado = dataBase.findEditora(editoraField.getText());
	    		  
	    		  Titulo tituloAdicionado = new Titulo(nomeField.getText(), autorLivroAdicionado, editoraLivroAdicionado);
	    		  dataBase.addTitulo(tituloAdicionado);
	    		  JOptionPane.showMessageDialog(null, "Livro adicionado com sucesso!");
	    	  }
	    	  catch(AutorNaoEncontradoException | EditoraNaoEncontradaException | DatabaseInoperanteException e1)
	    	  {
	    		  JOptionPane.showMessageDialog(null, e1.getMessage() + " Lembre que eu sou case sensitive!!!!");
	    	  }
	      }
	      

		
	}
	private void deletarUsuario() {
		JOptionPane.showMessageDialog(null, "deletar usuario");
		
	}
	private void adicionaCategoria() {
		JTextField nomeField = new JTextField(5);
	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Nome:"));
	    myPanel.add(nomeField);
	   
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Adicionar Categoria", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	    	  try {
	    		  dataBase.addCategoria(new Categoria(nomeField.getText()));
	    		  JOptionPane.showMessageDialog(null, "Operação concluida com sucesso!");
	    	  }
	    	  catch(DatabaseInoperanteException e1)
	    	  {
	    		  JOptionPane.showMessageDialog(null, e1.getMessage() + " Lembre que eu sou case sensitive!!!!");
	    	  }
	      }
	}
	private void adicionaExemplarOnline() {
		JOptionPane.showMessageDialog(null, "adicionar exemplar online");
		
	}
	
	
	private void adicionaEditora() {
		JTextField nomeField = new JTextField(5);
	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Nome:"));
	    myPanel.add(nomeField);
	   
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Adicionar Editora", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	    	  try {
	    		  dataBase.addEditora(new Editora(nomeField.getText()));
	    		  JOptionPane.showMessageDialog(null, "Operação concluida com sucesso!");
	    	  }
	    	  catch(DatabaseInoperanteException e1)
	    	  {
	    		  JOptionPane.showMessageDialog(null, e1.getMessage() + " Lembre que eu sou case sensitive!!!!");
	    	  }
	      }
	      
		
	}
	
	private void adicionarAutor() {
		JTextField nomeField = new JTextField(5);
	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Nome:"));
	    myPanel.add(nomeField);
	   
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Adicionar Autor", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	    	  try {
	    		  dataBase.addAutor(new Autor(nomeField.getText()));
	    		  JOptionPane.showMessageDialog(null, "Operação concluida com sucesso!");
	    	  }
	    	  catch(DatabaseInoperanteException e1)
	    	  {
	    		  JOptionPane.showMessageDialog(null, e1.getMessage() + " Lembre que eu sou case sensitive!!!!");
	    	  }
	      }
	}
	
	private void tornaUserADM() {
		
		JTextField nomeField = new JTextField(5);
	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Nome:"));
	    myPanel.add(nomeField);
	   
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Tornar usuario administrador", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	    	  try {
	    		  dataBase.tornaUserAdm(nomeField.getText());
	    		  JOptionPane.showMessageDialog(null, "Operação concluida com sucesso!");
	    	  }
	    	  catch(DatabaseInoperanteException | UsuarioNaoEncontradoException e1)
	    	  {
	    		  JOptionPane.showMessageDialog(null, e1.getMessage() + " Lembre que eu sou case sensitive!!!!");
	    	  }
	      }
	      
	}	
}
