/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objRmi.GroupChat;

import java.io.Serializable;
import java.util.ArrayList;
import objRmi.client.Client;

/**
 *
 * @author Ahmed
 */
public class GroupChat implements Serializable {

    private String name;
    private String admin;
    private int id;
    private String imgPath;
    private ArrayList<Client> clients;

    /**
     *
     * @param name the group name
     * @param admin the email of the admin(user)
     * @param id the id of the group
     * @param imgPath the image of the group
     * @param client array list contains all users in the group
     */
    public GroupChat(String name, String admin, int id, String imgPath, ArrayList<Client> client) {
        this.name = name;
        this.admin = admin;
        this.id = id;
        this.imgPath = imgPath;
        clients = client;

    }

    /**
     *
     * @return clients
     */
    public ArrayList<Client> getClients() {
        return clients;
    }

    /**
     *
     * @param clients sets the group members
     */
    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    /**
     *
     * @return name group name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name sets the group name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return admin the admin e-mail
     */
    public String getAdmin() {
        return admin;
    }

    /**
     *
     * @param admin sets the admin e-mail
     */
    public void setAdmin(String admin) {
        this.admin = admin;
    }

    /**
     *
     * @return id group id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id sets the group id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return imgPath the group image
     */
    public String getPath() {
        return imgPath;
    }

    /**
     *
     * @param path sets the group image
     */
    public void setPath(String path) {
        this.imgPath = path;
    }

}
