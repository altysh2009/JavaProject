/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.thirdscrenn.registration;

import java.nio.file.Path;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import screens.secondscreen.login.loginController;

/**
 *
 * @author Aya
 */
public class ThirdScrennRegistration extends Application {
     private FXMLLoader loader;
    private loginController controller;
    private Parent root;
     private static Stage primary;
    @Override
    public void start(Stage stage) throws Exception {
          primary=stage;
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
         loader = new FXMLLoader(getClass().getResource("registrationFXML.fxml"));
      //  controller = new loginController();
        controller = loader.getController();
        root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
 
    /**
     *
     * @return primary stage
     */
    public static Stage getPrimary ()
    {
     return (primary);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
