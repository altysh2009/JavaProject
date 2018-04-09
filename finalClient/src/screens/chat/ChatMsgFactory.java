/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.chat;

import chatobj.MessageInfo;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;
import message.NodeLabel;
import message.NodeMsgController;
import objRmi.massage.Message;

/**
 *
 * @author Altysh
 */
public class ChatMsgFactory implements
        Callback<ListView<MessageInfo>, ListCell<MessageInfo>> {

    private String senderMail, usernName;

    public ChatMsgFactory(String sender, String user) {
        senderMail = sender;
        usernName = user;
    }

    @Override
    public ListCell<MessageInfo> call(ListView<MessageInfo> param) {
        return new ListCell<MessageInfo>() {
            @Override
            protected void updateItem(MessageInfo it, boolean empty) {
                try {
                    super.updateItem(it, empty);
                    if (it == null) {
                        setGraphic(null);
                        setStyle("-fx-background-color:white");
                    } else {
                        Message item = it.getMassage();
                        FXMLLoader loader = new FXMLLoader(new File("./src/message/FactoryMsg.fxml").toURL());

                        Parent p = loader.load();
                        NodeMsgController cell = loader.getController();
                        
                        cell.getTextMsgNode().setFill(Color.valueOf(item.getColor()));
                        cell.getTextMsgNode().setStyle("-fx-font-style:"+item.getFont()+";");
                        System.out.println("-fx-font-style:"+item.getFont()+";");
                        System.out.println(senderMail + " " + item.getSender());
                        if (it.isIsFirst()) {
                            if (it.getDir().equals("left")) {
                                cell.getTextMsgNode().setText(item.getText());
                                cell.getTextMsgNode().setTextAlignment(TextAlignment.RIGHT);
                                cell.gethBox().setAlignment(Pos.TOP_LEFT);
                                cell.getTextFlow().setStyle("-fx-border-radius: 10 10 10 10; -fx-background-radius: 10 10 10 10; -fx-background-color: #90CAF9; -fx-padding: 10; -fx-border-insets: 5px; -fx-background-insets: 5px;");
                                //cell.gethBox().setAlignment(Pos.BASELINE_RIGHT);
                                setAlignment(Pos.BASELINE_LEFT);
                            } else {
                                cell.getTextMsgNode().setText(item.getText());
                                cell.getTextMsgNode().setTextAlignment(TextAlignment.LEFT);
                                cell.gethBox().setAlignment(Pos.TOP_RIGHT);
cell.getTextFlow().setStyle("-fx-border-radius: 10 10 10 10; -fx-background-radius: 10 10 10 10; -fx-background-color: #E0E0E0; -fx-padding: 10; -fx-border-insets: 5px; -fx-background-insets: 5px;");
                                //cell.gethBox().setAlignment(Pos.BASELINE_RIGHT);
                                setAlignment(Pos.BASELINE_RIGHT);
                            }
                            setGraphic(p);
                        } else {
                            FXMLLoader loader2 = new FXMLLoader(new File("./src/message/aw.fxml").toURL());
                            VBox box = loader2.load();

                            NodeLabel con = loader2.getController();

                            if (it.getDir().equals("left")) {
                                cell.getTextMsgNode().setText(item.getText());
                                cell.getTextMsgNode().setTextAlignment(TextAlignment.RIGHT);
                                cell.gethBox().setAlignment(Pos.TOP_LEFT);
cell.getTextFlow().setStyle("-fx-border-radius: 10 10 10 10; -fx-background-radius: 10 10 10 10; -fx-background-color: #90CAF9; -fx-padding: 10; -fx-border-insets: 5px; -fx-background-insets: 5px;");
                             
                                //cell.gethBox().setAlignment(Pos.BASELINE_RIGHT);
                                setAlignment(Pos.BASELINE_LEFT);
                                con.getLabel().setText(item.getSender());
                                // box = new VBox(new Label(usernName),p);
                                //box.setPrefHeight(USE_COMPUTED_SIZE);
                                box.getChildren().add(p);
                                box.setAlignment(Pos.BASELINE_LEFT);
                            } else {
cell.getTextFlow().setStyle("-fx-border-radius: 10 10 10 10; -fx-background-radius: 10 10 10 10; -fx-background-color: #E0E0E0; -fx-padding: 10; -fx-border-insets: 5px; -fx-background-insets: 5px;");
                                cell.getTextMsgNode().setText(item.getText());
                                cell.getTextMsgNode().setTextAlignment(TextAlignment.LEFT);
                                cell.gethBox().setAlignment(Pos.TOP_RIGHT);
                                con.getLabel().setText(item.getSender());
                                //cell.gethBox().setAlignment(Pos.BASELINE_RIGHT);
                                setAlignment(Pos.BASELINE_RIGHT);
                                box.getChildren().add(p);
                                //box.setPrefHeight(USE_COMPUTED_SIZE);
                                box.setAlignment(Pos.BASELINE_RIGHT);
                            }
                            
                            //setHeight(box.getHeight());
                            setGraphic(box);
                        }
                        //cell.getImageViewNode().setImage(new Image(new File("./src/screens/forthscreen/friends/groups/Node/hhh.PNG").toURL().toString()));

                        // System.out.println("null");
                        // cell.getLabelStatuesNode().setText(item.getStatus());
                        //???  cell.setC(item);
                        // setStyle("-fx-control-inner-background: blue;");
                    }

                } catch (IOException ex) {
                    Logger.getLogger(NodeMsgController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        };

    }

}
