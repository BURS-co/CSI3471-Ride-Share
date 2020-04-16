package presentation.application;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	public openPage(final JFrame parent) throws FontFormatException, IOException {
		super(parent, "Bear Pool", true);

		// For the Dialog constraints
		setLayout(new GridBagLayout());
		GridBagConstraints dc = new GridBagConstraints();
		
		// Will Contain Picture Logo
		JPanel logoPanel = new JPanel(new GridBagLayout());
		
		// Logo constraints
		GridBagConstraints lc = new GridBagConstraints();

		lc.fill = GridBagConstraints.HORIZONTAL;
		lc.weightx = 1;
		lc.weighty = 1;

		// Loading the image
		BufferedImage myPicture = ImageIO.read(new File("src/main/resources/poolfloat.png"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setBackground(new Color(255, 184, 25));
		picLabel.setOpaque(true);

		// First Row containing logo
		lc.gridx = 0;
		lc.gridy = 0;
		lc.anchor = GridBagConstraints.CENTER;
		logoPanel.add(picLabel, lc);
		
		dc.weightx = 1;
		dc.weightx = 1;
		dc.gridx = 0;
		dc.gridy = 0;
		dc.anchor = GridBagConstraints.ABOVE_BASELINE;
		
		// add logo panel in dialog
		getContentPane().add(logoPanel, dc);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(new Color(28, 60, 52));
		btnPanel.setLayout(new GridBagLayout());
		GridBagConstraints pc = new GridBagConstraints();
		
		btnLogin = new JButton("Log In");
		btnLogin.setBackground(new Color(255, 184, 25));
		Font customFont = null;
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
		
		// use the font
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
				LoginDialog loginDlg = new LoginDialog(parent);
				loginDlg.setVisible(true);
				if (loginDlg.isSucceeded()) {
					succeeded = true;
					parent.dispose();
				}
			}
		});



		btnCreateAccount = new JButton("Sign Up");
		btnCreateAccount.setBackground(new Color(255, 184, 25));
		btnCreateAccount.setFont(customFont);
		btnCreateAccount.setBorderPainted(false);
		btnCreateAccount.setOpaque(true);

		btnCreateAccount.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				AccountCreateDialog acDialog = new AccountCreateDialog(parent);
				acDialog.setVisible(true);
				if (acDialog.isSucceeded()) {
					succeeded = true;
					parent.dispose();
				}
			}
		});
		

		pc.weightx = 1;
		pc.weighty = 1;
		pc.insets = new Insets(5,5,5,5);
		
		pc.gridx = 0;
		pc.gridy = 0;
		pc.anchor = GridBagConstraints.CENTER;		
	
		// Add log in button to button panel
		btnPanel.add(btnLogin, pc);

		pc.gridx = 1;
		pc.gridy = 0;
		pc.anchor = GridBagConstraints.CENTER;

		// Add sign up button to button panel;
		btnPanel.add(btnCreateAccount, pc);
		
		
		// Bottom of Dialog
		dc.weightx = 1;
		dc.weighty = 1;
		
		// Should flow better
		dc.gridx = 0;
		dc.gridy = 1;
		dc.anchor = GridBagConstraints.BASELINE;
		dc.fill = GridBagConstraints.BOTH;
		
		// Add Button Panel to Dialog
		getContentPane().add(btnPanel,dc);

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);

	}

	public boolean isSucceeded() {
		return succeeded;
	}

}
