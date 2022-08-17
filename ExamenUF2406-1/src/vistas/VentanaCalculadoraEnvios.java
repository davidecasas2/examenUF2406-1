package vistas;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import modelo.Envio;
import net.miginfocom.swing.MigLayout;

public class VentanaCalculadoraEnvios extends JFrame {

	private JPanel contentPane;
	private JTextField txtOrigen;
	private JTextField txtDestino;
	private final ButtonGroup grupoOrigen = new ButtonGroup();
	private final ButtonGroup grupoDestino = new ButtonGroup();
	private JComboBox comboTipo;
	private JRadioButton rdbNacionalDestino;
	private JRadioButton rdbExtranjeroOrign;
	private JSpinner spinnerPeso;
	private JRadioButton rdbNacionalOrigen;
	private JRadioButton rdbExtranjeroDestino;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCalculadoraEnvios frame = new VentanaCalculadoraEnvios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaCalculadoraEnvios() {
		setFont(new Font("Consolas", Font.PLAIN, 14));
		setTitle("Calculadora Envíos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow]", "[][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Ciudad Origen:");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
		contentPane.add(lblNewLabel, "cell 1 1,growx");
		
		txtOrigen = new JTextField();
		txtOrigen.setFont(new Font("Consolas", Font.PLAIN, 14));
		contentPane.add(txtOrigen, "cell 2 1,growx");
		txtOrigen.setColumns(10);
		
		rdbNacionalOrigen = new JRadioButton("Nacional");
		rdbNacionalOrigen.setSelected(true);
		grupoOrigen.add(rdbNacionalOrigen);
		rdbNacionalOrigen.setFont(new Font("Consolas", Font.PLAIN, 14));
		contentPane.add(rdbNacionalOrigen, "flowx,cell 2 2,aligny bottom");
		
		JLabel lblNewLabel_1 = new JLabel("Ciudad Destino:");
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1, "cell 1 3,alignx trailing");
		
		txtDestino = new JTextField();
		txtDestino.setFont(new Font("Consolas", Font.PLAIN, 14));
		contentPane.add(txtDestino, "cell 2 3,growx");
		txtDestino.setColumns(10);
		
		rdbNacionalDestino = new JRadioButton("Nacional");
		rdbNacionalDestino.setSelected(true);
		grupoDestino.add(rdbNacionalDestino);
		rdbNacionalDestino.setFont(new Font("Consolas", Font.PLAIN, 14));
		contentPane.add(rdbNacionalDestino, "flowx,cell 2 4");
		
		JLabel lblNewLabel_2 = new JLabel("Tipo de envío:");
		lblNewLabel_2.setFont(new Font("Consolas", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_2, "cell 1 5,growx");
		
		comboTipo = new JComboBox();
		comboTipo.setModel(new DefaultComboBoxModel(new String[] {"Paq 10 - Antes de las 10 h", "Paq 14 - Antes de las 14 h", "Paq 24 - Al día siguiente"}));
		comboTipo.setFont(new Font("Consolas", Font.PLAIN, 14));
		contentPane.add(comboTipo, "cell 2 5,growx");
		
		JLabel lblNewLabel_3 = new JLabel("Peso:");
		lblNewLabel_3.setFont(new Font("Consolas", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_3, "cell 1 6");
		
		rdbExtranjeroOrign = new JRadioButton("Extranjero");
		grupoOrigen.add(rdbExtranjeroOrign);
		rdbExtranjeroOrign.setFont(new Font("Consolas", Font.PLAIN, 14));
		contentPane.add(rdbExtranjeroOrign, "cell 2 2");
		
		rdbExtranjeroDestino = new JRadioButton("Extranjero");
		grupoDestino.add(rdbExtranjeroDestino);
		rdbExtranjeroDestino.setFont(new Font("Consolas", Font.PLAIN, 14));
		contentPane.add(rdbExtranjeroDestino, "cell 2 4");
		
		spinnerPeso = new JSpinner();
		spinnerPeso.setMinimumSize(new Dimension(60, 18));
		spinnerPeso.setFont(new Font("Consolas", Font.PLAIN, 14));
		spinnerPeso.setModel(new SpinnerNumberModel(1, 1, 40, 1));
		contentPane.add(spinnerPeso, "flowx,cell 2 6");
		
		JButton btnNewButton = new JButton("Calcular Precio");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularEnvio();
			}
		});
		btnNewButton.setFont(new Font("Consolas", Font.PLAIN, 14));
		contentPane.add(btnNewButton, "cell 1 8 2 1,alignx center");
		
		JLabel lblNewLabel_4 = new JLabel("Kg");
		lblNewLabel_4.setFont(new Font("Consolas", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_4, "cell 2 6");
	}

	protected void calcularEnvio() {
		if (txtOrigen.getText()==null || txtOrigen.getText().isBlank() ||
			txtDestino.getText()==null || txtDestino.getText().isBlank()) {
			
			JOptionPane.showMessageDialog(this, 
				"Debe introducir los campos de origen y de destino",
				"Datos incorrectos", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String origen = txtOrigen.getText();
		String destino = txtDestino.getText();
		boolean origenNacional = this.rdbNacionalOrigen.isSelected();
		boolean destinoNacional = this.rdbNacionalDestino.isSelected();
		String tipoEnvio = (String) this.comboTipo.getSelectedItem();
		int peso = (int) this.spinnerPeso.getValue();
		Envio e = new Envio(origen, origenNacional, destino, 
				destinoNacional, tipoEnvio, peso);
		JOptionPane.showMessageDialog(this, 
				"El importe total es "+e.calcularImporte(),
				"Importe del envío", JOptionPane.INFORMATION_MESSAGE);
		
	}

}
