/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.forthscreen.friends.friendstogroup.Node;

import screens.forthscreen.friends.Node.*;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import objRmi.client.Client;
import rmi.interfaces.ServerRmi;


/**
 * FXML Controller class
 *
 * @author Aya
 */
public class NodeAddFriendsToGroupController implements Initializable {

    @FXML
    private ImageView imageViewNode;
    @FXML
    private Label labelNameNode;
    @FXML
    private Button buttonAdd;
    @FXML
    private HBox hBoxNode;
    @FXML
    private CheckBox checkBox;
    //ArrayList<Client> allClients;
    ArrayList<String> ArrayUsers;
    Client client;
    
    String SMail;
    long result;

    
    /**
     *
     * @return Hbox
     */
    HBox getNode() {
        return hBoxNode;
    }

    /**
     *
     * @param c the client obj
     */
    public void setC(Client c) {
        this.client = c;
    }

    /**
     *
     * @return add button
     */
    public Button getButton() {
        return buttonAdd;
    }

    /**
     *
     * @return imageview of image
     */
    public ImageView getImageViewNode() {
        return imageViewNode;
    }

    /**
     *
     * @return label of name
     */
    public Label getLabelNameNode() {
        return labelNameNode;
    }

    /**
     *
     * @param sMail which is sender mail
     */
    public void setSmail(String sMail) {
        SMail = sMail;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    /**
     *
     * used for find check of checkbox
     */
    @FXML
    void action() {
        System.out.println(" null");
        if (client != null) {
            if (checkBox != null) {
                System.out.println("not null");
                if (checkBox.isSelected()) {
                    ArrayUsers.add(client.getEmail());
                }
            }
        }

    }

    /**
     *
     * @param Users arraylist of strings contain mails 
     */
    public void setArray(ArrayList<String> Users) {
        ArrayUsers = Users;

    }

}

