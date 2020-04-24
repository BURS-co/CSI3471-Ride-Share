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
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import business.ValidateAccountInfo;
import data.databaseControllers.PostDatabase;
import data.user.User;

public class SelectPostType extends JDialog {
	private static final long serialVersionUID = 1L;
	JFrame frame;
	ValidateAccountInfo vaI;
	private boolean succeeded = false;
	Font customFont = null;
	public static String postTypeSelected = new String();
	String[] postTypes = { "Rider", "Driver"};
	
	/**
	 * @param parent
	 * @return
	 */
	public SelectPostType(JFrame parent, User u) {
		super(parent, "Create Post", true);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		

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

		
		JLabel type = new JLabel("Post Type: ");
		JComboBox postType = new JComboBox(postTypes);
		postType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				setPostTypeSelected((String) cb.getSelectedItem());
			}
		});

		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		type.setFont(customFont);
		panel.add(type, cs);
		
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(postType, cs);
		
		JButton select = new JButton("Select");
		select.setFont(customFont);
		select.setBackground(new Color(255,184,25));
		select.setFont(customFont);
		select.setBorderPainted(false);
		select.setOpaque(true);
		select.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent event) {
				CreatePost cp = new CreatePost(parent, u);
				cp.setVisible(true);
				if(cp.isSucceeded())
					dispose();
			}
		});
		JButton btnCancel = new JButton("Cancel");
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
				Application.log.log(Level.INFO, "Post Creation cancelled");
				dispose();
			}
		});
		
		

		JPanel bp = new JPanel();
		bp.add(select);
		bp.add(btnCancel);
		bp.setBackground(new Color(28,60,52));

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		panel.setBackground(new Color(255,184,25));

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}

	public String getPostTypeSelected() {
		return postTypeSelected;
	}

	public void setPostTypeSelected(String postTypeSelected) {
		this.postTypeSelected = postTypeSelected;
	}
}
