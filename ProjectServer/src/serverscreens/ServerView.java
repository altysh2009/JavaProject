/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverscreens;


import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rmi.implementation.ServerImpl;

/**
 *
 * @author Hagar
 */
public class ServerView implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Button online;
    @FXML
    private Button message;
    @FXML
    private Button search;

    @FXML
    private Button start;

    @FXML
    private Button stop;

    @FXML
    private Button allUsers;
    @FXML
    private Button analysis;



    Node node;

   

    private double xOffset, yOffset;
    private static Registry reg;
    private boolean checkServer;
    private Stage stage;
    ServerImpl imp;
public ServerView(Stage stage)
{
    this.stage = stage;
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image onlineIcon = new Image(getClass().getResourceAsStream("not.png"));
        online.setGraphic(new ImageView(onlineIcon));

        Image analysisIcon = new Image(getClass().getResourceAsStream("analysisIcon.png"));
        analysis.setGraphic(new ImageView(analysisIcon));

        Image allUsersIcon = new Image(getClass().getResourceAsStream("allUsers.png"));
        allUsers.setGraphic(new ImageView(allUsersIcon));

        Image searchIcon = new Image(getClass().getResourceAsStream("srch.png"));
        search.setGraphic(new ImageView(searchIcon));

        Image notificationIcon = new Image(getClass().getResourceAsStream("ntf.png"));
        message.setGraphic(new ImageView(notificationIcon));

stage.setOnCloseRequest((event) -> {
    System.exit(0);
});
        

    }

    @FXML
    private void loadOnlineUsersScreen() throws IOException {

        node = (Node) FXMLLoader.load(getClass().getResource("OnlinePane.fxml"));
        pane.getChildren().setAll(node);

    }

    @FXML
    private void loadAnalysisScreen() throws IOException {

        node = (Node) FXMLLoader.load(getClass().getResource("ChartsPane.fxml"));
        pane.getChildren().setAll(node);

    }

    @FXML
    private void loadAllUsersScreen() throws IOException {

        node = (Node) FXMLLoader.load(getClass().getResource("AllUsersPane.fxml"));
        pane.getChildren().setAll(node);

    }

    @FXML
    private void loadSearchScreen() throws IOException {

        node = (Node) FXMLLoader.load(getClass().getResource("SearchFXML.fxml"));
        pane.getChildren().setAll(node);

    }

    @FXML
    private void loadNotificationsScreen() throws IOException {
        if(checkServer)
        {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("NotificationFXML.fxml"));
        NotificationFXMLController controller = new NotificationFXMLController(imp);
        loader.setController(controller);
       Node node = loader.load();
        pane.getChildren().setAll(node);
        }
      

    }


    /**
     * use locate registry to start the server
     */
    public void startServer() {
        try {
            System.setProperty("java.rmi.server.hostname",InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerView.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            if (!checkServer) {
                 imp = new ServerImpl();
                // ServerImpl stub = (ServerImpl) UnicastRemoteObject.exportObject(imp, 0);
                reg = LocateRegistry.createRegistry(5000);
                reg.rebind("service 1", imp);
                System.out.println("server is On From Main Project");
                checkServer = true;
            } else {
                System.out.println("Server is already on");
            }
        } catch (RemoteException ex) {
            System.out.println("fe moshkela fel server");
            ex.printStackTrace();
        }

    }

    /**
     * stop the server
     */
    public void stopServer() {

        try {

            if (checkServer) {
                reg.unbind("service 1");
                UnicastRemoteObject.unexportObject(reg, true);
                System.out.println("from stop server try");
                checkServer = false;
            } else {
                System.out.println("Server is already stopped");
            }
        } catch (Exception e) {
            System.out.println("Exception from stop server ..");
            e.printStackTrace();
        }

    }

}
