/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.firstscreen.img;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import rmi.implementation.ClientImpl;
import rmi.interfaces.ServerRmi;

/**
 *
 * @author Aya
 */
public class FirstScreenImg extends Application {

  
    
    private FXMLLoader loader;
    private splashController controller;
    private Parent root;
     private static Stage primary;
      
    /**
     *
     * @return primary which is stage of screen
     */
    public static Stage getPrimary() {
       return (primary);
    }
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       
            
          primary=stage;
        loader = new FXMLLoader(getClass().getResource("splashFXML.fxml"));
       controller = new splashController();
        //loader.setController(controller);
        loader.setController(controller);
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
           @Override
           public void handle(WindowEvent event) {
         System.exit(0);
           }
       });
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    
  
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
