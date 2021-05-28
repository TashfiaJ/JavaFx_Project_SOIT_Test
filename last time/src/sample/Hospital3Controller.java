package sample;

//import animatefx.animation.BounceIn;
//import animatefx.animation.SlideInRight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import sample.Features;

import java.io.*;


public class Hospital3Controller extends Features {

    @FXML
    public TextField txtContact;
    @FXML
    private StackPane stkReg; // full stack for registering hospitals
    @FXML
    private Pane pnRegDone; // Register SUCCESSFUL window
    @FXML
    private Button RegDoneToUp;
    @FXML
    private Pane pnReg; // Register window
    @FXML
    private ImageView RegToHos;
    @FXML
    private Button RegToRegDone;
    @FXML
    private StackPane stkHU; // full stack for stkUp(Update info) and pnHos(2nd Mainwindow)
    @FXML
    private StackPane stkUp; // full stack for updating info(pnUp, pnUpDone)
    @FXML
    private Pane pnUpDone; // update SUCCESSFUL window
    @FXML
    private Button UpDoneToTable;
    @FXML
    private Pane pnUp; // update window
    @FXML
    private ImageView UpToHos;
    @FXML
    private Button UpToUpDone;
    @FXML
    private Pane pnHos; // 2nd main window
    @FXML
    private ImageView HosToHome;
    @FXML
    private Button HosToUp;
    @FXML
    private Button HosToReg;
    @FXML
    private Button HosToTable;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtLoc;
    @FXML
    private TextField txtKey;
    @FXML
    private TextField txtUpKey;
    @FXML
    private TextField txtICU;
    @FXML
    private TextField txtOxy;
    @FXML
    private Label empField;
    @FXML
    private Label incorrectKey;


    @FXML
    private void bHosToReg(ActionEvent event) {
        if (event.getSource().equals(HosToReg)) {
            System.out.println("Welcome to Registration Window");
            stkReg.toFront();
            pnReg.toFront();
        }
    }

    @FXML
    private void bHosToUp(ActionEvent event) {
        if (event.getSource().equals(HosToUp)) {
            System.out.println("Welcome to Update Window");
            stkUp.toFront();
            pnUp.toFront();
        }
    }

    @FXML
    private void bHosToTable(ActionEvent event) throws IOException {
        Parent Home2ToHome1 = LoadFXML(event.getSource(), "User.fxml");
    }

    @FXML
    public void mHosToHome(MouseEvent mouseEvent) throws IOException {
        System.out.println("Return to 1st MainWindow[ImageViewBack]");

        Parent node = LoadFXML(mouseEvent.getSource(), "home.fxml");
       // new SlideInRight(node).play();
    }

    @FXML
    private void bUpToUpDone(ActionEvent event) throws IOException {
        updateUserInfo();
        //System.out.println("Update DONE!!!");
        //pnUpDone.toFront();
        //new BounceIn(pnUpDone).play();
    }

    @FXML
    private void mUptoHos(MouseEvent event) {
        System.out.println("Update to 2nd MainWIndow[ImageViewBack]");
        pnHos.toFront();

    }

    @FXML
    private void bRegToRegDone(ActionEvent event) throws IOException { //registration done page
        //System.out.println("Registration DONE!!");
        // TODO: if any field is empty then show a message to fill it up;
        if(txtName.getText().toString().equals("") || txtLoc.getText().toString().equals("") || txtKey.getText().toString().equals("")){
            System.out.println("Fill UP");
            registerUser();
        }
        else {
            registerUser();
            pnRegDone.toFront();
            //new BounceIn(pnRegDone).play();
        }
    }

    @FXML
    private void mRegToHos(MouseEvent event) {
        System.out.println("Registration to 2nd MainWindow[ImageviewBack]");
        stkHU.toFront();
        pnHos.toFront();
    }

    @FXML
    private void bRegDoneToUp(ActionEvent event) { //return to update info page
        System.out.println("Registration Done!! to 2nd MainWindow*******");
        stkHU.toFront();
        stkUp.toFront();
        pnUp.toFront();

    }

    public void bUpDonetoHos(MouseEvent mouseEvent) {
        System.out.println("Update Done to 2nd MainWindow");
        stkHU.toFront();
        pnHos.toFront();
    }

    public void registerUser() throws IOException {
        String name = txtName.getText();
        String location = txtLoc.getText();
        String key = txtKey.getText();

        File f = ReadFile("data.csv");
        String data = name + "," + location + "," + null + "," + null + "," + null + "," + null + "," + key + "\n";
        if(name.toString().equals("") || location.toString().equals("") || key.toString().equals("")){
            empField.setText("You should fill up all the fields");
            txtKey.clear();
            txtLoc.clear();
            txtName.clear();
        }
        else{
            WriteFile(f, data, true);
            System.out.println("Info added successfully. Registration done.");

            txtKey.clear();
            txtLoc.clear();
            txtName.clear();}
    }

    public void updateUserInfo() throws IOException {

        String ICU = txtICU.getText();
        String Oxygen = txtOxy.getText();
        String Contact = txtContact.getText();
        String key = txtUpKey.getText();

        // TODO: 1. if key wrong or not found in database ...show one type of message. 2. if any field is empty show message.
        if(ICU.toString().equals("") || Oxygen.toString().equals("") || key.toString().equals("") || Contact.toString().equals("")){
            incorrectKey.setText("You should fill up all the fields");}
        else{
        FileReader fr = new FileReader("data.csv");
        BufferedReader br = new BufferedReader(fr);
        String s;
        int flag=0;
        while ((s = br.readLine()) != null) { // read a line
            String[] data=s.split(",");
            if(data[6].equals(key.toString())){
                flag=1;
                break;
            }}

        if(flag==1){

            StringBuffer sb = SaveData(key, ICU, Oxygen, Contact);

            File file = new File("data.csv");
            PrintWriter pw = new PrintWriter(new FileOutputStream(file, false));
            pw.print(sb);
            pw.close();
            System.out.println("Update DONE!!!");
            pnUpDone.toFront();}
        else{
            incorrectKey.setText("Incorrect Key");
        }}
        txtICU.clear();
        txtUpKey.clear();
        txtOxy.clear();
        txtContact.clear();

    }
}
