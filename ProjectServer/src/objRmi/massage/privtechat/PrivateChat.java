/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objRmi.massage.privtechat;

import java.io.Serializable;

/**
 *
 * @author ahmed
 */
public class PrivateChat implements Serializable {

    String sender;
    String reciver;
    int id;

    /**
     *
     * @return the sender E-mail
     */
    public String getSender() {
        return sender;
    }

    /**
     *
     * @param sender the email of the sender
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     *
     * @return reciver the reciver's E-mail
     */
    public String getReciver() {
        return reciver;
    }

    /**
     *
     * @param reciver the mail of the reciver
     */
    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    /**
     *
     * @return id the message ids
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id the chat id
     */
    public void setId(int id) {
        this.id = id;
    }

}
