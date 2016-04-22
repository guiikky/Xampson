package interfaces;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import idioma.Idioma;
import negocio.Conta;
import util.Dados;

@SuppressWarnings("serial")
public class TransferenciaGUI extends JDialog implements ActionListener {
	private JLabel lAgencia, lConta, lNome, lValor;
	private JTextField tAgencia, tConta, tValor;
	private Container c;
	private JPanel pai, mae;
	private JButton cancelar, continuar;
	private Conta conta;

	// Metodo construtor
	public TransferenciaGUI(JFrame f) {
		super(f, Idioma.getRb().getString("transf.super"), true);
		c = getContentPane();
		c.setLayout(new BorderLayout());
		conta = Conta.getInstance();

		lNome = new JLabel(conta.getCliente().getNome());
		lNome.setHorizontalAlignment(JLabel.CENTER);
		lAgencia = new JLabel(Idioma.getRb().getString("transf.label.agencia"));
		lConta = new JLabel(Idioma.getRb().getString("transf.label.Conta"));
		lValor = new JLabel(Idioma.getRb().getString("transf.label.Valor"));
		tAgencia = new JTextField(10);
		tConta = new JTextField(10);
		tValor = new JTextField(10);
		cancelar = new JButton(Idioma.getRb().getString("transf.button.cancelar"));
		cancelar.addActionListener(this);
		continuar = new JButton(Idioma.getRb().getString("transf.button.continuar"));
		continuar.addActionListener(this);

		mae = new JPanel();
		mae.setLayout(new FlowLayout());
		pai = new JPanel();
		pai.setLayout(new GridLayout(3, 2));

		mae.add(cancelar);
		mae.add(continuar);

		pai.add(lAgencia);
		pai.add(tAgencia);
		pai.add(lConta);
		pai.add(tConta);
		pai.add(lValor);
		pai.add(tValor);

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
			if (e.getSource() == continuar) {
				double x = Double.parseDouble(tValor.getText());
				String contaT = tConta.getText();
				String agenciaT = tAgencia.getText();
				if (conta.conferirSaldo(x) && Dados.verificar(agenciaT, contaT)) {
					new Transferencia2GUI(this, agenciaT, contaT, x);
				} else {
					JOptionPane.showMessageDialog(null, Idioma.getRb().getString("avisosAcesso3"),
							Idioma.getRb().getString("avisosAcesso2"), 2);
					tAgencia.setText("");
					tConta.setText("");
					tValor.setText("");
				}
			}

			if (e.getSource() == cancelar) {
				dispose();
			}
		} catch (Exception erro) {
			erro.printStackTrace();
		}
	}
}
