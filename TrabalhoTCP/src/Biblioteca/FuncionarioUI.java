package Biblioteca;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class FuncionarioUI extends JFrame implements ActionListener, FuncionarioInterface {

	private JPanel contentPane;
	private DatabaseInterface dataBase;
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
	private JButton btnBuscarTitulo;
	private JTextField CampoId;
	private JScrollPane scrollTitulos;
	private JPanel panelTitulos;
	private JTextField CampoNomeTitulo;

	private JButton btnDeletarEditora2;
	private JButton btnBuscarEditora;
	private JTextField CampoIdEditora;
	private JScrollPane scrollEditora;
	private JPanel panelEditora;
	private JTextField CampoNomeEditora;

	private JButton btnDeletarAutor2;
	private JButton btnBuscarAutor;
	private JTextField CampoIdAutor;
	private JScrollPane scrollAutor;
	private JPanel panelAutor;
	private JTextField CampoNomeAutor;

	private JButton btnDeletarUsuario2;
	private JButton btnBuscarUsuario;
	private JTextField CampoIdUsuario;
	private JScrollPane scrollUsuario;
	private JPanel panelUsuario;
	private JTextField CampoNomeUsuario;

	public FuncionarioUI(Usuario user) {
		setResizable(false);
		this.dataBase = new Database();
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
		else if(e.getSource() == btnBuscarTitulo)
			mostrarIdTitulos();
		else if(e.getSource() == btnBuscarEditora)
			mostrarIdEditoras();
		else if(e.getSource() == btnDeletarEditora2)
			deletarEditora2();
		else if(e.getSource() == btnDeletarAutor2)
			deletarAutor2();
		else if(e.getSource() == btnBuscarAutor)
			mostrarIdAutores();
		else if(e.getSource() == btnBuscarUsuario)
			mostrarIdUsuarios();
		else if(e.getSource() == btnDeletarUsuario2)
			deletarUsuario2();

	}

	/* (non-Javadoc)
	 * @see Biblioteca.FuncionarioInterface#deletarTitulo()
	 */
	@Override
	public void deletarTitulo() {
		JFrame janela = new JFrame("Deletar Titulo");
		janela.setBounds(100,100,300,400);

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

		scrollTitulos = new JScrollPane();
		scrollTitulos.setBounds(20,100,240,240);
		Painel.add(scrollTitulos);

		panelTitulos = new JPanel();
		scrollTitulos.setViewportView(panelTitulos);
		panelTitulos.setLayout(new BoxLayout(panelTitulos, BoxLayout.Y_AXIS));
		panelTitulos.setBackground(Color.WHITE);

		JLabel LabelBuscar = new JLabel("Nome do livro:");
		LabelBuscar.setBounds(20,70,100,23);
		Painel.add(LabelBuscar);

		CampoNomeTitulo = new JTextField(12);
		CampoNomeTitulo.setBounds(95,70,75,23);
		Painel.add(CampoNomeTitulo);

		btnBuscarTitulo = new JButton("Buscar ID");
		btnBuscarTitulo.setBounds(180,70,80,23);
		btnBuscarTitulo.addActionListener(this);
		Painel.add(btnBuscarTitulo);

		/*
		setBounds(100, 100, 489, 300);
		Painel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JTextField campoID = new JTextField(5);
		Painel.add(new JLabel("Id:"));
		Painel.add(campoID);
		 */
		janela.setVisible(true);

	}
	private void mostrarIdTitulos() {
		String titulo = CampoNomeTitulo.getText();
		try {
			ArrayList<Titulo> lista = dataBase.listaTitulos(titulo);
			JList list = new JList(lista.toArray());

			panelTitulos.removeAll();
			scrollTitulos = new JScrollPane(list);
			scrollTitulos.setBounds(20,100,240,240);
			panelTitulos.add(scrollTitulos);
			scrollTitulos.validate();
			scrollTitulos.repaint();
			panelTitulos.validate();
			panelTitulos.repaint();


		} catch (DatabaseInoperanteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void deletarTitulo2(){
		String titulo = CampoId.getText();
		if(dataBase.removeTitulo(Integer.parseInt(titulo))) {
			JOptionPane.showMessageDialog(null, "Titulo removido!");
		}		
	}

	/* (non-Javadoc)
	 * @see Biblioteca.FuncionarioInterface#deletarEditora()
	 */
	@Override
	public void deletarEditora() {
		JFrame janela = new JFrame("Deletar Editora");
		janela.setBounds(100,100,300,400);

		JPanel Painel = new JPanel();
		janela.add(Painel);

		Painel.setLayout(null);
		JLabel Labeltitulo = new JLabel("Id:");
		Labeltitulo.setBounds(20,20,150,23);
		Painel.add(Labeltitulo);

		CampoIdEditora = new JTextField(12);
		CampoIdEditora.setBounds(40,20,45,23);
		Painel.add(CampoIdEditora);

		btnDeletarEditora2 = new JButton("Deletar");
		btnDeletarEditora2.setBounds(95,20,80,23);
		btnDeletarEditora2.addActionListener(this);
		Painel.add(btnDeletarEditora2);

		scrollEditora = new JScrollPane();
		scrollEditora.setBounds(20,100,240,240);
		Painel.add(scrollEditora);

		panelEditora = new JPanel();
		scrollEditora.setViewportView(panelEditora);
		panelEditora.setLayout(new BoxLayout(panelEditora, BoxLayout.Y_AXIS));
		panelEditora.setBackground(Color.WHITE);

		JLabel LabelBuscar = new JLabel("Nome da editora:");
		LabelBuscar.setBounds(20,70,100,23);
		Painel.add(LabelBuscar);

		CampoNomeEditora = new JTextField(12);
		CampoNomeEditora.setBounds(105,70,65,23);
		Painel.add(CampoNomeEditora);

		btnBuscarEditora = new JButton("Buscar ID");
		btnBuscarEditora.setBounds(180,70,80,23);
		btnBuscarEditora.addActionListener(this);
		Painel.add(btnBuscarEditora);

		janela.setVisible(true);

	}
	private void deletarEditora2(){
		String nome = CampoIdEditora.getText();
		if(dataBase.removeEditora(Integer.parseInt(nome))) {
			JOptionPane.showMessageDialog(null, "Editora removida!");
		}		
	}
	private void mostrarIdEditoras() {
		String nome = CampoNomeEditora.getText();
		try {
			ArrayList<Editora> lista = dataBase.listaEditoras(nome);
			JList list = new JList(lista.toArray());

			panelEditora.removeAll();
			scrollEditora = new JScrollPane(list);
			scrollEditora.setBounds(20,100,240,240);
			panelEditora.add(scrollEditora);
			scrollEditora.validate();
			scrollEditora.repaint();
			panelEditora.validate();
			panelEditora.repaint();


		} catch (DatabaseInoperanteException e) {
			e.printStackTrace();
		}
	}


	/* (non-Javadoc)
	 * @see Biblioteca.FuncionarioInterface#deletarAutor()
	 */
	@Override
	public void deletarAutor() {
		JFrame janela = new JFrame("Deletar Autor");
		janela.setBounds(100,100,300,400);

		JPanel Painel = new JPanel();
		janela.add(Painel);

		Painel.setLayout(null);
		JLabel LabelAutor = new JLabel("Id:");
		LabelAutor.setBounds(20,20,150,23);
		Painel.add(LabelAutor);

		CampoIdAutor = new JTextField(12);
		CampoIdAutor.setBounds(40,20,45,23);
		Painel.add(CampoIdAutor);

		btnDeletarAutor2 = new JButton("Deletar");
		btnDeletarAutor2.setBounds(95,20,80,23);
		btnDeletarAutor2.addActionListener(this);
		Painel.add(btnDeletarAutor2);

		scrollAutor = new JScrollPane();
		scrollAutor.setBounds(20,100,240,240);
		Painel.add(scrollAutor);

		panelAutor = new JPanel();
		scrollAutor.setViewportView(panelAutor);
		panelAutor.setLayout(new BoxLayout(panelAutor, BoxLayout.Y_AXIS));
		panelAutor.setBackground(Color.WHITE);

		JLabel LabelBuscar = new JLabel("Nome do autor:");
		LabelBuscar.setBounds(20,70,100,23);
		Painel.add(LabelBuscar);

		CampoNomeAutor = new JTextField(12);
		CampoNomeAutor.setBounds(105,70,65,23);
		Painel.add(CampoNomeAutor);

		btnBuscarAutor = new JButton("Buscar ID");
		btnBuscarAutor.setBounds(180,70,80,23);
		btnBuscarAutor.addActionListener(this);
		Painel.add(btnBuscarAutor);

		janela.setVisible(true);

	}
	private void deletarAutor2(){
		String nome = CampoIdAutor.getText();
		if(dataBase.removeAutor(Integer.parseInt(nome))) {
			JOptionPane.showMessageDialog(null, "Autor removido!");
		}		
	}
	private void mostrarIdAutores() {
		String nome = CampoNomeAutor.getText();
		try {
			ArrayList<Autor> lista = dataBase.listaAutores(nome);
			JList list = new JList(lista.toArray());

			panelAutor.removeAll();
			scrollAutor = new JScrollPane(list);
			scrollAutor.setBounds(20,100,240,240);
			panelAutor.add(scrollAutor);
			scrollAutor.validate();
			scrollAutor.repaint();
			panelAutor.validate();
			panelAutor.repaint();


		} catch (DatabaseInoperanteException e) {
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see Biblioteca.FuncionarioInterface#deletarUsuario()
	 */
	@Override
	public void deletarUsuario() {
		JFrame janela = new JFrame("Deletar Usuario");
		janela.setBounds(100,100,300,400);

		JPanel Painel = new JPanel();
		janela.add(Painel);

		Painel.setLayout(null);
		JLabel LabelUsuario = new JLabel("Id:");
		LabelUsuario.setBounds(20,20,150,23);
		Painel.add(LabelUsuario);

		CampoIdUsuario = new JTextField(12);
		CampoIdUsuario.setBounds(40,20,45,23);
		Painel.add(CampoIdUsuario);

		btnDeletarUsuario2 = new JButton("Deletar");
		btnDeletarUsuario2.setBounds(95,20,80,23);
		btnDeletarUsuario2.addActionListener(this);
		Painel.add(btnDeletarUsuario2);

		scrollUsuario = new JScrollPane();
		scrollUsuario.setBounds(20,100,240,240);
		Painel.add(scrollUsuario);

		panelUsuario = new JPanel();
		scrollUsuario.setViewportView(panelUsuario);
		panelUsuario.setLayout(new BoxLayout(panelUsuario, BoxLayout.Y_AXIS));
		panelUsuario.setBackground(Color.WHITE);

		JLabel LabelBuscar = new JLabel("Nome do usuario:");
		LabelBuscar.setBounds(20,70,100,23);
		Painel.add(LabelBuscar);

		CampoNomeUsuario = new JTextField(12);
		CampoNomeUsuario.setBounds(105,70,65,23);
		Painel.add(CampoNomeUsuario);

		btnBuscarUsuario = new JButton("Buscar ID");
		btnBuscarUsuario.setBounds(180,70,80,23);
		btnBuscarUsuario.addActionListener(this);
		Painel.add(btnBuscarUsuario);

		janela.setVisible(true);

	}
	private void deletarUsuario2(){
		String nome = CampoIdUsuario.getText();
		if(dataBase.removeUsuario(Integer.parseInt(nome))) {
			JOptionPane.showMessageDialog(null, "Usuario removido!");
		}		
	}
	private void mostrarIdUsuarios() {
		String nome = CampoNomeUsuario.getText();
		try {
			ArrayList<Usuario> lista = dataBase.listaUsuarios(nome);
			JList list = new JList(lista.toArray());

			panelUsuario.removeAll();
			scrollUsuario = new JScrollPane(list);
			scrollUsuario.setBounds(20,100,240,240);
			panelUsuario.add(scrollUsuario);
			scrollUsuario.validate();
			scrollUsuario.repaint();
			panelUsuario.validate();
			panelUsuario.repaint();


		} catch (DatabaseInoperanteException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see Biblioteca.FuncionarioInterface#adicionaExemplarFisico()
	 */
	@Override
	public void adicionaExemplarFisico() {
		JFrame janela = new JFrame("Adicionar Exemplar Fisico");
		janela.setBounds(100,100,300,400);

		JPanel Painel = new JPanel();
		janela.add(Painel);

		Painel.setLayout(null);
		JLabel Labeltitulo = new JLabel("Id:");
		Labeltitulo.setBounds(20,20,150,23);
		Painel.add(Labeltitulo);

		CampoId = new JTextField(12);
		CampoId.setBounds(40,20,45,23);
		Painel.add(CampoId);

		JLabel labelQntd = new JLabel("Quantidade:");
		labelQntd.setBounds(100,20,150,23);
		Painel.add(labelQntd);

		JTextField campoQntd = new JTextField(12);
		campoQntd.setBounds(160,20,45,23);
		Painel.add(campoQntd);


		JButton btnAdicionarExemplarFisico = new JButton("Adicionar");
		btnAdicionarExemplarFisico.setBounds(80,45,80,23);
		btnAdicionarExemplarFisico.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				try {
					dataBase.addExemplarFisico(Integer.parseInt(CampoId.getText()), Integer.parseInt(campoQntd.getText()));
					JOptionPane.showMessageDialog(null, "Exemplar Físico adicionado com sucesso!");
				} catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			} 
		} );
		Painel.add(btnAdicionarExemplarFisico);

		scrollTitulos = new JScrollPane();
		scrollTitulos.setBounds(20,100,240,240);
		Painel.add(scrollTitulos);

		panelTitulos = new JPanel();
		scrollTitulos.setViewportView(panelTitulos);
		panelTitulos.setLayout(new BoxLayout(panelTitulos, BoxLayout.Y_AXIS));
		panelTitulos.setBackground(Color.WHITE);

		JLabel LabelBuscar = new JLabel("Nome do livro:");
		LabelBuscar.setBounds(20,70,100,23);
		Painel.add(LabelBuscar);

		CampoNomeTitulo = new JTextField(12);
		CampoNomeTitulo.setBounds(95,70,75,23);
		Painel.add(CampoNomeTitulo);

		btnBuscarTitulo = new JButton("Buscar ID");
		btnBuscarTitulo.setBounds(180,70,80,23);
		btnBuscarTitulo.addActionListener(this);
		Painel.add(btnBuscarTitulo);

		janela.setVisible(true);


	}
	/* (non-Javadoc)
	 * @see Biblioteca.FuncionarioInterface#adicionaExemplarOnline()
	 */
	@Override
	public void adicionaExemplarOnline() {
		JFrame janela = new JFrame("Adicionar Exemplar Online");
		janela.setBounds(100,100,300,400);

		JPanel Painel = new JPanel();
		janela.add(Painel);

		Painel.setLayout(null);
		JLabel Labeltitulo = new JLabel("Id:");
		Labeltitulo.setBounds(20,20,150,23);
		Painel.add(Labeltitulo);

		CampoId = new JTextField(12);
		CampoId.setBounds(40,20,45,23);
		Painel.add(CampoId);

		JLabel labelLink = new JLabel("Link:");
		labelLink.setBounds(100,20,150,23);
		Painel.add(labelLink);

		JTextField campoLink = new JTextField(12);
		campoLink.setBounds(160,20,45,23);
		Painel.add(campoLink);


		JButton btnAdicionarExemplarFisico = new JButton("Adicionar");
		btnAdicionarExemplarFisico.setBounds(80,45,80,23);
		btnAdicionarExemplarFisico.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				try {
					dataBase.addExemplarOnline(Integer.parseInt(CampoId.getText()), campoLink.getText());
					JOptionPane.showMessageDialog(null, "Exemplar Online adicionado com sucesso!");
				} catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			} 
		} );
		Painel.add(btnAdicionarExemplarFisico);

		scrollTitulos = new JScrollPane();
		scrollTitulos.setBounds(20,100,240,240);
		Painel.add(scrollTitulos);

		panelTitulos = new JPanel();
		scrollTitulos.setViewportView(panelTitulos);
		panelTitulos.setLayout(new BoxLayout(panelTitulos, BoxLayout.Y_AXIS));
		panelTitulos.setBackground(Color.WHITE);

		JLabel LabelBuscar = new JLabel("Nome do livro:");
		LabelBuscar.setBounds(20,70,100,23);
		Painel.add(LabelBuscar);

		CampoNomeTitulo = new JTextField(12);
		CampoNomeTitulo.setBounds(95,70,75,23);
		Painel.add(CampoNomeTitulo);

		btnBuscarTitulo = new JButton("Buscar ID");
		btnBuscarTitulo.setBounds(180,70,80,23);
		btnBuscarTitulo.addActionListener(this);
		Painel.add(btnBuscarTitulo);

		janela.setVisible(true);




	}




	/* (non-Javadoc)
	 * @see Biblioteca.FuncionarioInterface#adicionaTitulo()
	 */
	@Override
	public void adicionaTitulo() {
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
			catch(DatabaseInoperanteException e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage() + " Lembre que eu sou case sensitive!!!!");
			}
		}



	}
	/* (non-Javadoc)
	 * @see Biblioteca.FuncionarioInterface#adicionaCategoria()
	 */
	@Override
	public void adicionaCategoria() {
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


	/* (non-Javadoc)
	 * @see Biblioteca.FuncionarioInterface#adicionaEditora()
	 */
	@Override
	public void adicionaEditora() {
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

	/* (non-Javadoc)
	 * @see Biblioteca.FuncionarioInterface#adicionarAutor()
	 */
	@Override
	public void adicionarAutor() {
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

	/* (non-Javadoc)
	 * @see Biblioteca.FuncionarioInterface#tornaUserADM()
	 */
	@Override
	public void tornaUserADM() {

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
			catch(DatabaseInoperanteException e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage() + " Lembre que eu sou case sensitive!!!!");
			}
		}

	}	
}
