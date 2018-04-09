/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendfile;

import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rmi.implementation.ClientImpl;
import rmi.interfaces.ServerRmi;

/**
 *
 * @author Altysh
 */
public class NewClass extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println();
     Registry reg = LocateRegistry.getRegistry("127.0.0.1", 5000);
            ServerRmi oper =(ServerRmi)reg.lookup("service 1");
//            ClientImpl c = new ClientImpl(oper);
            ClientImpl c2 = new ClientImpl(oper,primaryStage,"sd");
//            if(msg.getText() != null)
//            oper.sendClient(c , "hesham");
    
            
            
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
