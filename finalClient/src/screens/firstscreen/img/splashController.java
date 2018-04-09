/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.firstscreen.img;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rmi.interfaces.ServerRmi;
import screens.secondscreen.login.loginController;

/**
 *
 * @author Aya
 */
public class splashController implements Initializable {
    
    @FXML
    private ImageView imageView;
    @FXML 
    private TextField ip;
    @FXML
    private Text error;
    @FXML
    private Button startApp;
   private static Stage primaryStage= FirstScreenImg.getPrimary();
   String path =".\\src\\screens\\secondscreen\\login\\loginFXML.fxml";
    ServerRmi server;
String IPADDRESS_PATTERN = 
        "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
 
    
    /**
     *
     *this method used to to transfer from first screen to second by changing stage
     *
     */
    @FXML
    private void signIn() 
    {
         
Matcher matcher = pattern.matcher(ip.getText());
if(matcher.find())
{
        try {
            Registry reg = LocateRegistry.getRegistry(ip.getText(), 5000);
            ServerRmi oper =(ServerRmi)reg.lookup("service 1");
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("../../secondscreen/login/loginFXML.fxml"));
            loginController controller = new loginController(oper);
            loader1.setController(controller);
            loader1.load();
            Parent p = loader1.getRoot();
            primaryStage.setScene(new Scene (p));
        } catch (IOException ex) {
            error.setText(error.getText()+" "+" ip not found ");
        }  catch (NotBoundException ex) {
        Logger.getLogger(splashController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
else error.setVisible(true);
        
   
    }
    /**
     *used to initialize controller
     * 
     * 
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
