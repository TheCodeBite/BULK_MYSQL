/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewbulkmysql;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author Kevin
 */
public class Archivo {

    final FileDialog fc;
    String name;
    Connection conexion;

    public Archivo(Connection conexion) {
        this.conexion = conexion;
        fc = new FileDialog((Frame) null, "Selecione un archvio");
        fc.setMode(FileDialog.LOAD);
        fc.setVisible(true);
        
        System.out.println("DATOS DEL ARCHVIO: ");
        System.out.println(fc.getDirectory() + fc.getFile());
        System.out.println("=======================");
        leerArchvio();

    }

    public void leerArchvio() {
        int contador = 0;
        ArrayList<String> Errores = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(fc.getDirectory() + fc.getFile())));
            String text = null;
            while ((text = reader.readLine()) != null) {
                
                if(text.length() > 5) {
                    contador ++;
                    String response = new Conexion().ExcuteQuery(text, conexion);
                    if(response == ""){
                        Errores.add(text);
                    }
                }else{
                    System.out.println("LINEA VACIA");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
        
        if(!Errores.isEmpty()){
            System.out.println("se encontraron " + Errores.size() + " Errores");
            Errores(Errores);
        }
        System.out.println("se agregaron " + contador + " Elementos");
        System.out.println("Termiando....");
    }
    
    public void Errores(ArrayList<String> errores){
        ArrayList<String> newErrors = new ArrayList<>();
        
        for(String error : errores) {
            String response = new Conexion().ExcuteQuery(error, conexion);
            System.out.println(error);
            if(response == ""){
                newErrors.add(error);
            }
        }
        
       System.out.println("se volvieron a encontrar " + newErrors.size() + " Errores");
        
        if(!newErrors.isEmpty()){
            Errores(newErrors);
        
        }
    }

}
