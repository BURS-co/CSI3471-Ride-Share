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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
 * @throws IOException 
 * @throws FontFormatException 
  */
  public openPage( final JFrame parent) throws FontFormatException, IOException {
    super(parent, "Bear Pool", true);
 
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints cs = new GridBagConstraints();
  
 
    cs.fill = GridBagConstraints.HORIZONTAL;
        
    panel.setBorder(new LineBorder(Color.GRAY));
    btnLogin = new JButton("Log In");
    btnLogin.setBackground(new Color(255,184,25));
    Font customFont = null;
    try {
        
        customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/OpenSans-Bold.ttf")).deriveFont(12f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //register the font
        ge.registerFont(customFont);
    } catch (IOException e) {
        e.printStackTrace();
    } catch(FontFormatException e) {
        e.printStackTrace();
    }

    //use the font
    btnLogin.setFont(customFont);
    
    //Loading the image
    BufferedImage myPicture = ImageIO.read(new File("src/main/resources/poolfloat_copy-removebg-preview.png"));
    JLabel picLabel = new JLabel(new ImageIcon(myPicture));
    picLabel.setBackground(new Color(255, 184, 25));
    picLabel.setOpaque(true);
    panel.add(picLabel);
    
    btnLogin.setBorderPainted(false);
    btnLogin.setOpaque(true);
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
   btnCreateAccount.setFont(customFont);
   btnCreateAccount.setBorderPainted(false);
   btnCreateAccount.setOpaque(true);
   
   
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

