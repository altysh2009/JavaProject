/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objRmi.massage;

import java.io.Serializable;
import javafx.scene.paint.Color;

/**
 *
 * @author ahmed
 */
public class Message implements Serializable {

    private int id;
    private Long date;
    private String type;
    private String text;
    private String color;
    private String font;
    private String path;
    private String fileName;
    private String sender;

    /**
     *
     * @return the email of the sender
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
     */
    public Message(){
    
}

    /**
     *
     * @param type if it was file
     * @param text the text of the message
     * @param color the color of the text
     * @param Font the style of the font
     */
    public  Message(String type,String text,String color,String Font)
{
    this.type = type;
    this.text = text;
    this.color = color;
    font = Font;
}

    /**
     *
     * @return the id of the message
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id the id of the message
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return the time of the message send
     */
    public Long getDate() {
        return date;
    }

    /**
     *
     * @param date the time of the message send
     */
    public void setDate(Long date) {
        this.date = date;
    }

    /**
     *
     * @return the type of the message
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type the type of the message
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return message text content
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text sets the message text content
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return the text color
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @param color the text color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @return the font style
     */
    public String getFont() {
        return font;
    }

    /**
     *
     * @param font font style
     */
    public void setFont(String font) {
        this.font = font;
    }

    /**
     *
     * @return the file path on the other computers
     */
    public String getPath() {
        return path;
    }

    /**
     *
     * @param path path on the other computers
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * @return the file name 
     */
    public String getFileName() {
        return fileName;
    }

    /**
     *
     * @param fileName the file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
