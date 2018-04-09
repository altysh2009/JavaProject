/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author Hagar
 */
public class NodeMsgController implements Initializable {

    @FXML
    private TextFlow textFlow;

    public TextFlow getTextFlow() {
        return textFlow;
    }

    @FXML
    private Text textMsg;

    @FXML
    private HBox hBox;

    public HBox gethBox() {
        return hBox;
    }

    Text msg;

    public void setMsg(Text msg) {
        this.msg = msg;
    }

    public Text getTextMsgNode() {
        return textMsg;
    }
    
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
