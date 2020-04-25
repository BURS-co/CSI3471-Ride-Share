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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

/**
 * @author Joseph Perez, Andrew Ammentorp, Leighton Glim
 *
 *         Class responsible for creating the opening page
 */
public class OpenPage extends JDialog {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private JButton btnLogin;
	private JButton btnCreateAccount;
	private boolean succeeded;
	//private User u = new User();

	/**
	 * Creates the open page
	 * 
	 * @param parent the frame for the open page to be put on
	 * @throws IOException         if issue with input of the images
	 * @throws FontFormatException if issue with importing the fonts
	 */
	public OpenPage(final JFrame parent) throws FontFormatException, IOException {
		super(parent, "BearPool Login or Signup", true);

		// For the Dialog constraints
		setLayout(new GridBagLayout());
		GridBagConstraints dc = new GridBagConstraints();

		// Loading the image
		BufferedImage myPicture = ImageIO.read(new File("src/main/resources/poolfloat.png"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setBackground(new Color(255, 184, 25));
		picLabel.setOpaque(true);

		// First Row containing logo
		dc.weightx = 1;
		dc.weightx = 1;
		dc.gridx = 0;
		dc.gridy = 0;
		dc.anchor = GridBagConstraints.CENTER;
		add(picLabel, dc);

		JPanel btnPanel = new JPanel();
		btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		btnPanel.setBackground(new Color(28, 60, 52));
		btnPanel.setLayout(new GridBagLayout());
		GridBagConstraints pc = new GridBagConstraints();

		UIManager.put("ToolTip.background", Color.white);
		UIManager.put("ToolTip.border", new LineBorder(Color.BLACK, 1));

		btnLogin = new JButton("Log In");
		btnLogin.setToolTipText("Sign in to your account and get started");
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
		btnCreateAccount.setToolTipText("Create Account and get Started");

		btnCreateAccount.createToolTip();
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
		pc.insets = new Insets(5, 5, 5, 5);
		pc.fill = GridBagConstraints.HORIZONTAL;

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
		getContentPane().add(btnPanel, dc);

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);

	}

	/**
	 * Returns if the login was successful
	 * 
	 * @return the boolean status
	 */
	public boolean isSucceeded() {
		return succeeded;
	}

}
