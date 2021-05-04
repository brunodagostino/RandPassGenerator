package gov.nsa.ia.pass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RandPassGeneratorGUI extends JFrame {

	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNumPasswords = new JLabel("Num Passwords");
		GridBagConstraints gbc_lblNumPasswords = new GridBagConstraints();
		gbc_lblNumPasswords.anchor = GridBagConstraints.EAST;
		gbc_lblNumPasswords.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumPasswords.gridx = 0;
		gbc_lblNumPasswords.gridy = 0;
		contentPane.add(lblNumPasswords, gbc_lblNumPasswords);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		contentPane.add(comboBox, gbc_comboBox);
		
		JLabel lblStrength = new JLabel("Strength");
		GridBagConstraints gbc_lblStrength = new GridBagConstraints();
		gbc_lblStrength.anchor = GridBagConstraints.EAST;
		gbc_lblStrength.insets = new Insets(0, 0, 5, 5);
		gbc_lblStrength.gridx = 0;
		gbc_lblStrength.gridy = 1;
		contentPane.add(lblStrength, gbc_lblStrength);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"128", "160", "192", "256"}));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 1;
		contentPane.add(comboBox_1, gbc_comboBox_1);
		
		JLabel lblPasswordCharset = new JLabel("Password Charset");
		GridBagConstraints gbc_lblPasswordCharset = new GridBagConstraints();
		gbc_lblPasswordCharset.anchor = GridBagConstraints.EAST;
		gbc_lblPasswordCharset.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswordCharset.gridx = 0;
		gbc_lblPasswordCharset.gridy = 2;
		contentPane.add(lblPasswordCharset, gbc_lblPasswordCharset);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"a0"}));
		comboBox_2.setEditable(true);
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 1;
		gbc_comboBox_2.gridy = 2;
		contentPane.add(comboBox_2, gbc_comboBox_2);
		
		JCheckBox chckbxVerbose = new JCheckBox("Verbose");
		GridBagConstraints gbc_chckbxVerbose = new GridBagConstraints();
		gbc_chckbxVerbose.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxVerbose.gridx = 0;
		gbc_chckbxVerbose.gridy = 4;
		contentPane.add(chckbxVerbose, gbc_chckbxVerbose);
	}

}
