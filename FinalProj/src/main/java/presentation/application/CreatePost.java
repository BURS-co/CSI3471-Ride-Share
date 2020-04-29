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
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import business.CreatePostValidate;
import business.PostService;
import data.post.Post;
import data.user.User;
import enums.Failures;

/**
 * @author Joseph Perez, Andrew Ammentorp, Leighton Glim
 *
 *         Class responsible for creating a Post
 */
public class CreatePost extends JDialog {
	private static final long serialVersionUID = 1L;
	JFrame frame;
	String o = new String();
	String destination = new String();
	String m = new String();
	String d = new String();
	String y = new String();
	String h = new String();
	String min = new String();
	String tOd = new String();
	Integer seatsAvail;
	private static boolean succeeded = false;
	CreatePostValidate vPI;
	Font customFont = null;
	static Post p = null;

	/**
	 * Airports to travel from, plus campus
	 */
	String[] locs = { "ACT", "AUS", "Baylor", "DAL", "DFW", "HOU", "IAH", "SAT", "ELP" };
	/**
	 * Airports to travel to, plus campus
	 */
	String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	String[] days = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
			"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	String[] years = { "2020", "2021", "2022", "2023" };
	String[] hours = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	String[] minutes = { "00", "15", "30", "45" };
	String[] timeOfDay = { "AM", "PM" };
	Integer[] seats = { 1, 2, 3, 4, 5, 6 };

	JComboBox<String> originLoc = new JComboBox<String>(locs);
	JComboBox<String> destLoc = new JComboBox<String>(locs);
	JComboBox<String> month = new JComboBox<String>(months);
	JComboBox<String> day = new JComboBox<String>(days);
	JComboBox<String> year = new JComboBox<String>(years);
	JComboBox<String> hour = new JComboBox<String>(hours);
	JComboBox<String> minute = new JComboBox<String>(minutes);
	JComboBox<String> timeDay = new JComboBox<String>(timeOfDay);
	JComboBox<Integer> numSeats = new JComboBox<Integer>(seats);

	/**
	 * Creates the post
	 * 
	 * @param parent the frame for it to be added to
	 * @return
	 */
	public CreatePost(JFrame parent, User u) {

		super(parent, "Create Post", true);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		JLabel originLabel = new JLabel("Origin: ");
		JLabel destLabel = new JLabel("Destination: ");
		JLabel dateLabel = new JLabel("Date: ");
		JLabel timeLabel = new JLabel("Time: ");
		JLabel seatsLabel = new JLabel("Seats Available: ");

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
		originLabel.setFont(customFont);
		panel.add(originLabel, cs);

		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(originLoc, cs);

		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		destLabel.setFont(customFont);
		panel.add(destLabel, cs);

		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(destLoc, cs);

		cs.gridx = 0;
		cs.gridy = 2;
		cs.gridwidth = 1;
		dateLabel.setFont(customFont);
		panel.add(dateLabel, cs);

		cs.gridx = 1;
		cs.gridy = 2;
		cs.gridwidth = 1;
		panel.add(month, cs);

		cs.gridx = 2;
		cs.gridy = 2;
		cs.gridwidth = 1;
		panel.add(day, cs);

		cs.gridx = 3;
		cs.gridy = 2;
		cs.gridwidth = 1;
		panel.add(year, cs);

		cs.gridx = 0;
		cs.gridy = 3;
		cs.gridwidth = 1;
		timeLabel.setFont(customFont);
		panel.add(timeLabel, cs);

		cs.gridx = 1;
		cs.gridy = 3;
		cs.gridwidth = 1;
		panel.add(hour, cs);

		cs.gridx = 2;
		cs.gridy = 3;
		cs.gridwidth = 1;
		panel.add(minute, cs);

		cs.gridx = 3;
		cs.gridy = 3;
		cs.gridwidth = 1;
		panel.add(timeDay, cs);

		if (SelectPostType.postTypeSelected == "Driver") {
			cs.gridx = 0;
			cs.gridy = 4;
			cs.gridwidth = 1;
			seatsLabel.setFont(customFont);
			panel.add(seatsLabel, cs);

			cs.gridx = 1;
			cs.gridy = 4;
			cs.gridwidth = 1;
			panel.add(numSeats, cs);
		}

		JButton createPost = new JButton("Create Post");
		createPost.setFont(customFont);
		createPost.setBackground(new Color(255, 184, 25));
		createPost.setFont(customFont);
		createPost.setBorderPainted(false);
		createPost.setOpaque(true);
		createPost.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent event) {

				// gather selected fields
				o = String.valueOf(originLoc.getSelectedItem());
				destination = String.valueOf(destLoc.getSelectedItem());
				m = String.valueOf(month.getSelectedItem());
				d = String.valueOf(day.getSelectedItem());
				y = String.valueOf(year.getSelectedItem());
				h = String.valueOf(hour.getSelectedItem());
				min = String.valueOf(minute.getSelectedItem());
				tOd = String.valueOf(timeDay.getSelectedItem());

				String[] input = null;
				if (SelectPostType.postTypeSelected == "Driver") {
					seatsAvail = (Integer) numSeats.getSelectedItem();
					input = new String[] { o, destination, m, d, y, h, min, tOd, seatsAvail.toString() };
				} else {
					input = new String[] { o, destination, m, d, y, h, min, tOd };
				}

				// pass to PostService for validation and creation
				// TODO
				Failures result = PostService.getInstance().verify(input);
				if (result == Failures.SUCCESS) {
					
				} else if (result == Failures.SameOriginandDestination) {
					
				} else if (result == Failures.BadDate) {
					
				} else if (result == Failures.PostField8notANumber) {
					
				} else if (result == Failures.PostField8NotInRange) {
					
				}
				
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
				Application.log.log(Level.INFO, "Post Creation canceled");
				setSucceeded(false);
				dispose();
			}
		});

		JPanel bp = new JPanel();
		bp.add(createPost);
		bp.add(btnCancel);
		bp.setBackground(new Color(28, 60, 52));

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		panel.setBackground(new Color(255, 184, 25));

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}

	/**
	 * If the post creation was successful
	 * 
	 * @param
	 * @return succeeded if post creation was successful
	 */
	public static boolean isSucceeded() {
		return succeeded;
	}

	/**
	 * @param succeeded
	 * @return
	 */
	public static void setSucceeded(boolean s) {
		succeeded = s;
	}
}
