package Biblioteca;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class RegistroUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtNomeDeUsurio;
	private JTextField txtEmail;
	private JPasswordField pwdSenha;
	private JPasswordField pwdSenhaconfirmacao;
	private DatabaseInterface dataBase;
	// botao
	private JButton btnRegistrar;

	/**
	  * Construtor para fazer o display do menu de registro de novo usuario
	  *
	  */
	public RegistroUI() {
				
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroUI.class.getResource("/Biblioteca/Imagens/icone.png")));
		setResizable(false);
		this.dataBase = new Database();
		setTitle("Registrar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNomeDeUsurio = new JTextField();
		txtNomeDeUsurio.setBounds(83, 109, 222, 20);
		contentPane.add(txtNomeDeUsurio);
		txtNomeDeUsurio.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(83, 140, 222, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		pwdSenha = new JPasswordField();
		pwdSenha.setBounds(124, 171, 181, 20);
		contentPane.add(pwdSenha);
		
		pwdSenhaconfirmacao = new JPasswordField();
		pwdSenhaconfirmacao.setBounds(124, 202, 181, 20);
		contentPane.add(pwdSenhaconfirmacao);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(21, 112, 103, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(21, 143, 103, 14);
		contentPane.add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(21, 174, 103, 14);
		contentPane.add(lblSenha);
		
		JLabel lblConfirmeASenha = new JLabel("Confirme a senha:");
		lblConfirmeASenha.setBounds(21, 205, 103, 14);
		contentPane.add(lblConfirmeASenha);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(68, 233, 89, 23);
		btnRegistrar.addActionListener(this);
		contentPane.add(btnRegistrar);
		
		JLabel lblNovoUsurio = new JLabel("Novo usu\u00E1rio");
		lblNovoUsurio.setFont(new Font("Monospaced", Font.PLAIN, 28));
		lblNovoUsurio.setBounds(10, 0, 265, 65);
		contentPane.add(lblNovoUsurio);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(186, 233, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(RegistroUI.class.getResource("/Biblioteca/Imagens/kakashi_lendo_um_livro.png")));
		lblNewLabel.setBounds(210, 0, 95, 112);
		contentPane.add(lblNewLabel);
	}
	/**
	  * Metodo das a��es que os bot�es performam no menu de registro
	  * @param e objeto da classe ActionEvent
	  */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRegistrar) {
			
			StringBuilder senha = new StringBuilder();
			senha.append(pwdSenha.getPassword());
			
			StringBuilder confirmacaoSenha = new StringBuilder();
			confirmacaoSenha.append(pwdSenhaconfirmacao.getPassword());
			if( ! (senha.toString().intern() == confirmacaoSenha.toString().intern() ) )
				JOptionPane.showMessageDialog(null, "Senhas n�o conferem!");
			else if(txtEmail.getText().isEmpty() || txtNomeDeUsurio.getText().isEmpty() || senha.toString().intern().isEmpty())
				JOptionPane.showMessageDialog(null, "Todos os campos s�o obrigat�rios!"); 
				else if(!(txtEmail.getText().contains("@") && ( txtEmail.getText().contains(".com") || txtEmail.getText().contains(".br") )))
					JOptionPane.showMessageDialog(null, "Isso n�o parece um e-mail..."); 
				else if(txtNomeDeUsurio.getText().length() < 5 || txtNomeDeUsurio.getText().length() > 30 )
				JOptionPane.showMessageDialog(null, "Nome de usu�rio entre 5 e 30 caracteres!");
				else {
					try{
						dataBase.addUser(new Usuario(txtNomeDeUsurio.getText(), senha.toString(), txtEmail.getText()));
						JOptionPane.showMessageDialog(null, "Usu�rio registrado com sucesso!");
						this.dispose();
					}
					catch(DatabaseInoperanteException e2)
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
		
			
				}
		}else
			this.dispose();
		
	}
}
