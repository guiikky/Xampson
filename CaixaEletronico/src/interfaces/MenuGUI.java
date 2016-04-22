package interfaces;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import idioma.Idioma;
import negocio.Conta;

@SuppressWarnings("serial")
public class MenuGUI extends JFrame implements ActionListener {
	private JLabel lNome;
	private Container c;
	private JPanel pai;
	private JButton saldo, extrato, sacar, transf, debito, finalizar;

	// Metodo construtor
	public MenuGUI() {
		super(Idioma.getRb().getString("menu.super"));
		c = getContentPane();
		c.setLayout(new BorderLayout());

		lNome = new JLabel(Conta.getInstance().getCliente().getNome());
		lNome.setHorizontalAlignment(JLabel.CENTER);
		saldo = new JButton(Idioma.getRb().getString("menu.button.conSaldo"));
		saldo.addActionListener(this);
		extrato = new JButton(Idioma.getRb().getString("menu.button.consExtrato"));
		extrato.addActionListener(this);
		sacar = new JButton(Idioma.getRb().getString("menu.button.sacarDinheiro"));
		sacar.addActionListener(this);
		transf = new JButton(Idioma.getRb().getString("menu.button.transcont"));
		transf.addActionListener(this);
		debito = new JButton(Idioma.getRb().getString("menu.button.cadastDeb"));
		debito.addActionListener(this);
		finalizar = new JButton(Idioma.getRb().getString("menu.button.finalizar"));
		finalizar.addActionListener(this);

		pai = new JPanel();
		pai.setLayout(new GridLayout(3, 2, 2, 2));

		pai.add(saldo);
		pai.add(extrato);
		pai.add(sacar);
		pai.add(transf);
		pai.add(debito);
		pai.add(finalizar);

		c.add(lNome, BorderLayout.NORTH);
		c.add(pai, BorderLayout.CENTER);

		setResizable(false);
		setSize(420, 180);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	// Tratamento dos botoes
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == saldo) {
				new ConsultarSaldoGUI(this);
			}

			if (e.getSource() == extrato) {
				new ConsultarExtratoGUI(this);
			}

			if (e.getSource() == sacar) {
				new SacarDinheiroGUI(this);
			}

			if (e.getSource() == transf) {
				new TransferenciaGUI(this);
			}

			if (e.getSource() == debito) {
				new DebitoAutomaticoGUI(this);
			}

			if (e.getSource() == finalizar) {
				Conta.destroy();
				new ValidarUsuarioGUI();
				dispose();
			}
		} catch (Exception erro) {
			erro.printStackTrace();
		}
	}

}