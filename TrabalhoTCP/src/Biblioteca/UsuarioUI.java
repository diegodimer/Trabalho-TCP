package Biblioteca;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class UsuarioUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Database dataBase;
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
	
	
	
	public UsuarioUI(Usuario user, Database dataBase) {
		setResizable(false);
		setTitle("Interface do Usuario");
		this.dataBase = dataBase;
		this.user = user;
		
		try {
			dataBase.listaAlgueisdoUsuario(user);
		} catch (DatabaseInoperanteException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollAlugueisAtivos = new JScrollPane();
		scrollAlugueisAtivos.setBounds(27, 250, 431, 96);
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
		separator.setBounds(27, 210, 431, 29);
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
		scrollBusca.setBounds(188, 65, 274, 131);
		contentPane.add(scrollBusca);
		
		paneldaBusca = new JPanel();
		scrollBusca.setViewportView(paneldaBusca);
		paneldaBusca.setLayout(new BoxLayout(paneldaBusca, BoxLayout.Y_AXIS));
		paneldaBusca.setBackground(Color.WHITE);
		
		listaLivrosMarcadosParaAluguel = new ArrayList<>();
		
		JLabel lblBuscarLivro = new JLabel("Buscar livro");
		lblBuscarLivro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBuscarLivro.setBounds(45, 9, 90, 19);
		contentPane.add(lblBuscarLivro);
		
		conteudoBuscado = new JTextField();
		conteudoBuscado.setBounds(153, 10, 212, 19);
		contentPane.add(conteudoBuscado);
		conteudoBuscado.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(369, 9, 89, 23);
		contentPane.add(btnBuscar);
		
		JPanel panelRadioButtons = new JPanel();
		panelRadioButtons.setBounds(55, 31, 355, 23);
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
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBuscar) {
			if(rdbtnNome.isSelected()) {
				try {
					ArrayList<ExemplarFisico> resultadoBusca = dataBase.listaExemplaresDisponiveis(conteudoBuscado.getText());
					listaResultadoBusca(resultadoBusca);
				} catch (DatabaseInoperanteException | SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
			else if(rdbtnEditora.isSelected()) {
				
			}
			else if(rdbtnCategoria.isSelected()) {
				
			}
			else if(rdbtnAutor.isSelected()){
			}
			else {
				JOptionPane.showMessageDialog(null, "Escolha um parametro para buscar!");
			}
		}
	}
	void listaAlugueisAtivos() {
		ArrayList<ExemplarAlugado> alugueisDoUsuario = new ArrayList<ExemplarAlugado>();
		ExemplarAlugado livroAlugadoUsuario;
		try {
			dataBase.listaAlgueisdoUsuario(user);
			alugueisDoUsuario = user.getAlugueis();
		} catch (DatabaseInoperanteException | SQLException e) {
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
	
	void listaResultadoBusca(ArrayList<ExemplarFisico> listadeResultados) {
		
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
}
