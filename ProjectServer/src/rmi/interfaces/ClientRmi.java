/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import objRmi.client.Client;
import objRmi.massage.Message;

/**
 *
 * @author Hesham edited by Hesham and ahmed
 */
public interface ClientRmi extends Remote {

    void receiveMsg(Message msg, ArrayList<String> mails) throws RemoteException;

    void receiveMsg(Message msg, long id) throws RemoteException;

    void recReqFile(String fileName, long fileSize, String toMail, String fromMail) throws RemoteException;

    void openSocket(String mail, String ip, int port, String file) throws RemoteException;

    void refused(String mail, String fileName) throws RemoteException;

    void reqReply(String reply, String mail) throws RemoteException;

    void sendOfflineNotification(String mail, ArrayList<Client> friends) throws RemoteException;

    ArrayList<Client> getBlockList(String mail) throws RemoteException;

    void receivePrivateMsg(Message msg, String myMail, String myFriendMail) throws RemoteException;

    void setOnlineStatus(String mail) throws RemoteException;

    void removeClient(ClientRmi clientRmi, String mail) throws RemoteException;

    void myGroupsCounter(long counter) throws RemoteException;

    ArrayList<Long> getMyCounterList() throws RemoteException;

    void receiveServerNot(String msg) throws RemoteException;

    void getLoginNoti(String onlineFriendName) throws RemoteException;

}
