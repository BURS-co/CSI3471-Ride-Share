package presentation.application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JTable;

import data.post.DriverPost;
import data.post.Post;

public class createDriverTable {
	public static JTable createTable(ArrayList<Post> dlist) {
		String[] driverPostLabels = { "Seats", "Driver", "Origin", "Destination", "Date" };
		Object[][] driverData = new Object[dlist.size()][driverPostLabels.length];
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
					SimpleDateFormat df = new SimpleDateFormat("E, MMM dd yy hh:mm");
					String str = df.format(dlist.get(r).getDate());
					driverData[r][c] = new String(str);
				}
			}
		}

		JTable driverTable = new JTable(driverData, driverPostLabels) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		return driverTable;
	}
}
