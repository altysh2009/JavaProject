/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatobj;

import objRmi.massage.Message;

/**
 *
 * @author ahmed 
 * contain information about the message importing to show it
 */
public class MessageInfo {

    private Message massage;

    /**
     *
     * @return the message object
     */
    public Message getMassage() {
        return massage;
    }

    /**
     *
     * @param massage the message object
     */
    public void setMassage(Message massage) {
        this.massage = massage;
    }

    /**
     *
     * @return the direction of the message 
     */
    public String getDir() {
        return dir;
    }

    /**
     *
     * @param dir the direction on the screen
     */
    public void setDir(String dir) {
        this.dir = dir;
    }

    private String dir;
    private boolean isFirst = false;

    /**
     *
     * @return true if it was the first message of some client
     */
    public boolean isIsFirst() {
        return isFirst;
    }

    /**
     *
     * @param isFirst used to determine it was the first message of the user
     */
    public void setIsFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    /**
     *
     * @param m object of the message
     */
    public MessageInfo(Message m) {
        massage = m;

    }
}
