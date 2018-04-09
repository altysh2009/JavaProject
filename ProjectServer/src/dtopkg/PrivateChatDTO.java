/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtopkg;

/**
 *
 * @author Hagar
 */
public class PrivateChatDTO {

    private int privateChatId;
    private String senderMail;
    private String recieverMail;

    public PrivateChatDTO() {
    }

    /**
     *
     * @param chatId sets the private chat room id
     */
    public void SetPrivateChatId(int chatId) {
        this.privateChatId = chatId;
    }

    /**
     *
     * @return privateChatId the private chat room id
     */
    public int GetPrivateChatId() {
        return privateChatId;
    }

    /**
     *
     * @param senderMail sets the mail of the sender
     */
    public void SetSenderMail(String senderMail) {
        this.senderMail = senderMail;
    }

    /**
     *
     * @return senderMail the mail of the sender
     */
    public String GetSenderMail() {

        return senderMail;
    }

    /**
     *
     * @param recieverMail sets the mail of the receiver
     */
    public void SetRecieverMail(String recieverMail) {
        this.recieverMail = recieverMail;
    }

    /**
     *
     * @return recieverMail the mail of the receiver
     */
    public String GetRecieverMail() {
        return recieverMail;
    }

}
