/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtopkg;

import java.io.Serializable;

/**
 *
 * @author Hagar
 */
//DAta about user
public class UserDTO implements Serializable{

    private String mail;
    private int statusOnOff;
    private int gender;
    private String country;
    private String userName;
    private String password;
    private String status;
    private byte[] pic; // not sure of the datatype..
    private long lastSeen;

    
    private String sequrityQues;
    private String answer;

    public UserDTO() {
    }
    public UserDTO(String ma,int on,int gender,String country,String name,String pass,String state,byte[] pic,String ques,String answer) {
    mail = ma;
    statusOnOff = on;
    this.gender= gender;
    this.country = country;
    userName = name;
    password = pass;
    status = state;
    this.pic = pic;
    sequrityQues = ques;
    this.answer=answer;
    lastSeen = new java.util.Date().getTime();
    }

    public String GetUserEmail() {
        return mail;
    }

    public void SetUserEmail(String mail) {
        this.mail = mail;
    }

    public int GetUserStatusOnOff() {
        return statusOnOff;
    }

    public void SetUserStatusOnOff(int statusOnOff) {
        this.statusOnOff = statusOnOff;
    }

    public int GetUserGender() {
        return gender;
    }

    public void SetUserGender(int gender) {
        this.gender = gender;
    }

    public String GetUserCountry() {
        return country;
    }

    public void SetUserCountry(String country) {
        this.country = country;
    }

    public String GetUserName() {
        return userName;
    }

    public void SetUserName(String userName) {
        this.userName = userName;
    }

    public String GetUserPassword() {
        return password;
    }

    public void SetUserPassword(String password) {
        this.password = password;
    }

    public String GetUserStatus() {
        return status;
    }

    public void SetUserStatus(String status) {
        this.status = status;
    }

    public byte[] GetUserPic() {
        return pic;
    }

    public void SetUserPic(byte [] pic) {
        this.pic = pic;
    }

    public long GetUserLastSeen() {
        return lastSeen;
    }

    public void SetUserLastSeen(long lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String GetUserSequrityQues() {
        return sequrityQues;
    }

    public void SetUserSequrityQues(String question) {
        this.sequrityQues = question;
    }

    public String GetUserAnswer() {
        return answer;
    }

    public void SetUserAnswer(String answer) {
        this.answer = answer;
    }

}
