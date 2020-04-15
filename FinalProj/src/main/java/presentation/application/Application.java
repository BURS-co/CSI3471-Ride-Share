package presentation.application;

import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableColumnModel;

import data.databaseControllers.PostDatabase;
import data.databaseControllers.UserDatabase;
import data.post.Post;
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
	public static User loggedIn = new User();

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

		/******* First Row **********/
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(0, 5, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.BOTH;

		/***** First Row of Panel ****/
		JButton test = new JButton("Click Me!");
		selection.setLayout(new GridBagLayout());
		GridBagConstraints pc = new GridBagConstraints();
		pc.weightx = 1;
		pc.weighty = 1;

		pc.gridx = 0;
		pc.gridy = 0;
		pc.anchor = GridBagConstraints.FIRST_LINE_START;
		pc.fill = GridBagConstraints.BOTH;

		selection.add(test);

		// Adding Panel to frame
		mainFrame.add(selection, gc);

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

		TableColumnModel columnModel = riderTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(35);
		columnModel.getColumn(2).setPreferredWidth(50);
		columnModel.getColumn(3).setPreferredWidth(100);

		TableColumnModel columnModel1 = driverTable.getColumnModel();
		columnModel1.getColumn(0).setPreferredWidth(30);
		columnModel1.getColumn(1).setPreferredWidth(100);
		columnModel1.getColumn(2).setPreferredWidth(35);
		columnModel1.getColumn(3).setPreferredWidth(50);
		columnModel1.getColumn(4).setPreferredWidth(100);

		// somehow need to add a view profile at the bottom

		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		userDatabase.write();
		postDatabase.write();

	}

}
