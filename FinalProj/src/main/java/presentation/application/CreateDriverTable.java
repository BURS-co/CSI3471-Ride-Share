package presentation.application;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import data.databaseControllers.PostDatabase;
import data.post.DriverPost;
import data.post.Post;

/**
 * @author Joseph Perez, Andrew Ammentorp, Leighton Glim, Joshua Huertas, Joseph
 *         Yu
 *
 *         Class responsible for creating the driver table
 */
public class CreateDriverTable {
	private static Font customFont = null;
	
	//2D array
	static Object[][] driverData;
	
	/**
	 * Creates the table of driver posts
	 * @param dlist the list of driver posts
	 * @return JTable the table for displaying purposes
	 * @param dList
	 * @return
	 */
	public static JTable createTable(ArrayList<Post> dlist) {
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
		
		String[] driverPostLabels = { "Seats", "Driver", "Origin", "Destination", "Date" };
		driverData = new Object[dlist.size()][driverPostLabels.length];
		for (int r = 0; r < dlist.size(); r++) {
			for (int c = 0; c < 5; c++) {
				if (c == 0) {
					driverData[r][c] = new String(((DriverPost) dlist.get(r)).getRiderLimit().toString());
				} else if (c == 1) {
					driverData[r][c] = new String(((DriverPost) dlist.get(r)).getDriver());
				} else if (c == 2) {
					driverData[r][c] = new String(dlist.get(r).getOrigin());
				} else if (c == 3) {
					driverData[r][c] = new String(dlist.get(r).getDest());
				} else if (c == 4) {
					SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
					String str = df.format(dlist.get(r).getDate());
					driverData[r][c] = new String(str);
				}
			}
		}

		JTable driverTable = new JTable(new DefaultTableModel(driverData, driverPostLabels)) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			@Override 
			public Object getValueAt(int row, int col) {
				return driverData[row][col];
            }
		};
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(driverTable.getModel());
		driverTable.setRowSorter(sorter);

		List<RowSorter.SortKey> sortKeys = new ArrayList<SortKey>(25);
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		
		driverTable.setFont(customFont);

		return driverTable;
	}
}
