package interfaces;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import idioma.Idioma;
import negocio.Conta;
import util.Dados;

@SuppressWarnings("serial")
public class AcessarCodigoGUI extends JFrame implements ActionListener {
	private JLabel lSenha, lNome;
	private JTextField tSenha;
	private Container c;
	private JPanel pai, mae, irma;
	private JButton corrigir, proximo, numero[];
	private String numeros[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	private int qtd;

	// Metodo construtor
	public AcessarCodigoGUI(int x) {
		super(Idioma.getRb().getString("acessarCodigo.super"));
		c = getContentPane();
		c.setLayout(new BorderLayout());
		qtd = x;
		// Conta conta = Conta.getPao();
		// String nome = conta.getCliente().getNome();

		lNome = new JLabel(Conta.getInstance().getCliente().getNome());
		lNome.setHorizontalAlignment(JLabel.CENTER);
		lSenha = new JLabel(Idioma.getRb().getString("acessarCodigo.label.codigo"));
		tSenha = new JTextField(10);
		corrigir = new JButton(Idioma.getRb().getString("acessarCodigo.button.corrigir"));
		corrigir.addActionListener(this);
		proximo = new JButton(Idioma.getRb().getString("acessarCodigo.button.proximo"));
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

		// Numeros aleatorios no cod de acesso (Resquisito)
		Random gnt = new Random();
		int vet[] = new int[10];

		for (int i = 0; i < vet.length; i++) {
			vet[i] = -1;
		}

		for (int i = 0; i < vet.length; i++) {
			int aux = gnt.nextInt(10);
			for (int j = 0; j < vet.length; j++) {
				if (aux == vet[j]) {
					aux = gnt.nextInt(10);
					j = -1;
				}
			}
			vet[i] = aux;
		}

		numero = new JButton[numeros.length];
		for (int i = 0; i < numeros.length; i++) {
			numero[vet[i]] = new JButton(numeros[vet[i]]);
			numero[vet[i]].addActionListener(this);

			if (i < 5) {
				irma.add(numero[vet[i]]);
			} else {
				pai.add(numero[vet[i]]);
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
				Dados.acessarCodigo(tSenha.getText(), qtd);
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