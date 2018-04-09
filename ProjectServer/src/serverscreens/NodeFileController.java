/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverscreens;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import objRmi.client.Client;
import rmi.implementation.ServerImpl;

/**
 * NodeFile Controller class
 *
 * @author hagar
 */
public class NodeFileController implements Initializable {

    @FXML
    private ImageView imageViewNode;
    @FXML
    private Label labelNameNode;
    @FXML
    private Label labelStatuesNode;
    @FXML
    private Circle shapeNode;

    Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    /**
     *
     * @return imageViewNode image element in the GUI
     */
    public ImageView getImageViewNode() {
        return imageViewNode;
    }

    /**
     *
     * @return labelNameNode name label element in the GUI
     */
    public Label getLabelNameNode() {
        return labelNameNode;
    }

    /**
     *
     * @return labelStatuesNode status label element in the GUI
     */
    public Label getLabelStatuesNode() {
        return labelStatuesNode;
    }

    /**
     *
     * @return shapeNode shape element in the GUI
     */
    public Circle getShapeNode() {
        return shapeNode;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void action() {
        if (client != null) {
            System.out.println(client);
        }
    }

}
