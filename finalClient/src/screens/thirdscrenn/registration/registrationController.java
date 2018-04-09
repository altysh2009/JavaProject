/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.thirdscrenn.registration;

import dtopkg.UserDTO;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import rmi.implementation.ClientImpl;
import rmi.interfaces.ServerRmi;

import screens.firstscreen.img.FirstScreenImg;
import screens.forthscreen.friends.ForthScreenContactController;

/**
 *
 * @author Aya
 */
public class registrationController implements Initializable {

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldPass;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldCpass;
    @FXML
    private TextField textFieldAnswer;
    @FXML
    private ComboBox comboBoxCountry;
    @FXML
    private ComboBox comboBoxGender;
    @FXML
    private ComboBox comboBoxQuestion;
    @FXML
    private Label labelErrorMail;
    @FXML
    private Label labelErrorPass;
    @FXML
    private Label labelErrorQuestion;
    @FXML
    private Label labelErrorAnswer;

    @FXML
    private Label labelErrorCPass;

    @FXML
    private Button buttonSignup;
    @FXML
    private Circle circleImg;
    @FXML
    private Button buttonAddImg;

    Connection con = null;
    File image = null;
    File Defult = null;

    private Pattern pattern;
    private Matcher matcher;
    private static String EMAIL_PATTERN;
    String name;
    String Email;
    String passwordd;
    String conPassword;
    String ans;
    String country;
    String gender;
    String question;
    boolean mail = false;
    boolean password = false;
    boolean answer = false;
    boolean Question = false;
    ServerRmi serverRmi;

    private static Stage primaryStage = ThirdScrennRegistration.getPrimary();
    String path2 = ".\\src\\screens\\forthScreen\\friends\\forthScreenContactFXML.fxml";

    /**
     *
     * @param rmi object of server rmi to call methods
     */
    public registrationController(ServerRmi rmi) {
        serverRmi = rmi;
    }

    /**
     * @return used to check validation of password and confirm password if they
     * are same or not
     *
     */
    boolean checkSamePass(String pass, String Cpass) {
        if (passwordd.isEmpty()) {
            labelErrorPass.setText("enter password");
        } else {
            if (!pass.equals(Cpass)) {
                labelErrorCPass.setText("enter same password");
            } else {
                labelErrorCPass.setText("");
                password = true;
            }
        }
        return (password);
    }

    /**
     * used to check if security question is selected or not
     *
     */
    void checkSelectQuestion() {
        if (comboBoxQuestion.getSelectionModel().isEmpty()) {

            labelErrorQuestion.setText("choose Question");
        } else {
            labelErrorQuestion.setText("");
        }

    }

    /**
     * @return boolean to check answer is written or not
     *
     */
    boolean checkAnswer(String a) {
        if (textFieldAnswer.getText().isEmpty()) {
            labelErrorAnswer.setText("please answer question");
        } else {
            labelErrorAnswer.setText("");
            answer = true;
        }
        return (answer);
    }

    /**
     * @return boolean to check validation of mail using pattern matching
     *
     */
//    @FXML
    boolean checkValidEmail(String s) {

        EMAIL_PATTERN
                = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(s);
        if (matcher.matches() == false) {
            labelErrorMail.setText("Invalid mail");
        } else {

            labelErrorMail.setText("");
            mail = true;
        }
        return (mail);
    }

    /**
     *
     * used to initialize components in UI and take action on signup button to
     * take values from UI and call previous method for checking data and
     * finally change screen to profile by loading loader and set controller
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        comboBoxCountry.getItems().addAll("Cairo", "Alexandria", "Others");
        comboBoxCountry.setPromptText("Country");
        comboBoxGender.getItems().addAll("Male", "Female");
        comboBoxGender.setPromptText("Gender");
        comboBoxQuestion.getItems().addAll("what is your plan for 10 years?", "what you want to do next year?");
        comboBoxQuestion.setPromptText("Questions");
        Defult = new File("./src/Blank_Avatar.png");
        try {
            circleImg.setFill(new ImagePattern(new Image(new File("./src/Blank_Avatar.png").toURL().toString())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(registrationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        buttonSignup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                name = textFieldName.getText();
                Email = textFieldEmail.getText();
                passwordd = textFieldPass.getText();
                conPassword = textFieldCpass.getText();
                ans = textFieldAnswer.getText();
                country = (String) comboBoxCountry.getValue();
                gender = (String) comboBoxGender.getValue();
                question = (String) comboBoxQuestion.getValue();
                checkSelectQuestion();
                checkAnswer(ans);
                checkValidEmail(Email);
                UserDTO client = new UserDTO();
                if (!(passwordd.isEmpty() && conPassword.isEmpty())) {
                    labelErrorPass.setText("");
                    checkSamePass(passwordd, conPassword);
                }
                int x;
                if (gender == "Male") {
                    x = 0;
                } else {
                    x = 1;
                }

                if (mail == true && password == true && answer == true) {
                    Path path;
                    try {
                        if (image != null) {
                            path = Paths.get(image.getAbsolutePath());

                        } else {
                            path = Paths.get(Defult.getAbsolutePath());
                        }
                        System.out.println(Files.readAllBytes(path).length);
                        client.SetUserPic(Files.readAllBytes(path));
                        client.SetUserAnswer(ans);
                        client.SetUserCountry(country);
                        client.SetUserEmail(Email);
                        client.SetUserGender(x);
                        client.SetUserPassword(passwordd);
                        client.SetUserName(name);
                        client.SetUserSequrityQues(question);
                        client.SetUserStatus("Online");
                        client.SetUserStatusOnOff(0);
                        boolean ok;

                        ok = serverRmi.signup(client);

                        if (ok == true) {
                            try {
                                primaryStage = FirstScreenImg.getPrimary();
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("../../forthScreen/friends/forthScreenContactFXML.fxml"));
                                ClientImpl c = new ClientImpl(serverRmi, primaryStage, Email);
                                serverRmi.sendClient(c, client.GetUserEmail());
                                ForthScreenContactController controller = new ForthScreenContactController(client, serverRmi, c);
                                loader.setController(controller);
                                loader.load();
                                Parent p = loader.getRoot();
                                primaryStage.setScene(new Scene(p));
                            } catch (IOException ex) {
                                Logger.getLogger(registrationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            labelErrorMail.setText("this mail already exist");
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(registrationController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(registrationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        });
        buttonAddImg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser;
                fileChooser = new FileChooser();
                FileChooser.ExtensionFilter imageFilter
                        = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
                //FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
                fileChooser.getExtensionFilters().add(imageFilter);
                File f = fileChooser.showOpenDialog(primaryStage);

                if (f != null) {
                    image = f;
                    Image img;
                    try {
                        img = new Image(f.toURL().toString());
                        circleImg.setFill(new ImagePattern(img));
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(registrationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });

    }
}
