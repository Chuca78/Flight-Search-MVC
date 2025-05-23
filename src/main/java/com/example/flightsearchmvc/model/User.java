package com.example.flightsearchmvc.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Represents a user account for authentication purposes.
 * This class is used by JAXB to serialize and deserialize user entries in users.xml.
 */
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    @XmlElement(name = "username")
    private String username;

    @XmlElement(name = "password")
    private String password;

    /**
     * Default constructor required for JAXB marshalling/unmarshalling.
     */
    public User() {
    }

    /**
     * Returns the username of the user.
     * @return the username string
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     * @param username the desired username string
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the user.
     * @return the password string
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * @param password the desired password string
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
