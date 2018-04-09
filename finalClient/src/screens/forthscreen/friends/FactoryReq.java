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
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.util.Callback;
import objRmi.Reqests.RequsetsObj;
import rmi.interfaces.ServerRmi;
import screens.forthscreen.friends.sendFriendRequest.Node.NodeFriendsReqController;

/**
 *
 * @author Altysh
 */
public class FactoryReq implements Callback<ListView<RequsetsObj>, ListCell<RequsetsObj>> {

    String Smail;
    ObservableList<RequsetsObj> obj;
    ServerRmi serverRmi;

    FactoryReq(String GetUserEmail, ObservableList list, ServerRmi server) {
        Smail = GetUserEmail;
        obj = list;
        serverRmi = server;
    }

    @Override
    public ListCell<RequsetsObj> call(ListView<RequsetsObj> param) {
        return new ListCell<RequsetsObj>() {
            @Override
            protected void updateItem(RequsetsObj item, boolean empty) {
                try {
                    super.updateItem(item, empty);
                    if (item == null) {
                        setGraphic(null);
                    } else {
                        FXMLLoader loader = new FXMLLoader(new File("./src/screens/forthscreen/friends/sendFriendRequest/Node/NodeFriendReq.fxml").toURL());
                        //System.out.println("nooooooooo");
                        NodeFriendsReqController cell = new NodeFriendsReqController(serverRmi);
                        loader.setController(cell);
                        Parent p = loader.load();
                        //  NodeFriendsReqController cell = loader.getController();
                        Image img = new Image(new ByteArrayInputStream(item.getClient().getImage()));

                        cell.getImageViewNode().setImage(img);

                        cell.getLabelNameNode().setText(item.getClient().getName());
                        cell.setSname(Smail);
                        cell.setC(item);
                        cell.setList(obj);

                        //cell.setTime(item.getDate().toString());
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
