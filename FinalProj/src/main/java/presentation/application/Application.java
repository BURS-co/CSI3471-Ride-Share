package presentation.application;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import data.databaseControllers.PostDatabase;
import data.databaseControllers.UserDatabase;
import data.post.AbstractPost;
import data.user.Admin;
import data.user.User;

/**
 * @author Joseph Perez, Andrew Ammentorp, Leighton Glim, Joshua Huertas, Joseph
 *         Yu
 *
 *         application class responsible for GUI
 */
public class Application {

	/**
	 * logger log for documenting events
	 */
	public final static Logger log = Logger.getLogger(Application.class.getName());

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
	public static User loggedIn;
	/**
	 * For using fonts on the graphics
	 */
	public static Font customFont = null;
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

	public static JTable riderTable;
	public static JTable driverTable;
	public static JTable myRidesTable;
	public static GridBagConstraints gc;
	public static JPanel selection;
	public static GridBagConstraints pc;

	public static boolean riderTableUp;
	public static boolean driverTableUp;
	public static boolean myRidesTableUp;

	public static JScrollPane pane;

	public static JTextField filterField;

	/**
	 * main method for the application
	 * 
	 * @param args (unused)
	 * @throws IOException         if user database has issue reading or writing
	 * @throws ParseException      if issue with parsing database
	 * @throws FontFormatException if font not found
	 * @throws HeadlessException   if key/mouse function not available on machine
	 */
	public static void main(String[] args) throws ParseException, IOException, HeadlessException, FontFormatException {

		loggedIn = null;

		// Load all users from database
		UserDatabase uDat = UserDatabase.getInstance();
		uDat.load();

		JFrame mainFrame = new JFrame("BearPool");

		OpenPage openDlg = new OpenPage(mainFrame);
		openDlg.setVisible(true);

		// if login is successful
		if (openDlg.isSucceeded()) {
			log.log(Level.INFO, "User successfully logged in");
			createRunGUI();
		} else {
			log.log(Level.INFO, "Application Closed");
			System.exit(1);
		}

	}

	/**
	 * method loads information for the application and creates the GUI and displays
	 * it.
	 * 
	 * @throws IOException    if user database has issue reading or writing
	 * @throws ParseException if issue with parsing database
	 */
	public static void createRunGUI() throws IOException, ParseException {
		final JFrame mainFrame = new JFrame("Bearpool");

		// Load posts
		PostDatabase pDat = PostDatabase.getInstance();
		pDat.load();

		// Setting up GridBagLayout
		mainFrame.setLayout(new GridBagLayout());
		gc = new GridBagConstraints();

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

		selection = new JPanel();
		Border innerB = BorderFactory.createEmptyBorder();
		Border outerB = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		selection.setBorder(BorderFactory.createCompoundBorder(outerB, innerB));

		// weight
		gc.weightx = 1;
		gc.weighty = 1;

		// query for rider posts
		ArrayList<AbstractPost> rlist = pDat.searchDatabase("rider");

		// query for driver posts
		ArrayList<AbstractPost> dlist = pDat.searchDatabase("driver");

		// create table of posts
		riderTable = CreateRiderTable.createTable(rlist);
		driverTable = CreateDriverTable.createTable(dlist);
		
		/** First Row of Panel **/
		JPanel searchPnl = new JPanel();
		GridBagConstraints fc = new GridBagConstraints();
		
		/*** Search Panel Components ***/
		fc.gridx = 0;
		fc.gridy = 0;
		fc.anchor = GridBagConstraints.FIRST_LINE_START;
		JLabel filterLabel = new JLabel("Filter posts:");
		filterLabel.setFont(customFont);
		searchPnl.add(filterLabel,fc);

		
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
		mainFrame.add(searchPnl, gc);

		// make it so columns may not be dragged around for
		// driver or rider posts
		riderTable.getTableHeader().setReorderingAllowed(false);
		driverTable.getTableHeader().setReorderingAllowed(false);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.BOTH;
		pane = new JScrollPane(riderTable);
		mainFrame.add(pane, gc);
		riderTableUp = true;
		driverTableUp = !riderTableUp;
		myRidesTableUp = !riderTableUp;
		// gc.gridx = 2;
		// gc.gridy = 0;
		// mainFrame.add(new JScrollPane(driverTable), gc);

		riderTable.setFillsViewportHeight(true);
		driverTable.setFillsViewportHeight(true);

		// mainFrame.setContentPane(table);
		riderTable.setOpaque(true);
		driverTable.setOpaque(true);

		// TODO sorting of rows

		// TODO make table fit to screen
		// DefaultTableModel rTable = new DefaultTableModel();
		// riderTable.setModel(rTable);

		String[] riderPostLabels = { "Poster", "Origin", "Destination", "Date" };
		rTable = (DefaultTableModel) riderTable.getModel();

		riderTable.getColumn(riderPostLabels[0]).setPreferredWidth(100);
		riderTable.getColumn(riderPostLabels[1]).setPreferredWidth(35);
		riderTable.getColumn(riderPostLabels[2]).setPreferredWidth(50);
		riderTable.getColumn(riderPostLabels[3]).setPreferredWidth(100);

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
					ViewPostInfo vpi = new ViewPostInfo(mainFrame, name, orig, dest, date);
					vpi.setVisible(true);

				}
			}
		});

		String[] driverPostLabels = { "Seats", "Driver", "Origin", "Destination", "Date" };
		dTable = (DefaultTableModel) driverTable.getModel();
		driverTable.getColumn(driverPostLabels[0]).setPreferredWidth(30);
		driverTable.getColumn(driverPostLabels[1]).setPreferredWidth(100);
		driverTable.getColumn(driverPostLabels[2]).setPreferredWidth(35);
		driverTable.getColumn(driverPostLabels[3]).setPreferredWidth(50);
		driverTable.getColumn(driverPostLabels[4]).setPreferredWidth(100);

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
					ViewPostInfo vpi = new ViewPostInfo(mainFrame, seats, name, orig, dest, date);
					vpi.setVisible(true);

				}
			}
		});

		/******* First Row **********/
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(0, 6, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.BOTH;

		/***** First Row of Panel ****/

		ImageIcon rIcn = new ImageIcon("src/main/resources/ridesIcon.png");
		Image rimage = rIcn.getImage(); // transform it
		// 60 makes it a tad wider
		Image rnewimg = rimage.getScaledInstance(60, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		rIcn = new ImageIcon(rnewimg); // transform it back
		JButton ridesBtn = new JButton(rIcn);
		ridesBtn.setOpaque(false);
		ridesBtn.setContentAreaFilled(false);
		ridesBtn.setBorderPainted(false);
		ridesBtn.setFocusPainted(false);
		// JButton ridesBtn = new JButton();
		selection.setLayout(new GridBagLayout());
		pc = new GridBagConstraints();
		pc.weightx = 1;
		pc.weighty = 1;

		pc.gridx = 0;
		pc.gridy = 0;
		pc.anchor = GridBagConstraints.CENTER;

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

					// remove pane
					mainFrame.remove(pane);

					pane = new JScrollPane(riderTable);
					mainFrame.add(pane, gc);

					mainFrame.remove(searchPnl);
					searchPnl.remove(filterField);
					// Add filtering here
					filterField = RowFilterUtil.createRowFilter(riderTable);

					fc.gridx = 1;
					fc.gridy = 0;
					fc.anchor = GridBagConstraints.RELATIVE;

					searchPnl.add(filterField, fc);

					gc.gridx = 2;
					gc.gridy = 0;
					gc.fill = GridBagConstraints.BOTH;

					mainFrame.add(searchPnl, gc);

					mainFrame.repaint();

					mainFrame.pack();

					riderTableUp = true;
					driverTableUp = false;
					myRidesTableUp = false;
				}
			}
		});

		// rides button image label
		// TODO

		/*
		 * try { // TODO create file Image img = ImageIO.read(new
		 * File("src/main/resources/Rides-test.png")); ridesBtn.setIcon(new
		 * ImageIcon(img)); } catch (Exception ex) {
		 * System.out.println(ex.getStackTrace()); }
		 * 
		 * // add button
		 */
		selection.add(ridesBtn, pc);

		/**** Second Row of Panel ****/
		ImageIcon drIcn = new ImageIcon("src/main/resources/drivesIcon.png");
		Image drimage = drIcn.getImage(); // transform it
		Image drnewimg = drimage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		drIcn = new ImageIcon(drnewimg); // transform it back
		JButton drivesBtn = new JButton(drIcn);
		drivesBtn.setOpaque(false);
		drivesBtn.setContentAreaFilled(false);
		drivesBtn.setBorderPainted(false);
		drivesBtn.setFocusPainted(false);
		// JButton drivesBtn = new JButton("Driver Posts");
		pc.gridx = 0;
		pc.gridy = 1;

		/*
		 * try { //TODO create file Image img = ImageIO.read(new
		 * File("src/main/resources/poolfloat copy.png")); drivesBtn.setIcon(new
		 * ImageIcon(img)); } catch (Exception ex) {
		 * System.out.println(ex.getStackTrace()); }
		 */
		selection.add(drivesBtn, pc);

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
					mainFrame.remove(pane);

					// new pane
					pane = new JScrollPane(driverTable);
					mainFrame.add(pane, gc);

					mainFrame.remove(searchPnl);
					searchPnl.remove(filterField);
					// Add filtering here
					filterField = RowFilterUtil.createRowFilter(driverTable);

					fc.gridx = 1;
					fc.gridy = 0;
					fc.anchor = GridBagConstraints.RELATIVE;
					searchPnl.add(filterField, fc);

					gc.gridx = 2;
					gc.gridy = 0;
					gc.fill = GridBagConstraints.BOTH;
					mainFrame.add(searchPnl, gc);

					mainFrame.pack();

					driverTableUp = true;
					riderTableUp = false;
					myRidesTableUp = false;
				}
			}
		});

		/**** Third Row of Panel ****/
		ImageIcon pIcn = new ImageIcon("src/main/resources/profileIcon.png");
		Image pimage = pIcn.getImage(); // transform it
		Image pnewimg = pimage.getScaledInstance(60, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		pIcn = new ImageIcon(pnewimg); // transform it back
		JButton profileBtn = new JButton(pIcn);
		profileBtn.setOpaque(false);
		profileBtn.setContentAreaFilled(false);
		profileBtn.setBorderPainted(false);
		profileBtn.setFocusPainted(false);
		// JButton profileBtn = new JButton("View Profile");
		// profileBtn.setOpaque(true);
		pc.gridx = 0;
		pc.gridy = 2;

		profileBtn.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				ViewProfile vp = new ViewProfile(mainFrame, loggedIn);
				vp.setVisible(true);
			}
		});
		// TODO
		/*
		 * try { //TODO create file Image img = ImageIO.read(new
		 * File("src/main/resources/poolfloat copy.png")); profileBtn.setIcon(new
		 * ImageIcon(img)); } catch (Exception ex) {
		 * System.out.println(ex.getStackTrace()); }
		 */
		selection.add(profileBtn, pc);

		/**** Fourth Row of Panel ****/
		ImageIcon rideIcn = new ImageIcon("src/main/resources/myRidesIcon.png");
		Image img = rideIcn.getImage(); // transform it
		Image newImage = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		rideIcn = new ImageIcon(newImage); // transform it back
		JButton myRidesBtn = new JButton(rideIcn);
		myRidesBtn.setOpaque(false);
		myRidesBtn.setContentAreaFilled(false);
		myRidesBtn.setBorderPainted(false);
		myRidesBtn.setFocusPainted(false);
		pc.gridx = 0;
		pc.gridy = 3;

		// query for rider posts
		ArrayList<AbstractPost> myList = pDat.searchDatabase(Application.loggedIn.getUsername());

		myRidesTable = CreateMyRidesTable.createTable(myList);

		// make it so columns may not be dragged around for
		// driver or rider posts
		myRidesTable.getTableHeader().setReorderingAllowed(false);

		myRidesTable.setFillsViewportHeight(true);

		myRidesTable.setOpaque(true);

		String[] myRidesLabels = { "Type", "Poster", "Origin", "Destination", "Date" };
		myRidesModel = (DefaultTableModel) myRidesTable.getModel();

		myRidesTable.getColumn(myRidesLabels[0]).setPreferredWidth(25);
		myRidesTable.getColumn(myRidesLabels[1]).setPreferredWidth(100);
		myRidesTable.getColumn(myRidesLabels[2]).setPreferredWidth(50);
		myRidesTable.getColumn(myRidesLabels[3]).setPreferredWidth(50);
		myRidesTable.getColumn(myRidesLabels[4]).setPreferredWidth(100);

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
						ViewPostInfo vpi = new ViewPostInfo(mainFrame, name, orig, dest, date);
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
					mainFrame.remove(pane);

					pane = new JScrollPane(myRidesTable);
					mainFrame.add(pane, gc);

					mainFrame.repaint();

					mainFrame.pack();

					myRidesTableUp = true;
					riderTableUp = false;
					driverTableUp = false;
				}

			}
		});
		selection.add(myRidesBtn, pc);

		/**** Fourth Row of Panel ****/
		ImageIcon crtIcn = new ImageIcon("src/main/resources/creatPostIcon.png");
		Image image = crtIcn.getImage(); // transform it
		Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		crtIcn = new ImageIcon(newimg); // transform it back
		JButton createBtn = new JButton(crtIcn);
		createBtn.setOpaque(false);
		createBtn.setContentAreaFilled(false);
		createBtn.setBorderPainted(false);
		createBtn.setFocusPainted(false);
		pc.gridx = 0;
		pc.gridy = 4;

		createBtn.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				SelectPostType cp = new SelectPostType(mainFrame, loggedIn);
				cp.setVisible(true);
				if (CreatePost.isSucceeded()) {
//					if (CreatePost.p.getType() == "driver") {
//						String driver = ((Driver) CreatePost.p).getDriver();
//						Integer riderLimit = ((Driver) CreatePost.p).getRiderLimit();
//						String origin = CreatePost.p.getOrigin();
//						String dest = CreatePost.p.getDest();
//						SimpleDateFormat df = new SimpleDateFormat("E, MMM dd yy hh:mm");
//						String date = df.format(CreatePost.p.getDate());
//
//						Object[] row = { riderLimit, driver, origin, dest, date };
//
//						dTable.addRow(row);
//						dTable.fireTableDataChanged();
//						CreatePost.setSucceeded(false);
//					} else {
//						String poster = CreatePost.p.getPoster();
//						String origin = CreatePost.p.getOrigin();
//						String dest = CreatePost.p.getDest();
//						SimpleDateFormat df = new SimpleDateFormat("E, MMM dd yy hh:mm");
//						String date = df.format(CreatePost.p.getDate());
//
//						Object[] row = { poster, origin, dest, date };
//
//						rTable.addRow(row);
//						rTable.fireTableDataChanged();
//						CreatePost.setSucceeded(false);
//					}
				}

			}
		});

		// TODO
		/*
		 * try { //TODO create file Image img = ImageIO.read(new
		 * File("src/main/resources/poolfloat copy.png")); createBtn.setIcon(new
		 * ImageIcon(img)); } catch (Exception ex) {
		 * System.out.println(ex.getStackTrace()); }
		 */
		selection.add(createBtn, pc);

		/**** Fifth Row of Panel (ADMIN) ****/
		if (loggedIn instanceof Admin) {
			ImageIcon reportIcn = new ImageIcon("src/main/resources/reportsIcon.png");
			Image reportimage = reportIcn.getImage(); // transform it
			Image reportnewimg = reportimage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // scale it the
			// smooth
			// way
			rIcn = new ImageIcon(reportnewimg); // transform it back
			JButton reportBtn = new JButton(rIcn);
			profileBtn.setOpaque(false);
			profileBtn.setContentAreaFilled(false);
			profileBtn.setBorderPainted(false);
			profileBtn.setFocusPainted(false);
			pc.gridx = 0;
			pc.gridy = 5;

			// TODO
			/*
			 * try { //TODO create file Image img = ImageIO.read(new
			 * File("src/main/resources/poolfloat copy.png")); drivesBtn.setIcon(new
			 * ImageIcon(img)); } catch (Exception ex) {
			 * System.out.println(ex.getStackTrace()); }
			 */

			reportBtn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					// Make report
					AdminReport a = new AdminReport(mainFrame);
					a.setVisible(true);
				}

			});

			selection.add(reportBtn, pc);
		}

		selection.setBackground(new Color(255, 255, 255));

		// Adding Panel to frame
		mainFrame.add(selection, gc);

		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		UserDatabase.getInstance().write();
		PostDatabase.getInstance().write();

	}

}
