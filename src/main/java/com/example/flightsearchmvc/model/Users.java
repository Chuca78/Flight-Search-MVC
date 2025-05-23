package com.example.flightsearchmvc.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of registered users for authentication purposes.
 * This class is the root element <users> used for JAXB binding with users.xml.
 */
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class Users {

    /** The list of user entries stored in the XML file. */
    @XmlElement(name = "user")
    private List<User> userList = new ArrayList<>();

    /**
     * Gets the list of users loaded from or to be written to XML.
     * @return a list of {@link User} objects
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * Sets the list of users to be stored in XML.
     * @param userList the list of {@link User} objects
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
