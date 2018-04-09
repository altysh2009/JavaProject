/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverscreens;

import dao.ClientDao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Hagar
 */
public class ChartsController implements Initializable {

    @FXML
    private PieChart pichart;

    @FXML
    NumberAxis yAxis;

    @FXML
    CategoryAxis xAxis;

    @FXML
    BarChart<String, Number> barChart;

    Connection con = null;

    ClientDao daoObject;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

      

        try {
            daoObject = ClientDao.getDeflutClientDoa();
            //daoObject.setCon(con);
            int countMale = (int) daoObject.getMale();
            int countFemale = (int) daoObject.getFemale();
            int onlineUsers = (int) daoObject.getOnlineClient();
            
            ObservableList<PieChart.Data> pieChartData
                    = FXCollections.observableArrayList(
                            new PieChart.Data("Male", countMale),
                            new PieChart.Data("Female", countFemale));
            
            pichart.setData(pieChartData);
            
            pichart.setClockwise(true);
            
            XYChart.Series series1 = new XYChart.Series();
            series1.setName("Number Of Online Users");
            series1.getData().add(new XYChart.Data("Online", onlineUsers));
            
            barChart.getData().addAll(series1);
        } catch (SQLException ex) {
            Logger.getLogger(ChartsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
