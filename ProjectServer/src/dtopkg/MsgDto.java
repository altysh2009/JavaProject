/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtopkg;

import java.util.Date;

/**
 *
 * @author Hagar
 */
public class MsgDto {

    private int msgId;
    private Date msgDate;
    private Boolean msgType;

    public MsgDto() {
    }

    /**
     *
     * @param msgId sets the message id
     */
    public void SetMsgId(int msgId) {
        this.msgId = msgId;

    }

    /**
     *
     * @return msgId the message id
     */
    public int GetMsgId() {
        return msgId;
    }

    /**
     *
     * @param msgDate sets the message content
     */
    public void SetMsgDate(Date msgDate) {
        this.msgDate = msgDate;

    }

    /**
     *
     * @return msgDate the message content
     */
    public Date GetMsgDate() {
        return msgDate;
    }

    /**
     *
     * @param msgType sets the message type (text or file)
     */
    public void SetMsgType(Boolean msgType) {
        this.msgType = msgType;

    }

    /**
     *
     * @return msgType the message type (text or file)
     */
    public Boolean GetMsgType() {
        return msgType;
    }

}
