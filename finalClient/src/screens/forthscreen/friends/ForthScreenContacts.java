/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.forthscreen.friends;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Aya
 */
public class ForthScreenContacts extends Application {

    static Stage getPrimary() {
        return (primary);
    }
    private FXMLLoader loader;
    private static Stage primary;

    private ForthScreenContactController controller;
    private Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        // Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        // System.out.println(new File(".\\src\\screens\\forthscreen\\friends\\Node/NodeFie.fxml").toURI().toString());
//        loader = new FXMLLoader(getClass().getResource("forthScreenContactFXML.fxml"));
        loader = new FXMLLoader(getClass().getResource("ForthScreenContactFXML.fxml"));
        // controller = new ForthScreenContactController();
        controller = loader.getController();
        root = loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
