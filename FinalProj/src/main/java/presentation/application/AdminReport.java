package presentation.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import data.databaseControllers.PostDatabase;
import data.databaseControllers.ReportDatabase;
import data.databaseControllers.SurveyDatabase;
import data.databaseControllers.UserDatabase;
import data.survey.Survey;
import data.user.User;

public class AdminReport extends JDialog {
	private static final long serialVersionUID = 1L;
	JFrame frame;
	Font customFont = null;

	public AdminReport(JFrame parent) {
		// TODO Auto-generated constructor stub

		super(parent, "Admin Report", true);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("../src/main/resources/OpenSans-Bold.ttf"))
					.deriveFont(12f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			// register the font
			ge.registerFont(customFont);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}

		cs.fill = GridBagConstraints.HORIZONTAL;

		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;

		JButton requestReport = new JButton("Request Report");
		requestReport.setFont(customFont);
		requestReport.setBackground(new Color(255, 184, 25));
		requestReport.setFont(customFont);
		requestReport.setBorderPainted(false);
		requestReport.setOpaque(true);
		requestReport.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent event) {
				Application.log.log(Level.INFO, "Admin Report Requested");
				
				String message = "";
				
				//num users
				Integer numUsers = UserDatabase.getInstance().getUserData().size();
				
				message+="Number of active users: " + numUsers.toString() + "\n";
				
				//users by graduation year
				String[] theYears = { "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027" };
				HashMap<String,Integer> years = new HashMap<String,Integer>();
				
				
				
				for(User u : UserDatabase.getInstance().getUserData()) {
					//System.out.println(u.getGradYear());
					if(years.containsKey(u.getGradYear())) {
						years.put(u.getGradYear(), years.get(u.getGradYear()) + 1);
					}
					else {
						years.put(u.getGradYear(), 1);
					}
					
				}
				
				message+= "Number of users by graduation year:\n";
				for(int i = 0; i < theYears.length;i++) {
					if(years.containsKey(theYears[i])) {
						message += theYears[i] + " : " + years.get(theYears[i]).toString() + "\n";
					}
					else {
						message += theYears[i] + " : " + "0\n";
					}
					
				}
				message+="\n";
				
				//num posts
				Integer numPosts = PostDatabase.getInstance().getPostData().size();
				message+="Number of active posts: " + numPosts.toString() + "\n\n";
				//posts by month(?)
				
				//num reports
				Integer numReports = ReportDatabase.getInstance().getReportData().size();
				message+="Number of reports: " + numReports.toString() + "\n";
				
				if(SurveyDatabase.getInstance().getInstance().getUserData().isEmpty()) {
					try {
						SurveyDatabase.getInstance().getInstance().load();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				Integer numServ = SurveyDatabase.getInstance().getUserData().size();
				message+="Number of surveys: " + numServ.toString() + "\n";
				
				//surveys by rating (0-5)
				Integer[] ratings = { 0,1,2,3,4,5 };
				HashMap<Integer,Integer> rating = new HashMap<Integer,Integer>();
				
				
				
				for(Survey s : SurveyDatabase.getInstance().getUserData()) {
					if(rating.containsKey(s.getRating())) {
						rating.put(s.getRating(), rating.get(s.getRating() + 1));
					}
					else {
						rating.put(s.getRating(), 1);
					}
					
				}
				
				message+= "Number of surveys by rating:\n";
				for(int i = 0; i < ratings.length;i++) {
					if(rating.containsKey(ratings[i])) {
						message += ratings[i].toString() + " star: " + rating.get(ratings[i]).toString() + "\n";
					}
					else {
						message += ratings[i].toString() + " star: " + "0\n";
					}
					
				}
				
				
				
				JOptionPane.showMessageDialog(null, message);
				
				
			}
		});
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(customFont);
		btnCancel.setFont(customFont);
		btnCancel.setBackground(new Color(255, 184, 25));
		btnCancel.setFont(customFont);
		btnCancel.setBorderPainted(false);
		btnCancel.setOpaque(true);
		btnCancel.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				Application.log.log(Level.INFO, "Admin Report Closed");
				dispose();
			}
		});

		// make buttons show up
		JPanel bp = new JPanel();
		bp.add(requestReport);
		bp.add(btnCancel);
		bp.setBackground(new Color(28, 60, 52));

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

		panel.setBackground(new Color(255, 184, 25));

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);

	}

}
