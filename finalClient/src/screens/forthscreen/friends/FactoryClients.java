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
import rmi.implementation.ClientImpl;
import rmi.interfaces.ServerRmi;
import screens.forthscreen.friends.Node.NodeFileController;

/**
 *
 * @author Altysh
 */
public class FactoryClients implements Callback<ListView<Client>, ListCell<Client>> {

    String mail;
    ServerRmi rmi;
    ClientImpl client;

    FactoryClients(String GetUserEmail, ServerRmi rmi, ClientImpl client) {
        mail = GetUserEmail;
        this.rmi = rmi;
        this.client = client;
    }

    @Override
    public ListCell<Client> call(ListView<Client> param) {
        return new ListCell<Client>() {
            @Override
            protected void updateItem(Client item, boolean empty) {
                try {
                    super.updateItem(item, empty);
                    if (item == null) {
                        setGraphic(null);
                    } else {
                        FXMLLoader loader = new FXMLLoader(new File("./src/screens/forthscreen/friends/Node/NodeFile.fxml").toURL());
                        NodeFileController cell = new NodeFileController(rmi, client);
                        loader.setController(cell);
                        Parent p = loader.load();
                        Image img = new Image(new ByteArrayInputStream(item.getImage()));

                        cell.getImageViewNode().setImage(img);
                        // System.out.println("null");
                        cell.getLabelNameNode().setText(item.getName());
                        cell.getLabelStatuesNode().setText(item.getStatus());
                        cell.setC(item);
                        cell.setSMail(mail);
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
