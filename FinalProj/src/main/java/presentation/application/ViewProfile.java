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
import java.util.Collections;
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

/**
 * @author Joseph Perez, Andrew Ammentorp, Leighton Glim
 *
 *         Class responsible for a User viewing their profile
 */
public class ViewProfile extends JDialog {
	private static final long serialVersionUID = 1L;
	JFrame frame;
	JTextField nameEdit;
	JTextField baylorEmailEdit;
	JTextField phoneNumEdit;
	String month = new String();
	String year = new String();
	JPasswordField password;
	JPasswordField confirmPassword;
	ValidateAccountInfo vaI;
	private boolean succeeded = false;
	Font customFont = null;

	String[] months = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	String[] years = { "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027" };

	/**
	 * Creates and displays the user's profile
	 * 
	 * @param parent the frame to be displayed on
	 * @param u the user logged in
	 */
	public ViewProfile(JFrame parent, User u) {
		super(parent, "View Profile", true);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		JLabel userLabel = new JLabel("Name: ");
		JLabel emailLabel = new JLabel("Baylor Email: ");
		JLabel phoneLabel = new JLabel("Phone: ");
		JLabel gradMonthLabel = new JLabel("Grad Month: ");
		JLabel gradYearLabel = new JLabel("Grad Year: ");
		JLabel passwordLabel = new JLabel("Password: ");

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

		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		JLabel name = new JLabel(u.getUsername());
		name.setFont(customFont);
		panel.add(name, cs);

		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		emailLabel.setFont(customFont);
		panel.add(emailLabel, cs);

		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		JLabel baylorEmail = new JLabel(u.getEmail());
		baylorEmail.setFont(customFont);
		panel.add(baylorEmail, cs);

		cs.gridx = 0;
		cs.gridy = 2;
		cs.gridwidth = 2;
		phoneLabel.setFont(customFont);
		panel.add(phoneLabel, cs);

		phoneNumEdit = new JTextField(12);
		cs.gridx = 1;
		cs.gridy = 2;
		cs.gridwidth = 2;
		JLabel phoneNum = new JLabel(u.getPhoneNumber());
		phoneNum.setFont(customFont);
		panel.add(phoneNum, cs);

		cs.gridx = 0;
		cs.gridy = 3;
		cs.gridwidth = 2;
		gradMonthLabel.setFont(customFont);
		panel.add(gradMonthLabel, cs);

		cs.gridx = 1;
		cs.gridy = 3;
		cs.gridwidth = 2;
		JLabel gradMonth = new JLabel(u.getGradMonth());
		gradMonth.setFont(customFont);
		panel.add(gradMonth, cs);

		cs.gridx = 0;
		cs.gridy = 4;
		cs.gridwidth = 2;
		gradYearLabel.setFont(customFont);
		panel.add(gradYearLabel, cs);

		cs.gridx = 1;
		cs.gridy = 4;
		cs.gridwidth = 2;
		JLabel gradYear = new JLabel(u.getGradYear());
		gradYear.setFont(customFont);
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
		String pass = String.join("", Collections.nCopies(u.getPassword().length(), "*"));
		JLabel password = new JLabel(pass);
		password.setFont(customFont);
		panel.add(password, cs);

		JButton editProfile = new JButton("Edit Profile");
		editProfile.setFont(customFont);
		editProfile.setBackground(new Color(255, 184, 25));
		editProfile.setFont(customFont);
		editProfile.setBorderPainted(false);
		editProfile.setOpaque(true);
		editProfile.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent event) {
				EditProfile ep = new EditProfile(parent, u);
				ep.setVisible(true);
				if (ep.isSucceeded()) {
					succeeded = true;
					name.setText(EditProfile.getUser().getUsername());
					baylorEmail.setText(EditProfile.getUser().getEmail());
					phoneNum.setText(EditProfile.getUser().getPhoneNumber());
					gradMonth.setText(EditProfile.getUser().getGradMonth());
					gradYear.setText(EditProfile.getUser().getGradYear());
					String pass = String.join("", Collections.nCopies(EditProfile.getUser().getPassword().length(), "*"));
					password.setText(pass);

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
				Application.log.log(Level.INFO, "View Profile Closed");
				dispose();
			}
		});

		JPanel bp = new JPanel();
		bp.add(editProfile);
		bp.add(btnCancel);
		bp.setBackground(new Color(28, 60, 52));

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		panel.setBackground(new Color(255, 184, 25));

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
}
