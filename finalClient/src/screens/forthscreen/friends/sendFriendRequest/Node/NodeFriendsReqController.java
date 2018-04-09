/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.forthscreen.friends.sendFriendRequest.Node;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import objRmi.Reqests.RequsetsObj;
import rmi.interfaces.ServerRmi;


/**
 * FXML Controller class
 *
 * @author Aya
 */
public class NodeFriendsReqController implements Initializable {
  @FXML
  private ImageView imageViewNode;
  @FXML
  private Label labelNameNode;
  @FXML
  private Button buttonAccept;
   @FXML
  private Button buttonIgnore;        
  @FXML
  private Pane hBoxNode;        
   String nameRecAcc;
   boolean result=false;
 private RequsetsObj c;
    ServerRmi serverRmi;  
   String s;
    ObservableList<RequsetsObj> observList;

    /**
     *
     * @param rmi which is object of server rmi to call methods using it
     */
    public NodeFriendsReqController(ServerRmi rmi)
    {
        serverRmi=rmi;
    }

    /**
     *
     * @param list of cotact list
     */
    public void setList(ObservableList<RequsetsObj> list) {
        this.observList = list;
    }
   
    /**
     *
     * @param c of client 
     */
    public void setC(RequsetsObj c) {
        this.c = c;
    }
    
    /**
     *
     * @return button
     */
    public Button getButton1 ()
    {
    return buttonAccept;
    }
    
    /**
     *
     * @return button of ignore
     */
    public Button getButton2 ()
    {
    return buttonIgnore;
    }

    /**
     *
     * @return image view of picture
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
     * @return boolean of Result
     */
    public boolean ReturnResult() {
        return result;
    }
 
             
      /**
     *
     * used for initialization
     */
        @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }
    
    /**
     *
     * used to take actions of accept using method accept
     */
    @FXML
    void action()
    {if(c!=null)
       try {
           serverRmi.accept(s, c, true);
           System.out.println(c);
           System.out.println(serverRmi.getRecReq(s)+"accept");
           nameRecAcc=c.getClient().getName();
           result=true;
          // System.out.println(serverRmi.getSendReq(s));
    } catch (RemoteException ex) {
        Logger.getLogger(NodeFriendsReqController.class.getName()).log(Level.SEVERE, null, ex);
    }
    observList.remove(c);
    }
    
    /**
     *
     * used to take action of ignore button using accept method with false parameter
     */
    @FXML
    void action2()
    { try {
        if(c!=null)
            serverRmi.accept(s,c ,false);
                System.out.println(c);
       System.out.println(serverRmi.getRecReq(s)+"result array ignore" );
       result=true;
      // System.out.println(serverRmi.getSendReq(s));
      } catch (RemoteException ex) {
          Logger.getLogger(NodeFriendsReqController.class.getName()).log(Level.SEVERE, null, ex);
      }
    observList.remove(c);
    }

    /**
     *
     * @param Smail mail of sender
     */
    public void setSname(String Smail) {
     s=Smail;   
    }
    
}




    


