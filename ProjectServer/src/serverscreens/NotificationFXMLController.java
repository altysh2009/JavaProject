/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverscreens;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import rmi.implementation.ServerImpl;

/**
 * FXML Controller class
 *
 * @author Hagar
 */
public class NotificationFXMLController implements Initializable {

    ServerImpl s;
    String message;

    @FXML
    private TextArea msg;
 @FXML
    private Button  send;
    /**
     * Initializes the controller class.
     *@param im  the server connection
     * 
     */
 public NotificationFXMLController(ServerImpl im)
 {
     s= im;
 }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        message = msg.getText();
    }

    @FXML
    void send() throws RemoteException {
      message = msg.getText();
        s.sendNotToAll(message);

    }

}
