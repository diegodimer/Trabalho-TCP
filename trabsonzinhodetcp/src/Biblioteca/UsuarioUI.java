package Biblioteca;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class UsuarioUI extends JFrame implements ActionListener, UsuarioInterface {

	private JPanel contentPane;
	private DatabaseInterface dataBase;
	private Usuario user;

	// painel com alugueis ativos
	private JPanel panelComAlugueis;
	private JScrollPane scrollAlugueisAtivos;
	// ArrayList de alugueis ativos
	private	ArrayList<JCheckBox> listaAlugueisAtivos; //na função de devolver percorre essa lista procurando os selecionados

	// painel das buscas
	private JPanel paneldaBusca;
	private JScrollPane scrollBusca;
	private	ArrayList<JCheckBox> listaLivrosMarcadosParaAluguel; // na função de alugar percorre essa lista procurando os selecionados
	// pra ser editado em outra função nao pode ser de uma função
	private JTextField conteudoBuscado;

	// BOTÕES
	private JButton btnAlugarNovoLivro;
	private JButton btnDevolverLivro;
	private JButton btnHistoricoDeEmprestimos;
	private JButton btnBuscar;

	// RADIO BUTTONS
	private final ButtonGroup opcaoBusca = new ButtonGroup(); // grupo de botoes
	private JRadioButton rdbtnEditora;
	private JRadioButton rdbtnCategoria;
	private JRadioButton rdbtnNome;
	private JRadioButton rdbtnAutor;
	private JCheckBox chckbxNova;

	private JComboBox<String> seletorTipoDeLivro;
	private HashMap<Integer, String> listaDeLinksLivrosBuscados;
	/**
	  * Construtor para fazer o display do menu de usuario
	  * @param user objeto da classe Usuario
	  * @param database objeto da classe DatabaseInterface
	  */
	public UsuarioUI(Usuario user, DatabaseInterface dataBase) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UsuarioUI.class.getResource("/Biblioteca/Imagens/icone.png")));
		setResizable(false);
		setTitle("Interface do Usuario");
		this.dataBase = dataBase;
		this.user = user;

		try {
			dataBase.listaAlgueisdoUsuario(user);
		} catch (DatabaseInoperanteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollAlugueisAtivos = new JScrollPane();
		scrollAlugueisAtivos.setBounds(27, 250, 493, 118);
		contentPane.add(scrollAlugueisAtivos);

		panelComAlugueis = new JPanel();
		scrollAlugueisAtivos.setViewportView(panelComAlugueis);
		panelComAlugueis.setLayout(new BoxLayout(panelComAlugueis, BoxLayout.Y_AXIS));
		panelComAlugueis.setBackground(Color.WHITE);
		listaAlugueisAtivos();

		JLabel lblAlugueisAtivos = new JLabel("Alugueis Ativos");
		lblAlugueisAtivos.setFont(new Font("Monospaced", Font.PLAIN, 15));
		lblAlugueisAtivos.setBounds(27, 220, 184, 19);
		contentPane.add(lblAlugueisAtivos);

		JSeparator separator = new JSeparator();
		separator.setBounds(27, 210, 475, 29);
		contentPane.add(separator);

		btnAlugarNovoLivro = new JButton("Alugar novo livro");
		btnAlugarNovoLivro.addActionListener(this);
		btnAlugarNovoLivro.setBounds(27, 156, 151, 29);
		contentPane.add(btnAlugarNovoLivro);

		btnDevolverLivro = new JButton("Devolver livro");
		btnDevolverLivro.addActionListener(this);
		btnDevolverLivro.setBounds(27, 116, 151, 29);
		contentPane.add(btnDevolverLivro);

		btnHistoricoDeEmprestimos = new JButton("Hist\u00F3rico de empr\u00E9stimos");
		btnHistoricoDeEmprestimos.addActionListener(this);
		btnHistoricoDeEmprestimos.setBounds(27, 76, 151, 29);
		contentPane.add(btnHistoricoDeEmprestimos);

		scrollBusca = new JScrollPane();
		scrollBusca.setBounds(188, 65, 314, 125);
		contentPane.add(scrollBusca);

		paneldaBusca = new JPanel();
		scrollBusca.setViewportView(paneldaBusca);
		paneldaBusca.setLayout(new BoxLayout(paneldaBusca, BoxLayout.Y_AXIS));
		paneldaBusca.setBackground(Color.WHITE);

		listaLivrosMarcadosParaAluguel = new ArrayList<>();

		JLabel lblBuscarLivro = new JLabel("Buscar livro");
		lblBuscarLivro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBuscarLivro.setBounds(20, 9, 90, 19);
		contentPane.add(lblBuscarLivro);

		conteudoBuscado = new JTextField();
		conteudoBuscado.setBounds(101, 10, 235, 19);
		contentPane.add(conteudoBuscado);
		conteudoBuscado.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(331, 31, 89, 23);
		contentPane.add(btnBuscar);

		JPanel panelRadioButtons = new JPanel();
		panelRadioButtons.setBounds(27, 31, 309, 23);
		contentPane.add(panelRadioButtons);

		rdbtnNome = new JRadioButton("Nome");
		opcaoBusca.add(rdbtnNome);
		rdbtnNome.setHorizontalAlignment(SwingConstants.CENTER);
		panelRadioButtons.add(rdbtnNome);

		rdbtnAutor = new JRadioButton("Autor");
		rdbtnAutor.setMnemonic('0');
		opcaoBusca.add(rdbtnAutor);
		rdbtnAutor.setHorizontalAlignment(SwingConstants.CENTER);
		panelRadioButtons.add(rdbtnAutor);

		rdbtnEditora = new JRadioButton("Editora");
		rdbtnEditora.setMnemonic('1');
		opcaoBusca.add(rdbtnEditora);
		rdbtnEditora.setHorizontalAlignment(SwingConstants.CENTER);
		panelRadioButtons.add(rdbtnEditora);

		rdbtnCategoria = new JRadioButton("Categoria");
		rdbtnCategoria.setMnemonic('2');
		opcaoBusca.add(rdbtnCategoria);
		rdbtnCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		panelRadioButtons.add(rdbtnCategoria);

		// aqui é o menu dropdown
		seletorTipoDeLivro = new JComboBox<String>();
		seletorTipoDeLivro.setBackground(Color.WHITE);
		seletorTipoDeLivro.insertItemAt("Livro Físico", 0);
		seletorTipoDeLivro.insertItemAt("Livro Online", 1);
		seletorTipoDeLivro.setBounds(432, 34, 88, 20);
		contentPane.add(seletorTipoDeLivro);

		JLabel lblUsuario = new JLabel("Usuario: "+ user.getUsername() + " débito de: " + user.getDebito() + " reais" );
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblUsuario.setBounds(346, 13, 205, 14);
		contentPane.add(lblUsuario);


	}
	/**
	  * Metodo das ações que os botões performam no menu de usuario
	  * @param e objeto da classe ActionEvent
	  */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBuscar) {
			if(seletorTipoDeLivro.getSelectedIndex() == -1) {
				// por padrão quando nada é selecionado o index é -1
				JOptionPane.showMessageDialog(null, "Escolha um tipo de livro para buscar!");
			} 
			else
				if(rdbtnNome.isSelected()) {
					try {
						if(seletorTipoDeLivro.getSelectedIndex() == 0) {
							ArrayList<ExemplarFisico> resultadoBusca = dataBase.listaExemplaresDisponiveis(conteudoBuscado.getText());
							listaResultadoBuscaFisico(resultadoBusca);
						}
						else // quer lisar livros Online
						{
							ArrayList<ExemplarOnline> resultadoBusca = dataBase.listaExemplarOnlinePorTitulo(conteudoBuscado.getText());
							listaResultadoBuscaOnline(resultadoBusca);
						}
					} catch (DatabaseInoperanteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}

				}
				else if(rdbtnEditora.isSelected()) {
					try {
						if(seletorTipoDeLivro.getSelectedIndex() == 0) {
							ArrayList<ExemplarFisico> resultadoBusca = dataBase.listaExemplaresDisponiveis(conteudoBuscado.getText());
							listaResultadoBuscaFisico(resultadoBusca);
						}
						else // quer lisar livros Online
						{
							//	ArrayList<ExemplarOnline> resultadoBusca;
						}
					} catch (DatabaseInoperanteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				else if(rdbtnCategoria.isSelected()) {
					try {
						if(seletorTipoDeLivro.getSelectedIndex() == 0) {
							ArrayList<ExemplarFisico> resultadoBusca = dataBase.listaExemplaresDisponiveisPorCategoria(conteudoBuscado.getText());
							listaResultadoBuscaFisico(resultadoBusca);
						}
						else // quer lisar livros Online
						{
							//	ArrayList<ExemplarOnline> resultadoBusca;
						}
					} catch (DatabaseInoperanteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}

				}
				else if(rdbtnAutor.isSelected()){
					try {
						if(seletorTipoDeLivro.getSelectedIndex() == 0) {
							ArrayList<ExemplarFisico> resultadoBusca = dataBase.listaExemplaresDisponiveisPorAutor(conteudoBuscado.getText());
							listaResultadoBuscaFisico(resultadoBusca);
						}
						else // quer lisar livros Online
						{
							//	ArrayList<ExemplarOnline> resultadoBusca;
						}
					} catch (DatabaseInoperanteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Escolha um parametro para buscar!");
				}
		}
		else if (e.getSource() == btnAlugarNovoLivro ) {
			if(seletorTipoDeLivro.getSelectedIndex() == 0) {
				for(int i=0; i< listaLivrosMarcadosParaAluguel.size(); i++) {
					if(listaLivrosMarcadosParaAluguel.get(i).isSelected())
						alugarLivroFisico(listaLivrosMarcadosParaAluguel.get(i).getMnemonic());
				}
			}
			else // livro online
			{
				for(int i=0; i< listaLivrosMarcadosParaAluguel.size(); i++) {
					if(listaLivrosMarcadosParaAluguel.get(i).isSelected())
						alugarLivroOnline(listaLivrosMarcadosParaAluguel.get(i).getMnemonic());
				}
			}
		}else if(e.getSource() == btnDevolverLivro) {

			for(int i=0; i< listaAlugueisAtivos.size(); i++) {
				if(listaAlugueisAtivos.get(i).isSelected())
					devolveLivros(listaAlugueisAtivos.get(i).getMnemonic());
			}
			listaAlugueisAtivos();
		}
		else if(e.getSource() == btnHistoricoDeEmprestimos) {
			listaHistoricoDeAlugueis();
		}

	}

	/**
	  * Metodo para fazer o display do historico de alugueis (emprestimos e devolucoes) do usuario
	  */

	/* (non-Javadoc)
	 * @see Biblioteca.UsuarioInterface#listaHistoricoDeAlugueis()
	 */
	@Override
	public void listaHistoricoDeAlugueis() {

		JPanel historicoPanel = new JPanel();
		JScrollPane scrollHistorico = new JScrollPane();
		scrollHistorico.setBounds(27, 250, 431, 96);
		historicoPanel.add(scrollHistorico);
		historicoPanel.setLayout(new BoxLayout(historicoPanel, BoxLayout.Y_AXIS));
		historicoPanel.setBackground(Color.white);
		JTextArea textoHistorico = new JTextArea();
		textoHistorico.setEditable(false);

		StringBuilder strBuilder = new StringBuilder();
		ArrayList<ExemplarAlugado> historicoAlugueis = dataBase.listaExemplaresDevolvidos(user.getUserid());
		for(int i=0; i<historicoAlugueis.size(); i++) {
			strBuilder.append(historicoAlugueis.get(i).getNome() + " Emprestado em: " + historicoAlugueis.get(i).getDataEmprestimo()
					+ " Devolvido em: " + historicoAlugueis.get(i).getDataDevolucao() + "\n");
		}
		String stringHistorico = strBuilder.toString();
		textoHistorico.setText(stringHistorico);
		historicoPanel.add(textoHistorico);
		JOptionPane.showMessageDialog(null, historicoPanel, "Histórico: ", JOptionPane.PLAIN_MESSAGE);
	}

	/**
	  * Metodo para fazer o display da confirmacao da devolucao do livro e a devolucao em si
	  */
	/* (non-Javadoc)
	 * @see Biblioteca.UsuarioInterface#devolveLivros(int)
	 */
	@Override
	public void devolveLivros(int livroId) {
		try {
			dataBase.devolveLivroAlugado(user.getUserid(), livroId);
			JOptionPane.showMessageDialog(null, "Livro devolvido com sucesso!");
		}
		catch(DatabaseInoperanteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	/**
	  * Metodo para listar todos alugueis ativos do usuario
	  */
	/* (non-Javadoc)
	 * @see Biblioteca.UsuarioInterface#listaAlugueisAtivos()
	 */
	@Override
	public void listaAlugueisAtivos() {
		panelComAlugueis.removeAll();
		user.resetaAlugueis();
		ArrayList<ExemplarAlugado> alugueisDoUsuario = new ArrayList<ExemplarAlugado>();
		ExemplarAlugado livroAlugadoUsuario;
		try {
			dataBase.listaAlgueisdoUsuario(user);
			alugueisDoUsuario = user.getAlugueis();
		} catch (DatabaseInoperanteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		listaAlugueisAtivos = new ArrayList<>();
		for(int i=0; alugueisDoUsuario.size() > i; i++) {
			livroAlugadoUsuario = alugueisDoUsuario.get(i);
			chckbxNova = new JCheckBox("Nome: " + livroAlugadoUsuario.getNome() + "   Devolução em: " + livroAlugadoUsuario.getDataDevolucao() );
			chckbxNova.setMnemonic(livroAlugadoUsuario.getIdTitulo());
			listaAlugueisAtivos.add(chckbxNova);
			chckbxNova.setBackground(Color.WHITE);
			panelComAlugueis.add(chckbxNova);
		}
		scrollAlugueisAtivos.validate();
		scrollAlugueisAtivos.repaint();
		panelComAlugueis.validate();
		panelComAlugueis.repaint();

	}
	/**
	  * Metodo para listar o resultado da pesquisa por exemplares fisicos especificados
	  * @param listadeResultados lista de objetos da classe ExemplarFisico
	  */
	private void listaResultadoBuscaFisico(ArrayList<ExemplarFisico> listadeResultados) {

		paneldaBusca.removeAll(); // tira tudo que tem na busca
		JCheckBox checkBoxBusca;
		listaLivrosMarcadosParaAluguel.clear();

		for(int i=0; listadeResultados.size() > i; i++) {
			ExemplarFisico livroAchado= listadeResultados.get(i);
			checkBoxBusca = new JCheckBox("Nome: " + livroAchado.getNome() + 
					"      Autor: " + livroAchado.getAutor().getNome() + 
					"      Editora: " + livroAchado.getEditora().getNome() + 
					"      Numº Disponiveis: " + livroAchado.getNumDisponiveis());

			checkBoxBusca.setMnemonic(livroAchado.getIdTitulo());
			checkBoxBusca.setBackground(Color.WHITE);
			checkBoxBusca.setAlignmentX(LEFT_ALIGNMENT);
			checkBoxBusca.setAlignmentY(LEFT_ALIGNMENT);
			if(livroAchado.getNumDisponiveis()==0) {
				checkBoxBusca.setEnabled(false);
			}
			listaLivrosMarcadosParaAluguel.add(checkBoxBusca);
			paneldaBusca.add(checkBoxBusca);

		}
		paneldaBusca.validate();
		paneldaBusca.repaint();
		scrollBusca.validate();
		scrollBusca.repaint();
	}
	/**
	  * Metodo para listar o resultado da pesquisa por exemplares online especificados
	  * @param listadeResultados lista de objetos da classe ExemplarOnline
	  */
	private void listaResultadoBuscaOnline(ArrayList<ExemplarOnline> listadeResultados) {

		paneldaBusca.removeAll(); // tira tudo que tem na busca
		JCheckBox checkBoxBusca;
		listaLivrosMarcadosParaAluguel.clear();
		listaDeLinksLivrosBuscados = new HashMap<>();

		for(int i=0; listadeResultados.size() > i; i++) {
			ExemplarOnline livroAchado= listadeResultados.get(i);
			checkBoxBusca = new JCheckBox("Nome: " + livroAchado.getNome() + 
					"      Autor: " + livroAchado.getAutor().getNome() + 
					"      Editora: " + livroAchado.getEditora().getNome());
			listaDeLinksLivrosBuscados.put(livroAchado.getIdTitulo(), livroAchado.getLink());
			checkBoxBusca.setMnemonic(livroAchado.getIdTitulo());
			checkBoxBusca.setBackground(Color.WHITE);
			checkBoxBusca.setAlignmentX(LEFT_ALIGNMENT);
			checkBoxBusca.setAlignmentY(LEFT_ALIGNMENT);

			listaLivrosMarcadosParaAluguel.add(checkBoxBusca);
			paneldaBusca.add(checkBoxBusca);

		}
		paneldaBusca.validate();
		paneldaBusca.repaint();
		scrollBusca.validate();
		scrollBusca.repaint();
	}
	/**
	  * Metodo para alugar o exemplar fisico, adicionando ele pra lista de alugueis ativos do usuario
	  * @param idLivro id do titulo
	  */
	/* (non-Javadoc)
	 * @see Biblioteca.UsuarioInterface#alugarLivroFisico(int)
	 */
	@Override
	public void alugarLivroFisico(int idLivro) {
		try {
			Titulo livroParaAlugar = dataBase.findTitulo(idLivro);
			dataBase.adicionaAluguelAtivo(user.getUserid(), livroParaAlugar );
			listaAlugueisAtivos();
			JOptionPane.showMessageDialog(null, "Aluguel Realizado com Sucesso!!");
		} catch (DatabaseInoperanteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}
	/**
	  * Metodo para alugar o exemplar online, ofereçendo o link para consulta virtual
	  * @param idLivro id do titulo
	  */
	/* (non-Javadoc)
	 * @see Biblioteca.UsuarioInterface#alugarLivroOnline(int)
	 */
	@Override
	public void alugarLivroOnline(int idLivro) {
		JLabel lblLivro = new JLabel("Link do seu livro: ");
		JTextField linkField = new JTextField(150);
		JPanel myPanel = new JPanel();
		linkField.setEditable(false);
		linkField.setBackground(Color.WHITE);
		linkField.setText(listaDeLinksLivrosBuscados.get(idLivro));
		myPanel.add(lblLivro);
		myPanel.add(linkField);

		JOptionPane.showMessageDialog(null, myPanel, "Consulta livro virtual: ", JOptionPane.PLAIN_MESSAGE);
	}
}
