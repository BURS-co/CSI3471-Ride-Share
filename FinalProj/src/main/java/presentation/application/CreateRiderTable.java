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
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import data.post.AbstractPost;

/**
 * @author Joseph Perez, Andrew Ammentorp, Leighton Glim
 *
 *         Class responsible for creating the rider table
 */
public class CreateRiderTable {
	private static Font customFont = null;

	// @D array
	static Object[][] riderData;

	/**
	 * @param rlist the list of rider posts to be displayed
	 * @return JTable the table encapsulating the formatted rider posts
	 */
	public static JTable createTable(ArrayList<AbstractPost> rlist) {
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

		String[] riderPostLabels = { "Poster", "Origin", "Destination", "Date" };
		riderData = new Object[rlist.size()][riderPostLabels.length];

		for (int r = 0; r < rlist.size(); r++) {
			for (int c = 0; c < 4; c++) {
				if (c == 0) {
					riderData[r][c] = new String(rlist.get(r).getPoster());
				} else if (c == 1) {
					riderData[r][c] = new String(rlist.get(r).getOrigin());
				} else if (c == 2) {
					riderData[r][c] = new String(rlist.get(r).getDest());
				} else if (c == 3) {
					SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
					String str = df.format(rlist.get(r).getDate());
					riderData[r][c] = new String(str);
				}
			}
		}

		DefaultTableModel model = new DefaultTableModel(riderData, riderPostLabels) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Object getValueAt(int row, int col) {
				return riderData[row][col];
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
		JTable riderTable = new JTable(model);

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(riderTable.getModel());
		riderTable.setRowSorter(sorter);

		List<RowSorter.SortKey> sortKeys = new ArrayList<SortKey>(25);
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);

		riderTable.setFont(customFont);

		return riderTable;
	}
}
