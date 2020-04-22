package presentation.application;

import java.awt.Color;
import java.awt.Dimension;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import data.databaseControllers.PostDatabase;
import data.databaseControllers.UserDatabase;
import data.post.DriverPost;
import data.post.Post;
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

	public static UserDatabase userDatabase = null;
	public static PostDatabase postDatabase = null;
	public static boolean accountCreated = false;
	public static boolean postCreated = false;
	public static User loggedIn = null;;
	public static Font customFont = null;

	/**
	 * main method for the application
	 * 
	 * @param args unused
	 * @throws IOException
	 * @throws ParseException
	 * @throws FontFormatException
	 * @throws HeadlessException
	 */
	public static void main(String[] args) throws ParseException, IOException, HeadlessException, FontFormatException {

		// Load all users from database
		userDatabase = UserDatabase.getInstance();
		userDatabase.load();

		// Display login/ signup window
		openPage openDlg = new openPage(new JFrame());
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
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void createRunGUI() throws IOException, ParseException {
		final JFrame mainFrame = new JFrame("Bearpool");

		// Load posts
		postDatabase = PostDatabase.getInstance();
		postDatabase.load();

		// Setting up GridBagLayout
		mainFrame.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

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

		JPanel selection = new JPanel();
		Dimension d = selection.getPreferredSize();
		d.width = 100;
		d.height = 100;
		selection.setPreferredSize(d);
		Border innerB = BorderFactory.createEmptyBorder();
		Border outerB = BorderFactory.createEmptyBorder(0, 0, 0, 0);
		selection.setBorder(BorderFactory.createCompoundBorder(outerB, innerB));

		// weight
		gc.weightx = 1;
		gc.weighty = 1;

		// query for rider posts
		ArrayList<Post> rlist = postDatabase.searchDatabase("rider");

		// query for driver posts
		ArrayList<Post> dlist = postDatabase.searchDatabase("driver");

		// create table of posts
		JTable riderTable = createRiderTable.createTable(rlist);
		JTable driverTable = createDriverTable.createTable(dlist);

		// make it so columns may not be dragged around for
		// driver or rider posts
		riderTable.getTableHeader().setReorderingAllowed(false);
		driverTable.getTableHeader().setReorderingAllowed(false);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.BOTH;
		mainFrame.add(new JScrollPane(riderTable), gc);
		gc.gridx = 2;
		gc.gridy = 0;
		mainFrame.add(new JScrollPane(driverTable), gc);

		riderTable.setFillsViewportHeight(true);
		driverTable.setFillsViewportHeight(true);

		// mainFrame.setContentPane(table);
		riderTable.setOpaque(true);
		driverTable.setOpaque(true);

		// TODO sorting of rows

		// TODO make table fit to screen
		// DefaultTableModel rTable = new DefaultTableModel();
		// riderTable.setModel(rTable);

		// DefaultTableModel columnModel = (DefaultTableModel) riderTable.getModel();
		String[] riderPostLabels = { "Poster", "Origin", "Destination", "Date" };
		DefaultTableModel rTable = (DefaultTableModel) riderTable.getModel();

		riderTable.getColumn(riderPostLabels[0]).setPreferredWidth(100);
		riderTable.getColumn(riderPostLabels[1]).setPreferredWidth(35);
		riderTable.getColumn(riderPostLabels[2]).setPreferredWidth(50);
		riderTable.getColumn(riderPostLabels[3]).setPreferredWidth(100);

		String[] driverPostLabels = { "Seats", "Driver", "Origin", "Destination", "Date" };
		DefaultTableModel dTable = (DefaultTableModel) driverTable.getModel();
		driverTable.getColumn(driverPostLabels[0]).setPreferredWidth(30);
		driverTable.getColumn(driverPostLabels[1]).setPreferredWidth(100);
		driverTable.getColumn(driverPostLabels[2]).setPreferredWidth(35);
		driverTable.getColumn(driverPostLabels[3]).setPreferredWidth(50);
		driverTable.getColumn(driverPostLabels[4]).setPreferredWidth(100);
		TableColumnModel columnModel1 = driverTable.getColumnModel();
		columnModel1.getColumn(0).setPreferredWidth(30);
		columnModel1.getColumn(1).setPreferredWidth(100);
		columnModel1.getColumn(2).setPreferredWidth(35);
		columnModel1.getColumn(3).setPreferredWidth(50);
		columnModel1.getColumn(4).setPreferredWidth(100);

		/******* First Row **********/
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(0, 5, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.BOTH;

		/***** First Row of Panel ****/

		JButton ridesBtn = new JButton();
		selection.setLayout(new GridBagLayout());
		GridBagConstraints pc = new GridBagConstraints();
		pc.weightx = 1;
		pc.weighty = 1;

		pc.gridx = 0;
		pc.gridy = 0;
		pc.anchor = GridBagConstraints.FIRST_LINE_START;
		pc.fill = GridBagConstraints.CENTER;

		// rides button image label
		// TODO

		try {
			// TODO create file
			Image img = ImageIO.read(new File("src/main/resources/Rides-test.png"));
			ridesBtn.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
		}

		// add button
		selection.add(ridesBtn, pc);

		/**** Second Row of Panel ****/
		JButton drivesBtn = new JButton("Driver Posts");
		pc.gridx = 0;
		pc.gridy = 1;
		pc.anchor = GridBagConstraints.FIRST_LINE_START;
		pc.fill = GridBagConstraints.CENTER;
		// TODO
		/*
		 * try { //TODO create file Image img = ImageIO.read(new
		 * File("src/main/resources/poolfloat copy.png")); drivesBtn.setIcon(new
		 * ImageIcon(img)); } catch (Exception ex) {
		 * System.out.println(ex.getStackTrace()); }
		 */
		selection.add(drivesBtn, pc);

		/**** Third Row of Panel ****/
		JButton profileBtn = new JButton("View Profile");
		profileBtn.setOpaque(true);
		pc.gridx = 0;
		pc.gridy = 2;
		pc.anchor = GridBagConstraints.FIRST_LINE_START;
		pc.fill = GridBagConstraints.CENTER;
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
		ImageIcon crtIcn = new ImageIcon("src/main/resources/pencil.png");
		Image image = crtIcn.getImage(); // transform it
		Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		crtIcn = new ImageIcon(newimg); // transform it back
		JButton createBtn = new JButton(crtIcn);
		createBtn.setOpaque(false);
		createBtn.setContentAreaFilled(false);
		createBtn.setBorderPainted(false);
		createBtn.setFocusPainted(false);
		pc.gridx = 0;
		pc.gridy = 3;
		pc.anchor = GridBagConstraints.FIRST_LINE_START;
		pc.fill = GridBagConstraints.CENTER;
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
					if (CreatePost.p.getType() == "driver") {
						String driver = ((DriverPost) CreatePost.p).getDriver();
						Integer riderLimit = ((DriverPost) CreatePost.p).getRiderLimit();
						String origin = CreatePost.p.getOrigin();
						String dest = CreatePost.p.getDest();
						SimpleDateFormat df = new SimpleDateFormat("E, MMM dd yy hh:mm");
						String date = df.format(CreatePost.p.getDate());

						Object[] row = { riderLimit, driver, origin, dest, date };

						dTable.addRow(row);
						dTable.fireTableDataChanged();
					} else {
						String poster = CreatePost.p.getPoster();
						String origin = CreatePost.p.getOrigin();
						String dest = CreatePost.p.getDest();
						SimpleDateFormat df = new SimpleDateFormat("E, MMM dd yy hh:mm");
						String date = df.format(CreatePost.p.getDate());

						Object[] row = { poster, origin, dest, date };

						rTable.addRow(row);
						rTable.fireTableDataChanged();
					}
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
			JButton reportsBtn = new JButton("Reports");
			pc.gridx = 0;
			pc.gridy = 4;
			pc.anchor = GridBagConstraints.FIRST_LINE_START;
			pc.fill = GridBagConstraints.CENTER;
			// TODO
			/*
			 * try { //TODO create file Image img = ImageIO.read(new
			 * File("src/main/resources/poolfloat copy.png")); drivesBtn.setIcon(new
			 * ImageIcon(img)); } catch (Exception ex) {
			 * System.out.println(ex.getStackTrace()); }
			 */
			selection.add(reportsBtn, pc);
		}
		
		selection.setBackground(new Color(28, 60, 52));

		// Adding Panel to frame
		mainFrame.add(selection, gc);

		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		userDatabase.write();
		postDatabase.write();

	}

}
