package interfaces;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import idioma.Idioma;
import negocio.Conta;
import util.Dados;

@SuppressWarnings("serial")
public class GerarCodigoGUI extends JFrame implements ActionListener {
	private JLabel lSenha, lNome;
	private JTextField tSenha;
	private Container c;
	private JPanel pai, mae, irma;
	private JButton corrigir, proximo, numero[];
	private String numeros[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	// Metodo construtor
	public GerarCodigoGUI() {
		super(Idioma.getRb().getString("gerarCodigo.super"));
		c = getContentPane();
		c.setLayout(new BorderLayout());

		lNome = new JLabel(Conta.getInstance().getCliente().getNome());
		lNome.setHorizontalAlignment(JLabel.CENTER);
		lSenha = new JLabel(Idioma.getRb().getString("gerarCodigo.label.codigo"));
		tSenha = new JTextField(10);
		corrigir = new JButton(Idioma.getRb().getString("gerarCodigo.button.corrigir"));
		corrigir.addActionListener(this);
		proximo = new JButton(Idioma.getRb().getString("gerarCodigo.button.proximo"));
		proximo.addActionListener(this);

		mae = new JPanel();
		mae.setLayout(new FlowLayout());
		pai = new JPanel();
		pai.setLayout(new GridLayout(5, 1, 2, 2));
		irma = new JPanel();
		irma.setLayout(new GridLayout(5, 1, 2, 2));

		mae.add(lSenha);
		mae.add(tSenha);
		mae.add(corrigir);
		mae.add(proximo);

		numero = new JButton[numeros.length];
		for (int i = 0; i < numeros.length; i++) {
			numero[i] = new JButton(numeros[i]);
			numero[i].addActionListener(this);
			if (i < 5) {
				irma.add(numero[i]);
			} else {
				pai.add(numero[i]);
			}
		}

		c.add(lNome, BorderLayout.NORTH);
		c.add(irma, BorderLayout.WEST);
		c.add(mae, BorderLayout.CENTER);
		c.add(pai, BorderLayout.EAST);

		setResizable(false);
		setSize(230, 170);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	// Tratamento dos botoes
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == proximo) {
				Dados.gerarCodigo(tSenha.getText());
				dispose();
			}

			if (e.getSource() == corrigir) {
				tSenha.setText("");
			}

			for (int i = 0; i < numeros.length; i++) {
				if (e.getSource() == numero[i]) {
					String x = tSenha.getText();
					if (x.length() < 3) {
						tSenha.setText(x + numeros[i]);
					}
				}
			}
		} catch (Exception erro) {
			erro.printStackTrace();
		}
	}

}
