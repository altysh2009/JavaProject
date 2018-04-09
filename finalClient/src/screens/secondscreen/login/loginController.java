/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.secondscreen.login;

import dtopkg.UserDTO;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import screens.firstscreen.img.FirstScreenImg;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.stage.WindowEvent;
import objRmi.client.Client;
import rmi.implementation.ClientImpl;
import rmi.interfaces.ServerRmi;

import screens.forthscreen.friends.ForthScreenContactController;
import screens.questionAnswerPass.QuestionAnswerController;
import screens.thirdscrenn.registration.registrationController;
/**
 *
 * @author Aya
 */
public class loginController implements Initializable {
    
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldpass;
    @FXML
    private Button buttonLogin;
    @FXML
   private Hyperlink linkForget;
    @FXML
    private Hyperlink linkSignup;
    @FXML
    private Label labelErrorMail;
    Connection con = null;
    UserDTO userD;
     Client clientObjct;
    private Pattern pattern;
    private Matcher matcher;
    private static String EMAIL_PATTERN;
    String stm;
    String password;
    ServerRmi serverRmi;
 
    private static Stage primaryStage = SecondScreenLogin.getPrimary();

    /**
     *
     * @param rmi which is object of server rmi
     */
    public loginController(ServerRmi rmi)
   {
       serverRmi = rmi;
   }
    
    /**
     *
     * this method used to go to signup screen by loading loader and set controller
     */
    @FXML
    private void signup() throws IOException
    {
      primaryStage= FirstScreenImg.getPrimary();   
     FXMLLoader loader = new FXMLLoader();
     loader.setLocation(getClass().getResource("../../thirdscrenn/registration/registrationFXML.fxml"));
     registrationController controller = new registrationController(serverRmi);
     loader.setController(controller);
     loader.load();
     Parent p = loader.getRoot();
     primaryStage.setScene(new Scene (p));
   
    }
    
     /**
     *
     * this method used to go to screen of forget password 
     */
    
    @FXML
     private void forgetPass() throws IOException
    {
     primaryStage= FirstScreenImg.getPrimary();   
     FXMLLoader loader = new FXMLLoader();
     loader.setLocation(getClass().getResource("../../questionAnswerPass/QuestionAnswerFXML.fxml"));
     QuestionAnswerController controller = new QuestionAnswerController(serverRmi);
     loader.setController(controller);
     loader.load();
     Parent p = loader.getRoot();
     primaryStage.setScene(new Scene (p));
    }
    
      /**
     *
     * this method used to signin by checking mail and password validation and then go to profile screen
     */
    @FXML
   private void signin() throws IOException
    {
           
      EMAIL_PATTERN =
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
      pattern = Pattern.compile(EMAIL_PATTERN);
      matcher = pattern.matcher(stm); 
       if(password.isEmpty())
       {
             labelErrorMail.setText("Enter right mail or password");
       }
       else
    {   
        if(matcher.matches()==false)
      {
        labelErrorMail.setText("Invalid mail");
      }
       else
      {
            boolean ok = serverRmi.login(stm, password);
                    if (ok==true)
                    {  
                        
                        
                        labelErrorMail.setText("");
                        primaryStage= FirstScreenImg.getPrimary();
                        ClientImpl client = new ClientImpl(serverRmi ,primaryStage ,stm);
                        serverRmi.sendClient(client, stm);
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("../../forthScreen/friends/forthScreenContactFXML.fxml"));
                        //System.out.println(loader.getLocation());
                         userD = serverRmi.getClient(stm,password);
                        ForthScreenContactController controller = new ForthScreenContactController(userD,serverRmi,client);
                        loader.setController(controller);
                        loader.load();
                       
                        //System.out.println(clientS);
                        //System.out.println(controller);
                        //controller.setClient(clientS);
                        Parent p = loader.getRoot();
                        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                try {
                                    serverRmi.singOut(stm);
                                    System.exit(0);
                                    Platform.exit();
                                } catch (RemoteException ex) {
                                    Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        //Stage stage = new Stage();
                         primaryStage.setScene(new Scene (p));
                        //stage.setScene(new Scene (p));
                    
                    
                }
                    else
                    {
                    labelErrorMail.setText("Enter right mail or password");
                    }
      }
       
    }
   }

 /**
     *
     * 
     * @param url no need
     * @param rb which is resource bundle
     * set action on login button and take values of name and password
     */
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            
            buttonLogin.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        stm = textFieldName.getText();
                        password=textFieldpass.getText();
                        signin();
                    } catch (IOException ex) {
                        Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });
         
    }    
    
}
