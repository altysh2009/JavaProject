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
public class UserDTO implements Serializable {

    private String mail;
    private int statusOnOff;
    private int gender;
    private String country;
    private String userName;
    private String password;
    private String status;
    private byte[] pic;
    private long lastSeen;

    private String sequrityQues;
    private String answer;

    public UserDTO() {
    }

    /**
     * constructor of the class
     *
     * @param mail user's Email
     * @param onStatus user's status (on - off)
     * @param gender user's gender
     * @param country user's country
     * @param name user's name
     * @param pass user's password
     * @param state user's status ( available - busy - outdoor)
     * @param pic user's image
     * @param ques user's security question
     * @param answer user's answer to the security question
     */
    public UserDTO(String mail, int onStatus, int gender, String country, String name, String pass, String state, byte[] pic, String ques, String answer) {
        this.mail = mail;
        statusOnOff = onStatus;
        this.gender = gender;
        this.country = country;
        userName = name;
        password = pass;
        status = state;
        this.pic = pic;
        sequrityQues = ques;
        this.answer = answer;
        lastSeen = new java.util.Date().getTime();
    }

    /**
     *
     * @return mail
     */
    public String GetUserEmail() {
        return mail;
    }

    /**
     *
     * @param mail the user mail
     */
    public void SetUserEmail(String mail) {
        this.mail = mail;
    }

    /**
     *
     * @return statusOnOff
     */
    public int GetUserStatusOnOff() {
        return statusOnOff;
    }

    /**
     *
     * @param statusOnOff the online status
     */
    public void SetUserStatusOnOff(int statusOnOff) {
        this.statusOnOff = statusOnOff;
    }

    /**
     *
     * @return gender
     */
    public int GetUserGender() {
        return gender;
    }

    /**
     *
     * @param gender the gender 0 or 1
     */
    public void SetUserGender(int gender) {
        this.gender = gender;
    }

    /**
     *
     * @return country
     */
    public String GetUserCountry() {
        return country;
    }

    /**
     *
     * @param country the country he live in
     */
    public void SetUserCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return userName
     */
    public String GetUserName() {
        return userName;
    }

    /**
     *
     * @param userName his screen name
     */
    public void SetUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return password the password
     */
    public String GetUserPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void SetUserPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return status
     */
    public String GetUserStatus() {
        return status;
    }

    /**
     *
     * @param status the stutes Busy or online
     */
    public void SetUserStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return pic the pic of the user
     */
    public byte[] GetUserPic() {
        return pic;
    }

    /**
     *
     * @param pic the pic of the user
     */
    public void SetUserPic(byte[] pic) {
        this.pic = pic;
    }

    /**
     *
     * @return lastSeen the last logout
     */
    public long GetUserLastSeen() {
        return lastSeen;
    }

    /**
     *
     * @param lastSeen the last logout  
     */
    public void SetUserLastSeen(long lastSeen) {
        this.lastSeen = lastSeen;
    }

    /**
     *
     * @return sequrityQues the  SequrityQues
     */
    public String GetUserSequrityQues() {
        return sequrityQues;
    }

    /**
     *
     * @param question the answer for the the  SequrityQues
     */
    public void SetUserSequrityQues(String question) {
        this.sequrityQues = question;
    }

    /**
     *
     * @return answer
     */
    public String GetUserAnswer() {
        return answer;
    }

    /**
     *
     * @param answer
     */
    public void SetUserAnswer(String answer) {
        this.answer = answer;
    }

}
