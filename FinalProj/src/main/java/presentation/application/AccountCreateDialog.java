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

import business.ValidateAccountInfo;
import data.user.User;

public class AccountCreateDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	JFrame frame;
	JTextField name;
	JTextField baylorEmail;
	JTextField phoneNum;
	String month = new String();
	String year = new String();
	JPasswordField password;
	JPasswordField confirmPassword;
	ValidateAccountInfo vaI;
	private boolean succeeded = false;
	private JButton btnCancel;
	Font customFont = null;
	User u = new User();

	String[] months = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	String[] years = { "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027" };

	/**
	 * @param parent
	 * @return
	 */
	public AccountCreateDialog(JFrame parent) {
		super(parent, "Create Account", true);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		JComboBox gradMonth = new JComboBox(months);
		JComboBox gradYear = new JComboBox(years);
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

		name = new JTextField(12);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(name, cs);

		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		emailLabel.setFont(customFont);
		panel.add(emailLabel, cs);

		baylorEmail = new JTextField(12);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(baylorEmail, cs);

		cs.gridx = 0;
		cs.gridy = 2;
		cs.gridwidth = 2;
		phoneLabel.setFont(customFont);
		panel.add(phoneLabel, cs);

		phoneNum = new JTextField(12);
		cs.gridx = 1;
		cs.gridy = 2;
		cs.gridwidth = 2;
		panel.add(phoneNum, cs);

		gradMonth.setSelectedIndex(0);
		gradYear.setSelectedIndex(0);
		gradMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				month = (String) cb.getSelectedItem();
			}
		});
		gradYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				year = (String) cb.getSelectedItem();
			}
		});

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

		password = new JPasswordField(12);
		cs.gridx = 1;
		cs.gridy = 5;
		cs.gridwidth = 2;
		panel.add(password, cs);

		cs.gridx = 0;
		cs.gridy = 6;
		cs.gridwidth = 1;
		confirmPasswordLabel.setFont(customFont);
		panel.add(confirmPasswordLabel, cs);

		confirmPassword = new JPasswordField(12);
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
			@SuppressWarnings("static-access")
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent event) {
				if (name.getText().length() == 0 || baylorEmail.getText().length() == 0 || phoneNum.getText().length() == 0
						|| password.getText().length() == 0 || confirmPassword.getText().length() == 0 || month.length() == 0
						|| year.length() == 0) {
					JOptionPane.showMessageDialog(AccountCreateDialog.this, "Please fill in all fields.", "Create Account",
							JOptionPane.INFORMATION_MESSAGE);
					succeeded = false;

				} else {
					// make sure text entered in all fields
					if (name.getText().length() > 1 && baylorEmail.getText().length() > 1 && phoneNum.getText().length() > 1
							&& password.getText().length() > 1 && confirmPassword.getText().length() > 1 && month.length() > 1
							&& year.length() > 1)
						if (vaI.validateAccountInfoEntered(name.getText(), baylorEmail.getText(), phoneNum.getText(),
								password.getText(), confirmPassword.getText(), month, year, AccountCreateDialog.this)) {

							User user = new User();
							user.setUsername(name.getText());
							user.setEmail(baylorEmail.getText());
							user.setPhoneNumber(phoneNum.getText());
							user.setGradMonth(month);
							user.setGradYear(year);
							user.setPassword(new String(password.getPassword()));

							setUser(u);

							// UserDatabase.getUserData().add(user);
							Application.userDatabase.add(user);

							// Keep track of user logged in
							Application.loggedIn.setEmail(user.getEmail());
							Application.loggedIn.setGradMonth(user.getGradMonth());
							Application.loggedIn.setGradYear(user.getGradYear());
							Application.loggedIn.setPassword(user.getPassword());
							Application.loggedIn.setPhoneNumber(user.getPhoneNumber());
							Application.loggedIn.setUsername(user.getUsername());

							ImageIcon icon = new ImageIcon("src/main/resources/poolfloat icon-yellow.png");
							JOptionPane.showMessageDialog(null, "Hi " + user.getUsername() + "! Welcome to Bearpool!", "Login",
									JOptionPane.INFORMATION_MESSAGE, icon);
							succeeded = true;
							Application.log.log(Level.INFO, user.getUsername() + " Login successful!");
							dispose();

						}
				}
			}
		});
		btnCancel = new JButton("Cancel");
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
	 * @param
	 * @return succeeded
	 */
	public boolean isSucceeded() {
		return succeeded;
	}

	public void setUser(User user) {
		this.u = user;
	}

	public User getUser() {
		return u;
	}

}
