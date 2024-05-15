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
    // Boolean flags to track whether a polygon or polyline is opened
    Boolean isPolygonOpened = false;
    Boolean isPolylineOpened = false;
    // Variable to store the number of sides or points entered by the user
    int n;
    // Variable to store user input
    String num;

    @Override
    public void start(Stage primaryStage) {
        // Set the title of the primary stage
        primaryStage.setTitle("Primary screen");

        // Create radio buttons for selecting between polygon and polyline
        RadioButton polygonRadioButton = new RadioButton("polygon");
        RadioButton polylineRadioButton = new RadioButton("polyline");
        // Create a toggle group for the radio buttons
        ToggleGroup toggle = new ToggleGroup();
        // Create a text field for user input
        TextField getText = new TextField();
        // Create grid panes for the primary screen, polygon screen, and polyline screen
        GridPane psPane = new GridPane();
        GridPane polygonPane = new GridPane();
        GridPane polylinePane = new GridPane();
        // Create stages for displaying the polygon and polyline screens
        Stage polylineStage = new Stage();
        Stage polygonStage = new Stage();
        // Create polygon and polyline objects
        Polygon polygon = new Polygon();
        Polyline polyline = new Polyline();
        // Create buttons for drawing and changing colors
        Button draw = new Button("Draw");
        Button polygonColor = new Button("color");
        Button polylineColor = new Button("color");
        // Create scenes for the primary screen, polygon screen, and polyline screen
        Scene psScene = new Scene(psPane, 400, 400);
        Scene polygonScene = new Scene(polygonPane, 400, 400);
        Scene polylineScene = new Scene(polylinePane, 400, 400);
        // Set default values for drawing shapes
        Double x = 400.0, y = 400.0, radius = 100.0;

        // Set titles for the polygon and polyline screens
        polygonStage.setTitle("polygon screen");
        polylineStage.setTitle("polyline screen");

        // Add radio buttons to the toggle group
        polygonRadioButton.setToggleGroup(toggle);
        polylineRadioButton.setToggleGroup(toggle);

        // Add UI elements to the primary screen grid pane
        psPane.add(polygonRadioButton, 1, 1);
        psPane.add(polylineRadioButton, 1, 2);
        psPane.add(getText, 2, 1);
        psPane.add(draw, 2, 2);
        psPane.setHgap(9);
        psPane.setVgap(9);
        psPane.setAlignment(Pos.CENTER);

        // Event handler for text field input
        getText.setOnAction(e -> {
            num = getText.getText();
            try {
                n = Integer.parseInt(num);
                if (2 > n) {
                    System.out.println("please enter a number that is greater or equal to two");
                    n = -1;
                }
            } catch (NumberFormatException ex) {
                System.out.println("please enter a valid integer number");
                n = -1;
            } catch (Exception ex) {
                System.out.println("please enter a valid number");
                n = -1;
            }
        });
        
     // Set onCloseRequest event handler for polygon stage
        polygonStage.setOnCloseRequest(event -> {
            isPolygonOpened = false; // Set isOpened to false when the polygon stage is closed
            polygonPane.getChildren().clear();
            polygon.getPoints().clear();
        });

        // Set onCloseRequest event handler for polyline stage
        polylineStage.setOnCloseRequest(event -> {
            isPolylineOpened = false; // Set isOpened to false when the polyline stage is closed
            polylinePane.getChildren().clear();
            polyline.getPoints().clear();

        });


        // Event handler for the draw button
        draw.setOnAction(e1 -> {
            if (polygonRadioButton.isSelected()) {
                if (!isPolygonOpened) {
                    if (n == 2 || n == -1) {
                        System.out.println("please enter a number greater than two");
                        return;
                    }
                    isPolygonOpened = true;
                    Double[] points = new Double[n * 2];
                    for (int i = 0; i < n * 2; i += 2) {
                        Double angle = i * Math.PI / n;
                        points[i] = x + radius * Math.cos(angle);
                        points[i + 1] = y + radius * Math.sin(angle);
                    }
                    // Draw and display the polygon with default black fill color
                    polygon.getPoints().addAll(points);
                    polygonPane.add(polygon, 2, 2);
                    polygonPane.add(polygonColor, 2, 3);

                    // Event handler for changing the polygon color
                    polygonColor.setOnAction(e2 -> {
                        polygon.setFill(Color.MEDIUMPURPLE);
                    });

                    polygonPane.setAlignment(Pos.CENTER);
                    polygonStage.setScene(polygonScene);
                    polygonStage.show();
                } else {
                	if (n == 2 || n == -1) {
                        System.out.println("please enter a number greater than two");
                        return;}
                    // Clear previous polygon and redraw with default black fill color
                    polygonPane.getChildren().clear();
                    polygon.getPoints().clear();
                    Double[] points = new Double[n * 2];
                    for (int i = 0; i < n * 2; i += 2) {
                        Double angle = i * Math.PI / n;
                        points[i] = x + radius * Math.cos(angle);
                        points[i + 1] = y + radius * Math.sin(angle);
                    }
                    polygon.setFill(Color.BLACK);
                    polygon.getPoints().addAll(points);
                    // Event handler for changing the polygon color
                    polygonColor.setOnAction(e2 -> {
                        polygon.setFill(Color.MEDIUMPURPLE);
                    });
                    polygonPane.add(polygonColor, 2, 3);
                    polygonPane.add(polygon, 2, 2);
                    polygonPane.setAlignment(Pos.CENTER);
                }
            } else if (polylineRadioButton.isSelected()) {
                if (!isPolylineOpened) {
                    if (n == -1) {
                        System.out.println("please enter a number greater than zero");
                        return;
                    }
                    isPolylineOpened = true;
                    Double[] points = new Double[n * 2];
                    for (int i = 0; i < n * 2; i += 2) {
                        Double angle = i * Math.PI / n;
                        points[i] = x + radius * Math.cos(angle);
                        points[i + 1] = y + radius * Math.sin(angle);
                    }
                    // Draw and display the polyline with default black fill color and black stroke color
                    polyline.getPoints().addAll(points);
                    polylinePane.add(polyline, 2, 2);
                    polylinePane.add(polylineColor, 2, 3);

                    // Event handler for changing the polyline color
                    polylineColor.setOnAction(e2 -> {
                        polyline.setFill(Color.MEDIUMPURPLE);
                        polyline.setStroke(Color.BLACK);
                    });

                    polylinePane.setAlignment(Pos.CENTER);
                    polylineStage.setScene(polylineScene);
                    polylineStage.show();
                } else {
                	if (n == -1) {
                        System.out.println("please enter a number greater than zero");
                        return;
                    }
                    // Clear previous polyline and redraw with default white fill color and black stroke color
                    polylinePane.getChildren().clear();
                    polyline.getPoints().clear();
                    Double[] points = new Double[n * 2];
                    for (int i = 0; i < n * 2; i += 2) {
                        Double angle = i * Math.PI / n;
                        points[i] = x + radius * Math.cos(angle);
                        points[i + 1] = y + radius * Math.sin(angle);
                    }
                    polyline.getPoints().addAll(points);
                    polyline.setFill(Color.WHITESMOKE);
                    polyline.setStroke(Color.BLACK);
                    // Event handler for changing the polyline color
                    polylineColor.setOnAction(e2 -> {
                        polyline.setFill(Color.MEDIUMPURPLE);
                        polyline.setStroke(Color.BLACK);
                    });
                    polylinePane.add(polylineColor, 2, 3);
                    polylinePane.add(polyline, 2, 2);
                    polylinePane.setAlignment(Pos.CENTER);
                }
            }

        });

        // Set the primary scene and display the primary stage
        primaryStage.setScene(psScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the application
        launch(args);
    }
}
