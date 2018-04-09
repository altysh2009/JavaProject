/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverscreens;

import dtopkg.UserDTO;
import dao.ClientDao;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import objRmi.client.Client;

/**
 * FXML Controller class
 *
 * @author Hagar
 */
public class OnlinePaneController implements Initializable {

    @FXML
    private ListView listView;

    Connection conn = null;
    ClientDao daoObject;
    Client client;
    public ArrayList<Client> users;
    UserDTO user;
    ObservableList<Client> doList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            users = new ArrayList<>();
            
            
            daoObject = ClientDao.getDeflutClientDoa();
            
            
            
            getOnlineUsers();
        } catch (SQLException ex) {
            Logger.getLogger(OnlinePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * List the online users into the GUI
     */
    public void getOnlineUsers() {

        users = daoObject.getOnlineList();
        if (users != null) {

            ObservableList<Client> observeList = FXCollections.observableArrayList();
            observeList.addAll(users);

            listView.setItems(observeList);
            listView.setCellFactory(new Factory());

        } else {
            System.out.println("Error");
        }

    }

}
