/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.questionAnswerPass;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import rmi.interfaces.ServerRmi;

import screens.firstscreen.img.FirstScreenImg;
import screens.secondscreen.login.loginController;


/**
 * FXML Controller class
 *
 * @author Aya
 */
public class QuestionAnswerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button buttonOk;
    @FXML
    private Button buttonBack;
    @FXML
    private TextField textAnswer;
    @FXML
    private TextField textMail;
    @FXML
    private ComboBox comboQuestoin;
    @FXML
    private Label labelErrorAnswer;
    @FXML
    private Label labelErrorMail;
    @FXML
    private Label labelPass;
    private Pattern pattern;
    private Matcher matcher;
    private static String EMAIL_PATTERN;
    String string;
    String Res;
    Connection con = null;

    ServerRmi serverRmi;
     private static Stage primaryStage=QuestionAnswer.getPrimary();
   

    /**
     *
     * @param rmi which is object of server rmi
     */
     public QuestionAnswerController(ServerRmi rmi)
     {
         serverRmi = rmi;
     }
     
     /**
     *
     * used to return password of client by checking mail pattern and security question and answer of this question
     */ 
    @FXML
    private void OKSend() throws IOException
    {
     String s = textMail.getText();
      EMAIL_PATTERN =
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
      pattern = Pattern.compile(EMAIL_PATTERN);
      matcher = pattern.matcher(s);  
        if(matcher.matches()==false)
      {
        labelErrorMail.setText("Invalid mail");
      }
       else
      {
       labelErrorMail.setText("");
       String stm = textAnswer.getText();
       if(stm.isEmpty())
       {
        labelErrorAnswer.setText("write your answer");
       }   
       else
       { 
        string=(String) comboQuestoin.getValue();   
        Res=serverRmi.forgetPassword(s,string,stm);
        labelPass.setText(Res);
    
       }
      }
    }
    
 /**
     *
     * used to initialize components of UI
     * @param url object of url
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
            // TODO
            comboQuestoin.getItems().addAll("what is your plan for 10 years?","what you want to do next year?","what is your name");

       /**
     *
     * used to set action on button back to back to login screen to set location of loader
     */
        buttonBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage= FirstScreenImg.getPrimary();   
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../secondscreen/login/loginFXML.fxml"));
                    loginController controller = new loginController(serverRmi);
                    loader.setController(controller);
                    loader.load();
                    Parent p = loader.getRoot();
                    primaryStage.setScene(new Scene (p));
                 } catch (MalformedURLException ex) {
                    Logger.getLogger(QuestionAnswerController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(QuestionAnswerController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                
            }
            
            
        });
       
        
           
                 }    
    
}
