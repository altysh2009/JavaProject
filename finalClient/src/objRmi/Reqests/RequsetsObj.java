/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objRmi.Reqests;

import java.io.Serializable;
import java.sql.Date;
import objRmi.client.Client;

/**
 *
 * @author ahmed
 */
public class RequsetsObj implements Serializable {

    Client client;
    long date;

    /**
     * default constructor
     */
    public RequsetsObj() {

    }

    /**
     *
     * @param client object from client
     * @param date the date it was sent
     */
    public RequsetsObj(Client client, Long date) {
        this.client = client;
        this.date = date;
    }

    /**
     *
     * @return client object
     */
    public Client getClient() {
        return client;
    }

    /**
     *
     * @param client set client object
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     *
     * @return the date of the request
     */
    public long getDate() {
        return date;
    }

    /**
     *
     * @param date sets the date of request
     */
    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return client.toString() + " " + date;
    }

}
