import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

class FinishedAlert {
    public static void display(String title, String message, boolean retry) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            window.close();
            FlagGUI.close();
        });

        Button replayButton = new Button("Replay");
        replayButton.setOnAction(e -> {
            window.close();
            FlagGUI.restart();
        });

        HBox midlayout = new HBox();
        midlayout.getChildren().addAll(label);
        midlayout.setAlignment(Pos.CENTER);

        HBox botlayout = new HBox();
        if(retry) botlayout.getChildren().addAll(replayButton, closeButton);
        else botlayout.getChildren().addAll(closeButton);
        botlayout.setAlignment(Pos.CENTER);

        BorderPane bp = new BorderPane();
        bp.setCenter(midlayout);
        bp.setBottom(botlayout);

        Scene scene = new Scene(bp, 320, 70);
        window.setScene(scene);
        window.showAndWait();
    }
}