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
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import business.Login;

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

		setLayout(new GridBagLayout());
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
		add(lbEmail, cs);

		tfEmail = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		add(tfEmail, cs);

		lbPassword = new JLabel("Password: ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		lbPassword.setFont(customFont);
		add(lbPassword, cs);

		pfPassword = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		add(pfPassword, cs);
		// setBorder(new LineBorder(Color.GRAY));

		btnLogin = new JButton("Login");// button

		pfPassword.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
			}

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (Login.authenticate(tfEmail.getText(), getPassword())) {
						succeeded = true;
						ImageIcon icon = new ImageIcon("src/main/resources/poolfloat icon-yellow.png");
						JOptionPane.showMessageDialog(null, "Hi " + Login.getUser().getUsername() + "! Welcome to Bearpool!",
								"Login", JOptionPane.INFORMATION_MESSAGE, icon);
						Application.log.log(Level.INFO, getUsername() + " Login successful!");
						dispose();
					} else {
						final JOptionPane window = new JOptionPane();
						JOptionPane.showMessageDialog(LoginDialog.this, "Invalid username or password", "Login",
								JOptionPane.ERROR_MESSAGE);
						
						window.addKeyListener(new KeyListener() {

							public void keyTyped(KeyEvent e) {
								// TODO Auto-generated method stub

							}

							public void keyPressed(KeyEvent e) {
								// TODO Auto-generated method stub
								if (e.getKeyCode() == KeyEvent.VK_ENTER) {
									dispose();
								}
							}

							public void keyReleased(KeyEvent e) {

							}

						});
						Application.log.log(Level.INFO, getUsername() + " Login failed!");

						// restore entered fields
						pfPassword.setText(getPassword());

						window.setVisible(true);

					}
				}
			}

		});

		tfEmail.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (Login.authenticate(tfEmail.getText(), getPassword())) {
						succeeded = true;
						ImageIcon icon = new ImageIcon("src/main/resources/poolfloat icon-yellow.png");

						JOptionPane.showMessageDialog(null, "Hi " + Login.getUser().getEmail() + "! Welcome to Bearpool!", "Login",
								JOptionPane.INFORMATION_MESSAGE, icon);
						Application.log.log(Level.INFO, getUsername() + " Login successful!");
						dispose();
					} else {
						final JOptionPane window = new JOptionPane();
						JOptionPane.showMessageDialog(LoginDialog.this, "Invalid username or password", "Login",
								JOptionPane.ERROR_MESSAGE);
						
						window.addKeyListener(new KeyListener() {

							public void keyTyped(KeyEvent e) {
								// TODO Auto-generated method stub

							}

							public void keyPressed(KeyEvent e) {
								if (e.getKeyCode() == KeyEvent.VK_ENTER) {
									dispose();
								}
							}

							public void keyReleased(KeyEvent e) {

							}

						});
						Application.log.log(Level.INFO, getUsername() + " Login failed!");

						pfPassword.setText(getPassword());

						window.setVisible(true);

					}
				}
			}

		});

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
				if (Login.authenticate(tfEmail.getText(), getPassword())) {
					ImageIcon icon = new ImageIcon("src/main/resources/poolfloat icon-yellow.png");
					JOptionPane.showMessageDialog(null, "Hi " + Login.getUser().getUsername() + "! Welcome to Bearpool!", "Login",
							JOptionPane.INFORMATION_MESSAGE, icon);
					succeeded = true;
					Application.log.log(Level.INFO, getUsername() + " Login successful!");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Invalid username or password", "Login", JOptionPane.ERROR_MESSAGE);

					Application.log.log(Level.INFO, getUsername() + " Login failed!");

					// reset username and password
					// tfUsername.setText("");
					pfPassword.setText("");
					succeeded = false;

				}
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
		
		cs.gridx = 0;
		cs.gridy = 3;
		cs.fill = GridBagConstraints.BOTH;

		//getContentPane().add(this, BorderLayout.CENTER);
		getContentPane().add(bp,cs);

		//panel.
//		setBackground(new Color(255, 184, 25));
//		//panel.
//		setFocusable(true);
//		//panel.
//		requestFocusInWindow();
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

	/**
	 * Sets the user to the one logged in
	 * 
	 * @param user the user to be set
	 */
//	public void setUser(User user) {
//		this.u = user;
//	}
//	
//	/**
//	 * Gets the user logged in
//	 * 
//	 * @return the user logged in
//	 */
//	public User getUser() {
//		return u;
//	}
}
