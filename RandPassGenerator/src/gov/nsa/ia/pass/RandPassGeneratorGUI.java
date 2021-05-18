package gov.nsa.ia.pass;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import gov.nsa.ia.util.CustomOutputStream;
import gov.nsa.ia.util.OptionManager;

public class RandPassGeneratorGUI extends JFrame {

	protected static final String VERSION = "RandPassGen 1.3 - 1 Oct 2018";

	private JPanel contentPane;

	OptionManager opt = RandPassGenerator.makeOptions();

	protected int errs;

	protected PrintWriter pw;

	protected boolean pwNeedsClose;

	protected String logfile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RandPassGeneratorGUI frame = new RandPassGeneratorGUI();
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
	public RandPassGeneratorGUI() {
		setTitle("RandPassGenerator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		contentPane.add(tabbedPane, gbc_tabbedPane);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.1);
		splitPane.setEnabled(false);
		tabbedPane.addTab("Passwords", null, splitPane, null);

		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNumPasswords = new JLabel("Num Passwords");
		GridBagConstraints gbc_lblNumPasswords = new GridBagConstraints();
		gbc_lblNumPasswords.fill = GridBagConstraints.BOTH;
		gbc_lblNumPasswords.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumPasswords.gridx = 0;
		gbc_lblNumPasswords.gridy = 0;
		panel.add(lblNumPasswords, gbc_lblNumPasswords);

		JSpinner spinner = new JSpinner();
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.BOTH;
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 0;
		panel.add(spinner, gbc_spinner);

		JLabel lblStrength = new JLabel("Strength");
		GridBagConstraints gbc_lblStrength = new GridBagConstraints();
		gbc_lblStrength.fill = GridBagConstraints.BOTH;
		gbc_lblStrength.insets = new Insets(0, 0, 5, 5);
		gbc_lblStrength.gridx = 0;
		gbc_lblStrength.gridy = 1;
		panel.add(lblStrength, gbc_lblStrength);

		JSlider slider = new JSlider();
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.fill = GridBagConstraints.BOTH;
		gbc_slider.insets = new Insets(0, 0, 5, 0);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 1;
		panel.add(slider, gbc_slider);
		slider.setValue(256);
		slider.setMajorTickSpacing(32);
		slider.setMinorTickSpacing(32);
		slider.setMinimum(128);
		slider.setMaximum(256);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);

		JLabel lblPasswordCharset = new JLabel("Password Charset");
		GridBagConstraints gbc_lblPasswordCharset = new GridBagConstraints();
		gbc_lblPasswordCharset.fill = GridBagConstraints.BOTH;
		gbc_lblPasswordCharset.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswordCharset.gridx = 0;
		gbc_lblPasswordCharset.gridy = 2;
		panel.add(lblPasswordCharset, gbc_lblPasswordCharset);

		JComboBox comboBox_2 = new JComboBox();
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.fill = GridBagConstraints.BOTH;
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_2.gridx = 1;
		gbc_comboBox_2.gridy = 2;
		panel.add(comboBox_2, gbc_comboBox_2);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "a0" }));
		comboBox_2.setEditable(true);

		JLabel lblNewLabel = new JLabel("Verbose");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		JCheckBox chckbxVerbose = new JCheckBox("");
		GridBagConstraints gbc_chckbxVerbose = new GridBagConstraints();
		gbc_chckbxVerbose.fill = GridBagConstraints.BOTH;
		gbc_chckbxVerbose.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxVerbose.gridx = 1;
		gbc_chckbxVerbose.gridy = 3;
		panel.add(chckbxVerbose, gbc_chckbxVerbose);

		JButton btnGenerate = new JButton("Generate");
		GridBagConstraints gbc_btnGenerate = new GridBagConstraints();
		gbc_btnGenerate.fill = GridBagConstraints.BOTH;
		gbc_btnGenerate.gridx = 1;
		gbc_btnGenerate.gridy = 4;
		panel.add(btnGenerate, gbc_btnGenerate);
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String args[] = null;

				if (chckbxVerbose.isSelected()) {
					args = new String[7];
					args[6] = "-v";
				} else if (!chckbxVerbose.isSelected())
					args = new String[6];

				args[0] = "-str";
				args[1] = String.valueOf(slider.getValue());
				args[2] = "-pw";
				args[3] = String.valueOf(spinner.getValue());
				args[4] = "-pwcs";
				args[5] = (String) comboBox_2.getSelectedItem();

				// parse GUI args
				errs = opt.parseOptions(args);
				if (errs > 0) {
					System.err.println("Command line had " + errs + " errors.");
					System.err.println("Please fix errors and try again.  Exiting.");
					return;
				}

				int strength = opt.getValueAsInt("strength");
				int numPasswords = opt.getValueAsInt("passwords");
				boolean verbose = opt.getValueAsBoolean("verbose");
				String passwordCharset = opt.getValue("passchars");

				// check for something to do
				if (numPasswords <= 0) {
					System.err.println("No keys, passwords, or passphrases requested.  Exiting.");
					return;
				}

				pw = new PrintWriter(System.out, true);
				pwNeedsClose = false;

				// ready to create the RandPassGenerator
				if (verbose) {
					System.err.println(VERSION);
					System.err.println("About to start initialization, requested key/passwd strength = " + strength);
				}

				logfile = null;
				System.err.println("Logging will go to stderr.");

				RandPassGenerator rpg = null;
				try {
					rpg = new RandPassGenerator(logfile, pw, verbose);
				} catch (Exception e1) {
					System.err.println("Could not initialize RandPassGenerator: " + e1);
					if (verbose) {
						e1.printStackTrace(System.err);
					}
					return;
				}

				// Do the work that the user asked of us
				int cnt;
				if (rpg != null) {
					if (numPasswords > 0) {
						cnt = rpg.generatePasswords(numPasswords, strength, passwordCharset);
						if (cnt <= 0) {
							rpg.message("Failed to generate passwords");
							rpg.getLogger().warning("Tried to generate " + numPasswords + " passwords, but failed.");
						} else {
							rpg.message("Generated " + cnt + " passwords at strength " + strength);
						}
					}

					rpg.close();
				}

				// flush the output PrintWriter if necessary
				if (pw != null) {
					if (pw.checkError()) {
						System.err.println("Error: output stream reported an error; output might not be complete.");
					}
				}
			}
		});

		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		panel_1.add(scrollPane_1, gbc_scrollPane_1);

		JTextArea txtr_1 = new JTextArea();
		txtr_1.setFont(new Font("Monospaced", Font.PLAIN, 13));
		PrintStream printStream = new PrintStream(new CustomOutputStream(txtr_1));
		System.setOut(printStream);
		System.setErr(printStream);
		txtr_1.setWrapStyleWord(true);
		txtr_1.setTabSize(4);
		txtr_1.setRows(1);
		txtr_1.setLineWrap(true);
		txtr_1.setEditable(false);
		txtr_1.setColumns(1);
		scrollPane_1.setViewportView(txtr_1);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setContinuousLayout(true);
		splitPane_1.setResizeWeight(0.1);
		tabbedPane.addTab("Passphrases", null, splitPane_1, null);

		JPanel panel_2 = new JPanel();
		splitPane_1.setLeftComponent(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 1.0, 1.0 };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JLabel lblNumPassphrases = new JLabel("Num Passphrases");
		GridBagConstraints gbc_lblNumPassphrases = new GridBagConstraints();
		gbc_lblNumPassphrases.fill = GridBagConstraints.BOTH;
		gbc_lblNumPassphrases.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumPassphrases.gridx = 0;
		gbc_lblNumPassphrases.gridy = 0;
		panel_2.add(lblNumPassphrases, gbc_lblNumPassphrases);

		JSpinner spinner_1 = new JSpinner();
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_1.fill = GridBagConstraints.BOTH;
		gbc_spinner_1.gridx = 1;
		gbc_spinner_1.gridy = 0;
		panel_2.add(spinner_1, gbc_spinner_1);

		JLabel lblNewLabel_1 = new JLabel("Passphrases URL");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JFormattedTextField formattedTextField = new JFormattedTextField();
		GridBagConstraints gbc_formattedTextField = new GridBagConstraints();
		gbc_formattedTextField.insets = new Insets(0, 0, 5, 0);
		gbc_formattedTextField.fill = GridBagConstraints.BOTH;
		gbc_formattedTextField.gridx = 1;
		gbc_formattedTextField.gridy = 1;
		panel_2.add(formattedTextField, gbc_formattedTextField);

		JLabel lblStrength_1 = new JLabel("Strength");
		GridBagConstraints gbc_lblStrength_1 = new GridBagConstraints();
		gbc_lblStrength_1.fill = GridBagConstraints.BOTH;
		gbc_lblStrength_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblStrength_1.gridx = 0;
		gbc_lblStrength_1.gridy = 2;
		panel_2.add(lblStrength_1, gbc_lblStrength_1);

		JSlider slider_1 = new JSlider();
		slider_1.setValue(256);
		slider_1.setSnapToTicks(true);
		slider_1.setPaintTicks(true);
		slider_1.setPaintLabels(true);
		slider_1.setMinorTickSpacing(32);
		slider_1.setMinimum(128);
		slider_1.setMaximum(256);
		slider_1.setMajorTickSpacing(32);
		GridBagConstraints gbc_slider_1 = new GridBagConstraints();
		gbc_slider_1.insets = new Insets(0, 0, 5, 0);
		gbc_slider_1.fill = GridBagConstraints.BOTH;
		gbc_slider_1.gridx = 1;
		gbc_slider_1.gridy = 2;
		panel_2.add(slider_1, gbc_slider_1);

		JLabel lblNewLabel_2 = new JLabel("Maximum Word Length");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		panel_2.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Verbose");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 4;
		panel_2.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JCheckBox chckbxVerbose_1 = new JCheckBox("");
		GridBagConstraints gbc_chckbxVerbose_1 = new GridBagConstraints();
		gbc_chckbxVerbose_1.fill = GridBagConstraints.BOTH;
		gbc_chckbxVerbose_1.gridx = 1;
		gbc_chckbxVerbose_1.gridy = 4;
		panel_2.add(chckbxVerbose_1, gbc_chckbxVerbose_1);

		JPanel panel_3 = new JPanel();
		splitPane_1.setRightComponent(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 0, 0 };
		gbl_panel_3.rowHeights = new int[] { 0, 0 };
		gbl_panel_3.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_3.add(scrollPane, gbc_scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}

}
