package presentation.application;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import business.SurveyService;
import business.UserService;
import enums.Failures;

public class SurveyGUI extends JDialog {
	
	public static Font customFont;
	public static JTextField tfREmail;
	public static JTextField tftarget;
	public static JTextArea tfReason;
	String[] ratings = { "0", "1", "2", "3", "4", "5" };
	JComboBox<String> rating = new JComboBox<String>(ratings);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SurveyGUI(final JFrame parent) {
		super(parent, "Survey", true);
		setLayout(new GridBagLayout());

		JPanel surveyCnt = new JPanel();
		surveyCnt.setLayout(new GridBagLayout());
		GridBagConstraints ss = new GridBagConstraints();

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

		// Start
		JLabel lbEmail = new JLabel("Your baylor email: ");
		lbEmail.setFont(customFont);
		ss.gridx = 0;
		ss.gridy = 0;
		ss.gridwidth = 1;
		ss.fill = GridBagConstraints.FIRST_LINE_START;
		lbEmail.setFont(customFont);
		surveyCnt.add(lbEmail, ss);

		tfREmail = new JTextField(20);//comment
		ss.gridx = 1;
		ss.gridy = 0;
		ss.gridwidth = 2;
		ss.fill = GridBagConstraints.RELATIVE;

		// ss.anchor = GridBagConstraints.FIRST_LINE_END;
		tfREmail.setText(UserService.getInstance().getCurrentUser().getEmail());
		surveyCnt.add(tfREmail, ss);

		JLabel lbTarget = new JLabel("Email of person you're rating: ");
		lbTarget.setFont(customFont);
		ss.gridx = 0;
		ss.gridy = 1;
		ss.gridwidth = 1;
		ss.fill = GridBagConstraints.FIRST_LINE_START;
		lbEmail.setFont(customFont);
		surveyCnt.add(lbTarget, ss);

		tftarget = new JTextField(20);
		ss.gridx = 1;
		ss.gridy = 1;
		ss.gridwidth = 2;
		ss.fill = GridBagConstraints.FIRST_LINE_END;
		surveyCnt.add(tftarget, ss);

		JLabel lbReason = new JLabel("Comments: ");
		lbReason.setFont(customFont);
		ss.gridx = 0;
		ss.gridy = 3;
		ss.fill = GridBagConstraints.RELATIVE;
		lbReason.setFont(customFont);
		surveyCnt.add(lbReason, ss);

		tfReason = new JTextArea(10,30);
		tfReason.setLineWrap(true);
		tfReason.setWrapStyleWord(true);
		ss.gridx = 0;
		ss.gridy = 4;
		ss.fill = GridBagConstraints.BOTH;
		surveyCnt.add(tfReason, ss);
		
		

		JLabel lbRate = new JLabel("Rating: ");
		lbRate.setFont(customFont);
		ss.gridx = 0;
		ss.gridy = 5;
		ss.fill = GridBagConstraints.FIRST_LINE_START;
		surveyCnt.add(lbRate, ss);

		rating.setSelectedIndex(-1);
		ss.gridx = 1;
		ss.gridy = 5;
		ss.fill = GridBagConstraints.RELATIVE;
		surveyCnt.add(rating, ss);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(customFont);
		btnSubmit.setBackground(new Color(255, 184, 25));
		btnSubmit.setBorderPainted(false);
		btnSubmit.setOpaque(true);

		btnSubmit.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				Application.log.log(Level.INFO, "Survey submitted");

				String score = String.valueOf(rating.getSelectedItem());

				String[] info = { tfREmail.getText(), tftarget.getText(), score, tfReason.getText() };

				Failures result = SurveyService.getInstance().verify(info);

				switch (result) {
				case emptyField:
					JOptionPane.showMessageDialog(null, "Fields must not be empty. Please Fill in All Fields", "Survey",
							JOptionPane.ERROR_MESSAGE);
					break;
				case SurveyField2notANumber:
					JOptionPane.showMessageDialog(null, "Please Enter A Valid Number For Rating", "Survey",
							JOptionPane.ERROR_MESSAGE);
					break;
				case SurveyField3TooLong:
					JOptionPane.showMessageDialog(null, "Your Comment is Too Long, Please Enter >300 Characters", "Survey",
							JOptionPane.ERROR_MESSAGE);
					break;
				default:
					JOptionPane.showMessageDialog(null, "Thank you for you survye :)", "Survey", JOptionPane.PLAIN_MESSAGE);
					dispose();
					parent.dispose();
					break;
				}

			}
		});

		ss.gridx = 0;
		ss.gridy = 6;
		ss.anchor = GridBagConstraints.CENTER;
		ss.fill = GridBagConstraints.BOTH;
		surveyCnt.add(btnSubmit, ss);
		getContentPane().add(surveyCnt);

		pack();
		setLocationRelativeTo(parent);
		setResizable(false);
	}
}
