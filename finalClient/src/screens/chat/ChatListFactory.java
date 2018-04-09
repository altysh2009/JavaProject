/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.chat;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
//import screens.forthscreen.friends.ForthScreenContactController;
//import screens.forthscreen.friends.addFriends.Node.NodeAddFriendsController;
import chatobj.ChatManger;
import screens.forthscreen.chat.Node.NodeAddFriendsController;




/**
 *
 * @author Altysh
 */
public class ChatListFactory implements
        Callback<ListView<ChatManger>, ListCell<ChatManger>> {
    
public ChatListFactory()
{
  
}
    @Override
    public ListCell<ChatManger> call(ListView<ChatManger> param) {
        return new ListCell<ChatManger>() {
            @Override
            protected void updateItem(ChatManger item, boolean empty) {
                try {
                    super.updateItem(item, empty);
                    if (item == null) {
                        setGraphic(null);
                        setStyle("-fx-background-color:white");
                    } else {
                        
                        FXMLLoader loader = new FXMLLoader(new File("./src/screens/forthscreen/chat/Node/NodeAddFriends.fxml").toURL());

                         //  NodeAddFriendsController cell = new NodeAddFriendsController(item);
                  
                 
                    Parent p = loader.load();
                     NodeAddFriendsController cell = loader.getController();
                        // System.out.println("null");
                        cell.getLabelNameNode().setText(item.getChatName());
                        cell.setC(item);
                        // cell.getLabelStatuesNode().setText(item.getStatus());
                        //???  cell.setC(item);
                        // setStyle("-fx-control-inner-background: blue;");
                        setGraphic(p);

                    }

                } catch (IOException ex) {
                    Logger.getLogger(ChatListFactory.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        };

    }
}
