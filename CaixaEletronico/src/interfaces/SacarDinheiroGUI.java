package interfaces;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import idioma.Idioma;
import negocio.Conta;
import negocio.Dispenser;
import negocio.Saque;

@SuppressWarnings("serial")
public class SacarDinheiroGUI extends JDialog implements ActionListener {
	private JLabel lNome, lValor;
	private JTextField tValor, tNotas;
	private Container c;
	private JPanel pai, mae, irma;
	private JButton cancelar, confirmar, b[];
	private String data;
	private String v[] = { "R$10,00", "R$20,00", "R$50,00", "R$100,00", "R$200,00", "R$500,00" };
	private double vet[] = { 10, 20, 50, 100, 200, 500 };
	private Dispenser p;
	private Conta conta;

	// Metodo construtor
	public SacarDinheiroGUI(JFrame f) {
		super(f, Idioma.getRb().getString("saque.super"), true);
		c = getContentPane();
		c.setLayout(new BorderLayout());
		conta = Conta.getInstance();
		p = new Dispenser();

		lNome = new JLabel(conta.getCliente().getNome());
		lNome.setHorizontalAlignment(JLabel.CENTER);
		lValor = new JLabel(Idioma.getRb().getString("saque.label.texto1"));
		tValor = new JTextField(16);
		tNotas = new JTextField(p.status());
		tNotas.setEditable(false);

		cancelar = new JButton(Idioma.getRb().getString("saque.button.cancelar"));
		cancelar.addActionListener(this);
		confirmar = new JButton(Idioma.getRb().getString("saque.button.confirmar"));
		confirmar.addActionListener(this);

		mae = new JPanel();
		mae.setLayout(new GridLayout(3, 1, 2, 2));
		pai = new JPanel();
		pai.setLayout(new GridLayout(3, 1, 2, 2));
		irma = new JPanel();
		irma.setLayout(new FlowLayout());

		b = new JButton[v.length];
		for (int i = 0; i < b.length; i++) {
			b[i] = new JButton(v[i]);
			b[i].addActionListener(this);
			if (i < 3) {
				mae.add(b[i]);
			} else {
				pai.add(b[i]);
			}
		}

		Calendar cldr = Calendar.getInstance();
		data = cldr.get(Calendar.DAY_OF_MONTH) + "/" + (cldr.get(Calendar.MONTH) + 1) + "/" + cldr.get(Calendar.YEAR);

		irma.add(lValor);
		irma.add(tValor);
		irma.add(cancelar);
		irma.add(confirmar);

		c.add(lNome, BorderLayout.NORTH);
		c.add(mae, BorderLayout.WEST);
		c.add(irma, BorderLayout.CENTER);
		c.add(pai, BorderLayout.EAST);
		c.add(tNotas, BorderLayout.SOUTH);

		setResizable(false);
		setSize(390, 170);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	// Tratamento dos botoes
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == confirmar) {
				double valor = Double.parseDouble(tValor.getText());
				Saque s = new Saque(data, conta, p, valor);
				JOptionPane.showMessageDialog(null, s.sacar(), Idioma.getRb().getString("avisosAcesso2"), 1);
			}

			if (e.getSource() == cancelar) {
				dispose();
			}

			for (int i = 0; i < b.length; i++) {
				if (e.getSource() == b[i]) {
					Saque s = new Saque(data, conta, p, vet[i]);
					JOptionPane.showMessageDialog(null, s.sacar(), Idioma.getRb().getString("avisosAcesso2"), 1);
				}
			}

		} catch (Exception erro) {
			erro.printStackTrace();
		}
	}
}
