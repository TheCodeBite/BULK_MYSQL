/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewbulkmysql;

import java.sql.Connection;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Kevin
 */
public class ViewBulkMySQL extends Application {
    Connection conexion = new Conexion("root", "", "inegi", "localhost", "3306").mysql();
    
    @Override
    public void start(Stage primaryStage) {
        
        
        
        Button btn = new Button();
        btn.setText("Seleccione Archivo");
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btn.setDisable(true);
                Archivo file = new Archivo(conexion);
                btn.setDisable(false);
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /*public void SplitCuerpo(String cuerpo) {
        String [] sentencias = cuerpo.split("\n");
        
        for(String query : sentencias) {
           String response = new Conexion().ExcuteQuery(query, conexion);
        }
    }*/

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
