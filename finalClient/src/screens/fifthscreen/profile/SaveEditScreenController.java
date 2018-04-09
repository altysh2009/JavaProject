/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.fifthscreen.profile;

import dtopkg.UserDTO;

import java.io.File;
import java.io.IOException;
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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import rmi.implementation.ClientImpl;
import rmi.interfaces.ServerRmi;

import screens.beforeFifthScreen.BeforeFifthScreenController;
import screens.beforeFifthScreen.beforeFifthScreenEdit;
import screens.firstscreen.img.FirstScreenImg;
import screens.forthscreen.friends.ForthScreenContactController;
import screens.forthscreen.friends.ForthScreenContacts;
import screens.secondscreen.login.SecondScreenLogin;

/**
 *
 * @author Aya
 */
public class SaveEditScreenController implements Initializable {

    @FXML
    private Circle imageViewProfile;
    @FXML
    private TextField textFieldName;
    @FXML
    private ComboBox comboBoxCountry;
    @FXML
    private ComboBox comboBoxStatues;
    @FXML
    private ComboBox comboBoxQuestion;
    @FXML
    private TextField textFieldPassword;
    @FXML
    private Label labelEmail;
    @FXML
    private TextField textFieldAnswer;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonBack;
    @FXML
    private Label labelErrorMail;
    @FXML
    private Button buttonChangeProf;
    private Pattern pattern;
    private Matcher matcher;
    private static String EMAIL_PATTERN;
    String s1;
    String s2;
    String s3;
    String s4;
    String s5;
    String s6;
    String s7;
    UserDTO userDtoCop;
    //ClientDao c;
    //Connection con;
    private static Stage primaryStage;
    ServerRmi obj4;
    String path = ".\\src\\screens\\beforeFifthScreen\\beforeFifthScreenEditFXML.fxml";
    String path2 = ".\\src\\screens\\forthScreen\\friends\\forthScreenContactFXML.fxml";
ClientImpl cleint;

    /**
     *
     * @param userDtoCopy object from Userdto
     * @param rmi OBBJECT FROM serverRmi to call needed methods
     * @param cleint of client impl
     */
    public SaveEditScreenController(UserDTO userDtoCopy,ServerRmi rmi,ClientImpl cleint) {
        userDtoCop = userDtoCopy;
        obj4 = rmi;
        this.cleint = cleint;
    }
    
   /**
     * used to back to profile without editing 
     *
     */ 

    @FXML
    private void backProfile() throws IOException {
        try {
            primaryStage = FirstScreenImg.getPrimary();
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("../../beforeFifthScreen/beforeFifthScreenEditFXML.fxml"));
            BeforeFifthScreenController controller = new BeforeFifthScreenController(userDtoCop,obj4,cleint);
            loader1.setController(controller);
            loader1.load();
            Parent p = loader1.getRoot();
            primaryStage.setScene(new Scene(p));
        } catch (IOException ex) {
            Logger.getLogger(BeforeFifthScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * used to back to screen of contacts of this person 
     *
     */ 

    @FXML
    private void goContact() throws IOException {
        primaryStage = FirstScreenImg.getPrimary();
        getNewInfoProfile();
        System.out.println(userDtoCop.GetUserName());
        obj4.modifieCleint(userDtoCop);
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource("../../forthScreen/friends/forthScreenContactFXML.fxml"));
        ForthScreenContactController controller = new ForthScreenContactController(userDtoCop,obj4,cleint);
        loader1.setController(controller);
        loader1.load();
        Parent p = loader1.getRoot();
        primaryStage.setScene(new Scene(p));

    }
    
     /**
     * used to set all parameters of userdto with values put into ui 
     *
     */ 


    void getNewInfoProfile() {
        //we will call method from client Dao which is getUserDto and we will set labels with this info 
        System.out.println("gte new" + textFieldAnswer.getText() + " " + comboBoxQuestion.getValue());
        userDtoCop.SetUserName(textFieldName.getText());
        userDtoCop.SetUserPassword(textFieldPassword.getText());
        userDtoCop.SetUserEmail(labelEmail.getText());
        userDtoCop.SetUserStatus((String) comboBoxStatues.getValue());
        userDtoCop.SetUserCountry((String) comboBoxCountry.getValue());
        userDtoCop.SetUserSequrityQues((String) comboBoxQuestion.getValue());
        userDtoCop.SetUserAnswer(textFieldAnswer.getText());
        // userDtoCop.SetUserPic(pic);

        //imageViewProfile.getImage();
    }

     /**
     * used for initialization by setting all ui components with data of this client 
     *
     */   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        primaryStage = SaveEditScreen.getPrimary();
        comboBoxCountry.getItems().addAll("Cairo", "Alexandria", "Others");
        comboBoxStatues.getItems().addAll("Avaliable", "Busy", "Away");
        comboBoxQuestion.getItems().addAll("what is your plan for 10 years?", "what you want to do next year?");
        textFieldName.setText(userDtoCop.GetUserName());
        textFieldPassword.setText(userDtoCop.GetUserPassword());
        labelEmail.setText(userDtoCop.GetUserEmail());
        comboBoxStatues.setValue(userDtoCop.GetUserStatus());
        comboBoxQuestion.setValue(userDtoCop.GetUserSequrityQues());
        textFieldAnswer.setText(userDtoCop.GetUserAnswer());
        comboBoxCountry.setValue(userDtoCop.GetUserCountry());
        buttonChangeProf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JFileChooser fileChooser;
                fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose Profile Image..");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
                fileChooser.setFileFilter(filter);
                
                fileChooser.showDialog(null, "Choose..");
                if (fileChooser.getSelectedFile() != null) {
                    if (fileChooser.getSelectedFile().isFile() && fileChooser.getSelectedFile().canRead()) {
                        Image img = new Image(fileChooser.getSelectedFile().toURI().toString());
                        imageViewProfile.setFill(new ImagePattern(img));
                    }
                }
            }
        });

    }

}
