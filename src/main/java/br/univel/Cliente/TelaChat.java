package br.univel.Cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ButtonUI;
import javax.swing.text.StyledEditorKit.ItalicAction;

import br.univel.comum.Arquivo;
import br.univel.comum.Cliente;
import br.univel.comum.IServer;
import br.univel.comum.TipoFiltro;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Insets;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaChat extends JFrame implements IServer, Runnable {

	private JList listParticipantes;
	private JPanel contentPane;
	private JTextField textFieldFiltrar;
	private JTextField textFieldIPortaServer;
	private JTextField textFieldIPServer;
	private JTextField textFieldPortaCliente;
	private JTextField textFieldIPCliente;
	private JTextArea textAreaChat;
	private JButton btnConectarCliente;
	private JButton btnFexarServer;
	private JButton btnAbrirServer;
	private JButton btnDesconectarCliente;
	private Registry registry, registryCliente;
	private List<Cliente> listaClientes = new ArrayList<>();
	private IServer servidor, servidorCliente;
	private Cliente cliente = new Cliente();
	private List<Arquivo> listaArquivos = new ArrayList<>();
	private JTextArea textAreaUsuarios;
	private int intPorta;
	private JTextArea textAreaArquivos; 
	private Map<Cliente, List<Arquivo>> mapaClientes = new HashMap<>();
	private String username;
	private JComboBox comboBoxArquivos;
	private JComboBox comboBoxClientes;
	private JComboBox comboBoxFiltrar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					TelaChat frame = new TelaChat();
				try {
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
		setBounds(100, 100, 644, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 399, -63, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 164, 35, 145, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel panelChat = new JPanel();
		GridBagConstraints gbc_panelChat = new GridBagConstraints();
		gbc_panelChat.insets = new Insets(0, 0, 5, 5);
		gbc_panelChat.fill = GridBagConstraints.BOTH;
		gbc_panelChat.gridx = 0;
		gbc_panelChat.gridy = 0;
		contentPane.add(panelChat, gbc_panelChat);
		GridBagLayout gbl_panelChat = new GridBagLayout();
		gbl_panelChat.columnWidths = new int[] { 0, 0 };
		gbl_panelChat.rowHeights = new int[] { 0, 0 };
		gbl_panelChat.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelChat.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panelChat.setLayout(gbl_panelChat);

		JScrollPane scrollPaneChat = new JScrollPane();
		GridBagConstraints gbc_scrollPaneChat = new GridBagConstraints();
		gbc_scrollPaneChat.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneChat.gridx = 0;
		gbc_scrollPaneChat.gridy = 0;
		panelChat.add(scrollPaneChat, gbc_scrollPaneChat);

		textAreaChat = new JTextArea();
		textAreaChat.setFont(new Font("Monospaced", Font.BOLD, 13));
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
		gbl_panelUsuarios.columnWidths = new int[] { 0, 0 };
		gbl_panelUsuarios.rowHeights = new int[] { 0, 0 };
		gbl_panelUsuarios.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelUsuarios.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panelUsuarios.setLayout(gbl_panelUsuarios);

		JScrollPane scrollPaneUsuarios = new JScrollPane();
		GridBagConstraints gbc_scrollPaneUsuarios = new GridBagConstraints();
		gbc_scrollPaneUsuarios.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneUsuarios.gridx = 0;
		gbc_scrollPaneUsuarios.gridy = 0;
		panelUsuarios.add(scrollPaneUsuarios, gbc_scrollPaneUsuarios);

		textAreaUsuarios = new JTextArea();
		textAreaUsuarios.setFont(new Font("Monospaced", Font.BOLD, 13));
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
		gbl_panelFiltrar.columnWidths = new int[] { 239, 182, 84, 0 };
		gbl_panelFiltrar.rowHeights = new int[] { 20, 0 };
		gbl_panelFiltrar.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelFiltrar.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelFiltrar.setLayout(gbl_panelFiltrar);

		textFieldFiltrar = new JTextField();
		GridBagConstraints gbc_textFieldFiltrar = new GridBagConstraints();
		gbc_textFieldFiltrar.fill = GridBagConstraints.BOTH;
		gbc_textFieldFiltrar.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldFiltrar.gridx = 0;
		gbc_textFieldFiltrar.gridy = 0;
		panelFiltrar.add(textFieldFiltrar, gbc_textFieldFiltrar);
		textFieldFiltrar.setColumns(10);

		comboBoxFiltrar = new JComboBox();
		comboBoxFiltrar.setModel(new DefaultComboBoxModel(TipoFiltro.values()));
		GridBagConstraints gbc_comboBoxFiltrar = new GridBagConstraints();
		gbc_comboBoxFiltrar.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxFiltrar.fill = GridBagConstraints.BOTH;
		gbc_comboBoxFiltrar.gridx = 1;
		gbc_comboBoxFiltrar.gridy = 0;
		panelFiltrar.add(comboBoxFiltrar, gbc_comboBoxFiltrar);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Map<Cliente, List<Arquivo>> retorno = new HashMap<>();
				TipoFiltro tf = null;
				try {
					textAreaChat.setText(null);
					comboBoxClientes.removeAllItems();
					comboBoxArquivos.removeAllItems();
					retorno = servidorCliente.procurarArquivo(textFieldFiltrar.getText(), tf,
							String.valueOf(comboBoxFiltrar.getSelectedItem()));

					for (Entry<Cliente, List<Arquivo>> entry : retorno.entrySet()) {
						Cliente cli = entry.getKey();

						textAreaChat.append(cli.getNome() + ": \n");
						comboBoxClientes.addItem(cli.getNome());

						for (int i = 0; i < entry.getValue().size(); i++) {
							Arquivo arq = entry.getValue().get(i);

							textAreaChat.append("  " + arq.getNome() + "  " + arq.getTamanho() + "\n  ");
							comboBoxArquivos.addItem(arq.getNome());
						}
					}

				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
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
		gbl_panelMain.columnWidths = new int[] { 14, 169, 93, 0 };
		gbl_panelMain.rowHeights = new int[] { 20, 0, 0, 0, 0 };
		gbl_panelMain.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panelMain.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelMain.setLayout(gbl_panelMain);

		JLabel lblIp = new JLabel("Porta server:");
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.anchor = GridBagConstraints.EAST;
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 0;
		panelMain.add(lblIp, gbc_lblIp);

		textFieldIPortaServer = new JTextField();
		textFieldIPortaServer.setText("1818");
		GridBagConstraints gbc_textFieldIPortaServer = new GridBagConstraints();
		gbc_textFieldIPortaServer.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldIPortaServer.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIPortaServer.gridx = 1;
		gbc_textFieldIPortaServer.gridy = 0;
		panelMain.add(textFieldIPortaServer, gbc_textFieldIPortaServer);
		textFieldIPortaServer.setColumns(10);

		btnAbrirServer = new JButton("Ligar server");
		btnAbrirServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iniciarServico();
			}
		});
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
		textFieldIPServer.setText("192.168.0.100");
		GridBagConstraints gbc_textFieldIPServer = new GridBagConstraints();
		gbc_textFieldIPServer.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldIPServer.fill = GridBagConstraints.BOTH;
		gbc_textFieldIPServer.gridx = 1;
		gbc_textFieldIPServer.gridy = 1;
		panelMain.add(textFieldIPServer, gbc_textFieldIPServer);
		textFieldIPServer.setColumns(10);

		btnFexarServer = new JButton("Fexar server");
		btnFexarServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fexarServer();
			}
		});
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
		textFieldPortaCliente.setText("1818");
		GridBagConstraints gbc_textFieldPortaCliente = new GridBagConstraints();
		gbc_textFieldPortaCliente.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPortaCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPortaCliente.gridx = 1;
		gbc_textFieldPortaCliente.gridy = 2;
		panelMain.add(textFieldPortaCliente, gbc_textFieldPortaCliente);
		textFieldPortaCliente.setColumns(10);

		btnConectarCliente = new JButton("Conectar cliente");
		btnConectarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conectarCliente();
			}
		});
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
		textFieldIPCliente.setText("192.168.0.100");
		GridBagConstraints gbc_textFieldIPCliente = new GridBagConstraints();
		gbc_textFieldIPCliente.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldIPCliente.fill = GridBagConstraints.BOTH;
		gbc_textFieldIPCliente.gridx = 1;
		gbc_textFieldIPCliente.gridy = 3;
		panelMain.add(textFieldIPCliente, gbc_textFieldIPCliente);
		textFieldIPCliente.setColumns(10);

		btnDesconectarCliente = new JButton("Desconectar cliente");
		btnDesconectarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldIPortaServer.setEnabled(true);
				try {
					if (servidorCliente != null) {
						servidorCliente.desconectar(cliente);
						servidorCliente = null;
					}
					textFieldPortaCliente.setEditable(true);
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(null, "erro ao desconectar do servidor");
					e.printStackTrace();
				}
			}
		});
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
		gbl_panelArquivos.columnWidths = new int[] { 0, 0 };
		gbl_panelArquivos.rowHeights = new int[] { 0, 0 };
		gbl_panelArquivos.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelArquivos.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panelArquivos.setLayout(gbl_panelArquivos);

		JScrollPane scrollPaneArquivos = new JScrollPane();
		GridBagConstraints gbc_scrollPaneArquivos = new GridBagConstraints();
		gbc_scrollPaneArquivos.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneArquivos.gridx = 0;
		gbc_scrollPaneArquivos.gridy = 0;
		panelArquivos.add(scrollPaneArquivos, gbc_scrollPaneArquivos);

		textAreaArquivos = new JTextArea();
		textAreaArquivos.setFont(new Font("Monospaced", Font.BOLD, 13));
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
		gbl_panelArquivosBtns.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelArquivosBtns.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panelArquivosBtns.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelArquivosBtns.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelArquivosBtns.setLayout(gbl_panelArquivosBtns);

		JLabel lblCliente = new JLabel("Cliente");
		GridBagConstraints gbc_lblCliente = new GridBagConstraints();
		gbc_lblCliente.anchor = GridBagConstraints.EAST;
		gbc_lblCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblCliente.gridx = 0;
		gbc_lblCliente.gridy = 0;
		panelArquivosBtns.add(lblCliente, gbc_lblCliente);

		comboBoxClientes = new JComboBox();
		comboBoxClientes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					for (Entry<Cliente, List<Arquivo>> map : mapaClientes.entrySet()) {
						Cliente cli = map.getKey();
						System.out.println(cli.getNome() + " : ");
						for (Arquivo arq : map.getValue()) {
							System.out.print(arq.getNome() + " ");
						}
						System.out.println();
					}
				}
			}
		});
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

		comboBoxArquivos = new JComboBox();
		comboBoxArquivos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					for (Entry<Cliente, List<Arquivo>> map : mapaClientes.entrySet()) {
						Cliente cli = map.getKey();
						System.out.println(cli.getNome() + " : ");
						for (Arquivo arq : map.getValue()) {
							System.out.print(arq.getNome() + " ");
						}
						System.out.println();
					}
				}
			}
		});
		GridBagConstraints gbc_comboBoxArquivos = new GridBagConstraints();
		gbc_comboBoxArquivos.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxArquivos.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxArquivos.gridx = 1;
		gbc_comboBoxArquivos.gridy = 1;
		panelArquivosBtns.add(comboBoxArquivos, gbc_comboBoxArquivos);

		JButton btnNewButton = new JButton("Baixar arquivos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				byte[] dados;
				TipoFiltro tf = null;
				try {
					Map<Cliente, List<Arquivo>> retornoD = servidorCliente.procurarArquivo(textFieldFiltrar.getText(), tf,
							String.valueOf(comboBoxArquivos.getSelectedItem()));

					for (Entry<Cliente, List<Arquivo>> entry : retornoD.entrySet()) {
						Cliente cli = entry.getKey();
						if (cli.getNome().equals(String.valueOf(comboBoxClientes.getSelectedItem()))) {
							for (int i = 0; i < entry.getValue().size(); i++) {
								Arquivo arq = entry.getValue().get(i);
								if (arq.getNome().equals(String.valueOf(comboBoxArquivos.getSelectedItem()))) {
									dados = servidor.baixarArquivo(cli, arq);
									escreva(new File(
											String.valueOf(String.valueOf(comboBoxArquivos.getSelectedItem()))), dados);
								}
							}
						}
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		panelArquivosBtns.add(btnNewButton, gbc_btnNewButton);

		
		username = System.getProperty("user.name");
		cliente.setId(1);
		cliente.setNome(username);
		cliente.setIp(mostrarIP());
		cliente.setPorta(intPorta);
		textFieldIPServer.setText(mostrarIP());
	}	
	
	public void escreva(File arq, byte[] dados) {
		try {
			Files.write(Paths.get(arq.getPath()), dados, StandardOpenOption.CREATE);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public String mostrarIP() {
		InetAddress IP;
		String IPString = null;
		try {
			IP = InetAddress.getLocalHost();
			IPString = IP.getHostAddress();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return IPString;
	}

	/**
	 * Formatador de data para informações no console. Ver:
	 * https://docs.oracle.com/javase/tutorial/i18n/format/simpleDateFormat.html
	 */
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm:ss:SSS");

	/**
	 * Registro onde o objeto exportado será buscado pelo nome o registro que
	 * escuta na porta TCP/IP.
	 */

	/**
	 * ReferÃªncia a esse prÃ³prio objeto depois de exportado, passado para o
	 * servidor.
	 */

	/**
	 * Meu nome, variÃ¡vel iniciada ao conectar no servidor.
	 */
	private String meunome;

	protected void conectarCliente() {

		// Endereço IP
		String host = textFieldIPCliente.getText().trim();
		if (!host.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
			JOptionPane.showMessageDialog(this, "O endereço ip parece invalido!");
			return;
		}

		// Porta
		String strPorta = textFieldPortaCliente.getText().trim();
		if (!strPorta.matches("[0-9]+") || strPorta.length() > 5) {
			JOptionPane.showMessageDialog(this, "A porta deve ser um valor numérico de no maximo 5 digitos!");
			return;
		}
		intPorta = Integer.parseInt(strPorta);

		// Iniciando objetos para conexao.
		try {
			registryCliente = LocateRegistry.getRegistry(host, intPorta);
			servidorCliente = (IServer) registryCliente.lookup(IServer.NOME_SERVICO);

			carregarArquivo();

			servidorCliente.registrarCliente(cliente);
			servidorCliente.publicarListaArquivos(cliente, listaArquivos);
			btnDesconectarCliente.setEnabled(true);

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}

	private void carregarArquivo() {
//		File dirStart = new File(".\\");// criar um file que sera o
		// diretorio
		 File dirStart = new File("C://Users//"+username+"//Desktop//Arquivo");
		for (File file : dirStart.listFiles()) {// para cada file da lista a
			// cima ele vai fazer:
			if (file.isFile()) {// se o file realmente for um file ele vai
				// executar
				Arquivo arq = new Arquivo();// criar um arquivo
				arq.setNome(file.getName());// colocar nome no arquivo
				arq.setTamanho(file.length());// colocar o tamanho no
				// arquivo
				int ex = file.getName().indexOf(".");// especificar que s
				// vai pegar a
				// extenso
				arq.setExtensao(file.getName().substring(ex));// colocar a
				// extenso
				// na esteno
				// do
				// arquivo
				arq.setPath(file.getPath());// colocar o path do file no
				// arquivo
				listaArquivos.add(arq);// adiciona o arquivo com todos os
				// dados a cima na lista de arquivos
			}
		}

	}

	protected void fexarServer() {
		mostrar("SERVIDOR PARANDO O SERVIÇO.");

		fecharTodosClientes();

		try {
			UnicastRemoteObject.unexportObject(this, true);
			UnicastRemoteObject.unexportObject(registry, true);

			btnAbrirServer.setEnabled(true);
			btnFexarServer.setEnabled(false);

			textFieldIPortaServer.setEnabled(true);

			mostrar("Serviço encerrado.");

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Desconecta todos os clientes.
	 */
	private void fecharTodosClientes() {
		mostrar("DESCONECTANDO TODOS OS CLIENTES.");
	}

	protected void iniciarServico() {

		// Copia do prof
		String strPorta = textFieldIPortaServer.getText().trim();

		if (!strPorta.matches("[0-9]+") || strPorta.length() > 5) {
			JOptionPane.showMessageDialog(this, "A porta deve ser um valor número de no maximo 5 digitos!");
			return;
		}
		int intPorta = Integer.parseInt(strPorta);
		if (intPorta < 1024 || intPorta > 65535) {
			JOptionPane.showMessageDialog(this, "A porta deve estar entre 1024 e 65535");
			return;
		}
		try {

			servidor = (IServer) UnicastRemoteObject.exportObject(this, 0);
			registry = LocateRegistry.createRegistry(intPorta);
			registry.rebind(IServer.NOME_SERVICO, servidor);

			mostrar("Serviço iniciado.");

			textFieldIPortaServer.setEnabled(false);
			btnAbrirServer.setEnabled(false);
			btnFexarServer.setEnabled(true);

		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this,
					"Erro criando registro, verifique se a porta já ão está sendo usada.");
			e.printStackTrace();
		}

	}

	/**
	 * Mostra no TextArea o texto recebido devidamente formatado e com data e
	 * hora.
	 * 
	 * @param string
	 */
	public void mostrar(String string) {
		textAreaChat.setBackground(Color.BLACK);
		textAreaChat.setForeground(Color.GREEN);
		textAreaChat.append(sdf.format(new Date()));
		textAreaChat.append(" -> ");
		textAreaChat.append(string);
		textAreaChat.append("\n");
	}

	public void run() {
		conectarCliente();
	}

	public void registrarCliente(Cliente c) throws RemoteException {
		listaClientes.add(c);
		textAreaUsuarios.setBackground(Color.BLACK);
		textAreaUsuarios.setForeground(Color.RED);
		textAreaUsuarios.append(c.getNome() + " se conectou.\n");
	}

	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		textAreaArquivos.setBackground(Color.BLACK);
		textAreaArquivos.setForeground(Color.WHITE);
		textAreaArquivos.append(c.getNome() + ":\n");// pega o nome do
		// cliente que ele vai
		// dar no argumento do
		// metodo
		for (Arquivo arq : lista) {// pra cada arquivo da lista de arquivos:

			textAreaArquivos.append("   " + arq.getNome() + "\n");// imprime
			// o
			// nome
			// do
			// arquivo

		}
		mapaClientes.put(c, lista);// adiciona no mapa de clientes o cliente e a
		// lista de arquivos dele que foi criada
		// logo a cima
	}

	public Map<Cliente, List<Arquivo>> procurarArquivo(String query, TipoFiltro tipoFiltro, String filtro)
			throws RemoteException {
		List<Arquivo> ListaArquivoFiltrado = new ArrayList<>();
		Map<Cliente, List<Arquivo>> mapaFiltrado = new HashMap<>();
		Pattern pat = Pattern.compile(".*" + query + ".*");
		
		if (filtro.equals(String.valueOf(tipoFiltro.NOME))) {
			for (Entry<Cliente, List<Arquivo>> entry : mapaClientes.entrySet()) {
				Cliente cli = entry.getKey();
				for (int i = 0; i < entry.getValue().size(); i++) {
					Arquivo arqui = entry.getValue().get(i);
					Matcher m = pat.matcher(arqui.getNome().toLowerCase());
					if (m.matches()) {
						ListaArquivoFiltrado.clear();
						ListaArquivoFiltrado.add(arqui);
						mapaFiltrado.put(entry.getKey(), ListaArquivoFiltrado);
					}
				}
			}
		}

		if (filtro.equals(String.valueOf(tipoFiltro.EXTENSAO))) {

			for (Entry<Cliente, List<Arquivo>> entry : mapaClientes.entrySet()) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					Arquivo arq = entry.getValue().get(i);
					Matcher m = pat.matcher(arq.getExtensao().toLowerCase());
					if (m.matches()) {
						ListaArquivoFiltrado.clear();
						ListaArquivoFiltrado.add(entry.getValue().get(i));
						mapaFiltrado.put(entry.getKey(), ListaArquivoFiltrado);
					}
				}
			}
		}
		if (filtro.equals(String.valueOf(tipoFiltro.TAMANHO_MIN))) {
			for (Entry<Cliente, List<Arquivo>> entry : mapaClientes.entrySet()) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					Arquivo arq = entry.getValue().get(i);

					if (arq.getTamanho() >= Long.parseLong(query)) {
						ListaArquivoFiltrado.clear();
						ListaArquivoFiltrado.add(entry.getValue().get(i));
						mapaFiltrado.put(entry.getKey(), ListaArquivoFiltrado);
					}
				}
			}
		}
		if (filtro.equals(String.valueOf(tipoFiltro.TAMANHO_MAX))) {
			for (Entry<Cliente, List<Arquivo>> entry : mapaClientes.entrySet()) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					Arquivo arq = entry.getValue().get(i);
					if (arq.getTamanho() <= Long.parseLong(query)) {
						ListaArquivoFiltrado.clear();
						ListaArquivoFiltrado.add(entry.getValue().get(i));
						mapaFiltrado.put(entry.getKey(), ListaArquivoFiltrado);
					}
				}
			}
		}
		return mapaFiltrado;
	}

	public byte[] baixarArquivo(Cliente cli, Arquivo arq) throws RemoteException {
		byte[] dados = null;
		Path path = Paths.get(arq.getPath());
		try {
			dados = Files.readAllBytes(path);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return dados;
	}

	public void desconectar(Cliente c) throws RemoteException {
		c.getNome();
		listaClientes.add(c);
		textAreaUsuarios.setBackground(Color.BLACK);
		textAreaUsuarios.setForeground(Color.RED);
		textAreaUsuarios.append(c.getNome() + " se desconectou.\n");
	}
}
