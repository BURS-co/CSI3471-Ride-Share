package presentation.application;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import data.post.Post;

public class createRiderTable {
	private static Font customFont = null;
	
	public static JTable createTable(ArrayList<Post> rlist) {
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

		JTable riderTable = new JTable(new DefaultTableModel(riderData, riderPostLabels)) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		riderTable.setFont(customFont);

		return riderTable;
	}
}
