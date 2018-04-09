/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.forthscreen.friends;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import objRmi.client.Client;
import javafx.util.Callback;
import screens.forthscreen.friends.friendstogroup.Node.NodeAddFriendsToGroupController;

/**
 *
 * @author ahmed
 */
public class FactoryAddToGroup implements Callback<ListView<Client>, ListCell<Client>> {

    String Email;
    ArrayList<String> Users;

    /**
     *
     * @param GetUserEmail User Email
     */
    public FactoryAddToGroup(String GetUserEmail) {
        Email = GetUserEmail;
    }

    FactoryAddToGroup(String GetUserEmail, ArrayList<String> user) {
        Email = GetUserEmail;
        Users = user;
    }

    @Override
    public ListCell<Client> call(ListView<Client> param) {
        return new ListCell<Client>() {
            @Override
            protected void updateItem(Client item, boolean empty) {
                try {
                    //System.out.println(item+"haaaaaaaaay");
                    super.updateItem(item, empty);
                    if (item == null) {
                        setGraphic(null);
                    } else {
                        FXMLLoader loader = new FXMLLoader(new File("./src/screens/forthscreen/friends/friendstogroup/Node/NodeAddFriendsGroup.fxml").toURL());

                        Parent p = loader.load();
                        NodeAddFriendsToGroupController cell = loader.getController();
                        Image img = new Image(new ByteArrayInputStream(item.getImage()));

                        cell.getImageViewNode().setImage(img);
                        // System.out.println("null");
                        cell.getLabelNameNode().setText(item.getName());
                        // cell.getLabelStatuesNode().setText(item.getStatus());
                        // cell.setC(item);
                        cell.setSmail(Email);
                        cell.setC(item);
                        cell.setArray(Users);
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
