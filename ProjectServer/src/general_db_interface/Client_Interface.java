/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general_db_interface;

import dtopkg.UserDTO;
import java.sql.Date;
import java.util.ArrayList;
import objRmi.GroupChat.GroupChat;
import objRmi.Reqests.RequsetsObj;
import objRmi.client.Client;

/**
 *
 * @author Hesham
 */
public interface Client_Interface extends General_DB_Interface<UserDTO> {

    static final String CLEINT_TABLE_NAME = "Clients";
    static final String EMAIL = "mail";
    static final String STATUS = "status";
    static final String GENDER = "gender";
    static final String COUNTRY = "country";
    static final String NAME = "name";
    static final String PASSWORD = "password";
    static final String ONSTATUS = "on_status";
    static final String PICTURE = "pic";
    static final String SECURTYQUTTION = "s_q";
    static final String ANSWER = "answer";
    static final String LASTSEEN = "lastseen";

    static final String CLEINT_GROUP_TABLE_NAME = "groups";
    static final String GID = "Gid";
    static final String GNAME = "group_name";
    static final String GIMAGE = "impath";
    static final String GADMIN = "admin";

    static final String CLIENT_GROUPS = "client_Groups";
    static final String CLIENT_MAil = EMAIL;
    static final String GROUP_ID = GID;

    static final String GROUP_MEMBERS = "group_members";
    static final String GROUP_CHAT_ID = GID;
    static final String GROUP_CONTEACT_ID = EMAIL;

    static final String CLEINT_PRIVTE_TABLE_NAME = "client_privte_chat";
    static final String PID = "Pid";
    static final String SenEMAIL = "Smail";
    static final String RecEMAIL = "Rmail";

    static final String CLEINT_PRIVTE_Contact_TABLE_NAME = "client_contact_privte";
    static final String PId = "Pid";
    static final String SendEMAIL = "mail";

    static final String CLEINT_CONTACT_TABLE_NAME = "client_contact";//contact
    static final String C_MAIL = "cmail";
    static final String BLOCK_STATUS = "block_status";

    static final String CLIENT_BLOCKED_PEAPLED = "client_BLOCKED_User";//contact
    static final String B_MAIL = "cmail";

    static final String USER_LOGIN_TABLE_NAME = "user_logins";//login details,contact
    static final String LOGIN_T = "login_t";
    static final String SIGNOUT_t = "signout_t";

    static final String USER_RECIVED_REQ_TABLE_NAME = "client_recived_req";//contact
    static final String MAIL = "mail";
    static final String RMAIL = "r_mail";

    static final String USER_SEND_REQ_TABLE_NAME = "client_sended_req";//contact
    //static final String MAIL ="mail";
    static final String SEND_MAIL = "smail";
    static final String DATE = "Datee";

    boolean login(String mail, String password);

    Client getCurrentClient(String mail);

    ArrayList<Client> getOnlineList();

    void addSendReq(String mail, String person);

    void addRecReq(String mail, String person);

    ArrayList<RequsetsObj> getSendReq(String mail);

    ArrayList<RequsetsObj> getRecReq(String mail);

    String forgetPassword(String mail, String answer, String q);

    ArrayList<Client> getAvailableUsers();

    ArrayList<Client> getContactList(String mail);

    void setStatus(String mail, String st);

    Client getStatus(String mail);

    ArrayList<GroupChat> getGroupList(String mail);

    ArrayList<Client> getGroupMemeber(int id);

    void changeProfile(UserDTO client);

    void AddFriend(String clientMail, String friendMail);

    void removeFriend(String clientMail, String friendMail);

    void blockFriend(String clientMail, String friendMail);

    void unblockFriend(String clientMail, String friendMail);

    ArrayList<Client> getBlockedList(String clientMail);

    long getClientCount();

    long getOnlineClient();

    long getMale();

    long getFemale();

    long getAvgUseageTime();

    ArrayList<Client> getLoyalCustomer();

    void signIn(String email);

    void signOut(String email);

    void setOnStatus(String mail, int b);

    Client getOnStatus(String mail);
}
