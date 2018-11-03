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
	private JButton btnEncontraCategoria;
	private JButton btnAdicionaTitulo;
	private JButton btnAdicionaExemplarFisico;
	private JButton btnAdicionaExemplarOnline;
	private JButton btnEncontraTituloOnline;
	private JButton btnEncontraTituloFisico;
	private JButton btnEncontraEditora;
	
	public FuncionarioUI(Usuario user, Database dataBase) {
		setResizable(false);
		this.dataBase = dataBase;
		this.user = user;
		setTitle("Biblioteca manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 217, 330);
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
		btnAdicionaCategoria.setBounds(15, 53, 174, 23);
		contentPane.add(btnAdicionaCategoria);
		
		btnEncontraCategoria = new JButton("Encontra Categoria");
		btnEncontraCategoria.addActionListener(this);
		btnEncontraCategoria.setBounds(15, 79, 174, 23);
		contentPane.add(btnEncontraCategoria);
		
		btnAdicionaTitulo = new JButton("Adiciona Titulo");
		btnAdicionaTitulo.addActionListener(this);
		btnAdicionaTitulo.setBounds(15, 157, 174, 23);
		contentPane.add(btnAdicionaTitulo);
		
		btnAdicionaExemplarFisico = new JButton("Adiciona Exemplar Fisico");
		btnAdicionaExemplarFisico.addActionListener(this);
		btnAdicionaExemplarFisico.setBounds(15, 183, 174, 23);
		contentPane.add(btnAdicionaExemplarFisico);
		
		btnAdicionaExemplarOnline = new JButton("Adiciona Exemplar Online");
		btnAdicionaExemplarOnline.addActionListener(this);
		btnAdicionaExemplarOnline.setBounds(15, 206, 174, 23);
		contentPane.add(btnAdicionaExemplarOnline);
		
		btnEncontraTituloOnline = new JButton("Encontra Titulo Online");
		btnEncontraTituloOnline.addActionListener(this);
		btnEncontraTituloOnline.setBounds(15, 232, 174, 23);
		contentPane.add(btnEncontraTituloOnline);
		
		btnEncontraTituloFisico = new JButton("Encontra Titulo Fisico");
		btnEncontraTituloFisico.addActionListener(this);
		btnEncontraTituloFisico.setBounds(15, 258, 174, 23);
		contentPane.add(btnEncontraTituloFisico);
		
		btnEncontraEditora = new JButton("Encontra Editora");
		btnEncontraEditora.addActionListener(this);
		btnEncontraEditora.setBounds(15, 131, 174, 23);
		contentPane.add(btnEncontraEditora);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnMakeAdm) 
			tornaUserADM();
		else if(e.getSource() == btnAdicionaEditora)
			adicionaEditora();
			else if(e.getSource() == btnAdicionaCategoria)
				adicionaCategoria();
				else if(e.getSource() == btnEncontraCategoria )
					encontraCategoria();
					else if(e.getSource() == btnAdicionaTitulo)
						adicionaTitulo();
						else if(e.getSource() == btnAdicionaExemplarFisico)
							adicionaExemplarFisico();
							else if(e.getSource() == btnAdicionaExemplarOnline)
								adicionaExemplarOnline();
								else if(e.getSource() == btnEncontraTituloOnline)
									encontraTituloOnline();
									else if(e.getSource() == btnEncontraTituloFisico)
										encontraTituloFisico();
										else if(e.getSource() == btnEncontraEditora )
											encontraEditora();
		


		
	}
	
	private void encontraTituloFisico() {
		JOptionPane.showMessageDialog(null, "encontra titulo fisico");
	}
	private void encontraTituloOnline() {
		JOptionPane.showMessageDialog(null, "encontra titulo online");
		
	}
	private void adicionaExemplarOnline() {
		JOptionPane.showMessageDialog(null, "adiciona exemplar online");
		
	}
	private void adicionaExemplarFisico() {
		JOptionPane.showMessageDialog(null, "adiciona exemplar fisico");
		
	}
	private void adicionaTitulo() {
		JOptionPane.showMessageDialog(null, "adiciona titulo");
		
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
	    

	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Por favor, entre com NOME, AUTOR e EDITORA", JOptionPane.OK_CANCEL_OPTION);
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
	private void encontraCategoria() {
		JOptionPane.showMessageDialog(null, "encontra categoria");
		
	}
	private void adicionaCategoria() {
		JOptionPane.showMessageDialog(null, "adiciona Categoria");
	}
	private void encontraEditora() {
		JOptionPane.showMessageDialog(null, "encontra editora");
		
	}
	private void adicionaEditora() {
		JOptionPane.showMessageDialog(null, "adiciona editora");
		
	}
	private void tornaUserADM() {
		String nomeAdm;
		nomeAdm = JOptionPane.showInputDialog("Qual o nome do usuário?");
		try {
		dataBase.tornaUserAdm(nomeAdm);
		JOptionPane.showMessageDialog(null, "Operação concluida!");
		}
		catch(DatabaseInoperanteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}	
	
	
		
	
}
