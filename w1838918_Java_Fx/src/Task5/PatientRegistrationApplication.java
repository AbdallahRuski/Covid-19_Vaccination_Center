package Task5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PatientRegistrationApplication extends Application {

    static int age;
    static ComboBox boothnum;
    public static Button receiptButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Patient Registration");

        // Create the registration form grid pane
        GridPane gridPane = createRegistrationFormPane();
        // Add UI controls to the registration form grid pane
        addUIControls(gridPane);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage	
        primaryStage.setScene(scene);

        primaryStage.show();
      
    }

    private GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints
        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("ADD PATIENT DETAILS");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        // Add First Name Label
        Label firstnameLabel = new Label("First Name : ");
        gridPane.add(firstnameLabel, 0, 1);

        // Add First Name Text Field
        TextField firstnameField = new TextField();
        firstnameField.setPrefHeight(40);
        gridPane.add(firstnameField, 1, 1);

        // Add Surname Label
        Label surnameLabel = new Label("Surname : ");
        gridPane.add(surnameLabel, 0, 2);

        // Add Surname Text Field
        TextField surnameField = new TextField();
        surnameField.setPrefHeight(40);
        gridPane.add(surnameField, 1, 2);

        // Add City Label
        Label cityLabel = new Label("City : ");
        gridPane.add(cityLabel, 0, 3);

        // Add City Field
        TextField cityField = new TextField();
        cityField.setPrefHeight(40);
        gridPane.add(cityField, 1, 3);

        // Add NICPassport Label
        Label NICPassportLabel = new Label("NIC or Passport : ");
        gridPane.add(NICPassportLabel, 0, 4);

        // Add City Field
        TextField NICPassportField = new TextField();
        NICPassportField.setPrefHeight(40);
        gridPane.add(NICPassportField, 1, 4);

        // Add Age Label
        Label ageLabel = new Label("Age : ");
        gridPane.add(ageLabel, 0, 5);

        // Add City Field
        TextField ageField = new TextField();
        ageField.setPrefHeight(40);
        gridPane.add(ageField, 1, 5);

        // Add VaccineType Label
        Label vaccinetypeLabel = new Label("Vaccine Type : ");
        gridPane.add(vaccinetypeLabel, 0, 6);

        // Create Vaccination Type Array
        String vaccines[] = {"AstraZeneca", "Sinopharm", "Pfizer"};

        // Create a combo box
        ComboBox vaccinetype = new ComboBox(FXCollections.observableArrayList(vaccines));
        gridPane.add(vaccinetype, 1, 6);

        // Add VaccineType Label
        Label BoothNoLabel = new Label("Booth Number : ");
        gridPane.add(BoothNoLabel, 0, 7);

        // Create Vaccination Type Array
        String boothnumaztrazenica[] = {"0", "1"};
        String boothnumsinopharm[] = {"2", "3"};
        String boothnumpfizer[] = {"4", "5"};

        Button submitButton = new Button("Add Patient");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(200);
        gridPane.add(submitButton, 1, 8, 1, 1);
        GridPane.setHalignment(submitButton, HPos.RIGHT);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

        Button clearButton = new Button("Clear");
        clearButton.setPrefHeight(40);
        clearButton.setDefaultButton(true);
        clearButton.setPrefWidth(100);
        gridPane.add(clearButton, 2, 8, 1, 1);
        GridPane.setHalignment(clearButton, HPos.LEFT);
        GridPane.setMargin(clearButton, new Insets(20, 0, 20, 0));

        receiptButton = new Button("Generate Receipt");
        receiptButton.setPrefHeight(40);
        receiptButton.setDefaultButton(true);
        receiptButton.setPrefWidth(200);
        gridPane.add(receiptButton, 0, 9, 2, 1);
        GridPane.setHalignment(receiptButton, HPos.RIGHT);
        GridPane.setMargin(receiptButton, new Insets(20, 0, 20, 0));
        //receiptButton.setDisable(true);

        vaccinetype.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {

                if (vaccinetype.getValue().toString().equals("AstraZeneca")) {
                    boothnum = new ComboBox(FXCollections.observableArrayList(boothnumaztrazenica));
                    gridPane.add(boothnum, 1, 7);
                } else if (vaccinetype.getValue().toString().equals("Sinopharm")) {
                    boothnum = new ComboBox(FXCollections.observableArrayList(boothnumsinopharm));
                    gridPane.add(boothnum, 1, 7);
                } else if (vaccinetype.getValue().toString().equals("Pfizer")) {
                    boothnum = new ComboBox(FXCollections.observableArrayList(boothnumpfizer));
                    gridPane.add(boothnum, 1, 7);
                }
            }

        });

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //Presence check for values at input fields

                    if (firstnameField.getText().isEmpty()) {
                        showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Input Error!", "Please enter your First Name");
                        return;
                    }
                    if (surnameField.getText().isEmpty()) {
                        showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Input Error!", "Please enter your Surname");
                        return;
                    }
                    if (cityField.getText().isEmpty()) {
                        showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Input Error!", "Please enter a City");
                        return;
                    }

                    if (NICPassportField.getText().isEmpty()) {
                        showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Input Error!", "Please enter a NIC or Passport");
                        return;
                    }

                    if (ageField.getText().isEmpty()) {
                        showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Input Error!", "Please enter a age");
                        return;
                    }

                    if (vaccinetype.getValue().equals("")) {
                        showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Input Error!", "Please select the Vaccine Type");
                        return;
                    }

                    try {
                        age = Integer.parseInt(ageField.getText());
                    } catch (NumberFormatException e) {
                        showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Input Error!", "Please add a numeric value as Age");
                        return;
                    }

                    new VaccinationCenter().inputPatientDetails(firstnameField.getText(), surnameField.getText(), cityField.getText(), NICPassportField.getText(), age, vaccinetype.getValue().toString(), boothnum.getValue().toString());
                                      
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                firstnameField.setText("");
                surnameField.setText("");
                cityField.setText("");
                NICPassportField.setText("");
                ageField.setText("");
                vaccinetype.setValue("");
                boothnum.setValue("");
            }
        });

        receiptButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //Presence check for values at input fields

                    Stage receiptStage = new Stage();
                    receiptStage.setTitle("Patient Registration Receipt");

                    // Create the registration form grid pane
                    GridPane gridPane = createReceiptFormPane();
                    // Add UI controls to the registration form grid pane
                    addReceiptUIControls(gridPane, firstnameField.getText(), surnameField.getText(), cityField.getText(), NICPassportField.getText(), ageField.getText(), vaccinetype.getValue().toString(), boothnum.getValue().toString());
                    // Create a scene with registration form grid pane as the root node
                    Scene scene = new Scene(gridPane, 450, 500);
                    // Set the scene in primary stage	
                    receiptStage.setScene(scene);

                    receiptStage.show();

                    firstnameField.setText("");
                    surnameField.setText("");
                    cityField.setText("");
                    NICPassportField.setText("");
                    ageField.setText("");
                    vaccinetype.setValue("");
                    boothnum.setValue("");

                    // stop();
                    //  Platform.exit();
                } catch (Exception ex) {
                    //Clear fields

                    ex.printStackTrace();
                }

            }
        });

    }

    private void addReceiptUIControls(GridPane gridPane, String strfirstname, String strsurname, String strcity, String strNICpassport, String strage, String strvaccinetype, String strboothnum) {
        // Add Header
        Label headerLabel = new Label("PATIENT RECEIPT");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        // Add First Name Label
        Label firstnameLabel = new Label("First Name : ");
        gridPane.add(firstnameLabel, 0, 1);

        // Add First Name Value
        Label firstname = new Label(strfirstname);
        gridPane.add(firstname, 1, 1);

        // Add Surname Label
        Label surnameLabel = new Label("Surname : ");
        gridPane.add(surnameLabel, 0, 2);

        // Add Surname Value
        Label surname = new Label(strsurname);
        surname.setPrefHeight(40);
        gridPane.add(surname, 1, 2);

        // Add City Label
        Label cityLabel = new Label("City : ");
        gridPane.add(cityLabel, 0, 3);

        // Add City Value
        Label city = new Label(strcity);
        gridPane.add(city, 1, 3);

        // Add NICPassport Label
        Label NICPassportLabel = new Label("NIC or Passport : ");
        gridPane.add(NICPassportLabel, 0, 4);

        // Add City Value
        Label NICPassport = new Label(strNICpassport);
        gridPane.add(NICPassport, 1, 4);

        // Add Age Label
        Label ageLabel = new Label("Age : ");
        gridPane.add(ageLabel, 0, 5);

        // Add Age Value
        Label age = new Label(strage);
        gridPane.add(age, 1, 5);

        // Add VaccineType Label
        Label vaccinetypeLabel = new Label("Vaccine Type : ");
        gridPane.add(vaccinetypeLabel, 0, 6);

        // Add VaccineType Value
        Label vaccinetype = new Label(strvaccinetype);
        gridPane.add(vaccinetype, 1, 6);

        // Add BoothNo Label
        Label BoothNoLabel = new Label("Booth Number : ");
        gridPane.add(BoothNoLabel, 0, 7);

        // Add BoothNo Value
        Label boothNo = new Label(strboothnum);
        gridPane.add(boothNo, 1, 7);

        // Display Date Time
        Label datetimeLabel = new Label(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(LocalDateTime.now()) + "");
        datetimeLabel.setPrefHeight(100);
        datetimeLabel.setPrefWidth(300);
        gridPane.add(datetimeLabel, 0, 8);

    }

    static GridPane createReceiptFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints
        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
