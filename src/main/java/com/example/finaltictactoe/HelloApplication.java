package com.example.finaltictactoe;

import java.util.Optional;

import com.example.finaltictactoe.controller.SOSGame;
import com.example.finaltictactoe.controller.SOSGame;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import com.example.finaltictactoe.viewer.SOSGameView;
import com.example.finaltictactoe.viewer.SOSGameView;

import java.io.IOException;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {

        // get the value of n
        int n = 0;

        // launch an alert to get the value of n
        TextInputDialog inputNDialog = new TextInputDialog("Enter the value of n");
        inputNDialog.setHeaderText("Enter grid size to start the game");
        Optional<String> response = inputNDialog.showAndWait();
        while( true ) {
            if( response.isPresent() ) {
                String input = response.get();
                if( input.length() == 0 ) {
                    Alert alertError = new Alert(AlertType.ERROR);
                    alertError.setTitle("Error");
                    alertError.setContentText("Enter a value for grid size");
                    alertError.showAndWait();
                }
                else {
                    try {
                        n = Integer.parseInt(input);
                        if( n<4 || n>10 ) {
                            Alert alertError = new Alert(AlertType.ERROR);
                            alertError.setTitle("Error");
                            alertError.setContentText("n must be between 4 and 10");
                            alertError.showAndWait();
                        }
                    }
                    catch(NumberFormatException nfe) {
                        Alert alertError = new Alert(AlertType.ERROR);
                        alertError.setTitle("Error");
                        alertError.setContentText("n must be between 4 and 10");
                        alertError.show();
                    }
                }
                if( n>=4 && n<=8 ) {
                    break;
                }
            }
            // show error too?
            response = inputNDialog.showAndWait();
        }


        // create the controller
        SOSGame game = new SOSGame(n);
        // create view
        SOSGameView view = new SOSGameView(game);
        // set view for the game
        game.setView(view);

        // show view
        arg0 = view;
        arg0.setTitle("SOS Game");
        arg0.setResizable(false);
        arg0.show();

        // game.play();
    }
}
