package interfaces;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import idioma.Idioma;
import negocio.Conta;

@SuppressWarnings("serial")
public class ConsultarExtratoGUI extends JDialog implements ActionListener {
	private JLabel lNome, lPeriodo;
	private JTextField tPeriodo;
	private Container c;
	private JPanel pai, mae, irma, irmao;
	private JButton cancelar, continuar, periodo7, periodo15;

	// Metodo construtor
	public ConsultarExtratoGUI(JFrame f) {
		super(f, Idioma.getRb().getString("consExtrato.super"), true);
		c = getContentPane();
		c.setLayout(new BorderLayout());

		lNome = new JLabel(Conta.getInstance().getCliente().getNome());
		lNome.setHorizontalAlignment(JLabel.CENTER);
		lPeriodo = new JLabel(Idioma.getRb().getString("consExtrato.label.perDesejado"));
		tPeriodo = new JTextField(10);
		cancelar = new JButton(Idioma.getRb().getString("consExtrato.button.cancelar"));
		cancelar.addActionListener(this);
		continuar = new JButton(Idioma.getRb().getString("consExtrato.button.Continuar"));
		continuar.addActionListener(this);
		periodo7 = new JButton(Idioma.getRb().getString("consExtrato.button.ult07"));
		periodo7.addActionListener(this);
		periodo15 = new JButton(Idioma.getRb().getString("consExtrato.button.ult15"));
		periodo15.addActionListener(this);

		pai = new JPanel();
		pai.setLayout(new FlowLayout());
		pai.add(cancelar);
		pai.add(continuar);

		irma = new JPanel();
		irma.setLayout(new FlowLayout());
		irma.add(lPeriodo);
		irma.add(tPeriodo);

		mae = new JPanel();
		mae.setLayout(new FlowLayout());
		mae.add(periodo7);

		irmao = new JPanel();
		irmao.setLayout(new FlowLayout());
		irmao.add(periodo15);

		c.add(lNome, BorderLayout.NORTH);
		c.add(mae, BorderLayout.WEST);
		c.add(irma, BorderLayout.CENTER);
		c.add(irmao, BorderLayout.EAST);
		c.add(pai, BorderLayout.SOUTH);

		setResizable(false);
		setSize(400, 135);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	// Tratamento dos botoes
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == continuar) {
				dispose();
				new ConsultarExtrato2GUI(this);
			}

			if (e.getSource() == cancelar) {
				dispose();
			}
		} catch (Exception erro) {
			erro.printStackTrace();
		}
	}

}
