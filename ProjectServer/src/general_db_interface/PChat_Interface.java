/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general_db_interface;

import objRmi.massage.privtechat.PrivateChat;

/**
 *
 * @author Aya
 */
public interface PChat_Interface extends General_DB_Interface<PrivateChat> {

    static final String CLEINT_PRIVTE_TABLE_NAME = "client_privte";
    static final String PID = "Pid";
    static final String SenEMAIL = "Smail";
    static final String RecEMAIL = "Rmail";

    int searchPId(String SMail, String RMail);

    boolean addPChat(String SMail, String RMail);

    PrivateChat getPChat(int Pid);

    byte[] viewImg(String RMail);

}
