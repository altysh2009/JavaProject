/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.chat;
import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import rmi.implementation.ClientImpl;
import rmi.interfaces.ServerRmi;

/**
 *
 * @author Aya
 */
public class chat extends Application {
     private FXMLLoader loader;
    private ChatController controller;
    private Parent root;
    private static Stage primarySt;
    @Override
    public void start(Stage stage)  {
         try {
             //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
             primarySt=stage;
             Registry reg = LocateRegistry.getRegistry("172.16.5.193", 5000);
             ServerRmi oper =(ServerRmi)reg.lookup("service 1");
             ClientImpl c2 = new ClientImpl(oper , stage,"asd");
             oper.sendClient(c2 , "ahmed");
             
             loader = new FXMLLoader(getClass().getResource("chat.fxml"));
             
             
             stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                 @Override
                 public void handle(WindowEvent event) {
                     try {
                         oper.singOut("ahmed");
                         System.exit(0);
                     } catch (RemoteException ex) {
                         Logger.getLogger(chat.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             });
              ArrayList<String> a = new ArrayList<>();
             a.add("ahmed");
             a.add("hesham");
            long id =  oper.stratGroupChat("ahmed", a);
             ChatController chat = new ChatController(id);
             loader.setController(chat);
            
             //controller = new ChatController();
             root = loader.load();
             chat.setServer(oper);
             chat.setUserName("ahmed");
             c2.setChat(chat);
             
             //controller.setId();
             Scene scene = new Scene(root);
             
             stage.setScene(scene);
             stage.show();
         } catch (RemoteException ex) {
             
             Logger.getLogger(chat.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(chat.class.getName()).log(Level.SEVERE, null, ex);
         } catch (NotBoundException ex) {
             Logger.getLogger(chat.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    /**
     *
     * @return the stage
     */
    public static Stage getPrimary ()
    {
     return (primarySt);
    }
    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}

/**
 *
 * @author Aya
 */


