/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.interfaces;

import dtopkg.UserDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import objRmi.Reqests.RequsetsObj;
import objRmi.client.Client;
import objRmi.massage.Message;

/**
 *
 * @author ahmed edited by ahmed and Hesham
 */
public interface ServerRmi extends Remote {

    public void setOfflineClient(String mail) throws RemoteException;

    Client getPublicClient(String mail) throws RemoteException;

    boolean login(String mail, String password) throws RemoteException;

    UserDTO getClient(String mail, String password) throws RemoteException;

    void modifieCleint(UserDTO user) throws RemoteException;

    boolean signup(UserDTO user) throws RemoteException;

    void sendReq(String me, String friend) throws RemoteException;

    void accept(String mail, RequsetsObj r, boolean accept) throws RemoteException;

    ArrayList<RequsetsObj> getSendReq(String mail) throws RemoteException;

    ArrayList<RequsetsObj> getRecReq(String mail) throws RemoteException;

    String forgetPassword(String mail, String q, String answer) throws RemoteException;

    ArrayList<Client> getAvailableUsers() throws RemoteException;

    ArrayList<Client> getContactList(String mail) throws RemoteException;

    //ArrayList<GroupChat> getGroupList(String mail)throws RemoteException;
    //ArrayList<PrivateChat> getPrivteList(String mail)throws RemoteException;
    long stratGroupChat(String admin, ArrayList<String> mails) throws RemoteException;

    void sendMassage(Message massage, ArrayList<String> mail) throws RemoteException;

    void stratPrivteChat(Message msg, String myMail, String myFriendMail) throws RemoteException;

    void AddFriend(String clientMail, String friendMail) throws RemoteException;

    void singOut(String mail) throws RemoteException;

    void removeFriend(String clientMail, String friendMail) throws RemoteException;

    /* hn3ml check eza kanet el block hteb2a bonus walla required*/
    void blockFriend(String clientMail, String friendMail) throws RemoteException;

    void unblockFriend(String clientMail, String friendMail) throws RemoteException;

    ArrayList<Client> getBlockedList(String clientMail) throws RemoteException;

    void sendRequstFile(String fileName, long fileSize, String toMail, String fromMail) throws RemoteException;

    void acceptFile(String ip, int port, String fileName, String mail) throws RemoteException;

    void refuseFile(String mail, String fileName) throws RemoteException;

    void sendClient(ClientRmi clientRmi, String mail) throws RemoteException;

    long getGroupCounter() throws RemoteException;

    ArrayList<String> getGroupMails(long count) throws RemoteException;

    void sendMassage(Message massage, long id) throws RemoteException;

    void refuseFile(String sMail, String rMail, String fileName) throws RemoteException;

    public void sendNotToAll(String msg) throws RemoteException;

    public void sendNotification(ArrayList<Client> contactList, String onlineName) throws RemoteException;

    public List<String> getMailList() throws RemoteException;
}
