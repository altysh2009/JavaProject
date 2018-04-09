/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.implementation;

import dtopkg.UserDTO;
import dao.ClientDao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import objRmi.Reqests.RequsetsObj;
import objRmi.client.Client;
import objRmi.massage.Message;
import rmi.interfaces.ClientRmi;

/**
 *
 * @author ahmed
 */
public class ServerImpl extends UnicastRemoteObject implements rmi.interfaces.ServerRmi {

    ClientDao client;
    List<ClientRmi> clientRmiList;
    List<String> mailsList;
    Map<Long, ArrayList<String>> group;
    Connection con;
    static long groupChatCounter;

    /**
     *
     * @throws RemoteException created by ahmed , edited by Hesham
     */
    public ServerImpl() throws RemoteException {

        try {
            client = ClientDao.getDeflutClientDoa();
            clientRmiList = Collections.synchronizedList(new ArrayList<>());
            mailsList = Collections.synchronizedList(new ArrayList<>());
            group = Collections.synchronizedMap(new HashMap<>());
        } catch (SQLException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param mail public client mail before add
     * @return client object with full data from database
     * @throws RemoteException created by ahmed
     */
    @Override
    public Client getPublicClient(String mail) throws RemoteException {
        return client.getCurrentClient(mail);
    }

    /**
     *
     * @param mail client's mail to login
     * @param password client's password
     * @return boolean to check if the login data exists in database
     * @throws RemoteException created by ahmed
     */
    @Override
    public boolean login(String mail, String password) throws RemoteException {
        boolean login = client.login(mail, password);
        if (login) {
            client.setOnStatus(mail, 1);
            sendNotification(client.getContactList(mail), mail);
        }
        return login;
    }

    /**
     *
     * @param mail of the client
     * @param password of the same client matching with the mail
     * @return DTO object of client
     * @throws RemoteException created by ahmed
     */
    @Override
    public UserDTO getClient(String mail, String password) throws RemoteException {
        return client.getUserDTO(mail);
    }

    /**
     *
     * @param user DTO object of the user to change or edit his data
     * @throws RemoteException created by ahmed
     */
    @Override
    public void modifieCleint(UserDTO user) throws RemoteException {
        client.changeProfile(user);
    }

    /**
     *
     * @param userMail my mail
     * @param friendMail th friend mail
     * @throws RemoteException created by ahmed
     */
    @Override
    public void sendReq(String userMail, String friendMail) throws RemoteException {
        client.addRecReq(userMail, friendMail);
        client.addSendReq(userMail, friendMail);

    }

    /**
     *
     * @param myMail current user mail
     * @param r object of RequestsObj to get other user mail
     * @param accept boolean to check if request accepted
     * @throws RemoteException created by ahmed
     */
    @Override
    public void accept(String myMail, RequsetsObj r, boolean accept) throws RemoteException {
        if (accept) {
            client.AddFriend(myMail, r.getClient().getEmail());
        }
        client.removeReq(myMail, r.getClient().getEmail());
    }

    /**
     *
     * @param mail current user mail
     * @return ArrayList of the request sent by the current client
     * @throws RemoteException created by ahmed
     */
    @Override
    public ArrayList<RequsetsObj> getSendReq(String mail) throws RemoteException {
        return client.getSendReq(mail);
    }

    /**
     *
     * @param mail current user mail
     * @return ArrayList of the requests sent to the current client
     * @throws RemoteException created by ahmed
     */
    @Override
    public ArrayList<RequsetsObj> getRecReq(String mail) throws RemoteException {
        return client.getRecReq(mail);
    }

    /**
     *
     * @param mail mail of user wants to get his password back
     * @param q String takes the Security Question to get password
     * @param answer String takes answer of the security question
     * @return String password of the user if the security question and answer
     * matched
     * @throws RemoteException created by ahmed
     */
    @Override
    public String forgetPassword(String mail, String q, String answer) throws RemoteException {
        return client.forgetPassword(mail, answer, q);
    }

    /**
     *
     * @return ArrayList of
     * @throws RemoteException created by ahmed
     */
    @Override
    public ArrayList<Client> getAvailableUsers() throws RemoteException {
        return client.getAvailableUsers();
    }

    /**
     *
     * @param mail String contains user's mail to get his contact list
     * @return ArrayList of Client objects
     * @throws RemoteException created by hesham
     */
    @Override
    public ArrayList<Client> getContactList(String mail) throws RemoteException {
        return client.getContactList(mail);
    }

    /**
     *
     * @param <T> type of the list
     * @param list1 the first list
     * @param list2 the other list
     * @return true if they are both equal even if they aren't in the same order
     */
    public static <T> boolean listEqualsIgnoreOrder(List<T> list1, List<T> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }

    /**
     *
     * @param admin String contains creator of group's mail
     * @param mails ArrayList of client mail's to be added to the group chat
     * @return long contains group chat id
     * @throws RemoteException created by hesham
     */

    @Override
    public long stratGroupChat(String admin, ArrayList<String> mails) throws RemoteException {
        // long id;
        Set<Long> s = group.keySet();
        for (Long l : s) {
            if (listEqualsIgnoreOrder(group.get(l), mails)) {
                return l.longValue();
            }
        }
        groupChatCounter++;
        group.put(Long.valueOf(groupChatCounter), mails);
        for (int i = 0; i < mailsList.size(); i++) {
            for (int j = 0; j < mails.size(); j++) {
                if (mails.get(j).equals(mailsList.get(i))) {
                    System.out.println(mailsList.get(i) + " : ");
                    clientRmiList.get(i).myGroupsCounter(groupChatCounter);
                }
            }
        }
        System.out.println(groupChatCounter);
        return groupChatCounter;
    }

    /**
     *
     * @param groupId long refers to the group's id
     * @return ArrayList of client mails in this group's id
     * @throws RemoteException created by hesham
     */
    @Override
    public ArrayList<String> getGroupMails(long groupId) throws RemoteException {
        ArrayList<String> mails = new ArrayList<>();
        if (group.containsKey(Long.valueOf(groupId))) {
            mails = group.get(Long.valueOf(groupId));
            System.out.println("Group Id = " + groupId);
        } else {
            System.out.println("This key not found in the hashMap");
        }

        return mails;
    }

    /**
     *
     * @return the number of all groups created by users
     * @throws RemoteException created by hesham
     */
    @Override
    public long getGroupCounter() throws RemoteException {
        return groupChatCounter;
    }

    /**
     *
     * @param massage object contains full data of the message such as color or
     * text
     * @param mail ArrayList of String contains mails of clients I want to send
     * message to
     * @throws RemoteException created by hesham
     */
    @Override
    public void sendMassage(Message massage, ArrayList<String> mail) throws RemoteException {

        for (int i = 0; i < mail.size(); i++) {
            for (int j = 0; j < mailsList.size(); j++) {
                if ((mail.get(i)).equals(mailsList.get(j))) {
                    System.out.print(mail.get(i) + " : ");
                    System.out.println(massage.getText());
                    clientRmiList.get(i).receiveMsg(massage, mail);
                }
            }
        }
        System.out.println("Send Message From ServerImpl");
    }

    /**
     *
     * @param clientMail current client mail
     * @param friendMail mail of the friend I want to add
     * @throws RemoteException created by ahmed
     */
    @Override
    public void AddFriend(String clientMail, String friendMail) throws RemoteException {
        client.addSendReq(clientMail, friendMail);
        client.addRecReq(clientMail, friendMail);
    }

    /**
     *
     * @param mail current client mail
     * @throws RemoteException created by ahmed
     */
    @Override
    public void singOut(String mail) throws RemoteException {
        int index = mailsList.indexOf(mail);
        if (index > -1) {
            mailsList.remove(index);
            clientRmiList.remove(index);
            client.setOnStatus(mail, 0);
            ArrayList<Client> contactList = client.getContactList(mail);
            for (int i = 0; i < contactList.size(); i++) {
                for (int j = 0; j < mailsList.size(); j++) {
                    if (contactList.get(i).getEmail().equals(mailsList.get(j))) {
                        clientRmiList.get(j).sendOfflineNotification(client.getCurrentClient(mail).getName(), contactList);
                    }
                }
            }

        }
    }

    /**
     *
     * @param clientMail current client mail
     * @param friendMail other friend mail
     * @throws RemoteException created by ahmed
     */
    @Override
    public void removeFriend(String clientMail, String friendMail) throws RemoteException {
        client.removeFriend(clientMail, friendMail);
    }

    /**
     *
     * @param clientMail current client mail
     * @param friendMail other friend mail
     * @throws RemoteException created by ahmed
     */
    @Override
    public void blockFriend(String clientMail, String friendMail) throws RemoteException {
        client.blockFriend(clientMail, friendMail);
    }

    /**
     *
     * @param clientMail current client mail
     * @param friendMail other friend mail
     * @throws RemoteException created by ahmed
     */
    @Override
    public void unblockFriend(String clientMail, String friendMail) throws RemoteException {
        client.unblockFriend(clientMail, friendMail);
    }

    /**
     *
     * @param clientMail current client mail
     * @return ArrayList of blocked clients
     * @throws RemoteException created by ahmed
     */
    @Override
    public ArrayList<Client> getBlockedList(String clientMail) throws RemoteException {
        return client.getBlockedList(clientMail);
    }

    /**
     *
     * @param user object of new user that signed up to be inserted into
     * database
     * @return boolean to check if the function executed correctively or not
     * @throws RemoteException created by ahmed
     */
    @Override
    public boolean signup(UserDTO user) throws RemoteException {
        return client.InsertRow(user);
    }

    /**
     *
     * @param clientRmi object of the new client's RMI
     * @param mail new user mail
     * @throws RemoteException created by hesham
     */
    @Override
    public void sendClient(ClientRmi clientRmi, String mail) throws RemoteException {
        clientRmiList.add(clientRmi);
        mailsList.add(mail);
        System.out.println(mail + " is online");
        // search by mail to get client's rmi object \\
        for (int i = 0; i < mailsList.size(); i++) {
            if (mailsList.get(i).equals(mail)) {
                // getting the client rmi object to change his status \\
                clientRmiList.get(i).setOnlineStatus(mail);
                client.setOnStatus(mail, 1);
            }
        }
    }

    /**
     *
     * @param mail of the current client to set him offline in database and
     * friend's
     * @throws RemoteException created by hesham
     */
    @Override
    public void setOfflineClient(String mail) throws RemoteException {

        System.out.println(mail + " is offline");
        // search by mail to get client's rmi object \\
        for (int i = 0; i < mailsList.size(); i++) {
            if (mailsList.get(i).equals(mail)) {
                // getting the client rmi object to change his status \\
                clientRmiList.get(i).setOnlineStatus(mail);
                client.setOnStatus(mail, 0);
            }
        }
        System.out.println(mail + " is offline");
    }

    /**
     *
     * @param fileName file to be sent name
     * @param fileSize size of the file to be sent
     * @param toMail friend mail
     * @param fromMail current client mail
     * @throws RemoteException created by hesham
     */
    @Override
    public void sendRequstFile(String fileName, long fileSize, String toMail, String fromMail) throws RemoteException {
        System.out.println("data in sendRequestFile (Server)\nfile name : " + fileName + "\nfile size : " + fileSize
                + "\nto-Mail : " + toMail + "\n Mail-From : " + fromMail);
        for (int i = 0; i < mailsList.size(); i++) {
            if (mailsList.get(i).equals(toMail)) {
                clientRmiList.get(i).recReqFile(fileName, fileSize, toMail, fromMail);
            }
        }
    }

    /**
     *
     * @param ip of the receiver who created server socket
     * @param port of the server socket created in the receiver side
     * @param fileName name of file to be sent
     * @param mail String contains mail of the sender
     * @throws RemoteException created by hesham
     */
    @Override
    public void acceptFile(String ip, int port, String fileName, String mail) throws RemoteException {
        System.out.println("From acceptFile ServerImpl ip = " + ip + " and port = " + port);
        for (int i = 0; i < mailsList.size(); i++) {
            if (mailsList.get(i).equals(mail)) {
                clientRmiList.get(i).openSocket(mail, ip, port, fileName);
            }
        }
    }

    /**
     *
     * @param sMail sender mail
     * @param rMail receiver mail
     * @param fileName name of file refused
     * @throws RemoteException created by hesham
     */
    @Override
    public void refuseFile(String sMail, String rMail, String fileName) throws RemoteException {
        System.out.println("Refused will be sent from server");
        for (int i = 0; i < mailsList.size(); i++) {
            if (mailsList.get(i).equals(sMail)) {
                clientRmiList.get(i).refused(sMail, fileName);
            }
            if (mailsList.get(i).equals(rMail)) {
                clientRmiList.get(i).refused(rMail, fileName);
            }
        }

    }

    /**
     *
     * @param msg object of message contains data of the message such as text
     * and font
     * @param myMail current client mail
     * @param myFriendMail other friend mail
     * @throws RemoteException created by hesham
     */
    @Override
    public void stratPrivteChat(Message msg, String myMail, String myFriendMail) throws RemoteException {
        for (int i = 0; i < mailsList.size(); i++) {
            if (mailsList.get(i).equals(myMail)) {
                clientRmiList.get(i).receivePrivateMsg(msg, myMail, myFriendMail);
            }
        }
    }

    /**
     *
     * @return List of the all online mails
     * @throws RemoteException created by hesham
     */
    @Override
    public List<String> getMailList() throws RemoteException {
        return mailsList;
    }

    /**
     *
     * @param contactList ArrayList of the current client's friend list
     * @param onlineName name of current client
     * @throws RemoteException created by hesham
     */
    @Override
    public void sendNotification(ArrayList<Client> contactList, String onlineName) throws RemoteException {
        for (int i = 0; i < contactList.size(); i++) {
            for (int j = 0; j < mailsList.size(); j++) {
                if (contactList.get(i).getEmail().equals(mailsList.get(j))) {
                    clientRmiList.get(j).getLoginNoti(onlineName);
                }
            }
        }
    }

    /**
     *
     * @param msg message to be sent from server to all clients
     * @throws RemoteException if not done
     */
    @Override
    public void sendNotToAll(String msg) throws RemoteException {

        for (int i = 0; i < clientRmiList.size(); i++) {
            clientRmiList.get(i).receiveServerNot(msg);
        }

    }

    /**
     *
     * @param massage object contains message's data such as color or text
     * @param id of the group I want to send message to it's contacts
     * @throws RemoteException created by ahmed
     */
    @Override
    public void sendMassage(Message massage, long id) throws RemoteException {
        ArrayList<String> r = group.get(Long.valueOf(id));
        if (r != null) {
            for (String s : r) {
                for (int i = 0; i < mailsList.size(); i++) {
                    if (mailsList.get(i).equals(s)) {
                        clientRmiList.get(i).receiveMsg(massage, id);
                    }
                }
            }
        }

    }

    /**
     *
     * @param mail the mail of the sender
     * @param fileName file name and path
     * @throws RemoteException if could connect to the client
     */
    @Override
    public void refuseFile(String mail, String fileName) throws RemoteException {
        //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
