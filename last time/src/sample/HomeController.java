package sample;

//import animatefx.animation.SlideInLeft;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Features;

import java.io.IOException;

public class HomeController extends Features {

    @FXML
    private Button btnToSignUp;
    @FXML
    private Button btnToHospital;
    @FXML
    private Button btnToAdmin;
    

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
    @FXML
    public void switchToUser(ActionEvent event) throws IOException {
        Parent user = LoadFXML(event.getSource(), "User.fxml");
    }

    @FXML
    public void switchToHospital(ActionEvent event) throws IOException {
        Parent hospital = LoadFXML(event.getSource(), "Hospital3.fxml");
        //new SlideInLeft(hospital).play();
    }
    
    @FXML
    public void switchToAdminSignIn(ActionEvent event) throws IOException {
        // will be deleted.
    }
}
