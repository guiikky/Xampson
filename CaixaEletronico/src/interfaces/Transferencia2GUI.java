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
import javax.swing.JLabel;
import javax.swing.JPanel;

import idioma.Idioma;
import negocio.Conta;
import negocio.Transferencia;

@SuppressWarnings("serial")
public class Transferencia2GUI extends JDialog implements ActionListener {
	private JLabel lNome, lAgencia, lConta, lValor;
	private Container c;
	private JPanel pai, mae;
	private JButton cancelar, confirmar;
	private int agenciaT, contaT;
	private String data;
	private Double valor;
	private Conta conta;

	// Metodo construtor
	public Transferencia2GUI(JDialog f, String ag, String cnt, double vlr) {
		super(f, Idioma.getRb().getString("transf.super"), true);
		c = getContentPane();
		c.setLayout(new BorderLayout());
		agenciaT = Integer.parseInt(ag);
		contaT = Integer.parseInt(cnt);
		valor = vlr;
		conta = Conta.getInstance();

		lNome = new JLabel(conta.getCliente().getNome());
		lNome.setHorizontalAlignment(JLabel.CENTER);
		lAgencia = new JLabel(Idioma.getRb().getString("transf.label.agencia") + "  " + agenciaT);
		lConta = new JLabel(Idioma.getRb().getString("transf.label.Conta") + "  " + contaT);
		lValor = new JLabel(Idioma.getRb().getString("transf.label.Valor") + "  " + valor);
		cancelar = new JButton(Idioma.getRb().getString("transf.button.cancelar"));
		cancelar.addActionListener(this);
		confirmar = new JButton(Idioma.getRb().getString("transf.button.continuar"));
		confirmar.addActionListener(this);

		mae = new JPanel();
		mae.setLayout(new FlowLayout());
		pai = new JPanel();
		pai.setLayout(new GridLayout(3, 1));

		Calendar cldr = Calendar.getInstance();
		data = cldr.get(Calendar.DAY_OF_MONTH) + "/" + (cldr.get(Calendar.MONTH) + 1) + "/" + cldr.get(Calendar.YEAR);

		mae.add(cancelar);
		mae.add(confirmar);

		pai.add(lAgencia);
		pai.add(lConta);
		pai.add(lValor);

		c.add(lNome, BorderLayout.NORTH);
		c.add(pai, BorderLayout.CENTER);
		c.add(mae, BorderLayout.SOUTH);

		setResizable(false);
		setSize(300, 160);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	// Tratamento dos botoes
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == confirmar) {
				Transferencia tr = new Transferencia(conta, agenciaT, contaT, data, valor);
				tr.transferir();
				dispose();
			}

			if (e.getSource() == cancelar) {
				dispose();
			}
		} catch (Exception erro) {
			erro.printStackTrace();
		}
	}
}
