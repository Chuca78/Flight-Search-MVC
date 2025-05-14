package com.example.flightsearchmvc.service;

import com.example.flightsearchmvc.model.User;
import com.example.flightsearchmvc.model.Users;
import jakarta.xml.bind.*;

import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Service to load and manage user authentication and registration via users.xml.
 */
@Service
public class UserService {

    /**
     * Validates login credentials against users.xml.
     *
     * @param username the input username
     * @param password the input password
     * @return true if valid credentials, false otherwise
     */
    public boolean validateUser(String username, String password) {
        Users users = loadUsers();
        return users.getUserList().stream()
                .anyMatch(user -> user.getUsername().equals(username)
                        && user.getPassword().equals(password));
    }

    /**
     * Checks if a username already exists.
     *
     * @param username the username to check
     * @return true if the username exists, false otherwise
     */
    public boolean userExists(String username) {
        Users users = loadUsers();
        return users.getUserList().stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    /**
     * Adds a new user to users.xml.
     *
     * @param username the new user's username
     * @param password the new user's password
     */
    public boolean addUser(String username, String password) {
        Users users = loadUsers();

        // Prevent duplicates
        boolean exists = users.getUserList().stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username));

        if (exists) {
            return false; // Do not add duplicate
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        users.getUserList().add(newUser);

        saveUsers(users);
        return true;
    }

    /**
     * Loads users from the users.xml file using JAXB.
     *
     * @return a Users object containing a list of User entries
     */
    private Users loadUsers() {
        try {
            File xmlFile = new File("src/main/resources/users.xml");
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Users) unmarshaller.unmarshal(xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
            return new Users(); // fallback: empty list
        }
    }

    /**
     * Saves the list of users to users.xml using JAXB.
     *
     * @param users the Users object to be persisted
     */
    private void saveUsers(Users users) {
        try {
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            File file = new File("src/main/resources/users.xml");
            marshaller.marshal(users, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
