/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.forthscreen.friends;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.util.Callback;
import objRmi.client.Client;
import rmi.interfaces.ServerRmi;
import screens.forthscreen.friends.addFriends.Node.NodeAddFriendsController;

/**
 *
 * @author Altysh
 */
public class FactoryAddFriend implements Callback<ListView<Client>, ListCell<Client>>{
String Smail;
ServerRmi server;
    FactoryAddFriend(String GetUserEmail ,ServerRmi server) {
       Smail=GetUserEmail;
       this.server = server;
    }
     @Override
            public ListCell<Client> call(ListView<Client> param) {
           return new ListCell<Client>(){
               @Override
               protected void updateItem(Client item, boolean empty) {
                   try {
                       super.updateItem(item, empty);
                       if(item == null )
                           setGraphic(null);
                       else{
                       FXMLLoader loader = new FXMLLoader(new File("./src/screens/forthscreen/friends/addFriends/Node/NodeAddFriends.fxml").toURL());
                       
                       
                     
                   //  NodeAddFriendsController cell = new NodeAddFriendsController(item);
                   NodeAddFriendsController cell = new NodeAddFriendsController(server);
                   loader.setController(cell);
                    Parent p = loader.load();
                    Image img = new Image(new ByteArrayInputStream(item.getImage()));


        
                    cell.getImageViewNode().setImage(img);
                     //NodeAddFriendsController cell = loader.getController();
//                       cell.getImageViewNode().setImage(new Image(new File("./src/screens/forthscreen/friends/Node/hhh.png").toURL().toString()));
                       
                          // System.out.println("null");
                       cell.getLabelNameNode().setText(item.getName());
                      // cell.getLabelStatuesNode().setText(item.getStatus());
                       cell.setC(item);
                       cell.setSmail(Smail);
                       
                      // item.getEmail();
                       // setStyle("-fx-control-inner-background: blue;");
                       setGraphic(p);
                       
                       }
                       
                   } catch (IOException ex) {
                       Logger.getLogger(ForthScreenContactController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   
                   
               }
            
};
    
}
}
