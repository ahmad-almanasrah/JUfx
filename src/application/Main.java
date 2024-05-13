package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class Main extends Application {

    String num; // Variable to store user input

    // Method to check if input string is a valid integer
    int checker(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        return 0;
    }

    // Method to check if the number is larger than two
    int largerThanTwo(int num, char type) {
        if (type == 'g' && num <= 2) {
            System.out.println("Please enter a number greater than two.");
            return 0;
        } else if (type == 'l' && num < 2) {
            System.out.println("Please enter a number greater than one.");
            return 0;
        }
        return num;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Main Screen");

        // Creating UI components using Java FX
        RadioButton pGButton = new RadioButton("Polygon");
        RadioButton pLButton = new RadioButton("Polyline");
        ToggleGroup toggle = new ToggleGroup();
        Button draw = new Button("Draw");
        TextField getN = new TextField();
        GridPane pSP = new GridPane();

        // Setting toggle group for radio buttons
        pGButton.setToggleGroup(toggle);
        pLButton.setToggleGroup(toggle);

        // Adding components to the grid pane
        pSP.add(pGButton, 1, 1);
        pSP.add(pLButton, 1, 2);
        pSP.add(getN, 2, 1);
        pSP.add(draw, 2, 2);
        pSP.setHgap(3);
        pSP.setVgap(3);
        pSP.setAlignment(Pos.CENTER);

        // Action event for text field
        getN.setOnAction(e -> {
            num = getN.getText();
        });

        // Action event for draw button
        draw.setOnAction(e -> {
            num = getN.getText(); // Get user input
            int nums = checker(num); // Check if input is valid
            if (pGButton.isSelected()) { // If Polygon radio button is selected
                nums = largerThanTwo(nums, 'g'); // Check if number is larger than two
                if (nums == 0)
                    return;

                // Create a new stage for Polygon
                Stage pGstage = new Stage();
                Polygon pg = new Polygon();
                Button color = new Button("Color"); // Button to change color
                pGstage.setTitle("Polygon");
                Double[] points = new Double[nums * 2];
                Double x = 400.0, y = 400.0, radius = 100.0;
                // Calculate points for the polygon
                for (int i = 0; i < nums * 2; i += 2) {
                    Double angle = i * Math.PI / nums;
                    points[i] = x + radius * Math.cos(angle);
                    points[i + 1] = y + radius * Math.sin(angle);
                }
                pg.getPoints().addAll(points); // Add points to the polygon
                // Action event for color button
                color.setOnAction(e1 -> {
                    pg.setFill(Color.LAVENDER); // Set fill color
                });
                // Add components to the grid pane
                GridPane pgp = new GridPane();
                pgp.add(pg, 1, 1);
                pgp.add(color, 1, 2);
                pgp.setAlignment(Pos.CENTER);
                Scene pgs = new Scene(pgp, 400, 400);
                pGstage.setScene(pgs);
                pGstage.show();
            } else if (pLButton.isSelected()) { // If Polyline radio button is selected
                nums = largerThanTwo(nums, 'l'); // Check if number is larger than one
                if (nums == 0)
                    return;

                // Create a new stage for Polyline
                Stage pLstage = new Stage();
                Polyline pl = new Polyline();
                Button color = new Button("Color"); // Button to change color
                pLstage.setTitle("Polyline");
                Double[] points = new Double[nums * 2];
                Double x = 400.0, y = 400.0, radius = 100.0;
                // Calculate points for the polyline
                for (int i = 0; i < nums * 2; i += 2) {
                    Double angle = i * Math.PI / nums;
                    points[i] = x + radius * Math.cos(angle);
                    points[i + 1] = y + radius * Math.sin(angle);
                }
                pl.getPoints().addAll(points); // Add points to the polyline
                // Action event for color button
                color.setOnAction(e1 -> {
               	 pl.setFill(Color.LAVENDER);
            	 pl.setStroke(Color.AQUAMARINE);
                });
                // Add components to the grid pane
                GridPane plp = new GridPane();
                plp.add(pl, 2, 2);
                plp.add(color, 2, 3);
                plp.setVgap(9);
                plp.setHgap(9);
                plp.setAlignment(Pos.CENTER);
                Scene pls = new Scene(plp, 400, 400);
                pLstage.setScene(pls);
                pLstage.show();
            }
        });

        // Create and show the main scene
        Scene scene = new Scene(pSP, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
