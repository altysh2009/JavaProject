/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.forthscreen.chat.Node;

import chatobj.ChatManger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
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
  @FXML
          private Pane pane;
   
    ChatManger c;
   
    
    HBox getNode()
  {
   return hBoxNode;  
  }
    public void setC(ChatManger c) {
        this.c = c;
    }
    
    public Button getButton ()
    {
    return buttonAdd;
    }

    public ImageView getImageViewNode() {
        return imageViewNode;
    }

    public Label getLabelNameNode() {
        return labelNameNode;
    }

      
        @Override
    public void initialize(URL location, ResourceBundle resources) {
        //listViewContact.getItems().add(hBoxNew);
        
    }
    @FXML
    void action()
    {if(c!=null)
        System.out.println(c);
    c.show();
    }
    
}




    


