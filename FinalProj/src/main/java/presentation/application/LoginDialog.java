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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import business.Login;
import business.SurveyService;
import business.UserService;
import data.databaseControllers.PostDatabase;
import data.post.AbstractPost;
import enums.Failures;

/**
 * @author Joseph Perez, Andrew Ammentorp, Leighton Glim
 *
 *         Class responsible for creating a login dialog
 */
public class LoginDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfEmail;
	private JPasswordField pfPassword;
	private JLabel lbEmail;
	private JLabel lbPassword;
	private JButton btnLogin;
	private JButton btnCancel;
	private boolean succeeded;
	private Font customFont;
	String[] ratings = { "0", "1", "2", "3", "4", "5" };
	JComboBox<String> rating = new JComboBox<String>(ratings);

	public static JTextField tfREmail;
	public static JTextField tftarget;
	public static JTextArea tfReason;

	// Sign up variables
	// private User u = new User();

	/**
	 * Creates the login dialog itself
	 * 
	 * @param parent the frame for the dialog to be put on
	 */
	public LoginDialog(final JFrame parent) {
		super(parent, "Bearpool Login", true);
		succeeded = false;
		JPanel information = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

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

		lbEmail = new JLabel("Baylor Email: ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		lbEmail.setFont(customFont);
		information.add(lbEmail, cs);

		tfEmail = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		information.add(tfEmail, cs);

		lbPassword = new JLabel("Password: ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		lbPassword.setFont(customFont);
		information.add(lbPassword, cs);

		pfPassword = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		information.add(pfPassword, cs);
		// setBorder(new LineBorder(Color.GRAY));

		pfPassword.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					authenticate();
				}
			}

			public void keyReleased(KeyEvent e) {
			}

		});

		tfEmail.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					authenticate();
				}
			}

			public void keyReleased(KeyEvent e) {
			}

		});

		btnLogin = new JButton("Login");// button
		btnLogin.setBackground(new Color(255, 184, 25));
		btnLogin.setFont(customFont);
		btnLogin.setBorderPainted(false);
		btnLogin.setOpaque(true);

		btnLogin.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				authenticate();
			}
		});

		btnCancel = new JButton("Cancel");
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
				dispose();
			}
		});

		JPanel bp = new JPanel();
		bp.add(btnLogin);
		bp.add(btnCancel);
		bp.setBackground(new Color(28, 60, 52));

		getContentPane().add(information, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.AFTER_LAST_LINE);

		information.setBackground(new Color(255, 184, 25));
		information.setFocusable(true);
		information.requestFocusInWindow();
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}

	/**
	 * Gets the username entered
	 * 
	 * @return the entered username
	 */
	public String getUsername() {
		return tfEmail.getText();
	}

	/**
	 * Gets the password entered
	 * 
	 * @return the entered password
	 */
	public String getPassword() {
		return new String(pfPassword.getPassword());
	}

	/**
	 * Returns if the login was successful
	 * 
	 * @return the boolean status
	 */
	public boolean isSucceeded() {
		return succeeded;
	}

	public void authenticate() {

		if (Login.authenticate(tfEmail.getText(), getPassword())) {
			succeeded = true;

			try {
				PostDatabase.getInstance().load();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			for (AbstractPost i : PostDatabase.getInstance()
					.quereyDatabase(UserService.getInstance().getCurrentUser().getEmail())) {

				if (i.isExpired()) {
					// Issue Survey
					JDialog surveyPanel = new JDialog(new JFrame(), "Survey", true);
					surveyPanel.setLayout(new GridBagLayout());
					GridBagConstraints ss = new GridBagConstraints();

					String[] ratings = { "0", "1", "2", "3", "4", "5" };

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

					ss.fill = GridBagConstraints.HORIZONTAL;

					JLabel lbEmail = new JLabel("What is your baylor email? ");
					ss.gridx = 0;
					ss.gridy = 0;
					ss.gridwidth = 1;
					ss.anchor = GridBagConstraints.FIRST_LINE_START;
					lbEmail.setFont(customFont);
					surveyPanel.add(lbEmail, ss);

					tfREmail = new JTextField(20);
					ss.gridx = 1;
					ss.gridy = 0;
					ss.gridwidth = 2;
					ss.anchor = GridBagConstraints.FIRST_LINE_END;
					tfREmail.setText(UserService.getInstance().getCurrentUser().getEmail());
					surveyPanel.add(tfREmail, ss);

					JLabel lbTarget = new JLabel("Email of person you're rating: ");
					ss.gridx = 0;
					ss.gridy = 1;
					ss.gridwidth = 1;
					ss.anchor = GridBagConstraints.FIRST_LINE_START;
					lbEmail.setFont(customFont);
					surveyPanel.add(lbTarget, ss);

					tftarget = new JTextField(20);
					ss.gridx = 1;
					ss.gridy = 1;
					ss.gridwidth = 2;
					ss.anchor = GridBagConstraints.FIRST_LINE_END;
					surveyPanel.add(tftarget, ss);

					JLabel lbReason = new JLabel("Comments: ");
					ss.gridx = 0;
					ss.gridy = 2;
					ss.gridwidth = 2;
					ss.anchor = GridBagConstraints.FIRST_LINE_START;
					lbReason.setFont(customFont);
					surveyPanel.add(lbReason, ss);

					tfReason = new JTextArea();
					ss.gridx = 0;
					ss.gridy = 3;
					ss.gridwidth = 2;
					ss.gridheight = 2;
					ss.anchor = GridBagConstraints.CENTER;
					surveyPanel.add(tfReason, ss);

					JLabel lbRate = new JLabel("Rating: ");
					ss.gridx = 0;
					ss.gridy = 4;
					ss.gridwidth = 1;
					ss.gridheight = 1;
					ss.gridwidth = 1;
					ss.anchor = GridBagConstraints.FIRST_LINE_START;
					surveyPanel.add(lbRate, ss);

					// rating = new JComboBox<String>(ratings);
					rating.setSelectedIndex(-1);
					ss.gridx = 1;
					ss.gridy = 4;
					ss.gridwidth = 1;
					ss.anchor = GridBagConstraints.FIRST_LINE_END;
					surveyPanel.add(rating, ss);

					JButton btnSubmit = new JButton("Submit");
					btnSubmit.setFont(customFont);
					btnSubmit.setBackground(new Color(255, 184, 25));
					btnSubmit.setBorderPainted(false);
					btnSubmit.setOpaque(true);

					btnSubmit.addActionListener(new ActionListener() {

						/*
						 * (non-Javadoc)
						 * 
						 * @see
						 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
						 */
						public void actionPerformed(ActionEvent e) {
							Application.log.log(Level.INFO, "Survey submitted");

							String score = String.valueOf(rating.getSelectedItem());

							String[] info = { tfREmail.getText(), tftarget.getText(), tfReason.getText(), score };

							Failures result = SurveyService.getInstance().verify(info);

							switch (result) {
							case emptyField:
								JOptionPane.showMessageDialog(null, "Fields must not be empty. Please Fill in All Fields", "Survey",
										JOptionPane.ERROR_MESSAGE);
								break;
							case SurveyField2notANumber:
								JOptionPane.showMessageDialog(null, "Please Enter A Valid Number For Rating", "Survey",
										JOptionPane.ERROR_MESSAGE);
								break;
							case SurveyField3TooLong:
								JOptionPane.showMessageDialog(null, "Your Comment is Too Long, Please Enter >300 Characters", "Survey",
										JOptionPane.ERROR_MESSAGE);
								break;
							default:
								dispose();
								break;
							}

						}
					});

					ss.gridx = 1;
					ss.gridy = 5;
					surveyPanel.add(btnSubmit, ss);

					surveyPanel.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					surveyPanel.setVisible(true);
					surveyPanel.pack();
				}

			}

			if (UserService.getInstance().getCurrentUser().getJoinNotif()) {
				// trigger pop up that tells user another user wants to join their post
			}

			if (UserService.getInstance().getCurrentUser().getPostCanceledNotif()) {
				// trigger pop up that tells user a post they were trying to become a prospect
				// for has been canceled
			}

			ImageIcon icon = new ImageIcon("src/main/resources/poolfloat icon-yellow.png");
			JOptionPane.showMessageDialog(null,
					"Hi " + UserService.getInstance().getCurrentUser().getUsername() + "! Welcome to Bearpool!", "Login",
					JOptionPane.INFORMATION_MESSAGE, icon);
			Application.log.log(Level.INFO, getUsername() + " Login successful!");
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Invalid username or password", "Login", JOptionPane.ERROR_MESSAGE);

			Application.log.log(Level.INFO, getUsername() + " Login failed!");

		}
	}

}
