/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.forthscreen.friends.groups.Node;

import screens.forthscreen.friends.Node.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import objRmi.client.Client;

/**
 * FXML Controller class
 *
 * @author Aya
 */
public class NodeFileGroupController implements Initializable {
  @FXML
  private ImageView imageViewNode;
  @FXML
  private Label labelNameNode;
  @FXML
 private HBox hBoxNode;         
   
  
  Client c;
   
    /**
     *
     * @return hbox 
     */
    HBox getNode()
  {
   return hBoxNode;  
  }

    /**
     *
     * @param c object of client
     */
    public void setC(Client c) {
        this.c = c;
    }

    /**
     *
     * @return imageview contain image
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
      
        @Override
    public void initialize(URL location, ResourceBundle resources) {
        //listViewContact.getItems().add(hBoxNew);
        
    }
    @FXML
    void action()
    {if(c!=null)
        System.out.println(c);
    }
    
}




    


