package presentation.application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JTable;

import data.post.Post;

public class createRiderTable {
	public static JTable createTable(ArrayList<Post> rlist) {
		String[] riderPostLabels = { "Poster", "Origin", "Destination", "Date" };
		Object[][] riderData = new Object[rlist.size()][riderPostLabels.length];

		for (int r = 0; r < rlist.size(); r++) {
			for (int c = 0; c < 4; c++) {
				if (c == 0) {
					riderData[r][c] = new String(rlist.get(r).getPoster());
				} else if (c == 1) {
					riderData[r][c] = new String(rlist.get(r).getOrigin());
				} else if (c == 2) {
					riderData[r][c] = new String(rlist.get(r).getDest());
				} else if (c == 3) {
					SimpleDateFormat df = new SimpleDateFormat("E, MMM dd yy hh:mm");
					String str = df.format(rlist.get(r).getDate());
					riderData[r][c] = new String(str);
				}
			}
		}

		JTable riderTable = new JTable(riderData, riderPostLabels) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		return riderTable;
	}
}
