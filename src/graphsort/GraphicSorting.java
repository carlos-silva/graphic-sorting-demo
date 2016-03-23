/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphsort;

import java.util.Random;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author csilva
 */
public class GraphicSorting extends Task<Void>{
    
    Pane pane = new Pane();
    Color color;
    int[] numbers = new int[20];
    int max_random = 30;
    int speed;
    
    Random random = new Random();
    
     public GraphicSorting(int max_random, Color color){
        this.max_random = max_random;
        this.color = color;
     }
    
    public int[] getNumbers(){
        return numbers;
    }
    public void setNumbers(){
        
         for(int i = 0; i < numbers.length; i++ )
             numbers[i] = random.nextInt(max_random);
    }
    
    public Pane getPane(){
        return pane;
    }

    
    public void graphBubble(){
       
        pane.getChildren().clear();
        
        int maxNumberOfArray = numbers[0];
        for (int i = 0; i < numbers.length; ++i)
        {
            if (numbers[i] > maxNumberOfArray)
                maxNumberOfArray = numbers[i];
        }
        for (int i = 0; i < numbers.length; ++i)
        {
            int heightRectangle = numbers[i] * 5; 
            int widthRectangle = 5;
            int pos = 200-heightRectangle;
            Rectangle x = new Rectangle(i*10, pos, widthRectangle, heightRectangle);
            
            x.setFill(color);
            pane.getChildren().add(x);
        }
        
    }


    @Override
    protected Void call() throws Exception {
      
        int temp;
        Pane panel = new Pane();
        for(int j = 0; j < numbers.length; ++j)
        {
            for(int i = 1; i < numbers.length; ++i)
            {

                if(numbers[i - 1] > numbers[i])
                {
                        temp = numbers[i - 1];
                        numbers[i - 1] = numbers[i];
                        numbers[i] = temp;

                         Platform.runLater(new Runnable() {
                     @Override public void run() {
                         graphBubble();
                     }
                 });
                       

                try
                {
                    Thread.sleep(50);
                }
                 catch( InterruptedException e )
                {
                } 
                }
            }
        }
     
          return null;
    }
    
}
    
    
