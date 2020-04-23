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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import business.CreatePostValidate;
import data.databaseControllers.PostDatabase;
import data.post.DriverPost;
import data.post.Post;
import data.user.User;

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
	private JButton btnCancel;
	Font customFont = null;
	static User u = new User();
	static Post p = null;

	String[] origin = { "ACT", "AUS", "Baylor", "DAL", "DFW", "HOU", "IAH", "SAT", "ELP" };
	String[] dest = { "ACT", "AUS", "Baylor", "DAL", "DFW", "HOU", "IAH", "SAT", "ELP" };
	String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	String[] days = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
			"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	String[] years = { "2020", "2021", "2022", "2023" };
	String[] hours = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	String[] minutes = { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" };
	String[] timeOfDay = { "AM", "PM" };
	Integer[] seats = { 1, 2, 3, 4, 5, 6 };

	/**
	 * @param parent
	 * @return
	 */
	public CreatePost(JFrame parent, User u) {
		super(parent, "Create Post", true);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		JComboBox originLoc = new JComboBox(origin);
		JComboBox destLoc = new JComboBox(dest);
		JComboBox month = new JComboBox(months);
		JComboBox day = new JComboBox(days);
		JComboBox year = new JComboBox(years);
		JComboBox hour = new JComboBox(hours);
		JComboBox minute = new JComboBox(minutes);
		JComboBox timeDay = new JComboBox(timeOfDay);
		JComboBox numSeats = new JComboBox(seats);
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

		originLoc.setSelectedIndex(-1);
		originLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				o = (String) cb.getSelectedItem();
			}
		});

		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(originLoc, cs);

		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		destLabel.setFont(customFont);
		panel.add(destLabel, cs);

		destLoc.setSelectedIndex(-1);
		destLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				destination = (String) cb.getSelectedItem();
			}
		});

		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(destLoc, cs);

		cs.gridx = 0;
		cs.gridy = 2;
		cs.gridwidth = 1;
		dateLabel.setFont(customFont);
		panel.add(dateLabel, cs);

		month.setSelectedIndex(-1);
		month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				m = (String) cb.getSelectedItem();
			}
		});

		cs.gridx = 1;
		cs.gridy = 2;
		cs.gridwidth = 1;
		panel.add(month, cs);

		day.setSelectedIndex(-1);
		day.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				d = (String) cb.getSelectedItem();
			}
		});

		cs.gridx = 2;
		cs.gridy = 2;
		cs.gridwidth = 1;
		panel.add(day, cs);

		year.setSelectedIndex(-1);
		year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				y = (String) cb.getSelectedItem();
			}
		});

		cs.gridx = 3;
		cs.gridy = 2;
		cs.gridwidth = 1;
		panel.add(year, cs);

		cs.gridx = 0;
		cs.gridy = 3;
		cs.gridwidth = 1;
		timeLabel.setFont(customFont);
		panel.add(timeLabel, cs);

		hour.setSelectedIndex(-1);
		hour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				h = (String) cb.getSelectedItem();
			}
		});

		cs.gridx = 1;
		cs.gridy = 3;
		cs.gridwidth = 1;
		panel.add(hour, cs);

		minute.setSelectedIndex(-1);
		minute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				min = (String) cb.getSelectedItem();
			}
		});

		cs.gridx = 2;
		cs.gridy = 3;
		cs.gridwidth = 1;
		panel.add(minute, cs);

		timeDay.setSelectedIndex(-1);
		timeDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				tOd = (String) cb.getSelectedItem();
			}
		});

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

			numSeats.setSelectedIndex(-1);
			numSeats.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					JComboBox cb = (JComboBox) e.getSource();
					seatsAvail = new Integer((int) cb.getSelectedItem());
				}
			});

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
			@SuppressWarnings("static-access")
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent event) {
				if (o.length() == 0 || destination.length() == 0 || m.length() == 0 || d.length() == 0 || y.length() == 0
						|| h.length() == 0 || min.length() == 0 || tOd.length() == 0) {
					JOptionPane.showMessageDialog(CreatePost.this, "Please fill in all fields.", "Create Post",
							JOptionPane.INFORMATION_MESSAGE);
					succeeded = false;

				} else {
					// make sure text entered in all fields
					if (o.length() > 0 && destination.length() > 0 && m.length() > 0 && d.length() > 0 && y.length() > 0
							&& h.length() > 0 && min.length() > 0 && tOd.length() > 0) {
						

						if (SelectPostType.postTypeSelected == "Driver") {
							try {
								if(vPI.validatePostInfo(o, destination, d, m, y, h, min, tOd,seatsAvail, CreatePost.this)) {
									p = new DriverPost();
									p.setType("driver");
									p.setPoster(Application.loggedIn.getUsername());
									((DriverPost) p).setRiderLimit(seatsAvail);
									((DriverPost) p).setDriver(Application.loggedIn.getUsername());
								}
							} catch (ParseException e) {
								e.printStackTrace();
							}
						} else {
							try {
								if(vPI.validatePostInfo(o,destination, d, m, y, h, min, tOd, CreatePost.this))
									p = new Post();
							} catch (ParseException e) {
								e.printStackTrace();
							}
								p.setType("rider");
						}
						if(vPI.succeeded) {
							p.setPoster(Application.loggedIn.getUsername());
							p.setOrigin(o);
							p.setDest(destination);

							String dayTime = d + " " + m + " " + y + " " + h + ":" + min + " " + tOd;
							Date d;
							try {
								d = new SimpleDateFormat("dd MMM yyyy hh:mm a").parse(dayTime);
								p.setDate(d);
							} catch (ParseException e) {
								e.printStackTrace();
							}
	
							ArrayList<Post> posts = PostDatabase.getPostData();
							posts.add(p);
							try {
								PostDatabase.write();
							} catch (IOException e) {
								e.printStackTrace();
							}
	
							ImageIcon icon = new ImageIcon("src/main/resources/poolfloat icon-yellow.png");
							JOptionPane.showMessageDialog(null, "Post Created Successfully. ", "Create Post",
									JOptionPane.INFORMATION_MESSAGE, icon);
							succeeded = true;
							Application.log.log(Level.INFO, Application.loggedIn.getUsername() + " sucessfuly created a post");
							dispose();
						}
					}
				}
			}
		});
		btnCancel = new JButton("Cancel");
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
	 * @param
	 * @return succeeded
	 */
	public static boolean isSucceeded() {
		return succeeded;
	}

	public void setUser(User user) {
		this.u = user;
	}

	public static User getUser() {
		return u;
	}
}
