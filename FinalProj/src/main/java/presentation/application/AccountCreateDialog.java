package presentation.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import business.validateAccountInfo;
import data.databaseControllers.UserDatabase;
import data.user.User;

public class AccountCreateDialog extends JDialog {

    JFrame          frame;
    JTextField      name;
    JTextField      baylorEmail;
    JTextField      phoneNum;
    JPasswordField  password;
    JPasswordField  confirmPassword;
    JLabel          warningLabel;
    validateAccountInfo vaI;
    private boolean succeeded = false;
    
    public AccountCreateDialog( JFrame parent) {
    	super(parent, "Create Account", true);
    	JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        JLabel userLabel = new JLabel("Name: ");
        JLabel emailLabel = new JLabel("Baylor Email: ");
        JLabel phoneLabel = new JLabel("Phone: ");
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
        
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 2;
        panel.add(passwordLabel, cs);
        
        password = new JPasswordField(12);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        panel.add(password, cs);
        
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        panel.add(confirmPasswordLabel, cs);
        
        confirmPassword = new JPasswordField(12);
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 3;
        panel.add(confirmPassword, cs);
        
        
        
        

        JButton createAccount = new JButton("Create account");
        createAccount.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
          /*      if (!(Arrays.equals(password.getPassword(),
                        confirmPassword.getPassword()))) {
                  JOptionPane.showMessageDialog(AccountCreateDialog.this,
                  "Your passwords do not match! Try again.",
                  "Login",
                  JOptionPane.INFORMATION_MESSAGE);
                  succeeded = false;
                } else if (password.getPassword().length < 8) {
                    JOptionPane.showMessageDialog(AccountCreateDialog.this,
                            "That password is not long enough! Try again.",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    succeeded = false;
                } else if (phoneNum.getText().length() != 10) {
                	JOptionPane.showMessageDialog(AccountCreateDialog.this,
                            "Invalid phone number. Must be 10 digits.",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    succeeded = false;
                } else if (!(baylorEmail.getText().toLowerCase().contains("@baylor.edu"))) {
                	JOptionPane.showMessageDialog(AccountCreateDialog.this,
                            "Invalid email address. Must be a Baylor email.",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    succeeded = false;
                } else if(name.getText() != null) {
                	String username = name.getText();
                    int words = 0;
                    if (!(" ".equals(username.substring(0, 1))) || !(" ".equals(username.substring(username.length() - 1))))
                    {
                        for (int i = 0; i < username.length(); i++)
                        {
                            if (username.charAt(i) == ' ')
                            {
                                words++;
                            }
                        }
                        words = words + 1; 
                    }
                    if(words != 2) {
                    	JOptionPane.showMessageDialog(AccountCreateDialog.this,
                       "Invalid user name. Enter first and last name.",
                       "Login",
                       JOptionPane.INFORMATION_MESSAGE);
                    	succeeded = false;
                    } else {
                    	User user =  new User();
                      user.setUsername(name.getText());
                		  user.setEmail(baylorEmail.getText());
                		  user.setPhoneNumber(phoneNum.getText());
                		  user.setPassword(new String(password.getPassword()));
                		
                		  UserDatabase.getUserData().add(user);
                		
                		
                		  JOptionPane.showMessageDialog(AccountCreateDialog.this,
                       "Hi " + user.getUsername() + "! Welcome to Bearpool!",
                       "Login",
                      JOptionPane.INFORMATION_MESSAGE);
                      succeeded = true;
                      Globals.log.log(Level.INFO, user.getUsername()+" Login successful!");
                      dispose();
                    }
                } else { */
        		if(vaI.validateAccountInfoEntered(name.getText(), baylorEmail.getText(), phoneNum.getText(), password.getText(), confirmPassword.getText(), AccountCreateDialog.this)) {
                	  	
                	  User user =  new User();
                		user.setUsername(name.getText());
                		user.setEmail(baylorEmail.getText());
                		user.setPhoneNumber(phoneNum.getText());
                		user.setPassword(new String(password.getPassword()));
                		
                		UserDatabase.getUserData().add(user);
                		
                		
                		JOptionPane.showMessageDialog(AccountCreateDialog.this,
                                "Hi " + user.getUsername() + "! Welcome to Bearpool!",
                                "Login",
                                JOptionPane.INFORMATION_MESSAGE);
                    succeeded = true;
                    Globals.log.log(Level.INFO, user.getUsername()+" Login successful!");
                    dispose();
                        
                }
            }
        });
        
      JPanel bp = new JPanel();
      bp.add(createAccount);
      getContentPane().add(panel, BorderLayout.CENTER);
      getContentPane().add(bp, BorderLayout.PAGE_END);
 
      pack();
      setResizable(false);
      setLocationRelativeTo(parent);
    }

    public boolean isSucceeded() {
      return succeeded;
    }
    
}
