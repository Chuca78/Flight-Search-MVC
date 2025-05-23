package com.example.flightsearchmvc.service;

import com.example.flightsearchmvc.model.User;
import com.example.flightsearchmvc.model.Users;
import jakarta.xml.bind.*;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Service for managing user registration and authentication
 * using a local XML file (users.xml) for persistence.
 */
@Service
public class UserService {

    /** Path to the external XML file storing user credentials. */
    private static final String USER_FILE_PATH = "user-data/users.xml";

    /**
     * Validates user login credentials by checking against stored users.
     *
     * @param username the entered username
     * @param password the entered password
     * @return true if the credentials match a user in the file, false otherwise
     */
    public boolean validateUser(String username, String password) {
        Users users = loadUsers();
        return users.getUserList().stream()
                .anyMatch(user -> user.getUsername().equals(username)
                        && user.getPassword().equals(password));
    }

    /**
     * Checks whether a username already exists in users.xml.
     *
     * @param username the username to check
     * @return true if the username is already taken, false otherwise
     */
    public boolean userExists(String username) {
        Users users = loadUsers();
        return users.getUserList().stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }

    /**
     * Adds a new user to the XML file if the username is not already taken.
     *
     * @param username desired username
     * @param password desired password
     * @return true if the user was successfully added; false if username exists
     */
    public boolean addUser(String username, String password) {
        Users users = loadUsers();

        boolean exists = users.getUserList().stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username));

        if (exists) {
            return false;
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        users.getUserList().add(newUser);

        saveUsers(users);
        return true;
    }

    /**
     * Loads the list of users from the users.xml file using JAXB.
     *
     * @return Users object containing user list; empty list if file is missing or malformed
     */
    private Users loadUsers() {
        try {
            File xmlFile = new File(USER_FILE_PATH);
            if (!xmlFile.exists()) {
                return new Users(); // Return empty list if file doesn't exist
            }

            JAXBContext context = JAXBContext.newInstance(Users.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Users) unmarshaller.unmarshal(xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
            return new Users(); // Fallback to empty list on error
        }
    }

    /**
     * Saves the given user list to users.xml using JAXB.
     *
     * @param users Users object to serialize to XML
     */
    private void saveUsers(Users users) {
        try {
            File xmlFile = new File(USER_FILE_PATH);
            xmlFile.getParentFile().mkdirs(); // Ensure "user-data" folder exists

            JAXBContext context = JAXBContext.newInstance(Users.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(users, xmlFile);
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
        }
    }
}
