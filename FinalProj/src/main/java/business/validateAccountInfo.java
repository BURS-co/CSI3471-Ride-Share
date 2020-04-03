package business;

import java.util.Arrays;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import data.databaseControllers.UserDatabase;
import data.user.User;
import presentation.application.AccountCreateDialog;
import presentation.application.Globals;

public class validateAccountInfo extends AccountCreateDialog {
	static boolean succeeded = false;
	
	public validateAccountInfo(JFrame parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	public static boolean validateAccountInfoEntered(String name, String email, String phone, String password, String reenterPass, AccountCreateDialog a) {
		if (!(password.equals(reenterPass))) {
          JOptionPane.showMessageDialog(a,
          "Your passwords do not match! Try again.",
          "Login",
          JOptionPane.INFORMATION_MESSAGE);
          succeeded = false;
        } else if (password.length() < 8) {
            JOptionPane.showMessageDialog(a ,
                    "That password is not long enough! Try again.",
                    "Login",
                    JOptionPane.INFORMATION_MESSAGE);
            succeeded = false;
        } else if (phone.length() != 10) {
        	JOptionPane.showMessageDialog(a,
                    "Invalid phone number. Must be 10 digits.",
                    "Login",
                    JOptionPane.INFORMATION_MESSAGE);
            succeeded = false;
        } else if (!(email.toLowerCase().contains("@baylor.edu"))) {
        	JOptionPane.showMessageDialog(a,
                    "Invalid email address. Must be a Baylor email.",
                    "Login",
                    JOptionPane.INFORMATION_MESSAGE);
            succeeded = false;
        } else if(name != null) {
        	String username = name;
            int words = 0;
            if (!(" ".equals(username.substring(0, 1))) || !(" ".equals(username.substring(username.length() - 1))))
            {
                for (int i = 0; i < username.length(); i++)
                {
                    if (username.charAt(i) == ' ')
                    {
                        words++;
                    }
                }
                words = words + 1; 
            }
            if(words != 2) {
            	JOptionPane.showMessageDialog(a,
               "Invalid user name. Enter first and last name.",
               "Login",
               JOptionPane.INFORMATION_MESSAGE);
            	succeeded = false;
            } else {
          /*  	User user =  new User();
              user.setUsername(name);
        		  user.setEmail(email);
        		  user.setPhoneNumber(phone);
        		  user.setPassword(password);
        		
        		  UserDatabase.getUserData().add(user);
        		
        		
        		  JOptionPane.showMessageDialog(a,
               "Hi " + user.getUsername() + "! Welcome to Bearpool!",
               "Login",
              JOptionPane.INFORMATION_MESSAGE);
              succeeded = true;
              Globals.log.log(Level.INFO, user.getUsername()+" Login successful!");*/
            	succeeded = true;
              
            }
        }
		return succeeded;
	}
}
