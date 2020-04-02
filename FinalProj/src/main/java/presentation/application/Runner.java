package presentation.application;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import java.util.Date;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import data.databaseControllers.PostDatabase;
import data.databaseControllers.UserDatabase;

public class Runner {
	
	private JButton loginButton = null;
	private JButton createAccountButton = null;
	
	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		final JFrame mainFrame = new JFrame("Bearpool");
		//Load every user
		UserDatabase.load();

		openPage openDlg = new openPage(mainFrame);
		openDlg.setVisible(true);

		// if login successfull
		if(openDlg.isSucceeded()){
			//Load posts
			PostDatabase.load();
		//	openDlg.setVisible(false);
			//Create frame of data
			mainFrame.setVisible(true);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			mainFrame.setBounds(0, 0, screenSize.width,screenSize.height);
			mainFrame.setSize(screenSize.width,screenSize.height);
			mainFrame.setLayout(new FlowLayout());
			
			//Create table of posts
			String [] columnNames = {"Type","Poster","Airport","Date"};
			Object[][] data = new Object[PostDatabase.getPostData().size()][4];
			
			for(int r = 0; r < PostDatabase.getPostData().size(); r++) {
				for(int c = 0; c < 4; c++) {
					if(c == 0) {
						data[r][c] = new String(PostDatabase.getPostData().get(r).getType());
						//data[r][c] = new String("Rider");
					}
					else if(c == 1) {
						data[r][c] = new String(PostDatabase.getPostData().get(r).getPoster());
						//data[r][c] = new String("billybob69");
					}
					else if(c == 2) {
						data[r][c] = new String(PostDatabase.getPostData().get(r).getAirport());
						//data[r][c] = new String("Bush");
					}
					else if(c == 3) {
						SimpleDateFormat df = new SimpleDateFormat("E, MMM dd yy hh:mm");
						String str = df.format(PostDatabase.getPostData().get(r).getDate()); 
						data[r][c] = new String(str);
						//data[r][c] = new String();
					}
				}
			}
			
			JTable table = new JTable(data, columnNames);
			
			//Scrolling
			//JScrollPane scrollPane = new JScrollPane(table);
			
			mainFrame.add(new JScrollPane(table));
			
			table.setFillsViewportHeight(true);
			
			//mainFrame.setContentPane(table);
			table.setOpaque(true);
			
			//TODO make table fit to screen
			
			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(50);
			columnModel.getColumn(1).setPreferredWidth(150);
			columnModel.getColumn(2).setPreferredWidth(100);
			columnModel.getColumn(3).setPreferredWidth(200);
			
			//table.setSize(1000, 200);
	        //Display the window.
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
