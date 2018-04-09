/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import dao.ClientDao;
import dao.PrivateChatDAO;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi.implementation.ServerImpl;

/**
 *
 * @author Hesham
 */
public class checkRmi {

    ClientDao client;
    PrivateChatDAO pChatTable;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        new checkRmi();
    }

    /**
     * this class is just used to test RMI connection
     *
     */
    public checkRmi() {
        try {

            ServerImpl imp = new ServerImpl();
            Registry reg = LocateRegistry.createRegistry(5000);
            reg.rebind("service 1", imp);
            System.out.println("server is On From Main Project");
        } catch (RemoteException ex) {
            System.out.println("fe moshkela fel server");
            ex.printStackTrace();
        }
    }

}
