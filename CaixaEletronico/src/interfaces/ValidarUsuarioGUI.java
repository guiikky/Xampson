package interfaces;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import idioma.Idioma;
import util.Acesso;

@SuppressWarnings("serial")
public class ValidarUsuarioGUI extends JFrame implements ActionListener, ItemListener {
	private JLabel lAgencia, lConta, lSenha;
	private JTextField tAgencia, tConta;
	private JPasswordField pSenha;
	private JComboBox<String> caixa;
	private Container c;
	private JPanel pai, mae;
	private JButton proximo;
	private String nomes[] = { "Português", "English", "Español" };
	private ResourceBundle bn;

	// Metodo construtor
	public ValidarUsuarioGUI() {
		super("Caixa Eletrônico");
		c = getContentPane();
		c.setLayout(new BorderLayout());
		bn = ResourceBundle.getBundle("idioma.internacionalizarProjeto", new Locale("pt", "BR"));

		lAgencia = new JLabel("Agência");
		lConta = new JLabel("Conta");
		lSenha = new JLabel("Senha");
		tAgencia = new JTextField(10);
		tConta = new JTextField(10);
		pSenha = new JPasswordField(10);
		proximo = new JButton("Próximo");
		proximo.addActionListener(this);
		caixa = new JComboBox<String>(nomes);
		caixa.addItemListener(this);

		pai = new JPanel();
		pai.setLayout(new GridLayout(3, 2));
		mae = new JPanel();
		mae.setLayout(new FlowLayout());

		pai.add(lAgencia);
		pai.add(tAgencia);
		pai.add(lConta);
		pai.add(tConta);
		pai.add(lSenha);
		pai.add(pSenha);

		mae.add(proximo);
		mae.add(caixa);

		c.add(pai, BorderLayout.CENTER);
		c.add(mae, BorderLayout.SOUTH);

		setResizable(false);
		setSize(300, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	// Tratamento do botao
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == proximo) {
				int conta = Integer.parseInt(tConta.getText());
				String senh = new String(pSenha.getPassword());
				int senha = Integer.parseInt(senh);
				int agencia = Integer.parseInt(tAgencia.getText());
				new Idioma(bn);
				new Acesso(conta, senha, agencia);
				dispose();
			}
		} catch (Exception erro) {
			erro.printStackTrace();
		}
	}

	// Tratamento do JCombBox, escolha do idioma (Requisito)
	public void itemStateChanged(ItemEvent e) {
		if (caixa.getSelectedIndex() == 0) {
			bn = ResourceBundle.getBundle("idioma.internacionalizarProjeto", new Locale("pt", "BR"));
			lAgencia.setText(bn.getString("validarUsuario.agencia"));
			lConta.setText(bn.getString("validarUsuario.Conta"));
			lSenha.setText(bn.getString("validarUsuario.senha"));
			proximo.setText(bn.getString("validarUsuario.prox"));
			setTitle(bn.getString("validarUsuario.super"));
		}

		if (caixa.getSelectedIndex() == 1) {
			bn = ResourceBundle.getBundle("idioma.internacionalizarProjeto", new Locale("en", "US"));
			lAgencia.setText(bn.getString("validarUsuario.agencia"));
			lConta.setText(bn.getString("validarUsuario.Conta"));
			lSenha.setText(bn.getString("validarUsuario.senha"));
			proximo.setText(bn.getString("validarUsuario.prox"));
			setTitle(bn.getString("validarUsuario.super"));
		}

		if (caixa.getSelectedIndex() == 2) {
			bn = ResourceBundle.getBundle("idioma.internacionalizarProjeto", new Locale("es", "ES"));
			lAgencia.setText(bn.getString("validarUsuario.agencia"));
			lConta.setText(bn.getString("validarUsuario.Conta"));
			lSenha.setText(bn.getString("validarUsuario.senha"));
			proximo.setText(bn.getString("validarUsuario.prox"));
			setTitle(bn.getString("validarUsuario.super"));
		}

	}

	public static void main(String args[]) {
		new ValidarUsuarioGUI();
	}
}

/*
 * 2145987/9630/9999/000/false 2385998/7845/9999/123/false 2395784/2389/9999/-1
 * /false 2398468/4748/9999/-1 /false 2587415/5689/9999/333/true
 * 
 * 
 * 
 * 
 * -44 -85 -80 83 -29 -105 -106 -37 39 118 -50 -94 43 17 32 120 -103 30 -69 -59
 * -96 -127 -123 -51 115 -106 11 -117 -28 -110 32 39 87 -21 -50 81 112 1 74 108
 * -33 84 -18 78 -108 -58 13 -104 88 67 37 -52 119 -6 -115 29 92 -45 16 -108 32
 * 24 -77 60 14 3 50 43 -82 77 101 25 -52 7 -37 -123 -91 -116 52 20 -9 -79 18
 * -93 90 115 -100 -117 -55 19 -4 -12 -115 -10 -10 28 -10 -51 64 -102 35 -102
 * 105 -64 88 -94 25 121 8 39 -20 -125 121 -41 -117 -38 47 -86 45 37 -56 -111
 * -93 93 -97 -119 -51 124 -100 -54 -28 71 -66 103 -55 56 108 93 -54 -31 35 -11
 * 6 81 -45 -93 -18 -72 51 52 111 -79 -16 112 -25 -17 67 7 14 111
 */