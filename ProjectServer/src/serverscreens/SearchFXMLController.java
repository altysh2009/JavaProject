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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Hagar
 */
public class SearchFXMLController implements Initializable {

    Connection con = null;
    ClientDao daoObject;

    @FXML
    private Label name;
    @FXML
    private Label status;
    @FXML
    private Label country;
    @FXML
    private Label gender;
    @FXML
    private Label email;
    @FXML
    private TextField emailField;
    UserDTO user;

    /**
     * Initializes the controller class.
     * @param url no need
     * @param rb no need
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            user = new UserDTO();
            
            daoObject = ClientDao.getDeflutClientDoa();
            //daoObject.setCon(con);
        } catch (SQLException ex) {
            Logger.getLogger(SearchFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * search for users by email and view data to the GUI F
     */
    public void action() {

        String text = emailField.getText();

        user = daoObject.getUserDTO(text);
        if (user != null) {
            name.setText("Name :     " + user.GetUserName());
            email.setText("Email :     " + user.GetUserEmail());
            country.setText("Country :     " + user.GetUserCountry());
            gender.setText("Gender :     " + user.GetUserGender());
            status.setText("Status :     " + user.GetUserStatus());

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No User with this Email !");
            alert.showAndWait();
        }

    }

}
