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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
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
import javax.swing.JTextField;

import business.ValidateAccountInfo;
import data.databaseControllers.UserDatabase;
import data.user.Admin;
import data.user.User;

/**
 * @author Joseph Perez, Andrew Ammentorp, Leighton Glim
 *
 *         Class responsible for editing your profile
 */
public class EditProfile extends JDialog {
	private static final long serialVersionUID = 1L;
	JFrame frame;
	JTextField name;
	JTextField baylorEmail;
	JTextField phoneNum;
	String month = new String();
	String year = new String();
	JPasswordField password;
	JPasswordField confirmPassword;
	ValidateAccountInfo vaI;
	private boolean succeeded = false;
	private JButton btnCancel;
	Font customFont = null;
	public static User u = new User();

	String[] months = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	String[] years = { "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027" };

	/**
	 * Creates the functionality to edit your profile
	 * 
	 * @param parent the frame for the prompts to be put on
	 * @param u the user in question
	 */
	public EditProfile(JFrame parent, final User u) {
		super(parent, "Edit Profile", true);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		JComboBox gradMonth = new JComboBox(months);
		JComboBox gradYear = new JComboBox(years);
		JLabel userLabel = new JLabel("Name: ");
		JLabel emailLabel = new JLabel("Baylor Email: ");
		JLabel phoneLabel = new JLabel("Phone: ");
		JLabel gradMonthLabel = new JLabel("Grad Month: ");
		JLabel gradYearLabel = new JLabel("Grad Year: ");
		JLabel passwordLabel = new JLabel("Password: ");
		JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
		
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

		cs.fill = GridBagConstraints.HORIZONTAL;

		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		userLabel.setFont(customFont);
		panel.add(userLabel, cs);

		name = new JTextField(12);
		name.setText(u.getUsername());
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(name, cs);

		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		emailLabel.setFont(customFont);
		panel.add(emailLabel, cs);

		baylorEmail = new JTextField(12);
		baylorEmail.setText(u.getEmail());
		baylorEmail.setEditable(false);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(baylorEmail, cs);

		cs.gridx = 0;
		cs.gridy = 2;
		cs.gridwidth = 2;
		phoneLabel.setFont(customFont);
		panel.add(phoneLabel, cs);

		phoneNum = new JTextField(12);
		phoneNum.setText(u.getPhoneNumber());
		cs.gridx = 1;
		cs.gridy = 2;
		cs.gridwidth = 2;
		panel.add(phoneNum, cs);

		gradMonth.setSelectedIndex(-1);
		gradYear.setSelectedIndex(-1);
		gradMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				month = (String) cb.getSelectedItem();
			}
		});
		gradYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				year = (String) cb.getSelectedItem();
			}
		});

		cs.gridx = 0;
		cs.gridy = 3;
		cs.gridwidth = 2;
		gradMonthLabel.setFont(customFont);
		panel.add(gradMonthLabel, cs);

		cs.gridx = 1;
		cs.gridy = 3;
		cs.gridwidth = 2;
		panel.add(gradMonth, cs);

		cs.gridx = 0;
		cs.gridy = 4;
		cs.gridwidth = 2;
		gradYearLabel.setFont(customFont);
		panel.add(gradYearLabel, cs);

		cs.gridx = 1;
		cs.gridy = 4;
		cs.gridwidth = 2;
		panel.add(gradYear, cs);

		cs.gridx = 0;
		cs.gridy = 5;
		cs.gridwidth = 2;
		passwordLabel.setFont(customFont);
		panel.add(passwordLabel, cs);

		password = new JPasswordField(12);
		cs.gridx = 1;
		cs.gridy = 5;
		cs.gridwidth = 2;
		panel.add(password, cs);

		cs.gridx = 0;
		cs.gridy = 6;
		cs.gridwidth = 1;
		confirmPasswordLabel.setFont(customFont);
		panel.add(confirmPasswordLabel, cs);

		confirmPassword = new JPasswordField(12);
		cs.gridx = 1;
		cs.gridy = 6;
		cs.gridwidth = 3;
		panel.add(confirmPassword, cs);

		JButton createAccount = new JButton("Save Changes");
		createAccount.setFont(customFont);
		createAccount.setBackground(new Color(255,184,25));
		createAccount.setFont(customFont);
		createAccount.setBorderPainted(false);
		createAccount.setOpaque(true);
		createAccount.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent event) {
				if (name.getText().length() == 0 || baylorEmail.getText().length() == 0 || phoneNum.getText().length() == 0
						|| password.getText().length() == 0 || confirmPassword.getText().length() == 0 || month.length() == 0
						|| year.length() == 0) {
					JOptionPane.showMessageDialog(EditProfile.this, "Please fill in all fields.", "Create Account",
							JOptionPane.INFORMATION_MESSAGE);
					succeeded = false;

				} else {
					// make sure text entered in all fields
					if (name.getText().length() > 1 && baylorEmail.getText().length() > 1 && phoneNum.getText().length() > 1
							&& password.getText().length() > 1 && confirmPassword.getText().length() > 1 && month.length() > 1
							&& year.length() > 1)
						if (vaI.validateAccountInfoEntered(name.getText(), baylorEmail.getText(), phoneNum.getText(),
								password.getText(), confirmPassword.getText(), month, year, EditProfile.this)) {

							User user = null;
							if(Application.loggedIn instanceof Admin)
								user = new Admin();
							else
								user = new User();
							
							//System.out.println("The old:\n" + Application.loggedIn.toString());
							
							//UserDatabase.getInstance().removeUser(Application.loggedIn);
							
							user.setUsername(name.getText());
							user.setEmail(baylorEmail.getText());
							user.setPhoneNumber(phoneNum.getText());
							user.setGradMonth(month);
							user.setGradYear(year);
							user.setPassword(new String(password.getPassword()));
							
							System.out.println("The edited user:\n"+user.toString());
							
							setUser(u);
							
							System.out.println("The chap whomstve logged in:\n"+Application.loggedIn.toString());
							
							//UserDatabase.getInstance().addUser(user);
							
							String filePath = "userDatabase.txt";
						    Scanner sc = null;
						    String content = "";
						    BufferedReader reader = null;
						    FileWriter writer = null;
							try {
								reader = new BufferedReader(new FileReader(filePath));
								
								String line = reader.readLine();
								
								while(line != null) {
									if(line.contains(Application.loggedIn.getEmail())) {
										//System.out.println("Collision!");
										line = user.toString();
										content = content + line;
									}
									else
										content = content + line + System.lineSeparator();
									line = reader.readLine();
								}
								
								//System.out.println("Content: \n" + content);
								
								

					             
						            //Rewriting the input text file with newContent
						             
						        writer = new FileWriter(filePath);
						        
						        writer.write(content);
						             
						        //writer.write(newContent);
						        reader.close();
								writer.flush();
								writer.close();
								
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						   
							
							//Keep track of user logged in
							Application.loggedIn.setEmail(user.getEmail());
							Application.loggedIn.setGradMonth(user.getGradMonth());
							Application.loggedIn.setGradYear(user.getGradYear());
							Application.loggedIn.setPassword(user.getPassword());
							Application.loggedIn.setPhoneNumber(user.getPhoneNumber());
							Application.loggedIn.setUsername(user.getUsername());
							
							ImageIcon icon = new ImageIcon("src/main/resources/poolfloat icon-yellow.png");
							JOptionPane.showMessageDialog(null,
									"Changes successfully made. ", "Edit Profile", JOptionPane.INFORMATION_MESSAGE, icon);
							succeeded = true;
							Application.log.log(Level.INFO, user.getUsername() + "'s Profile Edited successfully");
							dispose();

						}
				}
			}
		});
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(customFont);
		btnCancel.setFont(customFont);
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
				Application.log.log(Level.INFO, "Edit Profile canceled");
				dispose();
			}
		});

		JPanel bp = new JPanel();
		bp.add(createAccount);
		bp.add(btnCancel);
		bp.setBackground(new Color(28,60,52));

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		panel.setBackground(new Color(255,184,25));

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}

	
	/**
	 * Returns if editing your profile succeeded
	 * @return the boolean value corresponding to a successful editing
	 */
	public boolean isSucceeded() {
		return succeeded;
	}
	
	/**
	 * Sets the user
	 * @param user the user to be set
	 * @param user
	 * @return
	 */
	public void setUser(User user) {
		this.u = user;
	}
	
	/**
	 * Gets the user in question
	 * 
	 * @return u the user
	 * @param
	 * @return u
	 */
	public static User getUser() {
		return u;
	}
}
