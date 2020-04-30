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
import java.util.List;
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

import business.UserService;
import business.ValidateAccountInfo;
import data.databaseControllers.PostDatabase;
import data.databaseControllers.UserDatabase;
import data.post.AbstractPost;
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
	Font customFont = null;

	public JLabel name;
	public JLabel baylorEmail;
	public JLabel phoneNum;
	public JLabel gradMonth;
	public JLabel gradYear;
	public JLabel newPassword;

	String[] months = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	String[] years = { "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027" };

	/**
	 * Creates and displays the user's profile
	 * 
	 * @param parent the frame to be displayed on
	 * @param u      the user logged in
	 */
	public ViewProfile(final JFrame parent, final User u) {
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
		name = new JLabel(u.getUsername());
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
		baylorEmail = new JLabel(u.getEmail());
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
		phoneNum = new JLabel(u.getPhoneNumber());
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
		gradMonth = new JLabel(u.getGradMonth());
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
		gradYear = new JLabel(u.getGradYear());
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
		newPassword = new JLabel(pass);
		newPassword.setFont(customFont);
		panel.add(newPassword, cs);

		JButton editProfile = new JButton("Edit Profile");
		editProfile.setFont(customFont);
		editProfile.setBackground(new Color(255, 184, 25));
		editProfile.setBorderPainted(false);
		editProfile.setOpaque(true);
		editProfile.addActionListener(new ActionListener() {

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
					name.setText(UserService.getInstance().getCurrentUser().getUsername());
					baylorEmail.setText(UserService.getInstance().getCurrentUser().getEmail());
					phoneNum.setText(UserService.getInstance().getCurrentUser().getPhoneNumber());
					gradMonth.setText(UserService.getInstance().getCurrentUser().getGradMonth());
					gradYear.setText(UserService.getInstance().getCurrentUser().getGradYear());
					String pass = String.join("",
							Collections.nCopies(UserService.getInstance().getCurrentUser().getPassword().length(), "*"));
					newPassword.setText(pass);

				}
			}
		});
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(customFont);
		btnCancel.setBackground(new Color(255, 184, 25));
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

		JButton deleteBtn = new JButton("Delete Account");
		deleteBtn.setFont(customFont);
		deleteBtn.setBackground(new Color(255, 184, 25));
		deleteBtn.setBorderPainted(false);
		deleteBtn.setOpaque(true);

		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int res = JOptionPane.showConfirmDialog(null, "Confirm Deletion?", "Delete Account",
						JOptionPane.OK_CANCEL_OPTION);

				// only delete wiht secondary confirmation
				if (res == 1) {
					UserDatabase.getInstance().removeUser(UserService.getInstance().getCurrentUser());
					try {
						UserDatabase.getInstance().write();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					dispose();
					System.exit(0);
				}
			}
		});

		JButton viewBtn = new JButton("View Posts");
		viewBtn.setFont(customFont);
		viewBtn.setBackground(new Color(255, 184, 25));
		viewBtn.setBorderPainted(false);
		viewBtn.setOpaque(true);
		viewBtn.addActionListener(new ActionListener() {
			// (removed override)
			public void actionPerformed(ActionEvent e) {

				List<AbstractPost> posts = PostDatabase.getInstance()
						.quereyDatabase(UserService.getInstance().getCurrentUser().getEmail());

				JPanel selection = new JPanel();
				selection.setLayout(new GridBagLayout());

				JComboBox<String> box = new JComboBox<String>();

				JButton select = new JButton("select");
				select.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO
					}

				});
			}

		});

		JPanel bp = new JPanel();
		bp.add(editProfile);
		bp.add(btnCancel);
		bp.add(deleteBtn);
		bp.add(viewBtn);
		bp.setBackground(new Color(28, 60, 52));

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		panel.setBackground(new Color(255, 184, 25));

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
}
