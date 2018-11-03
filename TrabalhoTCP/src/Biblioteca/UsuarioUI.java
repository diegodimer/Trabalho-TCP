package Biblioteca;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.RootPaneContainer;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;

@SuppressWarnings("serial")
public class UsuarioUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Database dataBase;
	private Usuario user;
	
	// pra ser editado em outra função nao pode ser de uma função
	private JTextField conteudoBuscado;
	
	// BOTÕES
	private JButton btnAlugarNovoLivro;
	private JButton btnDevolverLivro;
	private JButton btnHistoricoDeEmprestimos;
	private JButton btnBuscar;
	private JTextArea resultadoBusca;
	
	// RADIO BUTTONS
	private final ButtonGroup opcaoBusca = new ButtonGroup(); // grupo de botoes
	private JRadioButton rdbtnEditora;
	private JRadioButton rdbtnCategoria;
	
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
		setBounds(100, 100, 474, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 241, 414, 72);
		contentPane.add(scrollPane);
		
		JPanel panelComAlugueis = new JPanel();
		scrollPane.setViewportView(panelComAlugueis);
		
		JTextArea listadeAlugueis = new JTextArea();
		listadeAlugueis.setEditable(false);
		panelComAlugueis.add(listadeAlugueis);
		
		
		listadeAlugueis.setText(user.listaAlugueisAtivos());
		
		JLabel lblAlugueisAtivos = new JLabel("Alugueis Ativos");
		lblAlugueisAtivos.setFont(new Font("Monospaced", Font.PLAIN, 15));
		lblAlugueisAtivos.setBounds(10, 220, 184, 19);
		contentPane.add(lblAlugueisAtivos);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(27, 210, 376, 29);
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
		
		JScrollPane scrollBusca = new JScrollPane();
		scrollBusca.setBounds(199, 65, 225, 120);
		contentPane.add(scrollBusca);
		
		JPanel paneldaBusca = new JPanel();
		scrollBusca.setViewportView(paneldaBusca);
		
		resultadoBusca = new JTextArea();
		resultadoBusca.setEditable(false);
		paneldaBusca.add(resultadoBusca);
		
		JLabel lblBuscarLivro = new JLabel("Buscar livro");
		lblBuscarLivro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBuscarLivro.setBounds(27, 11, 73, 14);
		contentPane.add(lblBuscarLivro);
		
		conteudoBuscado = new JTextField();
		conteudoBuscado.setBounds(10, 40, 125, 19);
		contentPane.add(conteudoBuscado);
		conteudoBuscado.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(359, 30, 89, 23);
		contentPane.add(btnBuscar);
		
		JPanel panelRadioButtons = new JPanel();
		panelRadioButtons.setBounds(142, 30, 223, 29);
		contentPane.add(panelRadioButtons);
		
		JRadioButton rdbtnAutor = new JRadioButton("Autor");
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
			if(rdbtnEditora.isSelected()) {
				
			}
			else if(rdbtnCategoria.isSelected()) {
				
			}
			else { // é pra procurar o autor
				
			}
		}
	}
}
