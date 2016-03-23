/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphsort;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/*
 *
 * @author csilva
 */
public class GraphicSortingUI extends Application {
    



    @Override
    public void start(Stage primaryStage) throws Exception{
        
       
        GridPane grid = new GridPane();
        
        //initializing first bubble sort
        GraphicSorting bubble = new GraphicSorting(30, Color.CYAN);
        bubble.setNumbers(); //adding a random set of numbers
        
        //initializing second bubble sort
        GraphicSorting bubble2 = new GraphicSorting(30, Color.CADETBLUE);
        bubble2.setNumbers();
 
        //graphing the initial set of numbers before start
        bubble.graphBubble();
        bubble2.graphBubble();
        
        
        Pane pane1 = bubble.getPane();
        
        Pane pane2 = bubble2.getPane();
        
        Label status = new Label("status: not started");
        
        ProgressBar bar1 = new ProgressBar();
        bar1.setProgress(0);
        bar1.progressProperty().bind(bubble.progressProperty());
        
        Button start = new Button("Start");
        start.setOnAction((ActionEvent event) -> 
        {
            status.setText("status: running");
            new Thread(bubble).start();
            new Thread(bubble2).start();
            
        });
        
        Button cancel = new Button("Cancel");
        cancel.setOnAction((ActionEvent event) -> 
        {
            status.setText("status: cancelled");
            bubble.cancel(true);
            bubble2.cancel(true);
            
        });
        
        grid.add(status,0, 3);
        grid.add(pane1, 0, 1);
        grid.add(pane2, 1, 1);
        grid.add(start, 0, 4);
        grid.add(cancel, 1, 4);
        
        Scene scene = new Scene(grid, 600, 250);

        primaryStage.setTitle("Bubble Sort Demostration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
}
