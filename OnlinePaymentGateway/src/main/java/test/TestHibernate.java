package test;

import service.UserService;

public class TestHibernate {
	
	 public static void main(String[] args) {
	        /*UserDAO userDAO = new UserDAO();

	        // Creating a new user
	        user user = new user();
	        user.setName("John Doe");
	        user.setEmail("john.doe@example.com");
	        user.setPassword("securepass123");

	        
	        userDAO.save(user);
	        System.out.println("User saved successfully!");

	        // Fetching user
	        user fetchedUser = userDAO.findById(1);
	        if (fetchedUser != null) {
	        	//userDAO.delete(fetchedUser);
	            System.out.println("User found: " + fetchedUser.getName());
	        }
	    }*/
		 
		 
		 UserService userService = new UserService();

	        // Register a user
	        userService.registerUser("John Doe", "john.doe@example.com", "securepass123");

	        // Try logging in
	        boolean isLoggedIn = userService.loginUser("john.doe@example.com", "securepass123");
	        System.out.println("Login Status: " + isLoggedIn);
	    }
}
