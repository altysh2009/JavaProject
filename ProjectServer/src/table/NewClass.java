/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table;

import dao.ClientDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import oracle.jdbc.OracleDriver;

/**
 *
 * @author Altysh
 */
public class NewClass extends Application{
     //CONNECTION DATABASE
    private ObservableList<ObservableList> data;
    private TableView tableview;
    static final String EMAIL = "mail";
    static final String STATUS = "status";
    static final String GENDER = "gender";
    static final String COUNTRY = "country";
    static final String NAME = "name";
    public void buildData(){
          Connection c ;
          data = FXCollections.observableArrayList();
          try{
               //ClientDao cc = new ClientDao();
            Connection con;
            DriverManager.registerDriver(new OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "1234");
            c = con;
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT mail,status,gender,country,name from "+general_db_interface.Client_Interface.CLEINT_TABLE_NAME;
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                //col.setEditable(true);
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
               // col.setEditable(true);
                col.setOnEditCommit(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        System.out.println("commted");
                    }
                });

                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
            }
           // tableview.setEditable(true);

            /********************************
             * Data added to ObservableList *
             ********************************/
            int i=1;
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for( i=1; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row ["+i+"] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
      }

public static void main(String[] args) {
        launch(args);
    }
      @Override
      public void start(Stage stage) throws Exception {
        //TableView
        tableview = new TableView();
        tableview.setEditable(true);
        buildData();

        //Main Scene
        Scene scene = new Scene(tableview);        

        stage.setScene(scene);
        stage.show();
      }
}
