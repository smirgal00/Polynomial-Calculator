package GUI;

import GUI.*;
import Polynomial.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.applet.AppletContext;
import java.awt.*;
import java.util.ArrayList;

public class Principal extends Application {
    GridPane panel = new GridPane();
    Button[] ops = new Button[6];
    TextArea p1 = new TextArea();
    TextArea p2 = new TextArea();
    Label rez = new Label();
    Polinom p = new Polinom();
    Polinom q = new Polinom();
    Polinom res = new Polinom();
    Operations op = new Operations();

    private void createFrame() {
        for (int i = 0; i < 2; i++) {
            ColumnConstraints c = new ColumnConstraints();
            c.setPercentWidth(1280.0 / 2);
            panel.getColumnConstraints().add(c);
        }
        for (int i = 0; i < 6; i++) {
            RowConstraints r = new RowConstraints();
            r.setPercentHeight(720.0 / 6);
            panel.getRowConstraints().add(r);
        }
    }

    private void textInit() {
        p1.setStyle("-fx-text-alignment: center;" +
                "-fx-font-size: 30;");
        p1.setLayoutX(360.0 / 6);
        p1.setLayoutY(1280.0 / 6);

        p2.setStyle("-fx-text-alignment: center;" +
                "-fx-font-size: 30;");
        p2.setLayoutX(360.0 / 6);
        p2.setLayoutY(1280.0 / 6);

        rez.setStyle("-fx-text-alignment: center;" +
                "-fx-font-size: 30;");
        rez.setLayoutX(360.0 / 6);
        rez.setLayoutY(1280.0 / 6);
    }

    private void buttonInit() {
        for (int i = 0; i < 6; i++) {
            ops[i] = new Button();
            ops[i].setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));
            ops[i].setStyle("-fx-border-color: black; " +
                    "-fx-font-size: 20;");
            ops[i].setPrefWidth(1280.0 / 2);
            ops[i].setPrefHeight(720.0 / 6);
        }

        ops[0].setText("+");
        ops[1].setText("-");
        ops[2].setText("X");
        ops[3].setText("/");
        ops[4].setText("∂");
        ops[5].setText("∫");
    }

    private void setResult(String rez) {
        this.rez.setText(rez);
    }

    private void buttonListeners() {
        ops[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setResult("");
                try {
                    if (p.readPolinom(p1.getText()) && q.readPolinom(p2.getText())) {
                        res = op.addition(p.getList(), q.getList());
                        setResult(res.printPolinom());
                    }
                } catch (InvalidInput invalidInput) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Reintroduceti polinoamele!");
                    alert.setTitle("Error!");
                    alert.setContentText(invalidInput.getMessage());
                    alert.showAndWait();
                }
            }
        });

        ops[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setResult("");
                try {
                    if (p.readPolinom(p1.getText()) && q.readPolinom(p2.getText())) {
                        res = op.substraction(p.getList(), q.getList());
                        if(res.getList().size() == 0) {
                            setResult("0");
                        }
                        else {
                            setResult(res.printPolinom());
                        }
                    }
                } catch (InvalidInput invalidInput) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Reintroduceti polinoamele!");
                    alert.setTitle("Error!");
                    alert.setContentText(invalidInput.getMessage());
                    alert.showAndWait();
                }
            }
        });

        ops[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setResult("");
                try {
                    if (p.readPolinom(p1.getText()) && q.readPolinom(p2.getText())) {
                        res = op.multiplication(p.getList(), q.getList());
                        setResult(res.printPolinom());
                    }
                } catch (InvalidInput invalidInput) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Reintroduceti polinoamele!");
                    alert.setTitle("Error!");
                    alert.setContentText(invalidInput.getMessage());
                    alert.showAndWait();
                }
            }
        });

        ops[3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setResult("");
                try {
                    if (p.readPolinom(p1.getText()) && q.readPolinom(p2.getText())) {
                        ArrayList<Polinom> temp = new ArrayList<>();
                        temp = op.division(p, q);
                        setResult(temp.get(0).printPolinom() + " R: " + temp.get(1).printPolinom());
                    }
                } catch (InvalidInput invalidInput) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Reintroduceti polinoamele!");
                    alert.setTitle("Error!");
                    alert.setContentText(invalidInput.getMessage());
                    alert.showAndWait();
                }
            }
        });

        ops[4].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setResult("");
                try {
                    if (p.readPolinom(p1.getText())) {
                        res = op.derivative(p.getList());
                        setResult(res.printPolinom());
                    }
                } catch (InvalidInput invalidInput) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Reintroduceti polinoamele!");
                    alert.setTitle("Error!");
                    alert.setContentText(invalidInput.getMessage());
                    alert.showAndWait();
                }
            }
        });

        ops[5].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                setResult("");
                try {
                    if (p.readPolinom(p1.getText())) {
                        res = op.integration(p.getList());
                        setResult(res.printPolinom());
                    }
                } catch (InvalidInput invalidInput) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Reintroduceti polinoamele!");
                    alert.setTitle("Error!");
                    alert.setContentText(invalidInput.getMessage());
                    alert.showAndWait();
                }
            }
        });
    }

    private void addToPane() {
        //panel.add(add, 0, 0);
        panel.add(p1, 0, 0, 1, 2);
        panel.add(p2, 0, 2, 1, 2);
        panel.add(rez, 0, 4, 1, 2);

        panel.add(ops[0], 1, 0, 1, 1);
        panel.add(ops[1], 1, 1, 1, 1);
        panel.add(ops[2], 1, 2, 1, 1);
        panel.add(ops[3], 1, 3, 1, 1);
        panel.add(ops[4], 1, 4, 1, 1);
        panel.add(ops[5], 1, 5, 1, 1);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI.fxml"));
        primaryStage.setTitle("Hello World");
        createFrame();
        buttonInit();
        buttonListeners();
        textInit();
        addToPane();
        primaryStage.setScene(new Scene(panel, 1280, 360));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

}
