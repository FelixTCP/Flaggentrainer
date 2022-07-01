import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

class FalseAlert {

    private static ArrayList<FalseGuess> falseListe = FlagGUI.getFalseListe();

    private static Stage window;
    private static Label rightLabel, falseLabel, loadingBarLabel;
    private static Button nextButton;

    private static ImageView flagImage;

    private static int index;

    private static boolean retry;

    public void display(boolean retry) throws FileNotFoundException {
        window = new Stage();

        index = 0;

        FalseAlert.retry =retry;

        HBox buttons = new HBox();
        Button backButton = new Button("Back");
        System.out.println(falseListe.size());
        if (falseListe.size() == 1) {
            nextButton = new Button("End");
        } else {
            nextButton = new Button("Next");
        }
        backButton.setMinWidth(50);
        nextButton.setMinWidth(50);

        buttons.getChildren().addAll(backButton, nextButton);

        buttons.setAlignment(Pos.CENTER);
        buttons.setMinHeight(100);

        HBox flagPic = new HBox();
        InputStream flagDir = getClass().getResourceAsStream(falseListe.get(index).getFlag());
        Image image = new Image(flagDir);
        flagImage = new ImageView(image);
        flagImage.setPreserveRatio(true);

        flagPic.getChildren().add(flagImage);
        flagPic.setAlignment(Pos.CENTER);
        setNewFlag();


        VBox labels = new VBox();
        falseLabel = new Label();
        rightLabel = new Label();
        loadingBarLabel = new Label();
        falseLabel.setStyle("-fx-text-fill: white; -fx-font-size: 24px;");
        rightLabel.setStyle("-fx-text-fill: white; -fx-font-size: 24px;");
        loadingBarLabel.setStyle("-fx-text-fill: white; -fx-font-size: 24px;");

        labels.getChildren().addAll(falseLabel, rightLabel, loadingBarLabel);
        labels.setAlignment(Pos.BASELINE_LEFT);
        setNewLabels();

        backButton.setOnAction(e -> back());
        nextButton.setOnAction(e -> next());

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(labels);
        borderPane.setCenter(flagPic);
        borderPane.setBottom(buttons);
        borderPane.setStyle("-fx-background-color: #696969;");

        Scene mainScene = new Scene(borderPane, 1920, 1080);

        mainScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
                back();
            }
            if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) {
                next();
            }
        });

        window.setScene(mainScene);
        window.setTitle("Falsche Antworten");
        window.setFullScreenExitHint("");
        window.setFullScreen(true);
        window.setResizable(false);
        window.setX(0);
        window.setY(0);
        window.show();
    }

    private void setNewFlag() throws FileNotFoundException {
        InputStream flagDir = getClass().getResourceAsStream(falseListe.get(index).getFlag());
        Image image = new Image(flagDir);
        flagImage.setImage(image);
    }

    private static void setNewLabels() {
        falseLabel.setText("Falsch: " + falseListe.get(index).getFalseName());
        rightLabel.setText("Richtig: " + FlagGUI.reverseFormat(falseListe.get(index).getRightName()));
        loadingBarLabel.setText("(" + (index + 1) + "/" + falseListe.size() + ")");
    }

    private void back() {
        if (index > 0) {
            try {
                index--;
                setNewFlag();
                setNewLabels();
                if (index + 1 < falseListe.size()) {
                    nextButton.setText("Next");
                }
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
    }

    private void next() {
        if (index + 1 < falseListe.size()) {
            try {
                index++;
                setNewFlag();
                setNewLabels();
                if (!(index + 1 < falseListe.size())) {
                    nextButton.setText("End");
                }
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        } else {
            window.close();
            FinishedAlert.display("Beendet", "Alle Flaggen sind durch! " + Math.round(100 * FlagGUI.richtigCounter / (FlagGUI.richtigCounter + FlagGUI.falschCounter)) + "% " + "richtig benannt!", retry);
        }
    }
}