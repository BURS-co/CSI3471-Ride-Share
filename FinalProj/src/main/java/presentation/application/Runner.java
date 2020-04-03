package presentation.application;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import data.databaseControllers.PostDatabase;
import data.databaseControllers.UserDatabase;
import data.post.DriverPost;
import data.post.Post;

public class Runner {
	
	public static void main(String[] args) throws IOException, ParseException {
		final JFrame mainFrame = new JFrame("Bearpool");
		//Load every user
		UserDatabase.load();

		openPage openDlg = new openPage(mainFrame);
		openDlg.setVisible(true);

		// if login is successful
		if(openDlg.isSucceeded()){
			//This is the user logged in
			//Globals.loggedIn;
			//Load posts
			PostDatabase.load();
			mainFrame.setVisible(true);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			mainFrame.setBounds(0, 0, screenSize.width,screenSize.height);
			mainFrame.setSize(screenSize.width,screenSize.height);
			mainFrame.setLayout(new FlowLayout());
			
			// query for rider posts
			ArrayList<Post> rlist = PostDatabase.searchDatabase("rider");
			
			//Create table of posts
			String [] riderPostLabels = {"Poster","Airport","Date"};
			Object[][] riderData = new Object[rlist.size()][riderPostLabels.length];
			
			for(int r = 0; r < rlist.size(); r++) {
				for(int c = 0; c < 4; c++) {
					if(c == 0) {
						riderData[r][c] = new String(rlist.get(r).getPoster());
					} else if(c == 1) {
						riderData[r][c] = new String(rlist.get(r).getAirport());
					} else if(c == 2) {
						SimpleDateFormat df = new SimpleDateFormat("E, MMM dd yy hh:mm");
						String str = df.format(rlist.get(r).getDate()); 
						riderData[r][c] = new String(str);
					}
				}
			}
			
			
			// query for driver posts
			ArrayList<Post> dlist = PostDatabase.searchDatabase("driver");
			
			String [] driverPostLabels = {"Seats","Driver","Airport","Date"};
			Object[][] driverData = new Object[dlist.size()][driverPostLabels.length];
			for(int r = 0; r < dlist.size(); r++) {
				for(int c = 0; c < 4; c++) {
					if(c == 0) {
						driverData[r][c] = new String(((DriverPost)dlist.get(r)).getRiderLimit().toString());
					} else if(c == 1) {
						driverData[r][c] = new String(((DriverPost)dlist.get(r)).getDriver());
					} else if(c == 2) {
						driverData[r][c] = new String(dlist.get(r).getAirport());
					} else if(c == 3) {
						SimpleDateFormat df = new SimpleDateFormat("E, MMM dd yy hh:mm");
						String str = df.format(dlist.get(r).getDate()); 
						driverData[r][c] = new String(str);
					}
				}
			}
			
			// make it so cells cannot be edited for both rider and driver posts
			JTable riderTable = new JTable(riderData, riderPostLabels) {
				@Override
			  public boolean isCellEditable(int row, int column) {
			    return false;
	      }
		  };
			
			JTable driverTable = new JTable(driverData, driverPostLabels) {
			  @Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			// make it so columns may not be dragged around for
			// driver or rider posts
			riderTable.getTableHeader().setReorderingAllowed(false);
			driverTable.getTableHeader().setReorderingAllowed(false);
			
			mainFrame.add(new JScrollPane(riderTable));
			mainFrame.add(new JScrollPane(driverTable));
			
			riderTable.setFillsViewportHeight(true);
			driverTable.setFillsViewportHeight(true);
			
			//mainFrame.setContentPane(table);
			riderTable.setOpaque(true);
			driverTable.setOpaque(true);
			
			//TODO make table fit to screen
			
			TableColumnModel columnModel = riderTable.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(50);
			columnModel.getColumn(1).setPreferredWidth(150);
			columnModel.getColumn(2).setPreferredWidth(100);
			
			TableColumnModel columnModel1 = driverTable.getColumnModel();
			columnModel1.getColumn(0).setPreferredWidth(50);
			columnModel1.getColumn(1).setPreferredWidth(100);
			columnModel1.getColumn(2).setPreferredWidth(100);
			columnModel1.getColumn(3).setPreferredWidth(150);
			
			//somehow need to add a view profile at the bottom
			
			mainFrame.pack();
			mainFrame.setVisible(true);
			
			UserDatabase.write();
			PostDatabase.write();
			
		} else {
			Globals.log.log(Level.INFO, "Exited login screen.");
			System.exit(1);
		}
		
	}

	
}
