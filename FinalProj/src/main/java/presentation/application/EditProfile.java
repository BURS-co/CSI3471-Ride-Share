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
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
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
import data.user.Admin;
import data.user.User;

/**
 * @author Joseph Perez, Andrew Ammentorp, Leighton Glim
 *
 *         Class responsible for editing your profile
 */
public class EditProfile extends JDialog {
	private static final long serialVersionUID = 1L;
	JFrame frame;
	JTextField name;
	JTextField baylorEmail;
	JTextField phoneNum;
	String month;
	String year;
	JPasswordField password;
	JPasswordField confirmPassword;
	private boolean succeeded;
	Font customFont = null;
	public static User u = new User();
	String[] months = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	String[] years = { "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027" };
	
	JComboBox<String> gradMonth;
	JComboBox<String> gradYear;
	

	/**
	 * Creates the functionality to edit your profile
	 * 
	 * @param parent the frame for the prompts to be put on
	 * @param u      the user in question
	 */
	public EditProfile(JFrame parent, final User u) {
		super(parent, "Edit Profile", true);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

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

		name = new JTextField(12);
		name.setText(u.getUsername());
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
		baylorEmail.setText(u.getEmail());
		baylorEmail.setEditable(false);
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
		phoneNum.setText(u.getPhoneNumber());
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

		JButton createAccount = new JButton("Save Changes");
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

				if (ValidateAccountInfo.validateAccountInfoEntered(name.getText(), baylorEmail.getText(), phoneNum.getText(),
						pass, rePass, month, year)) {
					succeeded = true;

					User user = null;
					if (Application.loggedIn instanceof Admin)
						user = new Admin();
					else
						user = new User();
					user.setUsername(name.getText());
					user.setEmail(baylorEmail.getText());
					user.setPhoneNumber(phoneNum.getText());
					user.setGradMonth(month);
					user.setGradYear(year);
					user.setPassword(new String(password.getPassword()));

					String filePath = "userDatabase.txt";
					Scanner sc = null;
					try {
						sc = new Scanner(new File(filePath));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					StringBuffer buffer = new StringBuffer();
					while (sc.hasNextLine()) {
						buffer.append(sc.nextLine() + System.lineSeparator());
					}
					String fileContents = buffer.toString();
					sc.close();
					String oldLine = u.toString();
					String newLine = user.toString();
					// Replacing the old line with new line
					fileContents = fileContents.replaceAll(oldLine, newLine);
					FileWriter writer = null;
					try {
						writer = new FileWriter(filePath);
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						writer.append(fileContents);
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						writer.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}

					ImageIcon icon = new ImageIcon("src/main/resources/poolfloat icon-yellow.png");
					JOptionPane.showMessageDialog(null, "Changes successfully made. ", "Edit Profile",
							JOptionPane.INFORMATION_MESSAGE, icon);
					succeeded = true;
					Application.log.log(Level.INFO, user.getUsername() + "'s Profile Edited successfully");
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
				Application.log.log(Level.INFO, "Edit Profile canceled");
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
	 * Returns if editing your profile succeeded
	 * 
	 * @return the boolean value corresponding to a successful editing
	 */
	public boolean isSucceeded() {
		return succeeded;
	}
}
