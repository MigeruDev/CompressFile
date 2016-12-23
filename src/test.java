
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author The Worst One
 */
public class test {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        int k=0;
        int entero=0;
        String ruta="TextoO";
        
        File archivoR = new File(ruta+".txt");
        FileReader reader = new FileReader(archivoR);
            File archivo = new File(ruta+"ASCII.dat");
        try (FileOutputStream escritor = new FileOutputStream(archivo)) {
            BufferedReader buffReader = new BufferedReader(reader);
            String s = buffReader.readLine();
           /* while (s!=null) {
                byte[] representacion = s.getBytes(StandardCharsets.US_ASCII);
                escritor.write(representacion);
                System.out.println("Este es mi representacion: "+new String(representacion,StandardCharsets.US_ASCII));
                System.out.println(Arrays.toString(representacion));
                s = buffReader.readLine();
            } */
            
            while(s!=null){
                //System.out.println("Soy la pasada: "+count); count++;
                int len = s.length();
                for(int i=0;i<=len-1;i++){
                    Character letra = s.charAt(i);
                    byte[] representacion = s.getBytes(StandardCharsets.US_ASCII);
                    for(byte j: representacion){
                        if (k == 8){
                            escritor.write(entero);
                            entero = j*(int) Math.pow(2,7);
                            k = 1;
                        }
                        else{
                            entero += j*(int) Math.pow(2,7-k);
                            k += 1;
                        }
                    }     
                }
                //s = buffReader.readLine();
                if (s.contentEquals("\n")){
                    byte[] representacion = s.getBytes(StandardCharsets.US_ASCII);
                    for(byte j: representacion){
                        if (k == 8){
                            escritor.write(entero);
                            entero = j*(int) Math.pow(2,7);
                            k = 1;
                        }
                        else{
                            entero += j*(int) Math.pow(2,7-k);
                            k += 1;
                        }    
                    }
                }
                s = buffReader.readLine();
                escritor.write(entero);
            }
            
            
            
            
        }
    }
}
