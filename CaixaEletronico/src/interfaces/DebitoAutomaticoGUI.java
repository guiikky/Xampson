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
import javax.swing.JPanel;
import javax.swing.JTextField;

import idioma.Idioma;
import negocio.Conta;
import negocio.DebitoAutomatico;

@SuppressWarnings("serial")
public class DebitoAutomaticoGUI extends JDialog implements ActionListener {
	private JLabel lNome, lOperadora, lConsumidor, lData;
	private JTextField tOperadora, tConsumidor, tData;
	private Container c;
	private JPanel pai, mae;
	private JButton cadastrar, cancelar;
	private Conta conta;

	// Debito automatico
	public DebitoAutomaticoGUI(JFrame f) {
		super(f, Idioma.getRb().getString("debAuto.super"), true);
		c = getContentPane();
		c.setLayout(new BorderLayout());
		conta = Conta.getInstance();

		Calendar cldr = Calendar.getInstance();
		String data = cldr.get(Calendar.DAY_OF_MONTH) + "/" + (cldr.get(Calendar.MONTH) + 1) + "/"
				+ cldr.get(Calendar.YEAR);

		lNome = new JLabel(conta.getCliente().getNome());
		lNome.setHorizontalAlignment(JLabel.CENTER);
		lOperadora = new JLabel(Idioma.getRb().getString("debAuto.label.tx1"));
		lConsumidor = new JLabel(Idioma.getRb().getString("debAuto.label.tx2"));
		lData = new JLabel(Idioma.getRb().getString("debAuto.label.data"));
		tOperadora = new JTextField(10);
		tConsumidor = new JTextField(10);
		tData = new JTextField(data);
		tData.setEditable(false);
		cadastrar = new JButton(Idioma.getRb().getString("debAuto.button.cadastrar"));
		cadastrar.addActionListener(this);
		cancelar = new JButton(Idioma.getRb().getString("debAuto.button.cancelar"));
		cancelar.addActionListener(this);

		mae = new JPanel();
		mae.setLayout(new FlowLayout());
		pai = new JPanel();
		pai.setLayout(new GridLayout(3, 2));

		mae.add(cancelar);
		mae.add(cadastrar);

		pai.add(lOperadora);
		pai.add(tOperadora);
		pai.add(lConsumidor);
		pai.add(tConsumidor);
		pai.add(lData);
		pai.add(tData);

		c.add(lNome, BorderLayout.NORTH);
		c.add(pai, BorderLayout.CENTER);
		c.add(mae, BorderLayout.SOUTH);

		setResizable(false);
		setSize(300, 160);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == cancelar) {
				dispose();
			}

			if (e.getSource() == cadastrar) {
				int operadora = Integer.parseInt(tOperadora.getText());
				int consumidor = Integer.parseInt(tConsumidor.getText());
				String data = tData.getText();

				DebitoAutomatico da = new DebitoAutomatico(operadora, consumidor, data, conta);
				da.criar();
				tOperadora.setText("");
				tConsumidor.setText("");
				tData.setText("");
			}
		} catch (Exception erro) {
			erro.printStackTrace();
		}
	}
}