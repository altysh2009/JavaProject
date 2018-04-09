/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbscreen;

import dao.ClientDao;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import serverscreens.ServerView;

/**
 *
 * @author Hagar
 */
public class FXMLDocumentController implements Initializable {
    
    
    
    
    @FXML
    private Label label;
    
    @FXML
    private Button connect;
    
     @FXML
    private Button close;
     
      @FXML
    private Button minimize;
      
      
    
    @FXML
    private TextField userName;
    
    @FXML
    private PasswordField password;
    @FXML
    private Label error;
    private Stage stage;
    
    public FXMLDocumentController(Stage stage)
    {
       this.stage = stage; 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         
    }


@FXML
private void check()
{
        try {
            ClientDao con = new ClientDao(userName.getText(), password.getText());
            try {
                FXMLLoader lLoader = new FXMLLoader(new File("./src/serverscreens/ServerView.fxml").toURL());
                ServerView control = new ServerView(stage);
                lLoader.setController(control);
            Parent p=lLoader.load();
            Scene s = new Scene(p);
            stage.setScene(s);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            error.setText(error.getText()+" error in input or output");
        }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            error.setText(error.getText()+" sql error check your password or database ");
            
        }
        
}


    
    
}
