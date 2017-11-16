/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitapp;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Prince
 */
public class CircuitApp {

    /**
     * @param args the command line arguments
     */
    
    private static String goodStr = "";
    private static String badStr = "";
    public static void main(String[] args) {
        // TODO code application logic here
        // The name of the file to open.
        String fileName = "data.txt";

        try {
            // Use this for reading the data.
            System.out.println("Reading filename: " + fileName);
            byte[] buffer = new byte[1000];

            FileInputStream inputStream = new FileInputStream(fileName);
             
            // read fills buffer with data and returns
            // the number of bytes read (which of course
            // may be less than the buffer size, but
            // it will never be more)  
            while((inputStream.read(buffer)) != -1) {
               
            }   
            
            String [] strArr = new String(buffer).split("\n");
            for (int i = 0; i < strArr.length-1; i++) {
                calculateResistance(strArr[i]);
            }
            // Always close files.
            inputStream.close(); 
            
            writeGoods(goodStr);
            
            System.out.println("The following are bad designs:");
            System.out.println(badStr);
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
    
    private static void calculateResistance(String buffer){
        String [] resistanceArr = buffer.split("\\s+");
        float r1 = Float.parseFloat(resistanceArr[0]);
        float r2 = Float.parseFloat(resistanceArr[1]);
        float r3 = Float.parseFloat(resistanceArr[2]);
        float r4 = Float.parseFloat(resistanceArr[3]);
        float r5 = Float.parseFloat(resistanceArr[4]);
        float r6 = Float.parseFloat(resistanceArr[5]);
        
        float result = ((r1+r2)*(r4*r6))/((r3+r4)*(r1*r5));
        
        if(result == 7.5){
            goodStr = goodStr + buffer + "\n";
        }
        else{
            badStr = badStr + buffer + "\n";
        }
    }
    
    private static void writeGoods(String buffer){
        System.out.println("Saving good designs to good.txt");        
        String fileName = "good.txt";

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.append(buffer);

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
}