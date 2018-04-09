/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverscreens;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import serverscreens.NodeFileController;
import java.net.MalformedURLException;
import objRmi.client.Client;

/**
 *
 * @author hagar this factory is used
 */
public class Factory implements Callback<ListView<Client>, ListCell<Client>> {

    @Override
    public ListCell<Client> call(ListView<Client> param) {
        return new ListCell<Client>() {
            @Override
            protected void updateItem(Client item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setGraphic(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(new File("./src/serverscreens/NodeFile.fxml").toURL());
                        Parent p = loader.load();
                        NodeFileController cell = loader.getController();
                        cell.getLabelNameNode().setText(item.getName());
                        cell.setClient(item);
                        setGraphic(p);
                    } catch (IOException ex) {
                        Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        };
    }
}
