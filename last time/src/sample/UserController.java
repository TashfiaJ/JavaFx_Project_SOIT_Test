package sample;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class UserController extends Features implements Initializable {

    @FXML
    private StackPane stkReport;
    @FXML
    private Pane pnReported;
    @FXML
    private Button reportToHome;
    @FXML
    private Pane pnIssue;
    @FXML
    private TextArea txtIssue;
    @FXML
    private Button reportSubmit;
    @FXML
    private Pane pnTable;
    @FXML
    private ImageView iconTableToReport;
    @FXML
    private TableView<Hospital> table;
    @FXML
    private TableColumn<Hospital, String> colName;
    @FXML
    private TableColumn<Hospital, String> colLoc;
    @FXML
    private TableColumn<Hospital, String> colICU;
    @FXML
    private TableColumn<Hospital, String> colOxy;
    @FXML
    private TableColumn<Hospital, String> colContact;
    @FXML
    private TableColumn<Hospital, String> colUpdate;
    @FXML
    private TextField searchBar;
    @FXML
    private Label tableToReport;
    @FXML
    private ImageView issueToTable;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        File f = ReadFile("data.csv");

        ArrayList<TableColumn<Hospital, String>> AllColumns = new ArrayList<>();
        AllColumns.add(colName);
        AllColumns.add(colLoc);
        AllColumns.add(colICU);
        AllColumns.add(colOxy);
        AllColumns.add(colContact);
        AllColumns.add(colUpdate);

        TableManager(f, AllColumns,table);

        SearchingContent(searchBar, table);
    }

    @FXML
    private void tableToReport (MouseEvent event) {
        if(event.getSource().equals(tableToReport) || event.getSource().equals(iconTableToReport)) {
            stkReport.toFront();
            pnIssue.toFront();
        }
    }

    @FXML
    private void issueToTable(MouseEvent event) {
        if(event.getSource().equals(issueToTable)) {
            pnTable.toFront();
        }
    }

    @FXML
    public void switchToHome(MouseEvent event) throws IOException {
        Parent switchHome = LoadFXML(event.getSource(), "home.fxml");
    }

    @FXML
    public void switchToHome2(ActionEvent event) throws IOException {
        Parent switchHome2 = LoadFXML(event.getSource(), "home.fxml");
    }

    @FXML
    private void reportSubmit(ActionEvent event) throws IOException {
        if(event.getSource().equals(reportSubmit)) {
            pnReported.toFront();
            String data = ">> " + txtIssue.getText() + "\n";

            if(txtIssue.getText() == null){
                return;
            }
            else{
                File fp = ReadFile("Report.txt");
                WriteFile(fp, data, true);
            }
        }
    }

}