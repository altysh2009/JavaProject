/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objRmi.client;

import java.io.Serializable;
import java.sql.Blob;

/**
 *
 * @author ahmed
 */
public class Client implements Serializable {

    private final String name;
    private final String email;
    private final byte[] image;
    private final boolean onStatus;
    private final String status;
    //private final boolean blocked;

    /**
     *
     * @param name the user's name
     * @param email the user's email
     * @param image the user's image
     * @param onStatus the user's status (on - off)
     * @param status the user's status (available-busy-outdoor)
     */
    public Client(String name, String email, byte[] image, Boolean onStatus, String status) {
        this.email = email;
        this.image = image;
        this.name = name;
        this.onStatus = onStatus;
        this.status = status;
        //this.blocked = blocked;
    }

    /**
     *
     * @return name user's name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return email user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return image user's image
     */
    public byte[] getImage() {
        return image;
    }

    /**
     *
     * @return onStatus user's status
     */
    public boolean isOnStatus() {
        return onStatus;
    }

    /**
     *
     * @return status user's status
     */
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return name + " " + email + " " + image + " " + onStatus + " " + status + " ";
    }

}
