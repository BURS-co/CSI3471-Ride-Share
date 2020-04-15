package presentation.application;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import business.validateAccountInfo;
import data.user.User;

public class AccountCreateDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	JFrame frame;
	JTextField name;
	JTextField baylorEmail;
	JTextField phoneNum;
//    JTextField		gradYear;
	String month = new String();
	String year = new String();
	JPasswordField password;
	JPasswordField confirmPassword;
	validateAccountInfo vaI;
	private boolean succeeded = false;
	private JButton btnCancel;

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

		cs.fill = GridBagConstraints.HORIZONTAL;

		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(userLabel, cs);

		name = new JTextField(12);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(name, cs);

		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(emailLabel, cs);

		baylorEmail = new JTextField(12);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(baylorEmail, cs);

		cs.gridx = 0;
		cs.gridy = 2;
		cs.gridwidth = 2;
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
		panel.add(gradMonthLabel, cs);

		cs.gridx = 1;
		cs.gridy = 3;
		cs.gridwidth = 2;
		panel.add(gradMonth, cs);

		cs.gridx = 0;
		cs.gridy = 4;
		cs.gridwidth = 2;
		panel.add(gradYearLabel, cs);

		cs.gridx = 1;
		cs.gridy = 4;
		cs.gridwidth = 2;
		panel.add(gradYear, cs);

		cs.gridx = 0;
		cs.gridy = 5;
		cs.gridwidth = 2;
		panel.add(passwordLabel, cs);

		password = new JPasswordField(12);
		cs.gridx = 1;
		cs.gridy = 5;
		cs.gridwidth = 2;
		panel.add(password, cs);

		cs.gridx = 0;
		cs.gridy = 6;
		cs.gridwidth = 1;
		panel.add(confirmPasswordLabel, cs);

		confirmPassword = new JPasswordField(12);
		cs.gridx = 1;
		cs.gridy = 6;
		cs.gridwidth = 3;
		panel.add(confirmPassword, cs);

		JButton createAccount = new JButton("Create account");
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

							// UserDatabase.getUserData().add(user);
							Application.userDatabase.add(user);
							
							//Keep track of user logged in
							Application.loggedIn.setEmail(user.getEmail());
							Application.loggedIn.setGradMonth(user.getGradMonth());
							Application.loggedIn.setGradYear(user.getGradYear());
							Application.loggedIn.setPassword(user.getPassword());
							Application.loggedIn.setPhoneNumber(user.getPhoneNumber());
							Application.loggedIn.setUsername(user.getUsername());
							
							JOptionPane.showMessageDialog(AccountCreateDialog.this,
									"Hi " + user.getUsername() + "! Welcome to Bearpool!", "Login", JOptionPane.INFORMATION_MESSAGE);
							succeeded = true;
							Application.log.log(Level.INFO, user.getUsername() + " Login successful!");
							dispose();

						}
				}
			}
		});
		btnCancel = new JButton("Cancel");
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
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

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

}
