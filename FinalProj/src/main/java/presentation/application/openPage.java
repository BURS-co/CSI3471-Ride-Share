package presentation.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class openPage extends JDialog {
	 
    /**
 * 
 */
	private static final long serialVersionUID = 1L;
	private JButton btnLogin;
	private JButton btnCreateAccount;
  private boolean succeeded;
 
  /**
  * @param parent
  */
  public openPage( final JFrame parent) {
    super(parent, "Bear Pool", true);
 
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints cs = new GridBagConstraints();
 
    cs.fill = GridBagConstraints.HORIZONTAL;
        
    panel.setBorder(new LineBorder(Color.GRAY));
    btnLogin = new JButton("Log In");
    btnLogin.setBackground(new Color(255,184,25));
    btnLogin.setFont(new Font("Times New Roman",Font.BOLD,12));
 
    btnLogin.addActionListener(new ActionListener() {
 
  	 /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
      public void actionPerformed(ActionEvent e) {
    	  LoginDialog loginDlg = new LoginDialog(parent);
  		  loginDlg.setVisible(true);
  		  if(loginDlg.isSucceeded()) {
  		    succeeded = true;
  			  parent.dispose();
  		  }
      }
    });
   
   btnCreateAccount = new JButton("Sign Up");
   btnCreateAccount.setBackground(new Color(255,184,25));
   btnCreateAccount.setFont(new Font("Times New Roman",Font.BOLD,12));
   
   
   btnCreateAccount.addActionListener(new ActionListener() {
 
  	 /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
  	 public void actionPerformed(ActionEvent e) {
  		 AccountCreateDialog acDialog = new AccountCreateDialog(parent);
  		 acDialog.setVisible(true);
  		 if(acDialog.isSucceeded()) {
  			 succeeded = true;
  			 parent.dispose();
  		 }
  	 }
   });
   
   JPanel bp = new JPanel();
   bp.setBackground(new Color(28,60,52));
   bp.add(btnLogin);
   bp.add(btnCreateAccount);
 
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

