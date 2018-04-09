/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objRmi.client;

import java.io.Serializable;

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
     * @param name the client name
     * @param email the client email
     * @param image the client image
     * @param onStatus the online states
     * @param status the states if busy or not
     */
    public Client(String name, String email,  byte[] image, Boolean onStatus, String status) {
        this.email = email;
        this.image = image;
        this.name = name;
        this.onStatus = onStatus;
        this.status = status;
        //this.blocked = blocked;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public  byte[] getImage() {
        return image;
    }

    public boolean isOnStatus() {
        return onStatus;
    }

    public String getStatus() {
        return status;
    }

   

    @Override
    public String toString() {
        return name+" "+email+" "+image+" "+onStatus+" "+status+" ";
    }

    

}
