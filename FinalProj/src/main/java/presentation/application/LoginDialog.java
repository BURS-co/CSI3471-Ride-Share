package presentation.application;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.*;
import javax.swing.border.*;

import data.databaseControllers.UserDatabase;
import data.user.User;
 
public class LoginDialog extends JDialog {
 
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
    
    //Sign up variables
    
 
    /**
     * @param parent
     */
    public LoginDialog(JFrame parent) {
        super(parent, "Login", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL;
 
        lbUsername = new JLabel("Baylor Email: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
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
        panel.add(lbPassword, cs);
 
        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
 
        btnLogin = new JButton("Login");
 
        btnLogin.addActionListener(new ActionListener() {
 
            /* (non-Javadoc)
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
            public void actionPerformed(ActionEvent e) {
                if (Login.authenticate(tfUsername.getText(), getPassword())) {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Hi " + getUsername() + "! Welcome to Bearpool!",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    succeeded = true;
                    Globals.log.log(Level.INFO, getUsername()+" Login successful!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    
                    Globals.log.log(Level.INFO, getUsername()+" Login failed!");
                    
                    // reset username and password
                    tfUsername.setText("");
                    pfPassword.setText("");
                    succeeded = false;
 
                }
            }
        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            /* (non-Javadoc)
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
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

class Login{
	/**
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean authenticate(String email, String password) {
		ArrayList<User> users = UserDatabase.getUserData();
		//boolean validate = false;
		
		for(User u : users) {
			if(u.getEmail().equals(email)) {
				if(u.getPassword().equals(password)) {
					//validate = true;
					//Set user data
					Globals.loggedIn.setEmail(email);
					Globals.loggedIn.setPassword(password);
					Globals.loggedIn.setPhoneNumber(u.getPhoneNumber());
					Globals.loggedIn.setUsername(u.getUsername());
					return true;
				}
				else {
					return false;
					//validate = false;
				}
			}
		}
		
		//Searched entire database, but no email match
		return false;
		
		//return validate;
		
        /*if (username.equals("bob") && password.equals("secret")) {
            return true;
        }
        return false;*/
    }
}
