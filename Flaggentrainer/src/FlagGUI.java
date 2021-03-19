import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class FlagGUI extends Application {

    static ArrayList<Land> landListe;
    static int richtigCounter;
    static int falschCounter;

    static {
        try {
            landListe = Listcreation.create();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    TextField inputField;
    Button startButton, checkButton;
    static Label resultLabel,richtigLabel,falschLabel,prozentLabel;
    Scene startScene, mainScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws IOException {

        //landListe wird erstellt
        landListe = Listcreation.create();


        //Es wird eine zufällige zu erratende Flagge bestimmt
        //Es wird eine Zufällige Land ID generiert
        AtomicInteger id = new AtomicInteger((int) (Math.random() * landListe.size()));
        //Das Flammenimage für das GUI wird initialisiert und konfiguriert
        InputStream flagDir = new FileInputStream(landListe.get(id.get()).getFlag());
        Image image = new Image(flagDir);
        ImageView flagImage = new ImageView(image);
        flagImage.setFitWidth(1000);
        flagImage.setPreserveRatio(true);

        //Das zugehörige Objekt in landListe wird ausgegeben - for Debug
        System.out.println(landListe.get(id.get()).getName());
        //Startmenu wird erstellt
        //Es wird ein Startbutton erstellt
        startButton = new Button("Start");
        //Beim klicken öffnet sich der Trainer
        startButton.setOnAction(event -> {
                window.setScene(mainScene);
                window.setFullScreen(true);
        });
        //Der Knopf wird dem Layout und das Layout der Szene hinzugefügt
        HBox startLayout = new HBox(20);
        startLayout.getChildren().addAll(startButton);
        startLayout.setAlignment(Pos.CENTER);
        startScene = new Scene(startLayout, 250, 200);

        //Flaggentrainer GUI wird erstellt
        //In resultBox wir angezeigt, ob die Antwort richtig war
        HBox resultBox = new HBox();
        resultLabel = new Label("-");
        resultLabel.setStyle("-fx-text-fill: white; -fx-font-size: 24px;");
        resultBox.getChildren().addAll(resultLabel);
        resultBox.setAlignment(Pos.CENTER);

        //In counterBox wird der counter Etabliert
        VBox counterBox = new VBox();
        richtigLabel = new Label();
        String r = "Richtig: ";
        richtigLabel.setText(r + richtigCounter);
        richtigLabel.setStyle("-fx-font-size: 24px;");
        falschLabel = new Label();
        String f = "Falsch: ";
        falschLabel.setText(f + falschCounter);
        falschLabel.setStyle("-fx-font-size: 24px;");
        prozentLabel = new Label();
        counterBox.getChildren().addAll(richtigLabel,falschLabel);
        VBox perrcentBox = new VBox();
        String p = "Prozent: ";
        prozentLabel.setText(p + 0+"%");
        prozentLabel.setStyle("-fx-font-size: 24px;");
        perrcentBox.getChildren().addAll(prozentLabel);

        //In flagPic wird das Flaggenimage angezeigt
        HBox flagPic = new HBox();
        flagPic.getChildren().add(flagImage);
        flagPic.setAlignment(Pos.CENTER);

        //In eingabe wird die Eingabe getätigt und mit der Lösung verglichen
        HBox eingabe = new HBox();
        inputField = new TextField();
        checkButton = new Button("Check");
        checkButton.setOnAction(event -> {
            //Die Ergebnisse werden ermittelt
            getResult(inputField.getText(),id);
            //Die Labels werden geupdated
            updateLabels();
            //Ein neues Bild wird ausgelost
            Image newImage = new Image(newFlag(id));
            //Das neue Bild wird gesetzt
            flagImage.setImage(newImage);
            //Das Eingabefeld wird zurückgesetzt
            inputField.clear();
        });
        eingabe.getChildren().addAll(inputField, checkButton);
        eingabe.setAlignment(Pos.BASELINE_CENTER);
        eingabe.setMinHeight(100);

        //Alle GUI Elemente werden zusammengefügt
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(resultBox);
        borderPane.setLeft(counterBox);
        borderPane.setCenter(flagPic);
        borderPane.setRight(perrcentBox);
        borderPane.setBottom(eingabe);

        //Das GUI Fenster wird final konfiguruert
        mainScene = new Scene(borderPane, 1920, 1080);
        window.setScene(startScene);
        window.setTitle("Flaggentrainer");
        window.setResizable(false);
        window.setX(260);
        window.setY(90);
        window.show();
    }

    //Diese Methode gibt einen kleingeschriebenen String ohne Umlaute und Leerzeichen zurück
    private static String format(String s) {
        s = s.toLowerCase();
        s = s.replace(' ', '-');
        s = s.replace("ä", "ae");
        s = s.replace("ö", "oe");
        s = s.replace("ü", "ue");
        s = s.replace("ß", "ss");

        return s;
    }

    //Diese Methode reformatiert den String für die Ausgabe in resultBox
    private static String reverseFormat(String s) {

        s = s.replace('-', ' ');
        s = s.replace("ae", "ä");
        s = s.replace("oe", "ö");
        s = s.replace("ue", "ü");

        String[] nameArray = s.split(" ");

        StringBuilder name = new StringBuilder();

        for (String value : nameArray) {
            if (value.equals("und")) {
                name.append("und ");
            } else {
                name.append(value.substring(0, 1).toUpperCase()).append(value.substring(1)).append(" ");
            }
        }
        return name.toString();
    }

    private static void updateLabels(){
        String r = "Richtig: ";
        String f = "Falsch: ";
        String p = "Prozent: ";

        richtigLabel.setText(r + richtigCounter);
        falschLabel.setText(f + falschCounter);
        prozentLabel.setText(p + Math.round(100*richtigCounter/(richtigCounter+falschCounter))+"%");
    }

    private static InputStream newFlag(AtomicInteger id){
        id.set((int) (Math.random() * landListe.size()));
        InputStream newFlagDir = null;
        try {
            newFlagDir = new FileInputStream(landListe.get(id.get()).getFlag());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return newFlagDir;
    }

    private static void getResult(String s, AtomicInteger id){
        String inputString = s;
        inputString = format(inputString);
        //Wenn die Eingabe mit dem Namen oder der TDL übereinstimmt
        if (inputString.equals(landListe.get(id.get()).getName()) || inputString.equals(landListe.get(id.get()).getTDL())) {
            System.out.println("True");
            richtigCounter ++;
            resultLabel.setText("Richtig!");
            resultLabel.setStyle("-fx-text-fill: green; -fx-font-size: 24px;");
        } else /*Wenn die Eingabe nich mit dem Namen oder der TDL übereinstimm*/ {
            System.out.println("False");
            falschCounter ++;
            resultLabel.setText("Falsch! Das ist die Flagge von " + reverseFormat(landListe.get(id.get()).getName()));
            resultLabel.setStyle("-fx-text-fill: red; -fx-font-size: 24px;");
        }
    }
}