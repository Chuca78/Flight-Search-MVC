package com.example.flightsearchmvc.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper class for a list of users, used for JAXB XML binding.
 */
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class Users {

    @XmlElement(name = "user")
    private List<User> userList = new ArrayList<>(); // List of users parsed from XML

    /**
     * Returns the list of users.
     * @return a list of User objects
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * Sets the list of users.
     * @param userList the list of User objects to set
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
