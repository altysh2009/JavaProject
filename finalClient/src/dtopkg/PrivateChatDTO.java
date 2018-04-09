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

    public void SetPrivateChatId(int chatId) {
        this.privateChatId = chatId;
    }

    public int GetPrivateChatId() {
        return privateChatId;
    }

    public void SetSenderMail(String senderMail) {
        this.senderMail = senderMail;
    }

    public String GetSenderMail() {

        return senderMail;
    }

    public void SetRecieverMail(String recieverMail) {
        this.recieverMail = recieverMail;
    }

    public String GetRecieverMail() {
        return recieverMail;
    }

}
