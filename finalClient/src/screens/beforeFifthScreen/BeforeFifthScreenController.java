/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.beforeFifthScreen;

import dtopkg.UserDTO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import objRmi.client.Client;
import rmi.implementation.ClientImpl;
import rmi.interfaces.ServerRmi;

import screens.fifthscreen.profile.SaveEditScreenController;
import screens.firstscreen.img.FirstScreenImg;
import screens.forthscreen.friends.ForthScreenContactController;
import screens.forthscreen.friends.ForthScreenContacts;
import screens.secondscreen.login.SecondScreenLogin;

/**
 * FXML Controller class
 *
 * @author Aya
 */
public class BeforeFifthScreenController implements Initializable {

    @FXML
    private Label labelName;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelPassword;
    @FXML
    private Label labelCountry;
    @FXML
    private Label labelStatues;
    @FXML
    private Label labelQuestion;
    @FXML
    private Label labelAnswer;
     @FXML
   private Circle imageViewProfile;
    @FXML
    private Button buttonEdit;
    @FXML
    private Button buttonBack;
    UserDTO userDtoCopy;
    
    private static Stage primaryStage= beforeFifthScreenEdit.getPrimary(); 
    String path =".\\src\\screens\\fifthscreen\\profile\\SaveEditFXML.fxml";
     String path1 =".\\src\\screens\\forthScreen\\friends\\forthScreenContactFXML.fxml";

   ServerRmi rmi;
ClientImpl cleint;

    /**
     *
     * @param userDto object from userdto which is contain informaion of user
     * @param rmi object of serverrmi which is contain methods used
     * @param cleint object of client implementation
     */
    public BeforeFifthScreenController(UserDTO userDto,ServerRmi rmi,ClientImpl cleint) {
        userDtoCopy=userDto;
        this.rmi = rmi;
        this.cleint = cleint;
    }
    
    /**
     *
     * used to move to edit profile screen
     */
    
    @FXML
    private void EditProfile() throws IOException
    {
         try {
            primaryStage= FirstScreenImg.getPrimary();
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("../fifthscreen/profile/SaveEditFXML.fxml"));
            SaveEditScreenController controller = new SaveEditScreenController(userDtoCopy,rmi,cleint);
            loader1.setController(controller);
            loader1.load();
            Parent p = loader1.getRoot();
            primaryStage.setScene(new Scene (p));
        } catch (IOException ex) {
            Logger.getLogger(BeforeFifthScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    /**
     *
     * used to back to main screen which is contain all buttons and contacts
     */
    @FXML
    private void backProfile() throws IOException
    {
             try {
            primaryStage= FirstScreenImg.getPrimary();
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("../forthscreen/friends/forthScreenContactFXML.fxml"));
             ForthScreenContactController controller = new ForthScreenContactController(userDtoCopy,rmi,cleint);
            loader1.setController(controller);
            loader1.load();
            Parent p = loader1.getRoot();
            primaryStage.setScene(new Scene (p));
        } catch (IOException ex) {
            Logger.getLogger(BeforeFifthScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
     /**
     *
     * used to set all UI components with data of user
     */
    void setInfoProfile()
    {
       //e will call method from client Dao which is getUserDto and we will set labels with this info 
        if(userDtoCopy==null)
            System.out.println("userdto == null");
      labelName.setText(userDtoCopy.GetUserName());
      labelEmail.setText(userDtoCopy.GetUserEmail());
      labelPassword.setText(userDtoCopy.GetUserPassword());
      labelCountry.setText(userDtoCopy.GetUserCountry());
      labelAnswer.setText(userDtoCopy.GetUserAnswer());
      labelQuestion.setText(userDtoCopy.GetUserSequrityQues());
      labelStatues.setText(userDtoCopy.GetUserStatus());
      //imageViewProfile.setImage(".png");
     }
    
     /**
     *
     * used for initialization
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setInfoProfile();
    }    
    
}
