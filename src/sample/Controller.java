package sample;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> resume_country;

    @FXML
    private ChoiceBox<String> resume_state;

    @FXML
    private ChoiceBox<String> resume_city;

    @FXML
    private TextField resume_first_name;

    @FXML
    private TextField resume_second_name;

    @FXML
    private TextField resume_email;

    @FXML
    private TextField resume_phone_number;

    @FXML
    private TextArea resume_experience;

    @FXML
    private TextArea resume_languages;

    @FXML
    private TextArea resume_about_you;

    @FXML
    private ChoiceBox<String> resume_to_position;

    @FXML
    private JFXButton resume_send_btn;

    @FXML
    void initialize() {
                try{
                    Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/hr_department", "root", "");
                    PreparedStatement pst = cn.prepareStatement("INSERT INTO resumes values(null,?,?,?,?,?,?,?,?,?,?,?)");
                    ObservableList<String> countries = FXCollections.observableArrayList();
                    ObservableList<String> states = FXCollections.observableArrayList();
                    ObservableList<String> cities = FXCollections.observableArrayList();
                    ObservableList<String> positions = FXCollections.observableArrayList();
                    ResultSet rst = cn.createStatement().executeQuery("SELECT * FROM countries");
                    while(rst.next()){
                        countries.add(rst.getString("name"));
                    }
                    rst=cn.createStatement().executeQuery("SELECT * FROM states");
                    while(rst.next()){
                        states.add(rst.getString("name"));
                    }
                    rst = cn.createStatement().executeQuery("SELECT * FROM cities");
                    while(rst.next()){
                        cities.add(rst.getString("name"));
                    }
                    rst = cn.createStatement().executeQuery("SELECT * FROM positions");
                    while(rst.next()){
                        positions.add(rst.getString("name"));
                    }
                    resume_country.setItems(countries);
                    resume_state.setItems(states);
                    resume_city.setItems(cities);
                    resume_to_position.setItems(positions);
                    resume_send_btn.setOnAction(event -> {
                    try{
                        pst.setString(1,resume_first_name.getText());
                        pst.setString(2,resume_second_name.getText());
                        pst.setString(3,resume_email.getText());
                        pst.setString(4,resume_phone_number.getText());
                        pst.setString(8,resume_experience.getText());
                        pst.setString(9,resume_languages.getText());
                        pst.setString(10,resume_about_you.getText());

                    ResultSet rst1 = cn.createStatement().executeQuery("SELECT id FROM countries WHERE name = '"+resume_country.getValue()+"'");
                    while(rst1.next()){
                        pst.setString(5,rst1.getString("id"));
                    }
                    rst1 = cn.createStatement().executeQuery("SELECT id FROM states WHERE name = '"+resume_state.getValue()+"'");
                    while(rst1.next()){
                        pst.setString(6,rst1.getString("id"));
                    }
                    rst1 = cn.createStatement().executeQuery("SELECT id FROM cities WHERE name = '"+resume_city.getValue()+"'");
                    while(rst1.next()){
                        pst.setString(7,rst1.getString("id"));
                    }
                    rst1 = cn.createStatement().executeQuery("SELECT id FROM positions WHERE name = '"+resume_to_position.getValue()+"'");
                    while(rst1.next()){
                        pst.setString(11,rst1.getString("id"));
                    }
                    pst.execute();}catch (Exception e){e.printStackTrace();}
                    System.exit(0);
                    });

                }catch (Exception e){e.printStackTrace();}
    }
}
