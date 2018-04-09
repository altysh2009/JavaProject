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

    public void SetMsgId(int msgId) {
        this.msgId = msgId;

    }

    public int GetMsgId() {
        return msgId;
    }

    public void SetMsgDate(Date msgDate) {
        this.msgDate = msgDate;

    }

    public Date GetMsgDate() {
        return msgDate;
    }

    public void SetMsgType(Boolean msgType) {
        this.msgType = msgType;

    }

    public Boolean GetMsgType() {
        return msgType;
    }

}
