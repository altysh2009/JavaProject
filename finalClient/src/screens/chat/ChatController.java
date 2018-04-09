/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.chat;

import chathandler.ChatHandler;
import chatobj.ChatManger;
import chatobj.MessageInfo;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import objRmi.client.Client;
import objRmi.massage.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ProcessingInstruction;
import rmi.interfaces.ServerRmi;
//import screens.secondscreen.login.SecondScreenLogin;

/**
 * FXML Controller class
 *
 * @author Aya
 */
public class ChatController implements Initializable {
    
    @FXML
    private ListView listChat;
    
    @FXML
    private TextField textMsg;
    
    @FXML
    private ComboBox<String> comboStyle;
    
    @FXML
    private ComboBox<Color> comboColor;
    
    @FXML
    private Button buttonSend;
    
    @FXML
    private Label labelName;
    
    @FXML
    private Circle circlePic;
    
    @FXML
    private Button buttonBack;
    
    @FXML
    private TextField textSearch;
    
    @FXML
    private ListView listPeople;
    private ServerRmi server;
    ChatHandler chatHandler;
    long id;
    @FXML
Button save;

    /**
     *
     * @param id of chat
     */
    public ChatController(long id) {
        setId(id);
    }

    /**
     *
     * @param id to set id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @param server object of server rmi
     */
    public void setServer(ServerRmi server) {
        this.server = server;
    }
    
    /**
     *
     * @param name object of string 
     */
    public void setName(String name) {
        this.name = name;
    }
    private String name = "ahmed";
    private String sender = null;
    
    private ObservableList<MessageInfo> listMsg;
    private ObservableList<ChatHandler> list;
    
    private static Stage primaryStage = chat.getPrimary();    
    String path = ".\\src\\screens\\forthscreen\\friends\\forthScreenContactFXML.fxml";
    
    /**
     *used to go to profile screen by loading path
     * 
     */
    @FXML
    private void goTOprof() {
        try {
            //primaryStage=SecondScreenLogin.getPrimary();
            FXMLLoader fxml = new FXMLLoader();
            Parent root = fxml.load(new File(path).toURL());
            if (primaryStage != null) {
                primaryStage.setScene(new Scene(root));
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @param mail which is name of user
     */
    public void setUserName(String mail) {
        name = mail;
    }
    
    /**
     *used to set massege style and send massege
     * 
     */
    @FXML
    private void send() {
        if (textMsg.getText().length() > 0) {
            try {
                Message m = new Message();
                m.setText(textMsg.getText());
                String sty = comboStyle.getValue();
                if (sty != null) {
                    m.setFont(sty);
                } else {
                    m.setFont("normal");
                }
                Color c = comboColor.getValue();
                if (c != null) {
                    m.setColor(c.toString());
                } else {
                    m.setColor(Color.BLACK.toString());
                }
                m.setDate(new Date().getTime());
                // m.setFont(textMsg.getStyle());
                m.setSender(name);
                m.setType("massage");
                
                server.sendMassage(m, id);

                //sender = name;
                // listMsg.add(mI);
            } catch (RemoteException ex) {
                Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        textMsg.setText("");
    }

    /**
     *
     * @param m which is object of massege
     * @param id which is object of id of group
     */
    public void reciveMassage(Message m, long id) {
        System.out.println("reciveMassage");
        MessageInfo mI = new MessageInfo(m);
        if (m.getSender().equals(name)) {
            mI.setDir("left");
        } else {
            mI.setDir("right");
        }
        if (sender != null && sender.equals(m.getSender())) {
            mI.setIsFirst(true);
        }
        chatHandler.addMsg(id, mI);
        sender = m.getSender();
        // listChat.scrollTo(listMsg.size()-1);
    }

    /**
     *
     * @param m object of massage
     */
    public void reciveMassage(Message m) {
        System.out.println("reciveMassage");
        
    }
    
    @FXML
    private void enterKey(KeyEvent ev) {
        if (ev.getCode() == KeyCode.ENTER) {
            send();
        }
        
    }
    
    @FXML
    private void setInf(Client client) {
        //Image img = client.getImage();
        //circlePic.setFill(new ImagePattern(img));
        labelName.setText(client.getName());
    }
    
    @FXML
    private void click() {
        ListCell<Color> c = comboColor.getButtonCell();
        Color c1 = c.getItem();
        String font = comboStyle.getButtonCell().getItem();
        
    }

    /**
     *
     * @param color object of Color
     * @return String color
     */
    public String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        comboStyle.getItems().addAll("normal", "italic", "oblique");
        comboColor.getItems().addAll(Color.BLUE, Color.BLACK, Color.RED);
        comboColor.setCellFactory(new Callback<ListView<Color>, ListCell<Color>>() {
            @Override
            public ListCell<Color> call(ListView<Color> param) {
                return new ListCell<Color>() {
                    @Override
                    protected void updateItem(Color item, boolean empty) {
                        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            Rectangle rect = new Rectangle(20, 20, item);
                            setGraphic(rect);
                        }
                    }
                    
                };                
            }
        });
        comboColor.setButtonCell(new ListCell<Color>() {
            @Override
            protected void updateItem(Color item, boolean empty) {
                super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    Rectangle rect = new Rectangle(20, 20, item);
                    setGraphic(rect);
                    
                }
            }
            
        });
        comboColor.setCellFactory(new Callback<ListView<Color>, ListCell<Color>>() {
            @Override
            public ListCell<Color> call(ListView<Color> param) {
                return new ListCell<Color>() {
                    @Override
                    protected void updateItem(Color item, boolean empty) {
                        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            Rectangle rect = new Rectangle(20, 20, item);
                            setGraphic(rect);
                            setOnMousePressed((event) -> {
                                String style = comboStyle.getValue();
                                if (style != null) {
                                    textMsg.setStyle("-fx-background-radius:100; -fx-text-fill:" + toRGBCode(comboColor.getValue()) + "; -fx-font-style:" + style + ";");
                                } else {
                                    textMsg.setStyle("-fx-text-fill:" + toRGBCode(comboColor.getValue()) + "; -fx-font-style:" + "normal;");
                                }
                            });
                            
                        }
                    }
                    
                };
            }
        });
        comboStyle.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            Text rect = new Text(item);
                            setGraphic(rect);
                            setOnMousePressed((event) -> {
                                String style = comboStyle.getValue();
                                if (comboColor.getValue() != null) {
                                    textMsg.setStyle("-fx-background-radius:100; -fx-text-fill:" + toRGBCode(comboColor.getValue()) + "; -fx-font-style:" + style + ";");
                                } else {
                                    textMsg.setStyle("-fx-background-radius:100; -fx-text-fill:" + toRGBCode(Color.BLACK) + "; -fx-font-style:" + style + ";");
                                }
                            });
                            
                        }
                    }
                    
                };
            }
        });
        
        chatHandler = ChatHandler.getDeFultChatHandler();
        
        chatHandler.setListViews(listPeople, listChat);
        // System.out.println("awf");
        buttonSend.setDefaultButton(true);
        list = FXCollections.observableArrayList();
        listMsg = FXCollections.observableArrayList();
        
        chatHandler.setViewById(id);
        listChat.setCellFactory(new ChatMsgFactory(sender, name));
        
        listPeople.setCellFactory(new ChatListFactory());
        
    }    
    @FXML
    void onSave() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showSaveDialog(primaryStage);
        ChatManger ch = chatHandler.getChat(id);
        ObservableList<MessageInfo> list= ch.getMessages();
        String last = "";
        try {
            DocumentBuilderFactory d = DocumentBuilderFactory.newInstance();
            DocumentBuilder dd = d.newDocumentBuilder();
            Document doc = dd.newDocument();
             ProcessingInstruction pi = (ProcessingInstruction)
        doc.createProcessingInstruction(
        "xml-stylesheet",
        "type=\"text/xsl\" href=\"https://rawgit.com/altysh2009/JavaProject/master/style.xsl\"");
             doc.insertBefore(pi, doc.getFirstChild());
            Element e = doc.createElement("MyMassages");
            for (MessageInfo m : list) {
                if(!m.getMassage().getSender().equals(last))
                {
                    Element mess = doc.createElement("message");
                    Element mass = doc.createElement("m");
                mass.setTextContent(m.getMassage().getSender());
                Element dir = doc.createElement("dir");
                dir.setTextContent(m.getDir());
                mess.appendChild(dir);
                mess.appendChild(mass);
                e.appendChild(mess);
                last = m.getMassage().getSender();
                }
                Element mass = doc.createElement("m");
                mass.setTextContent(m.getMassage().getText());
                Element color = doc.createElement("color");
               
                color.setTextContent(toRGBCode(Color.valueOf(m.getMassage().getColor())));
                Element backgroundColor = doc.createElement("backgroundColor");
               if(m.getDir().equals("left"))
                backgroundColor.setTextContent("#90CAF9");
               else backgroundColor.setTextContent("#E0E0E0");
                 Element colorXML = doc.createElement("colorXML");
               
                colorXML.setTextContent(m.getMassage().getColor());
                Element font = doc.createElement("font");
                font.setTextContent(m.getMassage().getFont());
                Element dir = doc.createElement("dir");
                dir.setTextContent(m.getDir());
             
                Element Message = doc.createElement("message");
                Message.appendChild(mass);
                Message.appendChild(backgroundColor);
                Message.appendChild(color);
                Message.appendChild(font);
                Message.appendChild(dir);
                Message.appendChild(colorXML);
                e.appendChild(Message);

            }
            doc.appendChild(e);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            if (file != null) {
                System.out.println("not null");
                if(file.exists())
                     file.createNewFile();
                trans.transform(new DOMSource(doc), new StreamResult(file));
            }

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
