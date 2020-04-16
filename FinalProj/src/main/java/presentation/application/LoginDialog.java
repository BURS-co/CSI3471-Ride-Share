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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import business.Login;

public class LoginDialog extends JDialog {

	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JLabel lbUsername;
	private JLabel lbPassword;
	private JButton btnLogin;
	private JButton btnCancel;
	private boolean succeeded;
	private Font customFont;
	// Sign up variables

	/**
	 * @param parent
	 */
	public LoginDialog(JFrame parent) {
		super(parent, "Login", true);
		//
		JPanel panel = new JPanel(new GridBagLayout());
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

		lbUsername = new JLabel("Baylor Email: ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		lbUsername.setFont(customFont);
		panel.add(lbUsername, cs);

		tfUsername = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(tfUsername, cs);

		lbPassword = new JLabel("Password: ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		lbPassword.setFont(customFont);
		panel.add(lbPassword, cs);

		pfPassword = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(pfPassword, cs);
		panel.setBorder(new LineBorder(Color.GRAY));

		btnLogin = new JButton("Login");// button

		pfPassword.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
			}

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (Login.authenticate(tfUsername.getText(), getPassword())) {
						JOptionPane.showMessageDialog(LoginDialog.this,
								"Hi " + getUsername() + "! Welcome to Bearpool!", "Login",
								JOptionPane.INFORMATION_MESSAGE);
						succeeded = true;
						Application.log.log(Level.INFO, getUsername() + " Login successful!");
						dispose();
					} else {
						JOptionPane.showMessageDialog(LoginDialog.this, "Invalid username or password", "Login",
								JOptionPane.ERROR_MESSAGE);

						Application.log.log(Level.INFO, getUsername() + " Login failed!");

						// reset username and password
						tfUsername.setText("");
						pfPassword.setText("");
						succeeded = false;

					}
				}
			}

		});

		tfUsername.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (Login.authenticate(tfUsername.getText(), getPassword())) {
						JOptionPane.showMessageDialog(LoginDialog.this,
								"Hi " + getUsername() + "! Welcome to Bearpool!", "Login",
								JOptionPane.INFORMATION_MESSAGE);
						succeeded = true;
						Application.log.log(Level.INFO, getUsername() + " Login successful!");
						dispose();
					} else {
						JOptionPane.showMessageDialog(LoginDialog.this, "Invalid username or password", "Login",
								JOptionPane.ERROR_MESSAGE);

						Application.log.log(Level.INFO, getUsername() + " Login failed!");

						// reset username and password
						tfUsername.setText("");
						pfPassword.setText("");
						succeeded = false;

					}
				}
			}

		});
		
		btnLogin.setBackground(new Color(255,184,25));
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
				if (Login.authenticate(tfUsername.getText(), getPassword())) {
					JOptionPane.showMessageDialog(LoginDialog.this, "Hi " + getUsername() + "! Welcome to Bearpool!",
							"Login", JOptionPane.INFORMATION_MESSAGE);
					succeeded = true;
					Application.log.log(Level.INFO, getUsername() + " Login successful!");
					dispose();
				} else {
					JOptionPane.showMessageDialog(LoginDialog.this, "Invalid username or password", "Login",
							JOptionPane.ERROR_MESSAGE);

					Application.log.log(Level.INFO, getUsername() + " Login failed!");

					// reset username and password
					tfUsername.setText("");
					pfPassword.setText("");
					succeeded = false;

				}
			}
		});
		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(255,184,25));
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
		bp.setBackground(new Color(28,60,52));

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		panel.setBackground(new Color(255,184,25));
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}

	/**
	 * @return
	 */
	public String getUsername() {
		return tfUsername.getText();
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return new String(pfPassword.getText());
	}

	/**
	 * @return
	 */
	public boolean isSucceeded() {
		return succeeded;
	}
}
