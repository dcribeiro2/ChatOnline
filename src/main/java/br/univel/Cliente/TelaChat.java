package br.univel.Cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class TelaChat extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldFiltrar;
	private JTextField textFieldIPortaServer;
	private JTextField textFieldIPServer;
	private JTextField textFieldPortaCliente;
	private JTextField textFieldIPCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaChat frame = new TelaChat();
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
	public TelaChat() {
		setTitle("Chat Online");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{399, -63, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{191, 35, 63, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panelChat = new JPanel();
		GridBagConstraints gbc_panelChat = new GridBagConstraints();
		gbc_panelChat.insets = new Insets(0, 0, 5, 5);
		gbc_panelChat.fill = GridBagConstraints.BOTH;
		gbc_panelChat.gridx = 0;
		gbc_panelChat.gridy = 0;
		contentPane.add(panelChat, gbc_panelChat);
		GridBagLayout gbl_panelChat = new GridBagLayout();
		gbl_panelChat.columnWidths = new int[]{0, 0};
		gbl_panelChat.rowHeights = new int[]{0, 0};
		gbl_panelChat.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelChat.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelChat.setLayout(gbl_panelChat);
		
		JScrollPane scrollPaneChat = new JScrollPane();
		GridBagConstraints gbc_scrollPaneChat = new GridBagConstraints();
		gbc_scrollPaneChat.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneChat.gridx = 0;
		gbc_scrollPaneChat.gridy = 0;
		panelChat.add(scrollPaneChat, gbc_scrollPaneChat);
		
		JTextArea textAreaChat = new JTextArea();
		scrollPaneChat.setViewportView(textAreaChat);
		
		JPanel panelUsuarios = new JPanel();
		GridBagConstraints gbc_panelUsuarios = new GridBagConstraints();
		gbc_panelUsuarios.gridwidth = 4;
		gbc_panelUsuarios.insets = new Insets(0, 0, 5, 0);
		gbc_panelUsuarios.fill = GridBagConstraints.BOTH;
		gbc_panelUsuarios.gridx = 1;
		gbc_panelUsuarios.gridy = 0;
		contentPane.add(panelUsuarios, gbc_panelUsuarios);
		GridBagLayout gbl_panelUsuarios = new GridBagLayout();
		gbl_panelUsuarios.columnWidths = new int[]{0, 0};
		gbl_panelUsuarios.rowHeights = new int[]{0, 0};
		gbl_panelUsuarios.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelUsuarios.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelUsuarios.setLayout(gbl_panelUsuarios);
		
		JScrollPane scrollPaneUsuarios = new JScrollPane();
		GridBagConstraints gbc_scrollPaneUsuarios = new GridBagConstraints();
		gbc_scrollPaneUsuarios.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneUsuarios.gridx = 0;
		gbc_scrollPaneUsuarios.gridy = 0;
		panelUsuarios.add(scrollPaneUsuarios, gbc_scrollPaneUsuarios);
		
		JTextArea textAreaUsuarios = new JTextArea();
		scrollPaneUsuarios.setViewportView(textAreaUsuarios);
		
		JPanel panelFiltrar = new JPanel();
		GridBagConstraints gbc_panelFiltrar = new GridBagConstraints();
		gbc_panelFiltrar.insets = new Insets(0, 0, 5, 0);
		gbc_panelFiltrar.gridwidth = 5;
		gbc_panelFiltrar.fill = GridBagConstraints.BOTH;
		gbc_panelFiltrar.gridx = 0;
		gbc_panelFiltrar.gridy = 1;
		contentPane.add(panelFiltrar, gbc_panelFiltrar);
		GridBagLayout gbl_panelFiltrar = new GridBagLayout();
		gbl_panelFiltrar.columnWidths = new int[]{239, 182, 84, 0};
		gbl_panelFiltrar.rowHeights = new int[]{20, 0};
		gbl_panelFiltrar.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelFiltrar.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelFiltrar.setLayout(gbl_panelFiltrar);
		
		textFieldFiltrar = new JTextField();
		GridBagConstraints gbc_textFieldFiltrar = new GridBagConstraints();
		gbc_textFieldFiltrar.fill = GridBagConstraints.BOTH;
		gbc_textFieldFiltrar.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldFiltrar.gridx = 0;
		gbc_textFieldFiltrar.gridy = 0;
		panelFiltrar.add(textFieldFiltrar, gbc_textFieldFiltrar);
		textFieldFiltrar.setColumns(10);
		
		JComboBox comboBoxFiltrar = new JComboBox();
		GridBagConstraints gbc_comboBoxFiltrar = new GridBagConstraints();
		gbc_comboBoxFiltrar.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxFiltrar.fill = GridBagConstraints.BOTH;
		gbc_comboBoxFiltrar.gridx = 1;
		gbc_comboBoxFiltrar.gridy = 0;
		panelFiltrar.add(comboBoxFiltrar, gbc_comboBoxFiltrar);
		
		JButton btnFiltrar = new JButton("Filtrar");
		GridBagConstraints gbc_btnFiltrar = new GridBagConstraints();
		gbc_btnFiltrar.fill = GridBagConstraints.BOTH;
		gbc_btnFiltrar.gridx = 2;
		gbc_btnFiltrar.gridy = 0;
		panelFiltrar.add(btnFiltrar, gbc_btnFiltrar);
		
		JPanel panelMain = new JPanel();
		GridBagConstraints gbc_panelMain = new GridBagConstraints();
		gbc_panelMain.gridheight = 2;
		gbc_panelMain.insets = new Insets(0, 0, 5, 5);
		gbc_panelMain.fill = GridBagConstraints.BOTH;
		gbc_panelMain.gridx = 0;
		gbc_panelMain.gridy = 2;
		contentPane.add(panelMain, gbc_panelMain);
		GridBagLayout gbl_panelMain = new GridBagLayout();
		gbl_panelMain.columnWidths = new int[]{14, 169, 93, 0};
		gbl_panelMain.rowHeights = new int[]{20, 0, 0, 0, 0};
		gbl_panelMain.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelMain.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelMain.setLayout(gbl_panelMain);
		
		JLabel lblIp = new JLabel("Porta server:");
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.anchor = GridBagConstraints.EAST;
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 0;
		panelMain.add(lblIp, gbc_lblIp);
		
		textFieldIPortaServer = new JTextField();
		GridBagConstraints gbc_textFieldIPortaServer = new GridBagConstraints();
		gbc_textFieldIPortaServer.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldIPortaServer.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIPortaServer.gridx = 1;
		gbc_textFieldIPortaServer.gridy = 0;
		panelMain.add(textFieldIPortaServer, gbc_textFieldIPortaServer);
		textFieldIPortaServer.setColumns(10);
		
		JButton btnAbrirServer = new JButton("Ligar server");
		GridBagConstraints gbc_btnAbrirServer = new GridBagConstraints();
		gbc_btnAbrirServer.insets = new Insets(0, 0, 5, 0);
		gbc_btnAbrirServer.fill = GridBagConstraints.BOTH;
		gbc_btnAbrirServer.gridx = 2;
		gbc_btnAbrirServer.gridy = 0;
		panelMain.add(btnAbrirServer, gbc_btnAbrirServer);
		
		JLabel lblIp_1 = new JLabel("IP:");
		GridBagConstraints gbc_lblIp_1 = new GridBagConstraints();
		gbc_lblIp_1.anchor = GridBagConstraints.EAST;
		gbc_lblIp_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp_1.gridx = 0;
		gbc_lblIp_1.gridy = 1;
		panelMain.add(lblIp_1, gbc_lblIp_1);
		
		textFieldIPServer = new JTextField();
		GridBagConstraints gbc_textFieldIPServer = new GridBagConstraints();
		gbc_textFieldIPServer.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldIPServer.fill = GridBagConstraints.BOTH;
		gbc_textFieldIPServer.gridx = 1;
		gbc_textFieldIPServer.gridy = 1;
		panelMain.add(textFieldIPServer, gbc_textFieldIPServer);
		textFieldIPServer.setColumns(10);
		
		JButton btnFexarServer = new JButton("Fexar server");
		GridBagConstraints gbc_btnFexarServer = new GridBagConstraints();
		gbc_btnFexarServer.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFexarServer.insets = new Insets(0, 0, 5, 0);
		gbc_btnFexarServer.gridx = 2;
		gbc_btnFexarServer.gridy = 1;
		panelMain.add(btnFexarServer, gbc_btnFexarServer);
		
		JLabel lblPortaCliente = new JLabel("Porta cliente");
		GridBagConstraints gbc_lblPortaCliente = new GridBagConstraints();
		gbc_lblPortaCliente.anchor = GridBagConstraints.EAST;
		gbc_lblPortaCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblPortaCliente.gridx = 0;
		gbc_lblPortaCliente.gridy = 2;
		panelMain.add(lblPortaCliente, gbc_lblPortaCliente);
		
		textFieldPortaCliente = new JTextField();
		GridBagConstraints gbc_textFieldPortaCliente = new GridBagConstraints();
		gbc_textFieldPortaCliente.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPortaCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPortaCliente.gridx = 1;
		gbc_textFieldPortaCliente.gridy = 2;
		panelMain.add(textFieldPortaCliente, gbc_textFieldPortaCliente);
		textFieldPortaCliente.setColumns(10);
		
		JButton btnConectarCliente = new JButton("Conectar cliente");
		GridBagConstraints gbc_btnConectarCliente = new GridBagConstraints();
		gbc_btnConectarCliente.fill = GridBagConstraints.BOTH;
		gbc_btnConectarCliente.insets = new Insets(0, 0, 5, 0);
		gbc_btnConectarCliente.gridx = 2;
		gbc_btnConectarCliente.gridy = 2;
		panelMain.add(btnConectarCliente, gbc_btnConectarCliente);
		
		JLabel lblIp_2 = new JLabel("IP:");
		GridBagConstraints gbc_lblIp_2 = new GridBagConstraints();
		gbc_lblIp_2.anchor = GridBagConstraints.EAST;
		gbc_lblIp_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblIp_2.gridx = 0;
		gbc_lblIp_2.gridy = 3;
		panelMain.add(lblIp_2, gbc_lblIp_2);
		
		textFieldIPCliente = new JTextField();
		GridBagConstraints gbc_textFieldIPCliente = new GridBagConstraints();
		gbc_textFieldIPCliente.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldIPCliente.fill = GridBagConstraints.BOTH;
		gbc_textFieldIPCliente.gridx = 1;
		gbc_textFieldIPCliente.gridy = 3;
		panelMain.add(textFieldIPCliente, gbc_textFieldIPCliente);
		textFieldIPCliente.setColumns(10);
		
		JButton btnDesconectarCliente = new JButton("Desconectar cliente");
		GridBagConstraints gbc_btnDesconectarCliente = new GridBagConstraints();
		gbc_btnDesconectarCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDesconectarCliente.gridx = 2;
		gbc_btnDesconectarCliente.gridy = 3;
		panelMain.add(btnDesconectarCliente, gbc_btnDesconectarCliente);
		
		JPanel panelArquivos = new JPanel();
		GridBagConstraints gbc_panelArquivos = new GridBagConstraints();
		gbc_panelArquivos.insets = new Insets(0, 0, 5, 0);
		gbc_panelArquivos.gridwidth = 4;
		gbc_panelArquivos.fill = GridBagConstraints.BOTH;
		gbc_panelArquivos.gridx = 1;
		gbc_panelArquivos.gridy = 2;
		contentPane.add(panelArquivos, gbc_panelArquivos);
		GridBagLayout gbl_panelArquivos = new GridBagLayout();
		gbl_panelArquivos.columnWidths = new int[]{0, 0};
		gbl_panelArquivos.rowHeights = new int[]{0, 0};
		gbl_panelArquivos.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelArquivos.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelArquivos.setLayout(gbl_panelArquivos);
		
		JScrollPane scrollPaneArquivos = new JScrollPane();
		GridBagConstraints gbc_scrollPaneArquivos = new GridBagConstraints();
		gbc_scrollPaneArquivos.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneArquivos.gridx = 0;
		gbc_scrollPaneArquivos.gridy = 0;
		panelArquivos.add(scrollPaneArquivos, gbc_scrollPaneArquivos);
		
		JTextArea textAreaArquivos = new JTextArea();
		scrollPaneArquivos.setViewportView(textAreaArquivos);
		
		JPanel panelArquivosBtns = new JPanel();
		GridBagConstraints gbc_panelArquivosBtns = new GridBagConstraints();
		gbc_panelArquivosBtns.gridwidth = 4;
		gbc_panelArquivosBtns.insets = new Insets(0, 0, 0, 5);
		gbc_panelArquivosBtns.fill = GridBagConstraints.BOTH;
		gbc_panelArquivosBtns.gridx = 1;
		gbc_panelArquivosBtns.gridy = 3;
		contentPane.add(panelArquivosBtns, gbc_panelArquivosBtns);
		GridBagLayout gbl_panelArquivosBtns = new GridBagLayout();
		gbl_panelArquivosBtns.columnWidths = new int[]{0, 0, 0};
		gbl_panelArquivosBtns.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelArquivosBtns.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelArquivosBtns.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelArquivosBtns.setLayout(gbl_panelArquivosBtns);
		
		JLabel lblCliente = new JLabel("Cliente");
		GridBagConstraints gbc_lblCliente = new GridBagConstraints();
		gbc_lblCliente.anchor = GridBagConstraints.EAST;
		gbc_lblCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblCliente.gridx = 0;
		gbc_lblCliente.gridy = 0;
		panelArquivosBtns.add(lblCliente, gbc_lblCliente);
		
		JComboBox comboBoxClientes = new JComboBox();
		GridBagConstraints gbc_comboBoxClientes = new GridBagConstraints();
		gbc_comboBoxClientes.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxClientes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxClientes.gridx = 1;
		gbc_comboBoxClientes.gridy = 0;
		panelArquivosBtns.add(comboBoxClientes, gbc_comboBoxClientes);
		
		JLabel lblArquivo = new JLabel("Arquivo");
		GridBagConstraints gbc_lblArquivo = new GridBagConstraints();
		gbc_lblArquivo.anchor = GridBagConstraints.EAST;
		gbc_lblArquivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblArquivo.gridx = 0;
		gbc_lblArquivo.gridy = 1;
		panelArquivosBtns.add(lblArquivo, gbc_lblArquivo);
		
		JComboBox comboBoxArquivos = new JComboBox();
		GridBagConstraints gbc_comboBoxArquivos = new GridBagConstraints();
		gbc_comboBoxArquivos.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxArquivos.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxArquivos.gridx = 1;
		gbc_comboBoxArquivos.gridy = 1;
		panelArquivosBtns.add(comboBoxArquivos, gbc_comboBoxArquivos);
		
		JButton btnNewButton = new JButton("Baixar arquivos");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		panelArquivosBtns.add(btnNewButton, gbc_btnNewButton);
	}

}
