package presentation.application;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import business.UserService;
import data.databaseControllers.PostDatabase;
import data.databaseControllers.UserDatabase;
import data.post.AbstractPost;
import data.post.Driver;
import data.user.Admin;
import data.user.User;

/**
 * @author Joseph Perez, Andrew Ammentorp, Leighton Glim, Joshua Huertas, Joseph
 *         Yu
 *
 *         application class responsible for GUI
 */
public class Application extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * logger log for documenting events
	 */
	public static final Logger log = Logger.getLogger(Application.class.getName());

	/**
	 * fh enables logging to specified file
	 */
	static FileHandler fh;

	/*
	 * Initializing logger
	 */

	static {
		try {
			fh = new FileHandler("projectLog.txt", true);
			log.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean accountCreated = false;
	public static boolean postCreated = false;

	/**
	 * Singleton of the post database
	 */
	// public static User loggedIn;
	/**
	 * For using fonts on the graphics
	 */
	public static Font customFont;
	/**
	 * Driver posts table model
	 */
	public static DefaultTableModel dTable;
	/**
	 * Rider posts table model
	 */
	public static DefaultTableModel rTable;
	/**
	 * User's rides table model
	 */
	public static DefaultTableModel myRidesModel;

	public static JScrollPane pane;
	public static JTable riderTable;
	public static JTable driverTable;
	public static JTable myRidesTable;
	public static GridBagConstraints gc;
	public static JPanel selection;
	public static JPanel searchPnl;
	public static GridBagConstraints pc;
	public static GridBagConstraints fc;

	public static boolean riderTableUp;
	public static boolean driverTableUp;
	public static boolean myRidesTableUp;
	public static JTextField filterField;

	public Application() throws IOException, FontFormatException, ParseException {
		super();

		// Load all users from database
		UserDatabase uDat = UserDatabase.getInstance();
		uDat.load();

		// Display Open Dialog
		OpenPage openDlg = new OpenPage(new JFrame());
		openDlg.setVisible(true);

		// if login is successful continue
		if (openDlg.isSucceeded()) {
			log.log(Level.INFO, "User successfully logged in");
		} else {
			log.log(Level.INFO, "Application Closed");
			System.exit(1);
		}

		// Load posts
		PostDatabase pDat = PostDatabase.getInstance();
		pDat.load();

		// Set up tool-tips
		UIManager.put("ToolTip.background", Color.white);
		UIManager.put("ToolTip.border", new LineBorder(Color.BLACK, 1));
		
		// Setting up GridBagLayout
		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();

		// load in our font
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

		// selection panel will hold our buttons
		selection = new JPanel();
		selection.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		selection.setLayout(new GridBagLayout());
		pc = new GridBagConstraints();

		/******* First Row **********/
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(0, 6, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.BOTH;

		/***** First Row of Panel ****/

		ImageIcon rIcn = new ImageIcon("src/main/resources/ridesIcon.png");
		Image rimage = rIcn.getImage(); // transform it
		Image rnewimg = rimage.getScaledInstance(60, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		rIcn = new ImageIcon(rnewimg); // transform it back
		JButton ridesBtn = new JButton(rIcn);
		ridesBtn.setToolTipText("View All Needing A Ride");
		ridesBtn.setOpaque(false);
		ridesBtn.setContentAreaFilled(false);
		ridesBtn.setBorderPainted(false);
		ridesBtn.setFocusPainted(false);

		ridesBtn.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				if (!riderTableUp) {
					// Set coordinates
					gc.gridx = 1;
					gc.gridy = 0;
					gc.fill = GridBagConstraints.BOTH;
					remove(pane);

					pane = new JScrollPane(riderTable);
					add(pane, gc);

					searchPnl.remove(filterField);

					// Add filtering here
					filterField = RowFilterUtil.createRowFilter(riderTable);

					// add filter to panel
					fc.gridx = 1;
					fc.gridy = 0;
					fc.anchor = GridBagConstraints.RELATIVE;

					searchPnl.add(filterField, fc);
					searchPnl.revalidate();
					searchPnl.repaint();

					revalidate();
					repaint();

					riderTableUp = true;
					driverTableUp = false;
					myRidesTableUp = false;
				}
			}
		});

		pc.weightx = 1;
		pc.weighty = 1;

		pc.gridx = 0;
		pc.gridy = 0;
		pc.anchor = GridBagConstraints.CENTER;
		selection.add(ridesBtn, pc);

		/**** Second Row of Panel ****/
		ImageIcon drIcn = new ImageIcon("src/main/resources/drivesIcon.png");
		Image drimage = drIcn.getImage(); // transform it
		Image drnewimg = drimage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		drIcn = new ImageIcon(drnewimg); // transform it back
		JButton drivesBtn = new JButton(drIcn);
		drivesBtn.setToolTipText("View All Available Rides");
		drivesBtn.setOpaque(false);
		drivesBtn.setContentAreaFilled(false);
		drivesBtn.setBorderPainted(false);
		drivesBtn.setFocusPainted(false);

		drivesBtn.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				if (!driverTableUp) {
					gc.gridx = 1;
					gc.gridy = 0;
					gc.fill = GridBagConstraints.BOTH;
					remove(pane);

					// new pane
					pane = new JScrollPane(driverTable);
					add(pane, gc);

					searchPnl.remove(filterField);
					// Add filtering here
					filterField = RowFilterUtil.createRowFilter(driverTable);

					fc.gridx = 1;
					fc.gridy = 0;
					fc.anchor = GridBagConstraints.RELATIVE;
					searchPnl.add(filterField, fc);
					searchPnl.revalidate();
					searchPnl.repaint();

					revalidate();
					repaint();

					driverTableUp = true;
					riderTableUp = false;
					myRidesTableUp = false;
				}
			}
		});

		pc.gridx = 0;
		pc.gridy = 1;

		selection.add(drivesBtn, pc);

		/**** Third Row of Panel ****/
		ImageIcon pIcn = new ImageIcon("src/main/resources/profileIcon.png");
		Image pimage = pIcn.getImage(); // transform it
		Image pnewimg = pimage.getScaledInstance(60, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		pIcn = new ImageIcon(pnewimg); // transform it back
		JButton profileBtn = new JButton(pIcn);
		profileBtn.setToolTipText("Your Profile");
		profileBtn.setOpaque(false);
		profileBtn.setContentAreaFilled(false);
		profileBtn.setBorderPainted(false);
		profileBtn.setFocusPainted(false);

		profileBtn.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				ViewProfile vp = new ViewProfile(null, UserService.getInstance().getCurrentUser());
				vp.setVisible(true);
			}
		});

		pc.gridx = 0;
		pc.gridy = 2;
		selection.add(profileBtn, pc);

		/**** Fourth Row of Panel ****/
		ImageIcon rideIcn = new ImageIcon("src/main/resources/myRidesIcon.png");
		Image img = rideIcn.getImage(); // transform it
		Image newImage = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		rideIcn = new ImageIcon(newImage); // transform it back
		JButton myRidesBtn = new JButton(rideIcn);
		myRidesBtn.setToolTipText("View All Your Rides");
		myRidesBtn.setOpaque(false);
		myRidesBtn.setContentAreaFilled(false);
		myRidesBtn.setBorderPainted(false);
		myRidesBtn.setFocusPainted(false);

		// query for rider posts
		ArrayList<AbstractPost> myList = pDat.quereyDatabase(UserService.getInstance().getCurrentUser().getEmail());

		myRidesTable = CreateMyRidesTable.createTable(myList);

		// make it so columns may not be dragged around for
		// driver or rider posts
		myRidesTable.getTableHeader().setReorderingAllowed(false);
		myRidesTable.setFillsViewportHeight(true);

		myRidesTable.setOpaque(true);

		String[] myRidesLabels = { "Type", "Poster", "Origin", "Destination", "Date", "" };
		myRidesModel = (DefaultTableModel) myRidesTable.getModel();

		myRidesTable.getColumn(myRidesLabels[0]).setPreferredWidth(35);
		myRidesTable.getColumn(myRidesLabels[1]).setPreferredWidth(100);
		myRidesTable.getColumn(myRidesLabels[2]).setPreferredWidth(50);
		myRidesTable.getColumn(myRidesLabels[3]).setPreferredWidth(50);
		myRidesTable.getColumn(myRidesLabels[4]).setPreferredWidth(100);
		myRidesTable.getColumnModel().removeColumn(myRidesTable.getColumn(myRidesLabels[5]));

		if (myList.size() > 0) {
			myRidesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			// When selection changes, provide user with row numbers for both view & model.
			myRidesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent event) {
					int viewRow = myRidesTable.getSelectedRow();
					if (viewRow < 0) {
						// Selection got filtered away.
						// statusText.setText("");
					} else {
						// String type = (String) myRidesTable.getValueAt(viewRow, 0);
						String name = (String) myRidesTable.getValueAt(viewRow, 1);
						String orig = (String) myRidesTable.getValueAt(viewRow, 2);
						String dest = (String) myRidesTable.getValueAt(viewRow, 3);
						String date = (String) myRidesTable.getValueAt(viewRow, 4);
						String postId = (String) myRidesTable.getModel().getValueAt(viewRow, 5);
						ViewPostInfo vpi = new ViewPostInfo(null, name, orig, dest, date, postId);
						vpi.setVisible(true);

					}
				}
			});
		}

		myRidesBtn.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				if (!myRidesTableUp) {
					// Set coordinates
					gc.gridx = 1;
					gc.gridy = 0;
					gc.fill = GridBagConstraints.BOTH;

					// remove pane
					remove(pane);

					pane = new JScrollPane(myRidesTable);
					add(pane, gc);

					revalidate();
					repaint();

					myRidesTableUp = true;
					riderTableUp = false;
					driverTableUp = false;
				}

			}
		});

		pc.gridx = 0;
		pc.gridy = 3;
		selection.add(myRidesBtn, pc);

		/**** Fourth Row of Panel ****/
		ImageIcon crtIcn = new ImageIcon("src/main/resources/createPostIcon.png");
		Image image = crtIcn.getImage(); // transform it
		Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		crtIcn = new ImageIcon(newimg); // transform it back
		JButton createBtn = new JButton(crtIcn);
		createBtn.setToolTipText("Create A Post");
		createBtn.setOpaque(false);
		createBtn.setContentAreaFilled(false);
		createBtn.setBorderPainted(false);
		createBtn.setFocusPainted(false);

		createBtn.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				SelectPostType cp = new SelectPostType(null, UserService.getInstance().getCurrentUser());
				cp.setVisible(true);
				if (CreatePost.isSucceeded()) {
					try {
						PostDatabase.getInstance().write();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			/*		if (CreatePost.post instanceof Driver) {
						String driver = CreatePost.post.getPoster();
						Integer riderLimit = ((Driver) CreatePost.post).getRiderLimit();
						String origin = CreatePost.post.getOrigin();
						String dest = CreatePost.post.getDest();
						SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm a");
						String date = df.format(CreatePost.post.getDate());

						Object[] row = { riderLimit, driver, origin, dest, date, CreatePost.post.getID() };

						if(driverTableUp) {
						//	dTable.addRow(row);
							dTable.fireTableDataChanged();
							CreatePost.setSucceeded(false);
						}
					} else {
						String poster = CreatePost.post.getPoster();
						String origin = CreatePost.post.getOrigin();
						String dest = CreatePost.post.getDest();
						SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm a");
						String date = df.format(CreatePost.post.getDate());

						Object[] row = { poster, origin, dest, date, CreatePost.post.getID() };

						if(riderTableUp) {
						//	rTable.addRow(row);
							rTable.fireTableDataChanged();
							CreatePost.setSucceeded(false);
						}
					}*/
				}

			}
		});

		pc.gridx = 0;
		pc.gridy = 4;
		selection.add(createBtn, pc);

		/**** Fifth Row of Panel (ADMIN) ****/
		if (UserService.getInstance().getCurrentUser() instanceof Admin) {
			ImageIcon reportIcn = new ImageIcon("src/main/resources/reportsIcon.png");
			Image reportimage = reportIcn.getImage(); // transform it
			Image reportnewimg = reportimage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // scale it the
			// smooth
			// way
			rIcn = new ImageIcon(reportnewimg); // transform it back
			JButton reportBtn = new JButton(rIcn);
			reportBtn.setToolTipText("View Reports");
			profileBtn.setOpaque(false);
			profileBtn.setContentAreaFilled(false);
			profileBtn.setBorderPainted(false);
			profileBtn.setFocusPainted(false);

			reportBtn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					// Make report
					AdminReport a = new AdminReport(null);
					a.setVisible(true);
				}

			});

			pc.gridx = 0;
			pc.gridy = 5;
			selection.add(reportBtn, pc);
		}

		selection.setBackground(new Color(255, 255, 255));

		// Adding Panel to frame
		add(selection, gc);
		
		// tables
		// query for rider posts
		ArrayList<AbstractPost> rlist = pDat.quereyDatabase("rider");

		// query for driver posts
		ArrayList<AbstractPost> dlist = pDat.quereyDatabase("driver");

		// create table of posts
		riderTable = CreateRiderTable.createTable(rlist);
		driverTable = CreateDriverTable.createTable(dlist);

		// make it so columns may not be dragged around for
		// driver or rider posts
		riderTable.getTableHeader().setReorderingAllowed(false);
		driverTable.getTableHeader().setReorderingAllowed(false);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.BOTH;
		pane = new JScrollPane(riderTable);
		add(pane, gc);
		riderTableUp = true;
		driverTableUp = !riderTableUp;
		myRidesTableUp = !riderTableUp;

		riderTable.setFillsViewportHeight(true);
		driverTable.setFillsViewportHeight(true);

		// mainFrame.setContentPane(table);
		riderTable.setOpaque(true);
		driverTable.setOpaque(true);

		String[] riderPostLabels = { "Poster", "Origin", "Destination", "Date","" };
		rTable = (DefaultTableModel) riderTable.getModel();

		riderTable.getColumn(riderPostLabels[0]).setPreferredWidth(100);
		riderTable.getColumn(riderPostLabels[1]).setPreferredWidth(35);
		riderTable.getColumn(riderPostLabels[2]).setPreferredWidth(50);
		riderTable.getColumn(riderPostLabels[3]).setPreferredWidth(100);
		riderTable.getColumnModel().removeColumn(riderTable.getColumn(riderPostLabels[4]));

		riderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// When selection changes, provide user with row numbers for both view & model.
		riderTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				int viewRow = riderTable.getSelectedRow();
				if (viewRow < 0) {
					// Selection got filtered away.
					// statusText.setText("");
				} else {
					String name = (String) riderTable.getValueAt(viewRow, 0);
					String orig = (String) riderTable.getValueAt(viewRow, 1);
					String dest = (String) riderTable.getValueAt(viewRow, 2);
					String date = (String) riderTable.getValueAt(viewRow, 3);
					String postId = (String) riderTable.getModel().getValueAt(viewRow, 4);
					ViewPostInfo vpi = new ViewPostInfo(null, name, orig, dest, date, postId);
					vpi.setVisible(true);

				}
			}
		});

		String[] driverPostLabels = { "Seats", "Driver", "Origin", "Destination", "Date","" };
		dTable = (DefaultTableModel) driverTable.getModel();
		driverTable.getColumn(driverPostLabels[0]).setPreferredWidth(30);
		driverTable.getColumn(driverPostLabels[1]).setPreferredWidth(100);
		driverTable.getColumn(driverPostLabels[2]).setPreferredWidth(35);
		driverTable.getColumn(driverPostLabels[3]).setPreferredWidth(50);
		driverTable.getColumn(driverPostLabels[4]).setPreferredWidth(100);
		driverTable.getColumnModel().removeColumn(driverTable.getColumn(driverPostLabels[5]));

		driverTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// When selection changes, provide user with row numbers for both view & model.
		driverTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				int viewRow = driverTable.getSelectedRow();
				if (viewRow < 0) {
					// Selection got filtered away.
					// statusText.setText("");
				} else {
					String seats = (String) driverTable.getValueAt(viewRow, 0);
					String name = (String) driverTable.getValueAt(viewRow, 1);
					String orig = (String) driverTable.getValueAt(viewRow, 2);
					String dest = (String) driverTable.getValueAt(viewRow, 3);
					String date = (String) driverTable.getValueAt(viewRow, 4);
					String postId = (String) driverTable.getModel().getValueAt(viewRow, 5);
					ViewPostInfo vpi = new ViewPostInfo(null, seats, name, orig, dest, date, postId);
					vpi.setVisible(true);

				}
			}
		});
		
		/** Filter Panel **/
		searchPnl = new JPanel();
		fc = new GridBagConstraints();

		/*** Search Panel Components ***/
		fc.gridx = 0;
		fc.gridy = 0;
		fc.anchor = GridBagConstraints.FIRST_LINE_START;
		JLabel filterLabel = new JLabel("Filter posts:");
		filterLabel.setFont(customFont);
		searchPnl.add(filterLabel, fc);

		fc.gridx = 1;
		fc.gridy = 0;
		fc.anchor = GridBagConstraints.RELATIVE;

		// Add filtering here
		filterField = RowFilterUtil.createRowFilter(riderTable);

		searchPnl.add(filterField, fc);

		// place panel into frame
		gc.gridx = 2;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.BOTH;
		add(searchPnl, gc);

		setVisible(true);
		
		//ArrayList<AbstractPost> ps = PostDatabase.getInstance().getPostData();

		UserDatabase.getInstance().write();
		PostDatabase.getInstance().write();
	}

	/**
	 * method loads information for the application and creates the GUI and displays
	 * it.
	 * 
	 * @throws IOException         if user database has issue reading or writing
	 * @throws ParseException      if issue with parsing database
	 * @throws FontFormatException
	 */
	public static void createAndShowGUI() throws IOException, FontFormatException, ParseException {

		JFrame mainFrame = new JFrame("BearPool");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		Application app = new Application();
		app.setOpaque(true);
		mainFrame.setContentPane(app);

		mainFrame.pack();
		mainFrame.setVisible(true);

	}

	/**
	 * main method for the application
	 * 
	 * @param args (unused)
	 */
	public static void main(String[] args) {

		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (FontFormatException e) {
					e.printStackTrace();
				}
			}

		});

	}

}
