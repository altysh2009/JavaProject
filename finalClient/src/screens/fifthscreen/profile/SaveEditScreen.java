/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package screens.fifthscreen.profile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Aya
 */
public class SaveEditScreen extends Application {
     private FXMLLoader loader;
    private SaveEditScreenController controller;
    private Parent root;
    private static Stage primarySt;
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        primarySt=stage;
        
       loader = new FXMLLoader(getClass().getResource("SaveEditFXML.fxml"));
      //  controller = new SaveEditScreenController();
        controller = loader.getController();
        root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    
    
    public static Stage getPrimary ()
    {
     return (primarySt);
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    
}
