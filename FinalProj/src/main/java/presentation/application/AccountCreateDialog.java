package presentation.application;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import business.validateAccountInfo;
import data.databaseControllers.UserDatabase;
import data.user.User;

public class AccountCreateDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	JFrame          frame;
    JTextField      name;
    JTextField      baylorEmail;
    JTextField      phoneNum;
    JPasswordField  password;
    JPasswordField  confirmPassword;
    validateAccountInfo vaI;
    private boolean succeeded = false;
    private JButton btnCancel;
    
    /**
	 * @param parent
	 * @return
	 */
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
        	@SuppressWarnings("static-access")
        	/* (non-Javadoc)
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
			public void actionPerformed(ActionEvent event) {
        		//make sure text entered in all fields
        		if(name.getText().length() > 1 &&
        				baylorEmail.getText().length()>1 && phoneNum.getText().length() > 1 &&
        				password.getText().length() > 1 && confirmPassword.getText().length()>1)
        		if(vaI.validateAccountInfoEntered(name.getText(), 
        				baylorEmail.getText(), phoneNum.getText(), 
        				password.getText(), confirmPassword.getText(), 
        				AccountCreateDialog.this)) {
                	  	
        			
                	  	User user =  new User();
                		user.setUsername(name.getText());
                		user.setEmail(baylorEmail.getText());
                		user.setPhoneNumber(phoneNum.getText());
                		user.setPassword(new String(password.getPassword()));
                		
                		//UserDatabase.getUserData().add(user);
                		Application.userDatabase.add(user);
                		
                		JOptionPane.showMessageDialog(AccountCreateDialog.this,
                                "Hi " + user.getUsername() + "! Welcome to Bearpool!",
                                "Login",
                                JOptionPane.INFORMATION_MESSAGE);
                    succeeded = true;
                    Application.log.log(Level.INFO, user.getUsername()+" Login successful!");
                    dispose();
                        
                }
            }
        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            /* (non-Javadoc)
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
            public void actionPerformed(ActionEvent e) {
            	Application.log.log(Level.INFO,"Sign up canceled");
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
