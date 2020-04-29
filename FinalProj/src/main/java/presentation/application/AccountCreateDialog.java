package presentation.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import business.UserService;
import enums.Failures;

/**
 * @author Joseph Perez, Andrew Ammentorp, Leighton Glim, Joshua Huertas, Joseph
 *         Yu
 *
 *         application class responsible creating an account
 */
public class AccountCreateDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	JTextField name;
	JTextField baylorEmail;
	JTextField phoneNum;
	String month;
	String year;
	JPasswordField password;
	JPasswordField confirmPassword;
	private boolean succeeded;
	Font customFont;
	String[] months = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	String[] years = new String[8];

	// Global
	JComboBox<String> gradMonth;
	JComboBox<String> gradYear;

	/**
	 * Creates the account creation dialog
	 * 
	 * @param parent The frame responsible for encapsulating the data
	 * @return
	 */
	public AccountCreateDialog(JFrame parent) {
		super(parent, "Create Account", true);

		succeeded = false;

		// Future proofed year selection
		Integer cur = Calendar.getInstance().get(Calendar.YEAR);
		for (Integer i = 0; i < years.length; i++) {
			Integer yr = cur + i;
			years[i] = yr.toString();
		}

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		// initialize labels
		gradMonth = new JComboBox<String>(months);
		gradYear = new JComboBox<String>(years);
		JLabel userLabel = new JLabel("Name: ");
		JLabel emailLabel = new JLabel("Baylor Email: ");
		JLabel phoneLabel = new JLabel("Phone: ");
		JLabel gradMonthLabel = new JLabel("Grad Month: ");
		JLabel gradYearLabel = new JLabel("Grad Year: ");
		JLabel passwordLabel = new JLabel("Password: ");
		JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");

		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/OpenSans-Bold.ttf"))
					.deriveFont(12f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			// register the font
			ge.registerFont(customFont);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}

		cs.fill = GridBagConstraints.HORIZONTAL;

		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		userLabel.setFont(customFont);
		panel.add(userLabel, cs);

		name = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(name, cs);

		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		emailLabel.setFont(customFont);
		panel.add(emailLabel, cs);

		baylorEmail = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(baylorEmail, cs);

		cs.gridx = 0;
		cs.gridy = 2;
		cs.gridwidth = 2;
		phoneLabel.setFont(customFont);
		panel.add(phoneLabel, cs);

		phoneNum = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 2;
		cs.gridwidth = 2;
		panel.add(phoneNum, cs);

		gradMonth.setSelectedIndex(-1);
		gradYear.setSelectedIndex(-1);

		cs.gridx = 0;
		cs.gridy = 3;
		cs.gridwidth = 2;
		gradMonthLabel.setFont(customFont);
		panel.add(gradMonthLabel, cs);

		cs.gridx = 1;
		cs.gridy = 3;
		cs.gridwidth = 2;
		panel.add(gradMonth, cs);

		cs.gridx = 0;
		cs.gridy = 4;
		cs.gridwidth = 2;
		gradYearLabel.setFont(customFont);
		panel.add(gradYearLabel, cs);

		cs.gridx = 1;
		cs.gridy = 4;
		cs.gridwidth = 2;
		panel.add(gradYear, cs);

		cs.gridx = 0;
		cs.gridy = 5;
		cs.gridwidth = 2;
		passwordLabel.setFont(customFont);
		panel.add(passwordLabel, cs);

		password = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 5;
		cs.gridwidth = 2;
		panel.add(password, cs);

		cs.gridx = 0;
		cs.gridy = 6;
		cs.gridwidth = 1;
		confirmPasswordLabel.setFont(customFont);
		panel.add(confirmPasswordLabel, cs);

		confirmPassword = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 6;
		cs.gridwidth = 3;
		panel.add(confirmPassword, cs);

		JButton createAccount = new JButton("Create account");
		createAccount.setFont(customFont);
		createAccount.setBackground(new Color(255, 184, 25));
		createAccount.setFont(customFont);
		createAccount.setBorderPainted(false);
		createAccount.setOpaque(true);

		createAccount.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent event) {
				month = String.valueOf(gradMonth.getSelectedItem());
				year = String.valueOf(gradYear.getSelectedItem());
				String pass = new String(password.getPassword());
				String rePass = new String(confirmPassword.getPassword());

				// ensure input is valid
				Failures result = UserService.getInstance().verify(
						new String[] { name.getText(), baylorEmail.getText(), phoneNum.getText(), pass, rePass, month, year });

				if (result == Failures.BadDate) {
					
				}
				
				// SUCCESS route
				if (result == Failures.SUCCESS) {
					ImageIcon icon = new ImageIcon("src/main/resources/poolfloat icon-yellow.png");
					JOptionPane.showMessageDialog(null, "Hi " + UserService.getInstance().getCurrentUser().getUsername() + "! Welcome to Bearpool!", "Login",
							JOptionPane.INFORMATION_MESSAGE, icon);
					Application.log.log(Level.INFO, UserService.getInstance().getCurrentUser().getUsername() + " Login successful!");
					dispose();
				}

			}
		});
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(customFont);
		btnCancel.setFont(customFont);
		btnCancel.setBackground(new Color(255, 184, 25));
		btnCancel.setFont(customFont);
		btnCancel.setBorderPainted(false);
		btnCancel.setOpaque(true);
		btnCancel.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				Application.log.log(Level.INFO, "Sign up canceled");
				dispose();
			}
		});

		JPanel bp = new JPanel();
		bp.add(createAccount);
		bp.add(btnCancel);
		bp.setBackground(new Color(28, 60, 52));

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		panel.setBackground(new Color(255, 184, 25));

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}

	/**
	 * Returns if the account creation was successful
	 * 
	 * @param
	 * @return succeeded if the login succeeded
	 */
	public boolean isSucceeded() {
		return succeeded;
	}

}
