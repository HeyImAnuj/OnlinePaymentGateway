package service;

import dao.UserDAO;
import model.user;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    // Register a new user
    public boolean registerUser(String name, String email, String password, String role) {
        if (userDAO.findByEmail(email) != null) {
            System.out.println("User already exists!");
            return false;
        }
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        user newUser = new user();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);
        newUser.setRole(role);
        
        userDAO.save(newUser);
        System.out.println("User registered successfully!");
        return true;
    }

    public boolean loginUser(String email, String password) {
        user existingUser = userDAO.findByEmail(email);
        System.out.println("existingUser => "+existingUser);
        if (existingUser != null && BCrypt.checkpw(password, existingUser.getPassword())) {
            System.out.println("Login successful!");
            return true;
        }
        System.out.println("Invalid email or password.");
        return false;
    }
    
    public user getUserByEmail(String email) {
        return userDAO.findByEmail(email);  // Fetch user from DB
    }


}
