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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import business.UserService;
import data.post.Rider;
import data.databaseControllers.PostDatabase;
import data.databaseControllers.UserDatabase;
import data.post.Driver;
import data.post.Prospects;

public class ViewPostInfo extends JDialog {
	private static final long serialVersionUID = 1L;
	Font customFont;

	JLabel nameLabel = new JLabel("Name: ");
	JLabel seatLabel = new JLabel("Seats Available: ");
	JLabel origLabel = new JLabel("Origin: ");
	JLabel destLabel = new JLabel("Destination: ");
	JLabel dateLabel = new JLabel("Date: ");
	JLabel postLabel = new JLabel("ID: ");

	/**
	 * Shows info of selected driver post
	 * 
	 * @param parent frame for encapsulating data, name, origin, destination, date
	 *               of ride
	 * @return
	 */
	public ViewPostInfo(JFrame parent, String seats, String name, String orig, String dest, String date, final String postID) {
		super(parent, "Post Info", true);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		// initialize labels
		JLabel n = new JLabel(name);
		JLabel s = new JLabel(seats);
		JLabel o = new JLabel(orig);
		JLabel d = new JLabel(dest);
		JLabel dt = new JLabel(date);
		JLabel postIDLabel = new JLabel(postID);

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

		cs.fill = GridBagConstraints.HORIZONTAL;

		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		nameLabel.setFont(customFont);
		panel.add(nameLabel, cs);

		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 1;
		n.setFont(customFont);
		panel.add(n, cs);

		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		seatLabel.setFont(customFont);
		panel.add(seatLabel, cs);

		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 1;
		s.setFont(customFont);
		panel.add(s, cs);

		cs.gridx = 0;
		cs.gridy = 2;
		cs.gridwidth = 1;
		origLabel.setFont(customFont);
		panel.add(origLabel, cs);

		cs.gridx = 1;
		cs.gridy = 2;
		cs.gridwidth = 1;
		o.setFont(customFont);
		panel.add(o, cs);

		cs.gridx = 0;
		cs.gridy = 3;
		cs.gridwidth = 2;
		destLabel.setFont(customFont);
		panel.add(destLabel, cs);

		cs.gridx = 1;
		cs.gridy = 3;
		cs.gridwidth = 2;
		d.setFont(customFont);
		panel.add(d, cs);

		cs.gridx = 0;
		cs.gridy = 4;
		cs.gridwidth = 2;
		dateLabel.setFont(customFont);
		panel.add(dateLabel, cs);

		cs.gridx = 1;
		cs.gridy = 4;
		cs.gridwidth = 2;
		dt.setFont(customFont);
		panel.add(dt, cs);
		
		cs.gridx = 0;
		cs.gridy = 5;
		cs.gridwidth = 2;
		dateLabel.setFont(customFont);
		panel.add(postLabel, cs);

		cs.gridx = 1;
		cs.gridy = 5;
		cs.gridwidth = 2;
		dt.setFont(customFont);
		panel.add(postIDLabel, cs);

		JButton acceptRide = new JButton("Accept Ride");
		acceptRide.setFont(customFont);
		acceptRide.setBackground(new Color(255, 184, 25));
		acceptRide.setFont(customFont);
		acceptRide.setBorderPainted(false);
		acceptRide.setOpaque(true);

		acceptRide.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent event) {

				int temp = Integer.valueOf(postID);
				Driver d = ((Driver)PostDatabase.getInstance().searchDatabase(temp));
				
				Prospects rider = new Prospects();
				rider.setName(UserService.getInstance().getCurrentUser().getUsername());
				rider.setStatus(false);
				
				if(d.getRiderLimit() > d.getRiders().size()) {
					
					System.out.println("TEST PLZ" + d.toString());
					
					ArrayList<Prospects> riders = d.getRiders();
					riders.add(rider);
					d.setRiders(riders);
					
					System.out.println(d.toString());
					
					PostDatabase.getInstance().storeUpdate(d);
					
					UserDatabase.getInstance().queryDatabase(d.getPoster()).setJoinNotif(true);
				}
				else {
					//TODO
					//pop up that this ride already has too many riders
				}
				
				
				
				// Keep track of user logged in
				// Application.loggedIn = u;

				// ImageIcon icon = new ImageIcon("src/main/resources/poolfloat
				// icon-yellow.png");
				// JOptionPane.showMessageDialog(null, "Hi " + u.getUsername() + "! Welcome to
				// Bearpool!", "Login",
				// JOptionPane.INFORMATION_MESSAGE, icon);
				// Application.log.log(Level.INFO, Application.loggedIn + " accepted a ride from
				// " + name);
				dispose();

			}
		});
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(customFont);
		btnCancel.setFont(customFont);
		btnCancel.setBackground(new Color(255, 184, 25));
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
				Application.log.log(Level.INFO, "Post info closed");
				dispose();
			}
		});

		JButton removePost = new JButton("Report Post");
		removePost.setFont(customFont);
		removePost.setFont(customFont);
		removePost.setBackground(new Color(255, 184, 25));
		removePost.setFont(customFont);
		removePost.setBorderPainted(false);
		removePost.setOpaque(true);
		removePost.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				Application.log.log(Level.INFO, "Post info closed");
				dispose();
			}
		});

		JPanel bp = new JPanel();
		bp.add(acceptRide);
		bp.add(btnCancel);
		if (UserService.getInstance().getCurrentUser().getUsername().equals(name))
			bp.add(removePost);

		bp.setBackground(new Color(28, 60, 52));

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		panel.setBackground(new Color(255, 184, 25));

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}

	/**
	 * Shows info of selected driver post
	 * 
	 * @param parent frame for encapsulating data, name, origin, destination, date
	 *               of ride
	 * @return
	 */
	public ViewPostInfo(JFrame parent, String name, String orig, String dest, String date, final String postID) {
		super(parent, "Post Info", true);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		// initialize labels
		JLabel n = new JLabel(name);
		JLabel o = new JLabel(orig);
		JLabel d = new JLabel(dest);
		JLabel dt = new JLabel(date);
		JLabel postIDLabel = new JLabel(postID);

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

		cs.fill = GridBagConstraints.HORIZONTAL;

		cs.gridx = 0;
		cs.gridy = 0;
		nameLabel.setFont(customFont);
		panel.add(nameLabel, cs);

		cs.gridx = 1;
		cs.gridy = 0;
		n.setFont(customFont);
		panel.add(n, cs);

		cs.gridx = 0;
		cs.gridy = 1;
		origLabel.setFont(customFont);
		panel.add(origLabel, cs);

		cs.gridx = 1;
		cs.gridy = 1;
		o.setFont(customFont);
		panel.add(o, cs);

		cs.gridx = 0;
		cs.gridy = 2;
		destLabel.setFont(customFont);
		panel.add(destLabel, cs);

		cs.gridx = 1;
		cs.gridy = 2;
		d.setFont(customFont);
		panel.add(d, cs);

		cs.gridx = 0;
		cs.gridy = 3;
		dateLabel.setFont(customFont);
		panel.add(dateLabel, cs);

		cs.gridx = 1;
		cs.gridy = 3;
		dt.setFont(customFont);
		panel.add(dt, cs);
		
		cs.gridx = 0;
		cs.gridy = 4;
		cs.gridwidth = 2;
		dateLabel.setFont(customFont);
		panel.add(postLabel, cs);

		cs.gridx = 1;
		cs.gridy = 4;
		cs.gridwidth = 2;
		dt.setFont(customFont);
		panel.add(postIDLabel, cs);

		JButton offerRide = new JButton("Offer Ride");
		offerRide.setFont(customFont);
		offerRide.setBackground(new Color(255, 184, 25));
		offerRide.setFont(customFont);
		offerRide.setBorderPainted(false);
		offerRide.setOpaque(true);

		offerRide.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent event) {

				int temp = Integer.valueOf(postID);
				Rider r = ((Rider)PostDatabase.getInstance().searchDatabase(temp));
				
				
				
				Prospects driver = new Prospects();
				driver.setName(UserService.getInstance().getCurrentUser().getUsername());
				driver.setStatus(false);
				
				r.setDriver(driver);
				
				
				//System.out.println(PostDatabase.getInstance().quereyDatabase(r.getID()).toString);
				PostDatabase.getInstance().storeUpdate(r);
				
				UserDatabase.getInstance().queryDatabase(r.getPoster()).setJoinNotif(true);
				
				// Keep track of user logged in
				// Application.loggedIn = u;

				// ImageIcon icon = new ImageIcon("src/main/resources/poolfloat
				// icon-yellow.png");
				// JOptionPane.showMessageDialog(null, "Hi " + u.getUsername() + "! Welcome to
				// Bearpool!", "Login",
				// JOptionPane.INFORMATION_MESSAGE, icon);
				// Application.log.log(Level.INFO, "You have offered " + name + " a ride!");
				dispose();

			}
		});
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(customFont);
		btnCancel.setFont(customFont);
		btnCancel.setBackground(new Color(255, 184, 25));
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
				Application.log.log(Level.INFO, "Post info closed");
				dispose();
			}
		});

		JButton removePost = new JButton("Remove Post");
		removePost.setFont(customFont);
		removePost.setFont(customFont);
		removePost.setBackground(new Color(255, 184, 25));
		removePost.setFont(customFont);
		removePost.setBorderPainted(false);
		removePost.setOpaque(true);
		removePost.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				Application.log.log(Level.INFO, "Post info closed");
				dispose();
			}
		});

		JPanel bp = new JPanel();
		bp.add(offerRide);
		bp.add(btnCancel);
		if (UserService.getInstance().getCurrentUser().getUsername().equals(name))
			bp.add(removePost);

		bp.setBackground(new Color(28, 60, 52));

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		panel.setBackground(new Color(255, 184, 25));

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
}
