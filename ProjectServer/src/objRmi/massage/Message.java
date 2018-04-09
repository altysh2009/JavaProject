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
     * @return sender the sender's e-mail
     */
    public String getSender() {
        return sender;
    }

    /**
     *
     * @param sender the sender mail
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     *
     */
    public Message() {

    }

    /**
     *
     * @param type the message type (text-file)
     * @param text the message content
     * @param color the message color
     * @param Font the message font
     */
    public Message(String type, String text, String color, String Font) {
        this.type = type;
        this.text = text;
        this.color = color;
        font = Font;
    }

    /**
     *
     * @return id the message id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id the message id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return date the message date
     */
    public Long getDate() {
        return date;
    }

    /**
     *
     * @param date the date it was sent
     */
    public void setDate(Long date) {
        this.date = date;
    }

    /**
     *
     * @return type the message type
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
     * @return text the message content
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text text content of the message
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return color the message color
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @param color color of the text
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @return font the message font
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
     * @return the file path
     */
    public String getPath() {
        return path;
    }

    /**
     *
     * @param path path of the file if it was type file
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * @return fileName the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     *
     * @param fileName file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
