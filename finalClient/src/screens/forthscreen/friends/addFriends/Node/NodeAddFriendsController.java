/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.forthscreen.friends.addFriends.Node;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import objRmi.client.Client;
import rmi.interfaces.ServerRmi;


/**
 * FXML Controller class
 *.
 * @author Aya
 */
public class NodeAddFriendsController implements Initializable {
  @FXML
  private ImageView imageViewNode;
  @FXML
  private Label labelNameNode;
  @FXML
  private Button buttonAdd;
  @FXML
  private HBox hBoxNode;  
  String RMail;
  String user;
  String SMail;
  Client client;
  ServerRmi serverRmi; 


    /**
     *
     * @param rmi object of server rmi
     */

   public NodeAddFriendsController(ServerRmi rmi)
   {
       serverRmi =  rmi;
   }
   
   /**
     * 
     * @return HBox
     */

    
    HBox getNode()
  {
   return hBoxNode;  
  }
    
    /**
     *
     * @param sMail which mail of sender
     */
    public void setSmail(String sMail) {
        SMail=sMail;
    }
    
    /**
     *
     * @param c object of client
     */
    public void setC(Client c) {
        this.client = c;
    }
    
    /**
     *
     * @return button 
     */
    public Button getButton ()
    {
    return buttonAdd;
    }

    /**
     *
     * @return imageView 
     */
    public ImageView getImageViewNode() {
        return imageViewNode;
    }

    /**
     *
     * @return label of name node
     */
    public Label getLabelNameNode() {
        return labelNameNode;
    }

     /**
     * 
     * used for initialization
     */
 
        @Override
    public void initialize(URL location, ResourceBundle resources) {}
    
    /**
     * 
     * used to send request from sender mail to client mail using sendReq method
     */

    
    @FXML
    void action()
    {         
      try {
          if(client!=null)
          System.out.println(SMail+"send");
          System.out.println(client.getEmail());
          RMail=client.getEmail();
          serverRmi.sendReq(SMail, RMail);
          buttonAdd.disableProperty().setValue(true);
      } catch (RemoteException ex) {
          Logger.getLogger(NodeAddFriendsController.class.getName()).log(Level.SEVERE, null, ex);
      }
    
}
    
}




    


