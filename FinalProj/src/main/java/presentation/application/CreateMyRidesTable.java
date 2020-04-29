package presentation.application;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.RowSorter.SortKey;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import data.post.AbstractPost;
import data.post.Driver;

public class CreateMyRidesTable {
	private static Font customFont = null;

	// @D array
	static Object[][] myRidesData;

	/**
	 * @param myRides the list of user's rides to be displayed
	 * @return JTable the table encapsulating the formatted rider posts
	 */
	public static JTable createTable(ArrayList<AbstractPost> myRides) {
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

		String[] myRidesLabels = { "Type", "Poster", "Origin", "Destination", "Date" };
		DefaultTableModel model;
		if(myRides.size() > 0) {
			myRidesData = new Object[myRides.size()][myRidesLabels.length];
	
			for (int r = 0; r < myRides.size(); r++) {
				if(myRides.get(r).getPoster() != Application.loggedIn.getUsername()) {
					for (int c = 0; c < 5; c++) {
						if (c == 0) {
							if(myRides.get(r) instanceof Driver)
								myRidesData[r][c] = new String("Driver");
							else
								myRidesData[r][c] = new String("Rider");
						} else if (c == 1) {
							myRidesData[r][c] = new String(myRides.get(r).getPoster());
						} else if (c == 2) {
							myRidesData[r][c] = new String(myRides.get(r).getOrigin());
						} else if (c == 3) {
							myRidesData[r][c] = new String(myRides.get(r).getDest());
						} else if (c == 4) {
							SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
							String str = df.format(myRides.get(r).getDate());
							myRidesData[r][c] = new String(str);
						}
					}
				}
			}
	
			model = new DefaultTableModel(myRidesData, myRidesLabels) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
	
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
	
				@Override
				public Object getValueAt(int row, int col) {
					return myRidesData[row][col];
				}
	
				@Override
				public Class<?> getColumnClass(int column) {
					Class<?> returnValue;
					if ((column >= 0) && (column < getColumnCount())) {
						returnValue = getValueAt(0, column).getClass();
					} else {
						returnValue = Object.class;
					}
	
					return returnValue;
	
				};
			};
		} else {
			myRidesData = new Object[0][myRidesLabels.length];
			model = new DefaultTableModel(myRidesData, myRidesLabels);
		}
		JTable myRidesTable = new JTable(model);

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(myRidesTable.getModel());
		myRidesTable.setRowSorter(sorter);

		List<RowSorter.SortKey> sortKeys = new ArrayList<SortKey>(25);
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);

		myRidesTable.setFont(customFont);

		return myRidesTable;
	}
}
