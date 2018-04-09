/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverscreens;

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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import objRmi.client.Client;

/**
 * FXML Controller class
 *
 * @author Hagar
 */
public class AllUsersController implements Initializable {

    @FXML
    private TableView usersTable;

    @FXML
    private TableColumn emailField;

    @FXML
    private TableColumn statusField;

    @FXML
    private TableColumn nameField;

    @FXML
    private TableColumn<Client, Boolean> onOffField;

    @FXML
    private TableColumn avatarField;



    ClientDao daoObject;

    public ArrayList<Client> users;

    public ObservableList<Client> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            onOffField = new TableColumn<>();
            
            avatarField = new TableColumn<>("image");
            
            users = new ArrayList<>();
            
            
            
            daoObject = ClientDao.getDeflutClientDoa();
            
            
            if (daoObject.getAvailableUsers() != null) {
                for (Client user : daoObject.getAvailableUsers()) {
                    users.add(new Client(user.getName(), user.getEmail(),
                            user.getImage(), user.isOnStatus(), user.getStatus()));
                }
                
                loadTableData(users);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param users array list of all users in the database
     *
     * this function loads the users from database into the usersTable in the
     * GUI
     */
    public void loadTableData(ArrayList<Client> users) {

        data = FXCollections.observableArrayList(users);

        usersTable.setEditable(true);

        emailField.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailField.setCellFactory(TextFieldTableCell.forTableColumn());

        nameField.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameField.setCellFactory(TextFieldTableCell.forTableColumn());

        statusField.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusField.setCellFactory(TextFieldTableCell.forTableColumn());

        onOffField.setCellValueFactory(new PropertyValueFactory<>("onStatus"));
        onOffField.setCellFactory(new Callback<TableColumn<Client, Boolean>, TableCell<Client, Boolean>>() {
            @Override
            public TableCell<Client, Boolean> call(TableColumn<Client, Boolean> param) {
                return new TableCell<Client, Boolean>() {

                    @Override
                    protected void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {

                            setText(null);
                        } else if (item == true) {
                            setText("on");
                        } else {
                            setText("off");
                        }

                    }
                };
            }
        });

        avatarField.setCellValueFactory(new PropertyValueFactory<Client, byte[]>("image"));
        avatarField.setPrefWidth(60);
        avatarField.setCellFactory(TextFieldTableCell.forTableColumn());

        usersTable.setItems(data);

    }

}
