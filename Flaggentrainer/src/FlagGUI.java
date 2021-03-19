import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FlagGUI extends Application {

    static ArrayList<Land> landListe;

    static {
        try {
            landListe = Listcreation.create();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    TextField inputField;
    Button startButton, checkButton;
    Label resultLabel;
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
        int id = (int) (Math.random() * landListe.size());
        //Das Flammenimage für das GUI wird initialisiert und konfiguriert
        InputStream flagDir = new FileInputStream(landListe.get(id).getFlag());
        Image image = new Image(flagDir);
        ImageView flagImage = new ImageView(image);
        flagImage.setImage(image);
        flagImage.setX(10);
        flagImage.setY(10);
        flagImage.setFitWidth(1000);
        flagImage.setPreserveRatio(true);

        //Das zugehörige Objekt in landListe wird ausgegeben - for Debug
        System.out.println(landListe.get(id).getName());

        //Startmenu wird erstellt
        //Es wird ein Startbutton erstellt
        startButton = new Button("Start");
        //Beim klicken öffnet sich der Trainer
        startButton.setOnAction(e -> window.setScene(mainScene));
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

        //In flagPic wird das Flaggenimage angezeigt
        HBox flagPic = new HBox();
        flagPic.getChildren().add(flagImage);
        flagPic.setAlignment(Pos.CENTER);

        //In eingabe wird die Eingabe getätigt und mit der Lösung verglichen
        HBox eingabe = new HBox();
        inputField = new TextField();
        checkButton = new Button("Check");
        checkButton.setOnAction(event -> {
            String inputString = inputField.getText();
            inputString = format(inputString);
            //Wenn die Eingabe mit dem Namen oder der TDL übereinstimmt
            if (inputString.equals(landListe.get(id).getName()) || inputString.equals(landListe.get(id).getTDL())) {
                System.out.println("True");
                resultLabel.setText("Richtig!");
                resultLabel.setStyle("-fx-text-fill: green; -fx-font-size: 24px;");
            } else /*Wenn die Eingabe nich mit dem Namen oder der TDL übereinstimm*/ {
                System.out.println("False");
                resultLabel.setText("Falsch! Das ist die Flagge von " + reverseFormat(landListe.get(id).getName()));
                resultLabel.setStyle("-fx-text-fill: red; -fx-font-size: 24px;");
            }
        });
        eingabe.getChildren().addAll(inputField, checkButton);
        eingabe.setAlignment(Pos.BASELINE_CENTER);
        eingabe.setMinHeight(100);

        //Alle GUI Elemente werden zusammengefügt
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(flagPic);
        borderPane.setBottom(eingabe);
        borderPane.setTop(resultBox);

        //Das GUI Fenster wird final konfiguruert
        mainScene = new Scene(borderPane, 1400, 900);
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
}