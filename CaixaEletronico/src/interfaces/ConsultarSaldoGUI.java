package interfaces;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import idioma.Idioma;
import negocio.Conta;

@SuppressWarnings("serial")
public class ConsultarSaldoGUI extends JDialog implements ActionListener {
	private JLabel lNome;
	private Container c;
	private JPanel mae;
	private JButton cancelar;
	private JTable tabela;
	private JScrollPane bar;

	// Metodo construtor
	public ConsultarSaldoGUI(JFrame f) {
		super(f, Idioma.getRb().getString("consSaldo.super"), true);
		c = getContentPane();
		c.setLayout(new BorderLayout());

		lNome = new JLabel(Conta.getInstance().getCliente().getNome());
		lNome.setHorizontalAlignment(JLabel.CENTER);

		cancelar = new JButton(Idioma.getRb().getString("consSaldo.button.cancelar"));
		cancelar.addActionListener(this);

		mae = new JPanel();
		mae.setLayout(new FlowLayout());
		mae.add(cancelar);

		Calendar cldr = Calendar.getInstance();
		String data = cldr.get(Calendar.DAY_OF_MONTH) + "/" + (cldr.get(Calendar.MONTH) + 1) + "/"
				+ cldr.get(Calendar.YEAR);

		Object dados[][] = { { data, Conta.getInstance().getSaldo() } };
		String cln[] = { Idioma.getRb().getString("consSaldo.coluna.data"),
				Idioma.getRb().getString("consSaldo.coluna.valor") };

		tabela = new JTable(dados, cln) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.getTableHeader().setResizingAllowed(false);
		tabela.setCellSelectionEnabled(false);

		bar = new JScrollPane(tabela);

		c.add(lNome, BorderLayout.NORTH);
		c.add(bar, BorderLayout.CENTER);
		c.add(mae, BorderLayout.SOUTH);

		setResizable(false);
		setSize(300, 160);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	// Tratamento do botao
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == cancelar) {
				dispose();
			}
		} catch (Exception erro) {
			erro.printStackTrace();
		}
	}
}
