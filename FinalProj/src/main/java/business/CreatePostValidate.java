package business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import data.user.User;
import presentation.application.AccountCreateDialog;
import presentation.application.Application;
import presentation.application.CreatePost;
import presentation.application.EditProfile;

public class CreatePostValidate extends CreatePost {
	public static boolean succeeded = false;

	/**
	 * @param parent
	 * @return
	 */
	public CreatePostValidate(JFrame parent) {
		super(parent, UserService.getInstance().getCurrentUser());
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name, email, phone, password, reenterPass, AccountCreateDialog
	 * @return succeeded
	 * @throws ParseException 
	 */
	public static boolean validatePostInfo(String o, String dest, String d,String m,String y,
					String h,String min,String tOd, CreatePost c) throws ParseException {

		Date today = new Date();
		SimpleDateFormat f= new SimpleDateFormat("dd MMM yyyy hh:mm a");
		
		String todayDay = f.format(today);
		Date todaysDay = f.parse(todayDay);
		
		String dayTime = d + " " + m + " " + y + " " + h + ":" + min + " " + tOd;
		Date inputDate = f.parse(dayTime);
		int comp = 0;
		comp = todaysDay.compareTo(inputDate);
		

		if (o.equals(dest)) {
			JOptionPane.showMessageDialog(c, "Origin and Destination must be different.", "Create Post",
					JOptionPane.INFORMATION_MESSAGE);
			succeeded = false;
		} else if (comp > 0) {
			JOptionPane.showMessageDialog(c,
					"Date must be today or after.",
					"Create Post", JOptionPane.INFORMATION_MESSAGE);
			succeeded = false;
		} else if (comp <= 0) {
			succeeded = true;
		}
		return succeeded;
	}

	public static boolean validatePostInfo(String o,String dest,String d,String m,String y,
			String h,String min,String tOd, Integer r, CreatePost c) throws ParseException {

		Date today = new Date();
		SimpleDateFormat f= new SimpleDateFormat("dd MMM yyyy hh:mm a");
		
		String todayDay = f.format(today);
		Date todaysDay = f.parse(todayDay);
		
		String dayTime = d + " " + m + " " + y + " " + h + ":" + min + " " + tOd;
		Date inputDate = f.parse(dayTime);
		int comp = 0;
		comp = todaysDay.compareTo(inputDate);
		
		
		if (o.equals(dest)) {
			JOptionPane.showMessageDialog(c, "Origin and Destination must be different.", "Create Post",
					JOptionPane.INFORMATION_MESSAGE);
			succeeded = false;
		} else if (comp > 0) {
			JOptionPane.showMessageDialog(c,
					"Date must be today or after.",
					"Create Post", JOptionPane.INFORMATION_MESSAGE);
			succeeded = false;
		} else if (comp <= 0 && r >= 1) {
			succeeded = true;
		}
		return succeeded;
	}
}
