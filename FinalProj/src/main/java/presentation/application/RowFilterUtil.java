package presentation.application;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class RowFilterUtil {
	public static TableRowSorter<? extends TableModel> rowSorter;
	public static JTextField createRowFilter(JTable table) {
		RowSorter<? extends TableModel> rs = table.getRowSorter();
		if (rs == null) {
			table.setAutoCreateRowSorter(true);
			rs = table.getRowSorter();
		}

		 rowSorter = (rs instanceof TableRowSorter)
				? (TableRowSorter<? extends TableModel>) rs
				: null;

		if (rowSorter == null) {
			throw new RuntimeException("Cannot find appropriate rowSorter: " + rs);
		}

		final JTextField tf = new JTextField(15);
		tf.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				update(e);
			}

			public void removeUpdate(DocumentEvent e) {
				update(e);
			}

			public void changedUpdate(DocumentEvent e) {
				update(e);
			}

			private void update(DocumentEvent e) {
				String text = tf.getText();
				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
		});

		return tf;
	}
}
